package com.lujinyong.thread.multipleThread.thread3.test.database;

/**
 * 数据库存取异常
 * @author David Day
 */
@SuppressWarnings("serial")
public class DataAccessException extends RuntimeException {
	
	public DataAccessException(Throwable cause) {
		super(cause);
	}

}
