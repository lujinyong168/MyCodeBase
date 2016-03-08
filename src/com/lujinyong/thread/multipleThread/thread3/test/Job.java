package com.lujinyong.thread.multipleThread.thread3.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.lang.Thread.State;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lujinyong.java.thread3.test.database.DatabaseTransaction;
import com.lujinyong.java.thread3.test.entry.Effective;
import com.lujinyong.java.thread3.test.entry.Request;
import com.lujinyong.java.thread3.test.entry.RequestItem;
import com.lujinyong.java.thread3.test.service.EffectiveService;
import com.lujinyong.java.thread3.test.service.RequestItemService;
import com.lujinyong.java.thread3.test.service.RequestService;
import com.lujinyong.java.thread3.test.service.impl.EffectiveServiceImpl;
import com.lujinyong.java.thread3.test.service.impl.RequestItemServiceImpl;
import com.lujinyong.java.thread3.test.service.impl.RequestServiceImpl;
import com.lujinyong.java.thread3.test.util.CONST;
import com.lujinyong.java.thread3.test.util.DateUtil;
import com.lujinyong.java.thread3.test.util.Locker;


public class Job {
	private static final Logger logger = LoggerFactory.getLogger(Job.class);
	public static List<Request> globalReqList = new ArrayList<Request>();
	public static List<RequestItem> globalReqItemList = new ArrayList<RequestItem>();
	public static SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static final String FILEPATH = Job.class.getResource("").getPath().substring(1)+"\\SyncTime.txt";
	
