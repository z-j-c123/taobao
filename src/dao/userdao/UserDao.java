package dao.userdao;

import java.util.List;

import po.User;
public interface UserDao {
	//查询用户
	public List<User> findUser(User user) throws Exception;
	//修改用户
	public Integer updateUser(User user) throws Exception;
	//添加用户
	public Integer addUser(User user) throws Exception;
	
}
