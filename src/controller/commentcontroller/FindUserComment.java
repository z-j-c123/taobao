package controller.commentcontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Comment;
import po.Result;
import po.User;
import service.commentservice.CommentService;

@Controller
public class FindUserComment {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/findUserAllComment")
	@ResponseBody
	public Result findUserAllComment(HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");
		Comment comment = new Comment();
		comment.setUserId(user.getId());
		Result result = null;
		try {
			result = commentService.findComment(comment);
		} catch (Exception e) {
			System.out.println("异常：FindUserComment类，findUserAllComment方法");
			e.printStackTrace();
			return Result.fail("查看失败，请稍后再试");
		}
		return result;
	}
	
	// 找到商品的全部评价
	@RequestMapping("/findCommodityAllComment/{id}")
	@ResponseBody
	public Result findCommodityAllComment(@PathVariable("id") Integer commodityId) {
		Comment comment = new Comment();
		comment.setCommodityId(commodityId);
		Result result = null;
		try {
			result = commentService.findComment(comment);
		} catch (Exception e) {
			System.out.println("异常：FindUserComment类，findCommodityAllComment方法");
			e.printStackTrace();
			return Result.fail("查看失败，请稍后再试");
		}
		return result;
	}

}
