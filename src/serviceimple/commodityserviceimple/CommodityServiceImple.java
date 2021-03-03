package serviceimple.commodityserviceimple;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import dao.commoditydao.CommodityDao;
import dao.commoditydetailsdao.CommodityDetailsDao;
import dao.journaldao.JournalDao;
import po.Commodity;
import po.CommodityDetails;
import po.Journal;
import po.Result;
import service.commodityservice.CommodityService;
import util.NowTime;
@Service
@Transactional
public class CommodityServiceImple implements CommodityService {
	@Autowired
	private CommodityDao commodityDao;
	@Autowired
	private CommodityDetailsDao commodityDetailsDao;
	@Autowired
	private JournalDao journalDao;
	
//	删除商品
	public Result deleteCommodity(Commodity commodity) throws Exception
	{
//		先判断有没有这个商品，以及这个商品的卖家是不是现在登录的这个卖家
		List<Commodity> commoditys = commodityDao.findCommodity(commodity);
		if(commoditys.size() < 1)
		{
			return Result.fail();
		}
//		先删除这个商品的所有商品属性
		CommodityDetails commodityDetails = new CommodityDetails();
		commodityDetails.setCommodityId(commodity.getId());
		commodityDetailsDao.deleteCommodityDetails(commodityDetails);
//		删除这个商品
		commodityDao.deleteCommodity(commodity);
		return Result.success();
	}
	
//	上传商品
	public Result uploadCommodity(Commodity commodity, List<MultipartFile> images, List<CommodityDetails> commodityDetailsList) throws Exception {
//		将图片保存至磁盘
		List<String> commodityPictures = new ArrayList<String>();
		for(MultipartFile image : images)
		{
			String fileName=UUID.randomUUID()+".jpg";
			String filePath="D:/Picture/Taobao/"+fileName;
			image.transferTo(new File(filePath));
			commodityPictures.add(fileName);
		}
//		将图片集合的长度扩展至15
		for(int i = images.size(); i < 15; i ++)
		{
			commodityPictures.add(null);
		}
		commodity.setCommodityPictures(commodityPictures);
//		将商品添加到商品表
		commodityDao.addCommodity(commodity);
//		通过商品图片名UUID查找到刚才添加的商品，获取商品id
		Commodity tempCommodity = new Commodity();
		tempCommodity.setCommodityPicture1(commodityPictures.get(0));
		int commodityId = commodityDao.findCommodity(tempCommodity).get(0).getId();
//		将获取到的商品id写入商品规格集合的每个商品规格对象，然后将商品规格记录写入商品规格表
		for(CommodityDetails commodityDetails : commodityDetailsList)
		{
			commodityDetails.setCommodityId(commodityId);
			commodityDetailsDao.addCommodityDetails(commodityDetails);
		}
//		记录日志
		try {
			Journal journal = new Journal();
			journal.setUserId(commodity.getSellerId());
			journal.setJournalTime(NowTime.getNowTime());
			journal.setOperation("添加商品");
			journalDao.addJournal(journal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success();
	}
	
//	查找卖家的单个商品，根据商品id和卖家id
	public Result findSellerCommodity(Commodity commodity) throws Exception
	{
//		先判断有没有这个商品，以及这个商品的卖家是不是现在登录的这个卖家
		List<Commodity> commoditys = commodityDao.findCommodityWithCommodityDetails(commodity);
		if(commoditys.size() < 1)
		{
			return Result.fail();
		}
		return Result.success(commoditys.get(0));
	}
	
//	查找卖家的商品，根据卖家id
	public Result findSellerAllCommodity(Commodity commodity) throws Exception
	{
		List<Commodity> commoditys = new ArrayList<Commodity>();
		commoditys = commodityDao.findCommodityWithCommodityDetails(commodity);
//		将查到的商品的图片放入集合
		for(Commodity tempCommodity : commoditys)
		{
			tempCommodity.initCommodityPictures();
		}
		return Result.success(commoditys);
	}
//	修改商品
	public Result updateCommodity(Commodity commodity) throws Exception {
//		先判断有没有这个商品，以及这个商品的卖家是不是现在登录的这个卖家
		Commodity tempCommodity = new Commodity();
		tempCommodity.setId(commodity.getId());;
		tempCommodity.setSellerId(commodity.getSellerId());
		List<Commodity> CommodityList = commodityDao.findCommodity(tempCommodity);
		if(CommodityList.size() < 1)
		{
			return Result.fail();
		}
		// 判断商品内容是否为空
		if(commodity.getCommodityType() == null || commodity.getCommodityType().equals("") || commodity.getCommodityName() == null
				|| commodity.getCommodityName().equals("")) {
			return Result.fail();
		}
		// 修改商品
		commodityDao.updateCommodity(commodity);
		return Result.success();
	}

	// 查找商品
	public Result findCommodity(Commodity commodity) throws Exception {
		ArrayList<Commodity> commodityList = (ArrayList<Commodity>) commodityDao.findCommodity(commodity);
		return Result.success(commodityList);
	}

	// 删除商品图片
	public Result deleteCommodityPicture(Commodity commodity) throws Exception {
//		先判断有没有这个商品，以及这个商品的卖家是不是现在登录的这个卖家
		Commodity tempCommodity = new Commodity();
		tempCommodity.setId(commodity.getId());
		tempCommodity.setSellerId(commodity.getSellerId());
		List<Commodity> CommodityList = commodityDao.findCommodity(tempCommodity);
		if(CommodityList.size() < 1)
		{
			return Result.fail();
		}
		// 删除图片
		commodityDao.deleteCommodityPicture(commodity);
		return Result.success();
	}

	// 上传商品图片
	public Result uploadCommodityPicture(Commodity commodity, MultipartFile image) throws Exception {
		// 先判断有没有这个商品，以及这个商品的卖家是不是现在登录的这个卖家
		Commodity tempCommodity = new Commodity();
		tempCommodity.setId(commodity.getId());
		tempCommodity.setSellerId(commodity.getSellerId());
		List<Commodity> commodityList = commodityDao.findCommodity(tempCommodity);
		if(commodityList.size() < 1)
		{
			return Result.fail();
		}
		// 将图片保存至本地磁盘
		String fileName = UUID.randomUUID() + ".jpg";
		String filePath="D:/Picture/Taobao/"+fileName;
		image.transferTo(new File(filePath));
		// 将图片名保存至数据库
		tempCommodity = commodityList.get(0);
		String pictureId = tempCommodity.getNullPictureId();
		commodity.commodityPictureAssignment(commodity, pictureId, fileName);
		commodityDao.updateCommodityPicture(commodity);
		return Result.success();
	}

	//获取用户搜索的商品
		public List<Commodity> searchCommodity(Integer userId,String searchContent) throws Exception {
			List<Commodity> listCommodity = new ArrayList<Commodity>();
			if(searchContent!=null&&!searchContent.equals("")&&userId!=null)
			{
				listCommodity = commodityDao.searchCommodity(userId,searchContent);	
				if(userId>0)
				{
					//添加日志
					try
					{
						Journal journal = new Journal();
						journal.setUserId(userId);
						journal.setJournalTime(NowTime.getNowTime());
						if(listCommodity.size()>=3)
							journal.setOperation("搜索商品_"+searchContent);
						else
							journal.setOperation("_搜索商品_"+searchContent);
						journalDao.addJournal(journal);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						System.out.println("CommodityServiceImple类"
						+"searchCommodity方法添加日志异常");
					}
				}
			}
			return listCommodity;
		}
		//获取轮播表的商品
		public List<Commodity> GetRotationTableCommodity(Integer userId) throws Exception
		{
			List<Commodity> listCommodity = new ArrayList<Commodity>();
			if(userId!=null&&userId>0)
			{
				List<String> listSearchContent = journalDao.collectionRecord(userId);
				Commodity c = null;
				String condition = "";
				for(int i=0;i<listSearchContent.size();i++)
				{
					c = commodityDao.getRotationTableCommodity(userId,listSearchContent.get(i),condition);
					if(c!=null)
					{
						listCommodity.add(c);
						condition += c.getId()+",";
					}
				}
				//如果不满十条商品记录，则直接去商品表中找到10-listSearchContent.size()销量最多商品
				if(listCommodity.size()<10)
				{
					listCommodity.addAll(commodityDao.mostSales(userId, condition,10-listCommodity.size()));
				}
			}
			//未登录的状态
			else
			{
				//直接去商品表查询销量最好的商品
				listCommodity = commodityDao.mostSales(0, "", 10);
			}
			return listCommodity;
		}

		//用户进入主页后展示的商品
		//先查询近段时间内销售的最好的商品
		//再查询所有商品（不包含销售的好的）
		public List<Commodity> getAllCommodity(Integer userId, Integer count) throws Exception
		{
			List<Commodity> listCommodity = null;
			listCommodity = commodityDao.recentlyMostSalesCommodity(userId, count);
			String condition = "";
			for(int i=0;i<listCommodity.size();i++)
			{
				condition += listCommodity.get(i).getId()+",";
			}
			listCommodity.addAll(commodityDao.doesNotContainIdCommodity(userId,condition));
			return listCommodity;
		}
		
		// 增加商品销量
		public Result addCommodityCount(Commodity commodity) throws Exception {
			commodity = commodityDao.findCommodity(commodity).get(0);
			Commodity updateCommodity = new Commodity();
			updateCommodity.setId(commodity.getId());
			updateCommodity.setCommodityCount(commodity.getCommodityCount() + 1);
			commodityDao.updateCommodity(updateCommodity);
			return Result.success();
		}
		
		//获取卖家店铺内销量最好的count件产品
	public Result getListDetailsJspCommodity(Integer userId, Integer count) throws Exception {
		if(userId!=null&&userId>0&&count!=null&&count>0)
		{
			return Result.result(true, "获取成功", commodityDao.getListDetailsJspCommodity(userId, count));
		}
		else
		{
			return Result.fail("获取失败");
		}
	}
}
