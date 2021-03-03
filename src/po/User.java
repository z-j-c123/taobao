package po;

//用户类
public class User {

	//自增id
	private Integer id;
	//手机号码
	private String phoneNumber;
	//登录密码
	private String passWord;
	//用户名
	private String userName;
	//性别
	private String sex;
	//身份证号码
	private String idCard;
	//用户类型
	//管理员、卖家、买家
	private String customerType;
	//商铺名称
	private String storeName;
	//用户状态
	//0：失效
	//1：有效
	private String state;
	//创建用户时间
	private String createTime;
	//用户最近登录日期
	private String recentlyLogin;
	
	public User()	{}

	public User(Integer id, String phoneNumber, String passWord, String userName, String sex, String idCard,
			String customerType, String storeName, String state, String createTime, String recentlyLogin) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.passWord = passWord;
		this.userName = userName;
		this.sex = sex;
		this.idCard = idCard;
		this.customerType = customerType;
		this.storeName = storeName;
		this.state = state;
		this.createTime = createTime;
		this.recentlyLogin = recentlyLogin;
	}

	public Integer getId() {
		return id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getUserName() {
		return userName;
	}

	public String getSex() {
		return sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public String getCustomerType() {
		return customerType;
	}

	public String getStoreName() {
		return storeName;
	}

	public String getState() {
		return state;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getRecentlyLogin() {
		return recentlyLogin;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setRecentlyLogin(String recentlyLogin) {
		this.recentlyLogin = recentlyLogin;
	}

	public String toString() {
		return "User [id=" + id + ", phoneNumber=" + phoneNumber + ", passWord=" + passWord + ", userName=" + userName
				+ ", sex=" + sex + ", idCard=" + idCard + ", customerType=" + customerType + ", storeName=" + storeName
				+ ", state=" + state + ", createTime=" + createTime + ", recentlyLogin=" + recentlyLogin + "]";
	}

	
	
}