	public static void main(String[] args) {
		//启动提交线程
		try {
			List<SortAndUpdateThread> timeList = new ArrayList<SortAndUpdateThread>();
			List<Thread> threadList = new ArrayList<Thread>();
			for(int i=0;i<10;i++){
				SortAndUpdateThread tmp = new SortAndUpdateThread();
				timeList.add(tmp);
				Thread thread = new Thread(tmp);
				threadList.add(thread);
				thread.start();
			}
			Calendar fromDate = Calendar.getInstance();
			Calendar endDate  = Calendar.getInstance();
			File file = new File(FILEPATH);
			fromDate.setTimeInMillis(0);
			logger.info("读取上一次的时间戳");
			if(file.exists()){
				byte tmpBytes[] = new byte[255];
				byte b = 0;
				Arrays.fill(tmpBytes,b);
				BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
				int num = fis.read(tmpBytes);
				if(num > 0){
					fromDate.setTime(sFormat.parse(new String(tmpBytes,0,num)));
				}
				fis.close();
			}
			logger.info("程序检查时间范围:" + sFormat.format(fromDate.getTime()) + "\t" + sFormat.format(endDate.getTime()));
			DatabaseTransaction trans = new DatabaseTransaction();
			RequestService requestService = new RequestServiceImpl(trans);
			RequestItemService requestItemService = new RequestItemServiceImpl(trans);
//			List<Request> reqList = requestService.readFromDB_ByModifies(sFormat.format(fromDate.getTime()), sFormat.format(endDate.getTime()));;
//			List<Request> reqList = requestService.readFromDB_ByModifies("2016-03-01 00:00:00", sFormat.format(endDate.getTime()));
//			List<RequestItem> reqItemList = requestItemService.readFromDB_ByModies("2016-03-01 00:00:00", sFormat.format(endDate.getTime()));
			//根据请求号获取请求信息
			Map<Integer,Integer> reqToHotelId = new HashMap<Integer,Integer>();
			Map<Integer,List<Request>> reqToMap = new HashMap<Integer,List<Request>>();
			Map<Integer,List<RequestItem>> reqItemToMap = new HashMap<Integer,List<RequestItem>>();
//			for (Request item : reqList) {
//				int hotelId = item.getRequestHotelID();
//				if(!reqToMap.containsKey(hotelId)){
//					reqToMap.put(hotelId, new ArrayList<Request>());
//				}
//				reqToMap.get(hotelId).add(item);
//				reqToHotelId.put(item.getRuleRequestID().intValue(), hotelId);
//			}
//			for(RequestItem item:reqItemList){
//				int ruleRequestId = item.getRuleRequestID()==null?0:item.getRuleRequestID().intValue();
//				if(!reqToHotelId.containsKey(ruleRequestId)){
//					logger.error("返回的请求项与预期的不一致,ruleRequestId="+ruleRequestId);
//					continue;
//				}
//				Integer hotelId = reqToHotelId.get(ruleRequestId);
//				if(!reqItemToMap.containsKey(hotelId)){
//					reqItemToMap.put(hotelId, new ArrayList<RequestItem>());
//				}
//				reqItemToMap.get(hotelId).add(item);
//			}
			//分发工作数据到工作线程
			logger.info("发放数据");
//			for(Integer hotelID : reqItemToMap.keySet()){
//				globalReqItemList = reqItemToMap.get(hotelID);
//				globalReqList = reqToMap.get(hotelID);
//				logger.info("globalReqItemList.size="+globalReqItemList.size()+"=globalReqList.size="+globalReqList.size());
//				Locker.unlock(CONST.WORK_THREAD);
//				Locker.lock(CONST.JOB_SEND);
//			}
			for(int i=0;i<10;i++){
				logger.info("分发数据。。。");
				Locker.unlock(CONST.WORK_THREAD);
				Locker.lock(CONST.JOB_SEND);
			}
			//持久化最近一次的更新时间到磁盘
			//往前回溯SKIPTIME秒
			endDate.setTimeInMillis(endDate.getTimeInMillis() - 10 * 1000);
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			long length = raf.length();
			byte[] bytes = new byte[(int) length];
			Arrays.fill(bytes, (byte)0);
			raf.seek(0);
			//清空日期数
			raf.write(bytes);
			//写入最新的日期数
			raf.seek(0);
			raf.write(sFormat.format(endDate.getTime()).getBytes());
			raf.close();
			//等待所有线程工作完毕
			int count = 0;
			while(count < threadList.size()){
				try{
					for(Thread item : threadList){
						if(item.getState() != State.WAITING){
							count = 0;
							break;
						}else{
							count++;
						}
					}
					Thread.sleep(800);
				}catch(Exception e){
					
				}
			}
			//退出线程
			for(SortAndUpdateThread item : timeList){
				item.close();
			}
			for(Thread item : threadList){
				Locker.unlock(CONST.WORK_THREAD);
			}
			System.out.println("程序结束检查时间范围:" + sFormat.format(fromDate.getTime()) + "\t" + sFormat.format(endDate.getTime()));
			trans.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
	}
	private static class SortAndUpdateThread implements Runnable{
		DatabaseTransaction trans = new DatabaseTransaction();
		List<Request> updateReqList = new ArrayList<Request>();
		List<RequestItem> updateReqItemList = new ArrayList<RequestItem>();
		RequestService requestService = new RequestServiceImpl(trans);
		RequestItemService requestItemService = new RequestItemServiceImpl(trans);
		EffectiveService effectiveService = new EffectiveServiceImpl();
		private boolean closeable = false;
		public void close(){
			closeable = true;
		}
		public void run() {
			//开始循环获取数据
			while(true){
				updateReqList.clear();
				updateReqItemList.clear();
				Locker.lock(CONST.WORK_THREAD);
				if(closeable){
					System.out.println("线程退出");
					break;
				}
				updateReqList = new ArrayList<Request>(globalReqList);
				updateReqItemList = new ArrayList<RequestItem>(globalReqItemList);
				Locker.unlock(CONST.JOB_SEND);
				Collections.sort(updateReqList,new Comparator<Request>() {
					
					public int compare(Request o1, Request o2) {
						if(o1.getRequestDate().getTime()>o2.getRequestDate().getTime()){
							return -1;
						}else if(o1.getRequestDate().getTime()==o2.getRequestDate().getTime()){
							return 0;
						}else{
							return 0;
						}
					}
				});
				Map<String/*date*/,Effective> inputMap = new HashMap<String, Effective>();
				//代码逻辑
				//根据req的effectdate排序日期
				//将对应的item放入到map中
				//如果已存在 那么不复写
				for(int i = 0;i<updateReqList.size();i++){
					Request item = updateReqList.get(i);
					//获取策略应用的时间范围，优先级别根据effectdate
					List<String> timerange = DateUtil.getTimeRange(item.getFromDate(),item.getEndDate());
					//根据日期写入对应的策略
					for(String date : timerange){
						RequestItem tmpItem = null;
						for(RequestItem element : updateReqItemList){
							if(element.getRuleRequestID().intValue() == item.getRuleRequestID().intValue()){
								tmpItem = element;
							}else{
								continue;
							}
							if(inputMap.containsKey(date+tmpItem.getBalanceType())){
								continue;
							}
							item.setStatus((short)40);
							Effective tmp = new Effective();
							tmp.setBalanceType(tmpItem.getBalanceType());
							tmp.setValueData(tmpItem.getValueData());
							tmp.setEffectiveHotelID(item.getRequestHotelID());
							tmp.setEffectDate(Timestamp.valueOf(date));
							tmp.setRuleRequestID((long)(tmpItem.getRuleRequestID().intValue()));
							inputMap.put(date + tmpItem.getBalanceType(), tmp);
						}
						if(tmpItem == null){
							System.out.println("reqID没有对应的reqItemID");
						}
					}
				}
				List<Effective> writeList = new ArrayList<Effective>(inputMap.values());
//				effectiveService.writeToDB(writeList);
//				requestService.writeToDB(updateReqList);
			}
			trans.close();
		}
		
	}
}
