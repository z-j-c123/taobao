package service.journalservice;

import java.util.List;

import po.Journal;

public interface JournalService {
	//添加日志记录
	public Integer addJournal(Journal journal) throws Exception;
}
