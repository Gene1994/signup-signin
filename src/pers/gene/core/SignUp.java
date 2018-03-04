package pers.gene.core;

import pers.gene.bean.User;
import pers.gene.dao.UserDaoImpl;

public class SignUp {
	User user;
	UserDaoImpl udi = new UserDaoImpl();
	private String rpsw;//校验密码
	private String verifyCode;
	
	/**
	 * 添加用户
	 * @param id
	 * @param userName
	 * @param password
	 * @param cellphone
	 * @param rpsw
	 * @param verifyCode
	 */
	public void addUser(String id, String userName, String password, String cellphone, String rpsw, String verifyCode) {
		//数据库中检查是否有重复userName
		try {
			if(checkUserName(userName) && checkPassword(rpsw) && checkVertifyCode(verifyCode)) {
				user = new User(id, userName, password, cellphone);
				udi.addUser(user);
			}else {
				//弹出用户名重复提示
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 检查数据库中是否存在相同的用户名
	 * @param userName
	 * @throws Exception 
	 */
	public boolean checkUserName(String userName) throws Exception {
		if(udi.findByUserName(userName) != null) return true;
		return false;
	}
	
	/**
	 * 验证两次输入的密码是否相同
	 * @param password
	 * @return
	 */
	public boolean checkPassword(String password) {
		return false;
	}
	
	
	public boolean checkVertifyCode(String vertifyCode) {
		return false;
	}
}
