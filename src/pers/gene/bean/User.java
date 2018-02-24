package pers.gene.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String userName;
	private String password;
	private String rpsw;//repeat passwordȷ������
	private String verifyCode;//��֤��
	private boolean repeatable;//�����û����Ƿ�����ظ�
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
	public String getRpsw() {
		return rpsw;
	}
	public void setRpsw(String rpsw) {
		this.rpsw = rpsw;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public boolean isRepeatable() {
		return repeatable;
	}
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
