package com.lujinyong.thread.multipleThread.thread3.service;

import java.util.List;

import com.lujinyong.java.thread3.test.entry.RequestItem;

public interface RequestItemService {

	List<RequestItem> readFromDB_ByModies(String format, String format2);

	void writeToDB(List<RequestItem> reqItemList);

	void insertList(List<RequestItem> reqItemList);
}
	