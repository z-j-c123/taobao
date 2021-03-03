package controller.commoditydetailscontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.CommodityDetails;
import po.Result;
import service.commoditydetailsservice.CommodityDetailsService;

@Controller
public class UpdateCommodityDetailsController {

	@Autowired
	private CommodityDetailsService commodityDetailsService;
	
	@RequestMapping("/updateCommodityDetails")
	@ResponseBody
	public Result updateCommodityDetails(@RequestBody CommodityDetails commodityDetails) {
		Result result = null;
		try {
			result = commodityDetailsService.updateCommodityDetails(commodityDetails);
		} catch (Exception e) {
			System.out.println("异常：updateCommodityDetailsController类，updateCommodityDetails方法");
			e.printStackTrace();
			return Result.fail("修改失败，请稍后再试");
		}
		return result;
	}
	
}
