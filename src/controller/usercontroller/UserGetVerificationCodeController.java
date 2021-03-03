package controller.usercontroller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Result;
import po.User;
import po.VerificationCode;
import service.userservice.UserService;
import util.NowTime;
import util.OperationSession;
import util.SendCode;

@Controller
public class UserGetVerificationCodeController {

	@Autowired
	private UserService userService;
	
	//用户登录获取验证码
	@RequestMapping("/logingetcode")
	@ResponseBody
	public Result loginGetCode(@RequestBody User user,HttpSession session)
	{
		if(user==null||user.getPhoneNumber()==null)
			return Result.fail("手机号码不能为空");
		//查询是否存在该用户
		User loginUser = new User();
		loginUser.setPhoneNumber(user.getPhoneNumber());
		List<User> listUser;
		try
		{
			listUser = userService.findUser(loginUser);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("获取验证码失败，请稍后再试");
		}
		if(listUser==null||listUser.size()<1)
			return Result.fail("该用户不存在，请核对手机号码，或进行注册");
		//存在该用户
		//发送验证码
		String codeNumber = SendCode.getCode();
		try
		{
			SendCode.sendSms(loginUser.getPhoneNumber(),codeNumber);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("获取验证码失败，请稍后再试");
		}
		//将验证码存入session，登录时验证
		VerificationCode vc= new VerificationCode();
		vc.setCodeNumber(codeNumber);
		vc.setCreateCodeNumberTime(System.currentTimeMillis());
		session.removeAttribute("loginVerificationCode");
		session.setAttribute("loginVerificationCode",vc);
		return Result.result(true, "获取验证码成功", null);
	}
	
	//用户注册获取验证码
	@RequestMapping("/addusergetcode")
	@ResponseBody
	public Result addUserGetCode(@RequestBody User user,HttpSession session)
	{
		//效验数据
		if(user==null||user.getPhoneNumber()==null)
			return Result.fail("手机号码不能为空");
		//发送验证码
		String codeNumber = SendCode.getCode();
		try
		{
			SendCode.sendSms(user.getPhoneNumber(),codeNumber);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("获取验证码失败，请稍后再试");
		}
		VerificationCode vc= new VerificationCode();
		vc.setCodeNumber(codeNumber);
		vc.setCreateCodeNumberTime(System.currentTimeMillis());
		session.removeAttribute("addUserVerificationCode");
		session.setAttribute("addUserVerificationCode",vc);
		return Result.result(true, "获取验证码成功", null);
	}
}
