package dao.commoditydetailsdao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import po.CommodityDetails;
import po.ShoppingCartCommodity;

public interface CommodityDetailsDao {
	public Integer addCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	public Integer updateCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	public Integer deleteCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	public ArrayList<CommodityDetails> findCommodityDetails(CommodityDetails commodityDetails) throws Exception;
	//根据id查询出ShoppingCartCommodity实体类
	public ShoppingCartCommodity getShoppingCartCommodity(@Param("id")Integer id) throws Exception;
}
