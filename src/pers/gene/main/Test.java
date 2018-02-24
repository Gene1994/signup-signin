package pers.gene.main;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import pers.gene.jdbc.JdbcUtil;

public class Test {
	public static Properties prop = SignupSignin.prop;
	public static void main(String[] args) throws IOException {
//		// TODO Auto-generated method stub
////		InputStream inStream = JdbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
//		setIp("aaa");
////		prop.load(inStream);
////        System.out.println(prop.getProperty("server.ip")+"1");
//		JdbcUtil a = new JdbcUtil();
//		System.out.println(a.ip);
		List a = new ArrayList();
		a.add("1");
		System.out.println(a.contains("1"));
	}
	public static void setIp(String ip) {
		try {
//        	InputStream inStream = JdbcUtil.class.getResourceAsStream("/resources/jdbc.properties");
//            prop.load(inStream);
            prop.setProperty("server.ip", ip);
		}catch(Exception e) {}
	}

}
