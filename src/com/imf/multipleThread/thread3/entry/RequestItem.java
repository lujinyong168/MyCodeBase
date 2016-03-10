package com.imf.multipleThread.thread3.entry;

import java.sql.Timestamp;

public class RequestItem {
	private Long ruleRequestItemID;
	private Long ruleRequestID;
	private String balanceType;
	private Double valueData;
	private Timestamp dataChange_LastTime;
	private Timestamp dataChange_CreateTime;
	public Long getRuleRequestItemID() {
		return ruleRequestItemID;
	}
	public void setRuleRequestItemID(Long ruleRequestItemID) {
		this.ruleRequestItemID = ruleRequestItemID;
	}
	public Long getRuleRequestID() {
		return ruleRequestID;
	}
	public void setRuleRequestID(Long ruleRequestID) {
		this.ruleRequestID = ruleRequestID;
	}
	public String getBalanceType() {
		return balanceType;
	}
	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}
	public Double getValueData() {
		return valueData;
	}
	public void setValueData(Double valueData) {
		this.valueData = valueData;
	}
	public Timestamp getDataChange_LastTime() {
		return dataChange_LastTime;
	}
	public void setDataChange_LastTime(Timestamp dataChange_LastTime) {
		this.dataChange_LastTime = dataChange_LastTime;
	}
	public Timestamp getDataChange_CreateTime() {
		return dataChange_CreateTime;
	}
	public void setDataChange_CreateTime(Timestamp dataChange_CreateTime) {
		this.dataChange_CreateTime = dataChange_CreateTime;
	}
}
