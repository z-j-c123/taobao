package po;

public class Order {

		//订单id
		private Integer id;
		//商品id
		private Integer commodityId;
		//商品规格id
		private Integer commodityDetailsId;
		//商品名称
		private String commodityName;
		//商品规格名称
		private String specifications;
		//商品图片
		private String commodityPicture;
		//订单购买的商品数量
		private Integer orderCount;
		//订单总价格
		private Double orderPrice;
		//订单状态
		private String orderStatus;
		
		private Order()	{}

		public Order(Integer id, Integer commodityId, Integer commodityDetailsId, String commodityName,
				String specifications, String commodityPicture, Integer orderCount, Double orderPrice, String orderStatus) {
			super();
			this.id = id;
			this.commodityId = commodityId;
			this.commodityDetailsId = commodityDetailsId;
			this.commodityName = commodityName;
			this.specifications = specifications;
			this.commodityPicture = commodityPicture;
			this.orderCount = orderCount;
			this.orderPrice = orderPrice;
			this.orderStatus = orderStatus;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public String getCommodityPicture() {
			return commodityPicture;
		}

		public void setCommodityPicture(String commodityPicture) {
			this.commodityPicture = commodityPicture;
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

		public String toString() {
			return "Order [id=" + id + ", commodityId=" + commodityId + ", commodityDetailsId=" + commodityDetailsId
					+ ", commodityName=" + commodityName + ", specifications=" + specifications + ", commodityPicture="
					+ commodityPicture + ", orderCount=" + orderCount + ", orderPrice=" + orderPrice + ", orderStatus="
					+ orderStatus + "]";
		}

		

}
