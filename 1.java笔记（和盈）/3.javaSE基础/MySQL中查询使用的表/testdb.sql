/*
 Navicat Premium Data Transfer

 Source Server         : Administor
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : testdb

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 05/05/2020 22:18:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bonus
-- ----------------------------
DROP TABLE IF EXISTS `bonus`;
CREATE TABLE `bonus`  (
  `ENAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `DEPTNO` int(2) NOT NULL,
  `DNAME` varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `LOC` varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`DEPTNO`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (10, 'ACCOUNTING', 'NEW YORK');
INSERT INTO `dept` VALUES (20, 'RESEARCH', 'DALLAS');
INSERT INTO `dept` VALUES (30, 'SALES', 'CHICAGO');
INSERT INTO `dept` VALUES (40, 'OPERATIONS', 'BOSTON');

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `EMPNO` int(4) NOT NULL,
  `ENAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MGR` int(4) NULL DEFAULT NULL,
  `HIREDATE` date NULL DEFAULT NULL,
  `SAL` double(7, 2) NULL DEFAULT NULL,
  `COMM` int(4) NULL DEFAULT NULL,
  `DEPTNO` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`EMPNO`) USING BTREE,
  INDEX `FK_DEPTNO`(`DEPTNO`) USING BTREE,
  CONSTRAINT `FK_DEPTNO` FOREIGN KEY (`DEPTNO`) REFERENCES `dept` (`DEPTNO`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (7369, 'SMITH', 'CLERK', 7902, '1980-12-17', 800.00, NULL, 20);
INSERT INTO `emp` VALUES (7499, 'ALLEN', 'SALESMAN', 7698, '1981-02-10', 1600.00, 300, 30);
INSERT INTO `emp` VALUES (7521, 'WARD', 'SALESMAN', 7698, '1981-02-11', 1100.00, 500, 30);
INSERT INTO `emp` VALUES (7566, 'JONES', 'MANAGER', 7839, '1981-02-04', 2975.00, NULL, 20);
INSERT INTO `emp` VALUES (7654, 'MARTIN', 'SALESMAN', 7698, '1981-09-28', 1100.00, 1400, 30);
INSERT INTO `emp` VALUES (7698, 'BLAKE', 'MANAGER', 7839, '1981-05-01', 2850.00, NULL, 30);
INSERT INTO `emp` VALUES (7777, 'Tom', 'Clert', NULL, '2020-05-12', 2000.00, NULL, 30);
INSERT INTO `emp` VALUES (7782, 'CLARK', 'MANAGER', 7839, '1981-06-09', 2850.00, NULL, 10);
INSERT INTO `emp` VALUES (7788, 'SCOTT', 'ANALYST', 7566, '1987-06-13', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7839, 'KING', 'PRESIDENT', NULL, '1981-11-17', 5000.00, NULL, 10);
INSERT INTO `emp` VALUES (7844, 'TURNER', 'SALESMAN', 7698, '1981-09-08', 1500.00, 0, 30);
INSERT INTO `emp` VALUES (7876, 'ADAMS', 'CLERK', 7788, '1987-06-13', 1100.00, NULL, 20);
INSERT INTO `emp` VALUES (7900, 'JAMES', 'CLERK', 7698, '1981-12-03', 950.00, NULL, 30);
INSERT INTO `emp` VALUES (7902, 'FORD', 'ANALYST', 7566, '1981-12-03', 3000.00, NULL, 20);
INSERT INTO `emp` VALUES (7934, 'MILLER', 'CLERK', 7782, '1982-02-13', 1500.00, NULL, NULL);

-- ----------------------------
-- Table structure for salgrade
-- ----------------------------
DROP TABLE IF EXISTS `salgrade`;
CREATE TABLE `salgrade`  (
  `GRADE` int(11) NULL DEFAULT NULL,
  `LOSAL` double(7, 2) NULL DEFAULT NULL,
  `HISAL` double(7, 2) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salgrade
-- ----------------------------
INSERT INTO `salgrade` VALUES (1, 700.00, 1200.00);
INSERT INTO `salgrade` VALUES (2, 1201.00, 1400.00);
INSERT INTO `salgrade` VALUES (3, 1401.00, 2000.00);
INSERT INTO `salgrade` VALUES (4, 2001.00, 3000.00);
INSERT INTO `salgrade` VALUES (5, 3001.00, 9999.00);

-- ----------------------------
-- Table structure for team
-- ----------------------------
DROP TABLE IF EXISTS `team`;
CREATE TABLE `team`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of team
-- ----------------------------
INSERT INTO `team` VALUES (1, '中国队');
INSERT INTO `team` VALUES (2, '美国队');
INSERT INTO `team` VALUES (3, '韩国队');
INSERT INTO `team` VALUES (4, '日本队');

-- ----------------------------
-- Table structure for worker
-- ----------------------------
DROP TABLE IF EXISTS `worker`;
CREATE TABLE `worker`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `job` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of worker
-- ----------------------------
INSERT INTO `worker` VALUES (1, '小明', 'teacher');
INSERT INTO `worker` VALUES (2, '小丽', 'doctor');
INSERT INTO `worker` VALUES (3, '小王', 'policement');
INSERT INTO `worker` VALUES (4, '小李', 'teacher');

SET FOREIGN_KEY_CHECKS = 1;
