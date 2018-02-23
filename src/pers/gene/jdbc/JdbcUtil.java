package pers.gene.jdbc;
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Properties;

import pers.gene.main.SignupSignin;  

/**
 * <p>Title: JdbcUtil<��p>
 * <p>Description: JDBC������<��p>
 * @author gene1994
 *
 * 2017��11��15��
 */

  
public class JdbcUtil {  
	public Properties prop = SignupSignin.prop;
	
    public String ip = prop.getProperty("server.ip");
    
    public String port = prop.getProperty("server.port");
    
    public String dbName = prop.getProperty("server.dbname");
    
    public String username = prop.getProperty("jdbc.username");

    public String password = prop.getProperty("jdbc.password");
    
    public static String DRIVER = "com.mysql.jdbc.Driver";

    public Connection connection;
  
    public PreparedStatement pstmt;

    public ResultSet resultSet;
    
    public JdbcUtil() {  
  
    }  

    public Connection getConnection() {
        try {  
            Class.forName(DRIVER).newInstance();
            String url = "jdbc:mysql://" + ip +":" + port + "/" + dbName + "?useUnicode=true&autoReconnect=true&characterEncoding=gbk";
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {  
            throw new RuntimeException("get connection error!", e);  
        }
        return connection;
    }

    /**
     * ִ�и��²���
     
     * @param sql
     *            sql���
     * @param params
     *            ִ�в���
     * @return ִ�н��
     * @throws SQLException
     */
    public boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {
        boolean flag = false;
        int result = -1;// ��ʾ���û�ִ�����ɾ�����޸ĵ�ʱ����Ӱ�����ݿ������
        pstmt = connection.prepareStatement(sql);
        int index = 1;
        // ���sql����е�ռλ��
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
     * ִ�в�ѯ���� 
     *  
     * @param sql 
     *            sql��� 
     * @param params 
     *            ִ�в��� 
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
        int cols_len = metaData.getColumnCount();  //��ȡ����
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
     * �ͷ���Դ 
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