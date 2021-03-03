package dao.journaldao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Journal;

public interface JournalDao {
	//添加日志
	public Integer addJournal(Journal journal) throws Exception;
	//查询前十条收索记录
	public List<String> collectionRecord(@Param("userId") Integer userId) throws Exception;
}
