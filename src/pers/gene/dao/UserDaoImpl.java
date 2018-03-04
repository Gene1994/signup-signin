package pers.gene.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pers.gene.bean.User;
import pers.gene.jdbc.JdbcUtil;

public class UserDaoImpl implements UserDao{
	JdbcUtil jdbcUtil = null;
	List<Map<String, Object>> mapList = null;
	User user = null;

	@Override
	public void addUser(User user) throws Exception {
		// TODO Auto-generated method stub
		String id = user.getId();
		String userName = user.getUserName();
		String password = user.getPassword();
		String cellphone = user.getCellphone();
		String sql = "INSERT INTO user (id, username, password, cellphone) VALUES (?,?,?,?)";
		List<String> paramList = new ArrayList<String>();
		paramList.add(id);
		paramList.add(userName);
		paramList.add(password);
		paramList.add(cellphone);
		jdbcUtil = new JdbcUtil();
		jdbcUtil.updateByPreparedStatement(sql, paramList);
		jdbcUtil.releaseConn();
	}

	@Override
	public void deleteUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	


	@Override
	public List<User> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByUserName(String userName) throws Exception {
		jdbcUtil = new JdbcUtil();
		String sql = "select * from user where username = ?";
		ResultSet rs = jdbcUtil.stmt.executeQuery(sql);
		String db_id = rs.getString("id");
		String db_userName = rs.getString("username");
		String db_password = rs.getString("password");
		String db_cellphone = rs.getString("cellphone");
		user = new User(db_id, db_userName, db_password, db_cellphone);
		jdbcUtil.releaseConn();
		return user;
	}
}
