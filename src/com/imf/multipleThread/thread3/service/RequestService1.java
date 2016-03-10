package com.imf.multipleThread.thread3.service;

import java.util.List;

import com.imf.multipleThread.thread3.entry.Request;

public interface RequestService1 {
	public int totalCount(String startDate, String endDate);
	public List<Request> queryByPage(String fromDate, String endDate, int pageNo, int pageSize);
}
