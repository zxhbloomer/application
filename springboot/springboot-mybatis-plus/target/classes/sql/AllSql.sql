-- 资源表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL,
	`status` tinyint(1) DEFAULT 1,
  `enabled` tinyint(1) DEFAULT 1,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`create_user_name` varchar(64) DEFAULT NULL,
	`update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`update_user_name` varchar(64) DEFAULT NULL,

	`order_no` int DEFAULT NULL COMMENT '资源排序号',
	`resource_level` int DEFAULT NULL COMMENT '资源等级',
	`resource_parent` bigint(20) DEFAULT NULL COMMENT '资源上级ID',
  `resource_name` varchar(128) NOT NULL COMMENT '资源名称',
  `resource_desc` varchar(128) DEFAULT NULL COMMENT '资源描述',
  `resource_path` varchar(128) DEFAULT NULL COMMENT '资源路径',
  `resource_icon` varchar(128) DEFAULT NULL COMMENT '资源图标',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 角色表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL,
	`status` tinyint(1) DEFAULT 1,
  `enabled` tinyint(1) DEFAULT 1,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`create_user_name` varchar(64) DEFAULT NULL,
	`update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`update_user_name` varchar(64) DEFAULT NULL,

  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `role_desc` varchar(256) DEFAULT NULL COMMENT '角色描述',
  `role_auth` varchar(64) DEFAULT NULL COMMENT '角色权限',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 用户表
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL,
	`status` tinyint(1) DEFAULT 1,
  `enabled` tinyint(1) DEFAULT 1,
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
-- User To Role
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL,
	`status` tinyint(1) DEFAULT 1,
  `enabled` tinyint(1) DEFAULT 1,
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP,
	`create_user_name` varchar(64) DEFAULT NULL,
	`update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
	`update_user_name` varchar(64) DEFAULT NULL,

  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',

  PRIMARY KEY (`id`),
  KEY `KEY_SYS_USER_ROLE_USER_ID` (`user_id`) USING BTREE,
  KEY `KEY_SYS_USER_ROLE_ROLE_ID` (`role_id`) USING BTREE,
  CONSTRAINT `FK_SYS_USER_ROLE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FK_SYS_USER_ROLE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- Role To Resource
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
	`id` bigint(20) NOT NULL,
	`status` tinyint(1) DEFAULT 1,
  `enabled` tinyint(1) DEFAULT 1,
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
  CONSTRAINT `FK_SYS_ROLE_RESOURCE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_SYS_ROLE_RESOURCE_RESOURCE_ID` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

