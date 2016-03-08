package com.lujinyong.thread.multipleThread.thread3.service;

import java.util.List;

import com.lujinyong.java.thread3.test.entry.Request;

public interface RequestService1 {
	public int totalCount(String startDate, String endDate);
	public List<Request> queryByPage(String fromDate, String endDate, int pageNo, int pageSize);
}
