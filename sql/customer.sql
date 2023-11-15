/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : email

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 02/11/2023 18:04:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer_black_list_records
-- ----------------------------
DROP TABLE IF EXISTS `customer_black_list_records`;
CREATE TABLE `customer_black_list_records`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱/邮箱后缀',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '建档黑名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_black_list_records
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer`;
CREATE TABLE `customer_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `company_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称',
  `country_region` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家地区',
  `sea_type` tinyint(1) NOT NULL COMMENT '私海/公海类型 1.私海 2.公海',
  `publicleads_groups_id` bigint(20) NULL DEFAULT NULL COMMENT '公海分组ID',
  `packet_id` bigint(20) NULL DEFAULT NULL COMMENT '分组ID',
  `stage_id` bigint(20) NULL DEFAULT NULL COMMENT '阶段ID',
  `rating` tinyint(1) NULL DEFAULT NULL COMMENT '客户星级',
  `customer_no_type` tinyint(1) NOT NULL COMMENT '客户编号类型 1.自动生成 2.自定义',
  `customer_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户编号',
  `phone_prefix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机-电话区号',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座机-电话号码',
  `timezone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时区',
  `scale` tinyint(1) NULL DEFAULT NULL COMMENT '规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上',
  `fax` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '传真',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `company_remarks` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司备注',
  `company_logo` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司logo',
  `focus_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '关注 0.未关注 1.已关注',
  `last_contacted_at` datetime(0) NOT NULL COMMENT '最近联系时间',
  `last_followup_at` datetime(0) COMMENT '最近跟进时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer_contact
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_contact`;
CREATE TABLE `customer_customer_contact`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户详情ID',
`nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
`email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
`social_platform` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社交平台\r\n[{\"type\":1,\"account\":\"\"}]\r\n',
`phone` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话\r\n[{\"phone_prefix\":\"\",\"phone\":\"\"}]\r\n',
`rank` tinyint(1) NULL DEFAULT NULL COMMENT '职级 1.普通职员 2.中层管理者 3.高层管理者',
`position` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
`birthday` datetime(0) NULL DEFAULT NULL COMMENT '生日',
`sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别 1.不设置 2.男 3.女',
`avatar_or_business_card` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像/名片',
`contact_remarks` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人备注',
`primary_contact_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否为主要联系人 1.是 0.否',
`order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户联系人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_contact
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer_segment
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_segment`;
CREATE TABLE `customer_customer_segment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `segment_id` bigint(20) NOT NULL COMMENT '客群ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户客群关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_segment
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer_source
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_source`;
CREATE TABLE `customer_customer_source`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `source_id` bigint(20) NOT NULL COMMENT '客户来源ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户来源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_source
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer_tag
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_tag`;
CREATE TABLE `customer_customer_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户ID',
  `tag_id` bigint(20) NOT NULL COMMENT '客户标签ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_tag
-- ----------------------------

-- ----------------------------
-- Table structure for customer_duplication_settings
-- ----------------------------
DROP TABLE IF EXISTS `customer_duplication_settings`;
CREATE TABLE `customer_duplication_settings`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `column_id` bigint(20) NOT NULL COMMENT '字段ID',
  `active_flag` tinyint(1) NOT NULL COMMENT '标志 0.未选 1.选中',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户查重设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_duplication_settings
-- ----------------------------
INSERT INTO `customer_duplication_settings` VALUES (1, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (2, 2, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (3, 3, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (4, 4, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (5, 5, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (6, 6, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (7, 7, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (8, 8, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (9, 9, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (10, 10, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (11, 11, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (12, 12, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_duplication_settings` VALUES (13, 13, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for customer_follow_up_quick_text
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow_up_quick_text`;
CREATE TABLE `customer_follow_up_quick_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `label` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签（以分号隔开）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进快捷文本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_quick_text
-- ----------------------------

-- ----------------------------
-- Table structure for customer_follow_up_rules
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow_up_rules`;
CREATE TABLE `customer_follow_up_rules`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类型 ',
`name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
`type` tinyint(1) NOT NULL COMMENT '类型\r\n1. 获取公海/同事客户\r\n2. 发送邮件 (客户+商机)\r\n3. 接收邮件 (客户+商机)\r\n4. 营销邮件 (客户+商机)\r\n5. 编辑客户\r\n6. 新建跟进 (客户+商机)\r\n7. 新建/编辑 商机\r\n8. 新建/编辑 报价单\r\n9. 新建/编辑 销售订单\r\n10. 上传客户文档\r\n11. 发送聊天消息 (TM+WhatsAPP+FB Messenger)\r\n12. 接收聊天消息 (TM+WhatsAPP+FB Messenger)\r\n\r\n13. 发送聊天消息 (TM+WhatsAPP+FB Messenger)\r\n14. 发送邮件 (客户+商机)\r\n15. 营销邮件 (客户+商机)\r\n16. 新建跟进 (客户+商机)',
`category` tinyint(1) NOT NULL COMMENT '类别 1. 发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间\r\n2. 发生以下行为会影响客户的“最近跟进时间”，进而影响跟进客户类型的任务截止时间',
`active_flag` tinyint(1) NOT NULL COMMENT '标志 0.未选 1.选中',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户跟进规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_rules
-- ----------------------------
INSERT INTO `customer_follow_up_rules` VALUES (1, '获取公海/同事客户', 1, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (2, '发送邮件 (客户+商机)', 2, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (3, '接收邮件 (客户+商机)', 3, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (4, '营销邮件 (客户+商机)', 4, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (5, '编辑客户', 5, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (6, '新建跟进 (客户+商机)', 6, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (7, '新建/编辑 商机', 7, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (8, '新建/编辑 报价单', 8, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (9, '新建/编辑 销售订单', 9, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (10, '上传客户文档', 10, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (11, '发送聊天消息 (TM+WhatsAPP+FB Messenger)', 11, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (12, '接收聊天消息 (TM+WhatsAPP+FB Messenger)', 12, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (13, '发送聊天消息 (TM+WhatsAPP+FB Messenger)', 13, 2, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (14, '发送邮件 (客户+商机)', 2, 2, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (15, '营销邮件 (客户+商机)', 15, 2, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (16, '新建跟进 (客户+商机)', 6, 2, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for customer_follow_up_templates
-- ----------------------------
DROP TABLE IF EXISTS `customer_follow_up_templates`;
CREATE TABLE `customer_follow_up_templates`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板名称',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_templates
-- ----------------------------

-- ----------------------------
-- Table structure for customer_limits
-- ----------------------------
DROP TABLE IF EXISTS `customer_limits`;
CREATE TABLE `customer_limits`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `type` tinyint(1) NOT NULL COMMENT '类型 1.不限 2.客户上限',
  `limits` int(11) NULL DEFAULT NULL COMMENT '客户上限值',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户上限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_limits
-- ----------------------------

-- ----------------------------
-- Table structure for customer_metadata_column
-- ----------------------------
DROP TABLE IF EXISTS `customer_metadata_column`;
CREATE TABLE `customer_metadata_column`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `column_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `column_alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段别名',
  `column_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
  `app_type` tinyint(1) NOT NULL COMMENT '应用类型 1.客户查重 2.客户列表 3.公海客户',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户元数据列' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_metadata_column
-- ----------------------------
INSERT INTO `customer_metadata_column` VALUES (1, 'customer_no', '客户编号', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (2, 'customer_stage', '客户阶段', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (3, 'company_name', '公司名称/简称', 'STRINIG', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (4, 'contact_name', '联系人名', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (5, 'customer_email', '客户邮箱', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (6, 'customer_phone', '客户电话', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (7, 'customer_source', '客户来源', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (8, 'country_region', '国家地区', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (9, 'source_follower', '源跟进人', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (10, 'current_attribution', '当前归属', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (11, 'create_time', '创建时间', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (12, 'last_contacted_at', '最近联系时间', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (13, 'social_media_account\n', '社交账号', 'STRING', 1, NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for customer_packet
-- ----------------------------
DROP TABLE IF EXISTS `customer_packet`;
CREATE TABLE `customer_packet`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NULL DEFAULT -1 COMMENT '父ID（第一级为-1）',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户分组名称',
  `available_member` tinyint(1) NOT NULL COMMENT '可用成员 1.全部成员 2.特定成员',
  `designated_member` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '指定成员 {\"user\":[1,2,3],\"dept\":[1,2,3]}',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_packet
-- ----------------------------
INSERT INTO `customer_packet` VALUES (1, -1, '分组测试', 2, NULL, '0', 1, 'admin', '2023-11-01 17:50:38', 1, 'admin', '2023-11-01 17:50:38');
INSERT INTO `customer_packet` VALUES (2, -1, '测试分组2', 2, NULL, '0', 1, 'admin', '2023-11-01 17:54:24', 1, 'admin', '2023-11-01 17:54:24');
INSERT INTO `customer_packet` VALUES (3, -1, '分组测试3', 2, '1,2', '0', 1, 'admin', '2023-11-01 17:56:59', 1, 'admin', '2023-11-01 17:56:59');

-- ----------------------------
-- Table structure for customer_packet_designate_member
-- ----------------------------
DROP TABLE IF EXISTS `customer_packet_designate_member`;
CREATE TABLE `customer_packet_designate_member`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `packet_id` bigint(20) NOT NULL COMMENT '分组ID',
  `association_id` bigint(20) NOT NULL COMMENT '部门或者业务员ID',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户分组指定成员表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_packet_designate_member
-- ----------------------------

-- ----------------------------
-- Table structure for customer_publicleads_claim_limit
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_claim_limit`;
CREATE TABLE `customer_publicleads_claim_limit`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '人员ID',
  `claim_limit` int(11) NOT NULL COMMENT '领取上限',
  `claim_period` tinyint(1) NOT NULL COMMENT '上限周期 1.每日2.每周3.每月4.每年',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '领取上限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_claim_limit
-- ----------------------------

-- ----------------------------
-- Table structure for customer_publicleads_groups
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_groups`;
CREATE TABLE `customer_publicleads_groups`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
`default_group_flag` tinyint(1) NOT NULL COMMENT '默认分组 0.否 1.是 ',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_publicleads_groups_user
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_groups_user`;
CREATE TABLE `customer_publicleads_groups_user`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`publicleads_groups_id` bigint(20) NULL DEFAULT NULL COMMENT '公海分组',
`user_id` bigint(20) NULL DEFAULT NULL COMMENT '分组成员',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海分组成员' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_publicleads_reason
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_reason`;
CREATE TABLE `customer_publicleads_reason`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '原因',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海原因表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_reason
-- ----------------------------

-- ----------------------------
-- Table structure for customer_publicleads_rules
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_rules`;
CREATE TABLE `customer_publicleads_rules`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则名称',
  `customer_segment_id` bigint(20) NOT NULL COMMENT '客群ID',
  `days` int(11) NOT NULL COMMENT '客户状态-天数',
  `type` tinyint(1) NOT NULL COMMENT '客户状态-类型1.未联系 2.未成交',
  `status` tinyint(1) NOT NULL COMMENT '启用状态 0.关闭 1.启用',
  `start_time` datetime(0) NOT NULL COMMENT '开始时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_rules
-- ----------------------------
INSERT INTO `customer_publicleads_rules` VALUES (1, '规则名称测试01', 1, 1, 1, 0, '2023-10-31 23:59:59', '0', 1, 'admin', '2023-11-01 14:23:21', 1, 'admin', '2023-11-01 14:28:31');

-- ----------------------------
-- Table structure for customer_publicleads_white_list
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_white_list`;
CREATE TABLE `customer_publicleads_white_list`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '人员ID',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海白名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_white_list
-- ----------------------------

-- ----------------------------
-- Table structure for customer_segment
-- ----------------------------
DROP TABLE IF EXISTS `customer_segment`;
CREATE TABLE `customer_segment`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`parent_id` bigint(20) NOT NULL COMMENT '父ID 第一级为-1',
`name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客群名称',
`usage_scope` tinyint(1) NULL DEFAULT NULL COMMENT '使用范围 1.公司共享 2.个人使用',
`visibility_scope` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可见范围-内容\r\n{\r\n\"dept\":{ //部门\r\n \"allFlag\": true, // true所有部门 false指定部门\r\n \"deptIds\": [] //指定部门ID\r\n},\r\n\"user\": { //用户\r\n \"allFlag\": true, //true所有用户 false指定用户\r\n \"userIds\": [] //指定用户ID\r\n}\r\n}',
`condition_rule_type` tinyint(1) NOT NULL COMMENT '条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件',
`condition_rule_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条件规则内容',
`subgroup_flag` tinyint(1) NULL DEFAULT NULL COMMENT '添加二级客群 0.未选 1.选中',
`addition_rule` tinyint(1) NULL DEFAULT NULL COMMENT '添加规则 1.自动生成 2.手动添加',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客群表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_segment
-- ----------------------------

-- ----------------------------
-- Table structure for customer_settings
-- ----------------------------
DROP TABLE IF EXISTS `customer_settings`;
CREATE TABLE `customer_settings`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mandatory_subgroup_flag` tinyint(1) NULL DEFAULT NULL COMMENT '子分组必选 0.未选 1.已选',
  `advance_flag` tinyint(1) NULL DEFAULT NULL COMMENT '提前标识 0.关闭 1.启动',
  `advance_days` int(11) NULL DEFAULT NULL COMMENT '提前几天',
  `account_disabled_flag` tinyint(1) NULL DEFAULT NULL COMMENT '排除禁用账号 0.未选 1.已选',
  `claim_limit_flag` tinyint(1) NULL DEFAULT NULL COMMENT '领取限制 1.限制 2.不限制',
  `claim_limit_days` int(11) NULL DEFAULT NULL COMMENT '限制几天内，原跟进人不能领取同一个客户',
  `opportunity_view_rule` tinyint(1) NULL DEFAULT NULL COMMENT '商机查看规则\r\n1.领取公海客户后，不能查看客户历史商机\r\n2.领取公海客户后，可查看客户历史商机\r\n',
  `show_public_leads_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否显示公海联系人 0.否 1.是',
  `followup_time` tinyint(1) NULL DEFAULT NULL COMMENT '写跟进时间\r\n1.提交跟进记录时默认提交时间，不允许修改\r\n2.提交跟进记录时默认提交时间，可手动修改为历史时间\r\n',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_settings
-- ----------------------------
INSERT INTO `customer_settings` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, '0', 1, 'admin', '2023-11-01 17:59:59', 1, 'admin', '2023-11-01 17:59:59');

-- ----------------------------
-- Table structure for customer_source
-- ----------------------------
DROP TABLE IF EXISTS `customer_source`;
CREATE TABLE `customer_source`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '来源名称',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户来源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_source
-- ----------------------------

-- ----------------------------
-- Table structure for customer_stage
-- ----------------------------
DROP TABLE IF EXISTS `customer_stage`;
CREATE TABLE `customer_stage`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '颜色',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户阶段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_stage
-- ----------------------------

-- ----------------------------
-- Table structure for customer_tag
-- ----------------------------
DROP TABLE IF EXISTS `customer_tag`;
CREATE TABLE `customer_tag`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签颜色',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_tag
-- ----------------------------

-- ----------------------------
-- Table structure for customer_customer_follow_up_personnel
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_follow_up_personnel`;
CREATE TABLE `customer_customer_follow_up_personnel`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`user_id` bigint(20) NOT NULL COMMENT '跟进人ID',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户跟进人' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_customer_follow_up_records
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_follow_up_records`;
CREATE TABLE `customer_customer_follow_up_records`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`follow_up_type` tinyint(1) NOT NULL COMMENT '跟进类型 1.快速记录 2.电话 3.会面 4.社交平台',
`follow_up_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '跟进内容',
`submission_time` datetime(0) NOT NULL COMMENT '提交时间',
`follow_up_contact_id` bigint(20) NOT NULL COMMENT '跟进联系人ID',
`next_follow_up_schedule` datetime(0) NULL DEFAULT NULL COMMENT '下次跟进日程',
`all_day_flag` tinyint(1) NULL DEFAULT NULL COMMENT '全天 0.否 1.是',
`schedule_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日程内容',
`color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '颜色',
`remarks` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户写跟进' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_follow_up_records_comment
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_follow_up_records_comment`;
CREATE TABLE `customer_customer_follow_up_records_comment`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`follow_up_records_id` bigint(20) NOT NULL COMMENT '写跟进ID',
`comment` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进评论' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for customer_customer_schedule
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_schedule`;
CREATE TABLE `customer_customer_schedule`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`schedule_content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日程内容',
`color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '颜色',
`all_day_flag` tinyint(1) NOT NULL COMMENT '全天 0.否 1.是',
`schedule_start_time` datetime(0) NOT NULL COMMENT '日程开始时间',
`schedule_end_time` datetime(0) NOT NULL COMMENT '日程结束时间',
`completed_flag` tinyint(1) NOT NULL COMMENT '日程是否完成 0.否 1.是',
`recurring_schedule` tinyint(1) NULL DEFAULT NULL COMMENT '周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义',
`custom_cycle_value` int(11) NULL DEFAULT NULL COMMENT '自定义周期-值',
`custom_cycle_type` tinyint(1) NULL DEFAULT NULL COMMENT '自定义周期-类型 1.天 2.周 3.月',
`cycle_end_time` datetime(0) NULL DEFAULT NULL COMMENT '周期结束时间',
`reminder_time` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提醒时间 [{\"reminderTimeType\": 1, \"reminderTimeValue\": \"2023-11-03 00:00:00\"}]\r\nreminderTimeType：提醒时间类型 1.不提醒 2.当天开始(上午9:00) 3.1天前(上午9:00) 4.2天前(上午9:00) 5.1周前(上午9:00) 6.自定义\r\nreminderTimeValue：提醒时间',
`remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户日程表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_customer_schedule_participants
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_schedule_participants`;
CREATE TABLE `customer_customer_schedule_participants`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`schedule_id` bigint(20) NOT NULL COMMENT '日程ID',
`user_id` bigint(20) NOT NULL COMMENT '参与人',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户日程参与人' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_publicleads
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_publicleads`;
CREATE TABLE `customer_customer_publicleads`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`publicleads_groups_id` bigint(20) NOT NULL COMMENT '公海分组ID',
`publicleads_reason_id` bigint(20) NULL DEFAULT NULL COMMENT '移入公海原因ID',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入客户到公海表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_publicleads_rules_segment
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_rules_segment`;
CREATE TABLE `customer_publicleads_rules_segment`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`publicleads_rules_id` bigint(20) NOT NULL COMMENT '公海规则ID',
`segment_id` bigint(20) NOT NULL COMMENT '客群ID',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海规则客群' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_sea_log
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_sea_log`;
CREATE TABLE `customer_customer_sea_log`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`sea_type` tinyint(1) NOT NULL COMMENT '私海/公海类型 1.私海 2.公海',
`type` tinyint(1) NOT NULL COMMENT '类型 1.手动 2.自动',
`create_id` bigint(20) NOT NULL COMMENT '创建者ID',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户私海/公海日志' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_segment_log
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_segment_log`;
CREATE TABLE `customer_customer_segment_log`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`segment_id` bigint(20) NOT NULL COMMENT '客群ID',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户关联客群日志' ROW_FORMAT = Dynamic;
