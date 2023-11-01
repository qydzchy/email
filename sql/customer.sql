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

 Date: 31/10/2023 18:06:46
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
-- Table structure for customer_details
-- ----------------------------
DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `company_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司名称',
  `short_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简称',
  `country_region` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家地区',
  `source_ids` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户来源ID（多个以逗号分隔拼接)',
  `tag_ids` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户标签ID（多个以逗号分隔拼接）',
  `sea_type` tinyint(1) NOT NULL COMMENT '私海/公海类型 1.私海 2.公海',
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
  `last_followup_at` datetime(0) NOT NULL COMMENT '最近跟进时间',
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
-- Table structure for customer_details_contact
-- ----------------------------
DROP TABLE IF EXISTS `customer_details_contact`;
CREATE TABLE `customer_details_contact`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `customer_details_id` bigint(20) NOT NULL COMMENT '客户详情ID',
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户联系人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_duplication_settings
-- ----------------------------
DROP TABLE IF EXISTS `customer_duplication_settings`;
CREATE TABLE `customer_duplication_settings`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `column_id` bigint(20) NOT NULL COMMENT '列ID',
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
-- Table structure for customer_metadata_column
-- ----------------------------
DROP TABLE IF EXISTS `customer_metadata_column`;
CREATE TABLE `customer_metadata_column`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段别名',
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段类型',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
INSERT INTO `customer_metadata_column` VALUES (1, 'customer_no', '客户编号', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (2, 'customer_stage', '客户阶段', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (3, 'company_name', '公司名称/简称', 'STRINIG', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (4, 'contact_name', '联系人名', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (5, 'customer_email', '客户邮箱', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (6, 'customer_phone', '客户电话', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (7, 'customer_source', '客户来源', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (8, 'country_region', '国家地区', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (9, 'source_follower', '源跟进人', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (10, 'current_attribution', '当前归属', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (11, 'create_time', '创建时间', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (12, 'last_contacted_at', '最近联系时间', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_metadata_column` VALUES (13, 'social_media_account\n', '社交账号', 'STRING', NULL, '0', NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户分组表' ROW_FORMAT = Dynamic;

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
-- Table structure for customer_publicleads_groups
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_groups`;
CREATE TABLE `customer_publicleads_groups`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `user_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组成员ID（逗号拼接）',
  `default_group_flag` tinyint(1) NOT NULL COMMENT '默认分组 0.否 1.是 ',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海分组表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户阶段表' ROW_FORMAT = Dynamic;