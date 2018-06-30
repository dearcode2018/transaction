/**
  * @filename CustomExtractor.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.hua.orm.entity.m2o.Custom;

 /**
 * @type CustomExtractor
 * @description 
 * @author qianye.zheng
 */
public class CustomExtractor implements ResultSetExtractor<Custom>
{

	/**
	 * @description 
	 * @param rs
	 * @return
	 * @throws SQLException
	 * @throws DataAccessException
	 * @author qianye.zheng
	 */
	@Override
	public Custom extractData(ResultSet rs) throws SQLException,
			DataAccessException
	{
		Custom entity = null;
		if (rs.next())
		{
			entity = new Custom();
			// 索引从1开始
			int i = 1;
			entity.setId(String.valueOf(rs.getBigDecimal(i++).intValue()));
			entity.setName(rs.getString(i++));
			entity.setBalance(rs.getDouble(i++));
			entity.setAddress(rs.getString(i++));
			entity.setStatus(rs.getInt(i++));
		}
		
		return entity;
	}

}
