package controller.commoditycontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Commodity;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class DeleteCommodityController {
	@Autowired
	private CommodityService commodityService;
	@RequestMapping("/deleteCommodity/{id}")
	@ResponseBody
	public Result deleteCommodity(@PathVariable("id") Integer id, HttpServletRequest request)
	{
//		根据商品id和卖家id删除商品
		User user = (User) request.getSession().getAttribute("user");
		Commodity commodity = new Commodity();
		commodity.setId(id);
		commodity.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityService.deleteCommodity(commodity);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("删除失败");
		}
		return result;
	}
}
