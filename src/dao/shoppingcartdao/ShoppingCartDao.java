package dao.shoppingcartdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.ShoppingCart;
import po.ShoppingCartCommodity;

public interface ShoppingCartDao {

	//添加商品到购物车
	public Integer addShoppingCart(ShoppingCart shoppingcart) throws Exception;
	//删除购物车中的商品
	public Integer deleteShoppingCart(@Param("userId") Integer userId,@Param("condition") String condition) throws Exception;
	//更新购物车中的商品
	public Integer updateShoppingCart(ShoppingCart shoppingcart) throws Exception;
	//查询用户购物车中的所有商品
	public List<ShoppingCartCommodity> findAllShoppingCart(@Param("userId") Integer userId) throws Exception;
	//查询购物车中的商品
	public List<ShoppingCart> findShoppingCart(ShoppingCart shoppingcart) throws Exception;
	//通过ID集合查询购物车表中的商品
	//public List<ShoppingCartCommodity> findListIdShoppingCart(@Param("condition") String condtion) throws Exception;
	//给定一个String condition = "1,2,3"
	//在购物车表中查询出id in condition的记录
	public List<ShoppingCartCommodity> findListShoppingCartCommodity(@Param("userId") Integer userId,@Param("condition") String condition) throws Exception;
}
