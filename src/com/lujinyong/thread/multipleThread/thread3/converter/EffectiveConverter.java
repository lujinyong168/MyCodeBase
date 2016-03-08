package com.lujinyong.thread.multipleThread.thread3.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lujinyong.java.thread3.test.entry.Effective;

public class EffectiveConverter implements ResultConverter<Effective>{

	public Effective convert(ResultSet rs) throws SQLException {
		Effective bean = new Effective();
		bean.setRuleEffectiveID(rs.getLong(1));
		bean.setEffectiveHotelID(rs.getInt(2));
		bean.setEffectDate(rs.getTimestamp(3));
		bean.setBalanceType(rs.getString(4));
		bean.setValueData(rs.getDouble(5));
		bean.setRuleRequestID(rs.getLong(6));
		bean.setDataChange_CreateTime(rs.getTimestamp(7));
		bean.setDataChange_LastTime(rs.getTimestamp(8));
		return bean;
	}

}
