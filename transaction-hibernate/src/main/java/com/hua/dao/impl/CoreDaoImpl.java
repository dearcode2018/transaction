/**
 * 描述: 
 * CoreDaoImpl.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.hua.dao.CoreDao;
import com.hua.log.BaseLog;
import com.hua.util.EmptyUtil;
import com.hua.util.hibernate.HibernateUtil;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDaoImpl
 */
public abstract class CoreDaoImpl<K extends Serializable, E> extends BaseLog implements CoreDao<K, E> {
	
	/* SessionFactory */
	//public static final SessionFactory factory = HibernateO2OUtil.buildByXmlOf01();
	public static final SessionFactory factory = HibernateUtil.buildByXml();
	
	/* Hibernate 会话 */
	public Session session;
	
	/* 增删改 会话中必须开启一个事务，然后再提交事务 */
	public Transaction tx;

	/**
	 * 可以在每个 daoImpl 中的构造方法中
	 * 调用 super.clazz = E.class; T是具体类
	 */
	public Class<E> clazz;
	
	/**
	 
	 Map<String, Object> 参数，可以将SearchBean 转成 Map之后，再传入
	 
	 调用 BeanUtil.bean2Map() 方法将 bean转成 Map
	 
	 将 vo 转成 map 传入也可以
	 
	 Map 方式查询 :   hql 语句中嵌入 =:key 的方式引用map的value
	 例如 : SELECT p FROM Person p WHERE p.oid = :key
	 注意      :key 即冒号是紧贴着key的，规范写法是 =两边留空格，
	 冒号紧贴这key
	 
	 */
	
