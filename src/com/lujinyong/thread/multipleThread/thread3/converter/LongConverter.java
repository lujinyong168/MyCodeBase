package com.lujinyong.thread.multipleThread.thread3.converter;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 把结果转换为长整型
 */
public class LongConverter implements ResultConverter<Long> {

	public Long convert(ResultSet rs) throws SQLException {
		return rs.getLong(1);
	}

}
