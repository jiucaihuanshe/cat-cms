/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.40 : Database - cat_sys
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cat_sys` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cat_sys`;

/*Table structure for table `cat_menus` */

DROP TABLE IF EXISTS `cat_menus`;

CREATE TABLE `cat_menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `type` int(11) DEFAULT NULL COMMENT '类型     1：菜单   2：按钮',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `parentId` int(11) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `permission` varchar(500) DEFAULT NULL COMMENT '授权(如：role:create)',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `cat_menus` */

insert  into `cat_menus`(`id`,`name`,`url`,`type`,`sort`,`note`,`parentId`,`permission`,`createdTime`,`modifiedTime`,`createdUser`,`modifiedUser`) values (1,'系统管理','/listUI.do',1,1,NULL,NULL,'index:listUI','2018-07-23 08:58:03','2018-07-23 08:58:03',NULL,NULL),(2,'用户管理','user/doFindPageObjects',1,1,NULL,1,'sys:user:view','2018-07-23 09:00:48','2018-07-23 09:00:48',NULL,NULL),(3,'菜单管理','menu/doFindObjects',1,1,NULL,1,'sys:menu:view','2018-07-23 09:11:20','2018-07-23 09:11:20',NULL,NULL),(4,'角色管理','role/doFindPageObjects',1,1,NULL,1,'sys:role:view','2018-07-24 16:35:34','2018-07-24 16:35:34',NULL,NULL),(5,'角色添加','role/doSaveUpdate',2,2,NULL,4,'sys:role:create','2018-07-24 16:36:33','2018-07-24 16:36:33',NULL,NULL),(6,'角色修改','role/doUpdateObject',2,3,NULL,4,'sys:role:update','2018-07-24 16:37:22','2018-07-24 16:37:22',NULL,NULL),(7,'角色删除','role/doDeleteObject',2,4,NULL,4,'sys:role:delete','2018-07-25 10:07:28','2018-07-25 10:07:28',NULL,NULL),(8,'用户添加','user/doInsertObject',2,2,NULL,2,'sys:user:create','2018-07-25 10:11:44','2018-07-25 10:11:44',NULL,NULL),(9,'用户修改','user/doUpdateObject',2,3,NULL,2,'sys:user:update','2018-07-25 10:12:46','2018-07-25 10:12:46',NULL,NULL),(10,'用户删除','user/doDeleteObject',2,4,NULL,2,'sys:user:delete','2018-07-25 10:13:18','2018-07-25 10:13:18',NULL,NULL),(11,'菜单添加','menu/doInsertObject',2,2,NULL,3,'sys:menu:create','2018-07-25 10:16:37','2018-07-25 10:16:37',NULL,NULL),(12,'菜单修改','menu/doUpdateObject',2,3,NULL,3,'sys:menu:update','2018-07-25 10:17:11','2018-07-25 10:17:11',NULL,NULL),(13,'菜单删除','menu/doDeleteObject',2,4,NULL,3,'sys:menu:delete','2018-07-25 10:17:44','2018-07-25 10:17:44',NULL,NULL),(14,'启/禁用','user/doValidById',2,5,NULL,2,'sys:user:valid','2018-07-25 10:18:39','2018-07-25 10:18:39',NULL,NULL);

/*Table structure for table `cat_roles` */

DROP TABLE IF EXISTS `cat_roles`;

CREATE TABLE `cat_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `createdTime` datetime DEFAULT NULL COMMENT '创建时间',
  `modifiedTime` datetime DEFAULT NULL COMMENT '修改时 间',
  `createdUser` varchar(20) DEFAULT NULL COMMENT '创建 用户',
  `modifiedUser` varchar(20) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色';

/*Data for the table `cat_roles` */

