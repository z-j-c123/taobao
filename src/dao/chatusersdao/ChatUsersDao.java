package dao.chatusersdao;

import java.util.List;

import po.ChatUsers;

public interface ChatUsersDao {

	public List<ChatUsers> getListChatUsers(Integer id) throws Exception;
	
}
