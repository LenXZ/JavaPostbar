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

 Date: 06/07/2018 10:35:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authority` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001', 'okarin', '001', '男', 'A');
INSERT INTO `user` VALUES ('002', 'mayoshi', '002', '女', 'A');
INSERT INTO `user` VALUES ('003', 'daru', '003', '男', 'A');
INSERT INTO `user` VALUES ('004', 'kurisu', '004', '女', 'S');
INSERT INTO `user` VALUES ('005', 'moe', '005', '女', 'A');
INSERT INTO `user` VALUES ('006', 'ryoka', '006', '男', 'A');
INSERT INTO `user` VALUES ('007', 'ferisu', '007', '女', 'A');
INSERT INTO `user` VALUES ('008', 'sutsuha', '008', '女', 'A');
INSERT INTO `user` VALUES ('009', 'maho', '009', '女', 'A');
INSERT INTO `user` VALUES ('010', 'kagari', '010', '女', 'A');
INSERT INTO `user` VALUES ('011', 'amadeus kurisu', '011', '女', 'A');
INSERT INTO `user` VALUES ('012', 'amadeus maho', '012', '女', 'A');
INSERT INTO `user` VALUES ('013', '何意 m', '012', '男', 'A');

SET FOREIGN_KEY_CHECKS = 1;
