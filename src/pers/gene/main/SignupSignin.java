package pers.gene.main;

import java.io.InputStream;
import java.util.Properties;

import pers.gene.jdbc.JdbcUtil;

public class SignupSignin {
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
	 * 注册
	 */
	public static void signUp() {
		
	}
}
