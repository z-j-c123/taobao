package controller.commodityordercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.internal.util.AlipaySignature;

import po.Commodity;
import po.CommodityOrder;
import po.Result;
import po.User;
import service.commodityorderservice.CommodityOrderService;
import service.commodityservice.CommodityService;
import service.userservice.UserService;
import util.AlipayConfig;

@Controller
public class PaySuccessController {

	@Autowired
	private CommodityOrderService commodityOrderServcie;
	@Autowired
	private UserService userService;
	@Autowired
	private CommodityService commodityService;

	// 同步通知
	@RequestMapping("/paySuccessJsp")
	public String paySuccessJsp(HttpServletRequest request, Model model) {
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.sign_type);
			if (signVerified) {
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
				model.addAttribute("flag", true);
			} else {
				model.addAttribute("flag", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}

	// 异步通知
	@RequestMapping("/paySuccess")
	public void paySuccess(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		try {
			// 调用SDK验证签名
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key,
					AlipayConfig.charset, AlipayConfig.sign_type);
			if (signVerified) {// 验证成功
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
				String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
				String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");
				String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
				// 验证订单号是否存在
				CommodityOrder commodityOrder = new CommodityOrder();
				if (out_trade_no.startsWith("t")) {
					commodityOrder.setTogetherOrderNumber(out_trade_no);
				} else if (out_trade_no.startsWith("kd")) { // 开店，没有记录订单，单独处理
					int userId = Integer.parseInt(out_trade_no.substring(2, out_trade_no.length()));
					User user = new User();
					user.setId(userId);
					user.setCustomerType("卖家");
					// 修改用户身份为卖家
					userService.updateUser(user);
					return;
				}else {
					commodityOrder.setOrderNumber(out_trade_no);
				}
				Result result = commodityOrderServcie.findCommodityOrder(commodityOrder);
				if (!result.getFlag()) {
					out.println("fail");
					return;
				}
				ArrayList<CommodityOrder> commodityOrderList = (ArrayList<CommodityOrder>) result.getData();
				// 验证付款金额
				double orderPrice = 0;
				for (CommodityOrder commodityOrder1 : commodityOrderList) {
					orderPrice += commodityOrder1.getOrderPrice();
				}
				if (orderPrice != Double.parseDouble(total_amount)) {
					out.println("fail");
					return;
				}
				// 验证seller_id
				if (!seller_id.equals(AlipayConfig.seller_id)) {
					out.println("fail");
					return;
				}
				// 验证app_id
				if (!app_id .equals(AlipayConfig.app_id)) {
					out.println("fail");
					return;
				}
				// 交易结束
				if (trade_status.equals("TRADE_FINISHED")) {
					for (CommodityOrder commodityOrder1 : commodityOrderList) {
						commodityOrderServcie.tradeFinish(commodityOrder1.getId()).getFlag();
					}
				} else if (trade_status.equals("TRADE_SUCCESS")) {// 交易支付成功
					for (CommodityOrder commodityOrder1 : commodityOrderList) {
						commodityOrderServcie.tradeSuccess(commodityOrder1.getId()).getFlag();
						// 增加销量
						Commodity commodity = new Commodity();
						commodity.setId(commodityOrder1.getCommodityId());
						commodityService.addCommodityCount(commodity);
					}
				}
				out.println("success");
			} else {// 验证失败
				out.println("fail");
			}
		} catch (Exception e1) {
			out.println("fail");
			System.out.println("异常：PaySuccessController类，paySuccess方法");
			e1.printStackTrace();
		}
	}

}
