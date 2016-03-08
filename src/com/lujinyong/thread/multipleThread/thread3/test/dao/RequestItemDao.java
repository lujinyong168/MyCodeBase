package com.lujinyong.thread.multipleThread.thread3.test.dao;

import java.sql.Connection;

import com.lujinyong.java.thread3.test.database.DataAccess;

public class RequestItemDao extends DataAccess{
	public RequestItemDao(Connection conn) {
		super(conn);
	}

}
