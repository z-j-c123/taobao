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
public class FindBuyerAllCommodityOrderController {
	
	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/myOrderJsp")
	public String myOrderJsp() {
		return "myOrder";
	}
	
	@RequestMapping("/findBuyerAllCommodityOrder")
	@ResponseBody
	// 查看买家的所有订单
	public Result findBuyerAllCommodityOrder(HttpSession session) {
		User user = (User) session.getAttribute("user");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setBuyerId(user.getId());
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
	
}
