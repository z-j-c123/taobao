package po;

public class ChatUsers {
	//聊天对象id
	private Integer userId;
	//几条未读消息
	private Integer unread;
	//最新的一条消息
	private String message;
	//最新消息时间
	private String time;
	//哪个用户给我的消息
	private User user;
	
	public ChatUsers()	{}

	public ChatUsers(Integer userId, Integer unread, String message, String time, User user) {
		super();
		this.userId = userId;
		this.unread = unread;
		this.message = message;
		this.time = time;
		this.user = user;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUnread() {
		return unread;
	}

	public void setUnread(Integer unread) {
		this.unread = unread;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		return "ChatUsers [userId=" + userId + ", unread=" + unread + ", message=" + message + ", time=" + time
				+ ", user=" + user + "]";
	}

	
	
}
