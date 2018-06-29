/**
 * 描述: 
 * CoreDaoImpl.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hua.constant.JdbcConstant;
import com.hua.dao.CoreDao;
import com.hua.log.BaseLog;
import com.hua.util.JdbcUtil;
import com.hua.util.SqlUtil;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDaoImpl
 */
public class CoreDaoImpl<T> extends BaseLog implements CoreDao<T> {

	/**
	 说明 : Jdbc Dao 事务是放在dao层一个方法中，
	 一个方法执行可能会回滚或提交当前事务
	 
	 */
	
	 /**
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insert(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	 /**
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insert(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	 /**
	 * 描述: 新增/更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insertOrUpdate(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	 /**
	 * 描述: 新增/更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int insertOrUpdate(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	 /**
	 * 描述: 单一删除
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public int delete(final String sql, final Object id)
	{
		final Object[] ids = new Object[] {id};
		
		return executeUpdate(sql, ids, null);
	}

	 /**
	 * 描述: 批量删除实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param ids
	 * @return
	 */
	@Override
	public int delete(final String sql, final Object[] ids)
	{
		return executeUpdate(sql, ids, null);
	}

	 /**
	 * 描述: 批量删除
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param ids
	 * @return
	 */
	@Override
	public int delete(final String sql, final List<Object> ids)
	{
		// 装化为List<Object>
		final List<Object> idList = new ArrayList<Object>(ids);
		
		return executeUpdate(sql, null, idList);
	}

	 /**
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int update(final String sql, final Object[] params)
	{
		return executeUpdate(sql, params, null);
	}

	 /**
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int update(final String sql, final List<Object> params)
	{
		return executeUpdate(sql, null, params);
	}

	 /**
	 * 描述: 获取单行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param id
	 * @return
	 */
	@Override
	public Object[] getSingleRow(final String sql, final Object id)
	{
		final Connection conn = JdbcUtil.getConnection();
		Object[] result = null;
		final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
		// 值注入
		SqlUtil.setValue(ps, new Object[] {id});
		try
		{
			// 结果集
			final ResultSet rs = ps.executeQuery();
			// 结果集元数据
			final ResultSetMetaData md = rs.getMetaData();
			// 总列数
			final int totalColumn = md.getColumnCount();
			log.info("getSingleRow =====> totalColumn = " + totalColumn);
			// 单行结果对象
			result = new Object[totalColumn];
			// 游标移动
			if (rs.next())
			{
				log.info("getSingleRow =====> next...");
				// 单行处理
				for (int i = 0; i < totalColumn; i++) 
				{
					/*
					 注意
					 PreparedStatement 设置值 和 ResultSet获取值，下标都是从1开始.
					 因此，下标和返回的对象数组不能相同.
					 此处 统一使用getObject() 返回的是没有经过处理的，直接对应
					 数据库的数据类型的值.
					 */
					result[i] = rs.getObject(i + 1);
					// 可以指定返回类型，但是这里是通过的，并没有判断依据.
					//System.out.println(rs.getDate(6));
					//System.out.println(rs.getTime(6));
				}
			}
		} catch (SQLException e)
		{
			log.error("getSingleRow =====> ", e);
		}
		
		return result;
	}

