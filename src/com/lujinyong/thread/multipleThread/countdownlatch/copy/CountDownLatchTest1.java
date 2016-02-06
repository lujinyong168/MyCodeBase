package com.lujinyong.thread.multipleThread.countdownlatch.copy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * 
 * @Description:3个工人合作做一个项目的问题，当所有的工人工作做完了，整个项目结束;
 *  CountDownLatch，一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 主要方法:
 *  public CountDownLatch(int count);// 构造方法参数指定了计数的次数;
 *  public void countDown();//当前线程调用此方法，则计数减一;
 *  public void await() throws InterruptedException;//调用此方法会一直阻塞当前线程，直到计时器的值为0
 *  此例子可以用于多线程将数据库中的数据拉取到缓存中,当所有线程完成才算完成.
 * @Author: lujinyong168
 * @Date: 2016年2月6日 上午9:53:36
 */
public class CountDownLatchTest1 {
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	public static void main(String[] args) throws InterruptedException { 
		CountDownLatch latch = new CountDownLatch(3);
		Worker worker1 = new Worker("zhangsan",10,latch);
		worker1.start();
		Worker worker2 = new Worker("lisi",8,latch);
		worker2.start();
		Worker worker3 = new Worker("wangwu",16,latch);
		worker3.start();
		latch.await();//等待所有工人完成工作
		System.out.println("all work done at "+sdf.format(new Date()));
		
	}
	//工作类
	static class Worker extends Thread{
		String name;
		int count;
		CountDownLatch latch ;
		public Worker(String name,int count,CountDownLatch latch){
			this.name = name ;
			this.count = count;
			this.latch = latch;
		}
		public void run(){
			try{
				System.out.println("Worker "+name+" do work begin at "+sdf.format(new Date()));
				doWork();
				System.out.println("Worker "+name+" do work complete at "+sdf.format(new Date()));
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				latch.countDown();//计数器减一
			}
		}
		private void doWork() {
			try {
				for(int i=0;i< count;i++){
					System.out.println(Thread.currentThread().getName()+" 第 "+i+" 次干活!!");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
		
}
