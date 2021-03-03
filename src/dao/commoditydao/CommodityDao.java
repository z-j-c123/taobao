package dao.commoditydao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Commodity;
import po.Result;

public interface CommodityDao {
	public Integer addCommodity(Commodity commodity) throws Exception;
	//查找商品，含有商品属性
	public List<Commodity> findCommodityWithCommodityDetails(Commodity commodity) throws Exception;
	//查找商品，不含商品属性
	public List<Commodity> findCommodity(Commodity commodity) throws Exception;
	public Integer updateCommodity(Commodity commodity) throws Exception;
	public Integer deleteCommodity(Commodity commodity) throws Exception;
	public Integer deleteCommodityPicture(Commodity commodity) throws Exception;
	// 修改商品图片
	public Integer updateCommodityPicture(Commodity commodity) throws Exception;
	//根据用户搜索内容通过匹配商品名和商品类别获取商品
	public List<Commodity> searchCommodity(@Param("userId") Integer userId,@Param("searchContent") String searchContent) throws Exception;
	//轮播表
	//根据用户曾经收索过的内容，获取一件销量最高商品，发布到轮播表上面去
	public Commodity getRotationTableCommodity(@Param("userId") Integer uesrId,@Param("searchContent") String searchContent,@Param("condition") String condition) throws Exception;
	//查询count件销量最多的商品
	public List<Commodity> mostSales(@Param("userId") Integer userId,@Param("condition") String condition,@Param("count") Integer count) throws Exception;
	//查询近一段时间内销量最多的count件商品
	public List<Commodity> recentlyMostSalesCommodity
	(@Param("userId") Integer userId,@Param("count") Integer count) throws Exception;
	//查询不包含某些id的商品
	public List<Commodity> doesNotContainIdCommodity(@Param("userId") Integer userId,@Param("condition") String condition) throws Exception;
	//获取卖家店铺内销量最好的count件产品
	public List<Commodity> getListDetailsJspCommodity(@Param("userId") Integer userId,@Param("count") Integer count) throws Exception;
}
