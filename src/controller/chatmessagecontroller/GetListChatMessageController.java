package controller.chatmessagecontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.chatmessagedao.ChatMessageDao;
import po.ChatMessage;
import po.Result;
import po.User;
import service.chatmessageservice.ChatMessageService;
import util.NowTime;

@Controller
public class GetListChatMessageController {

	@Autowired
	private ChatMessageService chatMessageService;
	
	@RequestMapping("/getlistchatmessage")
	@ResponseBody
	public Result getListChatMessage(@RequestBody ChatMessage chatMessage,HttpSession session)
	{
		if(session.getAttribute("user")==null)
			return Result.fail("请先登陆");
		Result result = null;
		if(chatMessage!=null&&chatMessage.getReceiveId()>0)
		{
			try
			{
				System.out.println(chatMessage.getReceiveId());
				System.out.println(((User)session.getAttribute("user")).getId());
				result =  
						chatMessageService.getListChatMessage
						(chatMessage.getReceiveId(),((User)session.getAttribute("user")).getId());
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				Result.fail("获取聊天消息失败，请稍后再试");
			}
		}
		else
		{
			System.out.println("chatMessageId为空");
		}
		if(result==null||result.getData()==null)
			return Result.fail("获取单个聊天消息失败，请稍后再试");
		return result;
	}
}
