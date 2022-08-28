/*
 Navicat MySQL Data Transfer

 Source Server         : amadeus
 Source Server Type    : MySQL
 Source Server Version : 50520
 Source Host           : localhost:3306
 Source Schema         : amadeus

 Target Server Type    : MySQL
 Target Server Version : 50520
 File Encoding         : 65001

 Date: 06/07/2018 10:35:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `postid` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `date` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `text` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  PRIMARY KEY (`postid`) USING BTREE,
  INDEX `userid`(`userid`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('A10000100000', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100002', '001', '2018.07.06. 10:08:18', '这是一dddddd条帖子');
INSERT INTO `post` VALUES ('A10000100003', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100004', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100005', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100006', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100007', '003', '2018.07.06. 10:08:22', 'sdfsadfasdfsdf');
INSERT INTO `post` VALUES ('A20000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A20000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100000', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100003', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100004', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100005', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100006', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10001100000', '006', '2018.07.06. 08:00:56', 'dfgsdfgfdsgsdfg');
INSERT INTO `post` VALUES ('B20000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B20000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100000', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100001', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100002', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100003', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100004', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100005', '004', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C10000100006', '005', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C20000100001', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('C20000100002', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100000', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100001', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100002', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100003', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100004', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100005', '004', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D10000100006', '005', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D20000100001', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('D20000100002', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100000', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100001', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100002', '002', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100003', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100004', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100005', '004', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E10000100006', '005', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E20000100001', '003', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('E20000100002', '002', '2019.08.09', '这是一条帖子');

SET FOREIGN_KEY_CHECKS = 1;
