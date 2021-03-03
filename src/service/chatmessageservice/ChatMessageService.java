package service.chatmessageservice;

import java.util.List;

import po.ChatMessage;
import po.Result;

public interface ChatMessageService {

	public Result getListChatMessage(Integer receiveId,Integer userId) throws Exception;
	
	public Result addChatMessage(ChatMessage chatMessage) throws Exception;
	
	public Result updateChatMessage(ChatMessage chatMessage) throws Exception;
}
