package service.commoditydetailsservice;

import po.CommodityDetails;
import po.Result;
import po.ShoppingCartCommodity;

public interface CommodityDetailsService {
	public Result findCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	// 修改商品规格
	public Result updateCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	//根据id查询出ShoppingCartCommodity实体类
	public ShoppingCartCommodity getShoppingCartCommodity(Integer id) throws Exception;
}
