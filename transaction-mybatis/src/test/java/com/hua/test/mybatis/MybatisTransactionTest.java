/**
 * 描述: 
 * MybatisTransactionTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.mybatis;

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
import com.hua.mapper.o2o.PersonMapper;
import com.hua.orm.entity.o2o.Person;
import com.hua.search.bean.o2o.PersonSearch;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * MybatisTransactionTest
 */
public final class MybatisTransactionTest extends BaseTest {

	//
	private PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
	
	private Person person;
	
	private PersonSearch personSearch;

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMybatisTransaction() {
		try {
			
			
		} catch (Exception e) {
			log.error("testMybatisTransaction =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert() {
		try {
			person = new Person();
			person.setName("王东22");
			// 支持插入空值
			person.setPhotoUrl(null);
			//person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE.getValue());
			//person.setGender(Gender.MALE.getValue());
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			int effect = mapper.insert(person);
			sqlSession.commit();
			
			log.info("testInsert =====> " + person.getId());
			log.info("testInsert =====> effect = " + effect);
			
		} catch (Exception e) {
			log.error("testInsert =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			oid = "2";
			int effect = mapper.delete(oid);
			
			log.info("testDelete =====> effect = " + effect);
			sqlSession.commit();
			
			
		} catch (Exception e) {
			log.error("testDelete =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdate() {
		try {
			person = new Person();
			oid = "13";
			person.setId(oid);
			person.setName("王东1");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE.getValue());
			//person.setGender(Gender.MALE.getValue());
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			mapper.update(person);
			
			sqlSession.commit();
		} catch (Exception e) {
			log.error("testUpdate =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testGet() {
		try {
			oid = "1";
			person = mapper.get(oid);
			System.out.println(person.toString());
			
		} catch (Exception e) {
			log.error("testGet =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearch() {
		try {
			personSearch = new PersonSearch();
			personSearch.setName("王");
			personSearch.setGender(Gender.MALE.getValue());
			personSearch.setNation("汉");
			personSearch.setStartBirthday(DateTimeUtil.parseDate("1950-01-01"));
			personSearch.setEndBirthday(DateTimeUtil.parseDate("1999-12-31"));
			personSearch.setAddress("北路");
			
			
			pagerEntity.setSearchBean(personSearch);
			//pagerEntity.setEntity(null);
			pagerEntity.setStartIndex(1L);
			pagerEntity.setEndIndex(10L);
			
			System.out.println(mapper.search(pagerEntity).size());
			
		} catch (Exception e) {
			log.error("testSearch =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSearchDataSize() {
		try {
			personSearch = new PersonSearch();
			personSearch.setName("王");
			personSearch.setGender(Gender.MALE.getValue());
			personSearch.setNation("汉");
			personSearch.setStartBirthday(DateTimeUtil.parseDate("1950-01-01"));
			personSearch.setEndBirthday(DateTimeUtil.parseDate("1999-12-31"));
			personSearch.setAddress("北路");
			
			pagerEntity.setSearchBean(personSearch);
			pagerEntity.setStartIndex(1L);
			pagerEntity.setEndIndex(20L);
			
			System.out.println(mapper.searchDataSize(pagerEntity));
			
		} catch (Exception e) {
			log.error("testSearchDataSize =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCount() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCount =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testArrayForeach() {
		try {
			Long[] oids = {1L, 2L, 3L};
			count = mapper.arrayForeach(oids);
			log.info("testArrayForeach =====> count = " + count);
			
		} catch (Exception e) {
			log.error("testArrayForeach =====> ", e);
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
