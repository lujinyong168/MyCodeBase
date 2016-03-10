package com.imf.multipleThread.thread3.entry.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 整型映射工具
 */
public class StringConverter implements ResultConverter<String> {

	public String convert(ResultSet rs) throws SQLException {
		return rs.getString(1);
	}

}
