/**
 * @filename MySqlTransaction.sql
 * @description MySql - 数据库
 * @author qye.zheng
 * @version 1.0
 */

/* ====================  ==================== */

/*  */

/* 创建数据库_示例 */
CREATE DATABASE `db_name` CHARACTER SET 'UTF8' COLLATE 'UTF8_general_ci';

/* 创建数据库_示例 */
CREATE DATABASE `good` CHARACTER SET 'UTF8' COLLATE 'UTF8_general_ci';

/* 使用数据库 */
use db_name;

/* 删除数据库 */
DROP DATABASE db_name;

/* ====================  ==================== */

/* 显示数据库 - 列表 */
show databases;

/* ====================  ==================== */

/* 查询当前数据库 事务的隔离级别 例如: REPEATABLE-READ */
select @@tx_isolation;

/* 开启事务 */
start transaction;

set autocommit=0
show variables like "%autocommit%";

/* 查询 全局事务隔离级别，就是一个默认值，会话断开重新连接则重置为此值 */
SELECT @@global.tx_isolation;
/* 查询 当前连接会话事务隔离级别 */
SELECT @@session.tx_isolation;
/* 查询 当前事务隔离级别 */
SELECT @@tx_isolation;

/* 设置当前会话事务隔离级别 */
SET SESSION TRANSACTION ISOLATION LEVEL read uncommitted;
SET SESSION TRANSACTION ISOLATION LEVEL read committed;
SET SESSION TRANSACTION ISOLATION LEVEL repeatable read;
SET SESSION TRANSACTION ISOLATION LEVEL serializable;

start transaction;


/* 查看正在被锁定的表 */
show OPEN TABLES where In_use > 0;


/* ====================  ==================== */

/* 场景实验 - 验证语句 */
/* 若存在先删除 */
DROP TABLE IF EXISTS `t_tx_test`;	
/* 创建表 事务测试表 */
CREATE TABLE t_tx_test(
	`ID` integer NOT NULL AUTO_INCREMENT comment '主键-自增长',
	`NAME` varchar(64) comment '名称',	
	`CREATE_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP() comment '创建时间',		
	 PRIMARY KEY (`ID`)		
)  ENGINE=InnoDB COMMENT '事务测试表' DEFAULT CHARSET=UTF8;

/* 初始化数据 */
INSERT INTO t_tx_test (name) VALUES ('张三');
INSERT INTO t_tx_test (name) VALUES ('李四');
INSERT INTO t_tx_test (name) VALUES ('张龙');
INSERT INTO t_tx_test (name) VALUES ('王威');

/*
use transaction; 
set autocommit=0;
SELECT @@tx_isolation;
*/
/* 场景一: Read Uncommitted 读取未提交内容
use transaction; // 使用指定的库
客户端A/客户端B 设置为不自动提交
set autocommit=0;
步骤1: 客户端A
查询当前事务隔离级别 SELECT @@tx_isolation;
设置当前连接的事务隔离级别: SET SESSION TRANSACTION ISOLATION LEVEL read uncommitted;
查询当前事务隔离级别 SELECT @@tx_isolation;
执行命令: start transaction;
查询数据: select * from t_tx_test;

步骤2: 客户端B
执行命令: start transaction;
更新数据: UPDATE t_tx_test SET name = '张三_01' WHERE ID = 1;
插入数据: INSERT INTO t_tx_test (name) VALUES ('猛虎团Read Uncommitted');
查询数据: select * from t_tx_test; (观察此时数据)

步骤3: 客户端A
查询数据: select * from t_tx_test; (观察此时数据，可以看到客户端B尚未提交的数据)

步骤4: 客户端B
执行命令: rollback; 回滚事务

步骤5: 客户端A
查询数据: select * from t_tx_test; (观察此时数据)
*/

/* 场景二: Read Committed 读取提交内容
use transaction; // 使用指定的库
客户端A/客户端B 设置为不自动提交
set autocommit=0;
步骤1: 客户端A
设置当前连接的事务隔离级别: SET SESSION TRANSACTION ISOLATION LEVEL read committed;
查询当前事务隔离级别 SELECT @@tx_isolation;
执行命令: start transaction;
查询数据: select * from t_tx_test;

步骤2: 客户端B
执行命令: start transaction;
更新数据: UPDATE t_tx_test SET name = '张三_02' WHERE ID = 1;
插入数据: INSERT INTO t_tx_test (name) VALUES ('猛虎团Read Committed');

步骤3: 客户端A
查询数据: select * from t_tx_test; (观察此时数据，可以看不到客户端B尚未提交事务的数据)

步骤4: 客户端B
执行命令: commit; 提交事务

步骤5: 客户端A
查询数据: select * from t_tx_test; (观察此时数据，2次查询的数据不一致)
*/

/* 场景三: Repeatable Read (可重复读)
use transaction; // 使用指定的库
客户端A/客户端B 设置为不自动提交
set autocommit=0;
步骤1: 客户端A
设置当前连接的事务隔离级别: SET SESSION TRANSACTION ISOLATION LEVEL repeatable read;
查询当前事务隔离级别 SELECT @@tx_isolation;
执行命令: start transaction;
查询数据: select * from t_tx_test;

步骤2: 客户端B
执行命令: start transaction;
插入数据: UPDATE t_tx_test SET name = '张三_03' WHERE ID = 1;
插入数据: INSERT INTO t_tx_test (name) VALUES ('猛虎团Repeatable Read');
执行命令: commit; 提交事务

步骤3: 客户端A
查询数据: select * from t_tx_test; (观察此时数据，可以看不到客户端B尚已经提交的数据，解决两次查询数据据不同问题)
*/

/* 场景四: Serializable (可串行化)
use transaction; // 使用指定的库
客户端A/客户端B 设置为不自动提交
set autocommit=0;
步骤1: 客户端A
设置当前连接的事务隔离级别: SET SESSION TRANSACTION ISOLATION LEVEL serializable;
查询当前事务隔离级别 SELECT @@tx_isolation;
执行命令: start transaction;
查询数据: select * from t_tx_test;

步骤2: 客户端B
执行命令: start transaction;
更新数据: UPDATE t_tx_test SET name = '张三_04' WHERE ID = 1;
插入数据: INSERT INTO t_tx_test (name) VALUES ('猛虎团 Serializable');
执行命令: commit; 提交事务，此时会报错，无法提交事务

步骤3: 客户端A
执行命令: commit; 提交事务

步骤4: 客户端B
更新数据: UPDATE t_tx_test SET name = '张三_05' WHERE ID = 1;
执行命令: commit; 再次提交事务，此时客户端A已经commit事务，客户端B可以成功提交事务.
*/

/* ====================  ==================== */



/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */


/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */	
	

/* ====================  ==================== */



/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */


/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */	
	

/* ====================  ==================== */



/* ====================  ==================== */

/*  */

/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */


/* ====================  ==================== */

/*  */



/* ====================  ==================== */

/*  */	
	

/* ====================  ==================== */



/* ====================  ==================== */

/*  */

