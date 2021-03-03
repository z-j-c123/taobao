package service.commodityservice;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import po.Commodity;
import po.CommodityDetails;
import po.Result;

public interface CommodityService {
	public Result uploadCommodity(Commodity commodity, List<MultipartFile> images, List<CommodityDetails> commodityDetailsList) throws Exception;
//	查找卖家全部商品
	public Result findSellerAllCommodity(Commodity commodity) throws Exception;
//	查找卖家单个商品
	public Result findSellerCommodity(Commodity commodity) throws Exception;
	public Result updateCommodity(Commodity commodity) throws Exception;
	public Result deleteCommodity(Commodity commodity) throws Exception;
	public Result findCommodity(Commodity commodity) throws Exception;
	// 删除商品图片
	public Result deleteCommodityPicture(Commodity commodity) throws Exception;
	// 上传商品图片啊
	public Result uploadCommodityPicture(Commodity commodity, MultipartFile image) throws Exception;
	//根据用户搜索内容通过匹配商品名和商品类别获取商品
	public List<Commodity> searchCommodity(Integer userId,String searchContent) throws Exception;
	//获取轮播表的商品
	public List<Commodity> GetRotationTableCommodity(Integer userId) throws Exception;
	//查询近一段时间内销量最多的count件商品
	public List<Commodity> getAllCommodity(Integer userId,Integer count) throws Exception;
	// 增加商品销量
	public Result addCommodityCount(Commodity commodity) throws Exception;
	//获取卖家店铺内销量最好的count件产品
	public Result getListDetailsJspCommodity(Integer userId,Integer count) throws Exception;
}
