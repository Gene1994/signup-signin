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

import pers.gene.main.SignupSignin;  

/**
 * <p>Title: JdbcUtil<锟斤拷p>
 * <p>Description: JDBC锟斤拷锟斤拷锟斤拷<锟斤拷p>
 * @author gene1994
 *
 * 2017锟斤拷11锟斤拷15锟斤拷
 */

  
public class JdbcUtil {  
	public Properties prop = SignupSignin.prop;
	
    public String ip = prop.getProperty("server.ip");
    
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

    public Connection init() {
        try {  
            Class.forName(DRIVER).newInstance();
//          连接MySql数据库：Connection conn = DriverManager.getConnection("jdbc:mysql://host:port/database", "user", "password");
//          连接Oracle数据库：Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@host:port:database", "user", "password");
//          连接SqlServer数据库：Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://host:port; DatabaseName=database", "user", "password");
            String url = "jdbc:mysql://" + ip +":" + port + "/" + dbName + "?useUnicode=true&autoReconnect=true&characterEncoding=gbk";
            connection = DriverManager.getConnection(url, username, password);
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("show tables;");
            List<String> nameList = new ArrayList<String>();
            while(rs.next()) {
            	nameList.add(rs.getString("ename"));
            }
            if(!nameList.contains("user")) {
            	String sql = "CREATE TABLE user " +
                        "(id INTEGER not NULL, " +
                        " username VARCHAR(255), " + 
                        " password VARCHAR(255), " + 
                        " cellphone CHAR(11), " + 
                        " PRIMARY KEY ( id ))";
            	stmt.executeUpdate(sql);
            }
        } catch (Exception e) {  
            throw new RuntimeException("get connection error!", e);  
        }
        return connection;
    }

    public void createTable() {
    	
    }
    /**
     * 执锟叫革拷锟铰诧拷锟斤拷
     
     * @param sql
     *            sql锟斤拷锟�
     * @param params
     *            执锟叫诧拷锟斤拷
     * @return 执锟叫斤拷锟�
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {
        boolean flag = false;
        int result = -1;// 锟斤拷示锟斤拷锟矫伙拷执锟斤拷锟斤拷锟缴撅拷锟斤拷锟斤拷薷牡锟绞憋拷锟斤拷锟接帮拷锟斤拷锟斤拷菘锟斤拷锟斤拷锟斤拷
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // 锟斤拷锟絪ql锟斤拷锟斤拷械锟秸嘉伙拷锟�
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
     * 执锟叫诧拷询锟斤拷锟斤拷 
     *  
     * @param sql 
     *            sql锟斤拷锟� 
     * @param params 
     *            执锟叫诧拷锟斤拷 
     * @return 
     * @throws SQLException 
     */  
    public List<Map<String, Object>> findResult(String sql, List<?> params)
            throws SQLException {
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
        int cols_len = metaData.getColumnCount();  //锟斤拷取锟斤拷锟斤拷
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
     * 锟酵凤拷锟斤拷源 
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