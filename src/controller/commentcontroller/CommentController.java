package controller.commentcontroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import po.Comment;
import po.Commodity;
import po.CommodityOrder;
import po.Result;
import po.User;
import service.commentservice.CommentService;
import service.commodityorderservice.CommodityOrderService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private CommodityOrderService commodityOrderService;
	
	@RequestMapping("/comment")
	@ResponseBody
	public Result comment(HttpServletRequest request, @RequestParam("commentImage") List<MultipartFile> tempUploadImageList) {
		User user = (User) request.getSession().getAttribute("user");
		String commodityOrderid = request.getParameter("commodityOrderId");
		CommodityOrder commodityOrder = new CommodityOrder();
		commodityOrder.setId(Integer.parseInt(commodityOrderid));
		try {
			Result result = commodityOrderService.findCommodityOrder(commodityOrder);
			ArrayList<CommodityOrder> commodityOrderList = (ArrayList<CommodityOrder>) result.getData();
			if(commodityOrderList.size() < 1) {
				return Result.fail();
			}
			commodityOrder = commodityOrderList.get(0);
			if(!commodityOrder.getOrderStatus().equals("已收货")) {
				return Result.fail();
			}
		} catch (Exception e1) {
			System.out.println("异常：CommentController类，comment方法");
			e1.printStackTrace();
			return Result.fail("评价失败，请稍后再试");
		}
		Comment comment = new Comment();
		comment.setUserId(user.getId());
		comment.setCommodityId(commodityOrder.getCommodityId());
		comment.setMessage(request.getParameter("message"));
		comment.setCommentGrade(request.getParameter("commentGrade"));
		// 排除tempImages里面的空值
		List<MultipartFile> uploadImageList = new ArrayList<MultipartFile>();
		for (MultipartFile image : tempUploadImageList) {
			if (!image.getOriginalFilename().equals("")) {
				uploadImageList.add(image);
			}
		}
		// 判断上传的文件是否全是图片
		for (MultipartFile image : uploadImageList) {
			if (!image.getOriginalFilename().endsWith(".jpg")) {
				return Result.fail("只支持jpg格式的图片");
			}
		}
		Result result = null;
		try {
			result = commentService.comment(comment, uploadImageList, commodityOrder.getId());
		} catch (Exception e) {
			System.out.println("异常：CommentController类，comment方法");
			e.printStackTrace();
			return Result.fail("评价失败，请稍后再试");
		}
		return result;
	}
	
	@RequestMapping("/commentJsp/{id}")
	public String commentJsp(Model model, @PathVariable("id") Integer commodityOrderId) {
		model.addAttribute(commodityOrderId);
		return "comment";
	}
	
}
