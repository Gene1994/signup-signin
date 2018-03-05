package pers.gene.dao;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import pers.gene.bean.User;
import pers.gene.main.SignUtil;

public interface UserDao {
	static final Properties prop = SignUtil.prop;

	/**
	 * 数据库 新增用户
	 * @param user
	 * @throws Exception
	 */
	public abstract void addUser(User user) throws Exception;
	
	
	/**
	 * 数据库 删除用户
	 * @param user
	 * @throws Exception
	 */
	public abstract void deleteUser(User user) throws Exception;
	
	
	/**
	 * 数据库 修改用户数据
	 * @param user
	 * @throws Exception
	 */
	public abstract void updateUser(User user) throws Exception;
	
	
	/**
	 * 数据库  根据用户ID查找
	 * @param id
	 * @throws Exception
	 */
	public abstract User findById(int id) throws Exception;
	
	/**
	 * 数据库 根据用户名查找
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public abstract User findByUserName(String userName) throws Exception;
	
	
	/**
	 * 数据库 查找全部用户
	 * @return
	 * @throws Exception
	 */
	public abstract List<User> findAll() throws Exception;
}
