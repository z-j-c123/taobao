package po;

public class CommodityDetails {
	private Integer id;
	private Integer commodityId;
	private String specifications;
	private Double price;
	private Integer stock;

	public CommodityDetails() {}

	public CommodityDetails(Integer id, Integer commodityId, String specifications, Double price, Integer stock) {
		super();
		this.id = id;
		this.commodityId = commodityId;
		this.specifications = specifications;
		this.price = price;
		this.stock = stock;
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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "CommodityDetails [id=" + id + ", commodityId=" + commodityId + ", specifications=" + specifications
				+ ", price=" + price + ", stock=" + stock + "]";
	}

}
