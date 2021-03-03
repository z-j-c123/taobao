package controller.getjspcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetJspController {

	@RequestMapping("/index")
	public String getIndexJsp()
	{
		return "index";
	}
	
	@RequestMapping("/userinfo")
	public String getUserInfo()
	{
		return "userinfo";
	}
	@RequestMapping("/login")
	public String getLogin()
	{
		return "login";
	}
	@RequestMapping("/cart")
	public String getCart()
	{
		return "cart";
	}
	@RequestMapping("/information")
	public String getInformation()
	{
		return "information";
	}
	@RequestMapping("/proDetails")
	public String getProDetails()
	{
		return "proDetails";
	}
	@RequestMapping("/purchasePro")
	public String getPurchasePro()
	{
		return "purchasePro";
	}
	@RequestMapping("/singlePurchase")
	public String getSinglePurchase()
	{
		return "singlePurchase";
	}
	@RequestMapping("/proList")
	public String getProList()
	{
		return "proList";
	}
	@RequestMapping("/addCartSuccess")
	public String getAddCartSuccess()
	{
		return "addCartSuccess";
	}
	@RequestMapping("/modifyUserInfo")
	public String getModifyUserInfo()
	{
		return "modifyUserInfo";
	}
	@RequestMapping("/register")
	public String getRegister()
	{
		return "register";
	}
	@RequestMapping("/editPro")
	public String getEditPro()
	{
		return "editPro";
	}
	@RequestMapping("/addPro")
	public String getAddPro()
	{
		return "addPro";
	}
	@RequestMapping("/allPro")
	public String getAllPro()
	{
		return "allPro";
	}
	@RequestMapping("/soldPro")
	public String getSoldPro()
	{
		return "soldPro";
	}
	@RequestMapping("/service")
	public String getService()
	{
		return "service";
	}
	@RequestMapping("/comments")
	public String getComments() {
		return "comments";
	}
	@RequestMapping("/sellerRegist")
	public String getSellerRegist() {
		return "sellerRegist";
	}
	@RequestMapping("/modify")
	public String getModify() {
		return "modify";
	}
	@RequestMapping("/modifyPro")
	public String getModifyPro() {
		return "modifyPro";
	}
}
