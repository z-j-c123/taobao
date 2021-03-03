package service.commodityorderservice;

import java.util.ArrayList;
import java.util.List;
import po.CommodityOrder;
import po.Order;
import po.Result;

public interface CommodityOrderService {

	public Result createCommodityOrder(ArrayList<CommodityOrder> commodityOrderList) throws Exception;
	public Result findCommodityOrder(CommodityOrder commodityOrder) throws Exception;
	// 交易结束
	public Result tradeFinish(Integer commodityOrderId) throws Exception;
	// 交易支付成功
	public Result tradeSuccess(Integer commodityOrderId) throws Exception;
	// 取消超时订单
	public Result cancelUnpaidCommodityOrder() throws Exception;
	// 取消订单
	public Result cancelCommodityOrder(CommodityOrder commodityOrder) throws Exception;
	// 申请退款
	public Result applyRefund(CommodityOrder commodityOrder) throws Exception;
	// 卖家处理退款
	public Result refund(CommodityOrder commodityOrder, String message) throws Exception;
	// 卖家发货
	public Result deliver(CommodityOrder commodityOrder) throws Exception;
	// 买家确认收货
	public Result receipt(CommodityOrder commodityOrder) throws Exception;
	//查询用户订单
	public Result getListOrder(Integer userId) throws Exception;
	//商家查看自己售出的全部订单
	public Result getListSellOrder(Integer userId) throws Exception;
}
