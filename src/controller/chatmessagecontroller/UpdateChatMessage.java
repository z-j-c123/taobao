package controller.chatmessagecontroller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import po.ChatMessage;
import po.Result;
import po.User;
import service.chatmessageservice.ChatMessageService;
import util.NowTime;

@Controller
public class UpdateChatMessage {

	@Autowired
	private ChatMessageService chatMessageService;
	
	@RequestMapping("/updatechatcessage")
	@ResponseBody
	public Result updateChatMessage(@RequestBody ChatMessage chatMessage,HttpSession session)
	{
		if(chatMessage!=null)
		{
			if(session.getAttribute("user")==null)
			{
				Result.fail("请先登陆");
			}
			chatMessage.setSendOutId(((User)session.getAttribute("user")).getId());
			chatMessage.setTime(NowTime.getNowTime());
			try
			{
				return chatMessageService.updateChatMessage(chatMessage);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return Result.fail("已读消息跟新失败");
			}
		}
		else
		{
			return Result.fail("已读消息跟新失败");
		}
	}
}
