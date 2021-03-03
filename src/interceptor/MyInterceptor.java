
package interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import po.Result;
import po.User;
import service.userservice.UserService;

public class MyInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
//		设置浏览器不缓存页面
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "No-cache");
		response.setDateHeader("Expires", 0);
		HttpSession session = request.getSession();
		User loggedUser = (User) session.getAttribute("user");
		// 当用户未登录时
		if (loggedUser == null) {
			// 若访问的是需要登录的页面，则拒绝访问并跳转至登录页面
			if (!dontNeedLogin(request)) {
				response.sendRedirect(request.getContextPath()+"/login");
				return false;
			}
		}
//		当用户已登录时
		else {
			User user = new User();
			user.setId(loggedUser.getId());
			List<User> userList = userService.findUser(user);
			loggedUser = userList.get(0);
			session.setAttribute("user", loggedUser);
			// 过滤需要卖家权限的请求
			if (!loggedUser.getCustomerType().equals("卖家")) {
				// 若访问的是需要卖家身份的页面，则拒绝访问并跳转至首页
				if (!needSeller(request)) {
					response.sendRedirect(request.getContextPath()+"/index");
					return false;
				}
			}
		}
		return true;
	}

//	过滤不需要登录的请求
	public boolean dontNeedLogin(HttpServletRequest request) {
//		获取请求路径
		String uri = request.getRequestURI();
		ArrayList<String> list = new ArrayList<String>();
		list.add("index");
		list.add("adduser");
		list.add("login");
		list.add("Login");
		list.add("register");
		list.add("getAllCommodity");
		list.add("singleCommodity");
		list.add("proDetails");
		list.add("loginGetRotationTableCommodity");
		for (String string : list) {
			if(uri.contains(string)) {
				return true;
			}
		}
		return false;
	}

//	过滤需要卖家权限的请求
	public boolean needSeller(HttpServletRequest request) {
//		获取请求路径
		String uri = request.getRequestURI();
		ArrayList<String> list = new ArrayList<String>();
		list.add("upload");
		list.add("Seller");
		list.add("Sell");
		list.add("refund");
		list.add("deliver");
		list.add("updateCommodity");
		list.add("deleteCommodity");
		for (String string : list) {
			if(uri.contains(string)) {
				return false;
			}
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

}
