/**
 * 描述: 
 * CoreDao.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.dao;

import java.util.List;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDao
 */
public interface CoreDao<T> {
	
	/**
	 Jdbc CoreDao
	 
	 
	 */
	
	/**
	 * 
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insert(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insert(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insertOrUpdate(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int insertOrUpdate(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 单一删除
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param id
	 * @return
	 */
	public int delete(final String sql, final Object id);
	
	/**
	 * 
	 * 描述: 批量删除
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param ids
	 * @return
	 */
	public int delete(final String sql, final Object[] ids);
	
	/**
	 * 
	 * 描述: 批量删除
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param ids
	 * @return
	 */
	public int delete(final String sql, final List<Object> ids);
	
	/**
	 * 
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 获取单行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param id
	 * @return
	 */
	public Object[] getSingleRow(final String sql, final Object id);
	
	/**
	 * 
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> search(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 搜索多行记录
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<Object[]> search(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long count(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 统计记录数
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long count(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 执行语句
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean execute(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 执行语句
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean execute(final String sql, final List<Object> params);
}
