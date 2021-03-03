package controller.journalcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import po.Journal;
import service.journalservice.JournalService;

@Controller
public class CollectionRecordController {

	@Autowired
	private JournalService journalService;
	
	@RequestMapping("/getcollectionrecord")
	public void getCollectionRecord()
	{
		
	}
	
	@RequestMapping("/bbb")
	public void bbb()
	{
		System.out.println("bbb");
	}
	
}
