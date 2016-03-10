package com.imf.multipleThread.thread3.dao;

import java.sql.Connection;

import com.imf.multipleThread.thread3.database.DataAccess;

public class RequestItemDao extends DataAccess{
	public RequestItemDao(Connection conn) {
		super(conn);
	}

}
