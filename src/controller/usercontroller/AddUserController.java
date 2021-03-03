package controller.usercontroller;

import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.Result;
import po.User;
import po.VerificationCode;
import service.userservice.UserService;
import util.NowTime;
import util.OperationSession;
import util.SendCode;

@Controller
public class AddUserController {
	
	@Autowired
	private UserService userSerivce;
	
	@RequestMapping("/adduserjsp")
	public String getAddUserJsp()
	{
		return "adduser";
	}
	
	@RequestMapping("/adduser")
	@ResponseBody
	public Result addUser(@RequestBody User user,HttpSession session)
	{
		//效验数据是否为空
		if(user==null||
				user.getPhoneNumber()==null||user.getPhoneNumber().equals("")||
				user.getPassWord()==null||user.getPassWord().equals("")||
				user.getUserName()==null||user.getUserName().equals("")||
				user.getSex()==null||user.getSex().equals("")||
				user.getIdCard()==null||user.getIdCard().equals(""))
			return Result.fail("请填入必填项");
		//判断性别是否符合规范
		if(!(user.getSex().equals("男")||user.getSex().equals("女")))
			return Result.fail("数据错误");
		//判断是否存在相同的手机号码
		String codeNumber = user.getCustomerType();
		user.setCustomerType(null);
		//判断是否获取过验证码
		if(session.getAttribute("addUserVerificationCode")==null)
			return Result.fail("请先获取验证码再注册");
		VerificationCode vc = (VerificationCode)session.getAttribute("addUserVerificationCode");
		//判断验证码是否正确
		if(!codeNumber.equals(vc.getCodeNumber()))
			return Result.fail("验证码错误");
		//判断验证码是否超时
		if(SendCode.whetherTimeOut(vc.getCreateCodeNumberTime()))
		{
			session.removeAttribute("addUserVerificationCode");
			return Result.fail("验证码超时");
		}
		user.setCustomerType("买家");
		user.setState("1");
		String time = NowTime.getNowTime();
		user.setCreateTime(time);
		user.setRecentlyLogin(time);
		Result result = null;
		try
		{
			//插入数据
			result = userSerivce.addUser(user);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("注册失败，请稍后再试");
		}
		if(result!=null&&result.getFlag())
		{
			session.setAttribute("user",result.getData());
			session.removeAttribute("addUserVerificationCode");
		}
		else
			return Result.fail("注册失败");
		return result;
	}
}
