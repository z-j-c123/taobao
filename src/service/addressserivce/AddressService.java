package service.addressserivce;

import java.util.List;

import po.Address;
import po.Result;

public interface AddressService {

	//添加收货地址
	public Result addAddress(Integer userId,Address address) throws Exception;
	
	//删除收货地址
	public Result deleteAddress(Integer id) throws Exception;

	//修改收货地址
	public Result updateAddress(Address address) throws Exception;
	
	//查询收货地址
	public Result findAddress(Address address) throws Exception;
	
}
