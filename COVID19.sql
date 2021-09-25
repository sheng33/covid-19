/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 47.102.86.236:3306
 Source Schema         : COVID19

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 22/12/2020 08:56:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for abnormalperson
-- ----------------------------
DROP TABLE IF EXISTS `abnormalperson`;
CREATE TABLE `abnormalperson`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `userid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '当前地址',
  `nearestaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近地址',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态',
  `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常人员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of abnormalperson
-- ----------------------------
INSERT INTO `abnormalperson` VALUES (4, '5fcb62f415044ad9299fd5fd', '13111111111', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', 0, '', '2020-12-09 21:00:33');
INSERT INTO `abnormalperson` VALUES (5, '5fcb62f415044ad9299fd5fd', '13111111111', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', 0, '', '2020-12-09 21:07:32');
INSERT INTO `abnormalperson` VALUES (6, '5fcb62f415044ad9299fd5fd', '13111111111', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', 0, '', '2020-12-09 21:18:08');
INSERT INTO `abnormalperson` VALUES (7, '5fcb62f415044ad9299fd5fd', '13111111111', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', 0, '', '2020-12-10 10:34:57');
INSERT INTO `abnormalperson` VALUES (8, '5fcb62f415044ad9299fd5fd', '13111111111', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', 0, '', '2020-12-10 10:35:22');

-- ----------------------------
-- Table structure for client_menu
-- ----------------------------
DROP TABLE IF EXISTS `client_menu`;
CREATE TABLE `client_menu`  (
  `id` bigint(5) NOT NULL AUTO_INCREMENT COMMENT '用户层菜单id',
  `menu_name` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单Url',
  `menu_imgId` bigint(5) NULL DEFAULT NULL COMMENT '菜单图片id',
  `status` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '状态id',
  `auth` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of client_menu
-- ----------------------------
INSERT INTO `client_menu` VALUES (3, '个人', '/pages/userinfo/userinfo', 6, 0, 0);
INSERT INTO `client_menu` VALUES (4, '每日填报', '/pages/dailyinput/dailyinput', 7, 0, 0);
INSERT INTO `client_menu` VALUES (5, '填报记录', '/pages/dailylist/index', 8, 0, 0);
INSERT INTO `client_menu` VALUES (6, '行程查看', '/pages/motiontrack/index', 9, 0, 0);
INSERT INTO `client_menu` VALUES (7, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for client_user
-- ----------------------------
DROP TABLE IF EXISTS `client_user`;
CREATE TABLE `client_user`  (
  `userid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `identity` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `istouch` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否与异常人员接触 0 未接触 1 接触 ',
  `isarea` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否处于异常地区 0 不处于 1处于',
  `istemperature` tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 0 COMMENT '是否温度异常 0 不处于 1处于',
  `createTime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `sessionKey` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'seesion_Key',
  `openid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'openid',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of client_user
-- ----------------------------
INSERT INTO `client_user` VALUES ('1', 'shengxiu', '19911111111', '362220199901111123', 1, 1, 1, '2020-10-27 19:58:03', NULL, NULL);
INSERT INTO `client_user` VALUES ('4', 'sheng', '15223333333', '15223333333', 0, 0, 0, '2020-10-27 19:47:42', NULL, NULL);
INSERT INTO `client_user` VALUES ('5fcb62f415044ad9299fd5fd', '盛嘉', '13111112222', NULL, 0, 0, 1, '2020-12-05 18:37:39', 'iRZyjvKdX9QJEu4P8Ypo7A==', 'osNBr5KujuHZTXuAp6bjGpARd3s4');

