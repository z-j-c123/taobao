package controller.commodityordercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.CommodityOrder;
import po.Result;
import po.User;
import service.commodityorderservice.CommodityOrderService;

@Controller
public class ReceiptController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/receipt/{id}")
	@ResponseBody
	public Result receipt(@PathVariable Integer id, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(id);
		commodityOrder.setBuyerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.receipt(commodityOrder);
		} catch (Exception e) {
			System.out.println("异常：ReceiptController类，receipt方法");
			e.printStackTrace();
			return Result.fail("确认收货失败，请稍后再试");
		}
		return result;
	}
	
}