	 /**
	 * 描述: 新增实体
	 * @author qye.zheng
	 * 
	 * @param entity
	 */
	@Override
	public void insert(final E entity)
	{
		/** ===== begin 异常拦截段 ===== */
		if (null == entity) {
			log.warn("insert =====> 对象为空，不执行持久化操作");
			
			return;
		}
		/** ===== end of 异常拦截段 ===== */

		try {
			// 打开会话
			session = factory.openSession();
			log.info("insert =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			// 持久化操作
			session.save(entity);
			//session.persist(entity);
			
			log.info("insert =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("insert =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
			}
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
	}

	 /**
	 * 描述: 删除实体
	 * @author qye.zheng
	 * 
	 * 	@param clazz
	 * @param id
	 */
	@Override
	public void delete(final Class<E> clazz, final K id)
	{
		/** ===== begin 异常拦截段 ===== */
		if (null == id) {
			log.warn("delete =====> id 为空，不执行删除操作");
			
			return;
		}
		/** ===== end of 异常拦截段 ===== */

		try {
			// 打开会话
			session = factory.openSession();
			log.info("delete =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			// 获取对象 load方式
			@SuppressWarnings("unchecked")
			final E entity = (E) session.load(clazz, id);
			// 删除对象
			session.delete(entity);
			
			log.info("delete =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("delete =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
			}
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
	}

	 /**
	 * 描述: 更新实体
	 * @author qye.zheng
	 * 
	 * @param entity
	 */
	@Override
	public void update(final E entity)
	{
		try {
			// 打开会话
			session = factory.openSession();
			log.info("update =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			// 更新对象
			// 合并 更新
			session.merge(entity);
			// session.update(entity);
			
			log.info("update =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("update =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
			}
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
	}

	/**
	 * 
	 * 描述: 保存或更新
	 * @author qye.zhenge
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(final E entity)
	{
		try {
			// 打开会话
			session = factory.openSession();
			log.info("saveOrUpdate =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			// 保存或更新
			session.saveOrUpdate(entity);
			
			log.info("saveOrUpdate =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("saveOrUpdate =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
			}
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
	}
	
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
	 * 描述: 根据id获取实体
	 * get 方式 
	 * @author qye.zheng
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public E get(final Class<E> clazz, final K id)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取对象
			@SuppressWarnings("unchecked")
			final E entity = (E) session.get(clazz, id);
			
			return entity;
		} catch (Exception e) {
			log.error("get =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 根据id获取实体
	 * load 方式
	 * @author qye.zheng
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public E load(final Class<E> clazz, final K id)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取对象
			@SuppressWarnings("unchecked")
			final E entity = (E) session.load(clazz, id);
			
			return entity;
		} catch (Exception e) {
			log.error("load =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 //===以下HQL操作区============================================================
	
	
	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public E get(final String hql, final K id)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// hql 语句
			// 获取查询对象
			final Query query = session.createQuery(hql);
			// 设置查询条件
			query.setParameter(0, id);
			@SuppressWarnings("unchecked")
			final E entity = (E) query.uniqueResult();
			
			return entity;
		} catch (Exception e) {
			log.error("get =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<E> search(final String hql)
	{
		List<E> list = null;
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			list = (List<E>) query.list();
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		
		return list;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	/**
	 * 		// Hibernate 分页方式
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			第一个结果位置，从这个位置开始取多少条记录
	 */
	
	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final int pageNo,final  int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final List<Object> params, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final Map<String, Object> params, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<E> search(final String hql, final Object[] params, final int pageNo, final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("search =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public Long count(final String hql)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("count =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String hql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) 
			{
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) 
				{
					query.setParameter(i, params.get(i));
				}
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("count =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String hql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;			
		} catch (Exception e) {
			log.error("count =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public Long count(final String hql, final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params))
			{
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) 
				{
					query.setParameter(i, params[i]);
				}
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("count =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @return
	 */
	@Override
	public int execute(final String hql)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("execute =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public int execute(final String hql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("execute =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public int execute(final String hql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("execute =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@Override
	public int execute(final String hql, final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象
			final Query query = session.createQuery(hql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("execute =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}
	
	 //===以下QBC操作区============================================================
	
	/**
	 * QBC 查询 : query by criterion 规则查询
	 * 
	 * Expressions 在 hibernate 4.x 中已经过时，查看官方文档，使用其父类 Restrictions 或其他子类
	 * session.createCriteria(String.class).add(Restrictions.eq("1", ""));
	 */
	
	
	
	
	

	 //===以下SQL操作区============================================================
	
	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @return
	 */
	@Override
	public List<E> searchBySql(final String sql)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
		
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> searchBySql(final String sql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
		
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> searchBySql(final String sql,final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
		
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public List<E> searchBySql(final String sql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.list();
		
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param clazz
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<E> searchBySql(final String sql, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

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
	@Override
	public List<E> searchBySql(final String sql, final List<Object> params, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}
	
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
	@Override
	public List<E> searchBySql(final String sql, final Map<String, Object> params, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}
	
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
	@Override
	public List<E> searchBySql(final String sql, final Object[] params, final int pageNo,
			final int pageSize)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			// Hibernate 分页方式
			@SuppressWarnings("unchecked")
			final List<E> list = (List<E>) query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			
			return list;
		} catch (Exception e) {
			log.error("searchBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}
	
	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public int executeBySql(final String sql)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("executeBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int executeBySql(final String sql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("executeBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int executeBySql(final String sql, final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params))
			{
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) 
				{
					query.setParameter(i, params[i]);
				}
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("executeBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public int executeBySql(final String sql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			// 影响记录条数
			final int affect = query.executeUpdate();
			
			return affect;
		} catch (Exception e) {
			log.error("executeBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return 0;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @return
	 */
	@Override
	public Long countBySql(final String sql)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("countBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long countBySql(final String sql, final List<Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i, params.get(i));
				}
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("countBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long countBySql(final String sql, final Object[] params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用下标来设置查询参数
				for (int i = 0; i < params.length; i++) {
					query.setParameter(i, params[i]);
				}
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("countBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}

	 /**
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	@Override
	public Long countBySql(final String sql, final Map<String, Object> params)
	{
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			// 获取查询对象 - sql方式
			final Query query = session.createSQLQuery(sql);
			if (!EmptyUtil.isEmpty(params)) {
				// 使用map类型来设置查询参数
				query.setProperties(params);
			}
			final Long result = (Long) query.uniqueResult();
			
			return result;
		} catch (Exception e) {
			log.error("countBySql =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	public void execute1(final String hql, final Map<String, Object> params, Class<E> clazz)
	{
		try {
			// 打开会话
			session = factory.openSession();
			// 查询规则 - 对象
			final Criteria cra = session.createCriteria(clazz);

			// 排序 : 属性名 绑定到 升序/降序
			cra.addOrder(Order.asc("propertyName")).addOrder(Order.desc("propertyName"));
			
			// 等值查询
			cra.add(Restrictions.eq("propertyName", "value"));
			
			// 不等值查询
			cra.add(Restrictions.not(Restrictions.eq("propertyName", "value")));
			
			// 模糊查询
			cra.add(Restrictions.like("propertyName", "value"));
			
			// 大小比较
			cra.add(Restrictions.ge("propertyName", "value"));
			
			// 非空
			
			// 分页
			cra.setFirstResult(0);
			cra.setMaxResults(10);
			
			// 创建别名
			cra.createAlias("属性名", "别名");
			
			// 设置是否只读
			cra.setReadOnly(true);
			
			// 设置超市值
			cra.setTimeout(255);
			
		} catch (Exception e) {
			log.error("execute =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
		}
	}
	
	
	
}
