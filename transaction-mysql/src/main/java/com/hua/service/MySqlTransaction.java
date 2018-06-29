/**
  * @filename MySqlTransaction.java
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
import com.hua.orm.entity.m2o.Custom;

 /**
 * @type MySqlTransaction
 * @description 
 * @author qianye.zheng
 */
@Service
public class MySqlTransaction 
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
	 * @description 手工事务
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void transactionA(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[5];
		params[0] = entity.getName();
		params[1] = entity.getAddress();
		params[2] = entity.getBalance();
		params[3] = entity.getStatus().getValue();
		params[4] = entity.getId();
		
		String sql = "update custom set name = ?, address = ?, balance = ?, status = ? " +
				"where id = ?";
		try
		{
			customDao.update(sql, params);
			System.out.println("A.提交事务前");
			// 提交事务
			transactionManager.commit(transactionStatus);
			System.out.println("A.提交事务后");
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
	/**
	 * 
	 * @description 手工事务
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void transactionB(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[5];
		params[0] = entity.getName();
		params[1] = entity.getAddress();
		params[2] = entity.getBalance();
		params[3] = entity.getStatus().getValue();
		params[4] = entity.getId();
		
		String sql = "update custom set name = ?, address = ?, balance = ?, status = ? " +
				"where id = ?";
		try
		{
			customDao.update(sql, params);
			System.out.println("B.提交事务前");
			// 提交事务
			transactionManager.commit(transactionStatus);
			System.out.println("B.提交事务后");
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
	/**
	 * 
	 * @description 锁表 (锁全表)
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void lockTable()
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		String sql = "update custom set status = 0 ";
		try
		{
			customDao.update(sql);
			System.out.println("锁表中...");
			
			// 提交事务
			//transactionManager.commit(transactionStatus);
			
			// 在此打断点，模拟锁表场景
			System.out.println("");
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
	/**
	 * 
	 * @description 行级锁
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void rowLevelLock(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
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
		params[3] = entity.getStatus().getValue();
		
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
	
	/**
	 * 
	 * @description 表级锁
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void tableLevelLock(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
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
		params[3] = entity.getStatus().getValue();
		
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
	
	/**
	 * 
	 * @description 事务不提交也不回滚
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void transactionUnCommit(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
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
		params[3] = entity.getStatus().getValue();
		
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
	
	/**
	 * 
	 * @description insert语句
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void insertStatement(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
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
		params[3] = entity.getStatus().getValue();
		
		String sql = "insert into custom (name, address, balance, status) " +
				"values (?, ?, ?, ?)";
		try
		{
			customDao.insert(sql, params);
			System.out.println("提交事务前");
			// 提交事务
			transactionManager.commit(transactionStatus);
			System.out.println("提交事务后");
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
	/**
	 * 
	 * @description update语句
	 * 在程序中操作事务
	 * @param entity
	 * @author qianye.zheng
	 */
	public void updateStatement(final Custom entity)
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[5];
		params[0] = entity.getName();
		params[1] = entity.getAddress();
		params[2] = entity.getBalance();
		params[3] = entity.getStatus().getValue();
		params[4] = entity.getId();
		
		String sql = "update custom set name = ?, address = ?, balance = ?, status = ? " +
				"where id = ?";
		try
		{
			Integer result = customDao.update(sql, params);
			System.out.println("提交事务前");
			// 提交事务
			transactionManager.commit(transactionStatus);
			System.out.println("提交事务后");
			System.out.println("更新结果数: " + result);
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
	}
	
	/**
	 * 
	 * @description 事务等待超时
	 * 在程序中操作事务
	 * @param entity
	 * @param timeout 超时时间
	 * @author qianye.zheng
	 */
	public void waitTimeout(final Custom entity, final Integer timeout)
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
		transactionDefinition.setTimeout(timeout);
		
		// 2.获取事务状态 (开启事务)
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
		/*
		 * Propagation.REQUIRED
		 * @Transactional 注解的默认传播方式
		 * 若当前存在事务，则使用该事务执行，否则创建一个事务.
		 * 
		 * 	注意: 若调用方有事务，则被调用方使用该事务; 否则被调用方
		 * 将创建一个事务.
		 * 
		 */
		Object[] params = new Object[5];
		params[0] = entity.getName();
		params[1] = entity.getAddress();
		params[2] = entity.getBalance();
		params[3] = entity.getStatus().getValue();
		params[4] = entity.getId();
		
		String sql = "update custom set name = ?, address = ?, balance = ?, status = ? " +
				"where id = ?";
		try
		{
			customDao.update(sql, params);
			System.out.println("事务等待超时.提交事务前");
			// 提交事务
			transactionManager.commit(transactionStatus);
			System.out.println("事务等待超时.提交事务后");
		} catch (Exception e)
		{
			// 有异常，回滚事务
			transactionManager.rollback(transactionStatus);
		}
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
		final TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);;
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
		params[3] = entity.getStatus().getValue();
		
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
