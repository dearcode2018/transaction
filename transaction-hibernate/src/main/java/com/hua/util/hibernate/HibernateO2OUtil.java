/**
 * 描述: 
 * HibernateO2OUtil.java
 * @author qye.zheng
 * 
 *  version 1.0
 */
package com.hua.util.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 描述: 
 * @author qye.zheng
 * 
 * HibernateO2OUtil
 */
public final class HibernateO2OUtil
{
	private static SessionFactory factory;

	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private HibernateO2OUtil()
	{
	}
	
	static {
		
	}

	/**
	 * 
	 * 描述: 获取SessionFactory
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		
		return factory;
	}
	
	/**
	 * 
	 * 描述: 通过xml文件方式构建
	 * @author qye.zheng
	 * 
	 */
	public static SessionFactory buildByXml_01() {
		if (null != factory) {
			
			return factory;
		}
		final String filePath = "/conf/xml/hibernate.cfg_o2o_01.xml";
		// 获取Hibernate配置对象
		final Configuration configuration = getConfiguration(filePath);
		
		// 说明 : 这种获取SessionFactory的方式已经过时
		// final SessionFactory  sessionFactory = configuration.buildSessionFactory();
		
		// 通过生成一个注册机对象，然后再生成SessionFactory
		/**
		 * 注意 : 需要调用applySettings方法，将hibernate配置设置，然后再构建
		 * 否则将抛以下异常:
		 * Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
		 */
		final ServiceRegistry registry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		// 通过注册机对象为参数，生成SessionFactory
		final SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		return sessionFactory;
	}
	
	/**
	 * 
	 * 描述: 通过xml文件方式构建
	 * @author qye.zheng
	 * 
	 */
	public static SessionFactory buildByXml_02() {
		if (null != factory) {
			
			return factory;
		}
		final String filePath = "/conf/xml/hibernate.cfg_o2o_02.xml";
		// 获取Hibernate配置对象
		final Configuration configuration = getConfiguration(filePath);
		
		// 说明 : 这种获取SessionFactory的方式已经过时
		// final SessionFactory  sessionFactory = configuration.buildSessionFactory();
		
		// 通过生成一个注册机对象，然后再生成SessionFactory
		/**
		 * 注意 : 需要调用applySettings方法，将hibernate配置设置，然后再构建
		 * 否则将抛以下异常:
		 * Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
		 */
		final ServiceRegistry registry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		// 通过注册机对象为参数，生成SessionFactory
		final SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		return sessionFactory;
	}
	
	/**
	 * 
	 * 描述: 通过xml文件方式构建
	 * @author qye.zheng
	 * 
	 */
	public static SessionFactory buildByXml_03() {
		if (null != factory) {
			
			return factory;
		}
		final String filePath = "/conf/xml/hibernate.cfg_o2o_03.xml";
		// 获取Hibernate配置对象
		final Configuration configuration = getConfiguration(filePath);
		
		// 说明 : 这种获取SessionFactory的方式已经过时
		// final SessionFactory  sessionFactory = configuration.buildSessionFactory();
		
		// 通过生成一个注册机对象，然后再生成SessionFactory
		/**
		 * 注意 : 需要调用applySettings方法，将hibernate配置设置，然后再构建
		 * 否则将抛以下异常:
		 * Access to DialectResolutionInfo cannot be null when 'hibernate.dialect' not set
		 */
		final ServiceRegistry registry = new StandardServiceRegistryBuilder()
		.applySettings(configuration.getProperties()).build();
		// 通过注册机对象为参数，生成SessionFactory
		final SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		return sessionFactory;
	}
	
	/**
	 * 
	 * 描述: 获取Hibernate配置对象
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public static Configuration getConfiguration(final String filePath) {
		final Configuration configuration = new Configuration();
		// 比属性文件方式，多了一步
		// 关键一步 : 调用 configure 方法，通过指定的xml文件来构建hibernate环境
		configuration.configure(filePath);
		
		// 代码中设置指定的属性
		// configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle9iDialect");
		
		// 使用默认的文件位置 classpath 根路径下
		// configuration.configure();
		return configuration;
	}
	
	/**
	 * 
	 * 描述: 通过注解的方式构建
	 * @author qye.zheng
	 * 
	 */
	public static void buildByAnnotation() {
		// 通过注解来构建hibernate环境
		final Configuration configuration = new AnnotationConfiguration();
		
		// 说明 : 由于 hibernat 和 jpa标准进行整合，因此配置方式构建已经过时
	}
	
	
	/**
	 * 
	 * 描述: 通过属性文件方式构建
	 * @author qye.zheng
	 * 
	 */
	public static void buildByProperties() {
		// 通过 hibernate.properties 文件来构建hibernate环境
		// 实例化 Configuration 对象
		final Configuration configuration = new Configuration();
		// 多次调用 addResource 方法，添加映射文件
		//configuration.addResource("conf/hibernate/hbm/xx.hbm.xml");
		
		/**
		 * 使用hibernate.properties文件配置Hibernate的属性固然简单，
		 * 但是因为要手动添加映射文件，当映射文件极其多时，
		 * 这是一件非常催人泪下的事情。
		 * 这也就是在实际开发中，不常使用hibernate.properties文件作为配置文件的原因。
		 */
		
		/**
		 * 当然还有另一种添加配置文件的策略，因为映射文件和持久化类是一一对应的，
		 * 可以通过Configuration对象来添加持久化类，让Hibernate自己来搜索映射文件。
		 */
		// 通过多次调用 addClass 方法，直接添加持久化类
		//configuration.addClass(Xx.class);
		
	}	
	
	
	

}
