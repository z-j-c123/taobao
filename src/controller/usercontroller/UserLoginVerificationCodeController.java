package controller.usercontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.userdao.UserDao;
import po.Journal;
import po.Result;
import po.User;
import po.VerificationCode;
import service.journalservice.JournalService;
import service.userservice.UserService;
import util.NowTime;
import util.OperationSession;
import util.SendCode;

@Controller
public class UserLoginVerificationCodeController {

	@Autowired
	private UserService userService;
	@Autowired
	private JournalService journalService;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping("/userloginverificationcodecontroller")
	@ResponseBody()
	public Result userLoginVerificationCodeController(@RequestBody User user,HttpSession session)
	{
		//数据效验
		if(user==null||user.getPhoneNumber()==null||user.getPhoneNumber().equals(""))
			return Result.fail("手机号码不能为空");
		if(user==null||user.getPassWord()==null||user.getPassWord().equals(""))
			return Result.fail("验证码不能为空");
		String codeNumber = user.getPassWord();
		user.setPassWord(null);
		//判断是否获取过验证码
		if(session.getAttribute("loginVerificationCode")==null)
			return Result.fail("请先获取验证码");
		VerificationCode vc = (VerificationCode)session.getAttribute("loginVerificationCode");
		//判断验证码是否正确
		if(!codeNumber.equals(vc.getCodeNumber()))
			return Result.fail("验证码错误");
		//判断验证码是否超时
		if(SendCode.whetherTimeOut(vc.getCreateCodeNumberTime()))
		{
			session.removeAttribute("loginVerificationCode");
			return Result.fail("验证码超时");
		}
		//验证码符合正确，判断是否存在该用户
		List<User> listUser = null;
		try
		{
			listUser = userService.findUser(user);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("登录失败，请稍后再试");
		}
		//存在这样的用户
		if(listUser!=null&&listUser.size()>0)
		{
			session.setAttribute("user",listUser.get(0));
			session.removeAttribute("loginVerificationCode");
			//添加日志
			Journal journal = new Journal(null,listUser.get(0).getId(),NowTime.getNowTime(),"手机验证码登录");
			try
			{
				journalService.addJournal(journal);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			//修改最后登录时间
			try
			{
				User updateUser = new User();
				updateUser.setId(listUser.get(0).getId());
				updateUser.setRecentlyLogin(NowTime.getNowTime());
				userDao.updateUser(updateUser);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			return Result.result(true, "登录成功", listUser.get(0));
		}
		else
		{
			return Result.fail("该用户不存在");
		}
	}
	
}
