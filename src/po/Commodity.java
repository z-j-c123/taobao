package po;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

//商品类
public class Commodity {

	//商品id,自增
	private Integer id;
	//卖家id
	private Integer sellerId;
	//类别id
	private String commodityType;
	//商品类别
	private String commodityName;
	//商品销量
	private Integer commodityCount;
	//商铺名称
	private String storeName;
	//商品图片，最多15张
	private String commodityPicture1;
	private String commodityPicture2;
	private String commodityPicture3;
	private String commodityPicture4;
	private String commodityPicture5;
	private String commodityPicture6;
	private String commodityPicture7;
	private String commodityPicture8;
	private String commodityPicture9;
	private String commodityPicture10;
	private String commodityPicture11;
	private String commodityPicture12;
	private String commodityPicture13;
	private String commodityPicture14;
	private String commodityPicture15;
	private List<String> commodityPictures;
	private List<CommodityDetails> commodityDetailsList;
//	将图片地址存入list集合
	public void initCommodityPictures()
	{
		List<String> tempCommodityPictures = new ArrayList<String>();
		tempCommodityPictures.add(commodityPicture1);
		tempCommodityPictures.add(commodityPicture2);
		tempCommodityPictures.add(commodityPicture3);
		tempCommodityPictures.add(commodityPicture4);
		tempCommodityPictures.add(commodityPicture5);
		tempCommodityPictures.add(commodityPicture6);
		tempCommodityPictures.add(commodityPicture7);
		tempCommodityPictures.add(commodityPicture8);
		tempCommodityPictures.add(commodityPicture9);
		tempCommodityPictures.add(commodityPicture10);
		tempCommodityPictures.add(commodityPicture11);
		tempCommodityPictures.add(commodityPicture12);
		tempCommodityPictures.add(commodityPicture13);
		tempCommodityPictures.add(commodityPicture14);
		tempCommodityPictures.add(commodityPicture15);
//		去除集合里的空值
		commodityPictures = new ArrayList<String>();
		for(String commodityPicture : tempCommodityPictures)
		{
			if(commodityPicture != null)
			{
				commodityPictures.add(commodityPicture);
			}
		}
	}
	public Commodity() {}
	
