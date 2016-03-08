package com.lujinyong.thread.multipleThread.thread3.test.service;

import java.util.List;

import com.lujinyong.java.thread3.test.entry.Request;


public interface RequestService {
	public List<Request> readFromDB_ByModifies(String fromDate, String endDate);

	public void writeToDB(List<Request> updateReqList);
	
	public void insertList(List<Request> updateReqList);

}
