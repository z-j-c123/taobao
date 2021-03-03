package po;

public class LunBoBiaoCommodity {

	//用户搜索的模糊商品名
	private String ssName;
	//该商品在一段时间内的销量
	private Integer orderCount;
	private Commodity commodity;
	
	public LunBoBiaoCommodity()	{}

	public LunBoBiaoCommodity(String ssName, Integer orderCount, Commodity commodity) {
		super();
		this.ssName = ssName;
		this.orderCount = orderCount;
		this.commodity = commodity;
	}

	public String getSsName() {
		return ssName;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setSsName(String ssName) {
		this.ssName = ssName;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public String toString() {
		return "LunBoBiaoCommodity [ssName=" + ssName + ", orderCount=" + orderCount + ", commodity=" + commodity + "]";
	}
	
	
}
