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

 Date: 23/09/2023 11:10:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mailbox_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_blacklist`;
CREATE TABLE `mailbox_blacklist`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(1) NOT NULL COMMENT '类型: 1.邮箱地址黑名单 2.域名黑名单',
  `content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱地址或域名',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '黑名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_folder
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_folder`;
CREATE TABLE `mailbox_folder`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_folder_id` bigint(20) NULL DEFAULT NULL COMMENT '父文件夹的ID,如果是顶级文件夹,此项为NULL',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件夹名称',
  `type` tinyint(1) NOT NULL DEFAULT 2 COMMENT '类型: 1.系统文件夹 2.自定义文件夹',
  `order_num` int(11) NOT NULL DEFAULT 0 COMMENT '顺序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件夹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_general
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_general`;
CREATE TABLE `mailbox_general`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `default_task_id` bigint(20) NULL DEFAULT NULL COMMENT '默认邮箱',
  `max_per_page` int(5) NOT NULL DEFAULT 20 COMMENT '每页显示邮件数量',
  `email_reading_mode_flag` tinyint(1) NOT NULL COMMENT '邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示',
  `move_delete_report` tinyint(1) NOT NULL DEFAULT 1 COMMENT '移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表',
  `language` tinyint(1) NOT NULL DEFAULT 1 COMMENT '语言: 1.简体中文 2.繁体中文 3.英文',
  `default_font` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '默认字体',
  `font_size` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文字大小',
  `word_color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文字颜色',
  `group_box_view` tinyint(1) NOT NULL DEFAULT 1 COMMENT '群发箱视图​: 1.任务视图 2.邮件视图',
  `remind` tinyint(1) NULL DEFAULT NULL COMMENT '提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒',
  `signature_id` bigint(20) NULL DEFAULT NULL COMMENT '签名ID',
  `write_letter` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效)',
  `auto_response_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次)',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `last_day` datetime(0) NULL DEFAULT NULL COMMENT '最后一天',
  `re_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回复内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱常规表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_general_single
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_general_single`;
CREATE TABLE `mailbox_general_single`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `general_id` bigint(20) NOT NULL COMMENT '配置常规ID',
  `letter_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '写信昵称',
  `default_signature_id` bigint(20) NULL DEFAULT NULL COMMENT '默认签名',
  `reply_signature_id` bigint(20) NULL DEFAULT NULL COMMENT '回复/转发签名',
  `default_cc_flag` tinyint(1) NOT NULL COMMENT '默认抄送: 0.关闭 1.启用',
  `default_cc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认抄送,请使用\";\"分隔多个邮箱',
  `default_bcc_flag` tinyint(1) NOT NULL COMMENT '默认密送: 0.关闭 1.启用',
  `default_bcc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认密送,请使用\";\"分隔多个邮箱',
  `trace_flag` tinyint(1) NOT NULL COMMENT '是否追踪邮件: 0.否 1.是',
  `return_receipt_flag` tinyint(1) NOT NULL COMMENT '是否回执: 0.否 1.是',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单个邮箱设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_host
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_host`;
CREATE TABLE `mailbox_host`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `domain` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '域名',
  `pop_host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'pop3服务器地址',
  `imap_host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'imap服务器地址',
  `exchange_host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'exchange服务器地址,exchange有自动识别的方法,此字段作为备用字段',
  `smtp_host` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'smtp服务器地址',
  `pop_port` int(11) NULL DEFAULT NULL COMMENT 'pop端口号',
  `imap_port` int(11) NULL DEFAULT NULL COMMENT 'imap端口号',
  `exchange_port` int(11) NULL DEFAULT NULL COMMENT '冗余字段',
  `smtp_port` int(11) NULL DEFAULT NULL COMMENT 'smtp端口号',
  `pop_ssl` tinyint(1) NULL DEFAULT NULL COMMENT 'pop ssl',
  `imap_ssl` tinyint(1) NULL DEFAULT NULL COMMENT 'imap ssl',
  `exchange_ssl` tinyint(1) NULL DEFAULT NULL COMMENT 'exchange ssl',
  `smtp_ssl` tinyint(1) NULL DEFAULT NULL COMMENT 'smtp ssl',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱服务器查询表' ROW_FORMAT = Dynamic;

