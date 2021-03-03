package service.chatusersservice;

import java.util.List;

import po.ChatUsers;
import po.Result;

public interface ChatUsersService {

	public Result getListChatUsers(Integer id) throws Exception;
	
}