	public Commodity(Integer id, Integer sellerId, String commodityType, String commodityName, Integer commodityCount,
			String storeName, String commodityPicture1, String commodityPicture2, String commodityPicture3,
			String commodityPicture4, String commodityPicture5, String commodityPicture6, String commodityPicture7,
			String commodityPicture8, String commodityPicture9, String commodityPicture10, String commodityPicture11,
			String commodityPicture12, String commodityPicture13, String commodityPicture14, String commodityPicture15,
			List<String> commodityPictures, List<CommodityDetails> commodityDetailsList) {
		super();
		this.id = id;
		this.sellerId = sellerId;
		this.commodityType = commodityType;
		this.commodityName = commodityName;
		this.commodityCount = commodityCount;
		this.storeName = storeName;
		this.commodityPicture1 = commodityPicture1;
		this.commodityPicture2 = commodityPicture2;
		this.commodityPicture3 = commodityPicture3;
		this.commodityPicture4 = commodityPicture4;
		this.commodityPicture5 = commodityPicture5;
		this.commodityPicture6 = commodityPicture6;
		this.commodityPicture7 = commodityPicture7;
		this.commodityPicture8 = commodityPicture8;
		this.commodityPicture9 = commodityPicture9;
		this.commodityPicture10 = commodityPicture10;
		this.commodityPicture11 = commodityPicture11;
		this.commodityPicture12 = commodityPicture12;
		this.commodityPicture13 = commodityPicture13;
		this.commodityPicture14 = commodityPicture14;
		this.commodityPicture15 = commodityPicture15;
		this.commodityPictures = commodityPictures;
		this.commodityDetailsList = commodityDetailsList;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	public String getCommodityType() {
		return commodityType;
	}
	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Integer getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(Integer commodityCount) {
		this.commodityCount = commodityCount;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getCommodityPicture1() {
		return commodityPicture1;
	}
	public void setCommodityPicture1(String commodityPicture1) {
		this.commodityPicture1 = commodityPicture1;
	}
	public String getCommodityPicture2() {
		return commodityPicture2;
	}
	public void setCommodityPicture2(String commodityPicture2) {
		this.commodityPicture2 = commodityPicture2;
	}
	public String getCommodityPicture3() {
		return commodityPicture3;
	}
	public void setCommodityPicture3(String commodityPicture3) {
		this.commodityPicture3 = commodityPicture3;
	}
	public String getCommodityPicture4() {
		return commodityPicture4;
	}
	public void setCommodityPicture4(String commodityPicture4) {
		this.commodityPicture4 = commodityPicture4;
	}
	public String getCommodityPicture5() {
		return commodityPicture5;
	}
	public void setCommodityPicture5(String commodityPicture5) {
		this.commodityPicture5 = commodityPicture5;
	}
	public String getCommodityPicture6() {
		return commodityPicture6;
	}
	public void setCommodityPicture6(String commodityPicture6) {
		this.commodityPicture6 = commodityPicture6;
	}
	public String getCommodityPicture7() {
		return commodityPicture7;
	}
	public void setCommodityPicture7(String commodityPicture7) {
		this.commodityPicture7 = commodityPicture7;
	}
	public String getCommodityPicture8() {
		return commodityPicture8;
	}
	public void setCommodityPicture8(String commodityPicture8) {
		this.commodityPicture8 = commodityPicture8;
	}
	public String getCommodityPicture9() {
		return commodityPicture9;
	}
	public void setCommodityPicture9(String commodityPicture9) {
		this.commodityPicture9 = commodityPicture9;
	}
	public String getCommodityPicture10() {
		return commodityPicture10;
	}
	public void setCommodityPicture10(String commodityPicture10) {
		this.commodityPicture10 = commodityPicture10;
	}
	public String getCommodityPicture11() {
		return commodityPicture11;
	}
	public void setCommodityPicture11(String commodityPicture11) {
		this.commodityPicture11 = commodityPicture11;
	}
	public String getCommodityPicture12() {
		return commodityPicture12;
	}
	public void setCommodityPicture12(String commodityPicture12) {
		this.commodityPicture12 = commodityPicture12;
	}
	public String getCommodityPicture13() {
		return commodityPicture13;
	}
	public void setCommodityPicture13(String commodityPicture13) {
		this.commodityPicture13 = commodityPicture13;
	}
	public String getCommodityPicture14() {
		return commodityPicture14;
	}
	public void setCommodityPicture14(String commodityPicture14) {
		this.commodityPicture14 = commodityPicture14;
	}
	public String getCommodityPicture15() {
		return commodityPicture15;
	}
	public void setCommodityPicture15(String commodityPicture15) {
		this.commodityPicture15 = commodityPicture15;
	}
	public List<String> getCommodityPictures() {
		return commodityPictures;
	}
	public void setCommodityPictures(List<String> commodityPictures) {
		this.commodityPictures = commodityPictures;
	}
	public List<CommodityDetails> getCommodityDetailsList() {
		return commodityDetailsList;
	}
	public void setCommodityDetailsList(List<CommodityDetails> commodityDetailsList) {
		this.commodityDetailsList = commodityDetailsList;
	}
	
	public String toString() {
		return "Commodity [id=" + id + ", sellerId=" + sellerId + ", commodityType=" + commodityType
				+ ", commodityName=" + commodityName + ", commodityCount=" + commodityCount + ", storeName=" + storeName
				+ ", commodityPicture1=" + commodityPicture1 + ", commodityPicture2=" + commodityPicture2
				+ ", commodityPicture3=" + commodityPicture3 + ", commodityPicture4=" + commodityPicture4
				+ ", commodityPicture5=" + commodityPicture5 + ", commodityPicture6=" + commodityPicture6
				+ ", commodityPicture7=" + commodityPicture7 + ", commodityPicture8=" + commodityPicture8
				+ ", commodityPicture9=" + commodityPicture9 + ", commodityPicture10=" + commodityPicture10
				+ ", commodityPicture11=" + commodityPicture11 + ", commodityPicture12=" + commodityPicture12
				+ ", commodityPicture13=" + commodityPicture13 + ", commodityPicture14=" + commodityPicture14
				+ ", commodityPicture15=" + commodityPicture15 + ", commodityPictures=" + commodityPictures
				+ ", commodityDetailsList=" + commodityDetailsList + ", getId()=" + getId() + ", getSellerId()="
				+ getSellerId() + ", getCommodityType()=" + getCommodityType() + ", getCommodityName()="
				+ getCommodityName() + ", getCommodityCount()=" + getCommodityCount() + ", getStoreName()="
				+ getStoreName() + ", getCommodityPicture1()=" + getCommodityPicture1() + ", getCommodityPicture2()="
				+ getCommodityPicture2() + ", getCommodityPicture3()=" + getCommodityPicture3()
				+ ", getCommodityPicture4()=" + getCommodityPicture4() + ", getCommodityPicture5()="
				+ getCommodityPicture5() + ", getCommodityPicture6()=" + getCommodityPicture6()
				+ ", getCommodityPicture7()=" + getCommodityPicture7() + ", getCommodityPicture8()="
				+ getCommodityPicture8() + ", getCommodityPicture9()=" + getCommodityPicture9()
				+ ", getCommodityPicture10()=" + getCommodityPicture10() + ", getCommodityPicture11()="
				+ getCommodityPicture11() + ", getCommodityPicture12()=" + getCommodityPicture12()
				+ ", getCommodityPicture13()=" + getCommodityPicture13() + ", getCommodityPicture14()="
				+ getCommodityPicture14() + ", getCommodityPicture15()=" + getCommodityPicture15()
				+ ", getCommodityPictures()=" + getCommodityPictures() + ", getCommodityDetailsList()="
				+ getCommodityDetailsList() + ", getNullPictureId()=" + getNullPictureId() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public void commodityPictureAssignment(Commodity commodity, String pictureId, String value) throws Exception
	{
		Field field = commodity.getClass().getDeclaredField("commodityPicture" + pictureId);
		field.setAccessible(true);
		field.set(commodity, value);
	}
	
	public String getNullPictureId() {
		String pictureId = null;
		if(commodityPicture1 == null) {
			pictureId = "1";
		} else if (commodityPicture2 == null) {
			pictureId = "2";
		} else if (commodityPicture3 == null) {
			pictureId = "3";
		} else if (commodityPicture4 == null) {
			pictureId = "4";
		} else if (commodityPicture5 == null) {
			pictureId = "5";
		} else if (commodityPicture6 == null) {
			pictureId = "6";
		} else if (commodityPicture7 == null) {
			pictureId = "7";
		} else if (commodityPicture8 == null) {
			pictureId = "8";
		} else if (commodityPicture9 == null) {
			pictureId = "9";
		} else if (commodityPicture10 == null) {
			pictureId = "10";
		} else if (commodityPicture11 == null) {
			pictureId = "11";
		} else if (commodityPicture12 == null) {
			pictureId = "12";
		} else if (commodityPicture13 == null) {
			pictureId = "13";
		} else if (commodityPicture14 == null) {
			pictureId = "14";
		} else if (commodityPicture15 == null) {
			pictureId = "15";
		}
		return pictureId;
	}
	
}
