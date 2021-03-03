package controller.shoppingcartcontroller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.commoditydao.CommodityDao;
import po.Commodity;
import po.Result;
import po.ShoppingCart;
import po.User;
import service.shoppingcartservice.ShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@RequestMapping("/getShoppingCartJsp")
	public String getShoppingCartJsp() {
		return "shoppingcart";
	}

	// 商品加入购物车
	// 传递 commodityId | commoditydetailsId | commodityCount
	@RequestMapping("/addShoppingCart")
	@ResponseBody
	public Result addShoppingCart(@RequestBody ShoppingCart shoppingCart, HttpSession session) {
		if (shoppingCart == null || shoppingCart.getCommodityId() == null || shoppingCart.getCommodityId() <= 0
				|| shoppingCart.getCommoditydetailsId() == null || shoppingCart.getCommoditydetailsId() <= 0
				|| shoppingCart.getCommodityCount() == null || shoppingCart.getCommodityCount() <= 0)
			return Result.fail("数据错误，商品加入购物车失败");
		else {
			// 判断用户是否登录
			User user = (User) session.getAttribute("user");
			if (user == null)
				return Result.fail("请先登录");
			else {
				shoppingCart.setUserId(user.getId());
				// 调用service
				try {
					return shoppingCartService.addShoppingCart(shoppingCart);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("ShoppingCartController类addShoppingCart方法异常");
					return Result.fail("商品加入购物车失败，请稍后再试");
				}
			}
		}
	}

	// 删除购物车商品
	// 前台传递一个数组
	// data:JSON.stringify({
	// arr : [3,4],
	// }),
	@RequestMapping("/deleteShoppingCart")
	@ResponseBody
	public Result deleteShoppingCart(@RequestBody Map<String, List<Integer>> map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Result.fail("请先登陆");
		List<Integer> listInteger = map.get("arr");
		String condition = "";
		for (int i = 0; i < listInteger.size(); i++)
			condition += listInteger.get(i) + ",";
		try {
			return shoppingCartService.deleteShoppingCart(user.getId(), condition);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ShoppingCartController类deleteShoppingCart方法异常");
			return Result.fail("删除失败，请稍后再试");
		}
	}

	// 查询用户购物车中的商品
	// 不需要传递任何数据，直接发起请求即可
	@RequestMapping("/findShoppingCart")
	@ResponseBody
	public Result findShoppingCart(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Result.fail("请先登录后再查看自己的购物车哦！");
		try {
			return shoppingCartService.findShoppingCart(user.getId());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("ShoppingCartController类findShoppingCart方法异常");
			return Result.fail("查询购物车中的商品失败，请烧糊再试");
		}
	}

	// 修改购物车商品信息
	// 传递商品id,商品数量count
	// 关键字必须为id,count
	@RequestMapping("updateShoppingCart")
	@ResponseBody
	public Result updateShoppingCart(@RequestBody Map<String, String> map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Result.fail("请先登录");
		Integer id;
		Integer count;
		try {
			id = new Integer(map.get("id"));
			count = new Integer(map.get("count"));
		} catch (Exception e) {
			System.out.println("传递数据错误");
			System.out.println(e.getMessage());
			return Result.fail("修改失败");
		}
		if (id != null && id > 0 && count != null && count > 0) {
			ShoppingCart sc = new ShoppingCart();
			sc.setId(id);
			sc.setUserId(user.getId());
			sc.setCommodityCount(count);
			try {
				return shoppingCartService.updateShoppingCart(sc);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ShoppingCartController类updateShoppingCart方法异常");
				return Result.fail("修改失败");
			}
		} else
			return Result.fail("数据错误");
	}

	// 根据id查询购物车记录
	// 前台传递一个id数组
	// map.key为arr
	@RequestMapping("/findListIdShoppingCart")
	@ResponseBody
	public Result findListIdShoppingCart(@RequestBody Map<String, List<Integer>> map, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return Result.fail("请先登录");
		if (map != null && map.get("arr") != null) {
			List<Integer> arr = map.get("arr");
			String condition = "";
			try {
				for (int i = 0; i < arr.size(); i++)
					condition += arr.get(i) + ",";
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ShoppingCartController类findListIdShoppingCart方法异常,异常为参数错误");
				return Result.fail("数据错误");
			}
			// 查询
			try {
				return shoppingCartService.findListShoppingCartCommodity(user.getId(), condition);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println("ShoppingCartController类findListIdShoppingCart方法查询异常");
				return Result.fail("查询失败");
			}
		} else {
			return Result.fail("前台传递数据错误");
		}
	}
}
