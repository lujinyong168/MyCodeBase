package com.imf.multipleThread.thread3.entry;

import java.sql.Timestamp;

public class Effective {
	private Long ruleEffectiveID;
	private Integer effectiveHotelID;
	private Timestamp effectDate;
	private String balanceType;
	private Double valueData;
	private Long ruleRequestID;
	private Timestamp dataChange_CreateTime;
	private Timestamp dataChange_LastTime;
	public Long getRuleEffectiveID() {
		return ruleEffectiveID;
	}
	public void setRuleEffectiveID(Long ruleEffectiveID) {
		this.ruleEffectiveID = ruleEffectiveID;
	}
	public Integer getEffectiveHotelID() {
		return effectiveHotelID;
	}
	public void setEffectiveHotelID(Integer effectiveHotelID) {
		this.effectiveHotelID = effectiveHotelID;
	}
	public Timestamp getEffectDate() {
		return effectDate;
	}
	public void setEffectDate(Timestamp effectDate) {
		this.effectDate = effectDate;
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
	public Timestamp getDataChange_CreateTime() {
		return dataChange_CreateTime;
	}
	public void setDataChange_CreateTime(Timestamp dataChange_CreateTime) {
		this.dataChange_CreateTime = dataChange_CreateTime;
	}
	public Timestamp getDataChange_LastTime() {
		return dataChange_LastTime;
	}
	public void setDataChange_LastTime(Timestamp dataChange_LastTime) {
		this.dataChange_LastTime = dataChange_LastTime;
	}
	public Long getRuleRequestID() {
		return ruleRequestID;
	}
	public void setRuleRequestID(Long ruleRequestID) {
		this.ruleRequestID = ruleRequestID;
	}
	
}
