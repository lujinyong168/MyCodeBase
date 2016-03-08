package com.lujinyong.thread.multipleThread.thread3.test.entry;

import java.sql.Timestamp;

public class Request {
	private Long ruleRequestID;
	private Timestamp fromDate;
	private Timestamp endDate;
	private Short status;
	private Integer requestHotelID;
	private Timestamp requestDate;
	private Timestamp dataChange_CreateTime;
	private Timestamp dataChange_LastTime;
	public Long getRuleRequestID() {
		return ruleRequestID;
	}
	public void setRuleRequestID(Long ruleRequestID) {
		this.ruleRequestID = ruleRequestID;
	}
	public Timestamp getFromDate() {
		return fromDate;
	}
	public void setFromDate(Timestamp fromDate) {
		this.fromDate = fromDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Integer getRequestHotelID() {
		return requestHotelID;
	}
	public void setRequestHotelID(Integer requestHotelID) {
		this.requestHotelID = requestHotelID;
	}
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
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
}
