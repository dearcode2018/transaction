/**
 * 描述: 
 * CoreDao.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDao
 */
public interface CoreDao<K extends Serializable, T> {

	
	/**
	 * 
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param t
	 */
	public void insert(final T t);
	
	/**
	 * 
	 * 描述: 删除实体
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param id
	 */
	public void delete(final Class<T> clazz, final K id);
	
	/**
	 * 
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param t
	 */
	public void update(final T t);
	
	/**
	 * 
	 * 描述: 保存或更新
	 * @author qye.zhenge
	 * 
	 * @param t
	 */
	public void saveOrUpdate(final T t);
	
	/**
	 * load()：返回的是代理对象，等到正真用到对象的内容时才发出sql语句；
	 * 如果缓存中没有则返回假代理对象，缓存中有则返回真代理对象。如果不用此对象，就结束；
	 * 如果要用，就到Db中去找，Db中有则返回真的，没有则抛异常（对象未找到异常）。
	 */
	
	/**
	 * get()：直接从数据库加载，不会延迟。
	 * 如果缓存有则返回，如果没有就到Db中找，Db中没有则返回null。
	 */
	
	/**
	 * 
	 * 描述: 根据id获取实体
	 * get 方式
	 * @author qye.zheng
	 * 
	 * @param id
	 * @return
	 */
	public T get(final Class<T> clazz, final K id);
	
	/**
	 * 
	 * 描述: 根据id获取实体
	 * load 方式
	 * @author qye.zheng
	 * 
	 * @param id
	 * @return
	 */
	public T load(final Class<T> clazz, final K id);
	
	 //===以下HQL操作区============================================================
	
	/**
	 * 
	 * 描述: 通过 hql 语句查询对象
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param id 对象id
	 * @return
	 */
	public T get(final String hql, final K id);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	//public T get(final String hql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	//public T get(final String hql, final Map<String, Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	//public T get(final String hql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	public List<T> search(final String hql);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> search(final String hql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> search(final String hql, final Map<String, Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> search(final String hql, final Object[] params);
	
	/**
	 * 
	 * 描述: 分页查询
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> search(final String hql, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 分页查询
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> search(final String hql, final List<Object> params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 分页查询
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> search(final String hql, final Map<String, Object> params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 分页查询
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> search(final String hql, final Object[] params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	public Long count(final String hql); 
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(final String hql, final List<Object> params); 
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(final String hql, final Map<String, Object> params); 
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(final String hql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	public int execute(final String hql);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int execute(final String hql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int execute(final String hql, final Map<String, Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int execute(final String hql, final Object[] params);
	
	
	//===以下QBC操作区============================================================
	/**
	 * QBC 查询 : query by criterion 规则查询 ，基于 hql api方式的查询
	 * 
	 * Expressions 在 hibernate 4.x 中已经过时，查看官方文档，使用其父类 Restrictions 或其他子类
	 * session.createCriteria(String.class).add(Restrictions.eq("1", ""));
	 * 
	 分页搜索、模糊查询、 等值/不等值、
	
	 */
	
	
	
	
	
	
	
	 //===以下SQL操作区============================================================
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @return
	 */
	public List<T> searchBySql(final String sql);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> searchBySql(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> searchBySql(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> searchBySql(final String sql, final Map<String, Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> searchBySql(final String sql, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param sql
	 * @param param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> searchBySql(final String sql, final List<Object> params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> searchBySql(final String sql, final Map<String, Object> params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 
	 * @author  qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<T> searchBySql(final String sql, final Object[] params, final int pageNo, final int pageSize);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @return
	 */
	public int executeBySql(final String sql);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeBySql(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeBySql(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeBySql(final String sql, final Map<String, Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @return
	 */
	public Long countBySql(final String sql);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(final String sql, final List<Object> params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(final String sql, final Object[] params);
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long countBySql(final String sql, final Map<String, Object> params);
	
	
	
	
	
}
