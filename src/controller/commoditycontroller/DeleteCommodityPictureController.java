package controller.commoditycontroller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Commodity;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class DeleteCommodityPictureController {

	@Autowired
	private CommodityService commodityService;
	
	// 删除商品图片
	@RequestMapping("/deleteCommodityPicture")
	@ResponseBody
	public Result deleteCommodityPicture(@RequestBody HashMap<String, Object> hashMap, HttpSession httpSession) {
		
		Integer id =  (Integer) hashMap.get("id");
		String pictureId = (String) hashMap.get("pictureId");
		User user = (User) httpSession.getAttribute("user");
		Commodity commodity = new Commodity();
		commodity.setId(id);
		commodity.setSellerId(user.getId());
		Result result = null;
		try {
			commodity.commodityPictureAssignment(commodity, pictureId, "delete");
			result = commodityService.deleteCommodityPicture(commodity);
		} catch (Exception e) {
			System.out.println("异常：DeleteCommodityPictureController类，deleteCommodityPicture方法");
			e.printStackTrace();
			return Result.fail("删除失败，请稍后再试");
		}
		return result;
	}
	
}
