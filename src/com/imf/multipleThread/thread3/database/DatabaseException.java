package com.imf.multipleThread.thread3.database;

/**
 * 数据库异常
 */
@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {
	
	public DatabaseException(Throwable cause) {
		super(cause);
	}

}
