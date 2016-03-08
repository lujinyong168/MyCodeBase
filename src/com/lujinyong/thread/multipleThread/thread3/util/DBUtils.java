package com.lujinyong.thread.multipleThread.thread3.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @Description:不是用连接池
 */
public class DBUtils {
	// 数据库用户名
	private static final String USERNAME = "root";
	// 数据库密码
	private static final String PASSWORD = "root";
	// 驱动信息
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	// 数据库地址
	private static final String URL = "jdbc:mysql://localhost:3306/projectms?characterEncoding=utf-8";
	private static Connection conn = null;

	/**
	 * 获得数据库的连接
	 * 
	 * @return
	 */
	public static synchronized Connection getConnection() {
		try {
			if(conn==null){
				Class.forName(DRIVER);
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 释放数据库连接
	 */
	public void close() {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
