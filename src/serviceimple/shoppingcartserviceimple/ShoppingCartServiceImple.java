package serviceimple.shoppingcartserviceimple;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.commoditydao.CommodityDao;
import dao.journaldao.JournalDao;
import dao.shoppingcartdao.ShoppingCartDao;
import po.Commodity;
import po.Journal;
import po.Result;
import po.ShoppingCart;
import po.ShoppingCartCommodity;
import service.shoppingcartservice.ShoppingCartService;
import util.NowTime;

@Service
@Transactional
public class ShoppingCartServiceImple implements ShoppingCartService{

	@Autowired
	private ShoppingCartDao shoppingCartDao;
	@Autowired
	private CommodityDao commodityDao;
	@Autowired
	private JournalDao journalDao;
	
	//将商品加入购物车
	public Result addShoppingCart(ShoppingCart shoppingCart) throws Exception {
		//效验数据
		if(shoppingCart!=null&&
				shoppingCart.getUserId()!=null&&
				shoppingCart.getCommodityId()!=null&&
				shoppingCart.getCommoditydetailsId()!=null&&
				shoppingCart.getCommodityCount()!=null&&
				shoppingCart.getCommodityCount()>0)
		{
			//用户不能将自己售卖的商品加入自己的购物车
			//查询是否是自己的商品
			Commodity commodity = new Commodity();
			commodity.setId(shoppingCart.getCommodityId());
			commodity.setSellerId(shoppingCart.getUserId());
			if(commodityDao.findCommodity(commodity).size()>0)
				return Result.fail("不能将自己的商品加入购物车哦");
			else
			{
				//购物车中不能存在同一个商品规格的商品
				//即(userId,commodityId,commoditydetailsId) unique
				//查询购物车是否存在同一规格的商品
				ShoppingCart sc = new ShoppingCart();
				sc.setUserId(shoppingCart.getUserId());
				sc.setCommodityId(shoppingCart.getCommodityId());
				sc.setCommoditydetailsId(shoppingCart.getCommoditydetailsId());
				if(shoppingCartDao.findShoppingCart(sc).size()>0)
					return Result.fail("您的购物车中已经存在该商品了哦");
				else
				{
					shoppingCart.setJoiningTime(new Date());
					//插入记录
					if(shoppingCartDao.addShoppingCart(shoppingCart)>0)
					{
						//添加日志
						Journal journal = new Journal();
						journal.setUserId(shoppingCart.getUserId());
						journal.setOperation("将商品加入购物车"+shoppingCart.toString());
						journal.setJournalTime(NowTime.getNowTime());
						try
						{
							journalDao.addJournal(journal);
						}
						catch(Exception e)
						{
							System.out.println(e.getMessage());
						}
						return Result.result(true, "商品已加入购物车", null);
					}
					else
						return Result.fail("加入失败，请稍后再试");
				}
			}
		}
		else
		{
			return Result.fail("数据错误，添加至购物车失败");
		}
		//查询是是否是自己出售的商品
		
	}

	//删除购物车中的商品
	public Result deleteShoppingCart(Integer userId,String condition) throws Exception {
		//效验数据
		if(condition==null&&condition.equals(""))
			return Result.fail("数据错误，删除失败");
		else
		{
			Integer result = shoppingCartDao.deleteShoppingCart(userId,condition);
			if(result>0)
			{
				return Result.result(true, "删除成功", result);
			}
			else
				return Result.fail("删除失败");
		}
	}

	//获取用户购物车的所有商品
	public Result findShoppingCart(Integer userId) throws Exception {
		if(userId!=null&&userId>0)
		{
			return Result.result(true, "获取购物车商品成功", shoppingCartDao.findAllShoppingCart(userId));
		}
		else
		{
			return Result.fail("数据错误，获取购物车商品失败");
		}
	}
	
	public Result updateShoppingCart(ShoppingCart shoppingCart) throws Exception {
		if(shoppingCart==null||
				shoppingCart.getUserId()==null||shoppingCart.getUserId()<=0||
				shoppingCart.getId()==null||shoppingCart.getId()<=0||
				shoppingCart.getCommodityCount()==null||shoppingCart.getCommodityCount()<=0)
			return Result.fail("数据错误，修改失败");
		else
		{
			Integer result = shoppingCartDao.updateShoppingCart(shoppingCart);
			if(result>0)
				return Result.result(true, "修改成功", result);
			else
				return Result.fail("修改失败");
		}
	}
	//根据多个id查询记录
	public Result findListShoppingCartCommodity(Integer userId, String condition)
			throws Exception {
		if(userId!=null&&userId>0&&condition!=null&&!condition.equals(""))
		{
			//查询
			List<ShoppingCartCommodity> listSC = shoppingCartDao.findListShoppingCartCommodity(userId, condition);
			return Result.result(true, "获取成功", listSC);
		}
		else
			return Result.fail("数据错误，请稍后再试");
	}


}
