package service.shoppingcartservice;

import java.util.List;

import po.Result;
import po.ShoppingCart;

public interface ShoppingCartService {

	//将商品添加至购物车
	public Result addShoppingCart(ShoppingCart shoppingcart) throws Exception;
	//删除购物车中的商品
	public Result deleteShoppingCart(Integer userId,String condition) throws Exception;
	//查询购物车中的商品
	public Result findShoppingCart(Integer userId) throws Exception;
	//修改购物车中的商品信息
	public Result updateShoppingCart(ShoppingCart shoppingCart) throws Exception;
	//根据多个id查询记录
	public Result findListShoppingCartCommodity(Integer userId,String condition) throws Exception;
}
