package controller.commodityordercontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import po.Address;
import po.Commodity;
import po.CommodityDetails;
import po.CommodityOrder;
import po.Result;
import po.ShoppingCart;
import po.User;
import service.addressserivce.*;
import service.commoditydetailsservice.CommodityDetailsService;
import service.commodityorderservice.CommodityOrderService;
import service.commodityservice.CommodityService;
import service.shoppingcartservice.ShoppingCartService;
import util.AlipayConfig;
import util.NowTime;
import dao.shoppingcartdao.*;

@Controller
public class CreateCommodityOrderController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	@Autowired
	private CommodityDetailsService commodityDetailsService;
	@Autowired
	private CommodityService commodityService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private  ShoppingCartDao  shoppingCartDao;

	@RequestMapping("/createCommodityOrderJsp")
	public String createCommodityOrderJsp() {
		return "createCommodityOrder";
	}
	
	@RequestMapping("/createCommodityOrder")
	@ResponseBody
	// 传commodityDetailsId和orderCount，一一对应，数组，然后还有一个地址id
	public Result createCommodityOrder(HttpServletRequest request, HttpServletResponse response) {
		// 取消超时订单
		try {
			commodityOrderService.cancelUnpaidCommodityOrder();
		} catch (Exception e) {
			System.out.println("异常：createCommodityOrderController类，createCommodityOrder方法");
			e.printStackTrace();
		}
		String[] tempCommodityDetailsIdList = request.getParameterValues("commodityDetailsId");
		String[] tempOrderCountList = request.getParameterValues("orderCount");
		int addressId = Integer.parseInt(request.getParameter("addressId"));
		ArrayList<String> commodityDetailsIdList = new ArrayList<String>();
		ArrayList<String> orderCountList = new ArrayList<String>();
		for (String commodityDetailsId : tempCommodityDetailsIdList) {
			if(commodityDetailsId != null && !commodityDetailsId.equals("")) {
				commodityDetailsIdList.add(commodityDetailsId);
			}
		}
		for (String orderCount : tempOrderCountList) {
			if(orderCount != null && !orderCount.equals("")) {
				orderCountList.add(orderCount);
			}
		}
		// 验证商品规格和商品数量是否一一对应
		if(commodityDetailsIdList.size() != orderCountList.size()) {
			return Result.fail();
		}
		ArrayList<CommodityDetails> commodityDetailsList = new ArrayList<CommodityDetails>();
		ArrayList<CommodityOrder> commodityOrderList = new ArrayList<CommodityOrder>();
		User user = (User) request.getSession().getAttribute("user");
		try {
			for (String id : commodityDetailsIdList) {
				CommodityDetails commodityDetails = new CommodityDetails();
				commodityDetails.setId(Integer.parseInt(id));
				Result result = commodityDetailsService.findCommodityDetails(commodityDetails);
				if(!result.getFlag()) {
					return Result.fail();
				}
				ArrayList<CommodityDetails> tempCommodityDetailsList = (ArrayList<CommodityDetails>) result.getData();
				commodityDetails = tempCommodityDetailsList.get(0);
				commodityDetailsList.add(commodityDetails);
			}
			for (int i = 0; i < commodityDetailsIdList.size(); i++) {
				CommodityDetails commodityDetails = commodityDetailsList.get(i);
				Commodity commodity = new Commodity();
				commodity.setId(commodityDetails.getCommodityId());
				Result result = commodityService.findCommodity(commodity);
				ArrayList<Commodity> tempCommodityList = (ArrayList<Commodity>) result.getData();
				commodity = tempCommodityList.get(0);
				// 获取地址
				Address address = new Address();
				address.setId(addressId);
				result = addressService.findAddress(address);
				ArrayList<Address> addressList = (ArrayList<Address>) result.getData();
				if(addressList.size() < 1) {
					return Result.fail();
				}
				address = addressList.get(0);
				CommodityOrder commodityOrder = new CommodityOrder();
				// 给订单赋值
				commodityOrder.setSellerId(commodity.getSellerId());
				commodityOrder.setBuyerId(user.getId());
				commodityOrder.setCommodityId(commodityDetails.getCommodityId());
				commodityOrder.setCommodityDetailsId(commodityDetails.getId());
				commodityOrder.setOrderNumber(NowTime.getOrderNumber()+Integer.toString(user.getId()));
				commodityOrder.setOrderCount(Integer.parseInt(orderCountList.get(i)));
				commodityOrder.setOrderPrice(commodityDetails.getPrice() * commodityOrder.getOrderCount());
				commodityOrder.setAddress(address.getOrderAddress());
				Thread.sleep(1);
				commodityOrderList.add(commodityOrder);
			}
			// 如果订单数量大于一，添加公共订单号
			String togetherOrderNumber = null;
			if (commodityOrderList.size() > 1) {
				togetherOrderNumber = "t" + NowTime.getOrderNumber() + Integer.toString(user.getId());
				for (CommodityOrder commodityOrder : commodityOrderList) {
					commodityOrder.setTogetherOrderNumber(togetherOrderNumber);
				}				
			}
			// 将订单写入数据库
			Result result = commodityOrderService.createCommodityOrder(commodityOrderList);
			if(!result.getFlag()) {
				return Result.fail(result.getMessage());
			}
			//删除购物车中的商品
			String condition = "";
			ShoppingCart sc = new ShoppingCart();
			for(int i=0;i<commodityOrderList.size();i++)
			{
				sc.setUserId(user.getId());
				sc.setCommoditydetailsId(commodityOrderList.get(i).getCommodityDetailsId());
				sc.setCommodityId(commodityOrderList.get(i).getCommodityId());
				List<ShoppingCart> list = shoppingCartDao.findShoppingCart(sc);
				if(list.size() <= 1) {
					break;
				}
				condition += list.get(0).getId();
			}
			shoppingCartDao.deleteShoppingCart(user.getId(), condition);
			AlipayClient alipayClient = AlipayConfig.getAlipayClient();
			AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
			alipayTradePagePayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
			// 订单号
			String out_trade_no = commodityOrderList.get(0).getOrderNumber();
			// 如果订单数量大于一，使用公共订单号
			if(commodityOrderList.size() > 1) {
				out_trade_no = togetherOrderNumber;
			}
			double orderPrice = 0;
			for (CommodityOrder commodityOrder : commodityOrderList) {
				orderPrice += commodityOrder.getOrderPrice();
			}
			// 付款金额
			String total_amount = Double.toString(orderPrice);
			// 订单名称
			String subject = "三叶草购物商城购物";
			// 商品描述，可空
			String body = "";
			alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			String result1 = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
			return Result.success(result1);
		} catch (Exception e) {
			System.out.println("异常：createCommodityOrderController类，createCommodityOrder方法");
			e.printStackTrace();
			return Result.fail("创建订单失败，请稍后再试");
		}
	}
	
	// 在我的订单中支付待付款的订单
		@RequestMapping("/pay/{id}")
		@ResponseBody
		public Result pay(@PathVariable("id") Integer orderId, HttpSession session) {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setId(orderId);
			try {
				// 验证订单是否存在
				Result result = commodityOrderService.findCommodityOrder(commodityOrder);
				if(!result.getFlag()) {
					return Result.fail();
				}
				ArrayList<CommodityOrder> commodityOrderList = (ArrayList<CommodityOrder>) result.getData();
				commodityOrder = commodityOrderList.get(0);
				// 验证订单是否属于这个买家
				User user = (User) session.getAttribute("user");
				if(user.getId() != commodityOrder.getBuyerId()) {
					return Result.fail();
				}
				// 验证订单是否是待支付
				if(!commodityOrder.getOrderStatus().equals("待付款")) {
					return Result.fail();
				}
				// 订单号
				String out_trade_no = commodityOrder.getOrderNumber();
				// 付款金额
				String total_amount = Double.toString(commodityOrder.getOrderPrice());
				// 订单名称
				String subject = "三叶草购物商城";
				// 订单描述，可空
				String body = "";
				AlipayClient alipayClient = AlipayConfig.getAlipayClient();
				AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
				alipayTradePagePayRequest.setReturnUrl(AlipayConfig.return_url);
				alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
				alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
						+ "\"total_amount\":\""+ total_amount +"\"," 
						+ "\"subject\":\""+ subject +"\"," 
						+ "\"body\":\""+ body +"\"," 
						+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
				String result1 = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
				return result.success(result1);
			} catch (Exception e) {
				System.out.println("异常：createCommodityOrderController类，pay方法");
				e.printStackTrace();
				return Result.fail("支付失败，请稍后再试");
			}
		}
	
}
