package pers.gene.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import pers.gene.main.SignUtil;

/**
 * <p>
 * Title: JdbcUtil<锟斤拷p>
 * <p>
 * Description: JDBC锟斤拷锟斤拷锟斤拷<锟斤拷p>
 * 
 * @author gene1994
 *
 *         2017锟斤拷11锟斤拷15锟斤拷
 */

public class JdbcUtil {
	public Properties prop = SignUtil.prop;

	public String ip = prop.getProperty("server.ip");

	String db_url = "jdbc:mysql://" + ip + "/";

	public String port = prop.getProperty("server.port");

	public String dbName = prop.getProperty("server.dbname");

	public String username = prop.getProperty("jdbc.username");

	public String password = prop.getProperty("jdbc.password");

	public static String DRIVER = "com.mysql.jdbc.Driver";

	public Connection connection = null;

	public Statement stmt = null;

	public PreparedStatement pstmt;

	public ResultSet resultSet;

	public JdbcUtil() {
		init();
	}

	/**
	 * 链接数据库、名为user，创建表、名为uesr。 user表中参数为：id、username、password、cellphone
	 * 
	 * @return
	 */
	public Connection init() {
		try {
			Class.forName(DRIVER).newInstance();
			// 连接MySql数据库：Connection conn =
			// DriverManager.getConnection("jdbc:mysql://host:port/database",
			// "user", "password");
			// 连接Oracle数据库：Connection conn =
			// DriverManager.getConnection("jdbc:oracle:thin:@host:port:database",
			// "user", "password");
			// 连接SqlServer数据库：Connection conn =
			// DriverManager.getConnection("jdbc:microsoft:sqlserver://host:port;
			// DatabaseName=database", "user", "password");
			// String url = "jdbc:mysql://" + ip +":" + port +
			// "/jsp_db?useSSL=false," + dbName +
			// "?useUnicode=true&autoReconnect=true&characterEncoding=gbk";
			// String url = "jdbc:mysql://" + ip +":" + port + "/" + dbName
			// +"?useSSL=false";
			// connection = DriverManager.getConnection(url, username,
			// password);
			//

			connection = DriverManager.getConnection(db_url, username, password);
			stmt = connection.createStatement();

			// 创建数据库
			String sql_createDB = "CREATE DATABASE IF NOT EXISTS " + dbName
					+ " DEFAULT CHARSET utf8 COLLATE utf8_general_ci";
			stmt.executeUpdate(sql_createDB);

		} catch (Exception e) {
			// System.out.println("Error when init datdabase");
		}
		return connection;
	}

	

	/**
	 * 执行更新
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {
		boolean flag = false;
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * 查找
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findResult(String sql, List<?> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount(); // 锟斤拷取锟斤拷锟斤拷
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 释放资源
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}