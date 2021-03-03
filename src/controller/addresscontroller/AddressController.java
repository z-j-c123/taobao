package controller.addresscontroller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.addressdao.AddressDao;
import po.Address;
import po.Result;
import po.User;
import service.addressserivce.AddressService;

@Controller
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@RequestMapping("/address")
	public String getAddressJsp()
	{
		return "address";
	}
	
	//用户添加收货地址
	@RequestMapping("/addAddress")
	@ResponseBody
	public Result addAddress(@RequestBody Address address,HttpSession session)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return Result.fail("请先登录");
		System.out.println(address.toString());
		if(address!=null&&
				address.getUserName()!=null&&!address.getUserName().equals("")&&
				address.getPhoneNumber()!=null&&!address.getPhoneNumber().equals("")&&
				address.getDetailedAddress()!=null&&!address.getDetailedAddress().equals(""))
		{
			address.setUserId(user.getId());
			try
			{
				return addressService.addAddress(user.getId(), address);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("AddressController类addAddress方法异常");
				return Result.fail("添加失败，请稍后再试");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
			
	}
	
	//删除用户收货地址
	@RequestMapping("/deleteAddress/{id}")
	@ResponseBody
	public Result deleteAddress(@PathVariable("id") Integer id,HttpSession session)
	{
		System.out.println(123456);
		if(session.getAttribute("user")==null)
			return Result.fail("请先登陆");
		if(id!=null&&id>0)
		{
			try
			{
				return addressService.deleteAddress(id);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("AddressController类deleteAddress方法异常");
				return Result.fail("删除失败，请稍后再试");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
	
	//查询收货地址
	@RequestMapping("/findAddress")
	@ResponseBody
	public Result findAddress(HttpSession session)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return Result.fail("请先登陆");
		try
		{
			Address address = new Address();
			address.setUserId(((User)session.getAttribute("user")).getId());
			return addressService.findAddress(address);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			System.out.println("AddressController类findAddress方法异常");
			return Result.fail("查询失败，请稍后再试");
		}
	}
	
	//修改收货地址
	@RequestMapping("/updateAddress")
	@ResponseBody
	public Result updateAddress(@RequestBody Address address,HttpSession session)
	{
		User user = (User)session.getAttribute("user");
		if(user==null)
			return Result.fail("请先登陆");
		if(address!=null&&address.getId()!=null)
		{
			try
			{
				return addressService.updateAddress(address);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("AddressController类updateAddress方法异常");
				return Result.fail("修改失败，请稍后再试");
			}
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
}
