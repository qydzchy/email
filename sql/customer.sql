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

 Date: 30/10/2023 22:51:57
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户查重设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for customer_field_metadata
-- ----------------------------
DROP TABLE IF EXISTS `customer_field_metadata`;
CREATE TABLE `customer_field_metadata`  (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段名称',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字段别名',
  `type` tinyint(1) NOT NULL COMMENT '类型 1.公司信息 2.联系人信息 3.日期时间',
  `order_index` bigint(20) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户字段元数据' ROW_FORMAT = Dynamic;

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
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '别名',
  `type` tinyint(1) NOT NULL COMMENT '类型 1.公司信息 2.联系人信息 3.日期时间',
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
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海规则表' ROW_FORMAT = Dynamic;

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