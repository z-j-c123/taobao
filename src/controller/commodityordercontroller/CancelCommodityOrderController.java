package controller.commodityordercontroller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.CommodityOrder;
import po.Result;
import po.User;
import service.commodityorderservice.CommodityOrderService;

@Controller
public class CancelCommodityOrderController {
	
	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/cancelCommodityOrder/{id}")
	@ResponseBody
	//取消订单
	public Result cancelCommodityOrder(@PathVariable("id") Integer id, HttpSession httpSession) {
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(id);
		User user = (User) httpSession.getAttribute("user");
		commodityOrder.setBuyerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.cancelCommodityOrder(commodityOrder);
		} catch (Exception e) {
			System.out.println("异常：CancelCommodityOrderController类，cancelCommodityOrder方法");
			e.printStackTrace();
			return Result.fail("取消订单失败，请稍后再试");
		}
		return result;
	}
	
}
