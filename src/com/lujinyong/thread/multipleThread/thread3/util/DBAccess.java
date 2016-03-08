package com.lujinyong.thread.multipleThread.thread3.util;

import java.lang.Thread.State;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lujinyong.java.thread3.test.database.DatabaseTransaction;



public abstract class DBAccess<T> {
	private DatabaseTransaction trans;
	
	protected DBAccess(DatabaseTransaction trans) {
		this.trans = trans;
	}
	protected DBAccess() { }
	protected DatabaseTransaction getTransaction() {
		return trans;
	}
	protected Connection getConnection() {
		return trans.getConnection();
	}
	private static final Logger logger = LoggerFactory.getLogger(DBAccess.class);
	protected static int PAGESIZE = 10;				//设置每页的大小，数据量大于10w时，启动多线程获取数据
	protected static int PAGENUMPERTASK = 5;
	protected String startDate,endDate;
	protected boolean queryBatch = false;
	private	List<List<Integer>> queryTasks;
	
	protected abstract String makupMainkey(T element );
	
	protected abstract int totalCount();
	
	protected abstract List<T> queryBeforUpdate(List<T> inputList);
	
	protected abstract List<T> queryByPage(String startDate,String endDate,List<Integer> pageNo);
	
	protected abstract void insertList(List<T> inputList);
	
	protected abstract void updateList(List<T> inputList);
	
	protected abstract T updateMainKey(T oldOne,T newOne);
	
	public List<T> readFromDB(String fromDate,String endDate) {
		// TODO Auto-generated method stub
		SimpleDateFormat sFomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		int totalCount = 0;
		if(fromDate == null){
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(0L);
			fromDate = sFomat.format(date.getTime());
		}
		if(endDate ==  null){
			Calendar date = Calendar.getInstance();
			endDate = sFomat.format(date.getTime());
		}
		this.startDate = fromDate;
		this.endDate = endDate;
		try{
			totalCount = this.totalCount();
		}catch(Exception e){
			logger.warn(e.getMessage());
			return null;
		}
		logger.info("加载表中的数据" + totalCount + "条数据");
		if(totalCount / PAGESIZE > 1){ // magic number
				queryTasks = makeupTask(totalCount, PAGENUMPERTASK);
		}else{
				queryTasks = makeupTask(totalCount);
		}
		return nextData(this.startDate,this.endDate);
	}
	
	public List<T> readFromDB_ByIndex(String fromDate,String endDate,List<Integer> IdList) {
		// TODO Auto-generated method stub
		queryTasks = new ArrayList<List<Integer>>();
		if(IdList.size() > 20){
			int count = 0;
			List<Integer> tmp = new ArrayList<Integer>();
			while(count < IdList.size()){
				tmp.add(IdList.get(count++));
				if(count % 20== 0){
					queryTasks.add(tmp);
					tmp = new ArrayList<Integer>();
				}
			}
			if(tmp.size() > 0)
				queryTasks.add(tmp);
		}else{
			queryTasks.add(IdList);
		}
		this.startDate = fromDate;
		this.endDate = endDate;
		this.queryBatch = true;
		return nextData(this.startDate,this.endDate);
	}
	
	protected Map<String,T> includedData(List<T> inputList ){
		Map<String,T> retMap = new HashMap<String,T>();
		for(T element : inputList){
			retMap.put(makupMainkey(element),element);
		}
		return retMap;
	}
	
