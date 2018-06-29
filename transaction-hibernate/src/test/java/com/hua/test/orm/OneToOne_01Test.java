/**
 * 描述: 
 * OneToOne_01Test.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.test.orm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.hua.constant.ext.Gender;
import com.hua.dao.o2o.IdCardDao;
import com.hua.dao.o2o.PersonDao;
import com.hua.dao.o2o.impl.IdCardDaoImpl;
import com.hua.dao.o2o.impl.PersonDaoImpl;
import com.hua.orm.entity.o2o.IdCard;
import com.hua.orm.entity.o2o.Person;
import com.hua.test.BaseTest;
import com.hua.util.DateTimeUtil;
import com.hua.util.hibernate.HibernateO2OUtil;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * OneToOne_01Test
 */
public final class OneToOne_01Test extends BaseTest
{
	/* SessionFactory */
	public static final SessionFactory factory = HibernateO2OUtil.buildByXml_01();
	
	/* Hibernate 会话 */
	public Session session;
	
	/* 增删改 会话中必须开启一个事务，然后再提交事务 */
	public Transaction tx;
	
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
	 * 描述: 删除测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDelete() {
		try {
			log.info("testDelete =====> begin");
			// 测试代码
			Person person = null;
			person = new Person();
			person.setId("16");
			
			IdCard idCard = null;
			idCard = new IdCard();
			/*
			 * 一定要设置该实体的id，级联删除才能有效，
			 * 因为级联删除并没有去检查 2个实体之间的关联关系，而是以其主键
			 * 是否为空作为判断准则
			 */
			idCard.setId(person.getId());
			
			session = factory.openSession();
			log.info("testDelete =====> 开始事务");
			// 开始事务
			tx = session.beginTransaction();
			
			// 设置关联关系
			person.setIdCard(idCard);
			idCard.setPerson(person);
			
			// 删除，级联
			session.delete(person);
			
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
			PersonDao pd = new PersonDaoImpl();
			IdCardDao cd = new IdCardDaoImpl();
			
			id = "1";
			Person person = pd.get(Person.class, id);
			System.out.println(person.toString());
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
			PersonDao pd = new PersonDaoImpl();
			IdCardDao cd = new IdCardDaoImpl();
			
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
