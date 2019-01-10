/*
Navicat MySQL Data Transfer

Source Server         : gata
Source Server Version : 50505
Source Host           : www.tyzn-tech.com:3306
Source Database       : gate

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-03-21 09:06:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `contactUs`
-- ----------------------------
DROP TABLE IF EXISTS `contactUs`;
CREATE TABLE `contactUs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) COLLATE utf8_bin NOT NULL,
  `contact_name1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_way1` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_name2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_way2` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_name3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_way3` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_name4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_way4` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_name5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `contact_way5` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='联系我们';

-- ----------------------------
-- Records of contactUs
-- ----------------------------
INSERT INTO `contactUs` VALUES ('18', '联系我们', 'QQ', '123456789', '邮箱', '1234516@.com', '电话', '123456789', '联系名字', '联系方式66', '联系名字', '联系方式');
INSERT INTO `contactUs` VALUES ('30', '阿道夫', '测试', '4567', '的萨芬', '阿斯蒂芬', '', '', '', '', '', '');
INSERT INTO `contactUs` VALUES ('31', 'adf', 'daf', '', '', '', 'dasf', 'adsf', 'dfas', 'adf', '', '');
INSERT INTO `contactUs` VALUES ('40', '123123', '123', '123', '123', '', '', '', '', '', '', '');
INSERT INTO `contactUs` VALUES ('41', '123', '123', '123', '', '', '', '', '', '', '', '');

-- ----------------------------
-- Table structure for `cooperator`
-- ----------------------------
DROP TABLE IF EXISTS `cooperator`;
CREATE TABLE `cooperator` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `co_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '合作单位名',
  `co_url` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `co_content` text COLLATE utf8mb4_unicode_ci,
  `co_date` datetime DEFAULT NULL,
  `co_status` int(11) DEFAULT NULL COMMENT '1有效，0无效',
  PRIMARY KEY (`co_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of cooperator
-- ----------------------------
INSERT INTO `cooperator` VALUES ('1', '富邦股份', '/coop.html?id=1', '测试1', '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('2', '上海恒光', '/coop.html?id=2', '<p style=\"text-indent: 2em;\">测试，这一次是测试修改</p>', '2018-03-20 22:09:54', '1');
INSERT INTO `cooperator` VALUES ('3', '杭州炽橙', '/coop.html?id=3', '测试2dfd', '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('4', '国家超算深圳中兴', '/coop.html?id=4', null, '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('5', '奥普天成', '/coop.html?id=5', null, '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('6', '成都东软', '/coop.html?id=6', null, '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('7', '西安航空', '/coop.html?id=7', '', '2018-03-02 20:20:45', '1');
INSERT INTO `cooperator` VALUES ('8', '沈阳农大', '/coop.html?id=8', null, '2018-03-02 20:20:45', '1');

-- ----------------------------
-- Table structure for `html`
-- ----------------------------
DROP TABLE IF EXISTS `html`;
CREATE TABLE `html` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COLLATE utf8_bin NOT NULL COMMENT 'html',
  `type` int(2) NOT NULL DEFAULT '1' COMMENT '类型，1总体介绍，2党建，3工会',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `owner` int(1) NOT NULL COMMENT '所属类型，1党建工会，2开源数据，3招贤纳士，4走近大江东，5关于我们',
  `flag` int(1) DEFAULT '1' COMMENT '0旧记录，1新纪录',
  `url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '访问此条记录的url，只有部分',
  `title` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `news_center`
-- ----------------------------
DROP TABLE IF EXISTS `news_center`;
CREATE TABLE `news_center` (
  `nc_id` int(11) NOT NULL AUTO_INCREMENT,
  `nc_title` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '新闻标题',
  `nc_content` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '新闻内容',
  `nc_date` datetime NOT NULL COMMENT '新闻日期',
  `nc_type` int(1) NOT NULL COMMENT '新闻类型 1-综合新闻 ，2-科研动态，3-人才招聘',
  `nc_flag` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`nc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pr_id` int(11) NOT NULL AUTO_INCREMENT,
  `pr_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '产品名',
  `pr_html` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '产品介绍',
  `pr_photo` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '产品图片路径',
  `pr_set` int(11) NOT NULL,
  `pr_flag` int(11) NOT NULL COMMENT '产品的有效性 0：无效  1：有效',
  `pr_date` datetime DEFAULT NULL,
  PRIMARY KEY (`pr_id`),
  KEY `FK_product_product_set` (`pr_set`),
  CONSTRAINT `FK_product_product_set` FOREIGN KEY (`pr_set`) REFERENCES `product_set` (`ps_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for `product_set`
-- ----------------------------
DROP TABLE IF EXISTS `product_set`;
CREATE TABLE `product_set` (
  `ps_id` int(11) NOT NULL AUTO_INCREMENT,
  `ps_name` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ps_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='产品系列';

-- ----------------------------
-- Records of product_set
-- ----------------------------
INSERT INTO `product_set` VALUES ('1', '三维模型数据');
INSERT INTO `product_set` VALUES ('2', '光谱系列');
INSERT INTO `product_set` VALUES ('3', '无人机载荷系列');
INSERT INTO `product_set` VALUES ('4', '热红外系列');
INSERT INTO `product_set` VALUES ('12', '新系列');

-- ----------------------------
-- Table structure for `technical`
-- ----------------------------
DROP TABLE IF EXISTS `technical`;
CREATE TABLE `technical` (
  `te_id` int(11) NOT NULL AUTO_INCREMENT,
  `te_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '技术名',
  `te_html` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '技术介绍',
  `te_set` int(11) DEFAULT '0',
  `te_flag` int(11) NOT NULL COMMENT '技术的有效性 0：无效 1；有效',
  `te_date` datetime DEFAULT NULL,
  PRIMARY KEY (`te_id`),
  KEY `FK_technical_technical_set` (`te_set`),
  CONSTRAINT `FK_technical_technical_set` FOREIGN KEY (`te_set`) REFERENCES `technical_set` (`ts_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for `technical_set`
-- ----------------------------
DROP TABLE IF EXISTS `technical_set`;
CREATE TABLE `technical_set` (
  `ts_id` int(11) NOT NULL AUTO_INCREMENT,
  `ts_name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  PRIMARY KEY (`ts_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='技术分类';

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `password` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `salt` varchar(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- View structure for `max_create_time`
-- ----------------------------
DROP VIEW IF EXISTS `max_create_time`;
CREATE ALGORITHM=UNDEFINED DEFINER=`gate`@`%` SQL SECURITY DEFINER VIEW `max_create_time` AS select max(`html`.`create_time`) AS `create_time`,`html`.`type` AS `type`,`html`.`owner` AS `owner` from `html` group by `html`.`type`,`html`.`owner` ;
DROP TRIGGER IF EXISTS `set_title`;
DELIMITER ;;
CREATE TRIGGER `set_title` BEFORE INSERT ON `html` FOR EACH ROW BEGIN
	IF NEW.owner = 1 THEN
		IF NEW.type = 1 THEN
			SET NEW.title = '党群工会-党建';
		ELSEIF NEW.type = 2 THEN
			SET NEW.title = '党群工会-工会';
		END IF;
	ELSEIF new.owner = 2 THEN
		IF NEW.type = 1 THEN
			SET NEW.title = '开源数据-光学';
		ELSEIF NEW.type = 2 THEN
			SET NEW.title = '开源数据-电子学';
		ELSEIF NEW.type = 3 THEN
			SET NEW.title = '开源数据-机械';
		END IF;
	ELSEIF new.owner = 3 THEN
		IF NEW.type = 1 THEN
			SET NEW.title = '招贤纳士-硬件';
		ELSEIF NEW.type = 2 THEN
			SET NEW.title = '招贤纳士-软件';
		ELSEIF NEW.type = 3 THEN
			SET NEW.title = '招贤纳士-机械';
		ELSEIF NEW.type = 4 THEN
			SET NEW.title = '招贤纳士-其他';
		END IF;
	ELSEIF new.owner = 4 THEN
		IF NEW.type = 1 THEN
			SET NEW.title = '走近大江东-人才引进政策';
		ELSEIF NEW.type = 2 THEN
			SET NEW.title = '走进大江东-区域规划';
		END IF;
	ELSEIF new.owner = 5 THEN
		IF NEW.type = 1 THEN
			SET NEW.title = '关于我们-本部介绍';
		ELSEIF NEW.type = 2 THEN
			SET NEW.title = '关于我们-研究院介绍';
		ELSEIF NEW.type = 3 THEN
			SET NEW.title = '关于我们-组织架构';
		END IF;
	END IF;
END
;;
DELIMITER ;
