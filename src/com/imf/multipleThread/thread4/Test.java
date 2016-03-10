package com.imf.multipleThread.thread4;

/**
 * 
 * @Description:两个线程交替输出1,2,1,2,1,2,1,2...
 * @Author: jy.lu
 * @Date: 2016年3月3日 下午1:51:42
 */
public class Test implements Runnable {
	int num = 1;
	private byte[] lock = new byte[0];

	public Test(int num, byte[] lock) {
		this.num = num;
		this.lock = lock;
	}

	public void run() {
		try {
			while (true) {
				synchronized(lock){
					lock.notifyAll();
					lock.wait();
					System.out.println(num);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		byte[] lock = new byte[0];
		Thread t1 = new Thread(new Test(1, lock));
		Thread t2 = new Thread(new Test(2, lock));
		t1.start();
		t2.start();
	}

}
