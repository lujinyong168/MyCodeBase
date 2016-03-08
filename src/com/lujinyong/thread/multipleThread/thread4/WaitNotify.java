package com.lujinyong.thread.multipleThread.thread4;

public class WaitNotify {
	public static void main(String[] args) throws InterruptedException {
//		test1();
//		test2();
		
	}


	/**
	 * 
	 * @Description:执行wait, notify时，不获得该对象的锁会报错：java.lang.IllegalMonitorStateException
	 * @Auther: jy.lu
	 * @Date: 2016年3月3日 上午11:09:07
	 */
	private static void test2() throws InterruptedException {
		Object obj = new Object();
		Object lock = new Object();
		synchronized(lock){
			obj.wait();
			obj.notify();
		}
	}

	/**
	 * 
	 * @Description:执行wait, notify时，不获得该对象的锁会报错：java.lang.IllegalMonitorStateException
	 * @Auther: jy.lu
	 * @Date: 2016年3月3日 上午11:07:17
	 */
	private static void test1() throws InterruptedException {
		Object obj = new Object();
		obj.wait();
		obj.notify();
	}
}
