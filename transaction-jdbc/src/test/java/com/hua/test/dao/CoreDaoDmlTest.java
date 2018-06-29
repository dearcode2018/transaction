/**
 * 描述: 
 * CoreDaoDmlTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.test.dao;

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
import com.hua.dao.o2o.PersonDao;
import com.hua.dao.o2o.impl.PersonDaoImpl;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * CoreDaoDmlTest
 */
public final class CoreDaoDmlTest extends BaseTest {

	private PersonDao personDao = new PersonDaoImpl();
	
	private Person person;
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDml() {
		try {

			
		} catch (Exception e) {
			log.error("testDml =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsertArrayParam() {
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
			
			sql = "INSERT INTO person (oid, name, photoUrl, gender, nation, birthday, address) VALUES (seq_person_oid.nextVal,?,?,?,?,?,?)";
			
			arrayParams = new Object[7];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			//arrayParams[6] = person.getIdCard().getCardId();
			arrayParams[6] = null;
			
			affectRow = personDao.insert(sql, arrayParams);
			
		} catch (Exception e) {
			log.error("testInsertArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsertListParam() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 
			 */
			person = new Person();
			person.setName("姜丽");
			person.setPhotoUrl("http://www.w3c.org/file/jiangli.png");
			person.setGender(Gender.FEMALE);
			person.setNation("汉族");
			person.setBirthday(DateTimeUtil.parseDate("1981-02-04"));
			person.setAddress("陕西省汉中市南汉区汉中大道12号");
			
			sql = "INSERT INTO person (oid, name, photoUrl, gender, nation, birthday, address) VALUES (seq_person_oid.nextVal,?,?,?,?,?,?)";
			
			listParams.add(person.getName());
			listParams.add(person.getPhotoUrl());
			listParams.add(person.getGender().getValue());
			listParams.add(person.getNation());
			listParams.add(person.getBirthday());
			listParams.add(person.getAddress());
			listParams.add(null);
			
			affectRow = personDao.insert(sql, listParams);
			
			log.info("testInsertListParam =====> affectRow = " + affectRow);
			
		} catch (Exception e) {
			log.error("testInsertListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsertOrUpdateArrayParam() {
		try {
			/**
			 新增或更新的sql语句应该进行动态拼接，若某个字段为空，
			 则不能加入sql语句.
			 例如 insert into  tb_name ()
			 values () 这2部分应该是进行拼接而得到的.
			 
			 */
			person = new Person();
			person.setName("王东平2");
			person.setPhotoUrl("http://www.w3c.org/file/wangdongping.png");
			person.setGender(Gender.MALE);
			person.setNation("回族");
			person.setBirthday(DateTimeUtil.parseDate("1971-05-14"));
			person.setAddress("广西省北海市江北区沿江东路33号");
			
			sql = "INSERT INTO person (oid, name, photoUrl, gender, nation, birthday, address) VALUES (seq_person_oid.nextVal,?,?,?,?,?,?)";
			
			arrayParams = new Object[7];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			//arrayParams[6] = person.getIdCard().getCardId();
			arrayParams[6] = null;
			
			affectRow = personDao.insertOrUpdate(sql, arrayParams);
			
		} catch (Exception e) {
			log.error("testInsertOrUpdateArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsertOrUpdateListParam() {
		try {
			
			
		} catch (Exception e) {
			log.error("testInsertOrUpdateListParam =====> ", e);
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
			person = new Person();
			person.setId("18");
			
			sql = "DELETE FROM person a WHERE a.oid = ?";
			
			affectRow = personDao.delete(sql, person.getId());
			
			log.info("testDelete =====> affectRow = " + affectRow);
			
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
	public void testDeleteArrayParam() {
		try {
			arrayParams = new Object[2];
			
			arrayParams[0] = 16L;
			arrayParams[1] = 17L;
			
			sql = "DELETE FROM person a WHERE a.oid IN (?,?)";
			
			affectRow = personDao.delete(sql, arrayParams);
			
		} catch (Exception e) {
			log.error("testDeleteArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeleteListParam() {
		try {
			listParams.add(12L);
			listParams.add(15L);
			
			sql = "DELETE FROM person a WHERE a.oid IN (?,?)";
			
			affectRow = personDao.delete(sql, listParams);
			
		} catch (Exception e) {
			log.error("testDeleteListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 批量删除
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDeleteBatch() {
		try {
			listParams.add(13L);
			listParams.add(14L);
			
			sql = "DELETE FROM person a WHERE a.oid IN (?,?)";
			
			affectRow = personDao.delete(sql, listParams);
		} catch (Exception e) {
			log.error("testDeleteBatch =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdateArrayParam() {
		try {
			person = new Person();
			person.setName("姜丽");
			person.setPhotoUrl("http://www.w3c.org/file/jiangli.png");
			person.setGender(Gender.FEMALE);
			person.setNation("汉族");
			person.setBirthday(DateTimeUtil.parseDate("1981-02-05"));
			person.setAddress("陕西省汉中市南汉区汉中大道12号");
			
			arrayParams = new Object[7];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			//arrayParams[6] = person.getIdCard().getCardId();
			arrayParams[6] = oid;
			
			sql = "UPDATE person a SET a.name = ?, a.photoUrl = ?, a.gender = ?, a.nation = ?, a.birthday = ?, a.address = ? where a.oid = ?";
			
			affectRow = personDao.update(sql, arrayParams);
			
		} catch (Exception e) {
			log.error("testUpdateArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpdateListParam() {
		try {
			person = new Person();
			person.setName("姜丽");
			person.setPhotoUrl("http://www.w3c.org/file/jiangli.png");
			person.setGender(Gender.FEMALE);
			person.setNation("汉族");
			person.setBirthday(DateTimeUtil.parseDate("1981-02-04"));
			person.setAddress("陕西省汉中市南汉区汉中大道13号");
			
			listParams.add(person.getName());
			listParams.add(person.getPhotoUrl());
			listParams.add(person.getGender().getValue());
			listParams.add(person.getNation());
			listParams.add(person.getBirthday());
			listParams.add(person.getAddress());
			listParams.add(oid);
			
			sql = "UPDATE person a SET a.name = ?, a.photoUrl = ?, a.gender = ?, a.nation = ?, a.birthday = ?, a.address = ? where a.oid = ?";
			
			affectRow = personDao.update(sql, listParams);
			
		} catch (Exception e) {
			log.error("testUpdateListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExecuteArrayParam() {
		try {
			person = new Person();
			person.setName("姜丽0");
			person.setPhotoUrl("http://www.w3c.org/file/jiangli.png");
			person.setGender(Gender.FEMALE);
			person.setNation("汉族");
			person.setBirthday(DateTimeUtil.parseDate("1981-02-05"));
			person.setAddress("陕西省汉中市南汉区汉中大道14号");
			
			arrayParams = new Object[7];
			arrayParams[0] = person.getName();
			arrayParams[1] = person.getPhotoUrl();
			arrayParams[2] = person.getGender().getValue();
			arrayParams[3] = person.getNation();
			arrayParams[4] = person.getBirthday();
			arrayParams[5] = person.getAddress();
			//arrayParams[6] = person.getIdCard().getCardId();
			log.info("testExecuteArrayParam =====> oid = " + oid);
			arrayParams[6] = oid;
			
			sql = "UPDATE person a SET a.name = ?, a.photoUrl = ?, a.gender = ?, a.nation = ?, a.birthday = ?, a.address = ? where a.oid = ?";
			
			// 返回false
			flag = personDao.execute(sql, arrayParams);
			
			log.info("testExecuteArrayParam =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testExecuteArrayParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testExecuteListParam() {
		try {
			person = new Person();
			person.setName("姜丽1");
			person.setPhotoUrl("http://www.w3c.org/file/jiangli.png");
			person.setGender(Gender.FEMALE);
			person.setNation("汉族");
			person.setBirthday(DateTimeUtil.parseDate("1981-02-04"));
			person.setAddress("陕西省汉中市南汉区汉中大道13号");
			
			listParams.add(person.getName());
			listParams.add(person.getPhotoUrl());
			listParams.add(person.getGender().getValue());
			listParams.add(person.getNation());
			listParams.add(person.getBirthday());
			listParams.add(person.getAddress());
			listParams.add(oid);
			
			sql = "UPDATE person a SET a.name = ?, a.photoUrl = ?, a.gender = ?, a.nation = ?, a.birthday = ?, a.address = ? where a.oid = ?";
			// 返回false
			flag = personDao.execute(sql, listParams);
			
			log.info("testExecuteListParam =====> flag = " + flag);
			
		} catch (Exception e) {
			log.error("testExecuteListParam =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommit() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommit =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testRollback() {
		try {
			
			
		} catch (Exception e) {
			log.error("testRollback =====> ", e);
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
			String str = null;
			
			log.info("testTemp =====> " + (str instanceof String));
			log.info("testTemp =====> " + (str instanceof Object));
			
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
