-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.15 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 ccl_oes 的数据库结构
CREATE DATABASE IF NOT EXISTS `ccl_oes` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `ccl_oes`;

-- 导出  表 ccl_oes.administrator 结构
CREATE TABLE IF NOT EXISTS `administrator` (
  `adminId` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '管理员登陆账号',
  `adminPassword` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '管理员登陆密码',
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储管理员账号，管理员可以对教师进行增删查改，可以对系部进行增删查改';

-- 正在导出表  ccl_oes.administrator 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` (`adminId`, `adminPassword`) VALUES
	('admin', 'e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;

-- 导出  表 ccl_oes.answer 结构
CREATE TABLE IF NOT EXISTS `answer` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '答案表编号',
  `ExamId` int(11) DEFAULT NULL COMMENT '考试编号',
  `StudentId` varchar(20) DEFAULT NULL COMMENT '学生编号',
  `QuestionId` varchar(50) DEFAULT NULL COMMENT '题目编号',
  `AnswerRel` varchar(50) DEFAULT NULL COMMENT '真实答案',
  `AnswerStudent` varchar(2000) DEFAULT '[]' COMMENT '学生答案',
  `Score` float DEFAULT NULL COMMENT '分数',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=237733 DEFAULT CHARSET=utf8 COMMENT='答案表';

-- 正在导出表  ccl_oes.answer 的数据：~19 rows (大约)
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` (`Id`, `ExamId`, `StudentId`, `QuestionId`, `AnswerRel`, `AnswerStudent`, `Score`) VALUES
	(2, 123456, '15310320108', '123456', NULL, '["123","广州"]', NULL),
	(237713, 939, '15310320108', '23', '[]', '[""]', NULL),
	(237714, 939, '15310320108', '1', '[]', '[""]', NULL),
	(237715, 939, '15310320108', '32', '你猜', '[""]', NULL),
	(237716, 939, '15310320108', '4', '[]', '[]', NULL),
	(237717, 939, '15310320108', '39', '知道', '[""]', NULL),
	(237718, 939, '15310320108', '47', '[]', '[]', NULL),
	(237719, 939, '15310320108', '54', '你猜', '[""]', NULL),
	(237720, 939, '15310320108', '65', '不知道', '[""]', NULL),
	(237721, 939, '15310320108', '44', '[]', '[""]', NULL),
	(237722, 939, '15310320108', '106', '知道', '["知道","的说法"]', NULL),
	(237723, 939, '15310320108', '75', '对', NULL, NULL),
	(237724, 939, '15310320108', '108', '[]', '[""]', NULL),
	(237725, 939, '15310320108', '89', '错', '[]', NULL),
	(237726, 939, '15310320108', '110', '对', '["dghshf是大法官fg","是大法官"]', NULL),
	(237727, 939, '15310320108', '94', '错', NULL, NULL),
	(237728, 939, '15310320108', '100', '对', '[]', NULL),
	(237729, 939, '15310320108', '120', '[]', '[""]', NULL),
	(237730, 939, '15310320108', '121', '[]', '["sddsf 电饭锅"]', NULL),
	(237731, 939, '15310320108', '122', '对', '["dfgdfg的说法"]', NULL),
	(237732, 939, '15310320108', '123', '[]', '[""]', NULL);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;

-- 导出  表 ccl_oes.department 结构
CREATE TABLE IF NOT EXISTS `department` (
  `departId` int(11) NOT NULL AUTO_INCREMENT COMMENT '系部编号',
  `departName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '系部名称',
  PRIMARY KEY (`departId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储系部信息，管理员可对其进行修改';

-- 正在导出表  ccl_oes.department 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`departId`, `departName`) VALUES
	(1, '计算机科学与技术系'),
	(2, '信息与财务管理系'),
	(3, '应用外语系'),
	(4, '数字媒体艺术系'),
	(5, '基础部');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;

-- 导出  表 ccl_oes.exam 结构
CREATE TABLE IF NOT EXISTS `exam` (
  `examId` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `libraryId` int(11) NOT NULL COMMENT '题库编号',
  `teacherUsername` varchar(50) COLLATE utf8_bin NOT NULL,
  `examName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '考试名，可以重复',
  `keyword` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录口令，在考生选择考试之后使用登陆口令验证登录',
  `startTime` datetime NOT NULL COMMENT '考试开始时间',
  `endTime` datetime NOT NULL COMMENT '考试结束时间',
  `sChoice` varchar(50) COLLATE utf8_bin NOT NULL,
  `mChoice` varchar(50) COLLATE utf8_bin NOT NULL,
  `tofQuestion` varchar(50) COLLATE utf8_bin NOT NULL,
  `sScore` varchar(50) COLLATE utf8_bin NOT NULL,
  `mScore` varchar(50) COLLATE utf8_bin NOT NULL,
  `tofScore` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`examId`),
  KEY `FK_Exam_QuestionMapping` (`libraryId`),
  KEY `FK2_Exam_Teacher` (`teacherUsername`),
  CONSTRAINT `FK2_Exam_Teacher` FOREIGN KEY (`teacherUsername`) REFERENCES `teacher` (`teacherUsername`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Exam_QuestionMapping` FOREIGN KEY (`libraryId`) REFERENCES `questionmapping` (`libraryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=940 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储每次考试信息，每场考试存储一条数据';

-- 正在导出表  ccl_oes.exam 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `exam` DISABLE KEYS */;
INSERT INTO `exam` (`examId`, `libraryId`, `teacherUsername`, `examName`, `keyword`, `startTime`, `endTime`, `sChoice`, `mChoice`, `tofQuestion`, `sScore`, `mScore`, `tofScore`) VALUES
	(920, 2, 'zhongmei@nsu.edu.cn', '数据结构期末测试233', '', '2016-09-08 23:00:00', '2016-09-10 04:58:00', '2', '2', '2', '2', '2', '2'),
	(921, 2, 'zhongmei@nsu.edu.cn', '数据结构期末测试', '', '2016-09-08 23:00:00', '2016-09-10 04:58:00', '2', '2', '2', '2', '2', '2'),
	(928, 2, 'zhongmei@nsu.edu.cn', '测试5', '', '2016-09-08 20:00:00', '2016-09-08 21:00:00', '2', '2', '2', '3', '2', '2'),
	(933, 8086, 'lishan@nsu.edu.cn', '16555', 'T2WGBL', '2016-09-27 00:00:00', '2016-12-27 23:00:00', '5', '4', '2', '4', '3', '2'),
	(939, 8086, 'lishan@nsu.edu.cn', '绝对可怜小孩', 'USPZWJ', '2016-09-28 09:00:00', '2017-12-28 23:00:00', '8', '9', '5', '4', '10', '5');
/*!40000 ALTER TABLE `exam` ENABLE KEYS */;

-- 导出  表 ccl_oes.exam_copy 结构
CREATE TABLE IF NOT EXISTS `exam_copy` (
  `examId` int(11) NOT NULL AUTO_INCREMENT COMMENT '考试编号',
  `libraryId` int(11) NOT NULL COMMENT '题库编号',
  `teacherUsername` varchar(50) COLLATE utf8_bin NOT NULL,
  `examName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '考试名，可以重复',
  `keyword` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '登录口令，在考生选择考试之后使用登陆口令验证登录',
  `startTime` datetime NOT NULL COMMENT '考试开始时间',
  `endTime` datetime NOT NULL COMMENT '考试结束时间',
  `sChoice` varchar(50) COLLATE utf8_bin NOT NULL,
  `mChoice` varchar(50) COLLATE utf8_bin NOT NULL,
  `tofQuestion` varchar(50) COLLATE utf8_bin NOT NULL,
  `sScore` varchar(50) COLLATE utf8_bin NOT NULL,
  `mScore` varchar(50) COLLATE utf8_bin NOT NULL,
  `tofScore` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`examId`),
  KEY `FK_Exam_QuestionMapping` (`libraryId`),
  KEY `FK2_Exam_Teacher` (`teacherUsername`),
  CONSTRAINT `Exam_copy_ibfk_1` FOREIGN KEY (`teacherUsername`) REFERENCES `teacher` (`teacherUsername`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Exam_copy_ibfk_2` FOREIGN KEY (`libraryId`) REFERENCES `questionmapping` (`libraryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=934 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储每次考试信息，每场考试存储一条数据';

-- 正在导出表  ccl_oes.exam_copy 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `exam_copy` DISABLE KEYS */;
INSERT INTO `exam_copy` (`examId`, `libraryId`, `teacherUsername`, `examName`, `keyword`, `startTime`, `endTime`, `sChoice`, `mChoice`, `tofQuestion`, `sScore`, `mScore`, `tofScore`) VALUES
	(920, 2, 'zhongmei@nsu.edu.cn', '数据结构期末测试233', '', '2016-09-08 23:00:00', '2016-09-10 04:58:00', '2', '2', '2', '2', '2', '2'),
	(921, 2, 'zhongmei@nsu.edu.cn', '数据结构期末测试', '', '2016-09-08 23:00:00', '2016-09-10 04:58:00', '2', '2', '2', '2', '2', '2'),
	(928, 2, 'zhongmei@nsu.edu.cn', '测试5', '', '2016-09-08 20:00:00', '2016-09-08 21:00:00', '2', '2', '2', '3', '2', '2'),
	(932, 8086, 'lishan@nsu.edu.cn', '绝对可怜小孩', 'USPZWJ', '2016-09-28 09:00:00', '2016-12-28 23:00:00', '8', '9', '5', '4', '10', '5'),
	(933, 8086, 'lishan@nsu.edu.cn', '16555', 'T2WGBL', '2016-09-27 00:00:00', '2016-12-27 23:00:00', '5', '4', '2', '4', '3', '2');
/*!40000 ALTER TABLE `exam_copy` ENABLE KEYS */;

-- 导出  表 ccl_oes.questionlist 结构
CREATE TABLE IF NOT EXISTS `questionlist` (
  `questionId` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '题目编号，由web端传入，可能重复',
  `libraryId` int(11) NOT NULL COMMENT '对应的题库编号',
  `question` text COLLATE utf8_bin NOT NULL COMMENT '题目内容，不包括选项和正确答案',
  `choice` varchar(1500) COLLATE utf8_bin NOT NULL COMMENT '备选答案，用分隔符分开每个选项，全部放到这一个字段里',
  `answer` varchar(1500) COLLATE utf8_bin DEFAULT NULL COMMENT '参考答案，如果是多选就用分隔符分开',
  `types` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '题目类型',
  KEY `FK_QuestionList_QuestionMapping` (`libraryId`),
  CONSTRAINT `FK_QuestionList_QuestionMapping` FOREIGN KEY (`libraryId`) REFERENCES `questionmapping` (`libraryId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='题库表，保存所有老师导入的所有题目，每个题目一行。';

-- 正在导出表  ccl_oes.questionlist 的数据：~118 rows (大约)
/*!40000 ALTER TABLE `questionlist` DISABLE KEYS */;
INSERT INTO `questionlist` (`questionId`, `libraryId`, `question`, `choice`, `answer`, `types`) VALUES
	('16', 673, '知道不', 'insert##select##into##where', 'select', '单选题'),
	('2', 673, '不知道', 'inkbox##king miss king##圣魔之血##冰雪奇缘', '冰雪奇缘', '多选题'),
	('3', 673, '终结的炽天使', ' 对##错', '对', '判断题'),
	('1', 676, '知道不', 'insert##select##into##where', 'select', '单选题'),
	('2', 676, '不知道', 'inkbox##king miss king##圣魔之血##冰雪奇缘', '冰雪奇缘', '多选题'),
	('3', 676, '终结的炽天使', ' 对##错', '对', '判断题'),
	('1', 2, '这是数据结构题库的第一题', '选项A##选项B##选项C##选项D', '选项C', '单选题'),
	('2', 2, '这是数据结构题库的第二题', '选项A##选项B##选项C##选项D', '选项B', '单选题'),
	('3', 2, '这是数据结构题库的第三题', '选项A##选项B##选项C##选项D', '选项C', '单选题'),
	('1', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('2', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('3', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('4', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('5', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '单选题'),
	('6', 8086, '知道了嘛', '知道##不知道##你猜##知道', '知道', '单选题'),
	('7', 8086, '知道了嘛', '知道##不知道##你猜##知道', '知道', '单选题'),
	('8', 8086, '知道了嘛', '不知道##不知道##你猜##知道', '不知道', '单选题'),
	('9', 8086, '知道了嘛', '不知道##不知道##你猜##知道', '你猜', '单选题'),
	('10', 8086, '知道了嘛', '不知道##不知道##你猜##不告诉你', '你猜', '单选题'),
	('11', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '你猜', '单选题'),
	('12', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('13', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('14', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('15', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('16', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '单选题'),
	('17', 8086, '知道了嘛', '知道##你猜##你猜##不告诉你', '你猜', '单选题'),
	('18', 8086, '知道了嘛', '知道##你猜##你猜##不告诉你', '你猜', '单选题'),
	('19', 8086, '知道了嘛', '不知道##你猜##知道##不告诉你', '你猜', '单选题'),
	('20', 8086, '知道了嘛', '不知道##你猜##不告诉你##你猜', '不告诉你', '单选题'),
	('21', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '单选题'),
	('22', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '单选题'),
	('23', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '单选题'),
	('24', 8086, '知道了嘛', '知道##不知道##不告诉你##你猜', '不知道', '单选题'),
	('25', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '单选题'),
	('26', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '单选题'),
	('27', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '单选题'),
	('28', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '单选题'),
	('29', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '单选题'),
	('30', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '不告诉你', '单选题'),
	('31', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '单选题'),
	('32', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '单选题'),
	('33', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '单选题'),
	('34', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('35', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '单选题'),
	('36', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '单选题'),
	('37', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '单选题'),
	('38', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('39', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('40', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('41', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('42', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '多选题'),
	('43', 8086, '知道了嘛', '知道##不知道##你猜##知道', '知道', '多选题'),
	('44', 8086, '知道了嘛', '知道##不知道##你猜##知道', '知道', '多选题'),
	('45', 8086, '知道了嘛', '不知道##不知道##你猜##知道', '不知道', '多选题'),
	('46', 8086, '知道了嘛', '不知道##不知道##你猜##知道', '你猜', '多选题'),
	('47', 8086, '知道了嘛', '不知道##不知道##你猜##不告诉你', '你猜', '多选题'),
	('48', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '你猜', '多选题'),
	('49', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('50', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('51', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('52', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('53', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '多选题'),
	('54', 8086, '知道了嘛', '知道##你猜##你猜##不告诉你', '你猜', '多选题'),
	('55', 8086, '知道了嘛', '知道##你猜##你猜##不告诉你', '你猜', '多选题'),
	('56', 8086, '知道了嘛', '不知道##你猜##知道##不告诉你', '你猜', '多选题'),
	('57', 8086, '知道了嘛', '不知道##你猜##不告诉你##你猜', '不告诉你', '多选题'),
	('58', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '多选题'),
	('59', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '多选题'),
	('60', 8086, '知道了嘛', '不知道##知道##不告诉你##你猜', '不告诉你', '多选题'),
	('61', 8086, '知道了嘛', '知道##不知道##不告诉你##你猜', '不知道', '多选题'),
	('62', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '多选题'),
	('63', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '多选题'),
	('64', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '多选题'),
	('65', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不知道', '多选题'),
	('66', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '多选题'),
	('67', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '不告诉你', '多选题'),
	('68', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '多选题'),
	('69', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '多选题'),
	('70', 8086, '知道了嘛', '知道##你猜##不知道##不告诉你', '你猜', '多选题'),
	('71', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('72', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '知道', '多选题'),
	('73', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '多选题'),
	('74', 8086, '知道了嘛', '知道##不知道##你猜##不告诉你', '不告诉你', '多选题'),
	('75', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('76', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('77', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('78', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('79', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('80', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('81', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('82', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('83', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('84', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('85', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('86', 8086, '懂不懂', ' 对##错', '错', '判断题'),
	('87', 8086, '知道不知道', ' 对##错', '错', '判断题'),
	('88', 8086, '知道不知道', ' 对##错', '错', '判断题'),
	('89', 8086, '知道不知道', ' 对##错', '错', '判断题'),
	('90', 8086, '知道不知道', ' 对##错', '错', '判断题'),
	('91', 8086, '知道不知道', ' 对##错', '错', '判断题'),
	('92', 8086, '懂不懂', ' 对##错', '错', '判断题'),
	('93', 8086, '懂不懂', ' 对##错', '错', '判断题'),
	('94', 8086, '懂不懂', ' 对##错', '错', '判断题'),
	('95', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('96', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('97', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('98', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('99', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('100', 8086, '懂不懂', ' 对##错', '对', '判断题'),
	('101', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('102', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('103', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('104', 8086, '知道不知道', ' 对##错', '对', '判断题'),
	('1', 8087, '知道不', 'insert##select##into##where', 'select', '单选题'),
	('3', 8087, '终结的炽天使', ' 对##错', '对', '判断题'),
	('105', 8086, '辛亥革命于##年,##地方爆发', ' ', '', '填空题'),
	('106', 8086, '辛亥革命于##年,##地方2', ' ', '', '填空题'),
	('2', 8087, '不知道', 'inkbox##king miss king##圣魔之血##冰雪奇缘', '冰雪奇缘', '多选题'),
	('107', 8086, '辛亥革命于##年,##地方3', ' ', '', '填空题'),
	('109', 8086, '辛亥革命于##年,##地方5', ' ', '', '填空题'),
	('110', 8086, '辛亥革命于##年,##地方6', ' ', '', '填空题'),
	('111', 8086, '辛亥革命于##年,##地方7', ' ', '', '填空题'),
	('112', 8086, '辛亥革命于##年,##地方8', ' ', '', '填空题'),
	('113', 8086, '辛亥革命于##年,##地方9', ' ', '', '填空题'),
	('114', 8086, '辛亥革命于##年,##地方10', ' ', '', '填空题'),
	('123', 8086, '测试解答题', ' ', '', '解答题'),
	('120', 8086, '测试解答题2', ' ', '', '解答题'),
	('121', 8086, '测试解答题3', ' ', '', '解答题'),
	('122', 8086, '测试解答题4', ' ', '', '解答题'),
	('108', 8086, '辛亥革命于##年,##地方4', ' ', '', '填空题');
/*!40000 ALTER TABLE `questionlist` ENABLE KEYS */;

-- 导出  表 ccl_oes.questionmapping 结构
CREATE TABLE IF NOT EXISTS `questionmapping` (
  `libraryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '题库ID，自增',
  `libraryName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '题库名称，汉字',
  `teacherUsername` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '教师登录名',
  PRIMARY KEY (`libraryId`),
  KEY `FK_QuestionMapping_Teacher` (`teacherUsername`),
  CONSTRAINT `FK_QuestionMapping_Teacher` FOREIGN KEY (`teacherUsername`) REFERENCES `teacher` (`teacherUsername`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8091 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='题目映射表，相当于题库名称表。将每一道题映射到相应的题库名上。';

-- 正在导出表  ccl_oes.questionmapping 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `questionmapping` DISABLE KEYS */;
INSERT INTO `questionmapping` (`libraryId`, `libraryName`, `teacherUsername`) VALUES
	(2, '数据结构', 'zhongmei@nsu.edu.cn'),
	(673, '罪恶王冠', 'lishan@nsu.edu.cn'),
	(676, '未闻花名', 'lishan@nsu.edu.cn'),
	(8086, '绝对可怜小孩', 'lishan@nsu.edu.cn'),
	(8087, '123', 'lishan@nsu.edu.cn'),
	(8088, '题库1', 'lishan@nsu.edu.cn'),
	(8089, '题库2', 'lishan@nsu.edu.cn'),
	(8090, '题库3', 'lishan@nsu.edu.cn');
/*!40000 ALTER TABLE `questionmapping` ENABLE KEYS */;

-- 导出  表 ccl_oes.student 结构
CREATE TABLE IF NOT EXISTS `student` (
  `teacherUsername` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '标识学生由哪个老师导入',
  `studentId` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '学生ID',
  `studentName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '学生姓名，汉字',
  `studentStatus` int(11) NOT NULL DEFAULT '0' COMMENT '0缺考#1考试中#2考试结束',
  `examId` int(11) NOT NULL,
  KEY `FK_Student_Teacher` (`teacherUsername`),
  KEY `examId` (`examId`),
  CONSTRAINT `FK_Student_Exam` FOREIGN KEY (`examId`) REFERENCES `exam` (`examId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Student_Teacher` FOREIGN KEY (`teacherUsername`) REFERENCES `teacher` (`teacherUsername`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储学生信息，教师可对自学生信息进行增删查（只能操作自己添加的学生信息）';

-- 正在导出表  ccl_oes.student 的数据：~37 rows (大约)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`teacherUsername`, `studentId`, `studentName`, `studentStatus`, `examId`) VALUES
	('zhongmei@nsu.edu.cn', '3', '3', 0, 920),
	('zhongmei@nsu.edu.cn', '4', '4', 0, 920),
	('zhongmei@nsu.edu.cn', '5', '5', 0, 920),
	('zhongmei@nsu.edu.cn', '2', '2', 0, 920),
	('zhongmei@nsu.edu.cn', '6', '6', 0, 920),
	('zhongmei@nsu.edu.cn', '7', '7', 0, 920),
	('zhongmei@nsu.edu.cn', '8', '8', 0, 920),
	('zhongmei@nsu.edu.cn', '9', '9', 0, 920),
	('zhongmei@nsu.edu.cn', '10', '10', 0, 920),
	('zhongmei@nsu.edu.cn', '11', '11', 0, 920),
	('zhongmei@nsu.edu.cn', '12', '12', 0, 920),
	('zhongmei@nsu.edu.cn', '13', '13', 0, 920),
	('zhongmei@nsu.edu.cn', '14', '14', 0, 920),
	('zhongmei@nsu.edu.cn', '15', '15', 0, 920),
	('zhongmei@nsu.edu.cn', '16', '16', 0, 920),
	('zhongmei@nsu.edu.cn', '17', '17', 0, 920),
	('zhongmei@nsu.edu.cn', '1', '1', 0, 920),
	('zhongmei@nsu.edu.cn', '王梓林', '14310120101', 0, 928),
	('zhongmei@nsu.edu.cn', '15310320108', 'inkbox', 2, 939),
	('lishan@nsu.edu.cn', '15437892356', 'afhsdjhf', 0, 939),
	('lishan@nsu.edu.cn', '1234567890987654', 'dgfhgjhkjlkjhgdf', 0, 939),
	('lishan@nsu.edu.cn', '234698237', 'sdhfjklasdh', 0, 939),
	('lishan@nsu.edu.cn', '48922374789', 'dgbhjzsd', 0, 939),
	('lishan@nsu.edu.cn', '1234567890986', 'jgklsd', 1, 939),
	('lishan@nsu.edu.cn', '234567896792354789', 'sdfghjklsdfghjk', 0, 939),
	('lishan@nsu.edu.cn', '14310120123', 'qwer', 2, 939),
	('lishan@nsu.edu.cn', '14310120134', 'asdf', 0, 939),
	('lishan@nsu.edu.cn', '1234567899999999', 'qwertyhjukdcfvbnm', 0, 939),
	('lishan@nsu.edu.cn', 'werfwe', 'sdf', 0, 939),
	('lishan@nsu.edu.cn', '123123123123123123', 'qwerwerwer', 0, 939),
	('lishan@nsu.edu.cn', '123123', 'gvsdfjhxkv', 0, 939),
	('lishan@nsu.edu.cn', '43523', 'rwerewfs', 0, 939),
	('lishan@nsu.edu.cn', '2342345', 'sdfgasd', 0, 939),
	('lishan@nsu.edu.cn', '5234675623479', 'sdhjifhjkasdfhjksd', 0, 939),
	('lishan@nsu.edu.cn', '', '', 0, 933),
	('lishan@nsu.edu.cn', '2342', '234', 0, 933),
	('lishan@nsu.edu.cn', 'qwe', '是的发送到', 0, 933);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- 导出  表 ccl_oes.teacher 结构
CREATE TABLE IF NOT EXISTS `teacher` (
  `teacherUsername` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '教师登录名，推荐使用学院邮箱登陆',
  `teacherPassword` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '教师登陆密码',
  `departId` int(11) NOT NULL COMMENT '教师所属系部编号',
  `teacherId` int(11) NOT NULL AUTO_INCREMENT COMMENT '教师编号，系部编号+教师姓名全拼',
  `teacherName` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '教师真实姓名，保存汉字',
  PRIMARY KEY (`teacherUsername`),
  KEY `FK_Teacher_Department` (`departId`),
  KEY `teacherId` (`teacherId`),
  CONSTRAINT `FK_Teacher_Department` FOREIGN KEY (`departId`) REFERENCES `department` (`departId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='存储教师信息，教师可以导入题库，创建考试，导入学生';

-- 正在导出表  ccl_oes.teacher 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` (`teacherUsername`, `teacherPassword`, `departId`, `teacherId`, `teacherName`) VALUES
	('admin@nsu.edu.cn', '123456', 2, 2, '墨盒'),
	('lishan@nsu.edu.cn', 'e10adc3949ba59abbe56e057f20f883e', 3, 4, '李珊'),
	('mojiao@nsu.edu.cn', 'e10adc3949ba59abbe56e057f20f883e', 3, 3, '莫骄'),
	('zhongmei@nsu.edu.cn', 'e10adc3949ba59abbe56e057f20f883e', 1, 1, '钟美');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;

-- 导出  过程 ccl_oes.deleteLibrary 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `deleteLibrary`(IN `questiondepartId` int)
    COMMENT 'ɾ?????'
BEGIN
	DELETE FROM  QuestionMapping WHERE libraryId = questiondepartId;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getExamId 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getExamId`(
	IN `s_id` VARCHAR(20),
	IN `s_name` VARCHAR(50),
	IN `k_word` VARCHAR(50)




)
    COMMENT '输入学生学号、学生姓名和考试口令获取开始编号'
BEGIN
 	select exam.examId from student,exam where student.studentId =s_id and student.studentName = s_name and exam.keyword=k_word limit 1;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getExamId_Mark 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getExamId_Mark`()
    COMMENT '???ÿ??Ա??'
BEGIN
   select examId from Mark ;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getExamInfo 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getExamInfo`()
    COMMENT '???ÿ?????Ϣ'
BEGIN
select examId,Exam.libraryId,examName,QuestionMapping.libraryName,date_format(startTime,'%Y-%c-%d %h:%i:%s'), date_format(endTime,'%Y-%c-%d %H:%i:%s') , Exam.teacherUsername from Exam , QuestionMapping where Exam.libraryId = QuestionMapping.libraryId;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getExamInfo_Student 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getExamInfo_Student`(IN `exam_Id` INT)
    COMMENT '???ÿ???ѧ????Ϣ'
BEGIN
   select ExamPaper.studentId , Student.studentName , Student.studentStatus ,ExamPaper.browser , ExamPaper.ipAddr , ExamPaper.remark , ExamPaper.answerReal , ExamPaper.answerStudent , ExamPaper.score , ExamPaper.eachType from Student , ExamPaper where ExamPaper.examId = Student.examId and ExamPaper.studentId = Student.studentId and Student.examId = exam_Id ;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getLibraryInfo 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getLibraryInfo`()
    DETERMINISTIC
    COMMENT '???????'
BEGIN
   declare s_Num , m_Num , tof_Num int ;
   set s_Num = (select count(types) from QuestionList where types = '��ѡ��');
   set m_Num = (select count(types) from QuestionList where types = '��ѡ��');
   set tof_Num = (select count(types) from QuestionList where types = '�ж���');   
   
   select QuestionMapping.libraryId , QuestionMapping.libraryName , s_Num , m_Num , tof_Num from QuestionMapping ;
  
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getStudentInfo 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getStudentInfo`(IN `exam_Id` INT)
    COMMENT '????ѧ????Ϣ'
BEGIN
    select teacherUsername , studentId , studentName , studentStatus from Student where examId = exam_Id; 
END//
DELIMITER ;

-- 导出  过程 ccl_oes.getStudentMark 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `getStudentMark`(IN `exam_Id` INT)
    COMMENT '????ѧ???ɼ?'
BEGIN
   select Mark.examId, Exam.examName,Mark.studentId ,Mark.studentName, Mark.mark,Mark.ipAddr from Mark, Exam
	
	where Exam.examId=exam_Id and Exam.examId = Mark.examId ;

END//
DELIMITER ;

-- 导出  过程 ccl_oes.GET_EXAMINEEINFO 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `GET_EXAMINEEINFO`(
	IN `student_Id` VARCHAR(20),
	IN `exam_Id` INT

)
    COMMENT '获取考生信息'
BEGIN
	select studentName,studentStatus from Student
	where studentId=student_Id and examId=exam_Id;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.GET_EXAMINFO 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `GET_EXAMINFO`(
	IN `student_Id` VARCHAR(20),
	IN `exam_Id` INT


)
    COMMENT '获取考场信息'
BEGIN
	select exam.examName,exam.teacherUsername as teacherName ,exam.startTime,exam.endTime 
	from exam join student on  exam.examId = student.examId where student.studentId = student_Id and student.examId = exam_Id;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.GET_EXAMQUESINFO 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `GET_EXAMQUESINFO`(
	IN `studentId` VARCHAR(20),
	IN `examId` INT




)
    COMMENT '获取考生的考题信息，只有ID和类型'
BEGIN
	select questionlist.questionId,questionlist.types  from questionlist,student,exam,answer
	where student.studentId = studentId and student.examId = examId
	and exam.examId = student.examId and questionlist.libraryId = exam.libraryId
	and questionlist.questionId = answer.QuestionId;

END//
DELIMITER ;

-- 导出  过程 ccl_oes.GET_SWICHQUE 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `GET_SWICHQUE`(
	IN `student_Id` VARCHAR(20),
	IN `exam_Id` INT,
	IN `question_Id` VARCHAR(20)




)
    COMMENT '获取单个考题信息'
BEGIN
	
	
	select questionlist.question,questionlist.choice,answer.AnswerStudent as answerSutdent ,questionlist.types
	from questionlist,student,exam,answer
	where student.studentId = student_Id and student.examId = exam_Id
	and exam.examId = exam_Id
	and answer.ExamId = exam_Id and answer.QuestionId = question_Id
	and questionlist.libraryId = exam.libraryId 
	and questionlist.questionId = question_Id;

END//
DELIMITER ;

-- 导出  过程 ccl_oes.setKeyword 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `setKeyword`(IN `key_word` VARCHAR(50), IN `exam_Id` INT)
    COMMENT '???ӿ??'
BEGIN
  update Exam set keyword=key_word where examId=exam_Id;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.setMark 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `setMark`(IN `exam_Id` INT, IN `student_Id` VARCHAR(50), IN `student_Mark` FLOAT)
    COMMENT '?洢?ɼ?'
BEGIN
    insert into Mark(examId , studentId , mark )values( exam_Id , student_Id , student_Mark);
    
    update Mark 
    inner join Exam on Exam.examId = Mark.examId
	 inner join Student on Student.examId = Exam.examId
	 
	 set Mark.studentName = Student.studentName where Mark.studentId =  Student.studentId ;

END//
DELIMITER ;

-- 导出  过程 ccl_oes.setStudentInfo 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `setStudentInfo`(
	IN `exam_Id` INT,
	IN `teacher_Id` VARCHAR(50),
	IN `student_Id` VARCHAR(50),
	IN `student_Name` VARCHAR(50)
 CHARACTER SET utf8
)
    COMMENT '????ѧ????Ϣ'
BEGIN
  declare res , i , lib int; 
  declare s_Num , m_Num , tof_Num int ; /*��Ŀ����*/
  declare s_QNum , m_QNum , tof_QNum int ; /* ��������*/
  declare s_MNum , m_MNum , tof_MNum  int ;
  declare s_Score , m_Score , tof_Score nvarchar(50);
  declare answer1 , answer2 , answer3 , problem1 , problem2 , problem3 , T_Spro , T_Mpro , T_TOFpro nvarchar(1500); 
  declare S_Str , M_Str , TOF_Str , S_Tstr , M_Tstr , TOF_Tstr varchar(1500); 
  
  set res=exam_Id;	/*(select MAX(Exam.examId) from Exam);*/
  set lib = (select Exam.libraryId from Exam where Exam.examId = res) ;
  select student_Name;
  insert into Student values (teacher_Id,student_Id,student_Name,0,res);
  insert into ExamPaper (examId , studentId ) values(res , student_Id );   
  
  set s_Num = (select Exam.sChoice from Exam where examId = res ) ;
  set m_Num = (select mChoice from Exam where examId = res ) ;
  set tof_Num = (select tofQuestion from Exam where examId = res ) ;
  
  set s_Score = (select Exam.sScore from Exam where examId = res ) ;
  set m_Score = (select Exam.mScore from Exam where examId = res ) ;
  set tof_Score = (select Exam.tofScore from Exam where examId = res ) ;
  
  set s_MNum = (select MIN(cast(QuestionList.questionId AS unsigned int)) from QuestionList where QuestionList.types = '��ѡ��' and QuestionList.libraryId = lib );
  set m_MNum = (select MIN(cast(QuestionList.questionId AS unsigned int)) from QuestionList where QuestionList.types = '��ѡ��' and QuestionList.libraryId = lib );
  set tof_MNum = (select MIN(cast(QuestionList.questionId AS unsigned int)) from QuestionList where QuestionList.types = '�ж���' and QuestionList.libraryId = lib );
   
   set s_QNum = (select sum(types = '��ѡ��') from QuestionList where QuestionList.libraryId = lib );
   set m_QNum = (select sum(types = '��ѡ��') from QuestionList where QuestionList.libraryId = lib );
   set tof_QNum = (select sum(types = '�ж���') from QuestionList where QuestionList.libraryId = lib );
   
   set S_Str = '##';
   set M_Str = '##';
   set TOF_Str = '##';
   
   /*��Ŀ���ο��� , ���� ���𰸣�eachType*/
   
   set i = 0;
   if s_QNum !=0 then
       
	    while i < s_Num do
	    /*�����㷨*/
	    	if i > 0 then
	    		loop_S:loop
	    			set T_Spro = floor((rand()*(s_QNum - 1)) + s_MNum ) ;
	    			 	 		
	   	 		set S_Tstr = '##';
					set S_Tstr = concat(S_Tstr,T_Spro);
	    			set S_Tstr = concat(S_Tstr,'##');
	   	 		
	    			if locate(S_Tstr,S_Str) =0 then
	    				set S_Str = concat(S_Str,T_Spro);
			    		set S_Str = concat(S_Str,'##');
	    				
	    				set problem1 = T_Spro;
	    				select problem1;
	    				leave loop_S;
	    			end if;	    		
	    		end loop loop_S;
	    	else
	    		set T_Spro = floor((rand()*(s_QNum - 1)) + s_MNum ) ;
	    		set S_Str = concat(S_Str,T_Spro);
		    	set S_Str = concat(S_Str,'##');
		    	
		    	set problem1 = T_Spro;
		   end if;
	    	
         set answer1 = (select answer from QuestionList where QuestionList.types = '��ѡ��' and QuestionList.libraryId  = lib and QuestionList.questionId = problem1) ;        
         
         if m_QNum = 0 and tof_QNum = 0 and i =s_Num - 1 then
                update ExamPaper set randomId = concat(randomId,'',problem1) where examId = res and studentId = student_Id ;                
                update ExamPaper set answerReal = concat(answerReal,'',answer1) where examId = res and studentId = student_Id ;             
                update ExamPaper set answerStudent = concat(answerStudent,'','**') where examId = res and studentId = student_Id ;
                update ExamPaper set eachType = concat(eachType,'','��ѡ��') where examId = res and studentId = student_Id ;
         else 
                update ExamPaper set randomId = concat(randomId,'',problem1) where examId = res and studentId = student_Id ;
                update ExamPaper set randomId = concat(randomId,'','##') where examId = res and studentId = student_Id ;
					 update ExamPaper set answerReal = concat(answerReal,'',answer1) where examId = res and studentId = student_Id ;
                update ExamPaper set answerReal = concat(answerReal,'','##') where examId = res and studentId = student_Id ;
                update ExamPaper set answerStudent = concat(answerStudent,'','**##') where examId = res and studentId = student_Id ;
                update ExamPaper set eachType = concat(eachType,'','��ѡ��##') where examId = res and studentId = student_Id ;
                
         end if;
         
         set i = i + 1;
       end while;
   end if ;    
   
   set i = 0;
   
   if m_QNum !=0 then
        
        while i < m_Num do
         /*�����㷨*/
         if i > 0 then
	    		loop_M:loop
	    			set T_Mpro = floor((rand()*(m_QNum - 1)) + m_MNum ) ;
	    			 	 		
	   	 		set M_Tstr = '##';
					set M_Tstr = concat(M_Tstr,T_Mpro);
	    			set M_Tstr = concat(M_Tstr,'##');
	   	 		
	    			if locate(M_Tstr,M_Str) =0 then
	    				set M_Str = concat(M_Str,T_Mpro);
			    		set M_Str = concat(M_Str,'##');
	    				
	    				set problem2 = T_Mpro;
	    				select problem2;
	    				leave loop_M;
	    			end if;	    		
	    		end loop loop_M;
	    	else
	    		set T_Mpro = floor((rand()*(m_QNum - 1)) + m_MNum ) ;
	    		set M_Str = concat(M_Str,T_Mpro);
			   set M_Str = concat(M_Str,'##');
		    	
		    	set problem2 = T_Mpro;
		   end if;         
         
         set answer2 = (select answer from QuestionList where QuestionList.types = '��ѡ��' and QuestionList.libraryId  = lib and QuestionList.questionId = problem2) ;
         
         if tof_QNum = 0  and i = m_Num - 1 then
                update ExamPaper set randomId = concat(randomId,'',problem2) where examId = res and studentId = student_Id ;                
                update ExamPaper set answerReal = concat(answerReal,'',answer2) where examId = res and studentId = student_Id ;
					 update ExamPaper set answerStudent = concat(answerStudent,'','**@@**') where examId = res and studentId = student_Id;
					 update ExamPaper set eachType = concat(eachType,'','��ѡ��') where examId = res and studentId = student_Id;                
         else 
                update ExamPaper set randomId = concat(randomId,'',problem2) where examId = res and studentId = student_Id ;
					 update ExamPaper set randomId = concat(randomId,'','##') where examId = res and studentId = student_Id ;
                update ExamPaper set answerReal = concat(answerReal,'',answer2) where examId = res and studentId = student_Id ;
                update ExamPaper set answerReal = concat(answerReal,'','##') where examId = res and studentId = student_Id ;
                update ExamPaper set answerStudent = concat(answerStudent,'','**@@**##') where examId = res and studentId = student_Id;
                update ExamPaper set eachType = concat(eachType,'','��ѡ��##') where examId = res and studentId = student_Id;
         end if;
         
         set i = i + 1;
        end while;
   end if ;   
   
   set i = 0;
   
   if tof_QNum !=0 then
        
        while i < tof_Num do
         /*�����㷨*/
         if i > 0 then
	    		loop_TOF:loop
	    			set T_TOFpro = floor((rand()*(tof_QNum - 1)) + tof_MNum ) ;
	    			 	 		
	   	 		set TOF_Tstr = '##';
					set TOF_Tstr = concat(TOF_Tstr,T_TOFpro);
	    			set TOF_Tstr = concat(TOF_Tstr,'##');
	   	 		
	    			if locate(TOF_Tstr,TOF_Str) =0 then
	    				set TOF_Str = concat(TOF_Str,T_TOFpro);
			    		set TOF_Str = concat(TOF_Str,'##');
	    				
	    				set problem3 = T_TOFpro;
	    				select problem3;
	    				leave loop_TOF;
	    			end if;	    		
	    		end loop loop_TOF;
	    	else
	    		set T_TOFpro = floor((rand()*(tof_QNum - 1)) + tof_MNum ) ;
	    		set TOF_Str = concat(TOF_Str,T_TOFpro);
			   set TOF_Str = concat(TOF_Str,'##');
		    	
		    	set problem3 = T_TOFpro;
		   end if; 
         
         set answer3 = (select answer from QuestionList where QuestionList.types = '�ж���' and QuestionList.libraryId  = lib and QuestionList.questionId = problem3) ;
         
         if i = tof_Num - 1 then
                update ExamPaper set randomId = concat(randomId,'',problem3) where examId = res and studentId = student_Id ;
					 update ExamPaper set answerReal = concat(answerReal,'',answer3) where examId = res and studentId = student_Id ;
					 update ExamPaper set answerStudent = concat(answerStudent,'','**') where examId = res and studentId = student_Id;
					 update ExamPaper set eachType = concat(eachType,'','�ж���') where examId = res and studentId = student_Id;
         else 
                update ExamPaper set randomId = concat(randomId,'',problem3) where examId = res and studentId = student_Id ;
                update ExamPaper set randomId = concat(randomId,'','##') where examId = res and studentId = student_Id ;
                update ExamPaper set answerReal = concat(answerReal,'',answer3) where examId = res and studentId = student_Id ;
					 update ExamPaper set answerReal = concat(answerReal,'','##') where examId = res and studentId = student_Id ;
					 update ExamPaper set answerStudent = concat(answerStudent,'','**##') where examId = res and studentId = student_Id;
					 update ExamPaper set eachType = concat(eachType,'','�ж���##') where examId = res and studentId = student_Id;
         end if;
         
         set i = i + 1;
        end while;
   end if ;
   
   /*����*/
   update ExamPaper set score = concat(score,'',s_Score) where examId = res and studentId = student_Id ;
   update ExamPaper set score = concat(score,'',',') where examId = res and studentId = student_Id ;
   update ExamPaper set score = concat(score,'',m_Score) where examId = res and studentId = student_Id ;
   update ExamPaper set score = concat(score,'',',') where examId = res and studentId = student_Id ;
   update ExamPaper set score = concat(score,'',tof_Score) where examId = res and studentId = student_Id ;
     
END//
DELIMITER ;

-- 导出  过程 ccl_oes.SUBMIT_KEY 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `SUBMIT_KEY`(
	IN `student_id` VARCHAR(20),
	IN `exam_id` INT,
	IN `question_id` INT,
	IN `answer_student` VARCHAR(500)




)
    COMMENT '提交考生答案'
BEGIN	
	update answer set answer.AnswerStudent = answer_student
--  select * from answer
	where answer.studentId=student_id and answer.examId=exam_id and answer.QuestionId = question_id;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.updateRemark 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `updateRemark`(IN `exam_Id` INT, IN `student_Id` VARCHAR(50), IN `reMark` VARCHAR(50))
    COMMENT '???ӱ?ע'
BEGIN
  update ExamPaper set remark = reMark where examId = exam_Id and studentId = student_Id;
END//
DELIMITER ;

-- 导出  过程 ccl_oes.UPDATE_STATUS 结构
DELIMITER //
CREATE DEFINER=`inkbox`@`%` PROCEDURE `UPDATE_STATUS`(
	IN `studentId` VARCHAR(20),
	IN `examId` INT,
	IN `statu` INT




)
    COMMENT '设置考生状态'
BEGIN

		update student set student.studentStatus = statu where student.studentId = studentId and student.examId = examId and student.studentStatus!=2;

END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