-- ----------------------------
-- Table structure for config_img
-- ----------------------------
DROP TABLE IF EXISTS `config_img`;
CREATE TABLE `config_img`  (
  `id` bigint(5) NOT NULL AUTO_INCREMENT,
  `imgname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片名称',
  `imgUrl` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片url',
  `operator` bigint(20) NOT NULL COMMENT '操作者',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `type` int(4) NULL DEFAULT NULL COMMENT '图片类型',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `用户判断`(`operator`) USING BTREE,
  CONSTRAINT `用户判断` FOREIGN KEY (`operator`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户配置图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_img
-- ----------------------------
INSERT INTO `config_img` VALUES (1, 'test', 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3141449705,3912039585&fm=26&gp=0.jpg', 1, '2020-12-04 14:38:36', 0);
INSERT INTO `config_img` VALUES (6, 'identity', '/static/img/identity-01.svg', 8, '2020-12-05 14:44:49', 1);
INSERT INTO `config_img` VALUES (7, 'input', '/static/img/input-01.svg', 8, '2020-12-09 12:26:03', 1);
INSERT INTO `config_img` VALUES (8, 'document', '/static/img/document-01.svg', 8, '2020-12-10 08:37:33', 1);
INSERT INTO `config_img` VALUES (9, 'aircraft', '/static/img/aircraft-01.svg', 8, '2020-12-10 16:00:31', 1);

-- ----------------------------
-- Table structure for dailyreport
-- ----------------------------
DROP TABLE IF EXISTS `dailyreport`;
CREATE TABLE `dailyreport`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `address` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定位地址',
  `temperature` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前体温',
  `remark` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(1) NULL DEFAULT NULL COMMENT '健康状态',
  `createTime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日常登记表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dailyreport
-- ----------------------------
INSERT INTO `dailyreport` VALUES (1, '1', '井冈山大学', '36.5', '', NULL, '2020-11-18 22:59:28');
INSERT INTO `dailyreport` VALUES (2, '1', '井冈山大学', '36.3', '', NULL, '2020-11-17 22:59:28');
INSERT INTO `dailyreport` VALUES (3, '1', '井冈山大学', '36.4', '', NULL, '2020-11-16 22:59:28');
INSERT INTO `dailyreport` VALUES (4, '1', '井冈山大学', '36.6', '', NULL, '2020-11-15 22:59:28');
INSERT INTO `dailyreport` VALUES (5, '1', '井冈山大学', '36.7', '', NULL, '2020-11-14 22:59:28');
INSERT INTO `dailyreport` VALUES (6, '1', '井冈山大学', '36.8', '', NULL, '2020-11-13 22:59:28');
INSERT INTO `dailyreport` VALUES (7, '1', '井冈山大学', '36.9', '', NULL, '2020-11-12 22:59:28');
INSERT INTO `dailyreport` VALUES (8, '1', '井冈山大学', '37.0', '', NULL, '2020-11-11 22:59:28');
INSERT INTO `dailyreport` VALUES (9, '1', '井冈山大学', '39.0', '', NULL, '2020-11-10 22:59:28');
INSERT INTO `dailyreport` VALUES (10, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '36.6', '', NULL, '2020-12-09 13:56:08');
INSERT INTO `dailyreport` VALUES (11, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '38.1', '', NULL, '2020-12-09 20:18:52');
INSERT INTO `dailyreport` VALUES (12, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '36.6', '', NULL, '2020-12-09 20:50:12');
INSERT INTO `dailyreport` VALUES (13, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '38.4', '', NULL, '2020-12-09 21:00:33');
INSERT INTO `dailyreport` VALUES (14, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '38.4', '', NULL, '2020-12-09 21:07:32');
INSERT INTO `dailyreport` VALUES (15, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '38.4', '', NULL, '2020-12-09 21:18:08');
INSERT INTO `dailyreport` VALUES (16, '5fcb62f415044ad9299fd5fd', '江西省吉安市青原区滨江街道吉安市青原区信访局吉安市青原区人民政府', '1.0', '', NULL, '2020-12-09 21:18:34');
INSERT INTO `dailyreport` VALUES (17, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '36.6', '', NULL, '2020-12-10 08:24:43');
INSERT INTO `dailyreport` VALUES (18, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '36.6', '', NULL, '2020-12-10 08:25:02');
INSERT INTO `dailyreport` VALUES (19, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '39.6', '', 1, '2020-12-10 10:34:57');
INSERT INTO `dailyreport` VALUES (20, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '36.6', '', 0, '2020-12-10 10:35:12');
INSERT INTO `dailyreport` VALUES (21, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '33.6', '', 1, '2020-12-10 10:35:22');
INSERT INTO `dailyreport` VALUES (22, '5fcb62f415044ad9299fd5fd', '江西省吉安市吉州区习溪桥街道鹭洲东路锦绣天成', '36.6', '', 0, '2020-12-15 08:59:36');

-- ----------------------------
-- Table structure for dangeraddress
-- ----------------------------
DROP TABLE IF EXISTS `dangeraddress`;
CREATE TABLE `dangeraddress`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省份',
  `city` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '城市',
  `area` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细地址',
  `status` int(11) NULL DEFAULT NULL COMMENT '记录状态',
  `createtime` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dangeraddress
-- ----------------------------

-- ----------------------------
-- Table structure for dangertransport
-- ----------------------------
DROP TABLE IF EXISTS `dangertransport`;
CREATE TABLE `dangertransport`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transportation` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交通工具',
  `serialNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车次',
  `findtime` date NOT NULL COMMENT '发生时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常车次表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dangertransport
-- ----------------------------
INSERT INTO `dangertransport` VALUES (1, '火车', 'D6295', '2020-11-18', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (2, '飞机', 'DH0223', '2020-11-18', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (3, '飞机', 'DH113', '2020-10-08', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (4, '火车', 'K113', '2020-10-22', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (5, '火车', 'K137', '2020-11-28', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (6, '汽车', '赣D11123', '2020-08-18', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (7, '火车', 'T102', '2020-12-13', '2020-12-04 14:39:41');
INSERT INTO `dangertransport` VALUES (8, '火车', 'K5912', '2020-11-16', '2020-12-04 14:39:41');

-- ----------------------------
-- Table structure for motiontrack
-- ----------------------------
DROP TABLE IF EXISTS `motiontrack`;
CREATE TABLE `motiontrack`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userid` varbinary(40) NOT NULL COMMENT '用户id',
  `transportation` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交通工具',
  `traveltime` datetime(0) NOT NULL COMMENT '乘车时间',
  `serialNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车次',
  `startPoint` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '起始地',
  `endPoint` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '目的地',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '运动轨迹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of motiontrack
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'admin');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `mobile` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '状态：删除 -1  正常 0',
  `permission` tinyint(1) NOT NULL DEFAULT 0 COMMENT '权限id',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '张三', 'sheng', '123456---', '15297987428', 1, 0, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (2, '李四', 'root', '123456---', '111', 1, 0, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (3, '王五', 'shengS', '123---', '12333', 1, 1, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (4, '赵六', 'shengD', '1234---', '12333', 1, 1, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (5, '刘七', 'shengE', '1234---', '12333', 1, 1, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (7, '黄八', 'shengG', '1234---', '12333', 1, 1, '2020-12-04 11:25:44', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (8, 'admin', 'admin1', '123', '1322', 1, 0, '2020-12-21 20:52:10', '2020-12-04 11:25:44');
INSERT INTO `sys_user` VALUES (9, 'shengs1', 'aas', '{bcrypt}$2a$10$dPIhupEMiebZwBOSSkATv.qTlJSn96tdxhBDrE.4hf5kns0k8FMQq', '1231222', 0, 0, '2020-12-21 23:07:13', '2020-12-21 23:07:13');

-- ----------------------------
-- Table structure for sys_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_roles`;
CREATE TABLE `sys_user_roles`  (
  `sys_user_id` int(11) NOT NULL,
  `roles_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`sys_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_roles
-- ----------------------------
INSERT INTO `sys_user_roles` VALUES (8, 1);

-- ----------------------------
-- Table structure for userSource
-- ----------------------------
DROP TABLE IF EXISTS `userSource`;
CREATE TABLE `userSource`  (
  `userid` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户id',
  `platform` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '来源 微信小程序 or 支付宝小程序',
  PRIMARY KEY (`userid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userSource
-- ----------------------------
INSERT INTO `userSource` VALUES ('1', '微信');
INSERT INTO `userSource` VALUES ('4', '支付宝');

SET FOREIGN_KEY_CHECKS = 1;
