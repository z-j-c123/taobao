package controller.commodityordercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.CommodityOrder;
import po.Result;
import po.User;
import service.commodityorderservice.CommodityOrderService;

@Controller
public class FindSellerAllCommodityOrderController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/findSellerAllCommodityOrder")
	@ResponseBody
	// 查找卖家的所有订单
	public Result findSellerAllCommodityOrder(HttpSession session) {
		User user = (User) session.getAttribute("user");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.findCommodityOrder(commodityOrder);
		} catch (Exception e) {
			System.out.println("异常：FindBuyerAllCommodityOrderController类，findBuyerAllCommodityOrder方法");
			e.printStackTrace();
			return Result.fail("查询失败，请稍后再试");
		} 
		return result;
	}
	//商家查看自己售出的全部订单
		@RequestMapping("/getListSellOrder")
		@ResponseBody
		public Result getListSellOrder(HttpSession session)
		{
			User user = (User)session.getAttribute("user");
			if(user==null||user.getId()==null||user.getId()<0)
				return Result.fail("请先登录");
			try
			{
				//查询
				return Result.result(true, "查询成功", commodityOrderService.getListSellOrder(user.getId()));
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println();
				return Result.fail("查询异常，请稍后再试");
			}
		}
	
}
