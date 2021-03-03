package dao.addressdao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import po.Address;

public interface AddressDao {

	//添加收货地址
	public Integer addAddress(Address address) throws Exception;
	
	//删除收货地址
	public Integer deleteAddress(@Param("id") Integer id) throws Exception;
	
	//修改收货地址
	public Integer updateAddress(Address address) throws Exception;
	
	//查询收货地址
	public List<Address> findAddress(Address address) throws Exception;
}
