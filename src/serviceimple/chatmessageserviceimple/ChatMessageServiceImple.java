package serviceimple.chatmessageserviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.chatmessagedao.ChatMessageDao;
import dao.journaldao.JournalDao;
import po.ChatMessage;
import po.Journal;
import po.Result;
import service.chatmessageservice.ChatMessageService;
import util.NowTime;

@Service
@Transactional
public class ChatMessageServiceImple implements ChatMessageService{

	@Autowired
	private ChatMessageDao chatMessageDao;
	@Autowired
	private JournalDao journalDao;
	
	public Result getListChatMessage(Integer receiveId,Integer userId) throws Exception {
		Result result = new Result();
		if(receiveId!=null&&receiveId>0&&userId!=null&&userId>0)
		{
			ChatMessage cm = new ChatMessage();
			cm.setSendOutId(userId);
			cm.setReceiveId(receiveId);
			result.setData(chatMessageDao.getListChatMessage(cm));
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setSendOutId(userId);
			chatMessage.setReceiveId(receiveId);
			chatMessage.setTime(NowTime.getNowTime());
			updateChatMessage(chatMessage);
			//写入日志
			Journal journal = new Journal();
			journal.setUserId(userId);
			journal.setOperation("进入与"+receiveId+"用户聊天的页面");
			journal.setJournalTime(NowTime.getNowTime());
			try
			{
				journalDao.addJournal(journal);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		result.setFlag(true);
		result.setMessage("成功获取聊天消息记录");
		return result;
	}

	public Result addChatMessage(ChatMessage chatMessage) throws Exception {
		Integer k = null;
		if(chatMessage!=null&&chatMessage.getSendOutId()!=null&&
				chatMessage.getReceiveId()!=null&&chatMessage.getTime()!=null)
		{
			k = chatMessageDao.addChatMessage(chatMessage);
			//写入日志
			Journal journal = new Journal();
			journal.setUserId(chatMessage.getId());
			journal.setOperation("向id为"+chatMessage.getReceiveId()
			+"用户发送chatmessage表id为"+chatMessage.getId()+"的消息");
			journal.setJournalTime(NowTime.getNowTime());
			try
			{
				journalDao.addJournal(journal);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		if(k!=null&&k>0)
			return Result.result(true, "发送消息成功", null);
		return Result.fail("发送消息失败");
	}
	
	public Result updateChatMessage(ChatMessage chatMessage) throws Exception {
		if(chatMessage!=null&&chatMessage.getSendOutId()!=null&&
				chatMessage.getSendOutId()>0&&chatMessage.getReceiveId()!=null&&
				chatMessage.getReceiveId()>0&&chatMessage.getTime()!=null&&
				(!chatMessage.getTime().equals("")))
		{
			chatMessageDao.updateChatMessage(chatMessage);
			//写入日志
			Journal journal = new Journal();
			journal.setUserId(chatMessage.getSendOutId());
			journal.setJournalTime(NowTime.getNowTime());
			journal.setOperation("id为"+chatMessage.getSendOutId()
			+"的用户,游览了id为"+chatMessage.getReceiveId()+"的用户给他发送的消息");
			try
			{
				journalDao.addJournal(journal);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		else
		{
			return Result.fail("消息更新失败");
		}
		return Result.result(true, "消息更新成功", null);
	}

}
