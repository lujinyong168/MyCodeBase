package com.lujinyong.thread.multipleThread.thread3.impl;

import java.util.List;

import com.lujinyong.java.thread3.test.dao.EffectiveDao;
import com.lujinyong.java.thread3.test.database.DatabaseTransaction;
import com.lujinyong.java.thread3.test.entry.Effective;
import com.lujinyong.java.thread3.test.service.EffectiveService;
import com.lujinyong.java.thread3.test.util.DBAccess;

public class EffectiveServiceImpl extends DBAccess<Effective>implements EffectiveService {
	EffectiveDao dao = null;
	public EffectiveServiceImpl(){}
	public EffectiveServiceImpl(DatabaseTransaction trans){
		super(trans);
	}
	@Override
	protected String makupMainkey(Effective element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int totalCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<Effective> queryBeforUpdate(List<Effective> inputList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Effective> queryByPage(String startDate, String endDate, List<Integer> pageNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void insertList(List<Effective> inputList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void updateList(List<Effective> inputList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Effective updateMainKey(Effective oldOne, Effective newOne) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void writeToDB(List<Effective> writeList){
		super.writeToDB(writeList);
	}

}