INSERT INTO `mailbox_host` VALUES (1, 'vip.qq.com', 'pop.qq.com', 'imap.qq.com', '', 'smtp.qq.com', 995, 993, -1, 465, 1, 1, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (2, 'oxfam.org.hk', 'eas.outlook.com', 'eas.outlook.com', '', 'eas.outlook.com', 443, 443, -1, 443, NULL, 0, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (3, 'gmail.com', 'pop.gmail.com', 'imap.gmail.com', '', 'smtp.gmail.com', 995, 993, -1, 465, 1, 1, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (4, 'yeah.net', 'pop.yeah.net', 'imap.yeah.net', '', 'smtp.yeah.net', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (5, 'tom.com', 'pop.tom.com', '', '', 'smtp.tom.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (6, '21cn.com', 'pop.21cn.com', 'imap.21cn.com', '', 'smtp.21cn.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (7, 'outlook.com', 'pop-mail.outlook.com', 'imap-mail.outlook.com', '', 'smtp-mail.outlook.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (8, 'hotmail.com', 'pop-mail.outlook.com', 'imap-mail.outlook.com', '', 'smtp-mail.outlook.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (9, 'qq.com', 'pop.qq.com', 'imap.qq.com', '', 'smtp.qq.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (10, '139.com', 'pop.139.com', 'imap.139.com', '', 'smtp.139.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (11, 'sina.com', 'pop.sina.com', 'imap.sina.com', '', 'smtp.sina.com', 995, 143, -1, 25, 0, 0, NULL, 0);
INSERT INTO `mailbox_host` VALUES (12, 'sohu.com', 'mail.sohu.com', '', '', 'mail.sohu.com', 110, 993, -1, 465, 0, NULL, NULL, 1);
INSERT INTO `mailbox_host` VALUES (13, 'yahoo.com', 'pop.mail.yahoo.com', '', '', 'smtp.mail.yahoo.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (14, '163.com', 'pop.163.com', 'imap.163.com', '', 'smtp.163.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (15, '126.com', 'pop3.126.com', 'imap.126.com', '', 'smtp.126.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (16, '189.cn', 'pop.189.cn', 'imap.189.cn', '', 'smtp.189.cn', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (17, 'foxmail.com', 'pop.qq.com', 'imap.qq.com', '', 'smtp.qq.com', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (18, 'chinaren.com', 'pop.chinaren.com', '', '', '', 995, 993, -1, 465, NULL, NULL, NULL, NULL);
INSERT INTO `mailbox_host` VALUES (19, 'tesnov.com', NULL, 'imap.ym.163.com', NULL, 'smtp.ym.163.com', NULL, 993, NULL, 465, NULL, 1, NULL, 1);
INSERT INTO `mailbox_host` VALUES (20, 'allxchips.com', 'pop3.mxhichina.com', NULL, NULL, 'smtp.mxhichina.com', 995, NULL, NULL, 465, 1, NULL, NULL, 1);

-- ----------------------------
-- Table structure for mailbox_import_email
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_import_email`;
CREATE TABLE `mailbox_import_email`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `file_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `task_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `folder_id` bigint(20) NOT NULL COMMENT '文件夹ID',
  `folder_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件夹名称',
  `import_status` tinyint(1) NOT NULL COMMENT '导入状态: 1.正在处理 2.导入成功 3.导入失败',
  `failure_reasons` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '失败原因',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导入邮件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_label
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_label`;
CREATE TABLE `mailbox_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签颜色',
  `type` tinyint(1) NOT NULL COMMENT '类型: 1.系统标签 2.自定义标签',
  `order` int(11) NOT NULL COMMENT '顺序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_other_config
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_other_config`;
CREATE TABLE `mailbox_other_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delayed_mail_delivery` tinyint(1) NOT NULL DEFAULT 1 COMMENT '邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送',
  `sending_interval` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发送间隔',
  `local_real_time_time` tinyint(1) NOT NULL COMMENT '当地实时时间: 0.停用 1.启用',
  `email_reminders_flag` tinyint(1) NOT NULL COMMENT '邮件发送提醒: 1.弹窗提醒 2.不提醒',
  `email_translation_function_flag` tinyint(1) NOT NULL COMMENT '邮件翻译功能: 0.停用 1.启用',
  `pending_close` tinyint(1) NOT NULL COMMENT '待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态',
  `automation_pending` tinyint(1) NOT NULL COMMENT '自动化待处理: 0.停用 1.启用',
  `abnormal_mailbox_detection` tinyint(1) NOT NULL COMMENT '异常邮箱检测: 0.停用 1.启用',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '其他配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_quick_text
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_quick_text`;
CREATE TABLE `mailbox_quick_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '快速文本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_signature
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_signature`;
CREATE TABLE `mailbox_signature`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '个性签名表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task`;
CREATE TABLE `mailbox_task`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱账号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱密码',
  `alias` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱别名',
  `conn_status` tinyint(1) NOT NULL COMMENT '连接状态: 1.正常 2.异常',
  `conn_exception_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '连接异常原因',
  `protocol_type` tinyint(1) NOT NULL COMMENT '协议类型 1.IMAP 2.POP3 3.EXCHANGE',
  `receiving_server` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收件服务器',
  `receiving_port` int(11) NOT NULL COMMENT '收件端口',
  `receiving_ssl_flag` tinyint(1) NOT NULL COMMENT '收件SSL: 0.否 1.是',
  `outgoing_server` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发件服务器',
  `outgoing_port` int(11) NULL DEFAULT NULL COMMENT '发件端口',
  `outgoing_ssl_flag` tinyint(1) NULL DEFAULT NULL COMMENT '发件SSL: 0.否 1.是',
  `custom_proxy_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '自定义代理: 0.关闭 1.开启',
  `proxy_server_type` tinyint(1) NULL DEFAULT NULL COMMENT '代理服务器类型 1.HTTP代理 2.SOCKS5',
  `proxy_server` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理服务器',
  `proxy_port` int(11) NULL DEFAULT NULL COMMENT '代理端口',
  `proxy_username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理用户名',
  `proxy_password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '代理密码',
  `synchronize_folder_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '同步文件夹 0关闭 1开启',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱任务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_attachment
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_attachment`;
CREATE TABLE `mailbox_task_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `size` int(10) NULL DEFAULT NULL COMMENT '附件大小',
  `path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '附件地址路径',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 660 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email`;
CREATE TABLE `mailbox_task_email`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(20) NOT NULL COMMENT '邮箱任务ID',
  `type` tinyint(1) NOT NULL COMMENT '类型 1.收件 2.发件',
  `uid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮件唯一ID',
  `fromer` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发件人',
  `receiver` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人JSON',
  `bcc` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密送人JSON,格式和收件人一样',
  `cc` varchar(3072) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '抄送人JSON,格式和收件人一样',
  `folder` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属文件夹,对应邮箱平台文件夹',
  `folder_id` bigint(20) NULL DEFAULT -1 COMMENT '文件夹ID,系统文件夹',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮件主题',
  `send_date` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `eml_path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始邮件存储路径',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败',
  `trace_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否追踪 0.否 1.是',
  `delayed_tx_flag` tinyint(1) NULL DEFAULT 0 COMMENT '定时发送: 0.否 1.是',
  `recipient_time_zone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收件人所在时区',
  `recipient_local_time` datetime(0) NULL DEFAULT NULL COMMENT '收件人当地时间',
  `read_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读: 0.否 1.是',
  `pending_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否待处理: 0.否 1.是',
  `pending_time` datetime(0) NULL DEFAULT NULL COMMENT '待处理时间',
  `fixed_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否固定: 0.否 1.是',
  `spam_flag` tinyint(1) NULL DEFAULT 0 COMMENT '垃圾邮件: 0.否 1.是',
  `message_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '每封邮件都应该有一个唯一的 Message-ID',
  `in_reply_to` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '当你回复一封邮件时，你应该设置 In-Reply-To 头为原始邮件的 Message-ID。',
  `reference` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '邮件链的列表',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_id`(`task_id`) USING BTREE COMMENT '任务索引',
  INDEX `idx_uid`(`uid`) USING BTREE COMMENT '邮件唯一id索引',
  INDEX `idx_type`(`type`) USING BTREE COMMENT '邮件类型索引'
) ENGINE = InnoDB AUTO_INCREMENT = 4672 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_attachment
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_attachment`;
CREATE TABLE `mailbox_task_email_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT '邮件ID',
  `attachment_id` bigint(20) NOT NULL COMMENT '附件ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_email_id`(`email_id`) USING BTREE COMMENT '邮件ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 512 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_content
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_content`;
CREATE TABLE `mailbox_task_email_content`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT '邮件ID',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '邮件内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7473 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件内容表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_dealings
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_dealings`;
CREATE TABLE `mailbox_task_email_dealings`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '唯一标识符',
  `fromer` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发件邮箱',
  `receiver` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收件邮箱',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '往来邮件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_pull_label
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_pull_label`;
CREATE TABLE `mailbox_task_email_pull_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_pull_id` bigint(20) NOT NULL COMMENT '收取邮件ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签ID',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_trace
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_trace`;
CREATE TABLE `mailbox_task_email_trace`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT '发送邮件ID',
  `beijing_time` datetime(0) NOT NULL COMMENT '北京时间',
  `local_time` datetime(0) NOT NULL COMMENT '当地时间',
  `ip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'IP地址',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '位置',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件追踪表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_template
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_template`;
CREATE TABLE `mailbox_template`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `template_type_id` bigint(20) NULL DEFAULT NULL COMMENT '模板类型',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '模板内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_template_type
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_template_type`;
CREATE TABLE `mailbox_template_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_transceiver_rule
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_transceiver_rule`;
CREATE TABLE `mailbox_transceiver_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_type` tinyint(1) NOT NULL COMMENT '规则类型: 1.收件规则 2.发件规则',
  `rule_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '基本信息-规则名称',
  `execute_condition` tinyint(1) NOT NULL COMMENT '执行条件: 1.执行以下操作 2.满足以下任一条件',
  `execute_condition_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行条件内容',
  `execute_operation` tinyint(1) NOT NULL COMMENT '执行操作: 1.执行以下操作 2.移动到【已删除邮件】',
  `execute_operation_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行操作内容',
  `apply_to_history_mail_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '应用于历史邮件: 0.否 1.是',
  `execute_task_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '执行邮箱ID: 0.表示全部邮箱',
  `other_sending_rules` tinyint(1) NOT NULL COMMENT '其他发件规则: 1.继续执行 2.不再执行',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建者',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '更新者',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收发件规则表' ROW_FORMAT = Dynamic;


-- 菜单
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2002, '邮件', 0, 4, 'email/index', 'email/index', NULL, 1, 0, 'C', '0', '0', 'email:index', 'email', 'admin', '2023-08-03 14:10:27', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2003, '邮件列表-（首页）', 2002, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:list', '#', 'admin', '2023-10-11 16:26:53', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2004, '邮件保存-（写信）', 2002, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:save', '#', 'admin', '2023-10-11 16:27:15', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2005, '邮件发送-（写信）', 2002, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send', '#', 'admin', '2023-10-11 16:27:33', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2006, '邮件固定', 2002, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:fixed', '#', 'admin', '2023-10-11 16:28:12', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2007, '邮件删除', 2002, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:delete', '#', 'admin', '2023-10-11 16:28:38', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2008, '快速回复', 2002, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send:quick:reply', '#', 'admin', '2023-10-11 16:33:08', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2009, '标记为已读邮件', 2002, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:read', '#', 'admin', '2023-10-11 16:33:53', 'admin', '2023-10-11 16:34:40', '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2010, '标记为垃圾邮件', 2002, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:spam', '#', 'admin', '2023-10-11 16:34:28', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2011, '移动邮件到文件夹', 2002, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:folder', '#', 'admin', '2023-10-11 16:36:38', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2012, '移动邮件到标签', 2002, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:label', '#', 'admin', '2023-10-11 16:37:12', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2013, '标记待处理', 2002, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:pending', '#', 'admin', '2023-10-11 16:37:32', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2014, '邮件导出', 2002, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:export', '#', 'admin', '2023-10-11 16:38:12', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2015, '附件上传', 2002, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:upload', '#', 'admin', '2023-10-11 16:40:19', 'admin', '2023-10-11 16:45:30', '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2016, '附件下载', 2002, 14, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:download', '#', 'admin', '2023-10-11 16:40:41', 'admin', '2023-10-11 16:45:36', '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2017, '附件重命名', 2002, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:rename', '#', 'admin', '2023-10-11 16:44:23', 'admin', '2023-10-11 16:45:44', '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2018, '附件删除', 2002, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:delete', '#', 'admin', '2023-10-11 16:45:03', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2019, '文件夹列表', 2002, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:list', '#', 'admin', '2023-10-11 16:46:24', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2020, '文件夹新增', 2002, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:add', '#', 'admin', '2023-10-11 16:46:47', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2021, '文件夹修改', 2002, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:edit', '#', 'admin', '2023-10-11 16:47:17', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2022, '文件夹删除', 2002, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:delete', '#', 'admin', '2023-10-11 16:47:36', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2023, '标签列表', 2002, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:list', '#', 'admin', '2023-10-11 16:48:09', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2024, '标签新增', 2002, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:add', '#', 'admin', '2023-10-11 16:48:27', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2025, '标签颜色修改', 2002, 23, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:color', '#', 'admin', '2023-10-11 16:48:51', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2026, '标签名称修改', 2002, 24, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:name', '#', 'admin', '2023-10-11 16:49:18', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2027, '标签删除', 2002, 25, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:delete', '#', 'admin', '2023-10-11 16:49:39', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2028, '邮箱列表', 2002, 26, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:list', '#', 'admin', '2023-10-11 16:50:10', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2029, '收件箱列表', 2002, 27, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:pull:list', '#', 'admin', '2023-10-11 16:54:44', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2030, '发件箱列表', 2002, 28, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:send:list', '#', 'admin', '2023-10-11 16:55:05', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2031, '邮箱新增', 2002, 29, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:add', '#', 'admin', '2023-10-11 16:55:29', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2032, '邮箱检测', 2002, 30, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:test', '#', 'admin', '2023-10-11 16:56:59', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2033, '邮箱修改', 2002, 31, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:edit', '#', 'admin', '2023-10-11 16:57:25', '', NULL, '');
INSERT INTO `email`.`sys_menu`(`menu_id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES (2034, '邮箱解绑', 2002, 32, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:unbind', '#', 'admin', '2023-10-11 16:57:46', '', NULL, '');

-- 定时任务
INSERT INTO `sys_job` VALUES (4, '邮件同步', 'SYSTEM', 'emailTask.syncEmail()', '0 0/5 * * * ?', '1', '1', '0', 'admin', '2023-09-10 16:11:28', 'admin', '2023-09-13 21:10:43', '');
INSERT INTO `sys_job` VALUES (5, '发送邮件', 'SYSTEM', 'emailTask.sendEmail', '0 0/2 * * * ? ', '1', '0', '0', 'admin', '2023-09-13 21:10:21', '', '2023-09-13 21:10:26', '');