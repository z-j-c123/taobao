package po;

public class ShoppingCartCommodity {
	
	//购物车记录的id
	private Integer id;
	//商品id
	private Integer commodityId;
	//商品规格id
	private Integer commoditydetailsId;
	//商品的名字
	private String commodityName;
	//商品的规格名称
	private String specifications;
	//商品的价格
	private Double price;
	//商品的数量
	private Integer count;
	//商品的图片路径
	private String commodityPicture1;
	
	public ShoppingCartCommodity()	{}

	public ShoppingCartCommodity(Integer id, Integer commodityId, Integer commoditydetailsId, String commodityName,
			String specifications, Double price, Integer count, String commodityPicture1) {
		super();
		this.id = id;
		this.commodityId = commodityId;
		this.commoditydetailsId = commoditydetailsId;
		this.commodityName = commodityName;
		this.specifications = specifications;
		this.price = price;
		this.count = count;
		this.commodityPicture1 = commodityPicture1;
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

	public Integer getCommoditydetailsId() {
		return commoditydetailsId;
	}

	public void setCommoditydetailsId(Integer commoditydetailsId) {
		this.commoditydetailsId = commoditydetailsId;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getCommodityPicture1() {
		return commodityPicture1;
	}

	public void setCommodityPicture1(String commodityPicture1) {
		this.commodityPicture1 = commodityPicture1;
	}

	public String toString() {
		return "ShoppingCartCommodity [id=" + id + ", commodityId=" + commodityId + ", commoditydetailsId="
				+ commoditydetailsId + ", commodityName=" + commodityName + ", specifications=" + specifications
				+ ", price=" + price + ", count=" + count + ", commodityPicture1=" + commodityPicture1 + "]";
	}
	
	
}
