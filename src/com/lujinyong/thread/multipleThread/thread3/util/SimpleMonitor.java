package com.lujinyong.thread.multipleThread.thread3.util;

public class SimpleMonitor {
	public class MonitorObject {

	}

	public SimpleMonitor() {
		this(0);
	}

	public SimpleMonitor(int init) {
		notify_count = init;
	}

	private MonitorObject myMonitorObject = new MonitorObject();
	private int notify_count = 0;
	boolean wasSignalled = false;

	public void doWait() {
		synchronized (myMonitorObject) {
			while (!wasSignalled && notify_count <= 0) {
				try {
					myMonitorObject.wait();
				} catch (InterruptedException e) {

				}
			}
			// clear signal and continue running.
			if (notify_count > 0)
				notify_count--;
			wasSignalled = false;
		}
	}

	public void doNotify() {
		synchronized (myMonitorObject) {
			wasSignalled = true;
			myMonitorObject.notify();
		}
	}

	public void doNotify_Record() {
		synchronized (myMonitorObject) {
			wasSignalled = true;
			notify_count++;
			myMonitorObject.notify();
		}
	}
}