	 /**
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<Object[]> search(final String sql, final Object[] params)
	{
		return search(sql, params, null);
	}

	 /**
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<Object[]> search(final String sql, final List<Object> params)
	{
		return search(sql, null, params);
	}

	 /**
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String sql, final Object[] params)
	{
		return count(sql, params, null);
	}

	 /**
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String sql, List<Object> params)
	{
		return count(sql, null, params);
	}

	 /**
	 * 描述: 执行语句
	 * @author qye.zheng
	 * 	返回true - 第一个结果是一个ResultSet
	 * 返回false -  第一个结果更新影响的行数或没有结果
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public boolean execute(final String sql, final Object[] params)
	{
		return execute(sql, params, null);
	}

	 /**
	 * 描述: 执行语句
	 * @author qye.zheng
	 * 	返回true - 第一个结果是一个ResultSet
	 * 返回false -  第一个结果更新影响的行数或没有结果
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public boolean execute(final String sql, final List<Object> params)
	{
		return execute(sql, null, params);
	}
	
	
	/**
	 * 
	 * 描述: 修改动作(增删改) 统一入口 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private int executeUpdate(final String sql, final Object[] arrayParams, final List<Object> listParams)
	{
		final Connection conn = JdbcUtil.getConnection();
		// 获取声明对象
		final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
		
		// 值注入
		if (null != arrayParams) 
		{
			log.info("executeUpdate =====> 值注入 - 数组参数");
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
		} else if (null != listParams) 
		{
			log.info("executeUpdate =====> 值注入 - 集合参数");
			// 集合参数
			SqlUtil.setValue(ps, listParams);
		} else 
		{
			// 两种参数均为空
		}
		
		// 影响行数
		int affect = 0;
		try {
			affect = ps.executeUpdate();
			
			log.info("executeUpdate =====> 提交事务");
			// 提交事务
			JdbcUtil.commit(conn);
		} catch (SQLException e) {
			log.error("executeUpdate =====> 回滚事务", e);
			
			// 回滚事务
			JdbcUtil.rollback(conn);
		} finally {
			log.info("executeUpdate =====> 关闭 db 资源");
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
		}
		
		return affect;
	}

	/**
	 * 
	 * 描述: 搜索统一入口
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private List<Object[]> search(final String sql, final Object[] arrayParams, final List<Object> listParams)
	{
		final Connection conn = JdbcUtil.getConnection();
		// 获取声明对象
		final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
		// 值注入
		if (null != arrayParams) {
			log.info("search =====> 值注入 - 数组参数");
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
		} else if (null != listParams) {
			log.info("search =====> 值注入 - 集合参数");
			// 集合参数
			SqlUtil.setValue(ps, listParams);
		} else 
		{
			// 两种参数均为空
		}
		// jdbc 结果集
		ResultSet rs = null;
		// 结果集合
		List<Object[]> resultList = null;
		try
		{
			// 结果集
			rs = ps.executeQuery();
			// 结果集元数据
			final ResultSetMetaData md = rs.getMetaData();
			// 总列数
			final int totalColumn = md.getColumnCount();
			final int totalRow = rs.getRow();
			//
			resultList = new ArrayList<Object[]>(totalRow);
			// 单行结果对象
			Object[] singleRow = null;
			// 游标移动 - 循环语句中
			while (rs.next())
			{
				singleRow = new Object[totalColumn];
				// 单行处理
				for (int i = 0; i < totalColumn; i++) 
				{
					/*
					 注意
					 PreparedStatement 设置值 和 ResultSet获取值，下标都是从1开始.
					 因此，下标和返回的对象数组不能相同.
					 此处 统一使用getObject() 返回的是没有经过处理的，直接对应
					 数据库的数据类型的值.
					 */
					singleRow[i] = rs.getObject(i + 1);
				}
				// 放入集合
				resultList.add(singleRow);
			}
		} catch (SQLException e)
		{
			log.error("search =====> ", e);
		} finally {
			log.info("search =====> 关闭 db 资源");
			JdbcUtil.close(rs, ps);
		}
		
		return resultList;
	}
	
	/**
	 * 
	 * 描述: 统计 统一入口
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private Long count(final String sql, final Object[] arrayParams, final List<Object> listParams)
	{
		final Connection conn = JdbcUtil.getConnection();
		// 获取声明对象
		final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
		// 值注入
		if (null != arrayParams) {
			log.info("count =====> 值注入 - 数组参数");
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
		} else if (null != listParams) {
			log.info("count =====> 值注入 - 集合参数");
			// 集合参数
			SqlUtil.setValue(ps, listParams);
		} else
		{
			// 两种参数均为空
		}
		ResultSet rs = null;
		Long result = null;
		try
		{
			// 结果集
			rs = ps.executeQuery();
			// 获取第一个结果集，无需循环游标
			if (rs.next()) {
				// 获取select count 返回值
				result = rs.getLong(JdbcConstant.FIRST_COLUMN_INDEX);
			}
		} catch (SQLException e)
		{
			log.error("search =====> ", e);
		} finally {
			log.info("count =====> 关闭 db 资源");
			JdbcUtil.close(rs, ps);
		}
		
		return result;
	}
	
	
	/**
	 * 
	 * 描述: 执行 统一入口 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param arrayParams
	 * @param listParams
	 * @return
	 */
	private boolean execute(final String sql, final Object[] arrayParams, final List<Object> listParams)
	{
		/**
		 可以执行dml或dql语句，根据返回值来判断执行的语句类型，
		 如果是dml的需要提交事务或在异常的时候回滚事务.
		 */
		final Connection conn = JdbcUtil.getConnection();
		// 获取声明对象
		final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
		// 值注入
		if (null != arrayParams) {
			log.info("execute =====> 值注入 - 数组参数");
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
		} else if (null != listParams) {
			log.info("execute =====> 值注入 - 集合参数");
			// 集合参数
			SqlUtil.setValue(ps, listParams);
		}
		boolean flag = false;
		try
		{
			log.info("execute =====> 执行...");
			/**
			 boolean execute()
			 返回true - 第一个结果是一个ResultSet
			 返回false -  第一个结果更新影响的行数或没有结果
			 */
			flag = ps.execute();
			if (!flag)
			{
				// dml 操作，提交事务
				log.info("execute =====> 提交事务");
				// 提交事务
				JdbcUtil.commit(conn);
			} else 
			{
				log.info("execute =====> 查询操作，没有事务!");
			}
		} catch (SQLException e)
		{
			log.error("execute =====> 回滚事务", e);
			
			/*
			 回滚事务 (在执行过程中出现异常，已经无法判断执行哪类型语句，
			 为了安全起见，统一进行事务回滚)
			 */
			JdbcUtil.rollback(conn);
		} finally {
			log.info("execute =====> 关闭db资源");
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
		}
		
		return flag;
	}
	
}
