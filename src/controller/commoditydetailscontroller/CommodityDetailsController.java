package controller.commoditydetailscontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Result;
import po.ShoppingCartCommodity;
import service.commoditydetailsservice.CommodityDetailsService;

@Controller
public class CommodityDetailsController {

	@Autowired
	private CommodityDetailsService commodityDetailsService;
	
	@RequestMapping("/goumaijsp")
	public String getGouMaiJsp()
	{
		return "goumai";
	}
	
	//该方法传递两个参数 规格id,购买数量count
	@RequestMapping("/getShoppingCartCommodity")
	@ResponseBody
	public Result getShoppingCartCommodity(@RequestBody Map<String,Integer> map,HttpSession session)
	{
		if(session.getAttribute("user")==null)
			return Result.fail("请先登录哦");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		if(map!=null&&map.get("id")!=null&&map.get("count")!=null)
		{
			Integer id = map.get("id");
			Integer count = map.get("count");
			if(id!=null&&id>0&&count!=null&&count>0)
			{
				try
				{
					//去数据库查询
					ShoppingCartCommodity shoppingCartCommodity = 
						commodityDetailsService.getShoppingCartCommodity(id);
					System.out.println(shoppingCartCommodity.toString());
					resultMap.put("shoppingCartCommodity", shoppingCartCommodity);
					resultMap.put("count", count);
					resultMap.put("message","获取成功");
					return Result.result(true, "获取成功", resultMap);
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					System.out.println("CommodityDetailsController类getShoppingCartCommodity方法查询异常");
					return Result.fail("获取失败");
				}
			}
			else
			{
				resultMap.put("message","参数错误");
				return Result.fail("获取失败");
			}
		}
		else
		{
			resultMap.put("message","参数错误");
			return Result.fail("获取失败");
		}
	}
	
}
