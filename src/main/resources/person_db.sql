/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL57
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : localhost:3306
 Source Schema         : person_db

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 22/12/2022 11:49:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `manager` int(11) NULL DEFAULT NULL COMMENT '员工号',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (1, '研发部', 101003, '产品研发');
INSERT INTO `t_department` VALUES (2, '运营部', 401010, '产品运营');
INSERT INTO `t_department` VALUES (3, '财务部', 301007, '财务管理');
INSERT INTO `t_department` VALUES (4, '人事部', 401009, '人事管理');

-- ----------------------------
-- Table structure for t_edu_level
-- ----------------------------
DROP TABLE IF EXISTS `t_edu_level`;
CREATE TABLE `t_edu_level`  (
  `code` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_edu_level
-- ----------------------------
INSERT INTO `t_edu_level` VALUES (0, '小学');
INSERT INTO `t_edu_level` VALUES (1, '初中');
INSERT INTO `t_edu_level` VALUES (2, '高中');
INSERT INTO `t_edu_level` VALUES (3, '职高');
INSERT INTO `t_edu_level` VALUES (4, '大专');
INSERT INTO `t_edu_level` VALUES (5, '本科');
INSERT INTO `t_edu_level` VALUES (6, '硕士');
INSERT INTO `t_edu_level` VALUES (7, '博士');
INSERT INTO `t_edu_level` VALUES (8, '博士后');

-- ----------------------------
-- Table structure for t_job
-- ----------------------------
DROP TABLE IF EXISTS `t_job`;
CREATE TABLE `t_job`  (
  `code` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_job
-- ----------------------------
INSERT INTO `t_job` VALUES (0, '实习员工');
INSERT INTO `t_job` VALUES (1, '普通员工');
INSERT INTO `t_job` VALUES (2, '主管');
INSERT INTO `t_job` VALUES (3, '经理');
INSERT INTO `t_job` VALUES (4, '部门总监');
INSERT INTO `t_job` VALUES (5, '副总经理');
INSERT INTO `t_job` VALUES (6, '总经理');

-- ----------------------------
-- Table structure for t_person
-- ----------------------------
DROP TABLE IF EXISTS `t_person`;
CREATE TABLE `t_person`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工号',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `authority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户权限\r',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `birthday` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生日',
  `department` int(11) NULL DEFAULT NULL COMMENT '部门编号',
  `job` int(11) NULL DEFAULT NULL COMMENT '职务代号',
  `edu_level` int(11) NULL DEFAULT NULL COMMENT '教育等级代号',
  `specialty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业技能',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家庭地址',
  `tel` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电子邮件',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前状态',
  `remake` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department`(`department`) USING BTREE,
  INDEX `job`(`job`) USING BTREE,
  INDEX `edu_level`(`edu_level`) USING BTREE,
  CONSTRAINT `t_person_ibfk_1` FOREIGN KEY (`department`) REFERENCES `t_department` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_person_ibfk_2` FOREIGN KEY (`job`) REFERENCES `t_job` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_person_ibfk_3` FOREIGN KEY (`edu_level`) REFERENCES `t_edu_level` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 401015 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_person
-- ----------------------------
INSERT INTO `t_person` VALUES (101001, '123456', '管理员', '陶渊明', '男', '1987-06-27', 1, 1, 8, '算法研发', '无', '16776867545', '16776867545@outlook.com', '员工', '无');
INSERT INTO `t_person` VALUES (101002, '233', '员工', '王未', '女', '1990-09-16', 1, 2, 5, 'Web前端', '无', '13818150229', '13818150229@qq.com', '员工', '无');
INSERT INTO `t_person` VALUES (101003, '233', '员工', '薛梓', '女', '1991-09-17', 1, 4, 6, '后端开发', '无', '13666766776', '13666766776@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (201002, '123', '员工', '张三丰', '男', '1980-06-18', 2, 5, 5, '产品运营', '无', '18818151222', '18818151222@gmail.com', '员工', '无');
INSERT INTO `t_person` VALUES (201006, '123456', '管理员', '王大武', '男', '1979-09-26', 4, 6, 2, '产品调研', '无', '18965432789', '8887.outlook.com', '员工', '无');
INSERT INTO `t_person` VALUES (301003, '456', '员工', '李世明', '男', '1999-06-19', 3, 1, 7, '无', '无', '16788151222', '16788151222@outlook.com', '员工', '无');
INSERT INTO `t_person` VALUES (301007, '123456', '员工', '金麦', '女', '1998-08-18', 3, 4, 6, '理财', '无', '18898886678', '888@email.com', '员工', '无');
INSERT INTO `t_person` VALUES (401005, '666666', '管理员', 'admin', '男', '1983-06-20', 4, 5, 7, '人事管理', '无', '17818150221', '17818150221@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401006, '123', '员工', '许欣', '女', '1993-09-16', 1, 3, 5, '无', '无', '19999995699', '999@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401007, '9908', '员工', '许攸之', '男', '1997-02-15', 1, 1, 6, '后端开发', '无', '12976882588', '无', '已辞退', '无');
INSERT INTO `t_person` VALUES (401008, '123456', '员工', '程槐', '男', '2001-02-10', 1, 1, 4, '软件测试', '无', '13949010089', '13949010089@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401009, '123456', '管理员', '谭佳', '女', '1990-01-30', 4, 4, 6, '财务管理', '无', '16676898998', '16676898998@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401010, '123456', '员工', '郑维之', '男', '1985-10-31', 2, 4, 8, '产品运营', '无', '19666768861', '19666768861@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401011, '123456', '员工', '汪泽', '男', '1996-06-16', 1, 2, 2, '前端开发', '无', '19888900988', '198666765543@163.com', '员工', '无');
INSERT INTO `t_person` VALUES (401012, '123456', '员工', '江杉', '男', '1997-06-12', 1, 1, 5, '软件测试', '无', '无', '无', '员工', '无');
INSERT INTO `t_person` VALUES (401014, '123456', '员工', '李元微', '男', '1998-06-18', 1, 1, 5, '软件开发', '18897633354', '无', '18897633354@163.com', 'T', '无');

-- ----------------------------
-- Table structure for t_personnel
-- ----------------------------
DROP TABLE IF EXISTS `t_personnel`;
CREATE TABLE `t_personnel`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录编号',
  `person` int(11) NULL DEFAULT NULL COMMENT '员工号',
  `change` int(11) NULL DEFAULT NULL COMMENT '变更代码',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `change`(`change`) USING BTREE,
  CONSTRAINT `t_personnel_ibfk_1` FOREIGN KEY (`change`) REFERENCES `t_personnel_change` (`code`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 538 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_personnel
-- ----------------------------
INSERT INTO `t_personnel` VALUES (500, 10010, 0, '2022-6-16');
INSERT INTO `t_personnel` VALUES (501, 201002, 1, '原职务: 运营部 部门总监--现职务:运营部 部门总监--操作时间:2022-6-17 20:48:07');
INSERT INTO `t_personnel` VALUES (503, 201006, 1, '原职务: 研发部 副总经理--现职务: 研发部 副总经理--操作时间:2022-06-17 20:57:28');
INSERT INTO `t_personnel` VALUES (504, 201006, 1, '原职务: 研发部 副总经理--现职务: 研发部 副总经理--操作时间:2022-06-17 20:58:25');
INSERT INTO `t_personnel` VALUES (505, 201006, 1, '原职务: 研发部 副总经理--现职务: 研发部 副总经理--操作时间:2022-06-17 20:59:18');
INSERT INTO `t_personnel` VALUES (506, 201006, 1, '原职务: 研发部 副总经理--现职务: 研发部 部门总监--操作时间:2022-06-17 21:00:28');
INSERT INTO `t_personnel` VALUES (507, 201006, 1, '原职务: 研发部 部门总监--现职务: 研发部 副总经理--操作时间:2022-06-17 21:00:49');
INSERT INTO `t_personnel` VALUES (508, 101003, 1, '原职务: 研发部 经理--现职务: 研发部 部门总监--操作时间:2022-06-17 21:01:06');
INSERT INTO `t_personnel` VALUES (510, 101002, 1, '原职务: 研发部 普通员工--现职务: 研发部 主管--操作时间:2022-06-17 23:01:53');
INSERT INTO `t_personnel` VALUES (511, 401007, 0, '员工id: null--操作时间:2022-06-18 09:07:36');
INSERT INTO `t_personnel` VALUES (512, 401008, NULL, '员工id: 401008--操作时间:2022-06-18 09:17:13');
INSERT INTO `t_personnel` VALUES (513, 201006, 1, '原职务: 研发部 副总经理--现职务: 研发部 总经理--操作时间:2022-06-18 12:12:47');
INSERT INTO `t_personnel` VALUES (514, 401009, 0, '员工id: 401009--操作时间:2022-06-18 14:57:54 ');
INSERT INTO `t_personnel` VALUES (515, 401009, 1, '原职务: 财务部 普通员工--现职务: 人事部 主管--操作时间:2022-06-18 15:03:40');
INSERT INTO `t_personnel` VALUES (517, 401010, 0, '员工id: 401010--操作时间:2022-06-18 15:12:55');
INSERT INTO `t_personnel` VALUES (518, 401010, 1, '原职务: 运营部 普通员工--现职务: 运营部 部门总监--操作时间:2022-06-18 15:13:42');
INSERT INTO `t_personnel` VALUES (519, 201002, 1, '原职务: 运营部 部门总监--现职务: 运营部 副总经理--操作时间:2022-06-18 15:18:47');
INSERT INTO `t_personnel` VALUES (520, 201006, 1, '原职务: 研发部 总经理--现职务: 人事部 总经理--操作时间:2022-06-18 15:22:26');
INSERT INTO `t_personnel` VALUES (521, 401011, 0, '员工id: 401011--操作时间:2022-06-19 20:43:16');
INSERT INTO `t_personnel` VALUES (522, 401011, 1, '原职务: 研发部 普通员工--现职务: 研发部 主管--操作时间:2022-06-19 20:49:41');
INSERT INTO `t_personnel` VALUES (523, 101003, 1, '原职务: 研发部 部门总监--现职务: 研发部 经理--操作时间:2022-06-19 22:44:08');
INSERT INTO `t_personnel` VALUES (524, 201002, 1, '原职务: 运营部 副总经理--现职务: 运营部 部门总监--操作时间:2022-06-19 22:44:25');
INSERT INTO `t_personnel` VALUES (525, 401012, 0, '员工id: 401012--操作时间:2022-06-19 22:52:40');
INSERT INTO `t_personnel` VALUES (526, 401012, 2, '员工id: 401012--操作时间:2022-06-19 22:54:55');
INSERT INTO `t_personnel` VALUES (527, 201002, 1, '原职务: 运营部 部门总监--现职务: 运营部 副总经理--操作时间:2022-06-23 13:04:52');
INSERT INTO `t_personnel` VALUES (528, 401010, 1, '原职务: 运营部 部门总监--现职务: 运营部 副总经理--操作时间:2022-06-23 13:05:20');
INSERT INTO `t_personnel` VALUES (529, 401010, 1, '原职务: 运营部 副总经理--现职务: 运营部 部门总监--操作时间:2022-06-23 13:05:52');
INSERT INTO `t_personnel` VALUES (530, 101003, 1, '原职务: 研发部 经理--现职务: 研发部 部门总监--操作时间:2022-06-23 13:06:59');
INSERT INTO `t_personnel` VALUES (531, 401006, 1, '原职务: 研发部 主管--现职务: 研发部 经理--操作时间:2022-06-23 13:13:41');
INSERT INTO `t_personnel` VALUES (532, 401005, 1, '原职务: 人事部 部门总监--现职务: 人事部 副总经理--操作时间:2022-06-23 13:51:46');
INSERT INTO `t_personnel` VALUES (533, 401009, 1, '原职务: 人事部 主管--现职务: 人事部 部门总监--操作时间:2022-06-23 13:53:06');
INSERT INTO `t_personnel` VALUES (534, 401007, 2, '员工id: 401007--操作时间:2022-06-23 21:17:25');
INSERT INTO `t_personnel` VALUES (536, 401014, 0, '员工id: 401014--操作时间:2022-07-08 18:51:52');
INSERT INTO `t_personnel` VALUES (537, 401015, 0, '员工id: 401015--操作时间:2022-07-08 18:53:08');

-- ----------------------------
-- Table structure for t_personnel_change
-- ----------------------------
DROP TABLE IF EXISTS `t_personnel_change`;
CREATE TABLE `t_personnel_change`  (
  `code` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of t_personnel_change
-- ----------------------------
INSERT INTO `t_personnel_change` VALUES (0, '新员工加入');
INSERT INTO `t_personnel_change` VALUES (1, '职务变动');
INSERT INTO `t_personnel_change` VALUES (2, '辞退');

SET FOREIGN_KEY_CHECKS = 1;
