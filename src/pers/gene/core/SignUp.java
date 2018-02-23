package pers.gene.core;

import pers.gene.bean.User;

public class SignUp {
	User user;
	/**
	 * 添加用户
	 * @param uesrName
	 * @param password
	 * @param rpsw
	 * @param verifyCode
	 * @param repeatable
	 */
	public void addUser(String uesrName, String password, String rpsw, String verifyCode, boolean repeatable) {
		user = new User();
		user.setUserName(uesrName);
		
		if(repeatable) {
			
		}else {
			//数据库中检查是否有重复userName
			checkUserName(uesrName);
		}
	}
	
	/**
	 * 检查数据库中是否存在相同的用户名
	 * @param userName
	 */
	public void checkUserName(String userName) {
		
	}
}
