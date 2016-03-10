package com.imf.multipleThread.thread3.service;

import java.util.List;

import com.imf.multipleThread.thread3.entry.Request;


public interface RequestService {
	public List<Request> readFromDB_ByModifies(String fromDate, String endDate);

	public void writeToDB(List<Request> updateReqList);
	
	public void insertList(List<Request> updateReqList);

}
