package controller.commoditycontroller;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import po.Commodity;
import po.CommodityDetails;
import po.Result;
import po.User;
import service.commodityservice.CommodityService;

@Controller
public class UploadCommodityController {
	@Autowired
	private CommodityService CommodityService;

	@RequestMapping("/uploadCommodityJsp")
	public String uploadCommodityJsp() {
		return "uploadCommodity";
	}

	@RequestMapping("/uploadCommodity")
	@ResponseBody
	public Result uploadCommodity(HttpServletRequest request, @RequestParam("commodityImage") List<MultipartFile> tempImages) {
		User user = (User) request.getSession().getAttribute("user");
//		获取商品信息
		int sellerId = user.getId();
		String commodityType = request.getParameter("commodityType");
		String commodityName = request.getParameter("commodityName");
		String[] specifications = request.getParameterValues("specifications");
		String[] price = request.getParameterValues("price");
		String[] stock = request.getParameterValues("stock");
//		排除tempImages里面的空值
		List<MultipartFile> images = new ArrayList<MultipartFile>();
		for (MultipartFile image : tempImages) {
			if (!image.getOriginalFilename().equals("")) {
				images.add(image);
			}
		}
//		判断商品信息是否有误
		if (commodityType == null || commodityType.equals("") || commodityName == null || commodityName.equals("") || images.isEmpty()
				|| images.size() < 1 || images.size() > 15 || specifications == null || price == null || stock == null
				|| !(specifications.length == price.length) || !(specifications.length == stock.length))
		{
			return Result.fail("上传的信息有误");
		}
//		判断上传的文件是否全是图片
		for (MultipartFile image : images) {
			if (!image.getOriginalFilename().endsWith(".jpg")) {
				return Result.fail("只支持jpg格式的图片");
			}
		}
		Commodity commodity = new Commodity();
		commodity.setSellerId(sellerId);
		commodity.setCommodityType(commodityType);
		commodity.setCommodityName(commodityName);
//		将商品的所有规格存入集合
		List<CommodityDetails> commodityDetailsList = new ArrayList<CommodityDetails>();
		for (int i = 0; i < specifications.length; i++) {
//			如果信息不合法就跳过
			if (specifications[i] == null || specifications[i].equals("") || price[i] == null || price[i].equals("") || stock == null 
					|| stock[i].equals("")) {
				continue;
			}
//			价格和库存不能小于0
			else if (Double.parseDouble(price[i]) < 0 || Integer.parseInt(stock[i]) < 0) {
				continue;
			}
			CommodityDetails commodityDetails = new CommodityDetails();
			commodityDetails.setSpecifications(specifications[i]);
			commodityDetails.setPrice(Double.parseDouble(price[i]));
			commodityDetails.setStock(Integer.parseInt(stock[i]));
			commodityDetailsList.add(commodityDetails);
		}
//		如果合法的商品属性的记录小于一条，返回失败信息
		if(commodityDetailsList.size() < 1)
		{
			return Result.fail("上传的信息有误");
		}
		Result result = null;
		try {
			result = CommodityService.uploadCommodity(commodity, images, commodityDetailsList);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.fail("上传失败");
		}
		return result;
	}
}