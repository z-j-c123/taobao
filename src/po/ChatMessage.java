package po;

public class ChatMessage {

	//自增id
	private Integer id;
	//发送者
	private Integer sendOutId;
	//接收者
	private Integer receiveId;
	//发送的消息内容
	private String message;
	//发送消息的时间
	private String time;
	//消息是否已读
	private Integer see;
	
	public ChatMessage()	{}

	public ChatMessage(Integer id, Integer sendOutId, Integer receiveId, String message, String time, Integer see) {
		super();
		this.id = id;
		this.sendOutId = sendOutId;
		this.receiveId = receiveId;
		this.message = message;
		this.time = time;
		this.see = see;
	}

	public Integer getId() {
		return id;
	}

	public Integer getSendOutId() {
		return sendOutId;
	}

	public Integer getReceiveId() {
		return receiveId;
	}

	public String getMessage() {
		return message;
	}

	public String getTime() {
		return time;
	}

	public Integer getSee() {
		return see;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setSendOutId(Integer sendOutId) {
		this.sendOutId = sendOutId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setSee(Integer see) {
		this.see = see;
	}

	public String toString() {
		return "ChatMessage [id=" + id + ", sendOutId=" + sendOutId + ", receiveId=" + receiveId + ", message="
				+ message + ", time=" + time + ", see=" + see + "]";
	}

}
