package serviceimple.commodityorderserviceimple;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;

import dao.commoditydao.CommodityDao;
import dao.commoditydetailsdao.CommodityDetailsDao;
import dao.commodityorderdao.CommodityOrderDao;
import po.Commodity;
import po.CommodityDetails;
import po.CommodityOrder;
import po.Order;
import po.Result;
import service.commodityorderservice.CommodityOrderService;
import util.AlipayConfig;
import util.NowTime;

@Service
@Transactional
public class CommodityOrderServiceImple implements CommodityOrderService {

	@Autowired
	private CommodityOrderDao commodityOrderDao;
	@Autowired
	private CommodityDetailsDao commodityDetailsDao;
	@Autowired
	private CommodityDao commodityDao;

	public Result createCommodityOrder(ArrayList<CommodityOrder> commodityOrderList) throws Exception {
		ArrayList<CommodityDetails> commodityDetailsList = new ArrayList<CommodityDetails>();
		// 查询库存是否足够
		for (CommodityOrder commodityOrder : commodityOrderList) {
			CommodityDetails commodityDetails = new CommodityDetails();
			commodityDetails.setId(commodityOrder.getCommodityDetailsId());
			commodityDetails = commodityDetailsDao.findCommodityDetails(commodityDetails).get(0);
			if (commodityDetails.getStock() - commodityOrder.getOrderCount() < 0) {
				Commodity commodity = new Commodity();
				commodity.setId(commodityDetails.getCommodityId());
				commodity = commodityDao.findCommodity(commodity).get(0);
				return Result.fail(commodity.getCommodityName() + "的" + commodityDetails.getSpecifications() + "的库存不足");
			}
			commodityDetailsList.add(commodityDetails);
		}
		// 修改商品库存
		for (int i = 0; i < commodityOrderList.size(); i++) {
			CommodityDetails commodityDetails = new CommodityDetails();
			commodityDetails.setId(commodityDetailsList.get(i).getId());
			int stock = commodityDetailsList.get(i).getStock() - commodityOrderList.get(i).getOrderCount();
			commodityDetails.setStock(stock);
			int len = commodityDetailsDao.updateCommodityDetails(commodityDetails);
			if (len < 1) {
				return Result.fail("修改库存失败");
			}
		}
		// 添加订单
		for (CommodityOrder commodityOrder : commodityOrderList) {
			commodityOrder.setOrderStatus("待付款");
			commodityOrder.setUnpaidTime(NowTime.getNowTime());
			commodityOrderDao.addCommodityOrder(commodityOrder);
		}
		return Result.success();
	}

	public Result findCommodityOrder(CommodityOrder commodityOrder) throws Exception {
		ArrayList<CommodityOrder> commodityOrderList = commodityOrderDao.findCommodityOrder(commodityOrder);
		if (commodityOrderList.size() < 1) {
			return Result.fail();
		}
		return Result.success(commodityOrderList);
	}

	// 交易结束
	public Result tradeFinish(Integer commodityOrderId) throws Exception {
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(commodityOrderId);
		commodityOrder.setOrderStatus("交易结束");
		commodityOrderDao.updateCommodityOrder(commodityOrder);
		return Result.success();
	}

	// 交易支付成功
	public Result tradeSuccess(Integer commodityOrderId) throws Exception {
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(commodityOrderId);
		commodityOrder.setOrderStatus("待发货");
		commodityOrder.setPaymentTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(commodityOrder);
		return Result.success();
	}

	// 取消超时订单
	public Result cancelUnpaidCommodityOrder() throws Exception {
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setOrderStatus("待付款");
		ArrayList<CommodityOrder> commodityOrderList = commodityOrderDao.findCommodityOrder(commodityOrder);
		AlipayClient alipayClient = AlipayConfig.getAlipayClient();
		AlipayTradeCloseRequest alipayTradeCloseRequest = new AlipayTradeCloseRequest();
		for (int i = 0; i < commodityOrderList.size(); i++) {
			long nowTime = NowTime.getTime(NowTime.getNowTime());
			long unpaidTime = NowTime.getTime(commodityOrderList.get(i).getUnpaidTime());
			if (nowTime - unpaidTime > NowTime.TENMINUTES) {
				// 关闭支付宝交易
				String out_trade_no = commodityOrderList.get(i).getOrderNumber();
				String trade_no = "";
				alipayTradeCloseRequest.setBizContent(
						"{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");
				alipayClient.execute(alipayTradeCloseRequest).getBody();
				// 取消订单
				commodityOrder.setId(commodityOrderList.get(i).getId());
				commodityOrder.setOrderStatus("订单取消");
				commodityOrder.setCancelTime(NowTime.getNowTime());
				commodityOrderDao.updateCommodityOrder(commodityOrder);
				// 返还库存
				CommodityDetails commodityDetails = new CommodityDetails();
				commodityDetails.setId(commodityOrderList.get(i).getCommodityDetailsId());
				int stock = commodityDetailsDao.findCommodityDetails(commodityDetails).get(0).getStock();
				stock += commodityOrderList.get(i).getOrderCount();
				commodityDetails.setStock(stock);
				commodityDetailsDao.updateCommodityDetails(commodityDetails);
			}
		}
		return Result.success();
	}

