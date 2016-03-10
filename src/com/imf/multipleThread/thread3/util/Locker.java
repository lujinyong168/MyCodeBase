package com.imf.multipleThread.thread3.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Locker {
	private static Map<Integer, SimpleMonitor> LockList = new ConcurrentHashMap<Integer, SimpleMonitor>();

	public static void unlock(int appId) {
		synchronized (LockList) {
			if (!LockList.containsKey(appId)) {
				LockList.put(appId, new SimpleMonitor());
			}
		}
		LockList.get(appId).doNotify_Record();
	}

	public static void lock(int appId) {
		synchronized (LockList) {
			if (!LockList.containsKey(appId)) {
				LockList.put(appId, new SimpleMonitor());
			}
		}
		LockList.get(appId).doWait();
	}

	public static void mutexLock(int appId) {
		synchronized (LockList) {
			if (!LockList.containsKey(appId)) {
				LockList.put(appId, new SimpleMonitor(1));
			}
		}
		LockList.get(appId).doWait();
	}
}
