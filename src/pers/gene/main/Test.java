package pers.gene.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pers.gene.jdbc.JdbcUtil;

public class Test {
	public static Properties prop = SignUtil.prop;
	public static void main(String[] args) throws IOException {
		SignUtil.setServerProperties("localhost", 3306, "aaa");
		SignUtil.setDBProperties("root", "password");
		JdbcUtil d = new JdbcUtil();
	}
	public static void setIp(String ip) {
		try {
//        	InputStream inStream = JdbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
//            prop.load(inStream);
            prop.setProperty("server.ip", ip);
		}catch(Exception e) {}
	}

}
