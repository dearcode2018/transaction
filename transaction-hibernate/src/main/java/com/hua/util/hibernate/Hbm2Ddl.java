/**
 * 描述: 
 * Hbm2Ddl.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package com.hua.util.hibernate;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

/**
 * 描述: 
 * 
 * @author qye.zheng
 * Hbm2Ddl
 */
public final class Hbm2Ddl {
	
	/**
	 * 构造方法
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	private Hbm2Ddl()
	{
	}
	
	/**
	 * 
	 * 描述: 打印输出ddl脚本语句
	 * @author qye.zheng
	 * 
	 */
	public static void printDdl() {
		// 根据 cfg 和 映射文件，将ddl脚本输出到控制台
		schemaExport(true, false);
	}
	
	/**
	 * 
	 * 描述: 将dd脚本传输到数据库服务器执行
	 * @author qye.zheng
	 * 
	 */
	public static void exportToDb() {
		//
		schemaExport(false, true);
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @param script 是否输出到控制台
	 * @param export 是否传送到数据库执行
	 */
	public static void schemaExport(final boolean script, final boolean export) {
		// 获取Hibernate配置对象
		final Configuration configuration = HibernateUtil.getConfiguration();
		// 创建 模式导出 对象
		final SchemaExport schemaExport = new SchemaExport(configuration);
		// 执行创建任务
		schemaExport.create(script, export);
	}
	
}
