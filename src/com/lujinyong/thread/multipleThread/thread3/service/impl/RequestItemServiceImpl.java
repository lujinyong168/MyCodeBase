package com.lujinyong.thread.multipleThread.thread3.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lujinyong.java.thread3.test.dao.RequestItemDao;
import com.lujinyong.java.thread3.test.database.DatabaseTransaction;
import com.lujinyong.java.thread3.test.entry.RequestItem;
import com.lujinyong.java.thread3.test.entry.converter.IntegerConverter;
import com.lujinyong.java.thread3.test.entry.converter.RequestItemConverter;
import com.lujinyong.java.thread3.test.service.RequestItemService;
import com.lujinyong.java.thread3.test.util.DBAccess;

public class RequestItemServiceImpl extends DBAccess<RequestItem>implements RequestItemService{
	private static Logger logger = LoggerFactory.getLogger(RequestItemServiceImpl.class);
	private enum FUNC{QUERYBYPAGE,QUERYMODIFYITEM,QUERYBYINDEX}
	private FUNC currFunc;
	RequestItemDao dao = null;
	//使用连接池
	public RequestItemServiceImpl(DatabaseTransaction trans) {
		super(trans);
		dao = new RequestItemDao(getConnection());
	}

	@Override
	protected String makupMainkey(RequestItem element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int totalCount() {
		String sql = "";
		switch(currFunc){
		case QUERYMODIFYITEM:
			sql = "select count(*) from RequestItem where DataChange_LastTime>=? and DataChange_LastTime<=?";
			return dao.queryForObject(sql,new IntegerConverter(),startDate,endDate);
		case QUERYBYPAGE:
			sql = "select count(*) from RequestItem";
			return dao.queryForObject(sql,new IntegerConverter());
		default:
			break;
		}
		return 0;
	}

	@Override
	protected List<RequestItem> queryBeforUpdate(List<RequestItem> inputList) {
		// 不需要实现
		return null;
	}

	@Override
	protected List<RequestItem> queryByPage(String startDate, String endDate, List<Integer> pageNos) {
		List<RequestItem> retList = new ArrayList<RequestItem>();
		String sql = "";
		switch(currFunc){
			case QUERYBYINDEX:
				sql = "select * from RequestItem where RuleRequestID in (?,?,?,?,?)";
				Integer[] ids = new Integer[5];
				Arrays.fill(ids, 0);
				int count = 0;
				while(count < pageNos.size()){
					ids[count] = pageNos.get(count++);
					if(count % 5 == 0){
						retList.addAll(dao.queryForList(sql,new RequestItemConverter(),ids[0],ids[1],ids[2],ids[3],ids[4]));
					}
				}
				if(ids[0]>0)
					retList.addAll(dao.queryForList(sql,new RequestItemConverter(),ids[0],ids[1],ids[2],ids[3],ids[4]));
				break;
			case QUERYBYPAGE:
				for(Integer ruleRequestID : pageNos){
					sql = "SELECT * FROM RequestItem where RuleRequestID = ?";
					retList.addAll(dao.queryForList(sql,new RequestItemConverter(),ruleRequestID, null));
				}
				break;
			case QUERYMODIFYITEM:
				for(Integer pageNo : pageNos){
					sql = "select * from RequestItem where DataChange_LastTime >= ? and DataChange_LastTime < ? order by ruleRequestItemID desc limit ?,?";
					retList.addAll(dao.queryForList(sql,new RequestItemConverter(),this.startDate, this.endDate, (pageNo-1)*PAGESIZE,PAGESIZE));
				}
				break;
			default :
					break;
		}
		return retList;
	}

	@Override
	public void insertList(List<RequestItem> inputList) {
		if(inputList==null||inputList.isEmpty()){
			return ;
		}
		for (RequestItem item : inputList) {
			String sql = "INSERT INTO RequestItem(ruleRequestID,balanceType,valueData,dataChange_CreateTime,dataChange_LastTime)VALUES(?,?,?,?,?);";
			dao.insert(sql, new IntegerConverter()
					,item.getRuleRequestID()
					,item.getBalanceType()
					,item.getValueData()
					,item.getDataChange_CreateTime()
					,item.getDataChange_LastTime());
		}
		
	}

	@Override
	protected void updateList(List<RequestItem> inputList) {
		// 没有更新操作，不用实现
	}

	@Override
	protected RequestItem updateMainKey(RequestItem oldOne, RequestItem newOne) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RequestItem> readFromDB_ByModies(String fromDate, String endDate) {
		currFunc = FUNC.QUERYMODIFYITEM;
		return this.readFromDB(fromDate, endDate);
	}
	
	public void writeToDB(List<RequestItem> reqItemList){
		super.writeToDB(reqItemList);
	}

}
