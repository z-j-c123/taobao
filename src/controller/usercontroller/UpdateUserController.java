package controller.usercontroller;

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
public class UpdateUserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/getUpdateUserJsp")
	public String getUpdateJsp()
	{
		return "updateuser";
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	//这里只用传递id,passWord,userName,sex
	//id必须传递，其它随便
	public Result updateUser(@RequestBody User newUser,HttpSession session)
	{
		System.out.println(newUser.toString());
		User usedUser = (User)session.getAttribute("user");
		if(usedUser==null)
			return Result.fail("请先登录");
		if(!newUser.getId().equals(usedUser.getId()))
			return Result.fail("数据错误");
		if(newUser!=null)
		{
			try
			{
				Result result = userService.updateUser(usedUser,newUser);
				//替换session中的User
				session.setAttribute("user",result.getData());
				return result;
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("UpdateUserController类updateUserService方法异常");
				return Result.fail("修改失败");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
	
	
}
