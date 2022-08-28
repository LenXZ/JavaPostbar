create database amadeus;
use amadeus;
create table user(id varchar(20) primary key,user varchar(20) not null,
pwd varchar(20) not null, gender varchar(4),authority varchar(8) not null)engine=innoDB charset=utf8;
insert into user values ('001','okarin','001','男','A');
insert into user values ('002','mayoshi','002','女','A');
insert into user values ('003','daru','003','男','A');
insert into user values ('004','kurisu','004','女','S');
insert into user values ('005','moe','005','女','A');
insert into user values ('006','ryoka','006','男','A');
insert into user values ('007','ferisu','007','女','A');
insert into user values ('008','sutsuha','008','女','A');
insert into user values ('009','maho','009','女','A');
insert into user values ('010','kagari','010','女','A');
insert into user values ('011','amadeus kurisu','011','女','A');
insert into user values ('012','amadeus maho','012','女','A');
insert into user values ('013','何意 maho','012','男','A');
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
INSERT INTO `post` VALUES ('A10000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100003', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100004', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100005', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A10000100006', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A20000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('A20000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100000', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100001', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100002', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100003', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100004', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100005', '001', '2019.08.09', '这是一条帖子');
INSERT INTO `post` VALUES ('B10000100006', '001', '2019.08.09', '这是一条帖子');
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