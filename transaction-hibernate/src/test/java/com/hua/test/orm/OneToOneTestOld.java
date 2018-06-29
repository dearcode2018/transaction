/**
 * 描述: 
 * OneToOneTestOld.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.test.orm;

import java.util.HashMap;
import java.util.Map;

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

/**
 * 描述: 
 * @author qye.zheng
 * 
 * OneToOneTestOld
 */
public final class OneToOneTestOld extends BaseTest
{
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
			
			PersonDao pd = new PersonDaoImpl();
			IdCardDao cd = new IdCardDaoImpl();
			Person p = null;
			String hql = null;
			// 注意 :key 即冒号是紧贴着key的
			hql = "SELECT p FROM " + Person.class.getName() + " p WHERE p.id = ?";
			// p = pd.load(Person.class, 12L);
			//p = pd.get(Person.class, 12L);
			Map<String, Object> params = new HashMap<String, Object>();
			p = new Person();
			p.setName("谭进");
			p.setAddress("广州市番禺区沥滘125号");
			p.setBirthday(DateTimeUtil.getDateTime());
			p.setGender(Gender.MALE);
			p.setNation("汉族");
			p.setPhotoUrl("http://www.bai.com/resource/abc/photo.jpg");
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
			
			p.setIdCard(idCard);
			idCard.setPerson(p);
			
			pd.insert(p);
			//cd.insert(idCard);
			
			
		} catch (Exception e) {
			log.error("testInsert01 =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 新增测试
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testInsert02() {
		try {
			log.info("testInsert02 =====> begin");
			
			// 测试代码
			
			PersonDao pd = new PersonDaoImpl();
			IdCardDao cd = new IdCardDaoImpl();
			Person p = null;
			String hql = null;
			// 注意 :key 即冒号是紧贴着key的
			hql = "SELECT p FROM " + Person.class.getName() + " p WHERE p.id = ?";
			// p = pd.load(Person.class, 12L);
			//p = pd.get(Person.class, 12L);
			Map<String, Object> params = new HashMap<String, Object>();
			p = new Person();
			p.setName("谭进");
			p.setAddress("广州市番禺区沥滘125号");
			p.setBirthday(DateTimeUtil.getDateTime());
			p.setGender(Gender.MALE);
			p.setNation("汉族");
			p.setPhotoUrl("http://www.bai.com/resource/abc/photo.jpg");
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
			
			
			p.setIdCard(idCard);
			idCard.setPerson(p);
			
			pd.insert(p);
			//cd.insert(idCard);
			
			
		} catch (Exception e) {
			log.error("testInsert02 =====> ", e);
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
			PersonDao pd = new PersonDaoImpl();
			id = "74";
			pd.delete(Person.class, id);
			
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