	public void writeToDB(List<T> inputList){
		List<T> updateList = new ArrayList<T>();
		List<T> insertList = new ArrayList<T>();
		long currentTime = System.currentTimeMillis();
		List<T> readList = this.queryBeforUpdate(inputList);
		logger.info("查询数据大小:" + inputList.size() + "查询时间:" + (System.currentTimeMillis() - currentTime)/1000 + "秒");
		Map<String,T> itemSet = includedData(readList);
		String tmp = "";
		for(T element : inputList){
			tmp = makupMainkey(element);
			if(itemSet.containsKey(tmp)){
				updateMainKey(itemSet.get(tmp), element);
				updateList.add(element);
			}
			else{
				insertList.add(element);
			}
		}
		try {
			currentTime = System.currentTimeMillis();
			this.updateList(updateList);
			logger.info("更新数据大小:" + updateList.size() + "更新时间:" + (System.currentTimeMillis() - currentTime)/1000 + "秒");
			currentTime = System.currentTimeMillis();
			this.insertList(insertList);
			logger.info("插入数据大小:" + insertList.size() + "插入时间:" + (System.currentTimeMillis() - currentTime)/1000 + "秒");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage());
		}
		itemSet.clear();
	}
	
	public List<T> nextData(){
		return nextData(this.startDate,this.endDate);
	}
	
	private List<T> nextData(String fromDate,String endDate) {
		if(queryTasks.isEmpty())
			return  new ArrayList<T>();
		List<List<Integer>> workTasks = null;
		if(queryTasks.size() > 10){
			workTasks = new ArrayList<List<Integer>>(queryTasks.subList(0, 10));
			queryTasks.removeAll(workTasks);
		}else{
			workTasks = new ArrayList<List<Integer>>(queryTasks);
			queryTasks.clear();
		}
		return work(workTasks,fromDate,endDate,queryBatch);
	}
	
	private List<T> work(List<List<Integer>> workTask,String fromDate,String endDate,boolean batch){
		List<T> retList = new ArrayList<T>();
		try{
			List<QueryThread<T>> taskList = new ArrayList<QueryThread<T>>();
			List<Thread> threadList = new ArrayList<Thread>();
			for(List<Integer> task : workTask){
				QueryThread<T> tmp = new QueryThread<T>(task, fromDate,endDate,this,batch);
				taskList.add(tmp);
				Thread thread = new Thread(tmp);
				threadList.add(thread);
				thread.start();
			}
			//等待工作线程退出
			while (!threadList.isEmpty()) {
				for (int i = 0; i < threadList.size(); i++) {
					if (threadList.get(i).getState() == State.TERMINATED) {
						threadList.remove(i);
						break;
					}
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
				}
			}
			//将所有结果组合
			for(QueryThread<T> element : taskList){
				retList.addAll(element.getCacheList());
				element.clean();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.warn(e.getMessage());
			retList = null;
		}
		return retList;
	}
	
	private List<List<Integer>> makeupTask(int totalCount){
		return makeupTask(totalCount, -1);
	}
	
	private List<List<Integer>> makeupTask(int totalCount, int countPerTask){
		List<List<Integer>> queryTasks = new ArrayList<List<Integer>>();
		int totalPages = (totalCount / PAGESIZE ) + ((totalCount % PAGESIZE )== 0 ? 0 : 1);
		int pageNumber = 0;
		if(countPerTask  < 0){
			countPerTask = totalPages;
		}
		List<Integer> task = new ArrayList<Integer>();
		while(pageNumber < totalPages){
			task.add(++pageNumber);
			if(pageNumber % countPerTask == 0){
				queryTasks.add(task);
				task = new ArrayList<Integer>();
			}
		}
		if(task.size() > 0)
			queryTasks.add(task);
		return queryTasks;
	}
	
	
	private static class QueryThread<T> implements Runnable{
		private List<T> cacheList;
		private List<Integer> queryList;
		private String startDate ;
		private String endDate;
		private DBAccess proxy;
		private boolean batch;
		
		public QueryThread(List<Integer> inputList,String fromDate,String endDate,DBAccess proxy,boolean batch) {
			// TODO Auto-generated constructor stub
			SimpleDateFormat sFomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			cacheList = new ArrayList<T>();
			queryList = new ArrayList<Integer>(inputList);
			this.proxy = proxy;
			this.batch = batch;
			if(fromDate == null){
				Calendar date = Calendar.getInstance();
				date.setTimeInMillis(0L);
				fromDate = sFomat.format(date.getTime());
			}
			if(endDate ==  null){
				Calendar date = Calendar.getInstance();
				endDate = sFomat.format(date.getTime());
			}
			this.startDate = fromDate;
			this.endDate = endDate;
		}
		
		public void clean(){
			cacheList.clear();
			queryList.clear();
		}
		
		public List<T> getCacheList(){
			return cacheList;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			List<T> tmp = null;
			if(queryList == null)
				return;
			if(batch){
				cacheList.addAll((tmp  = proxy.queryByPage(startDate, endDate, queryList)) == null ? new ArrayList<T>() : tmp );
			}else{
				for(Integer queryNo : queryList){
					cacheList.addAll((tmp  =proxy.queryByPage(startDate, endDate, Arrays.asList(queryNo))) == null ? new ArrayList<T>() : tmp);
				}
			}
		}
	}
}
