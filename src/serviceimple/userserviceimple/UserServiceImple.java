package serviceimple.userserviceimple;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dao.journaldao.JournalDao;
import dao.userdao.UserDao;
import po.Commodity;
import po.Journal;
import po.Result;
import po.User;
import service.userservice.UserService;
import util.NowTime;
@Service
@Transactional
public class UserServiceImple implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private JournalDao journalDao;
	//用户登录
	public Result login(User user) throws Exception
	{
//		验证用户账号密码
		User tempUser=new User();
		tempUser.setPhoneNumber(user.getPhoneNumber());
		List<User> listUser = userDao.findUser(tempUser);
		if(listUser == null || listUser.size() < 1)
		{
			return Result.fail("用户不存在");
		}
		else
		{
			tempUser = listUser.get(0);
			if(!user.getPassWord().equals(tempUser.getPassWord()))
			{
				return Result.fail("账号密码错误");
			}
//			修改最近登录日期
			User updaterecentlyLogin = new User();
			updaterecentlyLogin.setId(tempUser.getId());
			String time = NowTime.getNowTime();
			updaterecentlyLogin.setRecentlyLogin(time);
			tempUser.setRecentlyLogin(time);
			userDao.updateUser(updaterecentlyLogin);
//			添加用户登录日志
			try
			{
				Journal journal=new Journal();
				journal.setUserId(tempUser.getId());
				journal.setJournalTime(time);
				journal.setOperation("登录");
				journalDao.addJournal(journal);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return Result.result(true, "登录成功", tempUser);
		}
	}
	
	public Result addUser(User user) throws Exception {
		//查询手机号码是否重复
		User userTest = new User();
		userTest.setPhoneNumber(user.getPhoneNumber());
		List<User> listUser = userDao.findUser(userTest);
		//已存在该用户
		if(listUser!=null&&listUser.size()>0)
			return Result.fail("该手机号码已存在，请更换手机号码注册");
		//插入数据
		userDao.addUser(user);
		//写入日志
		try
		{
			journalDao.addJournal(new Journal(null,user.getId(),NowTime.getNowTime(),"注册"));
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return Result.result(true, "注册成功", user);
	}

	//通过手机号判断是否存在这样的用户，user中只设置手机号码
	public List<User> findUser(User user) throws Exception{
		List<User> listUser = null;
		try
		{
			listUser = userDao.findUser(user);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		if(listUser!=null&&listUser.size()>0)
			return listUser;
		else
			return null;
	}

	//修改用户
	//这个方法不可以修改customerType，storeName,state,createTime,recentlyLogin
	//用户也不需要修改这几个字段
	public Result updateUser(User usedUser,User newUser) throws Exception
	{
		if(newUser!=null&&newUser.getId()!=null&&newUser.getId()>0)
		{
			newUser.setCustomerType(null);
			newUser.setStoreName(null);
			newUser.setState(null);
			newUser.setCreateTime(null);
			newUser.setRecentlyLogin(null);
			Integer result = userDao.updateUser(newUser);
			//如果修改成功，返回修改后的那条记录
			if(result>0)
			{
				User u2 = new User();
				u2.setId(newUser.getId());
				List<User> listUser = userDao.findUser(u2);
				if(result>0&&listUser.size()>0)
				{
					//添加日志
					String str = "";
					if(!usedUser.getPassWord().equals(newUser.getPassWord())&&newUser.getPassWord()!=null)
						str += "将原密码"+usedUser.getPassWord()+"修改为"+newUser.getPassWord()+" ";
					if(!usedUser.getUserName().equals(newUser.getUserName())&&newUser.getUserName()!=null)
						str += "将原姓名"+usedUser.getUserName()+"修改为"+newUser.getUserName()+" ";
					if(!usedUser.getSex().equals(newUser.getSex())&&newUser.getSex()!=null)
						str += "将原性别"+usedUser.getSex()+"修改为"+newUser.getSex();
					Journal journal = new Journal();
					journal.setUserId(usedUser.getId());
					journal.setJournalTime(NowTime.getNowTime());
					journal.setOperation(str);
					try
					{
						journalDao.addJournal(journal);
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
						System.out.println("UserServiceImple类updateUser方法添加日志失败");
					}
					return Result.result(true, "修改成功", listUser.get(0));
				}
				else
					//根据id修改成功之后再根据id查不到这条记录（相同的id）
					//抛出异常，回滚事务
					throw new Exception();
			}
			else
			{
				return Result.fail("修改失败");
			}
		}
		else
		{
			return Result.fail("修改失败，请稍后再试");
		}
	}
	// 修改用户
	public Result updateUser(User user) throws Exception {
		userDao.updateUser(user);
		return Result.success();
	}
}
