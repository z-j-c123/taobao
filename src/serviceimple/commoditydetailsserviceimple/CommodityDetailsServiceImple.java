package serviceimple.commoditydetailsserviceimple;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.commoditydetailsdao.CommodityDetailsDao;
import po.CommodityDetails;
import po.Result;
import po.ShoppingCartCommodity;
import service.commoditydetailsservice.CommodityDetailsService;

@Service
@Transactional
public class CommodityDetailsServiceImple implements CommodityDetailsService {

	@Autowired
	private CommodityDetailsDao commodityDetailsDao;
	
	public Result findCommodityDetails(CommodityDetails commodityDetails) throws Exception {
		ArrayList<CommodityDetails> commodityDetailsList = commodityDetailsDao.findCommodityDetails(commodityDetails);
		if(commodityDetailsList.size() < 1) {
			return Result.fail();
		}
		return Result.success(commodityDetailsList);
	}

	// 修改商品规格
	public Result updateCommodityDetails(CommodityDetails commodityDetails) throws Exception {
		commodityDetailsDao.updateCommodityDetails(commodityDetails);
		return Result.success();
	}
	public ShoppingCartCommodity getShoppingCartCommodity(Integer id) throws Exception {
		if(id!=null&&id>0)
		{
			return commodityDetailsDao.getShoppingCartCommodity(id);
		}
		else
		{
			return null;
		}
	}
}
