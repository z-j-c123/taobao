package po;

public class CommodityOrder {

	private Integer id;
	private Integer buyerId;
	private Integer sellerId;
	private Integer commodityId;
	private Integer commodityDetailsId;
	private String orderStatus;
	private String orderNumber;
	private String togetherOrderNumber;
	private Integer orderCount;
	private Double orderPrice;
	private String address;
	private String unpaidTime;
	private String paymentTime;
	private String cancelTime;
	private String deliverGoodsTime;
	private String receivingGoodsTime;
	private String applyForRefundTime;
	private String refundedTime;

	public CommodityOrder() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
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

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getTogetherOrderNumber() {
		return togetherOrderNumber;
	}

	public void setTogetherOrderNumber(String togetherOrderNumber) {
		this.togetherOrderNumber = togetherOrderNumber;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUnpaidTime() {
		return unpaidTime;
	}

	public void setUnpaidTime(String unpaidTime) {
		this.unpaidTime = unpaidTime;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDeliverGoodsTime() {
		return deliverGoodsTime;
	}

	public void setDeliverGoodsTime(String deliverGoodsTime) {
		this.deliverGoodsTime = deliverGoodsTime;
	}

	public String getReceivingGoodsTime() {
		return receivingGoodsTime;
	}

	public void setReceivingGoodsTime(String receivingGoodsTime) {
		this.receivingGoodsTime = receivingGoodsTime;
	}

	public String getApplyForRefundTime() {
		return applyForRefundTime;
	}

	public void setApplyForRefundTime(String applyForRefundTime) {
		this.applyForRefundTime = applyForRefundTime;
	}

	public String getRefundedTime() {
		return refundedTime;
	}

	public void setRefundedTime(String refundedTime) {
		this.refundedTime = refundedTime;
	}

	@Override
	public String toString() {
		return "CommodityOrder [id=" + id + ", buyerId=" + buyerId + ", sellerId=" + sellerId + ", commodityId="
				+ commodityId + ", commodityDetailsId=" + commodityDetailsId + ", orderStatus=" + orderStatus
				+ ", orderNumber=" + orderNumber + ", togetherOrderNumber=" + togetherOrderNumber + ", orderCount="
				+ orderCount + ", orderPrice=" + orderPrice + ", address=" + address + ", unpaidTime=" + unpaidTime
				+ ", paymentTime=" + paymentTime + ", cancelTime=" + cancelTime + ", deliverGoodsTime="
				+ deliverGoodsTime + ", receivingGoodsTime=" + receivingGoodsTime + ", applyForRefundTime="
				+ applyForRefundTime + ", refundedTime=" + refundedTime + "]";
	}

	public CommodityOrder(Integer id, Integer buyerId, Integer sellerId, Integer commodityId,
			Integer commodityDetailsId, String orderStatus, String orderNumber, String togetherOrderNumber,
			Integer orderCount, Double orderPrice, String address, String unpaidTime, String paymentTime,
			String cancelTime, String deliverGoodsTime, String receivingGoodsTime, String applyForRefundTime,
			String refundedTime) {
		super();
		this.id = id;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.commodityId = commodityId;
		this.commodityDetailsId = commodityDetailsId;
		this.orderStatus = orderStatus;
		this.orderNumber = orderNumber;
		this.togetherOrderNumber = togetherOrderNumber;
		this.orderCount = orderCount;
		this.orderPrice = orderPrice;
		this.address = address;
		this.unpaidTime = unpaidTime;
		this.paymentTime = paymentTime;
		this.cancelTime = cancelTime;
		this.deliverGoodsTime = deliverGoodsTime;
		this.receivingGoodsTime = receivingGoodsTime;
		this.applyForRefundTime = applyForRefundTime;
		this.refundedTime = refundedTime;
	}
}