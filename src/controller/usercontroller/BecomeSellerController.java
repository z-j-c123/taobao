package controller.usercontroller;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import po.Result;
import po.User;
import service.userservice.UserService;
import util.AlipayConfig;

@Controller
public class BecomeSellerController {

	@Autowired
	private UserService userService;

	// 跳转开店页面
	@RequestMapping("becomeSellerJsp")
	public String becomeSellerJsp() {
		return "becomeSeller";
	}
	
	// 买家开店，传递店铺名，storeName
	@RequestMapping("/becomeSeller")
	@ResponseBody
	public Result becomeSeller(HttpSession session, @RequestBody HashMap<String, Object> hashMap) {
		User user = (User) session.getAttribute("user");
		if(!user.getCustomerType().equals("买家")) {
			return Result.fail("你已经开过店了，不能重复开店");
		}
		User findUser = new User();
		findUser.setCustomerType("卖家");
		List<User> userList = new ArrayList<User>();
		try {
			userList = userService.findUser(findUser);
		} catch (Exception e1) {
			System.out.println("异常：BecomeSellerController类，becomeSeller方法");
			e1.printStackTrace();
			return Result.fail("开店失败，请稍后再试");
		}
		String storeName = (String) hashMap.get("storeName");
		if(storeName == null || storeName.equals("")) {
			return Result.fail();
		}
		for (User user2 : userList) {
			if(storeName.equals(user2.getStoreName())) {
				return Result.fail("该店铺名已经被使用");
			}
		}
		AlipayClient alipayClient = AlipayConfig.getAlipayClient();
		AlipayTradePagePayRequest alipayTradePagePayRequest = new AlipayTradePagePayRequest();
		alipayTradePagePayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayTradePagePayRequest.setNotifyUrl(AlipayConfig.notify_url);
		String out_trade_no = "kd" + user.getId();
		String total_amount = "5000";
		String subject = "三叶草购物商城开店";
		String body = "";
		alipayTradePagePayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayTradePagePayRequest).getBody();
			User updateUser = new User();
			updateUser.setId(user.getId());
			updateUser.setStoreName(storeName);
			// 设置店铺名
			userService.updateUser(updateUser);
		} catch (Exception e) {
			System.out.println("异常：BecomeSellerController类，becomeSeller方法");
			e.printStackTrace();
			return Result.fail("开店失败，请稍后再试");
		}
		return Result.success(result);
	}
	
}
