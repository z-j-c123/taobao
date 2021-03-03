package controller.commoditycontroller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Commodity;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class FindSellerCommodityController {
	@Autowired
	private CommodityService commodityService;
	@RequestMapping("/findSellerCommodity")
	@ResponseBody
//	查找商家的全部商品
	public Result findSellerCommodity(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");
		Commodity commodity = new Commodity();
		commodity.setSellerId(user.getId());
		Result result = new Result();
		try {
			result = commodityService.findSellerAllCommodity(commodity);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("查询失败，请稍后再试");
		}
		return result;
	}
	
	@RequestMapping("/getSouSouJsp")
	public String getSouSouJsp()
	{
		return "shousuo";
	}
	
	//用户搜索获取商品
	@RequestMapping("/searchCommodity")
	@ResponseBody
	public Result searchCommodity(@RequestBody Map<String,String> map,HttpSession session)
	{
		String searchContent = map.get("searchContent");
		if(searchContent==null||searchContent.equals(""))
			return Result.fail("搜索内容不能为空");
		User user = (User)session.getAttribute("user");
		Integer userId = 0;
		if(user!=null)
			userId = user.getId();
		List<Commodity> listCommodity = null;
		try
		{
			listCommodity = commodityService.searchCommodity(userId,searchContent);
		}
		catch(Exception e)
		{
			
			e.printStackTrace();
			System.out.println("FindSellerCommodityController类searchCommodity方法"
					+ "commodityService.searchCommodity(searchContent,userId)发生异常");
			return Result.result(false, "搜索失败，请稍后再试", null);
		}
		return Result.result(true, "查询成功", listCommodity);
	}
	
	//获取轮播表的商品
	@RequestMapping("/loginGetRotationTableCommodity")
	@ResponseBody
	public Result getRotationTableCommodity(HttpSession session)
	{
		System.out.println(123456);
		User user = (User)session.getAttribute("user");
		Integer userId = 0;
		List<Commodity> listCommodity = null;
		if(user!=null)
			userId = user.getId();
		try
		{
			listCommodity = commodityService.GetRotationTableCommodity(userId);
			System.err.println(listCommodity);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("FindSellerCommodityController类getRotationTableCommodity方法异常");
			return Result.fail("获取失败");
		}
		return Result.result(true, "获取成功", listCommodity);
	}

	
	//获取全部商品
	@RequestMapping("/getAllCommodity")
	@ResponseBody
	public Result getAllCommodity(HttpSession session)
	{
		User user= (User)session.getAttribute("user");
		Integer userId = 0;
		if(user!=null)
			userId = user.getId();
		List<Commodity> listCommodity = null;
		try
		{
			listCommodity = commodityService.getAllCommodity(userId, 20);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("FindSellerCommodityController类getAllCommodity方法异常");
			return Result.fail("获取失败");
		}
		return Result.result(true, "获取成功", listCommodity);
	}
	
	//获取单个商品的全部信息
	@RequestMapping("/singleCommodity/{id}")
	@ResponseBody
	public Result singleCommodity(@PathVariable("id") Integer id,HttpServletRequest request)
	{
		if(id!=null&&!id.equals(""))
		{
			try
			{
				Integer commodityId = new Integer(id);
				//查询商品单个商品的全部信息
				Commodity commodity = new Commodity();
				commodity.setId(commodityId);
				return commodityService.findSellerCommodity(commodity);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return Result.fail("数据错误");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
	
	//获取卖家店铺内销量最好的count件产品
	@RequestMapping("/getListDetailsJspCommodity/{userId}")
	@ResponseBody()
	public Result getListDetailsJspCommodity(@PathVariable("userId") Integer userId)
	{
		System.out.println(userId);
		if(userId!=null&&userId>0)
		{
			try
			{
				return commodityService.getListDetailsJspCommodity(userId, 3);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("FindSellerCommodityController类getListDetailsJspCommodity方法异常");
				return Result.fail("获取失败");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
	}

	
}