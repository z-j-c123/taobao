package po;

public class SellOrder {

	//订单id
	private Integer id;
	//订单号
	private String orderNumber;
	//商品id
	private Integer commodityId;
	//商品规格id
	private Integer commodityDetailsId;
	//商品名称
	private String commodityName;
	//商品规格名称
	private String specifications;
	//购买的商品数量
	private Integer orderCount;
	//商品总价
	private Double orderPrice;
	//订单状态
	private String orderStatus;
	//收货地址
	private String address;
	//商品图片
	private String commodityPicture;
	
	public SellOrder()	{}

	public SellOrder(Integer id, String orderNumber, Integer commodityId, Integer commodityDetailsId,
			String commodityName, String specifications, Integer orderCount, Double orderPrice, String orderStatus,
			String address, String commodityPicture) {
		super();
		this.id = id;
		this.orderNumber = orderNumber;
		this.commodityId = commodityId;
		this.commodityDetailsId = commodityDetailsId;
		this.commodityName = commodityName;
		this.specifications = specifications;
		this.orderCount = orderCount;
		this.orderPrice = orderPrice;
		this.orderStatus = orderStatus;
		this.address = address;
		this.commodityPicture = commodityPicture;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getCommodityDetailsId() {
		return commodityDetailsId;
	}

	public void setCommodityDetailsId(Integer commodityDetailsId) {
		this.commodityDetailsId = commodityDetailsId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCommodityPicture() {
		return commodityPicture;
	}

	public void setCommodityPicture(String commodityPicture) {
		this.commodityPicture = commodityPicture;
	}

	public String toString() {
		return "SellOrder [id=" + id + ", orderNumber=" + orderNumber + ", commodityId=" + commodityId
				+ ", commodityDetailsId=" + commodityDetailsId + ", commodityName=" + commodityName
				+ ", specifications=" + specifications + ", orderCount=" + orderCount + ", orderPrice=" + orderPrice
				+ ", orderStatus=" + orderStatus + ", address=" + address + ", commodityPicture=" + commodityPicture
				+ "]";
	}

	
}
