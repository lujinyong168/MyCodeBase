package com.imf.multipleThread.thread3.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.imf.multipleThread.thread3.dao.RequestDao1;
import com.imf.multipleThread.thread3.entry.Request;
import com.imf.multipleThread.thread3.entry.converter.IntegerConverter;
import com.imf.multipleThread.thread3.entry.converter.RequestConverter;
import com.imf.multipleThread.thread3.service.RequestService1;

public class RequestServiceImpl1 implements RequestService1{
	RequestDao1 dao = null;
	public RequestServiceImpl1(){
		dao = new RequestDao1();
	}

	public int totalCount(String startDate, String endDate) {
		String sql = "select count(*) from Request where DataChange_LastTime>=? and DataChange_LastTime<=?";
		return dao.queryForObject(sql,new IntegerConverter(),startDate,endDate);
	}

	public List<Request> queryByPage(String startDate, String endDate, int pageNo,int pageSize) {
		String sql = "select * from Request where DataChange_LastTime>=? and DataChange_LastTime<=? ORDER BY ruleRequestID DESC limit ?,?";
		return dao.queryForList(sql,new RequestConverter(),startDate,endDate, (pageNo-1)*pageSize,pageSize);
	}
}
