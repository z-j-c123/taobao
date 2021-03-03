package controller.commodityordercontroller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import po.Commodity;
import po.CommodityDetails;
import po.Result;
import service.commoditydetailsservice.CommodityDetailsService;
import service.commodityservice.CommodityService;

@Controller
public class GoPayController {

	@Autowired
	private CommodityDetailsService commodityDetailsService;
	@Autowired
	private CommodityService commodityService;

	@RequestMapping("/payJsp")
	// 点立即购买，跳到订单信息页面，然后点提交订单
	public String payJsp(HttpServletRequest request, HttpServletResponse response) {
		String[] tempCommodityDetailsIdList = request.getParameterValues("commodityDetailsId");
		String[] tempOrderCountList = request.getParameterValues("orderCount");
		ArrayList<String> commodityDetailsIdList = new ArrayList<String>();
		ArrayList<String> orderCountList = new ArrayList<String>();
		for (String commodityDetailsId : tempCommodityDetailsIdList) {
			if (commodityDetailsId != null && !commodityDetailsId.equals("")) {
				commodityDetailsIdList.add(commodityDetailsId);
			}
		}
		for (String orderCount : tempOrderCountList) {
			if (orderCount != null && !orderCount.equals("")) {
				orderCountList.add(orderCount);
			}
		}
		// 验证商品规格和商品数量是否一一对应
		if (commodityDetailsIdList.size() != orderCountList.size()) {
			return null;
		}
		try {
			for (int i = 0; i < commodityDetailsIdList.size(); i++) {
				String id = commodityDetailsIdList.get(i);
				CommodityDetails commodityDetails = new CommodityDetails();
				commodityDetails.setId(Integer.parseInt(id));
				Result result = commodityDetailsService.findCommodityDetails(commodityDetails);
				if (!result.getFlag()) {
					return null;
				}
				ArrayList<CommodityDetails> tempCommodityDetailsList = (ArrayList<CommodityDetails>) result.getData();
				commodityDetails = tempCommodityDetailsList.get(0);
				Commodity commodity = new Commodity();
				commodity.setId(commodityDetails.getCommodityId());
				result = commodityService.findCommodity(commodity);
				ArrayList<Commodity> commodityList = (ArrayList<Commodity>) result.getData();
				commodity = commodityList.get(0);
				if(commodityDetails.getStock() < Integer.parseInt(orderCountList.get(i))) {
					return commodity.getCommodityName()+"的"+commodityDetails.getSpecifications()+"的库存不足";
				}
			}
		} catch (Exception e) {

		}
		return "pay";
	}

}
