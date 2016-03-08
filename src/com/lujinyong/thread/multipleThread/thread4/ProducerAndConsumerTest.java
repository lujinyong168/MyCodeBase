package com.lujinyong.thread.multipleThread.thread4;

/**
 * 
 * @Description:用生产者和消费者测试wait()和notifyAll()
 * @Author: jy.lu
 * @Date: 2016年3月3日 上午11:13:07
 */
public class ProducerAndConsumerTest {
	public static void main(String[] args) {
		QueueBuffer q = new QueueBuffer();
		new Producer(q);
		new Consumer(q);
		System.out.println("Press Control-C to stop.");
	}
}

class QueueBuffer {
	int n = -1;
	boolean valueSet = false;

	synchronized int get() {
		if (!valueSet) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("Get:" + n);
		valueSet = false;
		notify();
		return n;
	}

	synchronized void put(int n) {
		if (valueSet) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		this.n = n;
		valueSet = true;
		System.out.println("Put:" + n);
		notify();
	}
}

/**
 * 
 * @Description:生产者
 * @Author: jy.lu
 * @Date: 2016年3月3日 上午11:25:32
 */
class Producer implements Runnable {
	private QueueBuffer q;

	public Producer(QueueBuffer q) {
		this.q = q;
		new Thread(this, "Producer").start();
	}

	public void run() {
		int i = 0;
		while (true) {
			if(i>10)break;
			q.put(i++);
		}

	}

}

/**
 * 
 * @Description:消费者
 * @Author: jy.lu
 * @Date: 2016年3月3日 上午11:22:05
 */
class Consumer implements Runnable {
	private QueueBuffer q;

	public Consumer(QueueBuffer q) {
		this.q = q;
		new Thread(this, "Consumer").start();
	}

	public void run() {
		while (true) {
			q.get();
		}
	}
}
