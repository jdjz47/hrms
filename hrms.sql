/*
Navicat MySQL Data Transfer

Source Server         : heima
Source Server Version : 50536
Source Host           : localhost:3306
Source Database       : hrms

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2023-12-18 16:14:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `advertiseinfo`
-- ----------------------------
DROP TABLE IF EXISTS `advertiseinfo`;
CREATE TABLE `advertiseinfo` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `companyId` int(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `sex` varchar(50) DEFAULT NULL,
  `age` varchar(50) DEFAULT NULL,
  `eduBackground` varchar(50) DEFAULT NULL,
  `experience` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `school` varchar(50) DEFAULT NULL,
  `speciality` varchar(50) DEFAULT NULL,
  `undergo` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of advertiseinfo
-- ----------------------------
INSERT INTO `advertiseinfo` VALUES ('7', '1', 'czp', '全栈开发', '男', '21', '研究生', '4年', '1840854390@qq.com', '东华大学', '软件工程', '腾讯实习', '0', '研发部');

-- ----------------------------
-- Table structure for `companymanager`
-- ----------------------------
DROP TABLE IF EXISTS `companymanager`;
CREATE TABLE `companymanager` (
  `id` int(10) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of companymanager
-- ----------------------------
INSERT INTO `companymanager` VALUES ('0', 'admin4', 'admin3', 'admin');
INSERT INTO `companymanager` VALUES ('1', '腾讯', 'admin', 'admin');
INSERT INTO `companymanager` VALUES ('2', '字节跳动', 'admin1', '123456');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `salary` varchar(50) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `position` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `stage` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `companyId` int(20) DEFAULT NULL,
  `day_time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', 'czp1', '10000', '研发部', '后端开发', '0', '2', '1840854390@qq.com', '1', '10');
INSERT INTO `employee` VALUES ('2', 'czp', '5000', '研发部', '后端开发', '2', '0', '1840854390@qq.com', '1', '50');
INSERT INTO `employee` VALUES ('3', 'czp', '8000', '研发部', '后端开发', '2', '0', '1840854390@qq.com', '2', '120');
INSERT INTO `employee` VALUES ('4', 'lz', '2000', '后勤', '厕所', '0', '2', '2374001709@qq.com', '2', '180');
INSERT INTO `employee` VALUES ('5', 'czp', '5000', '研发部', '后端开发', '0', '2', '18540854390@qq.com', '2', '160');
INSERT INTO `employee` VALUES ('6', 'czp', '5000', '研发部', '后端开发', '2', '0', '1840854390@qq.com', '1', '80');
INSERT INTO `employee` VALUES ('7', 'test', '5000', '研发部', '后端开发', '2', '0', '1840854390@qq.com', '1', '105');
INSERT INTO `employee` VALUES ('8', 'test3', '5000', '研发部', '前端开发', '2', '0', '1840854390@qq.com', '1', null);
INSERT INTO `employee` VALUES ('9', 'dxj', '5000', '研发部', '前端开发', '1', '2', '2374001709@qq.com', '1', null);

-- ----------------------------
-- Table structure for `regular`
-- ----------------------------
DROP TABLE IF EXISTS `regular`;
CREATE TABLE `regular` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `salary` varchar(50) DEFAULT NULL,
  `companyId` varchar(50) DEFAULT NULL,
  `reward` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1058115587 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of regular
-- ----------------------------
INSERT INTO `regular` VALUES ('-1525592063', '研发部', '5000', '1', '5000');
INSERT INTO `regular` VALUES ('-451936254', 'houqing ', '100', '2', '100');
INSERT INTO `regular` VALUES ('517033986', '研发部门', '5000', '1', '5000');
INSERT INTO `regular` VALUES ('869384194', '研发部', '5000', '2', '50000');
INSERT INTO `regular` VALUES ('1058115586', '前端开发', '50000', '1', '10000');

-- ----------------------------
-- Table structure for `upload`
-- ----------------------------
DROP TABLE IF EXISTS `upload`;
CREATE TABLE `upload` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `url` varchar(500) DEFAULT NULL,
  `companyId` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of upload
-- ----------------------------
INSERT INTO `upload` VALUES ('1', '2023-12-135wage.xlsx', '2023-12-13', 'http://guli-czp.oss-cn-hangzhou.aliyuncs.com/1/2023-12-135wage.xlsx', '1');
INSERT INTO `upload` VALUES ('2', '2023-12-136wage.xlsx', '2023-12-13', 'http://guli-czp.oss-cn-hangzhou.aliyuncs.com/1/2023-12-136wage.xlsx', '1');
INSERT INTO `upload` VALUES ('3', '2023-12-171wage.xlsx', '2023-12-17', 'http://guli-czp.oss-cn-hangzhou.aliyuncs.com/2/2023-12-171wage.xlsx', '2');
INSERT INTO `upload` VALUES ('4', '2023-12-171wage.xlsx', '2023-12-17', 'http://guli-czp.oss-cn-hangzhou.aliyuncs.com/2/2023-12-171wage.xlsx', '2');
INSERT INTO `upload` VALUES ('5', '2023-12-181wage.xlsx', '2023-12-18', 'http://guli-czp.oss-cn-hangzhou.aliyuncs.com/1/2023-12-181wage.xlsx', '1');
