package controller.usercontroller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Result;

@Controller
public class SignOutController {

	@RequestMapping("/signout")
	@ResponseBody
	public Result signOut(HttpSession session)
	{
		session.removeAttribute("user");
		return Result.result(true, "您已退出当前账号", null);
	}
	
}
