package po;

import java.util.Date;

public class ShoppingCart {

	//自增id
	private Integer id;
	//哪个用户购物车中的商品
	private Integer userId;
	//商品id
	private Integer commodityId;
	//商品规格id
	private Integer commoditydetailsId;
	//商品数量
	private Integer commodityCount;
	//添加到购物车的日期
	private Date joiningTime;
	
	public ShoppingCart()	{}

	public ShoppingCart(Integer id, Integer userId, Integer commodityId, Integer commoditydetailsId,
			Integer commodityCount, Date joiningTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.commoditydetailsId = commoditydetailsId;
		this.commodityCount = commodityCount;
		this.joiningTime = joiningTime;
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

	public Integer getCommodityCount() {
		return commodityCount;
	}

	public void setCommodityCount(Integer commodityCount) {
		this.commodityCount = commodityCount;
	}

	public Date getJoiningTime() {
		return joiningTime;
	}

	public void setJoiningTime(Date joiningTime) {
		this.joiningTime = joiningTime;
	}

	public String toString() {
		return "ShoppingCart [id=" + id + ", userId=" + userId + ", commodityId=" + commodityId
				+ ", commoditydetailsId=" + commoditydetailsId + ", commodityCount=" + commodityCount + ", joiningTime="
				+ joiningTime + "]";
	}
	
	
}
