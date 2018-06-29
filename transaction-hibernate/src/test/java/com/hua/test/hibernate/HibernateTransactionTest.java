/**
 * 描述: 
 * HibernateTransactionTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.hibernate;

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

import org.junit.Ignore;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.orm.entity.o2o.IdCard;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * HibernateTransactionTest
 */
public final class HibernateTransactionTest extends BaseTest {


	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testHibernateTransaction() {
		try {
			log.info("testHibernateTransaction =====> begin");
			
			session = factory.openSession();
			
			// 测试代码
			Person person = null;
			person = new Person();
			person.setName("何进");
			person.setAddress("广州市番禺区沥滘125号");
			person.setBirthday(DateTimeUtil.getDateTime());
			person.setGender(Gender.MALE);
			person.setNation("汉族");
			person.setPhotoUrl("http://www.bai.com/resource/abc/photo.jpg");
			
			IdCard idCard = null;
			idCard = new IdCard();
			// 设置身份证标题
			idCard.setTitle("中华人民共和国 - 居民身份证");
			// 设置身份证号
			idCard.setCardNo("54088219890705885X");
			// 设置签发机构
			idCard.setIssuingAuthority("湛江市公安局");
			// 设置生效日期
			idCard.setEffectiveDate(dateFormat.parse("1989-07-05"));
			idCard.setExpiryDate(dateFormat.parse("1999-07-05"));
			
			log.info("testHibernateTransaction =====> 开始第一轮事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			person.setIdCard(idCard);
			idCard.setPerson(person);
			
			
			// 持久化操作
			session.save(person);
			
			log.info("testHibernateTransaction =====> 第一轮事务");
			// 第一轮事务
			tx.commit();
			tx.commit();
			
			
			
			person = new Person();
			person.setName("何进");
			person.setAddress("广州市番禺区沥滘125号");
			person.setBirthday(DateTimeUtil.getDateTime());
			person.setGender(Gender.MALE);
			person.setNation("汉族");
			person.setPhotoUrl("http://www.bai.com/resource/abc/photo.jpg");
			
			idCard = new IdCard();
			// 设置身份证标题
			idCard.setTitle("中华人民共和国 - 居民身份证");
			// 设置身份证号
			idCard.setCardNo("54088219890705885X");
			// 设置签发机构
			idCard.setIssuingAuthority("湛江市公安局");
			// 设置生效日期
			idCard.setEffectiveDate(dateFormat.parse("1989-07-05"));
			idCard.setExpiryDate(dateFormat.parse("1999-07-05"));
		
			log.info("testHibernateTransaction =====> 开始第二轮事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			person.setIdCard(idCard);
			idCard.setPerson(person);
			
			
			// 持久化操作
			session.save(person);
			
			log.info("testHibernateTransaction =====> 第二轮事务");
			// 第一轮事务
			tx.commit();
			
			
		} catch (Exception e) {
			log.error("testHibernateTransaction =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
			}
		}
	}
	
	/**
	 * 
	 * 描述: 新增测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert01() {
		try {
			
			log.info("testInsert01 =====> begin");
			
			// 测试代码
			Person person = null;
			person = new Person();
			person.setName("何进");
			person.setAddress("广州市番禺区沥滘125号");
			person.setBirthday(DateTimeUtil.getDateTime());
			person.setGender(Gender.MALE);
			person.setNation("汉族");
			person.setPhotoUrl("http://www.bai.com/resource/abc/photo.jpg");
			
			IdCard idCard = null;
			idCard = new IdCard();
			// 设置身份证标题
			idCard.setTitle("中华人民共和国 - 居民身份证");
			// 设置身份证号
			idCard.setCardNo("54088219890705885X");
			// 设置签发机构
			idCard.setIssuingAuthority("湛江市公安局");
			// 设置生效日期
			idCard.setEffectiveDate(dateFormat.parse("1989-07-05"));
			idCard.setExpiryDate(dateFormat.parse("1999-07-05"));
			
			session = factory.openSession();
			log.info("insert =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			person.setIdCard(idCard);
			idCard.setPerson(person);
			
			
			// 持久化操作
			session.save(person);
			
			log.info("insert =====> 提交事务");
			// 提交事务
			tx.commit();
		} catch (Exception e) {
			log.error("insert =====> 回滚事务", e);
			// 回滚事务
			if (null != tx) {
				tx.rollback();
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
