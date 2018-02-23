package pers.gene.main;

import java.io.InputStream;
import java.util.Properties;

import pers.gene.jdbc.JdbcUtil;

public class SignupSignin {
	public static Properties prop = new Properties();
	
//	public static Properties getProperties() {
//		return prop;
//	}
	
	public static void setIp(String ip) {
        prop.setProperty("server.ip", ip);
	}
	
	public static void setPort(int port) {
		prop.setProperty("server.port", port+"");
	}
	
	public static void setDBName(String dbName) {
		prop.setProperty("server.dbname", dbName);
	}
	
	public static void setJdbcUserName(String userName) {
		prop.setProperty("jdbc.username", userName);
	}
	
	public static void setJdbcPassword(String password) {
		prop.setProperty("jdbc.password", password);
	}
}
