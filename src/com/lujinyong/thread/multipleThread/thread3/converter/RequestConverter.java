package com.lujinyong.thread.multipleThread.thread3.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lujinyong.java.thread3.test.entry.Request;

public class RequestConverter implements ResultConverter<Request> {

	public Request convert(ResultSet rs) throws SQLException {
		Request bean = new Request();
		bean.setRuleRequestID(rs.getLong(1));
		bean.setFromDate(rs.getTimestamp(2));
		bean.setEndDate(rs.getTimestamp(3));
		bean.setStatus(rs.getShort(4));
		bean.setRequestHotelID(rs.getInt(5));
		bean.setRequestDate(rs.getTimestamp(6));
		bean.setDataChange_CreateTime(rs.getTimestamp(7));
		bean.setDataChange_LastTime(rs.getTimestamp(8));
		return bean;
	}

}
