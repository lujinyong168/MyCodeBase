package com.imf.multipleThread.thread3.genData;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imf.multipleThread.thread3.database.DatabaseTransaction;
import com.imf.multipleThread.thread3.entry.Request;
import com.imf.multipleThread.thread3.entry.RequestItem;
import com.imf.multipleThread.thread3.service.RequestItemService;
import com.imf.multipleThread.thread3.service.RequestService;
import com.imf.multipleThread.thread3.service.impl.RequestItemServiceImpl;
import com.imf.multipleThread.thread3.service.impl.RequestServiceImpl;
import com.imf.multipleThread.thread3.util.DateUtil;

/**
 * 
 * @Description:生产测试数据
 * @Author: jy.lu
 * @Date: 2016年3月1日 下午4:45:37
 */
public class ResquestData {
	private static final Logger logger = LoggerFactory.getLogger(ResquestData.class);
	public static void main(String[] args) {
		DatabaseTransaction trans = new DatabaseTransaction();
		DatabaseTransaction trans2 = new DatabaseTransaction();
		RequestService requestService = new RequestServiceImpl(trans);
		RequestItemService requestItemService = new RequestItemServiceImpl(trans2);
		List<Request> updateReqList = new ArrayList<Request>();
		logger.info("生成申请单数据");
		for(int i=1;i<=250;i++){
			Request obj = new Request();
			Calendar fromDate = Calendar.getInstance();
			fromDate.set(2016, 03, 01, 00, 00, 00);
			obj.setFromDate(DateUtil.parseStrToTimestamp(DateUtil.getDateTime(fromDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			Calendar endDate = Calendar.getInstance();
			endDate.set(2016, 03, 01, 00, 00, 00);
			endDate.add(Calendar.DAY_OF_MONTH, i);
			obj.setEndDate(DateUtil.parseStrToTimestamp(DateUtil.getDateTime(endDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			obj.setStatus((short)30);
			int requestHotelID = 101+i;
			obj.setRequestHotelID(requestHotelID);
			obj.setRequestDate(DateUtil.parseStrToTimestamp(DateUtil.getDateTime(fromDate, "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			obj.setDataChange_CreateTime(DateUtil.parseStrToTimestamp(DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			obj.setDataChange_LastTime(DateUtil.parseStrToTimestamp(DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
			updateReqList.add(obj);
		}
		logger.info("生成数据量:"+updateReqList.size());
		long sTime = System.currentTimeMillis();
		requestService.insertList(updateReqList);
		trans.commit();
		
		long eTime = System.currentTimeMillis();
		long totalTime = eTime-sTime;
		logger.info("生成的申请单数据写入数据库完成，耗时："+(totalTime/1000+"."+totalTime%1000+"秒"));
		
		String fromDate = "2016-03-01";
		String endDate = "2016-03-03";
		List<Request> dbReqList = requestService.readFromDB_ByModifies(fromDate, endDate);
		logger.info("获取从"+fromDate+"到"+endDate+"Request数据，数据量："+((dbReqList!=null&&dbReqList.size()>0)?dbReqList.size():0));
		trans.close();
		logger.info("生产申请单详细数据");
		List<RequestItem> reqItemList = new ArrayList<RequestItem>();
		if(dbReqList!=null&&dbReqList.size()>0){
			for (Request item : dbReqList) {
				RequestItem reqItem = new RequestItem();
				reqItem.setRuleRequestID(item.getRuleRequestID());
				reqItem.setBalanceType("PP");
				reqItem.setValueData(0.4);
				reqItem.setDataChange_CreateTime(DateUtil.parseStrToTimestamp(DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
				reqItem.setDataChange_LastTime(DateUtil.parseStrToTimestamp(DateUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss"));
				reqItemList.add(reqItem);
			}
		}
		logger.info("生产的申请单详细数据量："+reqItemList.size());
		sTime = System.currentTimeMillis();
		requestItemService.insertList(reqItemList);
		trans2.commit();
		trans2.close();
		eTime = System.currentTimeMillis();
		logger.info("生成的申请单详细数据写入数据库完成，耗时："+(totalTime/1000+"."+totalTime%1000+"秒"));
	}
}
