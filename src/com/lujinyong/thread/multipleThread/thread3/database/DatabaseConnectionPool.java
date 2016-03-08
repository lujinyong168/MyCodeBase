package com.lujinyong.thread.multipleThread.thread3.database;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * 数据库连接池
 */
public final class DatabaseConnectionPool {
	private static final Logger LOG = LoggerFactory.getLogger(DatabaseConnectionPool.class);
	private static final String MAX_CONNECTION = "20";
	public static String DRIVER = null;
	public static String URL = null;
	public static String USERNAME = null;
	public static String PASSWORD = null;
	static {
//		DRIVER = Config.DRIVER;
//		URL =  Config.URL;
//		USERNAME =  Config.USERNAME;
//		PASSWORD =  Config.PASSWORD;
		DRIVER = "com.mysql.jdbc.Driver";
		URL =  "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8";
		USERNAME =  "root";
		PASSWORD =  "root";
	}
	
	private static BoneCP pool;
	/**
	 * 开启连接池
	 */
	public static void startup() {
		try {
			Class.forName(DRIVER);
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(URL);
			config.setUsername(USERNAME);
			config.setPassword(PASSWORD);
			config.setMaxConnectionsPerPartition(Integer.parseInt(MAX_CONNECTION));
			pool = new BoneCP(config);
		} catch (Exception e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * 关闭连接池
	 */
	public static void shutdown() {
		pool.shutdown();
	}
	
	/**
	 * @return 数据库连接
	 */
	public static Connection getConnection() {
		try {
			if(pool==null){
				startup();
			}
			return pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(e.getMessage(), e);
			throw new DatabaseException(e);
		}
	}

}
