package controller.commoditycontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Commodity;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class UpdateCommodityController {
	@Autowired
	private CommodityService commodityService;
	@RequestMapping("/updateCommodityJsp/{id}")
	public String updateCommodityJsp(@PathVariable("id") Integer id,HttpServletRequest request, Model model)
	{
		User user = (User) request.getSession().getAttribute("user");
		Commodity commodity = new Commodity();
		commodity.setId(id);
		commodity.setSellerId(user.getId());
		try {
			commodity = (Commodity) commodityService.findSellerCommodity(commodity).getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(commodity);
		return "updateCommodity";
	}
	
	// 修改商品信息，commodityType和commodityName
	@RequestMapping("/updateCommodity")
	@ResponseBody
	public Result updateCommodity(@RequestBody Commodity commodity, HttpSession httpSession)
	{
		User user = (User) httpSession.getAttribute("user");
		commodity.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityService.updateCommodity(commodity);
		} catch (Exception e) {
			System.out.println("异常：UpdateCommodityController类，updateCommodity方法");
			e.printStackTrace();
			return Result.fail("修改失败，请稍后再试");
		}
		return result;
	}
}