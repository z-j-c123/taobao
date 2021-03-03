package po;

public class Journal {

	//自增id
	private Integer id;
	//用户id（哪个用户的操作）
	private Integer userId;
	//操作时间
	private String journalTime;
	//做了什么操作
	private String operation;
	
	public Journal()	{}
	
	public Journal(Integer id, Integer userId, String journalTime, String operation) {
		super();
		this.id = id;
		this.userId = userId;
		this.journalTime = journalTime;
		this.operation = operation;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getJournalTime() {
		return journalTime;
	}

	public String getOperation() {
		return operation;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setJournalTime(String journalTime) {
		this.journalTime = journalTime;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String toString() {
		return "Journal [id=" + id + ", userId=" + userId + ", journalTime=" + journalTime + ", operation=" + operation
				+ "]";
	}
	
}

