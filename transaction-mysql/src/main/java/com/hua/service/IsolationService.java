/**
  * @filename IsolationService.java
  * @description 
  * @version 1.0
  * @author qianye.zheng
 */
package com.hua.service;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hua.dao.m2o.CustomDao;
import com.hua.extractor.CustomExtractor;
import com.hua.orm.entity.m2o.Custom;
import com.hua.util.JacksonUtil;

 /**
 * @type IsolationService
 * @description 事务隔离
 * @author qianye.zheng
 */
@Service
public class IsolationService
{
	
	/*
	 * 在 spring-tx.xml配置中声明了一个transactionManager
	 * 事务管理器
	 * 
	 */
	@Resource
	private DataSourceTransactionManager transactionManager;
	
	@Resource
	private CustomDao customDao;
	
	/**
	 * 
	 * @description
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public Custom readUncommitted(final String id)
	{
		/*
		 * 事务定义对象，实现TransactionDefinition接口来
		 * 声明一个类型，或者使用默认的DefaultTransactionDefinition
		 * 然后设置需要修改的参数即可.
		 * 参数: 事务名称、隔离级别、传播行为、超时时间、是否只读
		 */
		// 1.设置事务参数
		final DefaultTransactionDefinition transactionDefinition = 
				new DefaultTransactionDefinition();
		// 事务名称
		//transactionDefinition.setName("transactionManager");
		// 隔离级别: 可重复读 或者直接使用数据库的默认值
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);
		// 使用默认值
		//transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//  当前方法事务的传播行为
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 是否只读
		transactionDefinition.setReadOnly(true);
		// 超时时间，单位: 秒
		//transactionDefinition.setTimeout(-1);
		
		// 2.获取事务状态 (开启事务)
		transactionManager.getTransaction(transactionDefinition);
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[1];
		params[0] = id;
		Custom entity = null;
		String sql = "select id, name, balance, address, status from custom where  id = ?";
		entity = customDao.get(sql, params, new CustomExtractor());
		
		return entity;
	}
	
	/**
	 * 
	 * @description
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public Custom readCommitted(final String id)
	{
		/*
		 * 事务定义对象，实现TransactionDefinition接口来
		 * 声明一个类型，或者使用默认的DefaultTransactionDefinition
		 * 然后设置需要修改的参数即可.
		 * 参数: 事务名称、隔离级别、传播行为、超时时间、是否只读
		 */
		// 1.设置事务参数
		final DefaultTransactionDefinition transactionDefinition = 
				new DefaultTransactionDefinition();
		// 事务名称
		//transactionDefinition.setName("transactionManager");
		// 隔离级别: 可重复读 或者直接使用数据库的默认值
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		// 使用默认值
		//transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//  当前方法事务的传播行为
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 是否只读
		transactionDefinition.setReadOnly(true);
		// 超时时间，单位: 秒
		//transactionDefinition.setTimeout(-1);
		
		// 2.获取事务状态 (开启事务)
		transactionManager.getTransaction(transactionDefinition);
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[1];
		params[0] = id;
		Custom entity = null;
		String sql = "select id, name, balance, address, status from custom where  id = ?";
		entity = customDao.get(sql, params, new CustomExtractor());
		
		return entity;
	}
	
	/**
	 * 
	 * @description
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public Custom repeatableRead(final String id)
	{
		/*
		 * 事务定义对象，实现TransactionDefinition接口来
		 * 声明一个类型，或者使用默认的DefaultTransactionDefinition
		 * 然后设置需要修改的参数即可.
		 * 参数: 事务名称、隔离级别、传播行为、超时时间、是否只读
		 */
		// 1.设置事务参数
		final DefaultTransactionDefinition transactionDefinition = 
				new DefaultTransactionDefinition();
		// 事务名称
		//transactionDefinition.setName("transactionManager");
		// 隔离级别: 可重复读 或者直接使用数据库的默认值
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		// 使用默认值
		//transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//  当前方法事务的传播行为
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 是否只读
		transactionDefinition.setReadOnly(true);
		// 超时时间，单位: 秒
		//transactionDefinition.setTimeout(-1);
		
		// 2.获取事务状态 (开启事务)
		transactionManager.getTransaction(transactionDefinition);
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[1];
		params[0] = id;
		Custom entity = null;
		String sql = "select id, name, balance, address, status from custom where  id = ?";
		
		System.out.println("其他事务变更该条数据之前");
		entity = customDao.get(sql, params, new CustomExtractor());
		System.out.println(JacksonUtil.writeAsString(entity));
		
		/*
		 * 在同一事务中，事务隔离级别为 可重复读，多次读取同一条数据的结果
		 * 一定保持相同，其他事务对这条数据的变更不可见.
		 */
		
		System.out.println("其他事务变更该条数据之后");
		entity = customDao.get(sql, params, new CustomExtractor());
		System.out.println(JacksonUtil.writeAsString(entity));
		
		return entity;
	}
	
	/**
	 * 
	 * @description
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public Custom serializable(final String id)
	{
		/*
		 * 事务定义对象，实现TransactionDefinition接口来
		 * 声明一个类型，或者使用默认的DefaultTransactionDefinition
		 * 然后设置需要修改的参数即可.
		 * 参数: 事务名称、隔离级别、传播行为、超时时间、是否只读
		 */
		// 1.设置事务参数
		final DefaultTransactionDefinition transactionDefinition = 
				new DefaultTransactionDefinition();
		// 事务名称
		//transactionDefinition.setName("transactionManager");
		// 隔离级别: 可重复读 或者直接使用数据库的默认值
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
		// 使用默认值
		//transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//  当前方法事务的传播行为
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 是否只读
		transactionDefinition.setReadOnly(true);
		// 超时时间，单位: 秒
		//transactionDefinition.setTimeout(-1);
		
		// 2.获取事务状态 (开启事务)
		transactionManager.getTransaction(transactionDefinition);
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[1];
		params[0] = id;
		Custom entity = null;
		String sql = "select id, name, balance, address, status from custom where  id = ?";
		entity = customDao.get(sql, params, new CustomExtractor());
		
		return entity;
	}
	
	/**
	 * 
	 * @description 手工事务
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void manualTransaction(final Custom entity)
	{
		/*
		 * 事务定义对象，实现TransactionDefinition接口来
		 * 声明一个类型，或者使用默认的DefaultTransactionDefinition
		 * 然后设置需要修改的参数即可.
		 * 参数: 事务名称、隔离级别、传播行为、超时时间、是否只读
		 */
		// 1.设置事务参数
		final DefaultTransactionDefinition transactionDefinition = 
				new DefaultTransactionDefinition();
		// 事务名称
		//transactionDefinition.setName("transactionManager");
		// 隔离级别: 可重复读 或者直接使用数据库的默认值
		transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
		// 使用默认值
		//transactionDefinition.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
		//  当前方法事务的传播行为
		transactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		// 是否只读
		transactionDefinition.setReadOnly(false);
		// 超时时间，单位: 秒
		//transactionDefinition.setTimeout(-1);
		
		// 2.获取事务状态 (开启事务)
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[4];
		params[0] = entity.getName();
		params[1] = entity.getAddress();
		params[2] = entity.getBalance();
		params[3] = entity.getStatus();
		
		String sql = "insert into custom (name, address, balance, status) " +
				"values (?, ?, ?, ?)";
		try
		{
			customDao.insert(sql, params);
			// 提交事务
			transactionManager.commit(transactionStatus);
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
}
