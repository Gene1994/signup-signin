package pers.gene.main;

import java.io.InputStream;
import java.util.Properties;

import pers.gene.bean.User;
import pers.gene.core.SignUp;
import pers.gene.jdbc.JdbcUtil;

public class SignUtil {
	public static Properties prop = new Properties();
	
	/**
	 * 设置服务器信息
	 * @param ip
	 * @param port
	 * @param dbName
	 */
	public static void setServerProperties(String ip, int port, String dbName) {
        prop.setProperty("server.ip", ip);
        prop.setProperty("server.port", port+"");
        prop.setProperty("server.dbname", dbName);
	}
	
	/**
	 * 设置数据库信息
	 * @param dbUserName(root)
	 * @param dbPassword
	 */
	public static void setDBProperties(String dbUserName, String dbPassword) {
		prop.setProperty("jdbc.username", dbUserName);
		prop.setProperty("jdbc.password", dbPassword);
	}
	
	/**
	 * 连接SignUtil至数据库
	 * @param ip
	 * @param port
	 * @param dbName
	 * @param dbUserName
	 * @param dbPassword
	 */
	public static void connectToDB(String ip, int port, String dbName, String dbUserName, String dbPassword){
		setServerProperties(ip, port, dbName);
		setDBProperties(dbUserName, dbPassword);
	}
	
	/**
	 * 用户注册
	 * @param id
	 * @param userName
	 * @param password
	 * @param cellphone
	 * @param rpsw
	 * @param verifyCode
	 */
	public static void signUp(String id, String userName, String password, String cellphone, String rpsw, String verifyCode) {
		new SignUp(new User(id, userName, password, cellphone)).addUser(id, userName, password, cellphone, rpsw, verifyCode);
	}
}
