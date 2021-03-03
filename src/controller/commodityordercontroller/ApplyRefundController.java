package controller.commodityordercontroller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
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
public class ApplyRefundController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	
	// 申请退款
	@RequestMapping("/applyRefund/{id}")
	@ResponseBody
	public Result applyRefund(@PathVariable("id") Integer id, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(id);
		commodityOrder.setBuyerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.applyRefund(commodityOrder);
		} catch (Exception e) {
			System.out.println("异常：ApplyRefundController类，applyRefund方法");
			e.printStackTrace();
			return Result.fail("申请失败，请稍后重试");
		}
		return result;
	}
	
	// 卖家同意退款，id:订单id，message:同意或者拒绝
	@RequestMapping("/refund")
	@ResponseBody
	public Result refund(HttpSession httpSession, @RequestBody HashMap<String, Object> hashMap) {
		User user = (User) httpSession.getAttribute("user");
		int id = Integer.parseInt((String) hashMap.get("id"));
		String message = (String) hashMap.get("message");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(id);
		commodityOrder.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.refund(commodityOrder, message);
		} catch (Exception e) {
			System.out.println("异常：ApplyRefundController类，refund方法");
			e.printStackTrace();
			return Result.fail(message + "失败，请稍再试");
		}
		return result;
	}
	
}
