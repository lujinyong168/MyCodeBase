package com.imf.multipleThread.thread3.dao;

import java.sql.Connection;

import com.imf.multipleThread.thread3.database.DataAccess;

public class RequestDao extends DataAccess{
	public RequestDao(Connection conn) {
		super(conn);
	}
}
