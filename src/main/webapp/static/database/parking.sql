/*
Navicat MySQL Data Transfer

Source Server         : MyConnection
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : parking

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-08-08 00:36:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `account` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL COMMENT '密码',
  `name` varchar(20) DEFAULT NULL COMMENT '名字',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin1', '123', '小张');
INSERT INTO `admin` VALUES ('2', 'admin2', '123', '小轩');
INSERT INTO `admin` VALUES ('3', 'admin', 'admin', '小李');
INSERT INTO `admin` VALUES ('4', '111', '111', '111');
INSERT INTO `admin` VALUES ('5', '222', '222', '222');
INSERT INTO `admin` VALUES ('6', '333', '333', '333');

-- ----------------------------
-- Table structure for `announcement`
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '公告id',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `context` varchar(200) DEFAULT NULL COMMENT '内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  PRIMARY KEY (`id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `announcement_ibfk_1` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '天河区车位降价', '赶紧来停车！！！', '2018-07-31 16:52:33', '1');
INSERT INTO `announcement` VALUES ('2', '加入会员7折优惠', '还不赶紧来！！！', '2018-07-25 16:53:11', '2');
INSERT INTO `announcement` VALUES ('9', '打骨折', '快来啊', '2018-08-07 15:50:45', '3');
INSERT INTO `announcement` VALUES ('10', '三折抢购', '优惠多多', '2018-08-07 15:52:41', '3');
INSERT INTO `announcement` VALUES ('16', '四折抢购', '快快快', '2018-08-07 18:24:17', '3');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '留言id',
  `comment` varchar(200) DEFAULT NULL COMMENT '留言内容',
  `com_time` datetime DEFAULT NULL COMMENT '留言时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `replication` varchar(200) DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime DEFAULT NULL COMMENT '回复时间',
  `admin_id` int(11) DEFAULT NULL COMMENT '管理员id',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `admin_id` (`admin_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`admin_id`) REFERENCES `admin` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '111111', '2018-08-06 16:39:54', '1', '11111', '2018-08-07 22:55:58', '1');
INSERT INTO `comment` VALUES ('2', '22222', '2018-08-07 22:56:30', '2', '22222', '2018-08-07 22:57:12', '2');
INSERT INTO `comment` VALUES ('3', '3333', '2018-08-07 22:56:48', '3', '1111', '2018-08-07 23:47:38', '3');

-- ----------------------------
-- Table structure for `lot`
-- ----------------------------
DROP TABLE IF EXISTS `lot`;
CREATE TABLE `lot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车位id',
  `number` varchar(200) DEFAULT NULL COMMENT '编号',
  `price` double DEFAULT NULL COMMENT '价格',
  `address` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lot
-- ----------------------------
INSERT INTO `lot` VALUES ('1', '车位一', '100', '广州市白云区广新路388号', '0');
INSERT INTO `lot` VALUES ('2', '车位二', '150', '广州市海珠区仲恺农业工程学院', '1');
INSERT INTO `lot` VALUES ('3', '车位三', '200', '广州市海珠区广州塔', '1');
INSERT INTO `lot` VALUES ('4', '车位四', '100', '广州市天河区广东技术师范学院', '1');
INSERT INTO `lot` VALUES ('5', '车位五', '150', '广州市白云区广新路388号', '1');
INSERT INTO `lot` VALUES ('6', '车位六', '200', '广州市白云区广新路388号', '0');
INSERT INTO `lot` VALUES ('7', '车位七', '150', '广州市白云区广新路388号', '1');
INSERT INTO `lot` VALUES ('8', '车位八', '100', '广州市白云区广新路388号', '0');
INSERT INTO `lot` VALUES ('9', '车位九', '200', '广州市海珠区仲恺农业工程学院', '0');
INSERT INTO `lot` VALUES ('10', '车位十', '150', '广州市天河区广东技术师范学院', '0');
INSERT INTO `lot` VALUES ('11', '车位十一', '50', '广州市仲恺农业工程学院海珠校区', '0');

-- ----------------------------
-- Table structure for `orders`
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `lot_id` int(11) NOT NULL,
  `order_time` datetime DEFAULT NULL,
  `price` double NOT NULL,
  `total` double NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userid` (`user_id`),
  KEY `lotid` (`lot_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`lot_id`) REFERENCES `lot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('6', '1', '5', '2018-08-06 16:45:08', '150', '150', '0');
INSERT INTO `orders` VALUES ('9', '2', '4', '2018-08-07 21:57:00', '100', '100', '1');
INSERT INTO `orders` VALUES ('10', '3', '6', '2018-08-07 21:57:27', '200', '200', '0');
INSERT INTO `orders` VALUES ('11', '5', '7', '2018-08-07 21:57:50', '150', '150', '1');
INSERT INTO `orders` VALUES ('12', '3', '8', '2018-08-07 21:59:28', '100', '100', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户流水号',
  `account` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `license_num` varchar(200) DEFAULT NULL,
  `identify_num` varchar(200) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  `integration` double DEFAULT NULL,
  `point` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '5555', '123', '13911222333', 'tom', 'MR110', null, '2018-07-19 10:39:05', '0', null, '0');
INSERT INTO `user` VALUES ('2', '8888', '123', '15511122233', 'jack', null, null, '2018-07-19 10:40:20', '1', null, '0');
INSERT INTO `user` VALUES ('3', '4444', '123', '13922111555', 'lily', null, null, '2018-07-20 09:55:02', '0', null, '10');
INSERT INTO `user` VALUES ('5', '6666', '123', '13602289756', 'mike', 'M110', null, '2018-08-06 18:18:07', '1', null, '0');
INSERT INTO `user` VALUES ('6', '7777', '123', '13632289756', 'mill', 'YD110', null, '2018-08-06 18:21:12', '0', null, '0');
INSERT INTO `user` VALUES ('7', '3333', '123', '10086', 'curry', 'M150', null, '2018-08-06 18:22:33', '0', null, '0');
INSERT INTO `user` VALUES ('8', '2222', '123', '10010', 'james', 'ZH110', null, '2018-08-06 18:24:25', '0', null, '0');
