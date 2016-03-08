package com.lujinyong.thread.multipleThread.thread3.test.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class TestBoneCP {
	public static void main(String[] args) {
		 BoneCP connectionPool = null;  
	        Connection connection = null;  
	   
	        try {  
	            //加载JDBC驱动  
	            Class.forName("com.mysql.jdbc.Driver");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return;  
	        }  
	          
	        try {  
	            //设置连接池配置信息  
	            BoneCPConfig config = new BoneCPConfig();  
	            //数据库的JDBC URL  
	            config.setJdbcUrl("jdbc:mysql://localhost:3306/test?characterEncoding=utf-8");   
	            //数据库用户名  
	            config.setUsername("root");   
	            //数据库用户密码  
	            config.setPassword("root");  
	            //数据库连接池的最小连接数  
	            config.setMinConnectionsPerPartition(5);  
	            //数据库连接池的最大连接数  
	            config.setMaxConnectionsPerPartition(10);  
	            //  
	            config.setPartitionCount(1);  
	            //设置数据库连接池  
	            connectionPool = new BoneCP(config);  
	            //从数据库连接池获取一个数据库连接  
	            connection = connectionPool.getConnection(); // fetch a connection  
	              
	            if (connection != null){  
	                System.out.println("Connection successful!");  
	                Statement stmt = connection.createStatement();  
	                ResultSet rs = stmt.executeQuery("SELECT * FROM Request");   
	                while(rs.next()){  
	                    System.out.println(rs.getInt(1)+":"+rs.getTimestamp(2)+","+rs.getTimestamp(3));   
	                }  
	            }  
	            //关闭数据库连接池  
	            connectionPool.shutdown();   
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        } finally {  
	            if (connection != null) {  
	                try {  
	                    connection.close();  
	                } catch (SQLException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
	        }  
	}
}
