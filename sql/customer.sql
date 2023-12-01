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
`rating` tinyint(1) NULL DEFAULT NULL COMMENT '客户星级 1 2 3 4 5',
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
`last_followup_at` datetime(0) NULL DEFAULT NULL COMMENT '最近跟进时间',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_company_name`(`company_name`) USING BTREE COMMENT '公司名称索引',
INDEX `idx_customer_no`(`customer_no`) USING BTREE COMMENT '客户编号索引',
INDEX `idx_sea_type`(`sea_type`) USING BTREE COMMENT '公海/私海类型索引',
INDEX `idx_publicleads_groups_id`(`publicleads_groups_id`) USING BTREE COMMENT '公海分组索引',
INDEX `idx_packet_id`(`packet_id`) USING BTREE COMMENT '分组索引',
INDEX `idx_stage_id`(`stage_id`) USING BTREE COMMENT '阶段索引',
INDEX `idx_focus_flag`(`focus_flag`) USING BTREE COMMENT '关注索引'
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户详情表' ROW_FORMAT = Dynamic;

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
`primary_contact_flag` tinyint(1) NULL DEFAULT NULL COMMENT '主要联系人 1.是 0.否',
`order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1699929794919 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户联系人表' ROW_FORMAT = Dynamic;
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
-- Table structure for customer_customer_source
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_source`;
CREATE TABLE `customer_customer_source`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`source_id` bigint(20) NOT NULL COMMENT '客户来源ID',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引',
INDEX `idx_source_id`(`source_id`) USING BTREE COMMENT '客户来源索引'
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户来源关联表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_tag
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_tag`;
CREATE TABLE `customer_customer_tag`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`tag_id` bigint(20) NOT NULL COMMENT '客户标签ID',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引',
INDEX `idx_tag_id`(`tag_id`) USING BTREE COMMENT '标签索引'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签关联表' ROW_FORMAT = Dynamic;


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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_column_id`(`column_id`) USING BTREE COMMENT '字段索引'
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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_publicleads_groups_id`(`publicleads_groups_id`) USING BTREE COMMENT '公海分组索引',
INDEX `idx_user_id`(`user_id`) USING BTREE COMMENT '分组成员索引'
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海分组成员' ROW_FORMAT = Dynamic;


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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海规则表' ROW_FORMAT = Dynamic;

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
`usage_scope` tinyint(1) COMMENT '使用范围 1.公司共享 2.个人使用',
`visibility_scope` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可见范围',
`condition_rule_type` tinyint(1) NOT NULL COMMENT '条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件',
`condition_rule_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条件规则内容',
`subgroup_flag` tinyint(1) NOT NULL COMMENT '添加二级客群 0.未选 1.选中',
`addition_rule` tinyint(1) NULL DEFAULT NULL COMMENT '添加规则 1.自动生成 2.手动添加',
`sub_group_column` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '二级分群字段',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客群表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户设置表' ROW_FORMAT = Dynamic;

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
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
`color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签颜色',
`type` tinyint(1) NOT NULL COMMENT '类型 1.公司 2.个人',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签' ROW_FORMAT = Dynamic;

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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户跟进人' ROW_FORMAT = Dynamic;


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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户写跟进' ROW_FORMAT = Dynamic;


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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_follow_up_records_id`(`follow_up_records_id`) USING BTREE COMMENT '写跟进索引'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进评论' ROW_FORMAT = Dynamic;


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
`completed_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '日程是否完成 0.否 1.是',
`recurring_schedule` tinyint(1) NULL DEFAULT NULL COMMENT '周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义',
`custom_cycle_value` int(11) NULL DEFAULT NULL COMMENT '自定义周期-值',
`custom_cycle_type` tinyint(1) NULL DEFAULT NULL COMMENT '自定义周期-类型 1.天 2.周 3.月',
`cycle_end_time` datetime(0) NULL DEFAULT NULL COMMENT '周期结束时间',
`reminder_time` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提醒时间 [{\"reminderTimeType\": 1, \"reminderTimeValue\": \"2023-11-03 00:00:00\"}]\r\nreminderTimeType：提醒时间类型 1.不提醒 2.当天开始(上午9:00) 3.1天前(上午9:00) 4.2天前(上午9:00) 5.1周前(上午9:00) 6.自定义\r\nreminderTimeValue：提醒时间',
`focus_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '重点关注 0否 1是',
`remark` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户日程表' ROW_FORMAT = Dynamic;


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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_schedule_id`(`schedule_id`) USING BTREE COMMENT '日程索引'
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户日程参与人' ROW_FORMAT = Dynamic;


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
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_publicleads_rules_id`(`publicleads_rules_id`) USING BTREE COMMENT '公海规则索引',
INDEX `idx_segment_id`(`segment_id`) USING BTREE COMMENT '客群索引'
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海规则客群' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_sea_log
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_sea_log`;
CREATE TABLE `customer_customer_sea_log`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`sea_type` tinyint(1) NOT NULL COMMENT '私海/公海类型 1.私海 2.公海',
`type` tinyint(1) NOT NULL COMMENT '类型 1.手动 2.自动',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户私海/公海日志' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_segment
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_segment`;
CREATE TABLE `customer_customer_segment`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`segment_id` bigint(20) NOT NULL COMMENT '客群ID',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引',
INDEX `idx_segment_id`(`segment_id`) USING BTREE COMMENT '客群索引'
) ENGINE = InnoDB AUTO_INCREMENT = 347 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户客群关联表' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_segment_log
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_segment_log`;
CREATE TABLE `customer_customer_segment_log`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`segment_id` bigint(20) NOT NULL COMMENT '客群ID',
`create_time` datetime(0) NOT NULL COMMENT '创建时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引',
INDEX `idx_segment_id`(`segment_id`) USING BTREE COMMENT '客群索引'
) ENGINE = InnoDB AUTO_INCREMENT = 330 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户关联客群日志' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_import
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_import`;
CREATE TABLE `customer_customer_import`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`import_type` tinyint(1) NOT NULL COMMENT '导入类型 1.客户列表 2.公海客户',
`import_status` tinyint(1) NOT NULL COMMENT '导入状态 1.进行中 2.成功 3.失败',
`expected_import_count` int(5) NOT NULL COMMENT '预见导入数',
`success_import_count` int(5) NOT NULL COMMENT '成功导入数',
`failed_import_count` int(5) NOT NULL COMMENT '导入失败数',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_import_type`(`import_type`) USING BTREE COMMENT '导入类型索引',
INDEX `idx_import_status`(`import_status`) USING BTREE COMMENT '导入状态索引',
INDEX `idx_create_time`(`create_time`) USING BTREE COMMENT '创建时间索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for customer_customer_document
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_document`;
CREATE TABLE `customer_customer_document`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名称',
`path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件路径',
`extension` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
`size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
`type` tinyint(1) NOT NULL COMMENT '关联类型 1.邮件附件 2.手动上传',
`customer_id` bigint(20) NOT NULL COMMENT '客户ID',
`del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
`create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
`create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
`create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
`update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
`update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
`update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY (`id`) USING BTREE,
INDEX `idx_customer_id`(`customer_id`) USING BTREE COMMENT '客户索引'
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户文档' ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `base_timezone`;
CREATE TABLE `base_timezone`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`value` int(5) NOT NULL COMMENT 'value',
`label` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'label',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '时区' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_timezone
-- ----------------------------
INSERT INTO `base_timezone` VALUES (1, 0, '零时区：伦敦');
INSERT INTO `base_timezone` VALUES (2, 1, '东一区：罗马，巴黎');
INSERT INTO `base_timezone` VALUES (3, 2, '东二区：雅典，以色列');
INSERT INTO `base_timezone` VALUES (4, 3, '东三区：莫斯科，科威特');
INSERT INTO `base_timezone` VALUES (5, 4, '东四区：喀布尔');
INSERT INTO `base_timezone` VALUES (6, 5, '东五区：伊斯兰堡，卡拉奇');
INSERT INTO `base_timezone` VALUES (7, 6, '东六区：阿拉木图，科伦坡');
INSERT INTO `base_timezone` VALUES (8, 7, '东七区：曼谷，雅加达');
INSERT INTO `base_timezone` VALUES (9, 8, '东八区：北京，香港，台湾');
INSERT INTO `base_timezone` VALUES (10, 9, '东九区：东京');
INSERT INTO `base_timezone` VALUES (11, 10, '东十区：悉尼');
INSERT INTO `base_timezone` VALUES (12, 11, '东十一区：霍尼亚拉，马加丹');
INSERT INTO `base_timezone` VALUES (13, 12, '东西十二区: 奥克兰');
INSERT INTO `base_timezone` VALUES (14, 13, '西十一区：帕果帕果，阿洛菲');
INSERT INTO `base_timezone` VALUES (15, 14, '西十区：夏威夷');
INSERT INTO `base_timezone` VALUES (16, 15, '西九区：阿拉斯加');
INSERT INTO `base_timezone` VALUES (17, 16, '西八区：洛杉矶，旧金山');
INSERT INTO `base_timezone` VALUES (18, 17, '西七区：盐湖城、丹佛、凤凰城');
INSERT INTO `base_timezone` VALUES (19, 18, '西六区：芝加哥，休斯顿，亚特兰大');
INSERT INTO `base_timezone` VALUES (20, 19, '西五区：纽约，华盛顿，波士顿');
INSERT INTO `base_timezone` VALUES (21, 20, '西四区：加拿大，加拉加斯');
INSERT INTO `base_timezone` VALUES (22, 21, '西三区：巴西利亚');
INSERT INTO `base_timezone` VALUES (23, 22, '西二区：协调世界时');
INSERT INTO `base_timezone` VALUES (24, 23, '西一区：佛得角群岛');

DROP TABLE IF EXISTS `base_country`;
CREATE TABLE `base_country`  (
`id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
`type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型（州）',
`value` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'value',
`label` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'label',
PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 204 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '国家地区' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_country
-- ----------------------------
INSERT INTO `base_country` VALUES (1, 'Asia', 'CN', '中国(China)');
INSERT INTO `base_country` VALUES (2, 'Asia', 'AF', '阿富汗(Afghanistan)');
INSERT INTO `base_country` VALUES (3, 'Asia', 'IN', '印度(India)');
INSERT INTO `base_country` VALUES (4, 'Asia', 'MN', '蒙古(Mongolia)');
INSERT INTO `base_country` VALUES (5, 'Asia', 'JP', '日本(Japan)');
INSERT INTO `base_country` VALUES (6, 'Asia', 'SY', '叙利亚(Syria)');
INSERT INTO `base_country` VALUES (7, 'Asia', 'SA', '沙特阿拉伯(Saudi Arabia)');
INSERT INTO `base_country` VALUES (8, 'Asia', 'AM', '亚美尼亚(Armenia)');
INSERT INTO `base_country` VALUES (9, 'Asia', 'BH', '巴林(Bahrain)');
INSERT INTO `base_country` VALUES (10, 'Asia', 'PK', '巴基斯坦(Pakistan)');
INSERT INTO `base_country` VALUES (11, 'Asia', 'KP', '朝鲜(North Korea)');
INSERT INTO `base_country` VALUES (12, 'Asia', 'SG', '新加坡(Singapore)');
INSERT INTO `base_country` VALUES (13, 'Asia', 'IQ', '伊拉克(Iraq)');
INSERT INTO `base_country` VALUES (14, 'Asia', 'IL', '以色列(Israel)');
INSERT INTO `base_country` VALUES (15, 'Asia', 'AZ', '阿塞拜疆(Azerbaijan)');
INSERT INTO `base_country` VALUES (16, 'Asia', 'MM', '缅甸(Myanmar)');
INSERT INTO `base_country` VALUES (17, 'Asia', 'BD', '孟加拉国(Bangladesh)');
INSERT INTO `base_country` VALUES (18, 'Asia', 'KR', '韩国(South Korea)');
INSERT INTO `base_country` VALUES (19, 'Asia', 'LB', '黎巴嫩(Lebanon)');
INSERT INTO `base_country` VALUES (20, 'Asia', 'JO', '约旦(Jordan)');
INSERT INTO `base_country` VALUES (21, 'Asia', 'PS', '巴勒斯坦(Palestine)');
INSERT INTO `base_country` VALUES (22, 'Asia', 'GE', '格鲁吉亚(Georgia)');
INSERT INTO `base_country` VALUES (23, 'Asia', 'KW', '科威特(Kuwait)');
INSERT INTO `base_country` VALUES (24, 'Asia', 'KG', '吉尔吉斯斯坦(Kyrgyzstan)');
INSERT INTO `base_country` VALUES (25, 'Asia', 'MV', '马尔代夫(Maldives)');
INSERT INTO `base_country` VALUES (26, 'Asia', 'MY', '马来西亚(Malaysia)');
INSERT INTO `base_country` VALUES (27, 'Asia', 'YE', '也门(Yemen)');
INSERT INTO `base_country` VALUES (28, 'Asia', 'BN', '文莱(Brunei)');
INSERT INTO `base_country` VALUES (29, 'Asia', 'LA', '老挝(Laos)');
INSERT INTO `base_country` VALUES (30, 'Asia', 'KH', '柬埔寨(Cambodia)');
INSERT INTO `base_country` VALUES (31, 'Asia', 'QA', '卡塔尔(Qatar)');
INSERT INTO `base_country` VALUES (32, 'Asia', 'KZ', '哈萨克斯坦(Kazakhstan)');
INSERT INTO `base_country` VALUES (33, 'Asia', 'NP', '尼泊尔(Nepal)');
INSERT INTO `base_country` VALUES (34, 'Asia', 'TH', '泰国(Thailand)');
INSERT INTO `base_country` VALUES (35, 'Asia', 'AE', '阿联酋(United Arab Emirates)');
INSERT INTO `base_country` VALUES (36, 'Asia', 'BT', '不丹(Bhutan)');
INSERT INTO `base_country` VALUES (37, 'Asia', 'OM', '阿曼(Oman)');
INSERT INTO `base_country` VALUES (38, 'Asia', 'TM', '土库曼斯坦(Turkmenistan)');
INSERT INTO `base_country` VALUES (39, 'Asia', 'RU', '俄罗斯(Russia)');
INSERT INTO `base_country` VALUES (40, 'Asia', 'LK', '斯里兰卡(Sri Lanka)');
INSERT INTO `base_country` VALUES (41, 'Asia', 'ID', '印度尼西亚(Indonesia)');
INSERT INTO `base_country` VALUES (42, 'Asia', 'IR', '伊朗(Iran)');
INSERT INTO `base_country` VALUES (43, 'Asia', 'TR', '土耳其(Turkey)');
INSERT INTO `base_country` VALUES (44, 'Asia', 'TJ', '塔吉克斯坦(Tajikistan)');
INSERT INTO `base_country` VALUES (45, 'Asia', 'CY', '塞浦路斯(Cyprus)');
INSERT INTO `base_country` VALUES (46, 'Asia', 'TL', '东帝汶(Timor-Leste)');
INSERT INTO `base_country` VALUES (47, 'Asia', 'UZ', '乌兹别克斯坦(Uzbekistan)');
INSERT INTO `base_country` VALUES (48, 'Asia', 'VN', '越南(Vietnam)');
INSERT INTO `base_country` VALUES (49, 'North America', 'US', '美国(United States)');
INSERT INTO `base_country` VALUES (50, 'North America', 'CA', '加拿大(Canada)');
INSERT INTO `base_country` VALUES (51, 'North America', 'MX', '墨西哥(Mexico)');
INSERT INTO `base_country` VALUES (52, 'North America', 'GT', '危地马拉(Guatemala)');
INSERT INTO `base_country` VALUES (53, 'North America', 'BZ', '伯利兹(Belize)');
INSERT INTO `base_country` VALUES (54, 'North America', 'SV', '萨尔瓦多(El Salvador)');
INSERT INTO `base_country` VALUES (55, 'North America', 'HN', '洪都拉斯(Honduras)');
INSERT INTO `base_country` VALUES (56, 'North America', 'PA', '巴拿马(Panama)');
INSERT INTO `base_country` VALUES (57, 'North America', 'BS', '巴哈马(Bahamas)');
INSERT INTO `base_country` VALUES (58, 'North America', 'CU', '古巴(Cuba)');
INSERT INTO `base_country` VALUES (59, 'North America', 'JM', '牙买加(Jamaica)');
INSERT INTO `base_country` VALUES (60, 'North America', 'HT', '海地(Haiti)');
INSERT INTO `base_country` VALUES (61, 'North America', 'DO', '多米尼加(Dominican Republic)');
INSERT INTO `base_country` VALUES (62, 'North America', 'CR', '哥斯达黎加(Costa Rica)');
INSERT INTO `base_country` VALUES (63, 'North America', 'KN', '圣基茨和尼维斯(Saint Kitts and Nevis)');
INSERT INTO `base_country` VALUES (64, 'North America', 'AG', '安提瓜和巴布达(Antigua and Barbuda)');
INSERT INTO `base_country` VALUES (65, 'North America', 'DM', '多米尼克(Dominica)');
INSERT INTO `base_country` VALUES (66, 'North America', 'LC', '圣卢西亚(Saint Lucia)');
INSERT INTO `base_country` VALUES (67, 'North America', 'VC', '圣文森特和格林纳丁斯(Saint Vincent and the Grenadines)');
INSERT INTO `base_country` VALUES (68, 'North America', 'BB', '巴巴多斯(Barbados)');
INSERT INTO `base_country` VALUES (69, 'North America', 'GD', '格林纳达(Grenada)');
INSERT INTO `base_country` VALUES (70, 'North America', 'TT', '特立尼达和多巴哥(Trinidad and Tobago)');
INSERT INTO `base_country` VALUES (71, 'North America', 'NI', '尼加拉瓜(Nicaragua)');
INSERT INTO `base_country` VALUES (72, 'South America', 'AR', '阿根廷(Argentina)');
INSERT INTO `base_country` VALUES (73, 'South America', 'BO', '玻利维亚(Bolivia)');
INSERT INTO `base_country` VALUES (74, 'South America', 'BR', '巴西(Brazil)');
INSERT INTO `base_country` VALUES (75, 'South America', 'CL', '智利(Chile)');
INSERT INTO `base_country` VALUES (76, 'South America', 'CO', '哥伦比亚(Colombia)');
INSERT INTO `base_country` VALUES (77, 'South America', 'EC', '厄瓜多尔(Ecuador)');
INSERT INTO `base_country` VALUES (78, 'South America', 'GY', '圭亚那(Guyana)');
INSERT INTO `base_country` VALUES (79, 'South America', 'PE', '秘鲁(Peru)');
INSERT INTO `base_country` VALUES (80, 'South America', 'PY', '巴拉圭(Paraguay)');
INSERT INTO `base_country` VALUES (81, 'South America', 'SR', '苏里南(Suriname)');
INSERT INTO `base_country` VALUES (82, 'South America', 'UY', '乌拉圭(Uruguay)');
INSERT INTO `base_country` VALUES (83, 'South America', 'VE', '委内瑞拉(Venezuela)');
INSERT INTO `base_country` VALUES (84, 'Europe', 'AL', '阿尔巴尼亚(Albania)');
INSERT INTO `base_country` VALUES (85, 'Europe', 'AD', '安道尔(Andorra)');
INSERT INTO `base_country` VALUES (86, 'Europe', 'AT', '奥地利(Austria)');
INSERT INTO `base_country` VALUES (87, 'Europe', 'BY', '白俄罗斯(Belarus)');
INSERT INTO `base_country` VALUES (88, 'Europe', 'BE', '比利时(Belgium)');
INSERT INTO `base_country` VALUES (89, 'Europe', 'BA', '波斯尼亚和黑塞哥维那(Bosnia and Herzegovina)');
INSERT INTO `base_country` VALUES (90, 'Europe', 'BG', '保加利亚(Bulgaria)');
INSERT INTO `base_country` VALUES (91, 'Europe', 'HR', '克罗地亚(Croatia)');
INSERT INTO `base_country` VALUES (92, 'Europe', 'CY', '塞浦路斯(Cyprus)');
INSERT INTO `base_country` VALUES (93, 'Europe', 'CZ', '捷克(Czechia)');
INSERT INTO `base_country` VALUES (94, 'Europe', 'DK', '丹麦(Denmark)');
INSERT INTO `base_country` VALUES (95, 'Europe', 'EE', '爱沙尼亚(Estonia)');
INSERT INTO `base_country` VALUES (96, 'Europe', 'FI', '芬兰(Finland)');
INSERT INTO `base_country` VALUES (97, 'Europe', 'FR', '法国(France)');
INSERT INTO `base_country` VALUES (98, 'Europe', 'DE', '德国(Germany)');
INSERT INTO `base_country` VALUES (99, 'Europe', 'GR', '希腊(Greece)');
INSERT INTO `base_country` VALUES (100, 'Europe', 'HU', '匈牙利(Hungary)');
INSERT INTO `base_country` VALUES (101, 'Europe', 'IS', '冰岛(Iceland)');
INSERT INTO `base_country` VALUES (102, 'Europe', 'IE', '爱尔兰(Ireland)');
INSERT INTO `base_country` VALUES (103, 'Europe', 'IT', '意大利(Italy)');
INSERT INTO `base_country` VALUES (104, 'Europe', 'LV', '拉脱维亚(Latvia)');
INSERT INTO `base_country` VALUES (105, 'Europe', 'LI', '列支敦士登(Liechtenstein)');
INSERT INTO `base_country` VALUES (106, 'Europe', 'LTA', '立陶宛(Lithuania)');
INSERT INTO `base_country` VALUES (107, 'Europe', 'LU', '卢森堡(Luxembourg)');
INSERT INTO `base_country` VALUES (108, 'Europe', 'MT', '马耳他(Malta)');
INSERT INTO `base_country` VALUES (109, 'Europe', 'MD', '摩尔多瓦(Moldova)');
INSERT INTO `base_country` VALUES (110, 'Europe', 'MC', '摩纳哥(Monaco)');
INSERT INTO `base_country` VALUES (111, 'Europe', 'ME', '黑山(Montenegro)');
INSERT INTO `base_country` VALUES (112, 'Europe', 'NL', '荷兰(Netherlands)');
INSERT INTO `base_country` VALUES (113, 'Europe', 'MK', '北马其顿(North Macedonia)');
INSERT INTO `base_country` VALUES (114, 'Europe', 'NO', '挪威(Norway)');
INSERT INTO `base_country` VALUES (115, 'Europe', 'PL', '波兰(Poland)');
INSERT INTO `base_country` VALUES (116, 'Europe', 'PT', '葡萄牙(Portugal)');
INSERT INTO `base_country` VALUES (117, 'Europe', 'RO', '罗马尼亚(Romania)');
INSERT INTO `base_country` VALUES (118, 'Europe', 'RU', '俄罗斯(Russia)');
INSERT INTO `base_country` VALUES (119, 'Europe', 'SM', '圣马力诺(San Marino)');
INSERT INTO `base_country` VALUES (120, 'Europe', 'RS', '塞尔维亚(Serbia)');
INSERT INTO `base_country` VALUES (121, 'Europe', 'SK', '斯洛伐克(Slovakia)');
INSERT INTO `base_country` VALUES (122, 'Europe', 'SI', '斯洛文尼亚(Slovenia)');
INSERT INTO `base_country` VALUES (123, 'Europe', 'ES', '西班牙(Spain)');
INSERT INTO `base_country` VALUES (124, 'Europe', 'SE', '瑞典(Sweden)');
INSERT INTO `base_country` VALUES (125, 'Europe', 'CH', '瑞士(Switzerland)');
INSERT INTO `base_country` VALUES (126, 'Europe', 'TR', '土耳其(Turkey)');
INSERT INTO `base_country` VALUES (127, 'Europe', 'UA', '乌克兰(Ukraine)');
INSERT INTO `base_country` VALUES (128, 'Europe', 'GB', '英国(United Kingdom)');
INSERT INTO `base_country` VALUES (129, 'Europe', 'VA', '梵蒂冈(Vatican City)');
INSERT INTO `base_country` VALUES (130, 'Africa', 'EG', '埃及(Egypt)');
INSERT INTO `base_country` VALUES (131, 'Africa', 'LY', '利比亚(Libya)');
INSERT INTO `base_country` VALUES (132, 'Africa', 'TN', '突尼斯(Tunisia)');
INSERT INTO `base_country` VALUES (133, 'Africa', 'DZ', '阿尔及利亚(Algeria)');
INSERT INTO `base_country` VALUES (134, 'Africa', 'MA', '摩洛哥(Morocco)');
INSERT INTO `base_country` VALUES (135, 'Africa', 'ET', '埃塞俄比亚(Ethiopia)');
INSERT INTO `base_country` VALUES (136, 'Africa', 'ER', '厄立特里亚(Eritrea)');
INSERT INTO `base_country` VALUES (137, 'Africa', 'SO', '索马里(Somalia)');
INSERT INTO `base_country` VALUES (138, 'Africa', 'DJ', '吉布提(Djibouti)');
INSERT INTO `base_country` VALUES (139, 'Africa', 'KE', '肯尼亚(Kenya)');
INSERT INTO `base_country` VALUES (140, 'Africa', 'TZ', '坦桑尼亚(Tanzania)');
INSERT INTO `base_country` VALUES (141, 'Africa', 'UG', '乌干达(Uganda)');
INSERT INTO `base_country` VALUES (142, 'Africa', 'RW', '卢旺达(Rwanda)');
INSERT INTO `base_country` VALUES (143, 'Africa', 'BI', '布隆迪(Burundi)');
INSERT INTO `base_country` VALUES (144, 'Africa', 'SC', '塞舌尔(Seychelles)');
INSERT INTO `base_country` VALUES (145, 'Africa', 'SD', '苏丹(Sudan)');
INSERT INTO `base_country` VALUES (146, 'Africa', 'SS', '南苏丹(South Sudan)');
INSERT INTO `base_country` VALUES (147, 'Africa', 'TD', '乍得(Chad)');
INSERT INTO `base_country` VALUES (148, 'Africa', 'CF', '中非(Central African Republic)');
INSERT INTO `base_country` VALUES (149, 'Africa', 'CM', '喀麦隆(Cameroon)');
INSERT INTO `base_country` VALUES (150, 'Africa', 'GQ', '赤道几内亚(Equatorial Guinea)');
INSERT INTO `base_country` VALUES (151, 'Africa', 'GA', '加蓬(Gabon)');
INSERT INTO `base_country` VALUES (152, 'Africa', 'CG', '刚果共和国(Congo Republic)');
INSERT INTO `base_country` VALUES (153, 'Africa', 'CD', '刚果民主共和国(Congo Democratic Republic)');
INSERT INTO `base_country` VALUES (154, 'Africa', 'ST', '圣多美和普林西比(Sao Tome and Principe)');
INSERT INTO `base_country` VALUES (155, 'Africa', 'MR', '毛里塔尼亚(Mauritania)');
INSERT INTO `base_country` VALUES (156, 'Africa', 'SN', '塞内加尔(Senegal)');
INSERT INTO `base_country` VALUES (157, 'Africa', 'GM', '冈比亚(Gambia)');
INSERT INTO `base_country` VALUES (158, 'Africa', 'ML', '马里(Mali)');
INSERT INTO `base_country` VALUES (159, 'Africa', 'BF', '布基纳法索(Burkina Faso)');
INSERT INTO `base_country` VALUES (160, 'Africa', 'GN', '几内亚(Guinea)');
INSERT INTO `base_country` VALUES (161, 'Africa', 'GW', '几内亚比绍(Guinea-Bissau)');
INSERT INTO `base_country` VALUES (162, 'Africa', 'CV', '佛得角(Cape Verde)');
INSERT INTO `base_country` VALUES (163, 'Africa', 'SL', '塞拉利昂(Sierra Leone)');
INSERT INTO `base_country` VALUES (164, 'Africa', 'LR', '利比里亚(Liberia)');
INSERT INTO `base_country` VALUES (165, 'Africa', 'CI', '科特迪瓦(Cote d\"Ivoire)');
INSERT INTO `base_country` VALUES (166, 'Africa', 'GH', '加纳(Ghana)');
INSERT INTO `base_country` VALUES (167, 'Africa', 'TG', '多哥(Togo)');
INSERT INTO `base_country` VALUES (168, 'Africa', 'BJ', '贝宁(Benin)');
INSERT INTO `base_country` VALUES (169, 'Africa', 'NE', '尼日尔(Niger)');
INSERT INTO `base_country` VALUES (170, 'Africa', 'NG', '尼日利亚(Nigeria)');
INSERT INTO `base_country` VALUES (171, 'Africa', 'ZM', '赞比亚(Zambia)');
INSERT INTO `base_country` VALUES (172, 'Africa', 'AO', '安哥拉(Angola)');
INSERT INTO `base_country` VALUES (173, 'Africa', 'ZW', '津巴布韦(Zimbabwe)');
INSERT INTO `base_country` VALUES (174, 'Africa', 'MW', '马拉维(Malawi)');
INSERT INTO `base_country` VALUES (175, 'Africa', 'MZ', '莫桑比克(Mozambique)');
INSERT INTO `base_country` VALUES (176, 'Africa', 'BW', '博茨瓦纳(Botswana)');
INSERT INTO `base_country` VALUES (177, 'Africa', 'NA', '纳米比亚(Namibia)');
INSERT INTO `base_country` VALUES (178, 'Africa', 'ZA', '南非(South Africa)');
INSERT INTO `base_country` VALUES (179, 'Africa', 'SZ', '斯威士兰(Eswatini)');
INSERT INTO `base_country` VALUES (180, 'Africa', 'LS', '莱索托(Lesotho)');
INSERT INTO `base_country` VALUES (181, 'Africa', 'MG', '马达加斯加(Madagascar)');
INSERT INTO `base_country` VALUES (182, 'Africa', 'KM', '科摩罗(Comoros)');
INSERT INTO `base_country` VALUES (183, 'Africa', 'MU', '毛里求斯(Mauritius)');
INSERT INTO `base_country` VALUES (184, 'Oceania', 'AU', '澳大利亚(Australia)');
INSERT INTO `base_country` VALUES (185, 'Oceania', 'NZ', '新西兰(New Zealand)');
INSERT INTO `base_country` VALUES (186, 'Oceania', 'PW', '帕劳(Palau)');
INSERT INTO `base_country` VALUES (187, 'Oceania', 'FM', '密克罗尼西亚联邦(Federated States of Micronesia)');
INSERT INTO `base_country` VALUES (188, 'Oceania', 'MH', '马绍尔群岛(Marshall Islands)');
INSERT INTO `base_country` VALUES (189, 'Oceania', 'NR', '瑙鲁(Nauru)');
INSERT INTO `base_country` VALUES (190, 'Oceania', 'PG', '巴布亚新几内亚(Papua New Guinea)');
INSERT INTO `base_country` VALUES (191, 'Oceania', 'SB', '所罗门群岛(Solomon Islands)');
INSERT INTO `base_country` VALUES (192, 'Oceania', 'VU', '瓦努阿图(Vanuatu)');
INSERT INTO `base_country` VALUES (193, 'Oceania', 'TV', '图瓦卢(Tuvalu)');
INSERT INTO `base_country` VALUES (194, 'Oceania', 'FJ', '斐济(Fiji)');
INSERT INTO `base_country` VALUES (195, 'Oceania', 'WS', '萨摩亚(Samoa)');
INSERT INTO `base_country` VALUES (196, 'Oceania', 'KI', '基里巴斯(Kiribati)');
INSERT INTO `base_country` VALUES (197, 'Oceania', 'TO', '汤加(Tonga)');
INSERT INTO `base_country` VALUES (198, 'Oceania', 'CK', '库克群岛(Cook Islands)');
INSERT INTO `base_country` VALUES (199, 'Oceania', 'NU', '纽埃(Niue)');
INSERT INTO `base_country` VALUES (200, 'Antarctica', 'AQ', '南极洲(Antarctica)');
INSERT INTO `base_country` VALUES (201, 'Antarctica', 'BI', '布韦岛(Bouvet Island)');
INSERT INTO `base_country` VALUES (202, 'Antarctica', 'FS', '法属南部领地(French Southern Territories)');
INSERT INTO `base_country` VALUES (203, 'Antarctica', 'HI', '赫德岛和麦克唐纳群岛(Heard Island and McDonald Islands)');


INSERT INTO `email`.`sys_job`(`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('客户客群洗牌', 'SYSTEM', 'customerTask.shuffle', '0 0 0/3 * * ? ', '1', '1', '0', 'admin', '2023-11-27 07:48:56', 'admin', '2023-11-29 15:02:41', '');
INSERT INTO `email`.`sys_job`(`job_name`, `job_group`, `invoke_target`, `cron_expression`, `misfire_policy`, `concurrent`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES ('移动公海规则处理', 'SYSTEM', 'customerTask.movePublicleadsRulesHandler', '0 5 0 ? * * ', '1', '1', '0', 'admin', '2023-11-27 07:49:49', '', '2023-11-27 07:49:55', '');