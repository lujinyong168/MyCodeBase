package com.imf.multipleThread.thread4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程间的通信
 * 
 */
public class Test2 {
	public static void main(String[] args) {
		final Business business = new Business();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 2; i++) {
					business.sub1(i);
				}

			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 2; i++) {
					business.sub2(i);
				}

			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 2; i++) {
					business.sub3(i);
				}

			}
		}).start();
	}

	static class Business {
		Lock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();// 控制sub1
		Condition condition2 = lock.newCondition();// 控制sub2
		Condition condition3 = lock.newCondition();// 控制sub3
		private int shouldSub = 1;// shouldSub等于几sub几就执行，否则等待

		public void sub1(int i) {
			lock.lock();
			try {
				while (shouldSub != 1) {// 这里用while而不用if，是为了防止线程被虚假唤醒，用while是多次校验
					try {
						condition1.await();// 不等于1，等待
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				for (int j = 1; j < 10; j++) {
					System.out.println("sub1 thread sequece of " + j + ",loop of " + i);
				}
				shouldSub = 2;// 设置为2
				condition2.signal();// 告诉执行sub2的线程执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void sub2(int i) {
			lock.lock();
			try {
				while (shouldSub != 2) {// 这里用while而不用if，是为了防止线程被虚假唤醒，用while是多次校验
					try {
						condition2.await();// 不等于2，等待
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				for (int j = 1; j < 10; j++) {
					System.out.println("sub2 thread sequece of " + j + ",loop of " + i);
				}
				shouldSub = 3;// 设置为3
				condition3.signal();// 告诉执行sub3的线程执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void sub3(int i) {
			lock.lock();
			try {
				while (shouldSub != 3) {// 不等于3,等待
					condition3.await();
				}
				for (int j = 1; j < 10; j++) {
					System.out.println("sub3 thread sequece of " + j + ",loop of " + i);
				}
				shouldSub = 1;
				condition1.signal();// 告诉执行sub1的线程执行
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

}