	// 取消订单
	public Result cancelCommodityOrder(CommodityOrder commodityOrder) throws Exception {
		// 判断订单是否存在，判断订单是否属于当前登录的用户
		ArrayList<CommodityOrder> commodityOrderList = commodityOrderDao.findCommodityOrder(commodityOrder);
		if (commodityOrderList.size() < 1) {
			return Result.fail();
		}
		// 判断订单是否是待付款状态
		if (!commodityOrderList.get(0).getOrderStatus().equals("待付款")) {
			return Result.fail();
		}
		// 关闭支付宝交易
		AlipayClient alipayClient = AlipayConfig.getAlipayClient();
		AlipayTradeCloseRequest alipayTradeCloseRequest = new AlipayTradeCloseRequest();
		String out_trade_no = commodityOrderList.get(0).getOrderNumber();
		String trade_no = "";
		alipayTradeCloseRequest
				.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\"}");
		alipayClient.execute(alipayTradeCloseRequest).getBody();
		// 取消订单
		commodityOrder.setOrderStatus("订单取消");
		commodityOrder.setCancelTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(commodityOrder);
		// 返还库存
		CommodityDetails commodityDetails = new CommodityDetails();
		commodityDetails.setId(commodityOrderList.get(0).getCommodityDetailsId());
		int stock = commodityDetailsDao.findCommodityDetails(commodityDetails).get(0).getStock();
		stock += commodityOrderList.get(0).getOrderCount();
		commodityDetails.setStock(stock);
		commodityDetailsDao.updateCommodityDetails(commodityDetails);
		return Result.success();
	}

	// 申请退款
	public Result applyRefund(CommodityOrder commodityOrder) throws Exception {
		// 判断订单是否存在以及订单是否属于这个买家
		commodityOrder = commodityOrderDao.findCommodityOrder(commodityOrder).get(0);
		if (commodityOrder == null) {
			return Result.fail();
		}
		// 判断订单是不是可以退款的状态
		if(commodityOrder.getOrderStatus().equals("待付款") || commodityOrder.getOrderStatus().equals("订单取消")
				|| commodityOrder.getOrderStatus().equals("申请退款") || commodityOrder.getOrderStatus().equals("已退款")
				|| commodityOrder.getOrderStatus().equals("交易结束") || commodityOrder.getOrderStatus().equals("已评价")) {
			return Result.fail();
		}
		// 申请退款
		CommodityOrder tempCommodityOrder = new CommodityOrder();
		tempCommodityOrder.setId(commodityOrder.getId());
		tempCommodityOrder.setOrderStatus("申请退款");
		tempCommodityOrder.setApplyForRefundTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(tempCommodityOrder);
		return Result.success();
	}

	// 卖家处理退款
	public Result refund(CommodityOrder commodityOrder, String message) throws Exception {
		// 判断订单是否存在以及订单是否属于这个卖家
		commodityOrder = commodityOrderDao.findCommodityOrder(commodityOrder).get(0);
		if (commodityOrder == null) {
			return Result.fail();
		}
		// 判断订单是不是申请退款状态
		if (!commodityOrder.getOrderStatus().equals("申请退款")) {
			return Result.fail();
		}
		// 拒绝退款
		if(message.equals("拒绝")) {
			// 修改订单状态为拒绝退款
			CommodityOrder tempCommodityOrder = new CommodityOrder();
			tempCommodityOrder.setId(commodityOrder.getId());
			tempCommodityOrder.setOrderStatus("拒绝退款");
			commodityOrderDao.updateCommodityOrder(tempCommodityOrder);
			return Result.success();
		}
		// 同意退款
		AlipayClient alipayClient = AlipayConfig.getAlipayClient();
		AlipayTradeRefundRequest alipayTradeRefundRequest = new AlipayTradeRefundRequest();
		String out_trade_no = commodityOrder.getOrderNumber();
		String trade_no = "";
		String refund_amount = Double.toString(commodityOrder.getOrderPrice());
		String refund_reason = "";
		String out_request_no = NowTime.getNowTime();
		alipayTradeRefundRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\""
				+ trade_no + "\"," + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\""
				+ refund_reason + "\"," + "\"out_request_no\":\"" + out_request_no + "\"}");
		alipayClient.execute(alipayTradeRefundRequest);
		if (commodityOrder.getTogetherOrderNumber() != null && commodityOrder.getTogetherOrderNumber().equals("")) {
			out_trade_no = commodityOrder.getTogetherOrderNumber();
		}
		alipayTradeRefundRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\""
				+ trade_no + "\"," + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\""
				+ refund_reason + "\"," + "\"out_request_no\":\"" + out_request_no + "\"}");
		alipayClient.execute(alipayTradeRefundRequest);
		// 修改订单状态为已退款
		CommodityOrder tempCommodityOrder = new CommodityOrder();
		tempCommodityOrder.setId(commodityOrder.getId());
		tempCommodityOrder.setOrderStatus("已退款");
		tempCommodityOrder.setRefundedTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(tempCommodityOrder);
		// 返还库存
		CommodityDetails commodityDetails = new CommodityDetails();
		commodityDetails.setId(commodityOrder.getCommodityDetailsId());
		int stock = commodityDetailsDao.findCommodityDetails(commodityDetails).get(0).getStock();
		stock += commodityOrder.getOrderCount();
		commodityDetails.setStock(stock);
		commodityDetailsDao.updateCommodityDetails(commodityDetails);
		return Result.success();
	}

	// 卖家发货
	public Result deliver(CommodityOrder commodityOrder) throws Exception {
		ArrayList<CommodityOrder> commodityOrderList = commodityOrderDao.findCommodityOrder(commodityOrder);
		// 判断这个订单是否存在以及这个订单是否属于这个卖家
		if (commodityOrderList.size() < 1) {
			return Result.fail();
		}
		commodityOrder = commodityOrderList.get(0);
		// 判断这个订单是否是待发货状态
		if (!commodityOrder.getOrderStatus().equals("待发货")) {
			return Result.fail();
		}
		// 修改订单状态为待收货
		CommodityOrder tempCommodityOrder = new CommodityOrder();
		tempCommodityOrder.setId(commodityOrder.getId());
		tempCommodityOrder.setOrderStatus("待收货");
		tempCommodityOrder.setDeliverGoodsTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(tempCommodityOrder);
		return Result.success();
	}

	// 买家确认收货
	public Result receipt(CommodityOrder commodityOrder) throws Exception {
		ArrayList<CommodityOrder> commodityOrderList = commodityOrderDao.findCommodityOrder(commodityOrder);
		// 判断这个订单是否存在以及这个订单是否属于这个买家
		if (commodityOrderList.size() < 1) {
			return Result.fail();
		}
		commodityOrder = commodityOrderList.get(0);
		// 判断这个订单是否是待收货状态
		if (!commodityOrder.getOrderStatus().equals("待收货")) {
			return Result.fail();
		}
		// 修改订单状态为待评价
		CommodityOrder tempCommodityOrder = new CommodityOrder();
		tempCommodityOrder.setId(commodityOrder.getId());
		tempCommodityOrder.setOrderStatus("已收货");
		tempCommodityOrder.setReceivingGoodsTime(NowTime.getNowTime());
		commodityOrderDao.updateCommodityOrder(tempCommodityOrder);
		return Result.success();
	}

	public Result getListOrder(Integer userId) throws Exception {
		if(userId!=null&&userId>0)
		{
			return Result.result(true, "查询成功", commodityOrderDao.getListOrder(userId));
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
	//商家查看自己售出的全部订单
		public Result getListSellOrder(Integer userId) throws Exception {
			if(userId!=null&&userId>0)
			{
				return Result.result(true, "查询成功", commodityOrderDao.getListSellOrder(userId));
			}
			else
			{
				return Result.fail("前台传递参数错误");
			}
			
		}

}
