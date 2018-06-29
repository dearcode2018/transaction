/**
 * 描述: 
 * CascadeTest.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.test.orm;

import org.junit.Test;

import com.hua.test.BaseTest;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * CascadeTest
 */
public final class CascadeTest extends BaseTest
{
	/**
	 * 
	 * 描述: 新增测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert() {
		try {
			// 打开会话
			session = factory.openSession();
			log.info("testInsert =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			
			log.info("testInsert =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testInsert =====> 回滚事务", e);
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
	 * 描述: 删除测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			// 打开会话
			session = factory.openSession();
			log.info("testDelete =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			
			log.info("testDelete =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testDelete =====> 回滚事务", e);
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
	 * 描述: 更新测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdate() {
		try {
			// 打开会话
			session = factory.openSession();
			log.info("testUpdate =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 操作过程
			
			log.info("testUpdate =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("testUpdate =====> 回滚事务", e);
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
	 * 描述: 查询测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testQuery() {
		try {
			// 打开会话
			session = factory.openSession();
			
			// 操作过程
			
			
		} catch (Exception e) {
			log.error("testQuery =====> ", e);
		} finally {
			if (null != session) {
				// 关闭会话
				session.close();
			}
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
