package serviceimple.addressserviceimple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.addressdao.AddressDao;
import dao.journaldao.JournalDao;
import po.Address;
import po.Journal;
import po.Result;
import service.addressserivce.AddressService;
import util.NowTime;

@Service
@Transactional
public class AddressServiceImple implements AddressService{

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private JournalDao journalDao;
	//最多允许用户有10个收货地址
	private Integer maxCountAddress = 10;
	
	//添加用户收货地址
	public Result addAddress(Integer userId,Address address) throws Exception {
		Address ar = new Address();
		ar.setUserId(address.getUserId());
		List<Address> listAddress = addressDao.findAddress(ar);
		if(listAddress.size()>=maxCountAddress)
			return Result.fail("添加失败，收货地址最多只能有10个哦，可以删除不必要的收货地址重新添加");
		else
		{
			Integer result = addressDao.addAddress(address);
			//写入日志
			try
			{
				Journal journal = new Journal();
				journal.setUserId(userId);
				journal.setJournalTime(NowTime.getNowTime());
				journal.setOperation("添加收货地址"+address.toString());
				journalDao.addJournal(journal);
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				System.out.println("AddressServiceImple类addAddress方法添加日志异常");
			}
			if(result>0)
				return Result.result(true, "添加收货地址成功", result);
			else
				return Result.fail("添加失败，请稍后再试");
		}
	}

	//删除用户收货地址
	public Result deleteAddress(Integer id) throws Exception {
		if(id!=null&&id>0)
		{
			Integer result = addressDao.deleteAddress(id);
			if(result>0)
				return Result.result(true, "删除成功", result);
			else
				return Result.fail("删除失败，请稍后再试");
		}
		else
			return Result.fail("数据错误");
	}
	
	//修改用户收货地址
	public Result updateAddress(Address address) throws Exception {
		if(address!=null&&address.getId()!=null&&address.getId()>0)
		{
			Integer result = addressDao.updateAddress(address);
			if(result>0)
				return Result.result(true, "修改成功", result);
			else
				return Result.fail("修改失败，请稍后再试");
		}
		else
		{
			return Result.fail("数据错误");
		}
	}

	//查询用户收货地址
	public Result findAddress(Address address) throws Exception {
		if(address!=null)
		{
//			List<Address> listAddress = addressDao.findAddress(address);
//			System.out.println(listAddress.size());
//			for(int i=0;i<listAddress.size();i++)
//			{
//				System.out.println(listAddress.get(i).toString());
//			}
			return Result.result(true, "查询成功",addressDao.findAddress(address));
		}
		else
		{
			return Result.fail("数据错误");
		}
	}
	
	
	
	
}
