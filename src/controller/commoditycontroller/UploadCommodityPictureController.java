package controller.commoditycontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import po.Commodity;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class UploadCommodityPictureController {

	@Autowired
	private CommodityService commodityService;
	
	@RequestMapping("/uploadCommodityPicture")
	@ResponseBody
	public Result uploadCommodityPicture(HttpServletRequest request, @RequestParam("commodityImage") MultipartFile image) {
		// 判断上传的文件是否是图片
		if(!image.getOriginalFilename().endsWith(".jpg")) {
			return Result.fail("只支持jpg格式的图片");
		}
		User user = (User) request.getSession().getAttribute("user");
		String id = request.getParameter("id");
		Commodity commodity = new Commodity();
		commodity.setId(Integer.parseInt(id));
		commodity.setSellerId(user.getId());
		Result result = null;
		try {
			result = commodityService.uploadCommodityPicture(commodity, image);
		} catch (Exception e) {
			System.out.println("异常：uploadCommodityPictureController类，uploadCommodityPicture方法");
			e.printStackTrace();
			return Result.fail("上传失败，请稍后重试");
		}
		return result;
	}
	
}
