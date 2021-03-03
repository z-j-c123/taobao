package service.userservice;

import java.util.List;
import po.Commodity;
import po.Journal;
import po.Result;
import po.User;

public interface UserService {
	//用户登录
	public Result login(User user) throws Exception;
	//添加用户
	public Result addUser(User user) throws Exception;
	//通过手机号判断是否存在这样的用户，user中只设置手机号码
	public List<User>findUser(User user) throws Exception;
	//修改用户				//旧User			//新User
	public Result updateUser(User usedUser,User newUser) throws Exception;
	public Result updateUser(User user) throws Exception;
}
