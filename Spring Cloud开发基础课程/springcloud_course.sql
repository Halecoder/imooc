/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : springcloud_course

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 19/10/2022 22:36:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL COMMENT '课程id',
  `course_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程名',
  `valid` int NOT NULL COMMENT '课程上架状态：0 下架，1 上架;',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES (1, 362, 'Java并发核心知识体系精讲', 1);
INSERT INTO `course` VALUES (2, 409, '玩转Java并发工具，精通JUC，成为并发多面手', 1);
INSERT INTO `course` VALUES (3, 121, 'Nginx入门到实践-Nginx中间件', 0);

-- ----------------------------
-- Table structure for course_price
-- ----------------------------
DROP TABLE IF EXISTS `course_price`;
CREATE TABLE `course_price`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int NOT NULL COMMENT '课程id',
  `price` int NOT NULL COMMENT '课程价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_price
-- ----------------------------
INSERT INTO `course_price` VALUES (1, 362, 348);
INSERT INTO `course_price` VALUES (2, 409, 399);
INSERT INTO `course_price` VALUES (3, 121, 266);

SET FOREIGN_KEY_CHECKS = 1;
