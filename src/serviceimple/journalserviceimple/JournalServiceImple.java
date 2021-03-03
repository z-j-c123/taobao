package serviceimple.journalserviceimple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.journaldao.JournalDao;
import po.Journal;
import service.journalservice.JournalService;

@Service
@Transactional
public class JournalServiceImple implements JournalService{

	@Autowired
	private JournalDao journalDao;
	
	public Integer addJournal(Journal journal) throws Exception {
		return journalDao.addJournal(journal);
	}

}