insert  into `cat_roles`(`id`,`name`,`note`,`createdTime`,`modifiedTime`,`createdUser`,`modifiedUser`) values (7,'系统管理','系统管理...','2018-07-23 08:55:23','2018-07-25 10:20:15',NULL,'admin'),(8,'角色管理','角色管理...','2018-07-23 08:55:41','2018-07-25 10:21:08',NULL,'admin'),(9,'用户管理','用户管理...','2018-07-23 08:55:53','2018-07-25 10:21:16',NULL,'admin'),(11,'菜单管理','菜单管理...','2018-07-25 10:21:32','2018-07-25 10:21:32',NULL,NULL),(12,'角色添加','只有角色添加权限','2018-07-25 10:22:17','2018-07-25 10:22:17',NULL,NULL),(13,'角色修改','只有角色修改权限','2018-07-25 10:22:30','2018-07-25 10:22:30',NULL,NULL),(14,'角色删除','只有角色删除权限','2018-07-25 10:22:47','2018-07-25 10:22:47',NULL,NULL),(15,'用户添加','只有用户添加权限','2018-07-25 10:23:07','2018-07-25 10:23:07',NULL,NULL),(16,'用户修改','只有用户修改权限','2018-07-25 10:23:26','2018-07-25 10:23:26',NULL,NULL),(17,'用户删除','只有用户删除权限','2018-07-25 10:23:38','2018-07-25 10:23:38',NULL,NULL),(18,'用户启/禁用','只有用户启/禁用权限','2018-07-25 10:24:06','2018-07-25 10:24:06',NULL,NULL),(19,'菜单添加','只有菜单添加权限','2018-07-25 10:24:29','2018-07-25 10:24:29',NULL,NULL),(20,'菜单修改','只有菜单修改权限','2018-07-25 10:24:44','2018-07-25 10:24:44',NULL,NULL),(21,'菜单删除','只有菜单删除权限','2018-07-25 10:24:54','2018-07-25 10:37:57',NULL,'admin');

/*Table structure for table `cat_roles_menus` */

DROP TABLE IF EXISTS `cat_roles_menus`;

CREATE TABLE `cat_roles_menus` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `menu_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cat_roles_menus` */

insert  into `cat_roles_menus`(`role_id`,`menu_id`) values (7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7),(7,8),(7,9),(7,10),(7,11),(7,12),(7,13),(7,14),(8,1),(8,4),(8,5),(8,6),(8,7),(9,1),(9,2),(9,8),(9,9),(9,10),(9,14),(11,1),(11,3),(11,11),(11,12),(11,13),(12,1),(12,4),(12,5),(13,1),(13,4),(13,6),(14,1),(14,4),(14,7),(15,1),(15,2),(15,8),(16,1),(16,2),(16,9),(17,1),(17,2),(17,10),(18,1),(18,2),(18,14),(19,1),(19,3),(19,11),(20,1),(20,3),(20,12),(21,1),(21,3),(21,13);

/*Table structure for table `cat_roles_users` */

DROP TABLE IF EXISTS `cat_roles_users`;

CREATE TABLE `cat_roles_users` (
  `user_id` int(11) NOT NULL DEFAULT '0',
  `role_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cat_roles_users` */

insert  into `cat_roles_users`(`user_id`,`role_id`) values (5,7),(6,8),(7,18),(8,9),(9,16);

/*Table structure for table `cat_users` */

DROP TABLE IF EXISTS `cat_users`;

CREATE TABLE `cat_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `valid` tinyint(4) DEFAULT NULL,
  `createdTime` datetime DEFAULT NULL,
  `modifiedTime` datetime DEFAULT NULL,
  `createdUser` varchar(20) DEFAULT NULL,
  `modifiedUser` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `cat_users` */

insert  into `cat_users`(`id`,`username`,`password`,`salt`,`email`,`mobile`,`valid`,`createdTime`,`modifiedTime`,`createdUser`,`modifiedUser`) values (5,'admin','1b84b2db911332bc8ab6d16bfe412319','6ffc7e9f-d99c-42ee-a528-33a3198cc473','admin@qq.com','13655448594',1,'2018-07-23 16:55:29','2018-07-25 10:52:41',NULL,'admin'),(6,'tedu','6ccb5d37bab8f825a0a9970766d42867','e3f17309-b54d-42b5-b026-6771d670a29f','tedu@qq.com','13957541235',1,'2018-07-24 16:40:21','2018-07-24 16:44:29',NULL,'admin'),(7,'tom','561807cd1331e582ab0471796ddebb60','1f7cf17d-ab53-4447-a5ce-f020eedd8eec','tom@qq.com','16536265465',0,'2018-07-25 11:07:26','2018-07-25 13:33:52','tedu','admin'),(8,'jack','bdfbb23a981cc593ec1e1b5ffa9c2c88','d69fae35-b643-4d3f-93c7-7d6b02e18b51','jack@qq.com','13526547854',1,'2018-07-25 13:30:59','2018-07-25 13:32:42','tedu','admin'),(9,'cc','229353cfe5b4dc9527d8736734a9de6e','0a61f2e8-0bf0-446d-a13e-e59cf2921bee','cc@qq.com','13622547895',0,'2018-07-25 13:33:24','2018-07-26 10:52:35','admin','admin');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
