-- --------------------------------------------------------
-- 主机:                           10.13.7.43
-- 服务器版本:                        5.7.15-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 lab-manager 的数据库结构
CREATE DATABASE IF NOT EXISTS `lab-manager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `lab-manager`;

-- 导出  表 lab-manager.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(11) NOT NULL,
  `salt` varchar(8) NOT NULL,
  `passwd` varchar(32) NOT NULL,
  `identity` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.account 的数据：~16 rows (大约)
DELETE FROM `account`;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`id`, `salt`, `passwd`, `identity`) VALUES
	(4312432, '6je1d1pp', 'RQzk0dQXnWJtBBQFozt8YQ==', 2),
	(10000000000, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 3),
	(15310320108, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320109, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320110, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320111, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320112, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320113, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320114, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320115, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320116, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 1),
	(15310320117, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320118, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320119, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(15310320120, 'qmha7w9g', 'EmkXwUP69X4aZo/xQAP2vQ==', 0),
	(17184589456, '9nxls3zc', 'NaT8sKWzHMbbpzrtGXMxAg==', 2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;

-- 导出  表 lab-manager.course 结构
CREATE TABLE IF NOT EXISTS `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.course 的数据：~0 rows (大约)
DELETE FROM `course`;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
/*!40000 ALTER TABLE `course` ENABLE KEYS */;

-- 导出  表 lab-manager.lab 结构
CREATE TABLE IF NOT EXISTS `lab` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `lab_describe` text NOT NULL,
  `qq_group` bigint(11) DEFAULT NULL,
  `address` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.lab 的数据：~2 rows (大约)
DELETE FROM `lab`;
/*!40000 ALTER TABLE `lab` DISABLE KEYS */;
INSERT INTO `lab` (`id`, `name`, `lab_describe`, `qq_group`, `address`) VALUES
	(18, '云计算实验室', '45', 0, 'A7201'),
	(19, '随便', '收费', 0, '12345');
/*!40000 ALTER TABLE `lab` ENABLE KEYS */;

