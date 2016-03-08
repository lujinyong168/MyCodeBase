package com.lujinyong.thread.multipleThread.thread3.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lujinyong.java.thread3.test.dao.RequestDao;
import com.lujinyong.java.thread3.test.database.DatabaseTransaction;
import com.lujinyong.java.thread3.test.entry.Request;
import com.lujinyong.java.thread3.test.entry.converter.IntegerConverter;
import com.lujinyong.java.thread3.test.entry.converter.LongConverter;
import com.lujinyong.java.thread3.test.entry.converter.RequestConverter;
import com.lujinyong.java.thread3.test.service.RequestService;
import com.lujinyong.java.thread3.test.util.DBAccess;

public class RequestServiceImpl extends DBAccess<Request> implements RequestService{
	private enum FUNC{QUERYBYPAGE,QUERYMODIFYDATA}
	private FUNC currFunc;
	RequestDao dao = null;
	public RequestServiceImpl(DatabaseTransaction trans){
		super(trans);
		dao = new RequestDao(getConnection());
	}
	
	@Override
	protected String makupMainkey(Request element) {
		// TODO Auto-generated method stub
		return "same";
	}

	@Override
	protected int totalCount() {
		String sql = "";
		switch (currFunc) {
		case QUERYMODIFYDATA:
			sql = "select count(*) from Request where DataChange_LastTime>=? and DataChange_LastTime<=?";
			return dao.queryForObject(sql,new IntegerConverter(),startDate,endDate);
		case QUERYBYPAGE:
			sql = "select count(*) from Request";
			return dao.queryForObject(sql, new IntegerConverter());
		default:
			break;
		}
		return 0;
	}

	@Override
	protected List<Request> queryBeforUpdate(List<Request> inputList) {
		//
		return Arrays.asList(new Request());
	}

	@Override
	protected List<Request> queryByPage(String startDate, String endDate, List<Integer> pageNos) {
		String sql = "select * from Request where DataChange_LastTime>=? and DataChange_LastTime<=? ORDER BY ruleRequestID DESC limit ?,?";
		List<Request> retList = new ArrayList<Request>();
		switch(currFunc){
		case QUERYMODIFYDATA:
			for(Integer pageNo : pageNos){
				retList.addAll(dao.queryForList(sql,new RequestConverter(),startDate,endDate, (pageNo.intValue()-1)*PAGESIZE,PAGESIZE));
			}
			break;
		default:
				break;
		}
		return retList;
	}

	@Override
	public void insertList(List<Request> inputList) {
		if(inputList==null||inputList.isEmpty()){
			return ;
		}
		for (Request item : inputList) {
			String sql = "INSERT INTO Request(fromDate,endDate,STATUS,requestHotelID,requestDate,dataChange_CreateTime,dataChange_LastTime) VALUES(?,?,?,?,?,?,?);";
			dao.insert(sql,new LongConverter()
					,item.getFromDate()
					,item.getEndDate()
					,item.getStatus()
					,item.getRequestHotelID()
					,item.getRequestDate()
					,item.getDataChange_CreateTime()
					,item.getDataChange_LastTime());
		}
		
	}

	@Override
	protected void updateList(List<Request> inputList) {
		if(inputList == null || inputList.isEmpty()){
			return;
		}
		//只更新状态即可
		for (Request item : inputList) {
			String sql = "update Request set STATUS=? where ruleRequestID=?";
			dao.update(sql,item.getStatus(),item.getRuleRequestID());
		}
		
	}

	@Override
	protected Request updateMainKey(Request oldOne, Request newOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Request> readFromDB_ByModifies(String fromDate, String endDate) {
		currFunc = FUNC.QUERYMODIFYDATA;
		return super.readFromDB(fromDate, endDate);
	}
	public void writeToDB(List<Request> updateReqList){
		super.writeToDB(updateReqList);
	}


}