package com.imf.multipleThread.thread3.entry.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 整型映射工具
 */
public class IntegerConverter implements ResultConverter<Integer> {

	public Integer convert(ResultSet rs) throws SQLException {
		return rs.getInt(1);
	}

}
