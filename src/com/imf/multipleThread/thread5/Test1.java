package com.imf.multipleThread.thread5;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Description:多线程排序
 * @Author: jy.lu
 * @Date: 2016年3月3日 下午6:26:46
 */
public class Test1 {
	private static final Logger logger = LoggerFactory.getLogger(Test1.class);
	private static List<Integer> globalList = new ArrayList<Integer>();
	public static void main(String[] args) {
		long stime = System.currentTimeMillis();
		try {
			logger.info("启动提交线程");
			List<PrintTest> printList = new ArrayList<PrintTest>();
			List<Thread> threadList = new ArrayList<Thread>();
			for (int i = 0; i < 10; i++) {
				PrintTest p = new PrintTest();
				printList.add(p);
				Thread thread = new Thread(p);
				threadList.add(thread);
				thread.start();
			}
			logger.info("创建模拟数据");
			Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
			for (int i= 0;i<10;i++) {
				for(int j=0;j<10;j++){
					if(!map.containsKey(i+"")){
						map.put(i+"", new ArrayList<Integer>());
					}
					map.get(i+"").add(new Random().nextInt(100));
				}
			}
			logger.info("分发数据");
			for (Map.Entry<String,List<Integer>> item : map.entrySet()) {
//				System.out.println(item.getKey()+"  "+item.getValue());
				globalList = item.getValue();
				Locker.unlock(CONST.WORK_THREAD);
				Locker.lock(CONST.JOB_SEND);
			}
			logger.info("等待所有线程工作完毕");
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
			for(PrintTest item : printList){
				item.close();
			}
			for(Thread item : threadList){
				Locker.unlock(CONST.WORK_THREAD);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			long etime = System.currentTimeMillis();
			System.out.println("took:"+(etime-stime));
			logger.info("程序退出");
			System.exit(0);
		}
		
	}
	private static class PrintTest implements Runnable{
		private List<Integer> sortList = new ArrayList<Integer>();
		private boolean closeable = false;
		public void close() {
			closeable = true;
		}
		
		public void run() {
			while(true){
				sortList.clear();
				Locker.lock(CONST.WORK_THREAD);
				if(closeable){
					logger.info("线程退出");
					break;
				}
				sortList = new ArrayList<Integer>(globalList);
//				Collections.sort(sortList);//升序
				Collections.sort(sortList,Collections.reverseOrder());//降序
				System.out.println("sort:"+sortList);
				Locker.unlock(CONST.JOB_SEND);
			}
			
		}

		
	}
}
