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
public class DeliverController {

	@Autowired
	private CommodityOrderService commodityOrderService;
	//发货
	@RequestMapping("/deliver/{id}")
	@ResponseBody
	public Result deliver(@PathVariable("id") Integer id, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(id);
		commodityOrder.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityOrderService.deliver(commodityOrder);
		} catch (Exception e) {
			System.out.println("异常：DeliverController类，deliver方法");
			e.printStackTrace();
			return Result.fail("发货失败，请稍后重试");
		}
		return result;
	}
	
}
