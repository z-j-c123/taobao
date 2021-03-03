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
public class AddChatMessage {

	@Autowired
	private ChatMessageService chatMessageService;
	
	@RequestMapping("/getaddchatmessagejsp")
	public String getAddChatMessageJsp()
	{
		return "addchatmessage";
	}
	
	@RequestMapping("/addChatMessage")
	@ResponseBody
	public Result addChatMessage(@RequestBody ChatMessage chatMessage,HttpSession session)
	{
		if(chatMessage!=null&&chatMessage.getReceiveId()!=null&&chatMessage.getMessage()!=null)
		{
			Result result = null;
			if(session.getAttribute("user")==null)
				return Result.fail("请先登陆");
			chatMessage.setSendOutId(((User)session.getAttribute("user")).getId());
			chatMessage.setTime(NowTime.getNowTime());
			try
			{
				result = chatMessageService.addChatMessage(chatMessage);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				Result.fail("发送消息失败");
			}
			return result;
		}
		else
		{
			return Result.fail("发送消息失败");
		}
	}
	
}
