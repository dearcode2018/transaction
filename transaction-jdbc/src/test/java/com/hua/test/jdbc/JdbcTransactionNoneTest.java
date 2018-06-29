/**
 * 描述: 
 * JdbcTransactionNoneTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.jdbc;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.dao.o2o.PersonDao;
import com.hua.dao.o2o.impl.PersonDaoImpl;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.JdbcUtil;
import com.hua.util.SqlUtil;


/**
 * 描述: 事务级别: 无事务
 * 有些数据库是不支持这个级别的，例如mysql，
 * Transaction isolation level NONE not supported by MySQL
 * 
 * @author qye.zheng
 * JdbcTransactionNoneTest
 */
public final class JdbcTransactionNoneTest extends BaseTest {

	private PersonDao personDao = new PersonDaoImpl();
	
	private Person person;

	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdbcTransaction() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 */
			person = new Person();
			person.setName("王东平");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "INSERT INTO person (name, photoUrl, gender, nation, birthday, address) VALUES (?,?,?,?,?,?)";
			
			arrayParams = new Object[6];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			
			final Connection conn = JdbcUtil.getConnection();
			
			/*
			 * 事务隔离级别
			 * TransactionIsolation
			 *  int TRANSACTION_NONE = 0; 无事务
			 *  int TRANSACTION_READ_UNCOMMITTED = 1; 不可
			 *   int TRANSACTION_READ_COMMITTED   = 2;
			 *   int TRANSACTION_REPEATABLE_READ  = 4;
			 *   int TRANSACTION_SERIALIZABLE     = 8;
			 */
			// 设置事务隔离级别 无事务
			conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
			
			// 获取声明对象
			final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
			// 影响行数
			int affect = 0;
			affect = ps.executeUpdate();
			
			// 提交事务
			//JdbcUtil.commit(conn);
			
			// 回滚事务
			//JdbcUtil.rollback(conn);
			
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
			log.info("testJdbcTransaction =====> affect = " + affect);
		} catch (Exception e) {
			log.error("testJdbcTransaction =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdbcTransactionA() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 */
			person = new Person();
			person.setName("王东平");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "select count(*) from person";
			
			final Connection conn = JdbcUtil.getConnection();
			/*
			 * 事务隔离级别
			 * TransactionIsolation
			 *  int TRANSACTION_NONE = 0; 无事务
			 *  int TRANSACTION_READ_UNCOMMITTED = 1; 不可
			 *   int TRANSACTION_READ_COMMITTED   = 2;
			 *   int TRANSACTION_REPEATABLE_READ  = 4;
			 *   int TRANSACTION_SERIALIZABLE     = 8;
			 */
			// 设置事务隔离级别
			conn.setTransactionIsolation(Connection.TRANSACTION_NONE);
			log.info("testJdbcTransactionA =====> 事务隔离级别 = " + conn.getTransactionIsolation());
			// 获取声明对象
			final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
			// 数组参数
			SqlUtil.setValue(ps, new Object[] {});
			// 影响行数
			int affect = 0;
			ResultSet resultSet = ps.executeQuery();
			resultSet.next();
			System.out.println(resultSet.getLong(1));
			
			System.out.println("事务提交之前...");
			
			// 提交事务
			//JdbcUtil.commit(conn);
			
			// 回滚事务
			//JdbcUtil.rollback(conn);
			
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
			log.info("testJdbcTransactionA =====> affect = " + affect);
		} catch (Exception e) {
			log.error("testJdbcTransactionA =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdbcTransactionB() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 */
			person = new Person();
			person.setName("王东平");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "INSERT INTO person (name, photoUrl, gender, nation, birthday, address) VALUES (?,?,?,?,?,?)";
			
			arrayParams = new Object[6];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			
			final Connection conn = JdbcUtil.getConnection();
			/*
			 * 事务隔离级别
			 * TransactionIsolation
			 *  int TRANSACTION_NONE = 0; 无事务
			 *  int TRANSACTION_READ_UNCOMMITTED = 1; 不可
			 *   int TRANSACTION_READ_COMMITTED   = 2;
			 *   int TRANSACTION_REPEATABLE_READ  = 4;
			 *   int TRANSACTION_SERIALIZABLE     = 8;
			 */
			// 设置事务隔离级别
			//conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			log.info("testJdbcTransactionB =====> 事务隔离级别 = " + conn.getTransactionIsolation());
			// 获取声明对象
			final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
			// 影响行数
			int affect = 0;
			affect = ps.executeUpdate();
			
			System.out.println("事务提交之前...");
			
			// 提交事务
			//JdbcUtil.commit(conn);
			
			// 回滚事务
			//JdbcUtil.rollback(conn);
			
			// 关闭 db 资源，关闭资源 没有自动提交事务
			JdbcUtil.close(null, ps);
			log.info("testJdbcTransactionB =====> affect = " + affect);
		} catch (Exception e) {
			log.error("testJdbcTransactionB =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testJdbcTransaction00000() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 */
			person = new Person();
			person.setName("王东平");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "INSERT INTO person (name, photoUrl, gender, nation, birthday, address) VALUES (?,?,?,?,?,?)";
			
			arrayParams = new Object[6];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			
			final Connection conn = JdbcUtil.getConnection();
			/*
			 * 事务隔离级别
			 * TransactionIsolation
			 *  int TRANSACTION_NONE = 0; 无事务
			 *  int TRANSACTION_READ_UNCOMMITTED = 1; 不可
			 *   int TRANSACTION_READ_COMMITTED   = 2;
			 *   int TRANSACTION_REPEATABLE_READ  = 4;
			 *   int TRANSACTION_SERIALIZABLE     = 8;
			 */
			// 设置事务隔离级别
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			// 获取声明对象
			final PreparedStatement ps = JdbcUtil.getPreparedStatement(conn, sql);
			// 数组参数
			SqlUtil.setValue(ps, arrayParams);
			
			
			//affectRow = personDao.insert(sql, arrayParams);
			
			// 影响行数
			int affect = 0;
			affect = ps.executeUpdate();
			
			// 提交事务
			JdbcUtil.commit(conn);
			
			// 回滚事务
			JdbcUtil.rollback(conn);
			
			// 关闭 db 资源
			JdbcUtil.close(null, ps);
		} catch (Exception e) {
			log.error("testJdbcTransaction00000 =====> ", e);
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
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
