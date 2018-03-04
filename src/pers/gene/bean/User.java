package pers.gene.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String password;
	private String cellphone;
//	private String rpsw;//repeat password这个不应该是user的属性
//	private String verifyCode; 这个不应该是user的属性
	
	public User(String id, String userName, String password, String cellphone) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.cellphone = cellphone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	
	
}
