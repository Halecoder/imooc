/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : imooc_oa

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 26/09/2022 16:17:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for adm_department
-- ----------------------------
DROP TABLE IF EXISTS `adm_department`;
CREATE TABLE `adm_department`  (
  `department_id` bigint NOT NULL AUTO_INCREMENT,
  `department_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adm_department
-- ----------------------------
INSERT INTO `adm_department` VALUES (1, '总裁办');
INSERT INTO `adm_department` VALUES (2, '研发部');
INSERT INTO `adm_department` VALUES (3, '市场部');

-- ----------------------------
-- Table structure for adm_employee
-- ----------------------------
DROP TABLE IF EXISTS `adm_employee`;
CREATE TABLE `adm_employee`  (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `department_id` bigint NOT NULL,
  `title` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adm_employee
-- ----------------------------
INSERT INTO `adm_employee` VALUES (1, '张晓涛', 1, '总经理', 8);
INSERT INTO `adm_employee` VALUES (2, '齐紫陌', 2, '部门经理', 7);
INSERT INTO `adm_employee` VALUES (3, '王美美', 2, '高级研发工程师', 6);
INSERT INTO `adm_employee` VALUES (4, '宋彩妮', 2, '研发工程师', 5);
INSERT INTO `adm_employee` VALUES (5, '欧阳峰', 2, '初级研发工程师', 4);
INSERT INTO `adm_employee` VALUES (6, '张世豪', 3, '部门经理', 7);
INSERT INTO `adm_employee` VALUES (7, '王子豪', 3, '大客户经理', 6);
INSERT INTO `adm_employee` VALUES (8, '段峰', 3, '客户经理', 5);
INSERT INTO `adm_employee` VALUES (9, '章雪峰', 3, '客户经理', 4);
INSERT INTO `adm_employee` VALUES (10, '李莉', 3, '见习客户经理', 3);

-- ----------------------------
-- Table structure for adm_leave_form
-- ----------------------------
DROP TABLE IF EXISTS `adm_leave_form`;
CREATE TABLE `adm_leave_form`  (
  `form_id` bigint NOT NULL AUTO_INCREMENT COMMENT '请假单编号',
  `employee_id` bigint NOT NULL COMMENT '员工编号',
  `form_type` int NOT NULL COMMENT '请假类型 1-事假 2-病假 3-工伤假 4-婚假 5-产假 6-丧假',
  `start_time` datetime NOT NULL COMMENT '请假起始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `reason` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '请假事由',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `state` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'processing-正在审批 approved-审批已通过 refused-审批被驳回',
  PRIMARY KEY (`form_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adm_leave_form
-- ----------------------------
INSERT INTO `adm_leave_form` VALUES (40, 4, 1, '2020-03-25 08:00:00', '2020-04-01 18:00:00', '回家探亲', '2022-08-21 14:22:38', 'processing');
INSERT INTO `adm_leave_form` VALUES (41, 8, 1, '2020-03-26 08:00:00', '2020-04-01 18:00:00', '市场部员工请假单(72小时以上)', '2022-08-21 17:19:55', 'processing');
INSERT INTO `adm_leave_form` VALUES (42, 2, 1, '1970-01-20 03:05:40', '1970-01-20 03:07:07', '测试', '2022-08-21 17:48:58', 'processing');
INSERT INTO `adm_leave_form` VALUES (43, 2, 1, '1970-01-20 03:05:40', '1970-01-20 03:07:07', '测试', '2022-08-21 18:50:17', 'processing');
INSERT INTO `adm_leave_form` VALUES (44, 1, 2, '2022-08-18 00:00:00', '2022-09-13 00:00:00', 'vv', '2022-08-21 21:05:55', 'approved');
INSERT INTO `adm_leave_form` VALUES (45, 1, 3, '2022-08-18 00:00:00', '2022-09-13 00:00:00', 'rr', '2022-08-21 21:07:35', 'approved');
INSERT INTO `adm_leave_form` VALUES (46, 3, 1, '2022-08-03 00:00:00', '2022-09-05 00:00:00', '事假', '2022-08-22 12:41:41', 'processing');
INSERT INTO `adm_leave_form` VALUES (47, 3, 1, '2020-03-26 08:00:00', '2020-04-01 18:00:00', '研发部员工王美美请假单(72小时以上)', '2022-08-22 13:45:16', 'approved');
INSERT INTO `adm_leave_form` VALUES (48, 3, 1, '2020-03-26 08:00:00', '2020-04-01 18:00:00', '研发部员工王美美请假单(72小时以上)', '2022-08-22 13:48:37', 'refused');
INSERT INTO `adm_leave_form` VALUES (49, 3, 1, '2020-03-26 08:00:00', '2020-03-27 18:00:00', '研发部员工王美美请假单(72小时以内)', '2022-08-22 13:49:17', 'approved');
INSERT INTO `adm_leave_form` VALUES (50, 3, 1, '2022-08-01 00:00:00', '2022-08-29 00:00:00', '我在这里！申请大于72h请假单', '2022-08-22 14:05:00', 'approved');
INSERT INTO `adm_leave_form` VALUES (51, 3, 1, '2020-03-26 08:00:00', '2020-04-01 18:00:00', '研发部员工王美美请假单(72小时以上)', '2022-08-22 15:20:20', 'approved');
INSERT INTO `adm_leave_form` VALUES (52, 3, 1, '2020-03-26 08:00:00', '2020-04-01 18:00:00', '研发部员工王美美请假单(72小时以上)', '2022-08-22 15:20:38', 'refused');
INSERT INTO `adm_leave_form` VALUES (53, 3, 1, '2020-03-26 08:00:00', '2020-03-27 18:00:00', '研发部员工王美美请假单(72小时以内)', '2022-08-22 15:20:44', 'approved');

-- ----------------------------
-- Table structure for adm_process_flow
-- ----------------------------
DROP TABLE IF EXISTS `adm_process_flow`;
CREATE TABLE `adm_process_flow`  (
  `process_id` bigint NOT NULL AUTO_INCREMENT COMMENT '处理任务编号',
  `form_id` bigint NOT NULL COMMENT '表单编号',
  `operator_id` bigint NOT NULL COMMENT '经办人编号',
  `action` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'apply-申请  audit-审批',
  `result` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT 'approved-同意 refused-驳回',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '审批意见',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `audit_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `order_no` int NOT NULL COMMENT '任务序号',
  `state` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ready-准备 process-正在处理 complete-处理完成 cancel-取消',
  `is_last` int NOT NULL COMMENT '是否最后节点,0-否 1-是',
  PRIMARY KEY (`process_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of adm_process_flow
-- ----------------------------
INSERT INTO `adm_process_flow` VALUES (96, 3, 2, 'audit', 'approved', '同意', '2022-08-21 14:41:09', '2022-08-21 14:41:09', 1, 'ready', 1);
INSERT INTO `adm_process_flow` VALUES (97, 41, 8, 'apply', NULL, NULL, '2022-08-21 17:19:56', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (98, 41, 6, 'audit', NULL, NULL, '2022-08-21 17:19:56', NULL, 2, 'process', 0);
INSERT INTO `adm_process_flow` VALUES (99, 41, 1, 'audit', NULL, NULL, '2022-08-21 17:19:56', NULL, 3, 'ready', 1);
INSERT INTO `adm_process_flow` VALUES (100, 42, 2, 'apply', NULL, NULL, '2022-08-21 17:48:59', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (101, 42, 1, 'audit', NULL, NULL, '2022-08-21 17:48:59', NULL, 2, 'process', 1);
INSERT INTO `adm_process_flow` VALUES (102, 43, 2, 'apply', NULL, NULL, '2022-08-21 18:50:17', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (103, 43, 1, 'audit', NULL, NULL, '2022-08-21 18:50:17', NULL, 2, 'process', 1);
INSERT INTO `adm_process_flow` VALUES (104, 44, 1, 'apply', NULL, NULL, '2022-08-21 21:05:55', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (105, 44, 1, 'audit', 'approved', '自动通过', '2022-08-21 21:05:55', '2022-08-21 21:05:55', 2, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (106, 45, 1, 'apply', NULL, NULL, '2022-08-21 21:07:35', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (107, 45, 1, 'audit', 'approved', '自动通过', '2022-08-21 21:07:35', '2022-08-21 21:07:35', 2, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (108, 46, 3, 'apply', NULL, NULL, '2022-08-22 12:41:41', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (109, 46, 2, 'audit', NULL, NULL, '2022-08-22 12:41:41', NULL, 2, 'process', 0);
INSERT INTO `adm_process_flow` VALUES (110, 46, 1, 'audit', NULL, NULL, '2022-08-22 12:41:41', NULL, 3, 'ready', 1);
INSERT INTO `adm_process_flow` VALUES (111, 47, 3, 'apply', NULL, NULL, '2022-08-22 13:45:17', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (112, 47, 2, 'audit', 'approved', '部门经理同意', '2022-08-22 13:45:17', '2022-08-22 13:45:17', 2, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (113, 47, 1, 'audit', 'approved', '总经理同意', '2022-08-22 13:45:17', '2022-08-22 13:45:17', 3, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (114, 48, 3, 'apply', NULL, NULL, '2022-08-22 13:48:38', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (115, 48, 2, 'audit', 'refused', '部门经理拒绝', '2022-08-22 13:48:38', '2022-08-22 13:48:38', 2, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (116, 48, 1, 'audit', NULL, NULL, '2022-08-22 13:48:38', NULL, 3, 'cancel', 1);
INSERT INTO `adm_process_flow` VALUES (117, 49, 3, 'apply', NULL, NULL, '2022-08-22 13:49:18', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (118, 49, 2, 'audit', 'approved', '部门经理同意', '2022-08-22 13:49:18', '2022-08-22 13:49:18', 2, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (119, 50, 3, 'apply', NULL, NULL, '2022-08-22 14:05:00', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (120, 50, 2, 'audit', 'approved', '祝早日康复', '2022-08-22 14:05:00', '2022-08-22 14:41:00', 2, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (121, 50, 1, 'audit', 'approved', '祝早日康复', '2022-08-22 14:05:00', '2022-08-22 14:42:15', 3, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (122, 51, 3, 'apply', NULL, NULL, '2022-08-22 15:20:21', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (123, 51, 2, 'audit', 'approved', '部门经理同意', '2022-08-22 15:20:21', '2022-08-22 15:20:21', 2, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (124, 51, 1, 'audit', 'approved', '总经理同意', '2022-08-22 15:20:21', '2022-08-22 15:20:21', 3, 'complete', 1);
INSERT INTO `adm_process_flow` VALUES (125, 52, 3, 'apply', NULL, NULL, '2022-08-22 15:20:39', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (126, 52, 2, 'audit', 'refused', '部门经理拒绝', '2022-08-22 15:20:39', '2022-08-22 15:20:39', 2, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (127, 52, 1, 'audit', NULL, NULL, '2022-08-22 15:20:39', NULL, 3, 'cancel', 1);
INSERT INTO `adm_process_flow` VALUES (128, 53, 3, 'apply', NULL, NULL, '2022-08-22 15:20:46', NULL, 1, 'complete', 0);
INSERT INTO `adm_process_flow` VALUES (129, 53, 2, 'audit', 'approved', '部门经理同意', '2022-08-22 15:20:46', '2022-08-22 15:20:46', 2, 'complete', 1);

-- ----------------------------
-- Table structure for sys_node
-- ----------------------------
DROP TABLE IF EXISTS `sys_node`;
CREATE TABLE `sys_node`  (
  `node_id` bigint NOT NULL AUTO_INCREMENT COMMENT '节点编号',
  `node_type` int NOT NULL COMMENT '节点类型 1-模块 2-功能',
  `node_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '节点名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '功能地址',
  `node_code` int NOT NULL COMMENT '节点编码,用于排序',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '上级节点编号',
  PRIMARY KEY (`node_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_node
-- ----------------------------
INSERT INTO `sys_node` VALUES (1, 1, '行政审批', NULL, 1000000, NULL);
INSERT INTO `sys_node` VALUES (2, 2, '通知公告', '/notice.html', 1000001, 1);
INSERT INTO `sys_node` VALUES (3, 2, '请假申请', '/leave_form.html', 1000002, 1);
INSERT INTO `sys_node` VALUES (4, 2, '请假审批', '/audit.html', 1000003, 1);

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` bigint NOT NULL AUTO_INCREMENT,
  `receiver_id` bigint NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 76 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (62, 3, '您的请假申请[2020-03-26-08时-2020-04-01-18时]已提交,请等待上级审批.', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (63, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时],请尽快审批', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (64, 3, '您的请假申请[2020-03-26-08时-2020-04-01-18时]部门经理齐紫陌已批准,审批意见:部门经理同意 ,请继续等待上级审批', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (65, 1, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时],请尽快审批', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (66, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时]您已批准,审批意见:部门经理同意,申请转至上级领导继续审批', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (67, 3, '您的请假申请[2020-03-26-08时-2020-04-01-18时]总经理张晓涛已批准,审批意见:总经理同意,审批流程已结束', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (68, 1, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时]您已批准,审批意见:总经理同意,审批流程已结束', '2022-08-22 15:20:21');
INSERT INTO `sys_notice` VALUES (69, 3, '您的请假申请[2020-03-26-08时-2020-04-01-18时]已提交,请等待上级审批.', '2022-08-22 15:20:39');
INSERT INTO `sys_notice` VALUES (70, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时],请尽快审批', '2022-08-22 15:20:39');
INSERT INTO `sys_notice` VALUES (71, 3, '您的请假申请[2020-03-26-08时-2020-04-01-18时]部门经理齐紫陌已驳回,审批意见:部门经理拒绝,审批流程已结束', '2022-08-22 15:20:39');
INSERT INTO `sys_notice` VALUES (72, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-04-01-18时]您已驳回,审批意见:部门经理拒绝,审批流程已结束', '2022-08-22 15:20:39');
INSERT INTO `sys_notice` VALUES (73, 3, '您的请假申请[2020-03-26-08时-2020-03-27-18时]已提交,请等待上级审批.', '2022-08-22 15:20:46');
INSERT INTO `sys_notice` VALUES (74, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-03-27-18时],请尽快审批', '2022-08-22 15:20:46');
INSERT INTO `sys_notice` VALUES (75, 3, '您的请假申请[2020-03-26-08时-2020-03-27-18时]部门经理齐紫陌已批准,审批意见:部门经理同意,审批流程已结束', '2022-08-22 15:20:46');
INSERT INTO `sys_notice` VALUES (76, 2, '高级研发工程师-王美美提起请假申请[2020-03-26-08时-2020-03-27-18时]您已批准,审批意见:部门经理同意,审批流程已结束', '2022-08-22 15:20:46');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_description` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色描述',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '业务岗角色');
INSERT INTO `sys_role` VALUES (2, '管理岗角色');

-- ----------------------------
-- Table structure for sys_role_node
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_node`;
CREATE TABLE `sys_role_node`  (
  `rn_id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `node_id` bigint NOT NULL,
  PRIMARY KEY (`rn_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_node
-- ----------------------------
INSERT INTO `sys_role_node` VALUES (1, 1, 1);
INSERT INTO `sys_role_node` VALUES (2, 1, 2);
INSERT INTO `sys_role_node` VALUES (3, 1, 3);
INSERT INTO `sys_role_node` VALUES (4, 2, 1);
INSERT INTO `sys_role_node` VALUES (5, 2, 2);
INSERT INTO `sys_role_node` VALUES (6, 2, 3);
INSERT INTO `sys_role_node` VALUES (7, 2, 4);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `ru_id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`ru_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 2, 1);
INSERT INTO `sys_role_user` VALUES (2, 2, 2);
INSERT INTO `sys_role_user` VALUES (3, 1, 3);
INSERT INTO `sys_role_user` VALUES (4, 1, 4);
INSERT INTO `sys_role_user` VALUES (5, 1, 5);
INSERT INTO `sys_role_user` VALUES (6, 2, 6);
INSERT INTO `sys_role_user` VALUES (7, 1, 7);
INSERT INTO `sys_role_user` VALUES (8, 1, 8);
INSERT INTO `sys_role_user` VALUES (9, 1, 9);
INSERT INTO `sys_role_user` VALUES (10, 1, 10);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `employee_id` bigint NOT NULL COMMENT '员工编号',
  `salt` int NOT NULL COMMENT '盐值',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'm8', 'f57e762e3fb7e1e3ec8ec4db6a1248e1', 1, 188);
INSERT INTO `sys_user` VALUES (2, 't7', 'dcfa022748271dccf5532c834e98ad08', 2, 189);
INSERT INTO `sys_user` VALUES (3, 't6', '76ce11f8b004e8bdc8b0976b177c620d', 3, 190);
INSERT INTO `sys_user` VALUES (4, 't5', '11f04f04054772bc1a8fdc41e70c7977', 4, 191);
INSERT INTO `sys_user` VALUES (5, 't4', '8d7713848189a8d5c224f94f65d18b06', 5, 192);
INSERT INTO `sys_user` VALUES (6, 's7', '044214e86e07d96c97de79a2222188cd', 6, 193);
INSERT INTO `sys_user` VALUES (7, 's6', 'ecbd2f592ee65838328236d06ce35252', 7, 194);
INSERT INTO `sys_user` VALUES (8, 's5', '846ecc83bba8fe420adc38b39f897201', 8, 195);
INSERT INTO `sys_user` VALUES (9, 's4', 'c1e523cd2daa02f6cf4b98b2f26585fd', 9, 196);
INSERT INTO `sys_user` VALUES (10, 's3', '89e89f369e07634fbb2286efffb9492b', 10, 197);

SET FOREIGN_KEY_CHECKS = 1;
