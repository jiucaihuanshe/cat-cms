CREATE DATABASE cat_sys DEFAULT CHARACTER SET utf8;

USE cat_sys

CREATE TABLE `cat_roles` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) DEFAULT NULL COMMENT '角色名称',
  `note` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时 间',
  `createdUser` VARCHAR(20) DEFAULT NULL COMMENT '创建 用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8 COMMENT='角色' 

CREATE TABLE `cat_users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL,
  `password` VARCHAR(100),
  `salt` VARCHAR(50), 
  `email` VARCHAR(100),
  `mobile` VARCHAR(100),
  `valid` TINYINT(4),
  `createdTime` DATETIME,
  `modifiedTime` DATETIME,
  `createdUser` VARCHAR(20),
  `modifiedUser` VARCHAR(20),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=INNODB  DEFAULT CHARSET=utf8;

CREATE TABLE `cat_menus` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL COMMENT '资源名称',
  `url` VARCHAR(200) DEFAULT NULL COMMENT '资源URL',
  `type` INT(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `sort` INT(11) DEFAULT NULL COMMENT '排序',
  `note` VARCHAR(100) DEFAULT NULL COMMENT '备注',
  `parentId` INT(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `permission` VARCHAR(500) DEFAULT NULL COMMENT '授权(如：role:create)',
  `createdTime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` DATETIME DEFAULT NULL COMMENT '修改时间',
  `createdUser` VARCHAR(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` VARCHAR(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
