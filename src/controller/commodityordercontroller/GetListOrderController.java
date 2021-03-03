package controller.commodityordercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Result;
import po.User;
import service.commodityorderservice.CommodityOrderService;

@Controller
public class GetListOrderController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/getListOrderController")
	@ResponseBody
	public Result getListOrderController(HttpSession session)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return Result.fail("请先登录");
		else
		{
			//去数据库查询
			try
			{
				return commodityOrderService.getListOrder(user.getId());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("GetListOrderController类getListOrderController方法异常");
				return Result.fail("查询失败");
			}
		}
	}
	
	
}
