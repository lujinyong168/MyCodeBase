package com.lujinyong.thread.multipleThread.thread3.readData;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.formula.functions.T;

import com.lujinyong.java.thread3.test.service.RequestService1;
import com.lujinyong.java.thread3.test.service.impl.RequestServiceImpl1;
/**
 * @Description：
 * @Author: jy.lu
 * @Date: 2016年3月2日 下午5:18:50
 */
public class ReadData1 {
	private static int pageSize = 2;//每页大小
	private static int pageCountPerThread = 5;//每个线程处理的最大页数
	public static void main(String[] args) {
		RequestService1 service = new RequestServiceImpl1();
		String startDate = "2016-03-01";
		String endDate = "2016-03-03";
		int totalCount = service.totalCount(startDate, endDate);
		int totalPages = (totalCount / pageSize ) + ((totalCount % pageSize )== 0 ? 0 : 1);
		List<List<Integer>> queryTasks = new ArrayList<List<Integer>>();
		List<Integer> task = new ArrayList<Integer>();
		int pageNo = 0;
		while(pageNo < totalPages){
			task.add(++pageNo);
			if(pageNo % pageCountPerThread == 0){
				queryTasks.add(task);
				task = new ArrayList<Integer>();
			}
		}
		if(task.size() > 0)
			queryTasks.add(task);
		List<T> retList = work(queryTasks, startDate, endDate, service);
		System.out.println("读取记录数："+retList.size());
		
	}
	//多线程取数
	private static  List<T> work(List<List<Integer>> workTask,String fromDate,String endDate,RequestService1 service){
		List<T> retList = new ArrayList<T>();
		try{
			//启动线程
			List<QueryThread<T>> taskList = new ArrayList<QueryThread<T>>();
			List<Thread> threadList = new ArrayList<Thread>();
			for(List<Integer> task : workTask){
				QueryThread<T> tmp = new QueryThread<T>(task, fromDate,endDate,service);
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
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//将所有结果组合
			for(QueryThread<T> element : taskList){
				retList.addAll(element.getCacheList());
				element.clean();
			}
		} catch (Exception e) {
			e.printStackTrace();
			retList = null;
		}
		return retList;
	}
	/**
	 * 
	 * @Description:取数线程
	 * @Author: jy.lu
	 * @Date: 2016年3月2日 下午4:18:33
	 */
	private static class QueryThread<Request> implements Runnable {
		private List<Request> cacheList;
		private List<Integer> queryList;
		private String fromDate ;
		private String endDate;
		private RequestService1 service;
		
		public QueryThread(List<Integer> taskList,String fromDate, String endDate,RequestService1 service) {
			this.cacheList = new ArrayList<Request>();
			this.queryList = new ArrayList<Integer>(taskList);
			this.fromDate = fromDate;
			this.endDate = endDate;
			this.service = service;
		}
		public void clean(){
			cacheList.clear();
			queryList.clear();
		}
		
		public List<Request> getCacheList(){
			return cacheList;
		}
		@SuppressWarnings("unchecked")
		public void run() {
			if(queryList!=null&&queryList.size()>0){
				for (Integer pageNo : queryList) {
					cacheList.addAll((List<Request>) service.queryByPage(fromDate, endDate,pageNo,pageSize));
				}
			}
			System.out.println(Thread.currentThread().getName()+"=cacheList.size="+cacheList.size());
		}
	}
}
