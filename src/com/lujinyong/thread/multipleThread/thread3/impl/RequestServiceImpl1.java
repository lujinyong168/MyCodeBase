package com.lujinyong.thread.multipleThread.thread3.impl;

import java.util.List;

import com.lujinyong.java.thread3.test.dao.RequestDao1;
import com.lujinyong.java.thread3.test.entry.Request;
import com.lujinyong.java.thread3.test.entry.converter.IntegerConverter;
import com.lujinyong.java.thread3.test.entry.converter.RequestConverter;
import com.lujinyong.java.thread3.test.service.RequestService1;

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
