/**
 * 描述: 
 * HqlM2OTest.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.test.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hua.orm.entity.m2o.Custom;
import com.hua.orm.entity.m2o.Item;
import com.hua.test.BaseTest;
import com.hua.util.HibernateUtil;

/**
 * 描述: many to one 双向
 * @author qye.zheng
 * 
 * HqlM2OTest
 */
public final class HqlM2OTest extends BaseTest
{
	
	/* SessionFactory */
	public static final SessionFactory factory = HibernateUtil.buildByXml();
	
	/* Hibernate 会话 */
	public Session session;
	
	/* 增删改 会话中必须开启一个事务，然后再提交事务 */
	public Transaction tx;
	
	/**
	 * 新增客户 --> 新增/查询订单
	 * 
	 * 删除客户 --> 删除/查询该客户的所有订单
	 *  
	 * 更新客户 --> 新增/删除/修改/查询 订单
	 *  
	 * 查询客户 --> 新增/删除/修改/查询 订单
	 * 
	 */
	
	/**
	 * 描述: 新增测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert01() {
		try {
			
			log.info("testInsert =====> begin");
			
			Custom custom = new Custom();
			custom.setName("zhengqianye");
			custom.setAddress("guagnzhoucity");
			custom.setBalance(10.34);
			
			Item item = null;
			
			item = new Item();
			// 设置关联关系
			custom.getItems().add(item);
			item.setCustom(custom);
			item.setName("天河又一城1");
			item.setRemark("消费金额1");
			item.setMonetary(10.34);
			
			item = new Item();
			// 设置关联关系
			custom.getItems().add(item);
			item.setCustom(custom);
			item.setName("天河又一城2");
			item.setRemark("消费金额2");
			item.setMonetary(5.34);
			
			session = factory.openSession();
			log.info("insert =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 持久化操作
			session.save(custom);
			
			log.info("insert =====> 提交事务");
			// 提交事务
			tx.commit();
			
		} catch (Exception e) {
			log.error("testInsert =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 删除测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			log.info("testDelete =====> begin");
			
			// 测试代码
			log.info("testDelete =====> begin");
			
			session = factory.openSession();
			log.info("testDelete =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			Custom custom = new Custom();
			custom.setName("zhengqianye");
			custom.setAddress("guagnzhoucity");
			custom.setBalance(10.34);
			Item item = null;
			item = new Item();
			custom.getItems().add(item);
			item.setCustom(custom);
			item.setName("天河又一城1");
			item.setRemark("消费金额1");
			item.setMonetary(10.34);
			
			item = new Item();
			custom.getItems().add(item);
			item.setCustom(custom);
			item.setName("天河又一城2");
			item.setRemark("消费金额2");
			item.setMonetary(5.34);
			
			// 持久化操作
			session.save(null);
			
			log.info("testDelete =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 更新测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdate() {
		try {
			log.info("testUpdate =====> begin");
			
			// 测试代码
			log.info("testUpdate =====> begin");
			
			session = factory.openSession();
			log.info("testUpdate =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			
			
			// 持久化操作
			session.save(null);
			
			log.info("insert =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testUpdate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 新增或更新
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSaveOrUpdate() {
		try {
			log.info("testSaveOrUpdate =====> begin");
			
			// 测试代码
			log.info("testSaveOrUpdate =====> begin");
			
			session = factory.openSession();
			log.info("testSaveOrUpdate =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			
			
			// 持久化操作
			session.save(null);
			
			log.info("testSaveOrUpdate =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testSaveOrUpdate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 查询测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testQuery() {
		try {
			log.info("testQuery =====> begin");
			
			// 测试代码
			
		} catch (Exception e) {
			log.error("testQuery =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 获取单个对象 - get 方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			log.info("testGet =====> begin");
			
			// 测试代码
			log.info("testGet =====> begin");
			
			session = factory.openSession();
			log.info("testGet =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			
			
			// 持久化操作
			session.save(null);
			
			log.info("testGet =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 获取单个对象 - load 方式
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testLoad() {
		try {
			log.info("testLoad =====> begin");
			
			// 测试代码
			
		} catch (Exception e) {
			log.error("testLoad =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 统计测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCount() {
		try {
			log.info("testCount =====> begin");
			
			// 测试代码
			
			
		} catch (Exception e) {
			log.error("testCount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 搜索测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearch() {
		try {
			log.info("testSearch =====> begin");
			
			// 测试代码
			
		} catch (Exception e) {
			log.error("testSearch =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 执行测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExecute() {
		try {
			log.info("testExecute =====> begin");
			
			// 测试代码
			
		} catch (Exception e) {
			log.error("testExecute =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 分页测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testPaging() {
		try {
			log.info("testPaging =====> begin");
			
			// 测试代码
			
			
		} catch (Exception e) {
			log.error("testPaging =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
			
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
}
