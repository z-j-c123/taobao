package controller.chatuserscontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.ChatUsers;
import po.Result;
import po.User;
import service.chatusersservice.ChatUsersService;

@Controller
public class GetListChatUsersController {

	@Autowired
	private ChatUsersService chatUsersService;
	
	@RequestMapping("/getlistchatusers")
	@ResponseBody
	public Result  getListChatUsers(HttpSession session)
	{
		if(session.getAttribute("user")==null)
			return Result.fail("请先登陆");
		Integer id = ((User)session.getAttribute("user")).getId();
		Result result = null;
		try
		{
			result = chatUsersService.getListChatUsers(id);
			List<ChatUsers> listChatUsers = (List<ChatUsers>)result.getData();
			for(int i=0;i<listChatUsers.size();i++)
			{
				System.out.println(listChatUsers.get(i).toString());
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return Result.fail("获取消息失败，请稍后再试");
		}
		if(result==null||result.getData()==null)
		{
			return Result.fail("获取消息失败，请稍后再试");
		}
		return result;
	}
	
}
