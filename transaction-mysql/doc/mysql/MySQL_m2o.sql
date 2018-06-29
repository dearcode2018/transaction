/* MySQL */

/* many to one */

/* 创建表 */
DROP TABLE IF EXISTS custom;
CREATE TABLE custom (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	name varchar(64) COMMENT '客户姓名',
	balance numeric(9, 2) COMMENT '帐户余额',
	address varchar(255) COMMENT '客户状态 (0-无效, 1-未激活, 2-正常)',
	status numeric(2) COMMENT '地址',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

/* 添加数据 */
INSERT INTO custom (name, balance, status, address) VALUES ('徐明', 254.21, 1, '新疆自治区乌鲁木齐市五山区天长路22号');

INSERT INTO custom (name, balance, status, address) VALUES ('王洁', 6942.47, 0, '广东省广州市天河区平云路11号');

INSERT INTO custom (name, balance, status, address) VALUES ('邝边', 0.00, 2, '江西省上饶市天水区青山路19号');

INSERT INTO custom (name, balance, status, address) VALUES ('刘雨', 221.01, 1, '广西省玉林市玉山区广平路13号');

INSERT INTO custom (name, balance, status, address) VALUES ('赵备', 10.15, 2, '广东省广州市越秀区清水路17号');

INSERT INTO custom (name, balance, status, address) VALUES ('卡扎尔-龙华', 19.11, 2, '西藏自治区拉萨市东口区江北路24号');

INSERT INTO custom (name, balance, status, address) VALUES ('牛芳', 294.41, 0, '云南省省昆明市昆山区海平路46号');

INSERT INTO custom (name, balance, status, address) VALUES ('伊尔民', 358.71, 0, '江苏省苏州市苏南区南江路89号');

INSERT INTO custom (name, balance, status, address) VALUES ('沙尔塔', 124.01, 1, '四川省成都市天都区天府路463号');

INSERT INTO custom (name, balance, status, address) VALUES ('郑飞', 239.10, 1, '陕西省西安市汉水区西北路104号');


/* 变更表结构: 新增字段 */
ALTER TABLE custom ADD COLUMN remark varchar(200) comment '备注';

/* 变更表结构: 修改字段 */
ALTER TABLE custom MODIFY COLUMN name varchar(100) comment '客户姓名';

/* 创建表 */
DROP TABLE IF EXISTS item;
CREATE TABLE item (
	`id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
	name varchar(64) COMMENT '定单名称',
	orderTs timestamp COMMENT '下单时间戳 yyyy-MM-dd HH:mm:ss',
	monetary numeric(7, 2) COMMENT '消费金额',
	remark varchar(64) COMMENT '备注',
	customId numeric COMMENT '客户id',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/* 添加约束 */
/* ALTER TABLE item ADD CONSTRAINT fk_item_studentId FOREIGN KEY(id) REFERENCES custom(id); */

INSERT INTO item (name, orderTs, monetary, remark, customId) VALUES ('订单1.1', '2015-02-25 12:30:25', 20.66, '刷卡消费', 1);
INSERT INTO item (name, orderTs, monetary, remark, customId) VALUES ('订单1.2', '2015-05-26 16:30:16', 653.2, '现金消费', 1);
INSERT INTO item (name, orderTs, monetary, remark, customId) VALUES ('订单2.1', '2015-01-27 21:30:35', 236.56, '刷卡消费', 2);
INSERT INTO item (name, orderTs, monetary, remark, customId) VALUES ('订单3.1', '2015-05-30 16:30:16', 10.29, '现金消费', 3);


