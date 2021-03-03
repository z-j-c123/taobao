package controller.usercontroller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Result;
import po.User;
import service.userservice.UserService;

@Controller
public class UserLoginController {
	@Autowired
	private UserService userService;

	@RequestMapping("/loginJsp")
	public String getLoginJsp() {
		return "login";
	}
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public Result login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(user.toString());
//		判断获取的参数是否有误
		if (user == null || user.getPhoneNumber() == null || user.getPhoneNumber().equals("")
				|| user.getPassWord() == null || user.getPassWord().equals(""))
			return Result.fail("输入不能为空");
//		 输入参数均不为空，去数据库查询
		Result result = null;
		try {
			result = userService.login(user);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return Result.fail("登录失败，请稍后再试");
		}
		HttpSession session = request.getSession();
		session.setAttribute("user", (User) result.getData());
		Cookie cookie = new Cookie("JSESSIONID", session.getId());
		response.addCookie(cookie);
		user = (User) result.getData();
		return result;
	}

}
