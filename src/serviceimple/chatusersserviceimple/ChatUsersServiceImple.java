package serviceimple.chatusersserviceimple;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.ChatUsers;
import po.Journal;
import po.Result;
import service.chatusersservice.ChatUsersService;
import util.NowTime;
import dao.chatusersdao.ChatUsersDao;
import dao.journaldao.JournalDao;

@Service
@Transactional
public class ChatUsersServiceImple implements ChatUsersService{

	@Autowired
	private ChatUsersDao ChatUsersDao;
	@Autowired
	private JournalDao journalDao;
	
	public Result getListChatUsers(Integer id) throws Exception{
		Result result = new Result();
		if(id!=null&&id>0)
		{
			List<ChatUsers> listChatUsers = ChatUsersDao.getListChatUsers(id);
			System.out.println("aaaaaaaaaa"+listChatUsers.size());
			result.setFlag(true);
			result.setMessage("成功");
			result.setData(listChatUsers);
		}
		//写入日志
		Journal journal = new Journal();
		journal.setUserId(id);
		journal.setJournalTime(NowTime.getNowTime());
		journal.setOperation("进入消息页面");
		try
		{
			journalDao.addJournal(journal);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}

}
