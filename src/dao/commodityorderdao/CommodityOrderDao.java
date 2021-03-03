package dao.commodityorderdao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.CommodityOrder;
import po.Order;
import po.SellOrder;

public interface CommodityOrderDao {

	public Integer addCommodityOrder(CommodityOrder commodityOrder) throws Exception;
	public ArrayList<CommodityOrder> findCommodityOrder(CommodityOrder commodityOrder) throws Exception;
	public Integer updateCommodityOrder(CommodityOrder commodityOrder) throws Exception;
	//查询用户订单
	public List<Order> getListOrder(@Param("userId") Integer userId) throws Exception;
	//商家查看自己售出的全部订单
	public List<SellOrder> getListSellOrder(@Param("userId") Integer userId) throws Exception;
}
