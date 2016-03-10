package com.imf.multipleThread.thread3.entry.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.imf.multipleThread.thread3.entry.RequestItem;

public class RequestItemConverter implements ResultConverter<RequestItem> {

	public RequestItem convert(ResultSet rs) throws SQLException {
		RequestItem bean = new RequestItem();
		bean.setRuleRequestItemID(rs.getLong(1));
		bean.setRuleRequestID(rs.getLong(2));
		bean.setBalanceType(rs.getString(3));
		bean.setValueData(rs.getDouble(4));
		bean.setDataChange_CreateTime(rs.getTimestamp(5));
		bean.setDataChange_LastTime(rs.getTimestamp(6));
		return bean;
	}

}
