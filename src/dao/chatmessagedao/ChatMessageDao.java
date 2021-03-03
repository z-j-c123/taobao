package dao.chatmessagedao;

import java.util.List;

import po.ChatMessage;

public interface ChatMessageDao {

	public List<ChatMessage> getListChatMessage(ChatMessage chatMessage) throws Exception;
	public Integer addChatMessage(ChatMessage chatMessage) throws Exception;
	public Integer updateChatMessage(ChatMessage chatMessage) throws Exception;
}