-- 导出  表 lab-manager.major 结构
CREATE TABLE IF NOT EXISTS `major` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.major 的数据：~24 rows (大约)
DELETE FROM `major`;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` (`id`, `name`) VALUES
	(4, '软件工程'),
	(5, '信息工程'),
	(6, '数据科学与大数据技术'),
	(7, '计算机科学与技术'),
	(8, '网络工程'),
	(9, '物联网工程'),
	(10, '软件技术'),
	(11, '信息安全和管理'),
	(12, '计算机网络技术'),
	(13, '信息管理与信息系统'),
	(14, '人力资源管理'),
	(15, '财务管理'),
	(16, '资产评估'),
	(17, '物流管理'),
	(18, '电子商务'),
	(19, '市场营销'),
	(20, '工业设计'),
	(21, '数字媒体技术'),
	(22, '动画'),
	(23, '产品艺术设计'),
	(24, '影视动画'),
	(25, '日语'),
	(26, '商务英语'),
	(27, '英语');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;

-- 导出  表 lab-manager.notice 结构
CREATE TABLE IF NOT EXISTS `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lab_id` int(3) NOT NULL,
  `publisher` bigint(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '发布者名字',
  `time` datetime NOT NULL,
  `title` varchar(30) NOT NULL,
  `content` text NOT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  `file_path` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`lab_id`) USING BTREE,
  KEY `FK_Reference_14` (`publisher`) USING BTREE,
  CONSTRAINT `notice_ibfk_1` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notice_ibfk_2` FOREIGN KEY (`publisher`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.notice 的数据：~3 rows (大约)
DELETE FROM `notice`;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` (`id`, `lab_id`, `publisher`, `name`, `time`, `title`, `content`, `file_name`, `file_path`) VALUES
	(61, 18, 10000000000, '系统管理员', '2017-11-17 00:00:00', '发给谁', 'GRE', '', '2017-11-17/2017-11-17 14-20-47'),
	(62, 18, 10000000000, '系统管理员', '2017-11-17 00:00:00', '有附件', 'GRE', '237-mp3-1.mp3', '2017-11-17/2017-11-17 14-21-00'),
	(63, 18, 10000000000, '系统管理员', '2017-11-17 00:00:00', '测试', '测试触发器', '', '2017-11-17/2017-11-17 15-20-32');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;

-- 导出  表 lab-manager.prize 结构
CREATE TABLE IF NOT EXISTS `prize` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` bigint(11) NOT NULL,
  `lab_id` int(3) NOT NULL,
  `time` date NOT NULL,
  `prize_name` varchar(60) NOT NULL,
  `url` varchar(50) DEFAULT NULL,
  `category` varchar(20) DEFAULT NULL,
  `region` varchar(15) NOT NULL COMMENT '比赛级别',
  `rank` varchar(10) NOT NULL COMMENT '获奖等级',
  `is_checked` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0待审核，1已审核',
  `committee` varchar(30) DEFAULT NULL COMMENT '审核者',
  `adviser` varchar(10) DEFAULT NULL COMMENT '指导老师',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_9` (`owner`) USING BTREE,
  CONSTRAINT `prize_ibfk_1` FOREIGN KEY (`owner`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.prize 的数据：~0 rows (大约)
DELETE FROM `prize`;
/*!40000 ALTER TABLE `prize` DISABLE KEYS */;
/*!40000 ALTER TABLE `prize` ENABLE KEYS */;

-- 导出  表 lab-manager.relationship 结构
CREATE TABLE IF NOT EXISTS `relationship` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lab_id` int(3) NOT NULL,
  `teacher_id` bigint(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`lab_id`) USING BTREE,
  KEY `FK_Reference_8` (`teacher_id`) USING BTREE,
  CONSTRAINT `relationship_ibfk_1` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `relationship_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.relationship 的数据：~2 rows (大约)
DELETE FROM `relationship`;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;
INSERT INTO `relationship` (`id`, `lab_id`, `teacher_id`) VALUES
	(9, 18, 17184589456),
	(10, 19, 4312432);
/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;

-- 导出  表 lab-manager.score 结构
CREATE TABLE IF NOT EXISTS `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `grade` int(11) NOT NULL,
  `major` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `term` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`stu_id`) USING BTREE,
  KEY `FK_Reference_4` (`major`) USING BTREE,
  KEY `FK_Reference_5` (`course`) USING BTREE,
  CONSTRAINT `score_ibfk_1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_2` FOREIGN KEY (`major`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `score_ibfk_3` FOREIGN KEY (`course`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.score 的数据：~0 rows (大约)
DELETE FROM `score`;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
/*!40000 ALTER TABLE `score` ENABLE KEYS */;

-- 导出  表 lab-manager.signed 结构
CREATE TABLE IF NOT EXISTS `signed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` bigint(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `register` tinyint(1) NOT NULL,
  `lab_id` int(3) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_11` (`lab_id`) USING BTREE,
  KEY `FK_Reference_6` (`stu_id`) USING BTREE,
  CONSTRAINT `signed_ibfk_1` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `signed_ibfk_2` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.signed 的数据：~3 rows (大约)
DELETE FROM `signed`;
/*!40000 ALTER TABLE `signed` DISABLE KEYS */;
INSERT INTO `signed` (`id`, `stu_id`, `date`, `register`, `lab_id`) VALUES
	(6, 15310320114, '2017-11-27 09:35:07', 1, 18),
	(7, 15310320118, '2017-11-29 06:33:02', 1, 18),
	(8, 15310320118, '2017-11-29 14:45:07', 1, 18);
/*!40000 ALTER TABLE `signed` ENABLE KEYS */;

-- 导出  表 lab-manager.student 结构
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` tinyint(1) DEFAULT NULL COMMENT '0男，1女',
  `grade` int(4) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `lab_id` int(3) DEFAULT NULL,
  `maj_id` int(11) DEFAULT NULL,
  `tel` bigint(11) DEFAULT NULL,
  `instructor` varchar(15) DEFAULT NULL,
  `IDcard` varchar(20) DEFAULT NULL,
  `stuClass` int(2) DEFAULT NULL,
  `department` varchar(10) DEFAULT NULL,
  `outTime` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`lab_id`) USING BTREE,
  KEY `FK_Reference_13` (`maj_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`maj_id`) REFERENCES `major` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.student 的数据：~13 rows (大约)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `name`, `gender`, `grade`, `time`, `lab_id`, `maj_id`, `tel`, `instructor`, `IDcard`, `stuClass`, `department`, `outTime`) VALUES
	(15310320108, '学生12', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320109, '学生13', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320110, '学生14', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320111, '学生15', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320112, '学生16', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320113, '学生17', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320114, '学生18', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320115, '学生19', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320116, '墨水20', 0, 2015, '2017-11-20', 18, 4, 18161202825, '但是额', '510322199902067818', 15201, '计算机科学与技术系', NULL),
	(15310320117, '学生21', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320118, '学生22', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320119, '学生23', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
	(15310320120, '学生24', NULL, NULL, NULL, 18, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- 导出  表 lab-manager.teacher 结构
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` bigint(11) NOT NULL,
  `name` varchar(15) NOT NULL,
  `gender` tinyint(1) DEFAULT NULL COMMENT '0男，1女',
  `tel` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  lab-manager.teacher 的数据：~2 rows (大约)
DELETE FROM `teacher`;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`id`, `name`, `gender`, `tel`) VALUES
	(4312432, '2352', 0, 4312432),
	(17184589456, '罗频捷', 0, 17184589456);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
