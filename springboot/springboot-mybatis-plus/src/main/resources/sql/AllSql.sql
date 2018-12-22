/*
Navicat MySQL Data Transfer

Source Server         : MyServer
Source Server Version : 50718
Source Host           : 39.108.82.13:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-21 09:35:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(18) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user_name` varchar(64) DEFAULT NULL,
  `order_no` int(11) DEFAULT NULL COMMENT '资源排序号',
  `resource_level` int(11) DEFAULT NULL COMMENT '资源等级',
  `resource_parent` bigint(20) DEFAULT NULL COMMENT '资源上级ID',
  `resource_name` varchar(128) NOT NULL COMMENT '资源名称',
  `resource_desc` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `resource_path` varchar(128) DEFAULT NULL COMMENT '资源路径',
  `resource_icon` varchar(128) DEFAULT NULL COMMENT '资源图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('524660268899237888', '1', '1', '2018-12-18 10:52:36', null, null, null, '100', '1', null, '系统管理', '系统基础基础功能', '#', 'fa system');
INSERT INTO `sys_resource` VALUES ('524660269687767040', '1', '1', '2018-12-18 10:52:37', null, null, null, '1000', '2', '524660268899237888', '资源菜单管理', '系统菜单资源设置', 'sysResource', 'fa resource');
INSERT INTO `sys_resource` VALUES ('524660270312718336', '1', '1', '2018-12-18 10:52:37', null, null, null, '2000', '2', '524660268899237888', '角色管理', '角色控制', 'sysRole', 'fa role');
INSERT INTO `sys_resource` VALUES ('524660270363049984', '1', '1', '2018-12-18 10:52:37', null, null, null, '3000', '2', '524660268899237888', '用户管理', '用户管理', 'sysUser', 'fa user');

/*
Navicat MySQL Data Transfer

Source Server         : MyServer
Source Server Version : 50718
Source Host           : 39.108.82.13:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-21 09:35:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(18) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user_name` varchar(64) DEFAULT NULL,
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `role_auth` varchar(64) DEFAULT NULL COMMENT '角色权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('524660270589542400', '1', '1', '2018-12-18 10:52:37', null, null, null, '测试用户', '测试', 'ROLE_TEST');
INSERT INTO `sys_role` VALUES ('524660270816034816', '1', '1', '2018-12-18 10:52:37', null, null, null, '管理员', '超级管理员', 'ROLE_ADMIN');

/*
Navicat MySQL Data Transfer

Source Server         : MyServer
Source Server Version : 50718
Source Host           : 39.108.82.13:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-21 09:35:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(18) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user_name` varchar(64) DEFAULT NULL,
  `username` varchar(128) NOT NULL COMMENT '用户名',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `name` varchar(64) DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) DEFAULT NULL COMMENT 'Email',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  `out_login_time` datetime DEFAULT NULL COMMENT '退出登录时间',
  `login_ip` varchar(128) DEFAULT NULL COMMENT '登录IP',
  `credentials_non_expired` varchar(256) DEFAULT NULL COMMENT '证书',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账户是否过期',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '是否锁定',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('524660271021555712', '1', '1', '2018-12-18 10:52:37', 'admin', '2018-12-18 11:08:16', null, '15277578023', '$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6', '梁金龙', '1198127035@qq.com', '2018-12-18 11:08:15', '2018-12-18 10:52:36', '192.168.31.12', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('524660271269019648', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '13978561915', '$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6', '周杰伦', '657603467@qq.com', '2018-12-18 10:52:36', '2018-12-18 10:52:36', '10.26.66.125', '1', '1', '1');
INSERT INTO `sys_user` VALUES ('524660271478734848', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '13978561241', '$2a$10$yw/jduKFFT9PNpiaKZ.s6.gkAh33BBFov2ERElVhXuwFdNxplfkh6', '吴彦祖', '1037331293@qq.com', '2018-12-18 10:52:36', '2018-12-18 10:52:36', '115.66.40.125', '1', '1', '1');

/*
Navicat MySQL Data Transfer

Source Server         : MyServer
Source Server Version : 50718
Source Host           : 39.108.82.13:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-21 09:35:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(18) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user_name` varchar(64) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `methods` int(11) DEFAULT NULL COMMENT '权限允许以什么Request方法来访问此资源',
  PRIMARY KEY (`id`),
  KEY `KEY_SYS_ROLE_RESOURCE_USER_ID` (`role_id`) USING BTREE,
  KEY `KEY_SYS_ROLE_RESOURCE_RESOURCE_ID` (`resource_id`) USING BTREE,
  CONSTRAINT `FK_SYS_ROLE_RESOURCE_RESOURCE_ID` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`),
  CONSTRAINT `FK_SYS_ROLE_RESOURCE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('524660271680061440', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270589542400', '524660269687767040', '15');
INSERT INTO `sys_role_resource` VALUES ('524660271931719680', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270589542400', '524660270312718336', '15');
INSERT INTO `sys_role_resource` VALUES ('524660272120463360', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270589542400', '524660270363049984', '15');
INSERT INTO `sys_role_resource` VALUES ('524660272330178560', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270589542400', '524660268899237888', '15');

/*
Navicat MySQL Data Transfer

Source Server         : MyServer
Source Server Version : 50718
Source Host           : 39.108.82.13:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2018-12-21 09:35:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(18) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `enabled` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `create_user_name` varchar(64) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_user_name` varchar(64) DEFAULT NULL,
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`id`),
  KEY `KEY_SYS_USER_ROLE_USER_ID` (`user_id`) USING BTREE,
  KEY `KEY_SYS_USER_ROLE_ROLE_ID` (`role_id`) USING BTREE,
  CONSTRAINT `FK_SYS_USER_ROLE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_USER_ROLE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('524660272586031104', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270589542400', '524660271021555712');
INSERT INTO `sys_user_role` VALUES ('524660272816717824', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270816034816', '524660271269019648');
INSERT INTO `sys_user_role` VALUES ('524660273013850112', '1', '1', '2018-12-18 10:52:37', 'admin', null, null, '524660270816034816', '524660271478734848');
