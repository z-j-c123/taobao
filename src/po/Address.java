package po;

public class Address {

	//自增id
	private Integer id;
	//哪个用户的地址记录
	private Integer userId;
	//收货人姓名
	private String userName;
	//收货人手机号码
	private String phoneNumber;
	//收货人地址
	private String detailedAddress;
	
	public Address()	{}

	public Address(Integer id, Integer userId, String userName, String phoneNumber, String detailedAddress) {
		super();
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
		this.detailedAddress = detailedAddress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", userName=" + userName + ", phoneNumber=" + phoneNumber
				+ ", detailedAddress=" + detailedAddress + "]";
	}

	public String getOrderAddress() {
		return "姓名：" + userName + "，电话：" + phoneNumber + "，地址：" + detailedAddress;
	}
	
}
