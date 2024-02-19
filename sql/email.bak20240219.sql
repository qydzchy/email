/*
 Navicat Premium Data Transfer

 Source Server         : 42.194.167.180
 Source Server Type    : MySQL
 Source Server Version : 50739
 Source Host           : 42.194.167.180:3306
 Source Schema         : email

 Target Server Type    : MySQL
 Target Server Version : 50739
 File Encoding         : 65001

 Date: 19/02/2024 09:34:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob NULL COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Blob类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'Cron类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint(7) NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint(12) NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint(10) NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '简单触发器的信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int(11) NULL DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint(20) NULL DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `QRTZ_TRIGGERS` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '同步机制的行锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS`  (
  `sched_name` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint(13) NULL DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint(13) NULL DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int(11) NULL DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint(13) NOT NULL COMMENT '开始时间',
  `end_time` bigint(13) NULL DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint(2) NULL DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `QRTZ_JOB_DETAILS` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '触发器详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for base_country
-- ----------------------------
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

-- ----------------------------
-- Table structure for base_timezone
-- ----------------------------
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '建档黑名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_black_list_records
-- ----------------------------
INSERT INTO `customer_black_list_records` VALUES (1, '@tangjm.com', '0', 1, 'admin', '2023-11-28 18:09:54', 1, 'admin', '2023-11-28 18:09:54');

-- ----------------------------
-- Table structure for customer_customer
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer`;
CREATE TABLE `customer_customer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_website` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司网址',
  `company_name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `short_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `country_region` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '国家地区',
  `sea_type` tinyint(1) NOT NULL COMMENT '私海/公海类型 1.私海 2.公海',
  `publicleads_groups_id` bigint(20) NULL DEFAULT NULL COMMENT '公海分组ID',
  `packet_id` bigint(20) NULL DEFAULT NULL COMMENT '分组ID',
  `stage_id` bigint(20) NULL DEFAULT NULL COMMENT '阶段ID',
  `rating` tinyint(1) NULL DEFAULT NULL COMMENT '客户星级 1 2 3 4 5',
  `customer_no_type` tinyint(1) NOT NULL COMMENT '客户编号类型 1.自动生成 2.自定义',
  `customer_no` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客户编号',
  `phone_prefix` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `timezone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时区',
  `scale` tinyint(1) NULL DEFAULT NULL COMMENT '规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上',
  `fax` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '传真',
  `address` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 975 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer
-- ----------------------------
INSERT INTO `customer_customer` VALUES (11, 'www.baidu.com', '店小秘', '小秘', 'CN/北京/西城', 2, 11, 1, 1, 1, 1, '1699668627026', '0663', '1307650000', NULL, 1, '111111', '广东省揭阳市空港经济区地都镇xxxx号', '公司备注', 'http://www.baidu.com/path', 0, '2023-11-27 07:53:56', '2023-11-27 07:53:56', '0', 1, 'admin', '2023-11-11 10:10:27', 1, 'admin', '2023-11-28 08:24:13');
INSERT INTO `customer_customer` VALUES (12, 'www.baidu.com', '鱼之乐', '鱼', 'CN/北京/东城', 1, NULL, 1, NULL, 5, 1, '1699929806819', NULL, NULL, '3', 2, 'aaaa', 'aaa', 'aaaa', NULL, 0, '2024-02-07 17:08:21', NULL, '0', 1, 'admin', '2023-11-14 10:43:27', 1, 'admin', '2024-02-07 17:08:21');
INSERT INTO `customer_customer` VALUES (13, 'www.islet.com', '娃哈哈', '娃', 'CN/山西/阳泉', 1, NULL, 7, 1, 5, 1, '1700526147913', '+355', '1308768822', NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-11-21 08:22:28', NULL, '0', 1, 'admin', '2023-11-21 08:22:28', 1, 'admin', '2023-11-28 14:30:33');
INSERT INTO `customer_customer` VALUES (14, 'www.ceshi01.com', '公司01', '01', 'TW', 1, NULL, 4, NULL, 3, 1, '1700530656969', '+86', '13087607711', NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-11-21 09:37:37', NULL, '0', 1, 'admin', '2023-11-21 09:37:37', 1, 'admin', '2023-11-21 09:37:37');
INSERT INTO `customer_customer` VALUES (15, 'www.ceshi02.com', '公司02', '02', 'TW', 1, NULL, 4, 3, 4, 1, '1700530812371', '+86', '13087607722', NULL, NULL, NULL, NULL, NULL, NULL, 0, '2023-12-12 09:24:33', NULL, '0', 1, 'admin', '2023-11-21 09:40:12', 1, 'admin', '2023-12-12 09:24:33');
INSERT INTO `customer_customer` VALUES (16, 'wwwww', '公司03', NULL, 'CN/北京/东城', 1, NULL, 1, 2, 5, 1, '1700547579009', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, 0, '2023-11-21 14:19:39', NULL, '0', 1, 'admin', '2023-11-21 14:19:39', 1, 'admin', '2023-11-21 14:19:39');
INSERT INTO `customer_customer` VALUES (17, 'wwwww', '公司03', NULL, 'CN/北京/东城', 1, 7, 2, 2, 5, 1, '1700548939850', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, 0, '2024-02-06 17:06:59', NULL, '0', 1, 'admin', '2023-11-21 14:42:20', 1, 'admin', '2024-02-06 17:06:58');
INSERT INTO `customer_customer` VALUES (18, NULL, '公司04', '', 'PE', 1, 7, 4, 4, 2, 1, '1700549730178', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, '2024-01-05 01:05:06', NULL, '0', 1, 'admin', '2023-11-21 14:55:30', 1, 'admin', '2024-01-05 01:05:06');
INSERT INTO `customer_customer` VALUES (19, 'www.baidu.com', '客户001号', '001', 'CA', 1, NULL, 1, 3, 3, 1, '1700549730179', NULL, NULL, '20', NULL, NULL, NULL, NULL, '', 0, '2023-11-27 08:59:50', NULL, '0', 1, 'admin', '2023-11-27 08:59:50', 1, 'admin', '2023-11-27 08:59:50');
INSERT INTO `customer_customer` VALUES (20, 'www.baidu.com', '客户002号', '002', 'LC', 2, 7, 1, 4, 5, 1, '1700549730180', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', 0, '2023-11-27 09:01:13', NULL, '0', 1, 'admin', '2023-11-27 09:01:13', 1, 'admin', '2023-11-28 15:18:27');
INSERT INTO `customer_customer` VALUES (21, NULL, '测试001', NULL, NULL, 1, 1, NULL, NULL, 5, 1, '1700549730181', NULL, NULL, '0', NULL, NULL, NULL, NULL, '/dev-api/profile/upload/2023/12/31/pic_20231231012309A001.jpg', 1, '2024-01-02 14:34:01', '2024-01-02 14:34:01', '0', 1, 'admin', '2023-11-28 18:12:31', 1, 'admin', '2024-01-10 14:29:10');

-- ----------------------------
-- Table structure for customer_customer_contact
-- ----------------------------
DROP TABLE IF EXISTS `customer_customer_contact`;
CREATE TABLE `customer_customer_contact`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `customer_id` bigint(20) NOT NULL COMMENT '客户详情ID',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `social_platform` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rank` tinyint(1) NULL DEFAULT NULL COMMENT '职级 1.普通职员 2.中层管理者 3.高层管理者',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
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
) ENGINE = InnoDB AUTO_INCREMENT = 1699929801498 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户联系人表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_contact
-- ----------------------------
INSERT INTO `customer_customer_contact` VALUES (7, 11, '张三', '278882047@qq.com', '', '', 1, '后端开发', '1995-01-01 00:00:00', 1, 'http://www.baidu.com/card/path', '小明的朋友', 1, NULL, '0', 1, 'admin', '2023-11-11 10:10:27', 1, 'admin', '2023-11-11 10:10:27');
INSERT INTO `customer_customer_contact` VALUES (8, 11, '李四', 'lisi@qq.com', '', '', 1, '前端开发', '1996-01-01 00:00:00', 1, 'http://www.baidu.com/card/path2', '小明的朋友2', 0, NULL, '0', 1, 'admin', '2023-11-11 10:10:27', 1, 'admin', '2023-11-11 10:10:27');
INSERT INTO `customer_customer_contact` VALUES (1699929744154, 12, '张三', 'w0r1d_space@sina.com', '[{\"type\":\"WeChat\",\"account\":\"888\"},{\"type\":\"Skype\",\"account\":\"777\"},{\"type\":\"WhatsApp\",\"account\":\"666\"},{\"type\":\"LinkedIn\",\"account\":\"555\"},{\"type\":\"Twitter\",\"account\":\"444\"},{\"type\":\"Facebook\",\"account\":\"333\"},{\"type\":\"阿里TM\",\"account\":\"111\"},{\"type\":\"Line\",\"account\":\"222\"}]', '[{\"phone_prefix\":\"+355\",\"phone\":\"13076506601\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-14 10:43:27', 1, 'admin', '2024-02-06 16:11:39');
INSERT INTO `customer_customer_contact` VALUES (1699929794908, 12, '李四', 'lisi@qq.com', '[{},{},{},{},{},{},{},{}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 0, NULL, '0', 1, 'admin', '2023-11-14 10:43:27', 1, 'admin', '2024-02-06 16:11:39');
INSERT INTO `customer_customer_contact` VALUES (1699929794909, 13, '王五', 'wangwu@qq.com', '[{\"type\":\"Facebook\",\"account\":\"aaaa\"}]', '[{\"phone_prefix\":\"+355\",\"phone\":\"1111111\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 08:22:28', 1, 'admin', '2023-11-21 08:22:28');
INSERT INTO `customer_customer_contact` VALUES (1699929794910, 14, '张三', 'w0r1d_space@tom.com', '[{\"type\":\"Facebook\",\"account\":\"aaaaaa\"}]', '[{\"phone_prefix\":\"+86\",\"phone\":\"22222\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 09:37:37', 1, 'admin', '2023-11-21 09:37:37');
INSERT INTO `customer_customer_contact` VALUES (1699929794911, 14, '李四', 'lisi@139.com', '[{\"type\":\"阿里TM\",\"account\":\"11111\"}]', '[{\"phone_prefix\":\"+971\",\"phone\":\"111111\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 09:37:37', 1, 'admin', '2023-11-21 09:37:37');
INSERT INTO `customer_customer_contact` VALUES (1699929794912, 15, '王五', 'm15217649756@163.com', '[{\"type\":\"阿里TM\",\"account\":\"22222\"}]', '[{\"phone_prefix\":\"+355\",\"phone\":\"11111\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 09:40:12', 1, 'admin', '2023-11-21 09:40:12');
INSERT INTO `customer_customer_contact` VALUES (1699929794913, 16, 'hahaha', 'w0r1d_space@sina.com', '[{\"type\":\"Facebook\",\"account\":\"aaaa\"}]', '[{\"phone_prefix\":\"+355\",\"phone\":\"1344\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 14:19:39', 1, 'admin', '2023-11-21 14:19:39');
INSERT INTO `customer_customer_contact` VALUES (1699929794914, 17, '赵六', 'zhaoliu@163.com', '[{\"type\":\"WhatsApp\",\"account\":\"222\"},{\"type\":\"Messenger\",\"account\":\"111\"}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 14:42:20', 1, 'admin', '2024-02-06 17:06:58');
INSERT INTO `customer_customer_contact` VALUES (1699929794915, 18, '田七', 'tianqi@qq.com', '[{\"type\":\"\",\"account\":\"\"}]', '[{\"phone_prefix\":\"+93\",\"phone\":\"2222\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-21 14:55:30', 1, 'admin', '2023-12-21 10:35:13');
INSERT INTO `customer_customer_contact` VALUES (1699929794916, 19, '001号', '001@qq.com', '[{\"type\":\"Facebook\",\"account\":\"f0001\"}]', '[{\"phone_prefix\":\"+355\",\"phone\":\"1111\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-27 08:59:50', 1, 'admin', '2023-11-27 08:59:50');
INSERT INTO `customer_customer_contact` VALUES (1699929794917, 20, '002号', 'm18224457632@163.com', '[{\"type\":\"LinkedIn\",\"account\":\"l0001\"}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-27 09:01:13', 1, 'admin', '2023-11-27 09:01:13');
INSERT INTO `customer_customer_contact` VALUES (1699929794918, 21, '嘉铭', '13076506601@139.com', '[{\"type\":\"\",\"account\":\"\"}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-11-28 18:12:31', 1, 'admin', '2023-12-31 01:23:13');
INSERT INTO `customer_customer_contact` VALUES (1699929794919, 21, 'jiaming', 'w0r1d_space1@sohu.com', '[{\"type\":\"\",\"account\":\"\"}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-12-21 10:34:49', 1, 'admin', '2023-12-31 01:23:13');
INSERT INTO `customer_customer_contact` VALUES (1699929794920, 18, '张三', '278882047@qq.com', '[{\"type\":\"\",\"account\":\"\"}]', '[{\"phone_prefix\":\"\",\"phone\":\"\"}]', NULL, '', NULL, 1, '', '', 1, NULL, '0', 1, 'admin', '2023-12-21 10:35:13', 1, 'admin', '2023-12-21 10:35:13');

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

-- ----------------------------
-- Records of customer_customer_document
-- ----------------------------
INSERT INTO `customer_customer_document` VALUES (1, '手机号.xlsx', 'D:\\back\\Desktop\\file\\\\xlsx', 'xlsx', 8648, 2, 11, '0', 1, 'admin', '2023-11-20 07:52:30', 1, 'admin', '2023-11-20 07:52:30');
INSERT INTO `customer_customer_document` VALUES (2, '新建文本文档.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 9941, 2, 11, '2', 1, 'admin', '2023-11-20 07:52:30', 1, 'admin', '2023-11-20 08:16:18');
INSERT INTO `customer_customer_document` VALUES (3, '新文件 34.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 584, 2, 11, '0', 1, 'admin', '2023-11-24 17:30:51', 1, 'admin', '2023-11-24 17:30:51');
INSERT INTO `customer_customer_document` VALUES (4, '提词器.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 143175, 2, 11, '0', 1, 'admin', '2023-11-24 17:32:22', 1, 'admin', '2023-11-24 17:32:22');
INSERT INTO `customer_customer_document` VALUES (5, '新文件 34.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 584, 2, 11, '0', 1, 'admin', '2023-11-24 17:48:19', 1, 'admin', '2023-11-24 17:48:19');
INSERT INTO `customer_customer_document` VALUES (6, '提词器.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 143175, 2, 11, '0', 1, 'admin', '2023-11-24 17:52:03', 1, 'admin', '2023-11-24 17:52:03');
INSERT INTO `customer_customer_document` VALUES (7, '新文件 34.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 584, 2, 11, '0', 1, 'admin', '2023-11-24 17:52:31', 1, 'admin', '2023-11-24 17:52:31');
INSERT INTO `customer_customer_document` VALUES (8, '新文件 34.txt', 'D:\\back\\Desktop\\file\\\\txt', 'txt', 584, 2, 11, '0', 1, 'admin', '2023-11-24 17:54:40', 1, 'admin', '2023-11-24 17:54:40');
INSERT INTO `customer_customer_document` VALUES (9, 'tracker.png', 'D:\\back\\Desktop\\file\\\\png', 'png', 95, 2, 11, '0', 1, 'admin', '2023-11-24 17:56:00', 1, 'admin', '2023-11-24 17:56:00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 997 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户跟进人' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_follow_up_personnel
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户写跟进' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_follow_up_records
-- ----------------------------
INSERT INTO `customer_customer_follow_up_records` VALUES (3, 11, 1, '跟进内容03', '2023-11-09 00:01:02', 1, '2023-11-12 00:01:02', 1, '日程内容03', '#eeeeee', '我是备注03', '0', 1, 'admin', '2023-11-11 14:18:48', 1, 'admin', '2023-11-11 14:30:03');
INSERT INTO `customer_customer_follow_up_records` VALUES (4, 11, 1, '跟进内容01', '2023-11-11 00:01:02', 1, '2023-11-12 00:01:02', 1, '日程内容01', '#CCCCCC', '我是备注01', '0', 1, 'admin', '2023-11-11 14:19:00', 1, 'admin', '2023-11-11 14:19:00');
INSERT INTO `customer_customer_follow_up_records` VALUES (5, 11, 1, '看书旅游爬山', '2023-11-27 07:53:01', 1, NULL, 1, '', '#ff3333', '', '0', 1, 'admin', '2023-11-27 07:53:56', 1, 'admin', '2023-11-27 07:53:56');
INSERT INTO `customer_customer_follow_up_records` VALUES (6, 21, 1, '爬山旅游看书', '2024-01-02 14:33:33', 1, NULL, 1, '', '#ff3333', '', '0', 1, 'admin', '2024-01-02 14:34:01', 1, 'admin', '2024-01-02 14:34:01');

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
-- Records of customer_customer_follow_up_records_comment
-- ----------------------------
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (1, 1, '评论001', '0', NULL, NULL, NULL, 1, 'admin', '2023-11-11 14:58:01');
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (2, 1, '222', '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (3, 3, '评论01', '2', 1, 'admin', '2023-11-11 14:54:17', 1, 'admin', '2023-11-11 15:01:33');
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (4, 3, '评论02', '0', 1, 'admin', '2023-11-11 14:54:29', 1, 'admin', '2023-11-11 14:54:29');
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (5, 3, '评论03', '0', 1, 'admin', '2023-11-11 14:54:37', 1, 'admin', '2023-11-11 14:54:37');
INSERT INTO `customer_customer_follow_up_records_comment` VALUES (6, 4, '评论01', '0', 1, 'admin', '2023-11-11 14:54:44', 1, 'admin', '2023-11-11 14:54:44');

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_import
-- ----------------------------
INSERT INTO `customer_customer_import` VALUES (1, 1, 1, 0, 0, 0, '0', 1, 'admin', '2024-01-16 21:39:14', 1, 'admin', '2024-01-16 21:39:14');
INSERT INTO `customer_customer_import` VALUES (2, 1, 1, 0, 0, 0, '0', 1, 'admin', '2024-01-16 21:41:15', 1, 'admin', '2024-01-16 21:41:15');
INSERT INTO `customer_customer_import` VALUES (3, 1, 1, 0, 0, 0, '0', 1, 'admin', '2024-01-16 21:43:47', 1, 'admin', '2024-01-16 21:43:47');
INSERT INTO `customer_customer_import` VALUES (4, 1, 2, 17, 17, 0, '0', 1, 'admin', '2024-01-16 21:45:26', 1, 'admin', '2024-01-16 21:45:26');
INSERT INTO `customer_customer_import` VALUES (5, 1, 2, 0, 0, 0, '0', 1, 'admin', '2024-01-31 17:29:41', 1, 'admin', '2024-01-31 17:29:44');
INSERT INTO `customer_customer_import` VALUES (6, 1, 2, 33, 27, 6, '0', 1, 'admin', '2024-01-31 17:35:09', 1, 'admin', '2024-01-31 17:35:09');
INSERT INTO `customer_customer_import` VALUES (7, 1, 2, 33, 27, 6, '0', 1, 'admin', '2024-01-31 17:50:26', 1, 'admin', '2024-01-31 17:50:26');
INSERT INTO `customer_customer_import` VALUES (8, 1, 2, 956, 879, 77, '0', 1, 'admin', '2024-02-02 09:14:38', 1, 'admin', '2024-02-02 09:14:38');
INSERT INTO `customer_customer_import` VALUES (9, 1, 1, 0, 0, 0, '0', 1, 'admin', '2024-02-02 11:35:08', 1, 'admin', '2024-02-02 11:35:08');
INSERT INTO `customer_customer_import` VALUES (10, 1, 2, 956, 879, 77, '0', 1, 'admin', '2024-02-02 11:58:08', 1, 'admin', '2024-02-02 11:58:08');
INSERT INTO `customer_customer_import` VALUES (11, 2, 2, 956, 879, 77, '0', 1, 'admin', '2024-02-02 13:55:24', 1, 'admin', '2024-02-02 13:55:24');
INSERT INTO `customer_customer_import` VALUES (12, 2, 1, 0, 0, 0, '0', 1, 'admin', '2024-02-07 14:15:29', 1, 'admin', '2024-02-07 14:15:29');
INSERT INTO `customer_customer_import` VALUES (13, 2, 2, 956, 879, 77, '0', 1, 'admin', '2024-02-07 14:26:39', 1, 'admin', '2024-02-07 14:26:39');
INSERT INTO `customer_customer_import` VALUES (14, 2, 2, 33, 33, 0, '0', 1, 'admin', '2024-02-07 15:05:36', 1, 'admin', '2024-02-07 15:05:36');
INSERT INTO `customer_customer_import` VALUES (15, 2, 2, 1, 0, 1, '0', 1, 'admin', '2024-02-07 15:30:03', 1, 'admin', '2024-02-07 15:30:03');
INSERT INTO `customer_customer_import` VALUES (16, 2, 1, 0, 0, 0, '0', 1, 'admin', '2024-02-07 15:36:19', 1, 'admin', '2024-02-07 15:36:19');
INSERT INTO `customer_customer_import` VALUES (17, 2, 2, 1, 1, 0, '0', 1, 'admin', '2024-02-07 15:40:41', 1, 'admin', '2024-02-07 15:40:41');
INSERT INTO `customer_customer_import` VALUES (18, 2, 2, 1, 1, 0, '0', 1, 'admin', '2024-02-07 16:13:13', 1, 'admin', '2024-02-07 16:13:13');
INSERT INTO `customer_customer_import` VALUES (19, 1, 2, 1, 1, 0, '0', 3, 'zhangsan', '2024-02-19 08:43:31', 3, 'zhangsan', '2024-02-19 08:43:31');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入客户到公海表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_publicleads
-- ----------------------------
INSERT INTO `customer_customer_publicleads` VALUES (1, 11, 7, 1, '0', 1, 'admin', '2023-11-11 17:25:40', 1, 'admin', '2023-11-11 17:25:40');
INSERT INTO `customer_customer_publicleads` VALUES (2, 20, 7, 1, '0', 1, 'admin', '2023-11-28 08:24:00', 1, 'admin', '2023-11-28 08:24:00');
INSERT INTO `customer_customer_publicleads` VALUES (3, 11, 11, 1, '0', 1, 'admin', '2023-11-28 08:24:13', 1, 'admin', '2023-11-28 08:24:13');
INSERT INTO `customer_customer_publicleads` VALUES (4, 20, 7, 1, '0', 1, 'admin', '2023-11-28 14:31:10', 1, 'admin', '2023-11-28 14:31:10');
INSERT INTO `customer_customer_publicleads` VALUES (5, 20, 7, 1, '0', 1, 'admin', '2023-11-28 14:33:22', 1, 'admin', '2023-11-28 14:33:22');
INSERT INTO `customer_customer_publicleads` VALUES (6, 20, 7, 1, '0', 1, 'admin', '2023-11-28 14:33:54', 1, 'admin', '2023-11-28 14:33:54');
INSERT INTO `customer_customer_publicleads` VALUES (7, 20, 7, 1, '0', 1, 'admin', '2023-11-28 15:01:27', 1, 'admin', '2023-11-28 15:01:27');
INSERT INTO `customer_customer_publicleads` VALUES (8, 20, 7, 1, '0', 1, 'admin', '2023-11-28 15:10:38', 1, 'admin', '2023-11-28 15:10:38');
INSERT INTO `customer_customer_publicleads` VALUES (9, 20, 7, 1, '0', 1, 'admin', '2023-11-28 15:12:18', 1, 'admin', '2023-11-28 15:12:18');
INSERT INTO `customer_customer_publicleads` VALUES (10, 20, 7, 1, '0', 1, 'admin', '2023-11-28 15:18:27', 1, 'admin', '2023-11-28 15:18:27');
INSERT INTO `customer_customer_publicleads` VALUES (11, 18, 7, 1, '0', 1, 'admin', '2023-11-28 15:19:11', 1, 'admin', '2023-11-28 15:19:11');
INSERT INTO `customer_customer_publicleads` VALUES (12, 17, 7, 1, '0', 1, 'admin', '2023-11-28 15:19:21', 1, 'admin', '2023-11-28 15:19:21');

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
-- Records of customer_customer_schedule
-- ----------------------------
INSERT INTO `customer_customer_schedule` VALUES (5, 11, '日程内容测试01', '#cccccc', 1, '2023-11-11 00:00:00', '2023-11-11 23:59:59', 0, 5, 1, 1, '2023-11-11 23:59:59', '[{\"reminderTimeType\": 1, \"reminderTimeValue\": \"2023-11-03 00:00:00\"}]', 0, NULL, '0', 1, 'admin', '2023-11-11 16:36:38', 1, 'admin', '2023-11-11 16:36:38');
INSERT INTO `customer_customer_schedule` VALUES (6, 11, '日程内容测试02', '#cccccc', 1, '2023-11-11 00:00:00', '2023-11-11 23:59:59', 0, 5, 1, 1, '2023-11-11 23:59:59', '[{\"reminderTimeType\": 1, \"reminderTimeValue\": \"2023-11-03 00:00:00\"}]', 0, NULL, '0', 1, 'admin', '2023-11-11 16:36:43', 1, 'admin', '2023-11-11 16:36:43');

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
-- Records of customer_customer_schedule_participants
-- ----------------------------
INSERT INTO `customer_customer_schedule_participants` VALUES (9, 5, 1, '0', 1, 'admin', '2023-11-11 16:36:38', 1, 'admin', '2023-11-11 16:36:38');
INSERT INTO `customer_customer_schedule_participants` VALUES (10, 5, 2, '0', 2, 'admin', '2023-11-11 16:36:38', 1, 'admin', '2023-11-11 16:36:38');
INSERT INTO `customer_customer_schedule_participants` VALUES (11, 6, 1, '0', 1, 'admin', '2023-11-11 16:36:43', 1, 'admin', '2023-11-11 16:36:43');
INSERT INTO `customer_customer_schedule_participants` VALUES (12, 6, 2, '0', 2, 'admin', '2023-11-11 16:36:43', 1, 'admin', '2023-11-11 16:36:43');

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户私海/公海日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_sea_log
-- ----------------------------
INSERT INTO `customer_customer_sea_log` VALUES (22, 20, 1, 1, 1, '2023-11-28 15:18:13');
INSERT INTO `customer_customer_sea_log` VALUES (23, 20, 2, 1, 1, '2023-11-28 15:18:27');
INSERT INTO `customer_customer_sea_log` VALUES (24, 18, 2, 1, 1, '2023-11-28 15:19:11');
INSERT INTO `customer_customer_sea_log` VALUES (25, 17, 2, 1, 1, '2023-11-28 15:19:21');
INSERT INTO `customer_customer_sea_log` VALUES (26, 18, 1, 1, 1, '2023-11-28 15:19:31');
INSERT INTO `customer_customer_sea_log` VALUES (27, 17, 1, 1, 1, '2023-11-28 15:21:37');
INSERT INTO `customer_customer_sea_log` VALUES (28, 974, 1, 1, 1, '2024-02-07 00:00:00');
INSERT INTO `customer_customer_sea_log` VALUES (29, 24, 1, 1, 3, '2024-02-07 00:00:00');

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
) ENGINE = InnoDB AUTO_INCREMENT = 200030 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户客群关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_segment
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 200013 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户关联客群日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_segment_log
-- ----------------------------

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
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户来源关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_source
-- ----------------------------
INSERT INTO `customer_customer_source` VALUES (17, 1, 1);
INSERT INTO `customer_customer_source` VALUES (18, 1, 2);
INSERT INTO `customer_customer_source` VALUES (19, 11, 1);
INSERT INTO `customer_customer_source` VALUES (20, 11, 2);
INSERT INTO `customer_customer_source` VALUES (21, 19, 1);
INSERT INTO `customer_customer_source` VALUES (22, 19, 2);
INSERT INTO `customer_customer_source` VALUES (23, 20, 2);
INSERT INTO `customer_customer_source` VALUES (27, 974, 2);
INSERT INTO `customer_customer_source` VALUES (28, 24, 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_customer_tag
-- ----------------------------
INSERT INTO `customer_customer_tag` VALUES (1, 19, 1);
INSERT INTO `customer_customer_tag` VALUES (2, 19, 2);
INSERT INTO `customer_customer_tag` VALUES (3, 20, 1);
INSERT INTO `customer_customer_tag` VALUES (4, 20, 7);
INSERT INTO `customer_customer_tag` VALUES (6, 18, 1);
INSERT INTO `customer_customer_tag` VALUES (7, 18, 6);
INSERT INTO `customer_customer_tag` VALUES (8, 21, 1);
INSERT INTO `customer_customer_tag` VALUES (9, 21, 2);
INSERT INTO `customer_customer_tag` VALUES (10, 67, 5);
INSERT INTO `customer_customer_tag` VALUES (11, 68, 5);
INSERT INTO `customer_customer_tag` VALUES (12, 69, 5);
INSERT INTO `customer_customer_tag` VALUES (13, 70, 5);
INSERT INTO `customer_customer_tag` VALUES (14, 71, 5);
INSERT INTO `customer_customer_tag` VALUES (15, 72, 5);
INSERT INTO `customer_customer_tag` VALUES (16, 73, 5);
INSERT INTO `customer_customer_tag` VALUES (17, 74, 5);
INSERT INTO `customer_customer_tag` VALUES (18, 75, 5);
INSERT INTO `customer_customer_tag` VALUES (19, 76, 5);
INSERT INTO `customer_customer_tag` VALUES (20, 77, 5);
INSERT INTO `customer_customer_tag` VALUES (21, 78, 5);
INSERT INTO `customer_customer_tag` VALUES (22, 79, 5);
INSERT INTO `customer_customer_tag` VALUES (23, 80, 5);
INSERT INTO `customer_customer_tag` VALUES (24, 81, 5);
INSERT INTO `customer_customer_tag` VALUES (25, 82, 5);
INSERT INTO `customer_customer_tag` VALUES (26, 83, 5);
INSERT INTO `customer_customer_tag` VALUES (27, 84, 5);
INSERT INTO `customer_customer_tag` VALUES (28, 85, 5);
INSERT INTO `customer_customer_tag` VALUES (29, 86, 5);
INSERT INTO `customer_customer_tag` VALUES (30, 87, 5);
INSERT INTO `customer_customer_tag` VALUES (31, 88, 5);
INSERT INTO `customer_customer_tag` VALUES (32, 89, 5);
INSERT INTO `customer_customer_tag` VALUES (33, 90, 5);
INSERT INTO `customer_customer_tag` VALUES (34, 91, 5);
INSERT INTO `customer_customer_tag` VALUES (35, 92, 5);
INSERT INTO `customer_customer_tag` VALUES (36, 93, 5);
INSERT INTO `customer_customer_tag` VALUES (37, 94, 5);

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进快捷文本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_quick_text
-- ----------------------------
INSERT INTO `customer_follow_up_quick_text` VALUES (1, '兴趣', '看书;旅游;爬山', '0', 1, 'admin', '2023-11-21 09:30:24', 1, 'admin', '2023-11-21 09:30:24');
INSERT INTO `customer_follow_up_quick_text` VALUES (2, '岗位', '前端;后端;测试;运维;产品', '0', 1, 'admin', '2023-11-21 09:31:08', 1, 'admin', '2023-11-21 09:31:08');
INSERT INTO `customer_follow_up_quick_text` VALUES (3, '测试', 'aaa', '0', 1, 'admin', '2024-01-31 18:04:21', 1, 'admin', '2024-01-31 18:04:21');

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
INSERT INTO `customer_follow_up_rules` VALUES (2, '发送邮件 (客户+商机)', 2, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (3, '接收邮件 (客户+商机)', 3, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (4, '营销邮件 (客户+商机)', 4, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (5, '编辑客户', 5, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (6, '新建跟进 (客户+商机)', 6, 1, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (7, '新建/编辑 商机', 7, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (8, '新建/编辑 报价单', 8, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (9, '新建/编辑 销售订单', 9, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (10, '上传客户文档', 10, 1, 0, '0', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (11, '发送聊天消息 (TM+WhatsAPP+FB Messenger)', 11, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (12, '接收聊天消息 (TM+WhatsAPP+FB Messenger)', 12, 1, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (13, '发送聊天消息 (TM+WhatsAPP+FB Messenger)', 13, 2, 1, '2', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `customer_follow_up_rules` VALUES (14, '发送邮件 (客户+商机)', 2, 2, 1, '0', NULL, NULL, NULL, NULL, NULL, NULL);
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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '写跟进模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_follow_up_templates
-- ----------------------------
INSERT INTO `customer_follow_up_templates` VALUES (1, '模板01', '今天天气真好。', '0', 1, 'admin', '2023-11-21 09:29:44', 1, 'admin', '2023-11-21 09:29:44');
INSERT INTO `customer_follow_up_templates` VALUES (2, '模板02', '我们去旅游吧', '0', 1, 'admin', '2023-11-21 09:30:06', 1, 'admin', '2023-11-21 09:30:06');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户上限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_limits
-- ----------------------------
INSERT INTO `customer_limits` VALUES (1, 1, 2, 3, '0', 1, 'admin', '2023-11-21 09:29:07', 1, 'admin', '2023-11-28 15:23:37');

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_packet
-- ----------------------------
INSERT INTO `customer_packet` VALUES (1, -1, '分组测试', 2, NULL, '0', 1, 'admin', '2023-11-01 17:50:38', 1, 'admin', '2023-11-01 17:50:38');
INSERT INTO `customer_packet` VALUES (2, -1, '测试分组2', 2, NULL, '0', 1, 'admin', '2023-11-01 17:54:24', 1, 'admin', '2023-11-01 17:54:24');
INSERT INTO `customer_packet` VALUES (3, -1, '分组测试3', 2, '1,2', '0', 1, 'admin', '2023-11-01 17:56:59', 1, 'admin', '2023-11-01 17:56:59');
INSERT INTO `customer_packet` VALUES (4, -1, '分组名称04', 1, '', '0', 1, 'admin', '2023-11-20 10:14:17', 1, 'admin', '2023-11-20 10:14:17');
INSERT INTO `customer_packet` VALUES (5, -1, '分组名称05', 2, '1', '0', 1, 'admin', '2023-11-20 10:17:32', 1, 'admin', '2023-11-20 10:17:32');
INSERT INTO `customer_packet` VALUES (6, -1, '分组测试5', 2, '6,1,3,5,2,4', '0', 1, 'admin', '2023-12-08 11:35:03', 1, 'admin', '2023-12-08 11:35:03');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '领取上限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_claim_limit
-- ----------------------------
INSERT INTO `customer_publicleads_claim_limit` VALUES (1, 1, 2, 1, '2', 1, 'admin', '2023-11-21 09:33:13', 1, 'admin', '2023-11-28 15:23:16');
INSERT INTO `customer_publicleads_claim_limit` VALUES (2, 1, 10, 1, '0', 1, 'admin', '2023-11-28 15:23:25', 1, 'admin', '2023-11-28 15:23:25');

-- ----------------------------
-- Table structure for customer_publicleads_groups
-- ----------------------------
DROP TABLE IF EXISTS `customer_publicleads_groups`;
CREATE TABLE `customer_publicleads_groups`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
  `default_group_flag` tinyint(1) NOT NULL COMMENT '默认分组 0.否 1.是 ',
  `group_member` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组成员{\"user\":[1,2,3],\"dept\":[1,2,3]}',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公海分组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_groups
-- ----------------------------
INSERT INTO `customer_publicleads_groups` VALUES (1, '公共公海分组', 0, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', '0', 1, 'admin', '2023-12-21 10:19:12', 1, 'admin', '2023-12-21 10:19:12');
INSERT INTO `customer_publicleads_groups` VALUES (2, '公海分组1', 0, '{\"dept\":{\"allFlag\":false,\"deptIds\":[]},\"user\":{\"allFlag\":false,\"userIds\":[1]}}', '0', 1, 'admin', '2023-12-21 10:19:33', 1, 'admin', '2023-12-21 10:19:33');
INSERT INTO `customer_publicleads_groups` VALUES (3, '公海分组2', 0, '{\"dept\":{\"allFlag\":false,\"deptIds\":[]},\"user\":{\"allFlag\":false,\"userIds\":[6]}}', '0', 1, 'admin', '2023-12-21 10:19:41', 1, 'admin', '2023-12-21 10:19:41');

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
-- Records of customer_publicleads_groups_user
-- ----------------------------
INSERT INTO `customer_publicleads_groups_user` VALUES (9, 7, 1);
INSERT INTO `customer_publicleads_groups_user` VALUES (10, 8, 1);
INSERT INTO `customer_publicleads_groups_user` VALUES (11, 8, 104);
INSERT INTO `customer_publicleads_groups_user` VALUES (12, 8, 2);
INSERT INTO `customer_publicleads_groups_user` VALUES (13, 8, 106);
INSERT INTO `customer_publicleads_groups_user` VALUES (14, 8, 107);
INSERT INTO `customer_publicleads_groups_user` VALUES (15, 8, 108);
INSERT INTO `customer_publicleads_groups_user` VALUES (16, 8, 109);
INSERT INTO `customer_publicleads_groups_user` VALUES (17, 9, 1);
INSERT INTO `customer_publicleads_groups_user` VALUES (18, 9, 104);
INSERT INTO `customer_publicleads_groups_user` VALUES (19, 9, 2);
INSERT INTO `customer_publicleads_groups_user` VALUES (20, 9, 106);
INSERT INTO `customer_publicleads_groups_user` VALUES (21, 9, 107);
INSERT INTO `customer_publicleads_groups_user` VALUES (22, 9, 108);
INSERT INTO `customer_publicleads_groups_user` VALUES (23, 9, 109);
INSERT INTO `customer_publicleads_groups_user` VALUES (31, 10, 2);
INSERT INTO `customer_publicleads_groups_user` VALUES (32, 11, 1);
INSERT INTO `customer_publicleads_groups_user` VALUES (33, 12, 6);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海原因表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_reason
-- ----------------------------
INSERT INTO `customer_publicleads_reason` VALUES (1, '客户没有购买意向', '0', NULL, NULL, NULL, NULL, NULL, NULL);

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
-- Records of customer_publicleads_rules
-- ----------------------------
INSERT INTO `customer_publicleads_rules` VALUES (4, '公海规则测试01', 1, 1, 1, '2023-11-26 12:00:00', '0', 1, 'admin', '2023-11-27 08:00:33', 1, 'admin', '2023-11-27 08:16:51');

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
-- Records of customer_publicleads_rules_segment
-- ----------------------------
INSERT INTO `customer_publicleads_rules_segment` VALUES (1, 1, 2);
INSERT INTO `customer_publicleads_rules_segment` VALUES (2, 1, 1);
INSERT INTO `customer_publicleads_rules_segment` VALUES (5, 2, 4);
INSERT INTO `customer_publicleads_rules_segment` VALUES (7, 3, 4);
INSERT INTO `customer_publicleads_rules_segment` VALUES (13, 4, 79);
INSERT INTO `customer_publicleads_rules_segment` VALUES (14, 4, 8);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '移入公海白名单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_publicleads_white_list
-- ----------------------------
INSERT INTO `customer_publicleads_white_list` VALUES (1, 1, '0', 1, 'admin', '2023-11-27 08:23:33', 1, 'admin', '2023-11-27 08:23:33');

-- ----------------------------
-- Table structure for customer_segment
-- ----------------------------
DROP TABLE IF EXISTS `customer_segment`;
CREATE TABLE `customer_segment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(20) NOT NULL COMMENT '父ID 第一级为-1',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '客群名称',
  `usage_scope` tinyint(1) NULL DEFAULT NULL COMMENT '使用范围 1.公司共享 2.个人使用',
  `visibility_scope` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可见范围',
  `condition_rule_type` tinyint(1) NOT NULL COMMENT '条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件',
  `condition_rule_content` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '条件规则内容',
  `subgroup_flag` tinyint(1) NULL DEFAULT NULL COMMENT '添加二级客群 0.未选 1.选中',
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
) ENGINE = InnoDB AUTO_INCREMENT = 739 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客群表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_segment
-- ----------------------------
INSERT INTO `customer_segment` VALUES (3, -1, '客群名称测试02', 1, NULL, 1, '[{\"columnId\":15,\"andOr\":\"and\",\"conditionType\":1,\"dateType\":null,\"value\":\"hello\"}]', 0, 2, NULL, '2', 1, 'admin', '2023-11-06 17:43:15', 1, 'admin', '2023-11-06 17:56:53');
INSERT INTO `customer_segment` VALUES (4, -1, '客群测试01', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"columnName\":\"short_name\",\"conditionType\":3,\"andOr\":\"and\"}]', 0, NULL, NULL, '2', 1, 'admin', '2023-11-20 09:35:12', 1, 'admin', '2023-11-27 07:50:10');
INSERT INTO `customer_segment` VALUES (5, -1, '客群测试02', 2, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 2, '[{\"columnName\":\"company_name\",\"conditionType\":4,\"andOr\":\"or\"}]', 0, NULL, NULL, '2', 1, 'admin', '2023-11-21 08:24:45', 1, 'admin', '2023-11-27 07:50:12');
INSERT INTO `customer_segment` VALUES (7, -1, '国家地区', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"country_region\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'country_region', '0', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (8, 7, '中国(China)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (9, 7, '阿富汗(Afghanistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (10, 7, '印度(India)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (11, 7, '蒙古(Mongolia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (12, 7, '日本(Japan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (13, 7, '叙利亚(Syria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (14, 7, '沙特阿拉伯(Saudi Arabia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (15, 7, '亚美尼亚(Armenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (16, 7, '巴林(Bahrain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (17, 7, '巴基斯坦(Pakistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (18, 7, '朝鲜(North Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (19, 7, '新加坡(Singapore)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (20, 7, '伊拉克(Iraq)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (21, 7, '以色列(Israel)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (22, 7, '阿塞拜疆(Azerbaijan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (23, 7, '缅甸(Myanmar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (24, 7, '孟加拉国(Bangladesh)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (25, 7, '韩国(South Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (26, 7, '黎巴嫩(Lebanon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (27, 7, '约旦(Jordan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (28, 7, '巴勒斯坦(Palestine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (29, 7, '格鲁吉亚(Georgia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (30, 7, '科威特(Kuwait)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (31, 7, '吉尔吉斯斯坦(Kyrgyzstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (32, 7, '马尔代夫(Maldives)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (33, 7, '马来西亚(Malaysia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (34, 7, '也门(Yemen)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"YE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (35, 7, '文莱(Brunei)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (36, 7, '老挝(Laos)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (37, 7, '柬埔寨(Cambodia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (38, 7, '卡塔尔(Qatar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"QA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (39, 7, '哈萨克斯坦(Kazakhstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (40, 7, '尼泊尔(Nepal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (41, 7, '泰国(Thailand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (42, 7, '阿联酋(United Arab Emirates)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (43, 7, '不丹(Bhutan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (44, 7, '阿曼(Oman)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"OM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (45, 7, '土库曼斯坦(Turkmenistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (46, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (47, 7, '斯里兰卡(Sri Lanka)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (48, 7, '印度尼西亚(Indonesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ID\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (49, 7, '伊朗(Iran)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (50, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (51, 7, '塔吉克斯坦(Tajikistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (52, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (53, 7, '东帝汶(Timor-Leste)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (54, 7, '乌兹别克斯坦(Uzbekistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (55, 7, '越南(Vietnam)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (56, 7, '美国(United States)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"US\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (57, 7, '加拿大(Canada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (58, 7, '墨西哥(Mexico)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MX\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (59, 7, '危地马拉(Guatemala)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (60, 7, '伯利兹(Belize)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (61, 7, '萨尔瓦多(El Salvador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (62, 7, '洪都拉斯(Honduras)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (63, 7, '巴拿马(Panama)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (64, 7, '巴哈马(Bahamas)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (65, 7, '古巴(Cuba)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (66, 7, '牙买加(Jamaica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (67, 7, '海地(Haiti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (68, 7, '多米尼加(Dominican Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (69, 7, '哥斯达黎加(Costa Rica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (70, 7, '圣基茨和尼维斯(Saint Kitts and Nevis)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (71, 7, '安提瓜和巴布达(Antigua and Barbuda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (72, 7, '多米尼克(Dominica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (73, 7, '圣卢西亚(Saint Lucia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (74, 7, '圣文森特和格林纳丁斯(Saint Vincent and the Grenadines)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (75, 7, '巴巴多斯(Barbados)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (76, 7, '格林纳达(Grenada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (77, 7, '特立尼达和多巴哥(Trinidad and Tobago)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (78, 7, '尼加拉瓜(Nicaragua)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (79, 7, '阿根廷(Argentina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (80, 7, '玻利维亚(Bolivia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (81, 7, '巴西(Brazil)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (82, 7, '智利(Chile)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (83, 7, '哥伦比亚(Colombia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (84, 7, '厄瓜多尔(Ecuador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (85, 7, '圭亚那(Guyana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (86, 7, '秘鲁(Peru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (87, 7, '巴拉圭(Paraguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (88, 7, '苏里南(Suriname)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (89, 7, '乌拉圭(Uruguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (90, 7, '委内瑞拉(Venezuela)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (91, 7, '阿尔巴尼亚(Albania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (92, 7, '安道尔(Andorra)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (93, 7, '奥地利(Austria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (94, 7, '白俄罗斯(Belarus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (95, 7, '比利时(Belgium)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (96, 7, '波斯尼亚和黑塞哥维那(Bosnia and Herzegovina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (97, 7, '保加利亚(Bulgaria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (98, 7, '克罗地亚(Croatia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (99, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (100, 7, '捷克(Czechia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (101, 7, '丹麦(Denmark)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (102, 7, '爱沙尼亚(Estonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (103, 7, '芬兰(Finland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (104, 7, '法国(France)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (105, 7, '德国(Germany)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (106, 7, '希腊(Greece)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (107, 7, '匈牙利(Hungary)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (108, 7, '冰岛(Iceland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (109, 7, '爱尔兰(Ireland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (110, 7, '意大利(Italy)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (111, 7, '拉脱维亚(Latvia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (112, 7, '列支敦士登(Liechtenstein)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (113, 7, '立陶宛(Lithuania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LTA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (114, 7, '卢森堡(Luxembourg)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (115, 7, '马耳他(Malta)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (116, 7, '摩尔多瓦(Moldova)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (117, 7, '摩纳哥(Monaco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (118, 7, '黑山(Montenegro)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ME\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (119, 7, '荷兰(Netherlands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (120, 7, '北马其顿(North Macedonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (121, 7, '挪威(Norway)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (122, 7, '波兰(Poland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (123, 7, '葡萄牙(Portugal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (124, 7, '罗马尼亚(Romania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (125, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (126, 7, '圣马力诺(San Marino)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (127, 7, '塞尔维亚(Serbia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (128, 7, '斯洛伐克(Slovakia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (129, 7, '斯洛文尼亚(Slovenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (130, 7, '西班牙(Spain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ES\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (131, 7, '瑞典(Sweden)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (132, 7, '瑞士(Switzerland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (133, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (134, 7, '乌克兰(Ukraine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (135, 7, '英国(United Kingdom)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (136, 7, '梵蒂冈(Vatican City)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (137, 7, '埃及(Egypt)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (138, 7, '利比亚(Libya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (139, 7, '突尼斯(Tunisia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (140, 7, '阿尔及利亚(Algeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (141, 7, '摩洛哥(Morocco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (142, 7, '埃塞俄比亚(Ethiopia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ET\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (143, 7, '厄立特里亚(Eritrea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ER\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (144, 7, '索马里(Somalia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (145, 7, '吉布提(Djibouti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (146, 7, '肯尼亚(Kenya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (147, 7, '坦桑尼亚(Tanzania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (148, 7, '乌干达(Uganda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (149, 7, '卢旺达(Rwanda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (150, 7, '布隆迪(Burundi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (151, 7, '塞舌尔(Seychelles)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (152, 7, '苏丹(Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (153, 7, '南苏丹(South Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (154, 7, '乍得(Chad)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (155, 7, '中非(Central African Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (156, 7, '喀麦隆(Cameroon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (157, 7, '赤道几内亚(Equatorial Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (158, 7, '加蓬(Gabon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (159, 7, '刚果共和国(Congo Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (160, 7, '刚果民主共和国(Congo Democratic Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (161, 7, '圣多美和普林西比(Sao Tome and Principe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ST\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (162, 7, '毛里塔尼亚(Mauritania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (163, 7, '塞内加尔(Senegal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (164, 7, '冈比亚(Gambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (165, 7, '马里(Mali)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ML\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (166, 7, '布基纳法索(Burkina Faso)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (167, 7, '几内亚(Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (168, 7, '几内亚比绍(Guinea-Bissau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (169, 7, '佛得角(Cape Verde)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (170, 7, '塞拉利昂(Sierra Leone)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (171, 7, '利比里亚(Liberia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (172, 7, '科特迪瓦(Cote d\"Ivoire)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (173, 7, '加纳(Ghana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (174, 7, '多哥(Togo)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (175, 7, '贝宁(Benin)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (176, 7, '尼日尔(Niger)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (177, 7, '尼日利亚(Nigeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (178, 7, '赞比亚(Zambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (179, 7, '安哥拉(Angola)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (180, 7, '津巴布韦(Zimbabwe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (181, 7, '马拉维(Malawi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (182, 7, '莫桑比克(Mozambique)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (183, 7, '博茨瓦纳(Botswana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (184, 7, '纳米比亚(Namibia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (185, 7, '南非(South Africa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (186, 7, '斯威士兰(Eswatini)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (187, 7, '莱索托(Lesotho)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (188, 7, '马达加斯加(Madagascar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (189, 7, '科摩罗(Comoros)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (190, 7, '毛里求斯(Mauritius)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (191, 7, '澳大利亚(Australia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (192, 7, '新西兰(New Zealand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (193, 7, '帕劳(Palau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (194, 7, '密克罗尼西亚联邦(Federated States of Micronesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (195, 7, '马绍尔群岛(Marshall Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (196, 7, '瑙鲁(Nauru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (197, 7, '巴布亚新几内亚(Papua New Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (198, 7, '所罗门群岛(Solomon Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (199, 7, '瓦努阿图(Vanuatu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (200, 7, '图瓦卢(Tuvalu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (201, 7, '斐济(Fiji)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (202, 7, '萨摩亚(Samoa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"WS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (203, 7, '基里巴斯(Kiribati)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (204, 7, '汤加(Tonga)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (205, 7, '库克群岛(Cook Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (206, 7, '纽埃(Niue)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (207, 7, '南极洲(Antarctica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (208, 7, '布韦岛(Bouvet Island)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (209, 7, '法属南部领地(French Southern Territories)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (210, 7, '赫德岛和麦克唐纳群岛(Heard Island and McDonald Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 07:51:46', 1, 'admin', '2023-11-27 07:51:46');
INSERT INTO `customer_segment` VALUES (211, -1, '标签', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"company_name\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'customer_tag', '0', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (212, 211, '公司标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (213, 211, '公司标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (214, 211, '公司标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (215, 211, '个人标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (216, 211, '个人标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"6\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (217, 211, '个人标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"7\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:28:05', 1, 'admin', '2023-11-27 08:28:05');
INSERT INTO `customer_segment` VALUES (218, 7, '中国(China)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (219, 7, '阿富汗(Afghanistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (220, 7, '印度(India)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (221, 7, '蒙古(Mongolia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (222, 7, '日本(Japan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (223, 7, '叙利亚(Syria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (224, 7, '沙特阿拉伯(Saudi Arabia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (225, 7, '亚美尼亚(Armenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (226, 7, '巴林(Bahrain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (227, 7, '巴基斯坦(Pakistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (228, 7, '朝鲜(North Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (229, 7, '新加坡(Singapore)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (230, 7, '伊拉克(Iraq)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (231, 7, '以色列(Israel)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (232, 7, '阿塞拜疆(Azerbaijan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (233, 7, '缅甸(Myanmar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (234, 7, '孟加拉国(Bangladesh)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (235, 7, '韩国(South Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (236, 7, '黎巴嫩(Lebanon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (237, 7, '约旦(Jordan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (238, 7, '巴勒斯坦(Palestine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (239, 7, '格鲁吉亚(Georgia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (240, 7, '科威特(Kuwait)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (241, 7, '吉尔吉斯斯坦(Kyrgyzstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (242, 7, '马尔代夫(Maldives)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (243, 7, '马来西亚(Malaysia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (244, 7, '也门(Yemen)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"YE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (245, 7, '文莱(Brunei)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (246, 7, '老挝(Laos)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (247, 7, '柬埔寨(Cambodia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (248, 7, '卡塔尔(Qatar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"QA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (249, 7, '哈萨克斯坦(Kazakhstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (250, 7, '尼泊尔(Nepal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (251, 7, '泰国(Thailand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (252, 7, '阿联酋(United Arab Emirates)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (253, 7, '不丹(Bhutan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (254, 7, '阿曼(Oman)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"OM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (255, 7, '土库曼斯坦(Turkmenistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (256, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (257, 7, '斯里兰卡(Sri Lanka)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (258, 7, '印度尼西亚(Indonesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ID\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (259, 7, '伊朗(Iran)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (260, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (261, 7, '塔吉克斯坦(Tajikistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (262, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (263, 7, '东帝汶(Timor-Leste)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (264, 7, '乌兹别克斯坦(Uzbekistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (265, 7, '越南(Vietnam)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (266, 7, '美国(United States)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"US\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (267, 7, '加拿大(Canada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (268, 7, '墨西哥(Mexico)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MX\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (269, 7, '危地马拉(Guatemala)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (270, 7, '伯利兹(Belize)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (271, 7, '萨尔瓦多(El Salvador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (272, 7, '洪都拉斯(Honduras)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (273, 7, '巴拿马(Panama)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (274, 7, '巴哈马(Bahamas)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (275, 7, '古巴(Cuba)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (276, 7, '牙买加(Jamaica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (277, 7, '海地(Haiti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (278, 7, '多米尼加(Dominican Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (279, 7, '哥斯达黎加(Costa Rica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (280, 7, '圣基茨和尼维斯(Saint Kitts and Nevis)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (281, 7, '安提瓜和巴布达(Antigua and Barbuda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (282, 7, '多米尼克(Dominica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (283, 7, '圣卢西亚(Saint Lucia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (284, 7, '圣文森特和格林纳丁斯(Saint Vincent and the Grenadines)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (285, 7, '巴巴多斯(Barbados)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (286, 7, '格林纳达(Grenada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (287, 7, '特立尼达和多巴哥(Trinidad and Tobago)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (288, 7, '尼加拉瓜(Nicaragua)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (289, 7, '阿根廷(Argentina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (290, 7, '玻利维亚(Bolivia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (291, 7, '巴西(Brazil)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (292, 7, '智利(Chile)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (293, 7, '哥伦比亚(Colombia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (294, 7, '厄瓜多尔(Ecuador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (295, 7, '圭亚那(Guyana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (296, 7, '秘鲁(Peru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (297, 7, '巴拉圭(Paraguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (298, 7, '苏里南(Suriname)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (299, 7, '乌拉圭(Uruguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (300, 7, '委内瑞拉(Venezuela)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (301, 7, '阿尔巴尼亚(Albania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (302, 7, '安道尔(Andorra)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (303, 7, '奥地利(Austria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (304, 7, '白俄罗斯(Belarus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (305, 7, '比利时(Belgium)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (306, 7, '波斯尼亚和黑塞哥维那(Bosnia and Herzegovina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (307, 7, '保加利亚(Bulgaria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (308, 7, '克罗地亚(Croatia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (309, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (310, 7, '捷克(Czechia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (311, 7, '丹麦(Denmark)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (312, 7, '爱沙尼亚(Estonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (313, 7, '芬兰(Finland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (314, 7, '法国(France)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (315, 7, '德国(Germany)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (316, 7, '希腊(Greece)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (317, 7, '匈牙利(Hungary)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (318, 7, '冰岛(Iceland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (319, 7, '爱尔兰(Ireland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (320, 7, '意大利(Italy)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (321, 7, '拉脱维亚(Latvia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (322, 7, '列支敦士登(Liechtenstein)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (323, 7, '立陶宛(Lithuania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LTA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (324, 7, '卢森堡(Luxembourg)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (325, 7, '马耳他(Malta)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (326, 7, '摩尔多瓦(Moldova)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (327, 7, '摩纳哥(Monaco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (328, 7, '黑山(Montenegro)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ME\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (329, 7, '荷兰(Netherlands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (330, 7, '北马其顿(North Macedonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (331, 7, '挪威(Norway)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (332, 7, '波兰(Poland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (333, 7, '葡萄牙(Portugal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (334, 7, '罗马尼亚(Romania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (335, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (336, 7, '圣马力诺(San Marino)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (337, 7, '塞尔维亚(Serbia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (338, 7, '斯洛伐克(Slovakia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (339, 7, '斯洛文尼亚(Slovenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (340, 7, '西班牙(Spain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ES\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (341, 7, '瑞典(Sweden)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (342, 7, '瑞士(Switzerland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (343, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (344, 7, '乌克兰(Ukraine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (345, 7, '英国(United Kingdom)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (346, 7, '梵蒂冈(Vatican City)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (347, 7, '埃及(Egypt)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (348, 7, '利比亚(Libya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (349, 7, '突尼斯(Tunisia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (350, 7, '阿尔及利亚(Algeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (351, 7, '摩洛哥(Morocco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (352, 7, '埃塞俄比亚(Ethiopia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ET\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (353, 7, '厄立特里亚(Eritrea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ER\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (354, 7, '索马里(Somalia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (355, 7, '吉布提(Djibouti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (356, 7, '肯尼亚(Kenya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (357, 7, '坦桑尼亚(Tanzania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (358, 7, '乌干达(Uganda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (359, 7, '卢旺达(Rwanda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (360, 7, '布隆迪(Burundi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (361, 7, '塞舌尔(Seychelles)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (362, 7, '苏丹(Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (363, 7, '南苏丹(South Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (364, 7, '乍得(Chad)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (365, 7, '中非(Central African Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (366, 7, '喀麦隆(Cameroon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (367, 7, '赤道几内亚(Equatorial Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (368, 7, '加蓬(Gabon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (369, 7, '刚果共和国(Congo Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (370, 7, '刚果民主共和国(Congo Democratic Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (371, 7, '圣多美和普林西比(Sao Tome and Principe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ST\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (372, 7, '毛里塔尼亚(Mauritania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (373, 7, '塞内加尔(Senegal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (374, 7, '冈比亚(Gambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (375, 7, '马里(Mali)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ML\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (376, 7, '布基纳法索(Burkina Faso)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (377, 7, '几内亚(Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (378, 7, '几内亚比绍(Guinea-Bissau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (379, 7, '佛得角(Cape Verde)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (380, 7, '塞拉利昂(Sierra Leone)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (381, 7, '利比里亚(Liberia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (382, 7, '科特迪瓦(Cote d\"Ivoire)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (383, 7, '加纳(Ghana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (384, 7, '多哥(Togo)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (385, 7, '贝宁(Benin)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (386, 7, '尼日尔(Niger)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (387, 7, '尼日利亚(Nigeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (388, 7, '赞比亚(Zambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (389, 7, '安哥拉(Angola)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (390, 7, '津巴布韦(Zimbabwe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (391, 7, '马拉维(Malawi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (392, 7, '莫桑比克(Mozambique)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (393, 7, '博茨瓦纳(Botswana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (394, 7, '纳米比亚(Namibia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (395, 7, '南非(South Africa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (396, 7, '斯威士兰(Eswatini)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (397, 7, '莱索托(Lesotho)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (398, 7, '马达加斯加(Madagascar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (399, 7, '科摩罗(Comoros)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (400, 7, '毛里求斯(Mauritius)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (401, 7, '澳大利亚(Australia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (402, 7, '新西兰(New Zealand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (403, 7, '帕劳(Palau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (404, 7, '密克罗尼西亚联邦(Federated States of Micronesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (405, 7, '马绍尔群岛(Marshall Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (406, 7, '瑙鲁(Nauru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (407, 7, '巴布亚新几内亚(Papua New Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (408, 7, '所罗门群岛(Solomon Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (409, 7, '瓦努阿图(Vanuatu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (410, 7, '图瓦卢(Tuvalu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (411, 7, '斐济(Fiji)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (412, 7, '萨摩亚(Samoa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"WS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (413, 7, '基里巴斯(Kiribati)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (414, 7, '汤加(Tonga)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (415, 7, '库克群岛(Cook Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (416, 7, '纽埃(Niue)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (417, 7, '南极洲(Antarctica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (418, 7, '布韦岛(Bouvet Island)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (419, 7, '法属南部领地(French Southern Territories)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (420, 7, '赫德岛和麦克唐纳群岛(Heard Island and McDonald Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:30:39', 1, 'admin', '2023-11-27 08:30:39');
INSERT INTO `customer_segment` VALUES (421, 7, '中国(China)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (422, 7, '阿富汗(Afghanistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (423, 7, '印度(India)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (424, 7, '蒙古(Mongolia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (425, 7, '日本(Japan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (426, 7, '叙利亚(Syria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (427, 7, '沙特阿拉伯(Saudi Arabia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (428, 7, '亚美尼亚(Armenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (429, 7, '巴林(Bahrain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (430, 7, '巴基斯坦(Pakistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (431, 7, '朝鲜(North Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (432, 7, '新加坡(Singapore)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (433, 7, '伊拉克(Iraq)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (434, 7, '以色列(Israel)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (435, 7, '阿塞拜疆(Azerbaijan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (436, 7, '缅甸(Myanmar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (437, 7, '孟加拉国(Bangladesh)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (438, 7, '韩国(South Korea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (439, 7, '黎巴嫩(Lebanon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (440, 7, '约旦(Jordan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (441, 7, '巴勒斯坦(Palestine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (442, 7, '格鲁吉亚(Georgia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (443, 7, '科威特(Kuwait)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (444, 7, '吉尔吉斯斯坦(Kyrgyzstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (445, 7, '马尔代夫(Maldives)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (446, 7, '马来西亚(Malaysia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (447, 7, '也门(Yemen)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"YE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (448, 7, '文莱(Brunei)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (449, 7, '老挝(Laos)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (450, 7, '柬埔寨(Cambodia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (451, 7, '卡塔尔(Qatar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"QA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (452, 7, '哈萨克斯坦(Kazakhstan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (453, 7, '尼泊尔(Nepal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NP\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (454, 7, '泰国(Thailand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (455, 7, '阿联酋(United Arab Emirates)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (456, 7, '不丹(Bhutan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (457, 7, '阿曼(Oman)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"OM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (458, 7, '土库曼斯坦(Turkmenistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (459, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (460, 7, '斯里兰卡(Sri Lanka)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (461, 7, '印度尼西亚(Indonesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ID\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (462, 7, '伊朗(Iran)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (463, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (464, 7, '塔吉克斯坦(Tajikistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (465, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (466, 7, '东帝汶(Timor-Leste)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (467, 7, '乌兹别克斯坦(Uzbekistan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (468, 7, '越南(Vietnam)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (469, 7, '美国(United States)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"US\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (470, 7, '加拿大(Canada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (471, 7, '墨西哥(Mexico)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MX\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (472, 7, '危地马拉(Guatemala)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (473, 7, '伯利兹(Belize)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (474, 7, '萨尔瓦多(El Salvador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (475, 7, '洪都拉斯(Honduras)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (476, 7, '巴拿马(Panama)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (477, 7, '巴哈马(Bahamas)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (478, 7, '古巴(Cuba)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (479, 7, '牙买加(Jamaica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"JM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (480, 7, '海地(Haiti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (481, 7, '多米尼加(Dominican Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (482, 7, '哥斯达黎加(Costa Rica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (483, 7, '圣基茨和尼维斯(Saint Kitts and Nevis)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (484, 7, '安提瓜和巴布达(Antigua and Barbuda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (485, 7, '多米尼克(Dominica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (486, 7, '圣卢西亚(Saint Lucia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (487, 7, '圣文森特和格林纳丁斯(Saint Vincent and the Grenadines)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (488, 7, '巴巴多斯(Barbados)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (489, 7, '格林纳达(Grenada)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (490, 7, '特立尼达和多巴哥(Trinidad and Tobago)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (491, 7, '尼加拉瓜(Nicaragua)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (492, 7, '阿根廷(Argentina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (493, 7, '玻利维亚(Bolivia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (494, 7, '巴西(Brazil)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (495, 7, '智利(Chile)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (496, 7, '哥伦比亚(Colombia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (497, 7, '厄瓜多尔(Ecuador)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (498, 7, '圭亚那(Guyana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (499, 7, '秘鲁(Peru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (500, 7, '巴拉圭(Paraguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (501, 7, '苏里南(Suriname)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (502, 7, '乌拉圭(Uruguay)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (503, 7, '委内瑞拉(Venezuela)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (504, 7, '阿尔巴尼亚(Albania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (505, 7, '安道尔(Andorra)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (506, 7, '奥地利(Austria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (507, 7, '白俄罗斯(Belarus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (508, 7, '比利时(Belgium)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (509, 7, '波斯尼亚和黑塞哥维那(Bosnia and Herzegovina)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (510, 7, '保加利亚(Bulgaria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (511, 7, '克罗地亚(Croatia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (512, 7, '塞浦路斯(Cyprus)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (513, 7, '捷克(Czechia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (514, 7, '丹麦(Denmark)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (515, 7, '爱沙尼亚(Estonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (516, 7, '芬兰(Finland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (517, 7, '法国(France)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (518, 7, '德国(Germany)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (519, 7, '希腊(Greece)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (520, 7, '匈牙利(Hungary)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (521, 7, '冰岛(Iceland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (522, 7, '爱尔兰(Ireland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (523, 7, '意大利(Italy)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"IT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (524, 7, '拉脱维亚(Latvia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (525, 7, '列支敦士登(Liechtenstein)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (526, 7, '立陶宛(Lithuania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LTA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (527, 7, '卢森堡(Luxembourg)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (528, 7, '马耳他(Malta)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (529, 7, '摩尔多瓦(Moldova)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (530, 7, '摩纳哥(Monaco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (531, 7, '黑山(Montenegro)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ME\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (532, 7, '荷兰(Netherlands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (533, 7, '北马其顿(North Macedonia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (534, 7, '挪威(Norway)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (535, 7, '波兰(Poland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (536, 7, '葡萄牙(Portugal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PT\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (537, 7, '罗马尼亚(Romania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (538, 7, '俄罗斯(Russia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (539, 7, '圣马力诺(San Marino)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (540, 7, '塞尔维亚(Serbia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (541, 7, '斯洛伐克(Slovakia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (542, 7, '斯洛文尼亚(Slovenia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (543, 7, '西班牙(Spain)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ES\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (544, 7, '瑞典(Sweden)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (545, 7, '瑞士(Switzerland)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (546, 7, '土耳其(Turkey)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (547, 7, '乌克兰(Ukraine)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (548, 7, '英国(United Kingdom)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (549, 7, '梵蒂冈(Vatican City)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (550, 7, '埃及(Egypt)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"EG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (551, 7, '利比亚(Libya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LY\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (552, 7, '突尼斯(Tunisia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (553, 7, '阿尔及利亚(Algeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (554, 7, '摩洛哥(Morocco)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (555, 7, '埃塞俄比亚(Ethiopia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ET\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (556, 7, '厄立特里亚(Eritrea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ER\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (557, 7, '索马里(Somalia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (558, 7, '吉布提(Djibouti)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"DJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (559, 7, '肯尼亚(Kenya)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (560, 7, '坦桑尼亚(Tanzania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (561, 7, '乌干达(Uganda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"UG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (562, 7, '卢旺达(Rwanda)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"RW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (563, 7, '布隆迪(Burundi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (564, 7, '塞舌尔(Seychelles)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SC\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (565, 7, '苏丹(Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (566, 7, '南苏丹(South Sudan)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (567, 7, '乍得(Chad)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (568, 7, '中非(Central African Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (569, 7, '喀麦隆(Cameroon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (570, 7, '赤道几内亚(Equatorial Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (571, 7, '加蓬(Gabon)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (572, 7, '刚果共和国(Congo Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (573, 7, '刚果民主共和国(Congo Democratic Republic)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CD\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (574, 7, '圣多美和普林西比(Sao Tome and Principe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ST\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (575, 7, '毛里塔尼亚(Mauritania)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (576, 7, '塞内加尔(Senegal)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (577, 7, '冈比亚(Gambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (578, 7, '马里(Mali)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ML\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (579, 7, '布基纳法索(Burkina Faso)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BF\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (580, 7, '几内亚(Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GN\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (581, 7, '几内亚比绍(Guinea-Bissau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (582, 7, '佛得角(Cape Verde)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (583, 7, '塞拉利昂(Sierra Leone)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SL\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (584, 7, '利比里亚(Liberia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (585, 7, '科特迪瓦(Cote d\"Ivoire)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (586, 7, '加纳(Ghana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"GH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (587, 7, '多哥(Togo)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (588, 7, '贝宁(Benin)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (589, 7, '尼日尔(Niger)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NE\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (590, 7, '尼日利亚(Nigeria)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (591, 7, '赞比亚(Zambia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (592, 7, '安哥拉(Angola)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (593, 7, '津巴布韦(Zimbabwe)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (594, 7, '马拉维(Malawi)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (595, 7, '莫桑比克(Mozambique)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (596, 7, '博茨瓦纳(Botswana)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (597, 7, '纳米比亚(Namibia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (598, 7, '南非(South Africa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"ZA\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (599, 7, '斯威士兰(Eswatini)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (600, 7, '莱索托(Lesotho)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"LS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (601, 7, '马达加斯加(Madagascar)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (602, 7, '科摩罗(Comoros)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (603, 7, '毛里求斯(Mauritius)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (604, 7, '澳大利亚(Australia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (605, 7, '新西兰(New Zealand)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NZ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (606, 7, '帕劳(Palau)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PW\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (607, 7, '密克罗尼西亚联邦(Federated States of Micronesia)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FM\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (608, 7, '马绍尔群岛(Marshall Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"MH\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (609, 7, '瑙鲁(Nauru)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NR\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (610, 7, '巴布亚新几内亚(Papua New Guinea)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"PG\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (611, 7, '所罗门群岛(Solomon Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"SB\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (612, 7, '瓦努阿图(Vanuatu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"VU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (613, 7, '图瓦卢(Tuvalu)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TV\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (614, 7, '斐济(Fiji)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FJ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (615, 7, '萨摩亚(Samoa)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"WS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (616, 7, '基里巴斯(Kiribati)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"KI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (617, 7, '汤加(Tonga)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"TO\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (618, 7, '库克群岛(Cook Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"CK\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (619, 7, '纽埃(Niue)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"NU\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (620, 7, '南极洲(Antarctica)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"AQ\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (621, 7, '布韦岛(Bouvet Island)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"BI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (622, 7, '法属南部领地(French Southern Territories)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"FS\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (623, 7, '赫德岛和麦克唐纳群岛(Heard Island and McDonald Islands)', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"HI\",\"columnName\":\"country_region\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:36:45', 1, 'admin', '2023-11-27 08:36:45');
INSERT INTO `customer_segment` VALUES (624, 211, '公司标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (625, 211, '公司标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (626, 211, '公司标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (627, 211, '个人标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (628, 211, '个人标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"6\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (629, 211, '个人标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"7\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 08:42:23', 1, 'admin', '2023-11-27 08:42:23');
INSERT INTO `customer_segment` VALUES (630, 211, '公司标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (631, 211, '公司标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (632, 211, '公司标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (633, 211, '个人标签01', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (634, 211, '个人标签02', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"6\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (635, 211, '个人标签03', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"7\",\"columnName\":\"customer_tag\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 08:43:10', 1, 'admin', '2023-11-27 08:43:10');
INSERT INTO `customer_segment` VALUES (636, -1, '时区', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"company_name\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'timezone', '0', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (637, 636, '零时区：伦敦', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"0\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (638, 636, '东一区：罗马，巴黎', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (639, 636, '东二区：雅典，以色列', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (640, 636, '东三区：莫斯科，科威特', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (641, 636, '东四区：喀布尔', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"4\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (642, 636, '东五区：伊斯兰堡，卡拉奇', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (643, 636, '东六区：阿拉木图，科伦坡', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"6\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (644, 636, '东七区：曼谷，雅加达', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"7\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (645, 636, '东八区：北京，香港，台湾', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"8\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (646, 636, '东九区：东京', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"9\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (647, 636, '东十区：悉尼', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"10\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (648, 636, '东十一区：霍尼亚拉，马加丹', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"11\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (649, 636, '东西十二区: 奥克兰', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"12\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (650, 636, '西十一区：帕果帕果，阿洛菲', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"13\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (651, 636, '西十区：夏威夷', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"14\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (652, 636, '西九区：阿拉斯加', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"15\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (653, 636, '西八区：洛杉矶，旧金山', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"16\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (654, 636, '西七区：盐湖城、丹佛、凤凰城', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"17\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (655, 636, '西六区：芝加哥，休斯顿，亚特兰大', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"18\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (656, 636, '西五区：纽约，华盛顿，波士顿', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"19\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (657, 636, '西四区：加拿大，加拉加斯', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"20\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (658, 636, '西三区：巴西利亚', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"21\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (659, 636, '西二区：协调世界时', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"22\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (660, 636, '西一区：佛得角群岛', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"23\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-24 11:52:48', 1, 'admin', '2023-11-24 11:52:48');
INSERT INTO `customer_segment` VALUES (661, 636, '零时区：伦敦', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"0\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (662, 636, '东一区：罗马，巴黎', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (663, 636, '东二区：雅典，以色列', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (664, 636, '东三区：莫斯科，科威特', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (665, 636, '东四区：喀布尔', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"4\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (666, 636, '东五区：伊斯兰堡，卡拉奇', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (667, 636, '东六区：阿拉木图，科伦坡', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"6\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (668, 636, '东七区：曼谷，雅加达', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"7\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (669, 636, '东八区：北京，香港，台湾', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"8\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (670, 636, '东九区：东京', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"9\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (671, 636, '东十区：悉尼', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"10\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (672, 636, '东十一区：霍尼亚拉，马加丹', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"11\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (673, 636, '东西十二区: 奥克兰', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"12\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (674, 636, '西十一区：帕果帕果，阿洛菲', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"13\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (675, 636, '西十区：夏威夷', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"14\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (676, 636, '西九区：阿拉斯加', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"15\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (677, 636, '西八区：洛杉矶，旧金山', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"16\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (678, 636, '西七区：盐湖城、丹佛、凤凰城', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"17\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (679, 636, '西六区：芝加哥，休斯顿，亚特兰大', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"18\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (680, 636, '西五区：纽约，华盛顿，波士顿', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"19\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (681, 636, '西四区：加拿大，加拉加斯', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"20\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (682, 636, '西三区：巴西利亚', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"21\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (683, 636, '西二区：协调世界时', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"22\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (684, 636, '西一区：佛得角群岛', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"23\",\"columnName\":\"timezone\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-24 12:00:32', 1, 'admin', '2023-11-24 12:00:32');
INSERT INTO `customer_segment` VALUES (685, -1, '跟进人', 1, '{\"dept\":{\"allFlag\":false,\"deptIds\":[103]},\"user\":{\"allFlag\":false,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"company_name\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'follow_up_personnel', '0', 1, 'admin', '2023-11-27 13:46:32', 1, 'admin', '2023-11-27 14:24:13');
INSERT INTO `customer_segment` VALUES (686, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:46:32', 1, 'admin', '2023-11-27 13:46:32');
INSERT INTO `customer_segment` VALUES (687, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:46:32', 1, 'admin', '2023-11-27 13:46:32');
INSERT INTO `customer_segment` VALUES (688, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:46:32', 1, 'admin', '2023-11-27 13:46:32');
INSERT INTO `customer_segment` VALUES (689, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:53:48', 1, 'admin', '2023-11-27 13:53:48');
INSERT INTO `customer_segment` VALUES (690, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:53:48', 1, 'admin', '2023-11-27 13:53:48');
INSERT INTO `customer_segment` VALUES (691, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:53:48', 1, 'admin', '2023-11-27 13:53:48');
INSERT INTO `customer_segment` VALUES (692, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:54:14', 1, 'admin', '2023-11-27 13:54:14');
INSERT INTO `customer_segment` VALUES (693, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:54:14', 1, 'admin', '2023-11-27 13:54:14');
INSERT INTO `customer_segment` VALUES (694, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:54:14', 1, 'admin', '2023-11-27 13:54:14');
INSERT INTO `customer_segment` VALUES (695, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:58:35', 1, 'admin', '2023-11-27 13:58:35');
INSERT INTO `customer_segment` VALUES (696, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:58:35', 1, 'admin', '2023-11-27 13:58:35');
INSERT INTO `customer_segment` VALUES (697, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:58:35', 1, 'admin', '2023-11-27 13:58:35');
INSERT INTO `customer_segment` VALUES (698, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:59:17', 1, 'admin', '2023-11-27 13:59:17');
INSERT INTO `customer_segment` VALUES (699, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:59:17', 1, 'admin', '2023-11-27 13:59:17');
INSERT INTO `customer_segment` VALUES (700, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 13:59:17', 1, 'admin', '2023-11-27 13:59:17');
INSERT INTO `customer_segment` VALUES (701, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:09:06', 1, 'admin', '2023-11-27 14:09:06');
INSERT INTO `customer_segment` VALUES (702, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:09:06', 1, 'admin', '2023-11-27 14:09:06');
INSERT INTO `customer_segment` VALUES (703, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:09:06', 1, 'admin', '2023-11-27 14:09:06');
INSERT INTO `customer_segment` VALUES (704, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:17:55', 1, 'admin', '2023-11-27 14:17:55');
INSERT INTO `customer_segment` VALUES (705, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:17:55', 1, 'admin', '2023-11-27 14:17:55');
INSERT INTO `customer_segment` VALUES (706, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:17:55', 1, 'admin', '2023-11-27 14:17:55');
INSERT INTO `customer_segment` VALUES (707, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:20:13', 1, 'admin', '2023-11-27 14:20:13');
INSERT INTO `customer_segment` VALUES (708, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:20:13', 1, 'admin', '2023-11-27 14:20:13');
INSERT INTO `customer_segment` VALUES (709, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:20:13', 1, 'admin', '2023-11-27 14:20:13');
INSERT INTO `customer_segment` VALUES (710, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:23:58', 1, 'admin', '2023-11-27 14:23:58');
INSERT INTO `customer_segment` VALUES (711, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:23:58', 1, 'admin', '2023-11-27 14:23:58');
INSERT INTO `customer_segment` VALUES (712, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:23:58', 1, 'admin', '2023-11-27 14:23:58');
INSERT INTO `customer_segment` VALUES (713, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 14:24:13', 1, 'admin', '2023-11-27 14:24:13');
INSERT INTO `customer_segment` VALUES (714, 685, '若依', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 14:24:13', 1, 'admin', '2023-11-27 14:24:13');
INSERT INTO `customer_segment` VALUES (715, 685, '张三', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"follow_up_personnel\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 14:24:13', 1, 'admin', '2023-11-27 14:24:13');
INSERT INTO `customer_segment` VALUES (716, -1, '客群条件多选测试001', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"company_name\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 2, NULL, '0', 1, 'admin', '2023-11-27 14:29:36', 1, 'admin', '2023-11-27 16:01:32');
INSERT INTO `customer_segment` VALUES (717, 716, '多选标签', NULL, NULL, 1, '[{\"columnName\":\"customer_tag\",\"conditionType\":5,\"dateType\":null,\"timeRange\":null,\"value\":[1,5],\"andOr\":\"and\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:29:36', 1, 'admin', '2023-11-27 14:29:36');
INSERT INTO `customer_segment` VALUES (718, 716, '多选标签', NULL, NULL, 1, '[{\"columnName\":\"customer_tag\",\"conditionType\":5,\"dateType\":null,\"timeRange\":null,\"value\":[1,5],\"andOr\":\"and\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 14:30:47', 1, 'admin', '2023-11-27 14:30:47');
INSERT INTO `customer_segment` VALUES (719, 716, '多选标签', NULL, NULL, 1, '[{\"columnName\":\"customer_tag\",\"conditionType\":5,\"dateType\":null,\"timeRange\":null,\"value\":[1,5],\"andOr\":\"and\"}]', NULL, NULL, NULL, '2', 1, 'admin', '2023-11-27 15:57:45', 1, 'admin', '2023-11-27 15:57:45');
INSERT INTO `customer_segment` VALUES (720, 716, '多选标签', NULL, NULL, 1, '[{\"columnName\":\"customer_tag\",\"conditionType\":5,\"dateType\":null,\"timeRange\":null,\"value\":[1,5],\"andOr\":\"and\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 16:01:32', 1, 'admin', '2023-11-27 16:01:32');
INSERT INTO `customer_segment` VALUES (721, -1, '客户星级测试', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":4,\"value\":null,\"columnName\":\"customer_rating\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'customer_rating', '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (722, 721, '0星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"0\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (723, 721, '1星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (724, 721, '2星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (725, 721, '3星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (726, 721, '4星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"4\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (727, 721, '5星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:01:42', 1, 'admin', '2023-11-27 17:01:42');
INSERT INTO `customer_segment` VALUES (728, -1, '客户星级测试02', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":5,\"value\":[0,1,2],\"columnName\":\"customer_rating\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 1, 1, 'customer_rating', '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (729, 728, '0星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"0\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (730, 728, '1星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"1\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (731, 728, '2星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"2\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (732, 728, '3星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"3\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (733, 728, '4星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"4\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (734, 728, '5星', NULL, NULL, 1, '[{\"andOr\":\"and\",\"conditionType\":1,\"value\":\"5\",\"columnName\":\"customer_rating\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-11-27 17:04:50', 1, 'admin', '2023-11-27 17:04:50');
INSERT INTO `customer_segment` VALUES (735, -1, '跟进人时间', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":1,\"value\":null,\"columnName\":\"create_time\",\"dateType\":2,\"timeRange\":12,\"andOr\":\"and\"}]', 0, NULL, NULL, '0', 1, 'admin', '2023-11-29 14:48:39', 1, 'admin', '2023-11-29 14:48:39');
INSERT INTO `customer_segment` VALUES (736, -1, '一级客群测试01', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 2, '[{\"conditionType\":3,\"value\":null,\"columnName\":\"nick_name\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"or\"}]', 1, 2, NULL, '0', 1, 'admin', '2023-12-01 14:47:37', 1, 'admin', '2023-12-01 14:47:37');
INSERT INTO `customer_segment` VALUES (737, 736, '二级客群名称003', NULL, NULL, 1, '[{\"columnName\":\"customer_no\",\"conditionType\":3,\"dateType\":null,\"timeRange\":null,\"value\":null,\"andOr\":\"and\"}]', NULL, NULL, NULL, '0', 1, 'admin', '2023-12-01 14:47:37', 1, 'admin', '2023-12-01 14:47:37');
INSERT INTO `customer_segment` VALUES (738, -1, '联系人为空', 1, '{\"dept\":{\"allFlag\":true,\"deptIds\":[]},\"user\":{\"allFlag\":true,\"userIds\":[]}}', 1, '[{\"conditionType\":3,\"value\":null,\"columnName\":\"phone\",\"dateType\":null,\"timeRange\":null,\"andOr\":\"and\"}]', 0, NULL, NULL, '0', 1, 'admin', '2023-12-11 09:25:05', 1, 'admin', '2023-12-11 09:25:05');

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
  `claim_limit_flag` tinyint(1) NULL DEFAULT NULL COMMENT '领取限制 0.限制 1.不限制',
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
INSERT INTO `customer_settings` VALUES (1, NULL, NULL, NULL, 0, 0, 2, NULL, 1, 2, '0', 1, 'admin', '2023-11-01 17:59:59', 1, 'admin', '2023-11-28 15:11:39');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户来源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_source
-- ----------------------------
INSERT INTO `customer_source` VALUES (1, '门店', '0', 1, 'admin', '2023-11-07 08:31:11', 1, 'admin', '2023-11-07 08:31:11');
INSERT INTO `customer_source` VALUES (2, '线上', '0', 1, 'admin', '2023-11-07 08:31:18', 1, 'admin', '2023-11-07 08:31:18');

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户阶段表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_stage
-- ----------------------------
INSERT INTO `customer_stage` VALUES (1, '阶段名称2', '15', '2', 1, 'admin', '2023-11-07 08:34:39', 1, 'admin', '2023-11-21 09:27:09');
INSERT INTO `customer_stage` VALUES (2, '阶段名称03', '#acacac', '0', 1, 'admin', '2023-11-21 09:27:02', 1, 'admin', '2023-11-21 09:27:02');
INSERT INTO `customer_stage` VALUES (3, '阶段名称02', '#eb9955', '0', 1, 'admin', '2023-11-21 09:27:17', 1, 'admin', '2023-11-21 09:27:17');
INSERT INTO `customer_stage` VALUES (4, '阶段名称01', '#5d89e9', '0', 1, 'admin', '2023-11-21 09:27:29', 1, 'admin', '2023-11-21 09:27:29');

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '客户标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer_tag
-- ----------------------------
INSERT INTO `customer_tag` VALUES (1, '公司标签01', '#80c463', 1, '0', 1, 'admin', '2023-11-21 09:24:59', 1, 'admin', '2023-11-21 09:24:59');
INSERT INTO `customer_tag` VALUES (2, '公司标签02', '#5d89e9', 1, '0', 1, 'admin', '2023-11-21 09:25:06', 1, 'admin', '2023-11-21 09:25:06');
INSERT INTO `customer_tag` VALUES (3, '公司标签03', '#acacac', 1, '0', 1, 'admin', '2023-11-21 09:25:12', 1, 'admin', '2023-11-21 09:25:12');
INSERT INTO `customer_tag` VALUES (4, '个人标签01', '#4b679d', 1, '2', 1, 'admin', '2023-11-21 09:25:31', 1, 'admin', '2023-11-21 09:25:45');
INSERT INTO `customer_tag` VALUES (5, '个人标签01', '#d87538', 2, '0', 1, 'admin', '2023-11-21 09:25:51', 1, 'admin', '2023-11-21 09:25:51');
INSERT INTO `customer_tag` VALUES (6, '个人标签02', '#e43e3e', 2, '0', 1, 'admin', '2023-11-21 09:25:57', 1, 'admin', '2023-11-21 09:25:57');
INSERT INTO `customer_tag` VALUES (7, '个人标签03', '#ee7b7b', 2, '0', 1, 'admin', '2023-11-21 09:26:03', 1, 'admin', '2023-11-21 09:26:03');

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'mailbox_blacklist', '黑名单表', NULL, NULL, 'Blacklist', 'crud', 'com.ruoyi.email', 'email', 'blacklist', '黑名单', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:34', NULL);
INSERT INTO `gen_table` VALUES (2, 'mailbox_folder', '文件夹表', NULL, NULL, 'Folder', 'crud', 'com.ruoyi.email', 'email', 'folder', '文件夹', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49', NULL);
INSERT INTO `gen_table` VALUES (3, 'mailbox_general', '邮箱常规表', NULL, NULL, 'General', 'crud', 'com.ruoyi.email', 'email', 'general', '邮箱常规', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:01', NULL);
INSERT INTO `gen_table` VALUES (4, 'mailbox_general_single', '单个邮箱设置表', NULL, NULL, 'GeneralSingle', 'crud', 'com.ruoyi.email', 'email', 'single', '单个邮箱设置', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12', NULL);
INSERT INTO `gen_table` VALUES (5, 'mailbox_host', '邮箱服务器查询表', NULL, NULL, 'Host', 'crud', 'com.ruoyi.email', 'email', 'host', '邮箱服务器查询', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24', NULL);
INSERT INTO `gen_table` VALUES (6, 'mailbox_import_email', '导入邮件表', NULL, NULL, 'ImportEmail', 'crud', 'com.ruoyi.email', 'email', 'email', '导入邮件', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:36', NULL);
INSERT INTO `gen_table` VALUES (7, 'mailbox_label', '标签表', NULL, NULL, 'Label', 'crud', 'com.ruoyi.email', 'email', 'label', '标签', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49', NULL);
INSERT INTO `gen_table` VALUES (8, 'mailbox_other_config', '其他配置表', NULL, NULL, 'OtherConfig', 'crud', 'com.ruoyi.email', 'email', 'config', '其他配置', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:02', NULL);
INSERT INTO `gen_table` VALUES (9, 'mailbox_quick_text', '快速文本表', NULL, NULL, 'QuickText', 'crud', 'com.ruoyi.email', 'email', 'text', '快速文本', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14', NULL);
INSERT INTO `gen_table` VALUES (10, 'mailbox_signature', '个性签名表', NULL, NULL, 'Signature', 'crud', 'com.ruoyi.email', 'email', 'signature', '个性签名', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:30', NULL);
INSERT INTO `gen_table` VALUES (11, 'mailbox_task', '邮箱任务表', NULL, NULL, 'Task', 'crud', 'com.ruoyi.email', 'email', 'task', '邮箱任务', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43', NULL);
INSERT INTO `gen_table` VALUES (12, 'mailbox_task_email_attachment', '邮件附件表', NULL, NULL, 'TaskEmailAttachment', 'crud', 'com.ruoyi.email', 'email', 'attachment', '邮件附件', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56', NULL);
INSERT INTO `gen_table` VALUES (13, 'mailbox_task_email_content', '邮件内容表', NULL, NULL, 'TaskEmailContent', 'crud', 'com.ruoyi.email', 'email', 'content', '邮件内容', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20', NULL);
INSERT INTO `gen_table` VALUES (14, 'mailbox_task_email_dealings', '往来邮件表', NULL, NULL, 'TaskEmailDealings', 'crud', 'com.ruoyi.email', 'email', 'dealings', '往来邮件', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33', NULL);
INSERT INTO `gen_table` VALUES (15, 'mailbox_task_email_pull', '拉取邮件表', NULL, NULL, 'TaskEmailPull', 'crud', 'com.ruoyi.email', 'email', 'pull', '拉取邮件', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51', NULL);
INSERT INTO `gen_table` VALUES (16, 'mailbox_task_email_pull_label', '邮件标签表', NULL, NULL, 'TaskEmailPullLabel', 'crud', 'com.ruoyi.email', 'email', 'label', '邮件标签', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04', NULL);
INSERT INTO `gen_table` VALUES (17, 'mailbox_task_email_send', '发送邮件表', NULL, NULL, 'TaskEmailSend', 'crud', 'com.ruoyi.email', 'email', 'send', '发送邮件', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15', NULL);
INSERT INTO `gen_table` VALUES (18, 'mailbox_task_email_send_trace', '邮件追踪表', NULL, NULL, 'TaskEmailSendTrace', 'crud', 'com.ruoyi.email', 'email', 'trace', '邮件追踪', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27', NULL);
INSERT INTO `gen_table` VALUES (19, 'mailbox_template', '模板表', NULL, NULL, 'Template', 'crud', 'com.ruoyi.email', 'email', 'template', '模板', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38', NULL);
INSERT INTO `gen_table` VALUES (20, 'mailbox_template_type', '模板类型表', NULL, NULL, 'TemplateType', 'crud', 'com.ruoyi.email', 'email', 'type', '模板类型', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50', NULL);
INSERT INTO `gen_table` VALUES (21, 'mailbox_transceiver_rule', '收发件规则表', NULL, NULL, 'TransceiverRule', 'crud', 'com.ruoyi.email', 'email', 'rule', '收发件规则', 'tangJM', '0', '/', '{}', 'admin', '2023-07-31 13:53:51', '', '2023-07-31 15:00:01', NULL);
INSERT INTO `gen_table` VALUES (23, 'mailbox_task_email', '邮件表', NULL, NULL, 'TaskEmail', 'crud', 'com.ruoyi.email', 'email', 'email', '邮件', 'tangJM.', '0', '/', '{}', 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57', NULL);
INSERT INTO `gen_table` VALUES (24, 'mailbox_task_email_label', '邮件标签表', NULL, NULL, 'TaskEmailLabel', 'crud', 'com.ruoyi.email', 'email', 'label', '邮件标签', 'tangJM', '0', '/', '{}', 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24', NULL);
INSERT INTO `gen_table` VALUES (26, 'customer_black_list_records', '建档黑名单表', NULL, NULL, 'BlackListRecords', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '建档黑名单', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22', NULL);
INSERT INTO `gen_table` VALUES (27, 'customer_duplication_settings', '客户查重设置表', NULL, NULL, 'DuplicationSettings', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户查重设置', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55', NULL);
INSERT INTO `gen_table` VALUES (28, 'customer_follow_up_quick_text', '写跟进快捷文本表', NULL, NULL, 'FollowUpQuickText', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '写跟进快捷文本', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16', NULL);
INSERT INTO `gen_table` VALUES (29, 'customer_follow_up_templates', '写跟进模板表', NULL, NULL, 'FollowUpTemplates', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '写跟进模板', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32', NULL);
INSERT INTO `gen_table` VALUES (30, 'customer_limits', '客户上限表', NULL, NULL, 'Limits', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户上限', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49', NULL);
INSERT INTO `gen_table` VALUES (31, 'customer_packet', '客户分组表', '', '', 'Packet', 'tree', 'com.ruoyi.customer', 'customer', 'customer', '客户分组', 'tangJM.', '0', '/', '{\"treeCode\":\"id\",\"treeName\":\"name\",\"treeParentCode\":\"paren_id\"}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29', NULL);
INSERT INTO `gen_table` VALUES (32, 'customer_packet_designate_member', '客户分组指定成员表', NULL, NULL, 'PacketDesignateMember', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户分组指定成员', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27', NULL);
INSERT INTO `gen_table` VALUES (33, 'customer_publicleads_claim_limit', '领取上限表', NULL, NULL, 'PublicleadsClaimLimit', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '领取上限', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10', NULL);
INSERT INTO `gen_table` VALUES (34, 'customer_publicleads_groups', '公海分组表', NULL, NULL, 'PublicleadsGroups', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '公海分组', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05', NULL);
INSERT INTO `gen_table` VALUES (35, 'customer_publicleads_reason', '移入公海原因表', NULL, NULL, 'PublicleadsReason', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '移入公海原因', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21', NULL);
INSERT INTO `gen_table` VALUES (36, 'customer_publicleads_rules', '移入公海规则表', NULL, NULL, 'PublicleadsRules', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '移入公海规则', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36', NULL);
INSERT INTO `gen_table` VALUES (37, 'customer_publicleads_white_list', '移入公海白名单表', NULL, NULL, 'PublicleadsWhiteList', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '移入公海白名单', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52', NULL);
INSERT INTO `gen_table` VALUES (38, 'customer_settings', '客户设置表', NULL, NULL, 'Settings', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户设置', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09', NULL);
INSERT INTO `gen_table` VALUES (39, 'customer_stage', '客户阶段表', NULL, NULL, 'Stage', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户阶段', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25', NULL);
INSERT INTO `gen_table` VALUES (40, 'customer_follow_up_rules', '客户跟进规则表', NULL, NULL, 'FollowUpRules', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户跟进规则', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41', NULL);
INSERT INTO `gen_table` VALUES (41, 'customer_details', '客户详情表', NULL, NULL, 'Details', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户详情', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18', NULL);
INSERT INTO `gen_table` VALUES (42, 'customer_details_contact', '客户联系人表', NULL, NULL, 'DetailsContact', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户联系人', 'tangJM.', '0', '/', '{}', 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54', NULL);
INSERT INTO `gen_table` VALUES (43, 'customer_metadata_column', '客户元数据列', NULL, NULL, 'MetadataColumn', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户元数据列', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:08:04', NULL);
INSERT INTO `gen_table` VALUES (44, 'customer_segment', '客群表', NULL, NULL, 'Segment', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客群', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38', NULL);
INSERT INTO `gen_table` VALUES (45, 'customer_segment_relation', '客户客群关联表', NULL, NULL, 'CustomerSegmentRelation', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户客群关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:07', NULL);
INSERT INTO `gen_table` VALUES (46, 'customer_source_relation', '客户来源关联表', NULL, NULL, 'CustomerSourceRelation', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户来源关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:58', NULL);
INSERT INTO `gen_table` VALUES (47, 'customer_tag_relation', '客户标签关联表', NULL, NULL, 'CustomerTagRelation', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户标签关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:47', NULL);
INSERT INTO `gen_table` VALUES (48, 'customer_customer', '客户详情表', NULL, NULL, 'Customer', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户详情', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26', NULL);
INSERT INTO `gen_table` VALUES (49, 'customer_customer_contact', '客户联系人表', NULL, NULL, 'CustomerContact', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户联系人', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42', NULL);
INSERT INTO `gen_table` VALUES (50, 'customer_customer_segment', '客户客群关联表', NULL, NULL, 'CustomerSegment', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户客群关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:03', NULL);
INSERT INTO `gen_table` VALUES (51, 'customer_customer_source', '客户来源关联表', NULL, NULL, 'CustomerSource', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户来源关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:17', NULL);
INSERT INTO `gen_table` VALUES (52, 'customer_customer_tag', '客户标签关联表', NULL, NULL, 'CustomerTag', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户标签关联', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:34', NULL);
INSERT INTO `gen_table` VALUES (53, 'customer_customer_follow_up_records_comment', '写跟进评论', NULL, NULL, 'CustomerFollowUpRecordsComment', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '写跟进评论', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11', NULL);
INSERT INTO `gen_table` VALUES (54, 'customer_customer_schedule', '客户日程表', NULL, NULL, 'CustomerSchedule', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户日程', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09', NULL);
INSERT INTO `gen_table` VALUES (55, 'customer_customer_schedule_participants', '客户日程参与人', NULL, NULL, 'CustomerScheduleParticipants', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户日程参与人', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04', NULL);
INSERT INTO `gen_table` VALUES (56, 'customer_customer_sea_log', '客户私海/公海日志', NULL, NULL, 'CustomerSeaLog', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户私海/公海日志', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-15 11:15:26', '', '2023-11-15 11:15:56', NULL);
INSERT INTO `gen_table` VALUES (57, 'customer_customer_segment_log', '客户关联客群日志', NULL, NULL, 'CustomerSegmentLog', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户关联客群日志', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:39:53', NULL);
INSERT INTO `gen_table` VALUES (58, 'customer_publicleads_rules_segment', '公海规则客群', NULL, NULL, 'PublicleadsRulesSegment', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '公海规则客群', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:40:12', NULL);
INSERT INTO `gen_table` VALUES (59, 'customer_customer_document', '客户文档', NULL, NULL, 'CustomerDocument', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户文档', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:19:01', NULL);
INSERT INTO `gen_table` VALUES (60, 'customer_tag', '客户标签', NULL, NULL, 'Tag', 'crud', 'com.ruoyi.customer', 'customer', 'customer', '客户标签', 'tangJM.', '0', '/', '{}', 'admin', '2023-11-17 11:24:10', '', '2023-11-17 11:24:31', NULL);
INSERT INTO `gen_table` VALUES (61, 'mailbox_task_single_setting', '单个邮箱设置表', NULL, NULL, 'TaskSingleSetting', 'crud', 'com.ruoyi.email', 'email', 'setting', '单个邮箱设置', 'tangJM.', '0', '/', '{}', 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:10:33', NULL);

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` bigint(20) NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 795 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, 1, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (2, 1, 'type', '类型: 1.邮箱地址黑名单 2.域名黑名单', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (3, 1, 'content', '邮箱地址或域名', 'varchar(128)', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (4, 1, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (5, 1, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (6, 1, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (7, 1, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (8, 1, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-12-22 15:06:16');
INSERT INTO `gen_table_column` VALUES (9, 2, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (10, 2, 'parent_folder_id', '父文件夹的ID,如果是顶级文件夹,此项为NULL', 'bigint(20)', 'Long', 'parentFolderId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (11, 2, 'name', '文件夹名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (12, 2, 'type', '类型: 1.系统文件夹 2.自定义文件夹', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (13, 2, 'order', '顺序', 'int(11)', 'Long', 'order', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (14, 2, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (15, 2, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (16, 2, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (17, 2, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (18, 2, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:55:49');
INSERT INTO `gen_table_column` VALUES (19, 3, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (20, 3, 'default_task_id', '默认邮箱', 'bigint(20)', 'Long', 'defaultTaskId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (21, 3, 'max_per_page', '每页显示邮件数量', 'int(5)', 'Integer', 'maxPerPage', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (22, 3, 'email_reading_mode_flag', '邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示', 'tinyint(1)', 'Integer', 'emailReadingModeFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (23, 3, 'move_delete_report', '移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表', 'tinyint(1)', 'Integer', 'moveDeleteReport', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (24, 3, 'language', '语言: 1.简体中文 2.繁体中文 3.英文', 'tinyint(1)', 'Integer', 'language', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (25, 3, 'default_font', '默认字体', 'varchar(32)', 'String', 'defaultFont', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (26, 3, 'font_size', '文字大小', 'varchar(16)', 'String', 'fontSize', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (28, 3, 'group_box_view', '群发箱视图​: 1.任务视图 2.邮件视图', 'tinyint(1)', 'Integer', 'groupBoxView', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:54');
INSERT INTO `gen_table_column` VALUES (29, 3, 'remind', '提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒', 'tinyint(1)', 'Integer', 'remind', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (30, 3, 'signature_id', '签名ID', 'bigint(20)', 'Long', 'signatureId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (31, 3, 'write_letter', '写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效)', 'varchar(10)', 'String', 'writeLetter', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (32, 3, 'auto_response_flag', '自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次)', 'tinyint(1)', 'Integer', 'autoResponseFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (33, 3, 'start_time', '开始时间', 'datetime', 'Date', 'startTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 17, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (34, 3, 'last_day', '最后一天', 'datetime', 'Date', 'lastDay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 19, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (35, 3, 're_content', '回复内容', 'text', 'String', 'reContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 20, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (36, 3, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 21, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (37, 3, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (38, 3, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (39, 3, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 26, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (40, 3, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 27, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (41, 4, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (42, 4, 'general_id', '配置常规ID', 'bigint(20)', 'Long', 'generalId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (43, 4, 'letter_nickname', '写信昵称', 'varchar(64)', 'String', 'letterNickname', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (44, 4, 'default_signature_id', '默认签名', 'bigint(20)', 'Long', 'defaultSignatureId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (45, 4, 'reply_signature_id', '回复/转发签名', 'bigint(20)', 'Long', 'replySignatureId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (46, 4, 'default_cc_flag', '默认抄送: 0.关闭 1.启用', 'tinyint(1)', 'Integer', 'defaultCcFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (47, 4, 'default_cc', '默认抄送,请使用\";\"分隔多个邮箱', 'varchar(1024)', 'String', 'defaultCc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (48, 4, 'default_bcc_flag', '默认密送: 0.关闭 1.启用', 'tinyint(1)', 'Integer', 'defaultBccFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (49, 4, 'default_bcc', '默认密送,请使用\";\"分隔多个邮箱', 'varchar(1024)', 'String', 'defaultBcc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (50, 4, 'trace_flag', '是否追踪邮件: 0.否 1.是', 'tinyint(1)', 'Integer', 'traceFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (51, 4, 'return_receipt_flag', '是否回执: 0.否 1.是', 'tinyint(1)', 'Integer', 'returnReceiptFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (52, 4, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (53, 4, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (54, 4, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (55, 4, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (56, 4, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:12');
INSERT INTO `gen_table_column` VALUES (57, 5, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (58, 5, 'domain', '域名', 'varchar(128)', 'String', 'domain', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (59, 5, 'pop_host', 'pop3服务器地址', 'varchar(128)', 'String', 'popHost', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (60, 5, 'imap_host', 'imap服务器地址', 'varchar(128)', 'String', 'imapHost', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (61, 5, 'exchange_host', 'exchange服务器地址,exchange有自动识别的方法,此字段作为备用字段', 'varchar(128)', 'String', 'exchangeHost', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (62, 5, 'smtp_host', 'smtp服务器地址', 'varchar(128)', 'String', 'smtpHost', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (63, 5, 'pop_port', 'pop端口号', 'int(11)', 'Long', 'popPort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (64, 5, 'imap_port', 'imap端口号', 'int(11)', 'Long', 'imapPort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (65, 5, 'exchange_port', '冗余字段', 'int(11)', 'Long', 'exchangePort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (66, 5, 'smtp_port', 'smtp端口号', 'int(11)', 'Long', 'smtpPort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:24');
INSERT INTO `gen_table_column` VALUES (67, 6, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:10');
INSERT INTO `gen_table_column` VALUES (68, 6, 'file_name', '文件名', 'varchar(128)', 'String', 'fileName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (69, 6, 'task_id', '任务ID', 'bigint(20)', 'Long', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (70, 6, 'task_name', '任务名称', 'varchar(64)', 'String', 'taskName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (71, 6, 'folder_id', '文件夹ID', 'bigint(20)', 'Long', 'folderId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (72, 6, 'folder_name', '文件夹名称', 'varchar(64)', 'String', 'folderName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (73, 6, 'import_status', '导入状态: 1.正在处理 2.导入成功 3.导入失败', 'tinyint(1)', 'Integer', 'importStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (74, 6, 'failure_reasons', '失败原因', 'varchar(255)', 'String', 'failureReasons', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (75, 6, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (76, 6, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (77, 6, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (78, 6, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 20, 'admin', '2023-07-31 13:53:50', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (79, 7, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (80, 7, 'name', '标签名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (81, 7, 'color', '标签颜色', 'varchar(32)', 'String', 'color', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (82, 7, 'type', '类型: 1.系统标签 2.自定义标签', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (83, 7, 'order', '顺序', 'int(11)', 'Long', 'order', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (84, 7, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (85, 7, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (86, 7, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (87, 7, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (88, 7, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:56:49');
INSERT INTO `gen_table_column` VALUES (89, 8, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (90, 8, 'delayed_mail_delivery', '邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送', 'tinyint(1)', 'Integer', 'delayedMailDelivery', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (91, 8, 'sending_interval', '发送间隔', 'varchar(32)', 'String', 'sendingInterval', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (92, 8, 'local_real_time_time', '当地实时时间: 0.停用 1.启用', 'tinyint(1)', 'Integer', 'localRealTimeTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (93, 8, 'email_reminders_flag', '邮件发送提醒: 1.弹窗提醒 2.不提醒', 'tinyint(1)', 'Integer', 'emailRemindersFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (94, 8, 'email_translation_function_flag', '邮件翻译功能: 0.停用 1.启用', 'tinyint(1)', 'Integer', 'emailTranslationFunctionFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (95, 8, 'pending_close', '待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态', 'tinyint(1)', 'Integer', 'pendingClose', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (96, 8, 'automation_pending', '自动化待处理: 0.停用 1.启用', 'tinyint(1)', 'Integer', 'automationPending', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:26');
INSERT INTO `gen_table_column` VALUES (97, 8, 'abnormal_mailbox_detection', '异常邮箱检测: 0.停用 1.启用', 'tinyint(1)', 'Integer', 'abnormalMailboxDetection', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:27');
INSERT INTO `gen_table_column` VALUES (98, 8, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:27');
INSERT INTO `gen_table_column` VALUES (99, 8, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:27');
INSERT INTO `gen_table_column` VALUES (100, 8, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:27');
INSERT INTO `gen_table_column` VALUES (101, 8, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-12-28 14:19:27');
INSERT INTO `gen_table_column` VALUES (102, 9, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (103, 9, 'title', '主题', 'varchar(512)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (104, 9, 'content', '内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (105, 9, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (106, 9, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (107, 9, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (108, 9, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (109, 9, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:14');
INSERT INTO `gen_table_column` VALUES (110, 10, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:26');
INSERT INTO `gen_table_column` VALUES (111, 10, 'title', '主题', 'varchar(512)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:26');
INSERT INTO `gen_table_column` VALUES (112, 10, 'content', '内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:26');
INSERT INTO `gen_table_column` VALUES (113, 10, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:26');
INSERT INTO `gen_table_column` VALUES (114, 10, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:27');
INSERT INTO `gen_table_column` VALUES (115, 10, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:27');
INSERT INTO `gen_table_column` VALUES (116, 10, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:27');
INSERT INTO `gen_table_column` VALUES (117, 10, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-12-27 16:16:27');
INSERT INTO `gen_table_column` VALUES (118, 11, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (119, 11, 'account', '邮箱账号', 'varchar(64)', 'String', 'account', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (120, 11, 'password', '邮箱密码', 'varchar(64)', 'String', 'password', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (121, 11, 'alias', '邮箱别名', 'varchar(64)', 'String', 'alias', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (122, 11, 'conn_status', '连接状态: 1.正常 2.异常', 'tinyint(1)', 'Integer', 'connStatus', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (123, 11, 'conn_exception_reason', '连接异常原因', 'varchar(255)', 'String', 'connExceptionReason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (124, 11, 'receiving_server', '收件服务器', 'varchar(128)', 'String', 'receivingServer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (125, 11, 'receiving_port', '收件端口', 'int(11)', 'Long', 'receivingPort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (126, 11, 'receiving_ssl_flag', '收件SSL: 0.否 1.是', 'tinyint(1)', 'Integer', 'receivingSslFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (127, 11, 'outgoing_server', '发件服务器', 'varchar(128)', 'String', 'outgoingServer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (128, 11, 'outgoing_port', '发件端口', 'int(11)', 'Long', 'outgoingPort', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (129, 11, 'outgoing_ssl_flag', '发件SSL: 0.否 1.是', 'tinyint(1)', 'Integer', 'outgoingSslFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (130, 11, 'custom_proxy_flag', '自定义代理: 0.关闭 1.开启', 'tinyint(1)', 'Integer', 'customProxyFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (131, 11, 'proxy_server_type', '代理服务器类型', 'tinyint(1)', 'Integer', 'proxyServerType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 14, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (132, 11, 'proxy_server', '代理服务器', 'varchar(128)', 'String', 'proxyServer', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (133, 11, 'proxy_port', '代理端口', 'int(11)', 'Long', 'proxyPort', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (134, 11, 'proxy_username', '代理用户名', 'varchar(64)', 'String', 'proxyUsername', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 17, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (135, 11, 'proxy_password', '代理密码', 'varchar(64)', 'String', 'proxyPassword', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (136, 11, 'synchronize_folder_flag', '同步文件夹', 'tinyint(1)', 'Integer', 'synchronizeFolderFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (137, 11, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (138, 11, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 21, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (139, 11, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 22, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (140, 11, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (141, 11, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 24, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:43');
INSERT INTO `gen_table_column` VALUES (142, 12, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (143, 12, 'email_id', '邮件ID', 'bigint(20)', 'Long', 'emailId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (144, 12, 'type', '邮件类型 1.拉取 2.发送', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (145, 12, 'name', '附件名称', 'varchar(128)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (146, 12, 'path', '附件地址路径', 'varchar(512)', 'String', 'path', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (147, 12, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (148, 12, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (149, 12, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (150, 12, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (151, 12, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:57:56');
INSERT INTO `gen_table_column` VALUES (152, 13, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (153, 13, 'email_id', '邮件ID', 'bigint(20)', 'Long', 'emailId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (154, 13, 'type', '邮件类型 1.拉取 2.发送', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (155, 13, 'content', '邮件内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (156, 13, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (157, 13, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (158, 13, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (159, 13, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (160, 13, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:20');
INSERT INTO `gen_table_column` VALUES (161, 14, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (162, 14, 'uid', '唯一标识符', 'varchar(32)', 'String', 'uid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (163, 14, 'fromer', '发件邮箱', 'varchar(128)', 'String', 'fromer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (164, 14, 'receiver', '收件邮箱', 'varchar(128)', 'String', 'receiver', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (165, 14, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (166, 14, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (167, 14, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (168, 14, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (169, 14, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:33');
INSERT INTO `gen_table_column` VALUES (170, 15, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (171, 15, 'task_id', '邮箱任务ID', 'bigint(20)', 'Long', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (172, 15, 'uid', '邮件唯一ID', 'varchar(128)', 'String', 'uid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (173, 15, 'fromer', '发件人', 'varchar(128)', 'String', 'fromer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (174, 15, 'receiver', '收件人JSON', 'varchar(3072)', 'String', 'receiver', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (175, 15, 'bcc', '密送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'bcc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (176, 15, 'cc', '抄送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'cc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (177, 15, 'folder', '所属文件夹,对应邮箱平台文件夹', 'varchar(128)', 'String', 'folder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (178, 15, 'folder_id', '文件夹ID,系统文件夹', 'bigint(20)', 'Long', 'folderId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (179, 15, 'title', '邮件主题', 'varchar(512)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (180, 15, 'eml_path', '原始邮件存储路径', 'varchar(1024)', 'String', 'emlPath', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 11, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (181, 15, 'read_flag', '是否已读: 0.否 1.是', 'tinyint(1)', 'Integer', 'readFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (182, 15, 'pending_flag', '是否待处理: 0.否 1.是', 'tinyint(1)', 'Integer', 'pendingFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (183, 15, 'pending_time', '待处理时间', 'datetime', 'Date', 'pendingTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 14, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (184, 15, 'fixed_flag', '是否固定: 0.否 1.是', 'tinyint(1)', 'Integer', 'fixedFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (185, 15, 'spam_flag', '垃圾邮件: 0.否 1.是', 'tinyint(1)', 'Integer', 'spamFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (186, 15, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (187, 15, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (188, 15, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 19, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (189, 15, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (190, 15, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 21, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:58:51');
INSERT INTO `gen_table_column` VALUES (191, 16, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (192, 16, 'email_pull_id', '收取邮件ID', 'bigint(20)', 'Long', 'emailPullId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (193, 16, 'label_id', '标签ID', 'bigint(20)', 'Long', 'labelId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (194, 16, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (195, 16, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (196, 16, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (197, 16, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (198, 16, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:04');
INSERT INTO `gen_table_column` VALUES (199, 17, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (200, 17, 'task_id', '邮箱任务ID', 'bigint(20)', 'Long', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (201, 17, 'fromer', '发件人', 'varchar(128)', 'String', 'fromer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (202, 17, 'receiver', '收件人JSON', 'varchar(3072)', 'String', 'receiver', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (203, 17, 'bcc', '密送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'bcc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (204, 17, 'cc', '抄送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'cc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (205, 17, 'title', '邮件主题', 'varchar(512)', 'String', 'title', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (206, 17, 'status', '状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败', 'tinyint(1)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (207, 17, 'delayed_tx_flag', '定时发送: 0.否 1.是', 'tinyint(1)', 'Integer', 'delayedTxFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (208, 17, 'recipient_time_zone', '收件人所在时区', 'varchar(20)', 'String', 'recipientTimeZone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (209, 17, 'recipient_local_time', '收件人当地时间', 'datetime', 'Date', 'recipientLocalTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 11, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (210, 17, 'pending_flag', '是否待处理: 0.否 1.是', 'tinyint(1)', 'Integer', 'pendingFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (211, 17, 'pending_time', '待处理时间', 'datetime', 'Date', 'pendingTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 13, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (212, 17, 'fixed_flag', '是否固定: 0.否 1.是', 'tinyint(1)', 'Integer', 'fixedFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (213, 17, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (214, 17, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (215, 17, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (216, 17, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 18, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (217, 17, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 19, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:15');
INSERT INTO `gen_table_column` VALUES (218, 18, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (219, 18, 'email_send_id', '发送邮件ID', 'bigint(20)', 'Long', 'emailSendId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (220, 18, 'beijing_time', '北京时间', 'datetime', 'Date', 'beijingTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 3, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (221, 18, 'local_time', '当地时间', 'datetime', 'Date', 'localTime', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 4, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (222, 18, 'ip', 'IP地址', 'varchar(32)', 'String', 'ip', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (223, 18, 'location', '位置', 'varchar(255)', 'String', 'location', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (224, 18, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (225, 18, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (226, 18, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (227, 18, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (228, 18, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-07-31 13:53:50', '', '2023-07-31 14:59:27');
INSERT INTO `gen_table_column` VALUES (229, 19, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (230, 19, 'name', '名称', 'varchar(512)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'textarea', '', 2, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (231, 19, 'template_type_id', '模板类型', 'bigint(20)', 'Long', 'templateTypeId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (232, 19, 'title', '主题', 'varchar(512)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (233, 19, 'content', '模板内容', 'text', 'String', 'content', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 5, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (234, 19, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (235, 19, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (236, 19, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (237, 19, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (238, 19, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:38');
INSERT INTO `gen_table_column` VALUES (239, 20, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (240, 20, 'name', '名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (241, 20, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (242, 20, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (243, 20, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 5, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (244, 20, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (245, 20, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-07-31 13:53:51', '', '2023-07-31 14:59:50');
INSERT INTO `gen_table_column` VALUES (246, 21, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (247, 21, 'rule_type', '规则类型: 1.收件规则 2.发件规则', 'tinyint(1)', 'Integer', 'ruleType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (248, 21, 'rule_name', '规则名称', 'varchar(64)', 'String', 'ruleName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (249, 21, 'execute_condition', '执行条件: 1.满足以下所有条件 2.满足以下任一条件', 'tinyint(1)', 'Integer', 'executeCondition', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (250, 21, 'execute_condition_content', '执行条件内容', 'text', 'String', 'executeConditionContent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 5, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (251, 21, 'execute_operation', '执行操作: 1.执行以下操作 2.移动到【已删除邮件】', 'tinyint(1)', 'Integer', 'executeOperation', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (253, 21, 'apply_to_history_mail_flag', '应用于历史邮件: 0.否 1.是', 'tinyint(1)', 'Integer', 'applyToHistoryMailFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (254, 21, 'execute_task_id', '执行邮箱ID: 0.表示全部邮箱', 'bigint(20)', 'Long', 'executeTaskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (255, 21, 'other_sending_rules', '其他发件规则: 1.继续执行 2.不再执行', 'tinyint(1)', 'Integer', 'otherSendingRules', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (256, 21, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 27, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (257, 21, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 29, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (258, 21, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 30, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (259, 21, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 32, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (260, 21, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 33, 'admin', '2023-07-31 13:53:51', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (294, 23, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (295, 23, 'task_id', '邮箱任务ID', 'bigint(20)', 'Long', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (296, 23, 'type', '类型 1.收件 2.发件', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (297, 23, 'uid', '邮件唯一ID', 'varchar(128)', 'String', 'uid', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (298, 23, 'fromer', '发件人', 'varchar(128)', 'String', 'fromer', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (299, 23, 'receiver', '收件人JSON', 'varchar(3072)', 'String', 'receiver', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (300, 23, 'bcc', '密送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'bcc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (301, 23, 'cc', '抄送人JSON,格式和收件人一样', 'varchar(3072)', 'String', 'cc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 8, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (302, 23, 'folder', '所属文件夹,对应邮箱平台文件夹', 'varchar(128)', 'String', 'folder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (303, 23, 'folder_id', '文件夹ID,系统文件夹', 'bigint(20)', 'Long', 'folderId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (304, 23, 'title', '邮件主题', 'varchar(512)', 'String', 'title', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 11, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (305, 23, 'send_date', '发送时间', 'datetime', 'Date', 'sendDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (306, 23, 'eml_path', '原始邮件存储路径', 'varchar(1024)', 'String', 'emlPath', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 13, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (307, 23, 'status', '状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败', 'tinyint(1)', 'Integer', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 14, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (308, 23, 'trace_flag', '是否追踪 0.否 1.是', 'tinyint(1)', 'Integer', 'traceFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (309, 23, 'delayed_tx_flag', '定时发送: 0.否 1.是', 'tinyint(1)', 'Integer', 'delayedTxFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (310, 23, 'recipient_time_zone', '收件人所在时区', 'varchar(20)', 'String', 'recipientTimeZone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (311, 23, 'recipient_local_time', '收件人当地时间', 'datetime', 'Date', 'recipientLocalTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 18, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (312, 23, 'read_flag', '是否已读: 0.否 1.是', 'tinyint(1)', 'Integer', 'readFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (313, 23, 'pending_flag', '是否待处理: 0.否 1.是', 'tinyint(1)', 'Integer', 'pendingFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (314, 23, 'pending_time', '待处理时间', 'datetime', 'Date', 'pendingTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 21, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (315, 23, 'fixed_flag', '是否固定: 0.否 1.是', 'tinyint(1)', 'Integer', 'fixedFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (316, 23, 'spam_flag', '垃圾邮件: 0.否 1.是', 'tinyint(1)', 'Integer', 'spamFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (317, 23, 'message_id', '每封邮件都应该有一个唯一的 Message-ID', 'varchar(255)', 'String', 'messageId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (318, 23, 'in_reply_to', '当你回复一封邮件时，你应该设置 In-Reply-To 头为原始邮件的 Message-ID。', 'varchar(255)', 'String', 'inReplyTo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 25, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (319, 23, 'references', '邮件链的列表', 'text', 'String', 'references', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 26, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (320, 23, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 27, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (321, 23, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (322, 23, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 29, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (323, 23, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 30, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (324, 23, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 31, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (325, 23, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 32, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (326, 23, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 33, 'admin', '2023-09-12 16:46:16', '', '2023-09-12 16:47:57');
INSERT INTO `gen_table_column` VALUES (327, 24, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (328, 24, 'email_id', '邮件ID', 'bigint(20)', 'Long', 'emailId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (329, 24, 'label_id', '标签ID', 'bigint(20)', 'Long', 'labelId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (330, 24, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (331, 24, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (332, 24, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (333, 24, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (334, 24, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', '1', '1', '1', NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-10-10 16:58:18', '', '2023-10-10 16:59:24');
INSERT INTO `gen_table_column` VALUES (345, 26, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (346, 26, 'domain', '邮箱/邮箱后缀', 'varchar(64)', 'String', 'domain', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (347, 26, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (348, 26, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (349, 26, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (350, 26, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (351, 26, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (352, 26, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (353, 26, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:02:22');
INSERT INTO `gen_table_column` VALUES (354, 27, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (355, 27, 'name', '字段名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (356, 27, 'display_flag', '显示或隐藏 0.隐藏 1.显示', 'tinyint(1)', 'Integer', 'displayFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (357, 27, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (358, 27, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (359, 27, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (360, 27, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (361, 27, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (362, 27, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (363, 27, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:01:55');
INSERT INTO `gen_table_column` VALUES (364, 28, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (365, 28, 'name', '名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (366, 28, 'label', '标签', 'varchar(1024)', 'String', 'label', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (367, 28, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (368, 28, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (369, 28, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (370, 28, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:16');
INSERT INTO `gen_table_column` VALUES (371, 28, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:17');
INSERT INTO `gen_table_column` VALUES (372, 28, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:17');
INSERT INTO `gen_table_column` VALUES (373, 28, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:17');
INSERT INTO `gen_table_column` VALUES (374, 29, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (375, 29, 'name', '模板名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (376, 29, 'content', '模板内容', 'text', 'String', 'content', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (377, 29, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (378, 29, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (379, 29, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (380, 29, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (381, 29, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (382, 29, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (383, 29, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:32');
INSERT INTO `gen_table_column` VALUES (384, 30, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (385, 30, 'user_id', '用户ID', 'bigint(20)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (386, 30, 'type', '类型 1.不限 2.客户上限', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (387, 30, 'limits', '客户上限值', 'int(11)', 'Long', 'limits', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (388, 30, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (389, 30, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (390, 30, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (391, 30, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (392, 30, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (393, 30, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (394, 30, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:03:49');
INSERT INTO `gen_table_column` VALUES (395, 31, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (396, 31, 'paren_id', '父ID（第一级为-1）', 'bigint(20)', 'Long', 'parenId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (397, 31, 'name', '客户名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (398, 31, 'available_member', '可用成员 1.全部成员 2.特定成员-部门 3.特定成员-业务员', 'tinyint(1)', 'Integer', 'availableMember', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (399, 31, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (400, 31, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (401, 31, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (402, 31, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (403, 31, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (404, 31, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (405, 31, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 17:18:29');
INSERT INTO `gen_table_column` VALUES (406, 32, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (407, 32, 'packet_id', '分组ID', 'bigint(20)', 'Long', 'packetId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (408, 32, 'association_id', '部门或者业务员ID', 'bigint(20)', 'Long', 'associationId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (409, 32, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (410, 32, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (411, 32, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (412, 32, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (413, 32, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (414, 32, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (415, 32, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:04:27');
INSERT INTO `gen_table_column` VALUES (416, 33, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (417, 33, 'user_id', '人员ID', 'bigint(20)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (418, 33, 'claim_limit', '领取上限', 'int(11)', 'Long', 'claimLimit', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (419, 33, 'claim_period', '上限周期 1.每日2.每周3.每月4.每年', 'tinyint(1)', 'Integer', 'claimPeriod', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (420, 33, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (421, 33, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (422, 33, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (423, 33, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (424, 33, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (425, 33, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (426, 33, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:08:10');
INSERT INTO `gen_table_column` VALUES (427, 34, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (428, 34, 'name', '分组名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (429, 34, 'user_ids', '分组成员ID（逗号拼接）', 'varchar(255)', 'String', 'userIds', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (430, 34, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (431, 34, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (432, 34, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (433, 34, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (434, 34, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (435, 34, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (436, 34, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:05');
INSERT INTO `gen_table_column` VALUES (437, 35, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (438, 35, 'reason', '原因', 'varchar(255)', 'String', 'reason', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (439, 35, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (440, 35, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (441, 35, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (442, 35, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (443, 35, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (444, 35, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (445, 35, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:21');
INSERT INTO `gen_table_column` VALUES (446, 36, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (447, 36, 'name', '规则名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (448, 36, 'customer_segment_id', '客群ID', 'bigint(20)', 'Long', 'customerSegmentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (449, 36, 'days', '客户状态-天数', 'int(11)', 'Long', 'days', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (450, 36, 'type', '客户状态-类型1.未联系 2.未成交', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (451, 36, 'status', '启用状态 0.关闭 1.启用', 'tinyint(1)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (452, 36, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (453, 36, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (454, 36, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (455, 36, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (456, 36, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (457, 36, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (458, 36, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:36');
INSERT INTO `gen_table_column` VALUES (459, 37, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (460, 37, 'user_id', '人员ID', 'bigint(20)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (461, 37, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (462, 37, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (463, 37, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (464, 37, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (465, 37, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (466, 37, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (467, 37, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:05:52');
INSERT INTO `gen_table_column` VALUES (468, 38, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (469, 38, 'mandatory_subgroup_flag', '子分组必选 0.未选 1.已选', 'tinyint(1)', 'Integer', 'mandatorySubgroupFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (470, 38, 'advance_flag', '提前标识 0.关闭 1.启动', 'tinyint(1)', 'Integer', 'advanceFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (471, 38, 'advance_days', '提前几天', 'int(11)', 'Long', 'advanceDays', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (472, 38, 'account_disabled_flag', '排除禁用账号 0.未选 1.已选', 'tinyint(1)', 'Integer', 'accountDisabledFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (473, 38, 'claim_limit_flag', '领取限制 1.限制 2.不限制', 'tinyint(1)', 'Integer', 'claimLimitFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (474, 38, 'claim_limit_days', '限制几天内，原跟进人不能领取同一个客户', 'int(11)', 'Long', 'claimLimitDays', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (475, 38, 'opportunity_view_rule', '商机查看规则\r\n1.领取公海客户后，不能查看客户历史商机\r\n2.领取公海客户后，可查看客户历史商机\r\n', 'tinyint(1)', 'Integer', 'opportunityViewRule', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (476, 38, 'show_public_leads_flag', '是否显示公海联系人 0.否 1.是', 'tinyint(1)', 'Integer', 'showPublicLeadsFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (477, 38, 'followup_time', '写跟进时间\r\n1.提交跟进记录时默认提交时间，不允许修改\r\n2.提交跟进记录时默认提交时间，可手动修改为历史时间\r\n', 'tinyint(1)', 'Integer', 'followupTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (478, 38, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (479, 38, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (480, 38, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (481, 38, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (482, 38, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (483, 38, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (484, 38, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:09');
INSERT INTO `gen_table_column` VALUES (485, 39, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (486, 39, 'name', '名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (487, 39, 'color', '颜色', 'varchar(32)', 'String', 'color', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (488, 39, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (489, 39, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (490, 39, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (491, 39, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (492, 39, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (493, 39, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (494, 39, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:56:13', '', '2023-10-25 16:06:25');
INSERT INTO `gen_table_column` VALUES (495, 40, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (496, 40, 'name', '名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (497, 40, 'type', '类型 \r\n1. 发生以下行为会影响客户的“最近联系时间”，进而影响客户被自动移入公海的时间\r\n2. 发生以下行为会影响客户的“最近跟进时间”，进而影响跟进客户类型的任务截止时间', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (498, 40, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (499, 40, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (500, 40, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (501, 40, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (502, 40, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (503, 40, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (504, 40, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-10-25 15:58:15', '', '2023-10-25 16:06:41');
INSERT INTO `gen_table_column` VALUES (505, 41, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (506, 41, 'company_website', '公司网址', 'varchar(255)', 'String', 'companyWebsite', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (507, 41, 'company_name', '公司名称', 'varchar(64)', 'String', 'companyName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (508, 41, 'short_name', '简称', 'varchar(64)', 'String', 'shortName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (509, 41, 'country_region', '国家地区', 'varchar(1024)', 'String', 'countryRegion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (510, 41, 'source_ids', '客户来源ID（多个以逗号分隔拼接)', 'varchar(128)', 'String', 'sourceIds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (511, 41, 'tag_ids', '客户标签ID（多个以逗号分隔拼接）', 'varchar(128)', 'String', 'tagIds', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (512, 41, 'sea_type', '私海/公海类型 1.私海 2.公海', 'tinyint(1)', 'Integer', 'seaType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 8, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (513, 41, 'packet_id', '分组ID', 'bigint(20)', 'Long', 'packetId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (514, 41, 'stage_id', '阶段ID', 'bigint(20)', 'Long', 'stageId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (515, 41, 'rating', '客户星级', 'tinyint(1)', 'Integer', 'rating', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (516, 41, 'code_type', '客户编号类型 1.自动生成 2.自定义', 'tinyint(1)', 'Integer', 'codeType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 12, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (517, 41, 'customer_no', '客户编号', 'varchar(64)', 'String', 'customerNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (518, 41, 'phone_prefix', '座机-电话区号', 'varchar(10)', 'String', 'phonePrefix', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (519, 41, 'phone', '座机-电话号码', 'varchar(20)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (520, 41, 'timezone', '时区', 'varchar(32)', 'String', 'timezone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (521, 41, 'scale', '规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上', 'tinyint(1)', 'Integer', 'scale', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (522, 41, 'fax', '传真', 'varchar(32)', 'String', 'fax', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (523, 41, 'address', '详细地址', 'varchar(255)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (524, 41, 'company_remarks', '公司备注', 'varchar(512)', 'String', 'companyRemarks', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 20, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (525, 41, 'company_logo', '公司logo', 'varchar(64)', 'String', 'companyLogo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (526, 41, 'focus_flag', '关注 0.未关注 1.已关注', 'tinyint(1)', 'Integer', 'focusFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (527, 41, 'last_contacted_at', '最近联系时间', 'datetime', 'Date', 'lastContactedAt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 23, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (528, 41, 'last_followup_at', '最近跟进时间', 'datetime', 'Date', 'lastFollowupAt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 24, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (529, 41, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 25, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (530, 41, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 26, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (531, 41, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 27, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (532, 41, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 28, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (533, 41, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 29, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (534, 41, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 30, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (535, 41, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 31, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:18');
INSERT INTO `gen_table_column` VALUES (536, 42, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (537, 42, 'customer_details_id', '客户详情ID', 'bigint(20)', 'Long', 'customerDetailsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (538, 42, 'nick_name', '昵称', 'varchar(64)', 'String', 'nickName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (539, 42, 'email', '邮箱', 'varchar(64)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (540, 42, 'social_platform', '社交平台\r\n[{\"type\":1,\"account\":\"\"}]\r\n', 'varchar(512)', 'String', 'socialPlatform', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (541, 42, 'phone', '联系电话\r\n[{\"phone_prefix\":\"\",\"phone\":\"\"}]\r\n', 'varchar(512)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (542, 42, 'rank', '职级 1.普通职员 2.中层管理者 3.高层管理者', 'tinyint(1)', 'Integer', 'rank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (543, 42, 'position', '职位', 'varchar(32)', 'String', 'position', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (544, 42, 'birthday', '生日', 'datetime', 'Date', 'birthday', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (545, 42, 'sex', '性别 1.不设置 2.男 3.女', 'tinyint(1)', 'Integer', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 10, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (546, 42, 'avatar_or_business_card', '头像/名片', 'varchar(128)', 'String', 'avatarOrBusinessCard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (547, 42, 'contact_remarks', '联系人备注', 'varchar(1024)', 'String', 'contactRemarks', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 12, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (548, 42, 'primary_contact_flag', '是否为主要联系人 1.是 0.否', 'tinyint(1)', 'Integer', 'primaryContactFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (549, 42, 'order_index', '排序', 'bigint(20)', 'Long', 'orderIndex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (550, 42, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (551, 42, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (552, 42, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (553, 42, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (554, 42, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (555, 42, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (556, 42, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 21, 'admin', '2023-10-31 16:43:19', '', '2023-10-31 16:45:54');
INSERT INTO `gen_table_column` VALUES (557, 43, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (561, 43, 'order_index', '排序', 'bigint(20)', 'Long', 'orderIndex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (562, 43, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (563, 43, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (564, 43, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (565, 43, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (566, 43, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (567, 43, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (568, 43, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2023-11-01 16:07:10', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (569, 43, 'column_name', '字段名称', 'varchar(64)', 'String', 'columnName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, '', '2023-11-01 16:17:29', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (570, 43, 'column_alias', '字段别名', 'varchar(64)', 'String', 'columnAlias', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, '', '2023-11-01 16:17:29', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (571, 43, 'column_type', '字段类型', 'varchar(10)', 'String', 'columnType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, '', '2023-11-01 16:17:29', '', '2023-11-01 16:33:15');
INSERT INTO `gen_table_column` VALUES (572, 43, 'app_type', '应用类型 1.客户查重 2.客户列表 3.公海客户', 'tinyint(1)', 'Integer', 'appType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, '', '2023-11-01 16:33:15', '', NULL);
INSERT INTO `gen_table_column` VALUES (573, 44, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (574, 44, 'parent_id', '父ID 第一级为-1', 'bigint(20)', 'Long', 'parentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (575, 44, 'name', '客群名称', 'varchar(64)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (576, 44, 'usage_scope', '使用范围 1.公司共享 2.个人使用', 'tinyint(1)', 'Integer', 'usageScope', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (577, 44, 'visibility_scope_type', '可见范围-类型 1.全公司可见 2.全部部门 3.指定部门 4.全部人员 5.指定人员', 'tinyint(1)', 'Integer', 'visibilityScopeType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (578, 44, 'visibility_scope_content', '可见范围-内容', 'varchar(255)', 'String', 'visibilityScopeContent', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'editor', '', 6, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (579, 44, 'condition_rule_type', '条件规则 1.满足全部条件 2.满足任一条件 3.自定义条件', 'tinyint(1)', 'Integer', 'conditionRuleType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 7, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (580, 44, 'condition_rule_content', '条件规则内容', 'varchar(2048)', 'String', 'conditionRuleContent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 8, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (581, 44, 'subgroup_flag', '添加二级客群 0.未选 1.选中', 'tinyint(1)', 'Integer', 'subgroupFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (582, 44, 'addition_rule', '添加规则 1.自动生成 2.手动添加', 'tinyint(1)', 'Integer', 'additionRule', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (583, 44, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 11, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (584, 44, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (585, 44, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (586, 44, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (587, 44, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (588, 44, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (589, 44, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-11-02 13:26:34', '', '2023-11-02 13:27:38');
INSERT INTO `gen_table_column` VALUES (590, 45, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:07');
INSERT INTO `gen_table_column` VALUES (591, 45, 'customer_details_id', '客户详情ID', 'bigint(20)', 'Long', 'customerDetailsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:07');
INSERT INTO `gen_table_column` VALUES (592, 45, 'segment_id', '客群ID', 'bigint(20)', 'Long', 'segmentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:07');
INSERT INTO `gen_table_column` VALUES (593, 46, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:58');
INSERT INTO `gen_table_column` VALUES (594, 46, 'customer_details_id', '客户详情ID', 'bigint(20)', 'Long', 'customerDetailsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:58');
INSERT INTO `gen_table_column` VALUES (595, 46, 'source_id', '客户来源ID', 'bigint(20)', 'Long', 'sourceId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:58');
INSERT INTO `gen_table_column` VALUES (596, 47, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:47');
INSERT INTO `gen_table_column` VALUES (597, 47, 'customer_details_id', '客户详情ID', 'bigint(20)', 'Long', 'customerDetailsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:47');
INSERT INTO `gen_table_column` VALUES (598, 47, 'tag_id', '客户标签ID', 'bigint(20)', 'Long', 'tagId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:10:44', '', '2023-11-02 17:12:47');
INSERT INTO `gen_table_column` VALUES (599, 48, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (600, 48, 'company_website', '公司网址', 'varchar(255)', 'String', 'companyWebsite', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (601, 48, 'company_name', '公司名称', 'varchar(64)', 'String', 'companyName', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (602, 48, 'short_name', '简称', 'varchar(64)', 'String', 'shortName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (603, 48, 'country_region', '国家地区', 'varchar(1024)', 'String', 'countryRegion', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (604, 48, 'sea_type', '私海/公海类型 1.私海 2.公海', 'tinyint(1)', 'Integer', 'seaType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (605, 48, 'packet_id', '分组ID', 'bigint(20)', 'Long', 'packetId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (606, 48, 'stage_id', '阶段ID', 'bigint(20)', 'Long', 'stageId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (607, 48, 'rating', '客户星级', 'tinyint(1)', 'Integer', 'rating', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (608, 48, 'customer_no_type', '客户编号类型 1.自动生成 2.自定义', 'tinyint(1)', 'Integer', 'customerNoType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 10, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (609, 48, 'customer_no', '客户编号', 'varchar(64)', 'String', 'customerNo', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (610, 48, 'phone_prefix', '座机-电话区号', 'varchar(10)', 'String', 'phonePrefix', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (611, 48, 'phone', '座机-电话号码', 'varchar(20)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (612, 48, 'timezone', '时区', 'varchar(32)', 'String', 'timezone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (613, 48, 'scale', '规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上', 'tinyint(1)', 'Integer', 'scale', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (614, 48, 'fax', '传真', 'varchar(32)', 'String', 'fax', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (615, 48, 'address', '详细地址', 'varchar(255)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (616, 48, 'company_remarks', '公司备注', 'varchar(512)', 'String', 'companyRemarks', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 18, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (617, 48, 'company_logo', '公司logo', 'varchar(64)', 'String', 'companyLogo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (618, 48, 'focus_flag', '关注 0.未关注 1.已关注', 'tinyint(1)', 'Integer', 'focusFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (619, 48, 'last_contacted_at', '最近联系时间', 'datetime', 'Date', 'lastContactedAt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 21, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (620, 48, 'last_followup_at', '最近跟进时间', 'datetime', 'Date', 'lastFollowupAt', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'datetime', '', 22, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (621, 48, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 23, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (622, 48, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (623, 48, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 25, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (624, 48, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 26, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (625, 48, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 27, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (626, 48, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 28, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (627, 48, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 29, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:26');
INSERT INTO `gen_table_column` VALUES (628, 49, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (629, 49, 'customer_id', '客户详情ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (630, 49, 'nick_name', '昵称', 'varchar(64)', 'String', 'nickName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (631, 49, 'email', '邮箱', 'varchar(64)', 'String', 'email', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (632, 49, 'social_platform', '社交平台\r\n[{\"type\":1,\"account\":\"\"}]\r\n', 'varchar(512)', 'String', 'socialPlatform', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (633, 49, 'phone', '联系电话\r\n[{\"phone_prefix\":\"\",\"phone\":\"\"}]\r\n', 'varchar(512)', 'String', 'phone', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (634, 49, 'rank', '职级 1.普通职员 2.中层管理者 3.高层管理者', 'tinyint(1)', 'Integer', 'rank', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (635, 49, 'position', '职位', 'varchar(32)', 'String', 'position', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (636, 49, 'birthday', '生日', 'datetime', 'Date', 'birthday', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 9, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (637, 49, 'sex', '性别 1.不设置 2.男 3.女', 'tinyint(1)', 'Integer', 'sex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 10, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (638, 49, 'avatar_or_business_card', '头像/名片', 'varchar(128)', 'String', 'avatarOrBusinessCard', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (639, 49, 'contact_remarks', '联系人备注', 'varchar(1024)', 'String', 'contactRemarks', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 12, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (640, 49, 'primary_contact_flag', '是否为主要联系人 1.是 0.否', 'tinyint(1)', 'Integer', 'primaryContactFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (641, 49, 'order_index', '排序', 'bigint(20)', 'Long', 'orderIndex', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (642, 49, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 15, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (643, 49, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (644, 49, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (645, 49, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (646, 49, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (647, 49, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 20, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (648, 49, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 21, 'admin', '2023-11-02 17:15:24', '', '2023-11-02 17:16:42');
INSERT INTO `gen_table_column` VALUES (649, 50, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:03');
INSERT INTO `gen_table_column` VALUES (650, 50, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:03');
INSERT INTO `gen_table_column` VALUES (651, 50, 'segment_id', '客群ID', 'bigint(20)', 'Long', 'segmentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:03');
INSERT INTO `gen_table_column` VALUES (652, 51, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:17');
INSERT INTO `gen_table_column` VALUES (653, 51, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:17');
INSERT INTO `gen_table_column` VALUES (654, 51, 'source_id', '客户来源ID', 'bigint(20)', 'Long', 'sourceId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:17');
INSERT INTO `gen_table_column` VALUES (655, 52, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:34');
INSERT INTO `gen_table_column` VALUES (656, 52, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:34');
INSERT INTO `gen_table_column` VALUES (657, 52, 'tag_id', '客户标签ID', 'bigint(20)', 'Long', 'tagId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-02 17:23:14', '', '2023-11-02 17:24:34');
INSERT INTO `gen_table_column` VALUES (658, 53, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (659, 53, 'follow_up_records_id', '写跟进ID', 'bigint(20)', 'Long', 'followUpRecordsId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (660, 53, 'comment', '评论内容', 'varchar(1024)', 'String', 'comment', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'textarea', '', 3, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (661, 53, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (662, 53, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (663, 53, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (664, 53, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (665, 53, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (666, 53, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (667, 53, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-11-03 13:49:46', '', '2023-11-03 13:51:11');
INSERT INTO `gen_table_column` VALUES (668, 54, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (669, 54, 'schedule_content', '日程内容', 'varchar(512)', 'String', 'scheduleContent', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'editor', '', 2, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (670, 54, 'color', '颜色', 'varchar(32)', 'String', 'color', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (671, 54, 'all_day_flag', '全天 0.否 1.是', 'tinyint(1)', 'Integer', 'allDayFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (672, 54, 'schedule_start_time', '日程开始时间', 'datetime', 'Date', 'scheduleStartTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 5, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (673, 54, 'schedule_end_time', '日程结束时间', 'datetime', 'Date', 'scheduleEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 6, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (674, 54, 'recurringSchedule', '周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义', 'tinyint(1)', 'Integer', 'recurringSchedule', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (675, 54, 'custom_cycle_value', '自定义周期-值', 'int(11)', 'Long', 'customCycleValue', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (676, 54, 'custom_cycle_type', '自定义周期-类型 1.天 2.周 3.月', 'tinyint(1)', 'Integer', 'customCycleType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 9, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (677, 54, 'cycle_end_time', '周期结束时间', 'datetime', 'Date', 'cycleEndTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 10, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (678, 54, 'reminder_time', '提醒时间 [{\"reminderTimeType\": 1, \"reminderTimeValue\": \"2023-11-03 00:00:00\"}]\r\nreminderTimeType：提醒时间类型 1.不提醒 2.当天开始(上午9:00) 3.1天前(上午9:00) 4.2天前(上午9:00) 5.1周前(上午9:00) 6.自定义\r\nreminderTimeValue：提醒时间', 'varchar(512)', 'String', 'reminderTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 11, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (679, 54, 'participants', '参与人（多个逗号分隔）', 'varchar(255)', 'String', 'participants', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (680, 54, 'remark', '备注', 'varchar(512)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', '', 13, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (681, 54, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (682, 54, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (683, 54, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (684, 54, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 17, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (685, 54, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (686, 54, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 19, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (687, 54, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 20, 'admin', '2023-11-03 16:22:46', '', '2023-11-03 16:23:09');
INSERT INTO `gen_table_column` VALUES (688, 55, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (689, 55, 'schedule_id', '日程ID', 'bigint(20)', 'Long', 'scheduleId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (690, 55, 'user_id', '参与人', 'bigint(20)', 'Long', 'userId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (691, 55, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 4, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (692, 55, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (693, 55, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 6, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (694, 55, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 7, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (695, 55, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (696, 55, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (697, 55, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2023-11-03 17:15:45', '', '2023-11-03 17:16:04');
INSERT INTO `gen_table_column` VALUES (698, 56, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-15 11:15:26', '', '2023-11-15 14:10:23');
INSERT INTO `gen_table_column` VALUES (699, 56, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-15 11:15:26', '', '2023-11-15 14:10:23');
INSERT INTO `gen_table_column` VALUES (700, 56, 'sea_type', '私海/公海类型 1.私海 2.公海', 'tinyint(1)', 'Integer', 'seaType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 3, 'admin', '2023-11-15 11:15:26', '', '2023-11-15 14:10:23');
INSERT INTO `gen_table_column` VALUES (701, 56, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2023-11-15 11:15:26', '', '2023-11-15 14:10:23');
INSERT INTO `gen_table_column` VALUES (702, 56, 'type', '类型 1.手动 2.自动', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 5, '', '2023-11-15 14:10:23', '', NULL);
INSERT INTO `gen_table_column` VALUES (703, 57, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:39:53');
INSERT INTO `gen_table_column` VALUES (704, 57, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:39:53');
INSERT INTO `gen_table_column` VALUES (705, 57, 'segment_id', '客群ID', 'bigint(20)', 'Long', 'segmentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:39:53');
INSERT INTO `gen_table_column` VALUES (706, 57, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 4, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:39:53');
INSERT INTO `gen_table_column` VALUES (707, 58, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:40:12');
INSERT INTO `gen_table_column` VALUES (708, 58, 'publicleads_rules_id', '公海规则ID', 'bigint(20)', 'Long', 'publicleadsRulesId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:40:12');
INSERT INTO `gen_table_column` VALUES (709, 58, 'segment_id', '客群ID', 'bigint(20)', 'Long', 'segmentId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-15 15:39:31', '', '2023-11-15 15:40:12');
INSERT INTO `gen_table_column` VALUES (710, 59, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (711, 59, 'name', '文件名称', 'varchar(128)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (712, 59, 'path', '文件路径', 'varchar(255)', 'String', 'path', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (713, 59, 'extension', '文件后缀', 'varchar(32)', 'String', 'extension', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (714, 59, 'size', '文件大小', 'bigint(20)', 'Long', 'size', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (715, 59, 'type', '关联类型 1.邮件附件 2.手动上传', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (716, 59, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 8, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (717, 59, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (718, 59, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (719, 59, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (720, 59, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (721, 59, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (722, 59, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 14, 'admin', '2023-11-16 11:18:37', '', '2023-11-16 11:40:56');
INSERT INTO `gen_table_column` VALUES (723, 59, 'customer_id', '客户ID', 'bigint(20)', 'Long', 'customerId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2023-11-16 11:40:56', '', NULL);
INSERT INTO `gen_table_column` VALUES (724, 60, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-11-17 11:24:10', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (725, 60, 'name', '标签名称', 'varchar(32)', 'String', 'name', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 2, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (726, 60, 'color', '标签颜色', 'varchar(32)', 'String', 'color', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (727, 60, 'type', '类型 1.公司 2.个人', 'tinyint(1)', 'Integer', 'type', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 4, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (728, 60, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', '1', '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (729, 60, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (730, 60, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 7, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (731, 60, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 8, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (732, 60, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (733, 60, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 10, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (734, 60, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2023-11-17 11:24:11', '', '2023-11-17 11:24:31');
INSERT INTO `gen_table_column` VALUES (735, 1, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-12-22 15:06:16', '', NULL);
INSERT INTO `gen_table_column` VALUES (736, 1, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, '', '2023-12-22 15:06:16', '', NULL);
INSERT INTO `gen_table_column` VALUES (737, 6, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, '', '2023-12-22 15:19:34', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (738, 6, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, '', '2023-12-22 15:19:34', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (739, 6, 'mail_total', '邮件总数', 'int(11)', 'Long', 'mailTotal', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 9, '', '2023-12-22 15:54:29', '', '2023-12-26 14:14:11');
INSERT INTO `gen_table_column` VALUES (740, 6, 'added_count', '新增数', 'int(11)', 'Long', 'addedCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2023-12-26 14:14:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (741, 6, 'duplicate_count', '重复数', 'int(11)', 'Long', 'duplicateCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-12-26 14:14:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (742, 6, 'filtered_count', '过滤数', 'int(11)', 'Long', 'filteredCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, '', '2023-12-26 14:14:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (743, 6, 'failure_count', '失败数', 'int(11)', 'Long', 'failureCount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-12-26 14:14:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (744, 6, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, '', '2023-12-26 14:14:11', '', NULL);
INSERT INTO `gen_table_column` VALUES (745, 10, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, '', '2023-12-27 16:16:26', '', NULL);
INSERT INTO `gen_table_column` VALUES (746, 10, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, '', '2023-12-27 16:16:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (747, 61, 'id', '主键', 'bigint(20)', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (749, 61, 'letter_nickname', '写信昵称', 'varchar(64)', 'String', 'letterNickname', '0', '0', '1', '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (750, 61, 'default_signature_id', '默认签名', 'bigint(20)', 'Long', 'defaultSignatureId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (751, 61, 'reply_signature_id', '回复/转发签名', 'bigint(20)', 'Long', 'replySignatureId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (752, 61, 'default_cc_flag', '默认抄送: 0.关闭 1.启用', 'tinyint(1)', 'Integer', 'defaultCcFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2023-12-27 17:09:49', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (753, 61, 'default_cc', '默认抄送,请使用\";\"分隔多个邮箱', 'varchar(1024)', 'String', 'defaultCc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 7, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (754, 61, 'default_bcc_flag', '默认密送: 0.关闭 1.启用', 'tinyint(1)', 'Integer', 'defaultBccFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (755, 61, 'default_bcc', '默认密送,请使用\";\"分隔多个邮箱', 'varchar(1024)', 'String', 'defaultBcc', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 9, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (756, 61, 'trace_flag', '是否追踪邮件: 0.否 1.是', 'tinyint(1)', 'Integer', 'traceFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (757, 61, 'return_receipt_flag', '是否回执: 0.否 1.是', 'tinyint(1)', 'Integer', 'returnReceiptFlag', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (758, 61, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (759, 61, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (760, 61, 'create_by', '创建者', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (761, 61, 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (762, 61, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (763, 61, 'update_by', '更新者', 'varchar(64)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 17, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (764, 61, 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 18, 'admin', '2023-12-27 17:09:50', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (765, 61, 'task_id', '配置常规ID', 'bigint(20)', 'Long', 'taskId', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'input', '', 2, '', '2023-12-27 17:17:26', '', '2023-12-27 17:18:13');
INSERT INTO `gen_table_column` VALUES (766, 3, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, '', '2023-12-27 21:31:48', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (767, 3, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 25, '', '2023-12-27 21:31:48', '', '2023-12-27 22:11:55');
INSERT INTO `gen_table_column` VALUES (768, 3, 'font_color', '文字颜色', 'varchar(32)', 'String', 'fontColor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, '', '2023-12-27 22:11:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (769, 3, 'mass_email_display', '群发邮件展示 1.仅在群发箱 2.发件箱与群发箱', 'tinyint(1)', 'Integer', 'massEmailDisplay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-12-27 22:11:54', '', NULL);
INSERT INTO `gen_table_column` VALUES (770, 3, 'paste_format', '粘贴格式 1,始终移除格式 2.始终保持格式 3.每次粘贴提醒', 'tinyint(1)', 'Integer', 'pasteFormat', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, '', '2023-12-27 22:11:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (771, 3, 'last_day_flag', '最后一天标识 1.勾选 0未选', 'tinyint(1)', 'Integer', 'lastDayFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, '', '2023-12-27 22:11:55', '', NULL);
INSERT INTO `gen_table_column` VALUES (772, 8, 'del_flag', '删除标志(0代表存在2代表删除)', 'char(1)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 10, '', '2023-12-28 14:19:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (773, 8, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-12-28 14:19:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (774, 8, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, '', '2023-12-28 14:19:27', '', NULL);
INSERT INTO `gen_table_column` VALUES (775, 21, 'fixed_flag', '是否固定: 0.否 1.是', 'tinyint(1)', 'Integer', 'fixedFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (776, 21, 'read_flag', '是否已读: 0.否 1.是', 'tinyint(1)', 'Integer', 'readFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (777, 21, 'folder_id', '文件夹ID', 'bigint(20)', 'Long', 'folderId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (778, 21, 'forward_to', '转发至', 'varchar(64)', 'String', 'forwardTo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (779, 21, 'pending_flag', '是否待处理: 0.否 1.是', 'tinyint(1)', 'Integer', 'pendingFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (780, 21, 'pending_type', '待处理类型 1.邮件接收时间 2.邮件接收时间之后的第', 'tinyint(1)', 'Integer', 'pendingType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 16, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (781, 21, 'pending_day', '待处理-天', 'int(5)', 'Integer', 'pendingDay', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (782, 21, 'pending_time', '待处理-时间', 'varchar(10)', 'String', 'pendingTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 18, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (783, 21, 'auto_response', '自动回复', 'text', 'String', 'autoResponse', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 20, '', '2023-12-28 15:22:30', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (784, 21, 'create_id', '创建者ID', 'bigint(20)', 'Long', 'createId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, '', '2023-12-28 15:22:31', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (785, 21, 'update_id', '更新者ID', 'bigint(20)', 'Long', 'updateId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 31, '', '2023-12-28 15:22:31', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (786, 21, 'status', '状态 1.开启 0.关闭', 'tinyint(1)', 'Integer', 'status', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'radio', '', 25, '', '2023-12-28 15:26:58', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (788, 21, 'folder_flag', '是否移动至：0.否 1.是', 'tinyint(1)', 'Integer', 'folderFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, '', '2023-12-28 21:47:47', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (789, 21, 'apply_to_history_mail_true_type', '应用于历史邮件类型：1.针对收件箱的历史邮件 2.针对收件箱及所有文件夹的历史邮件（不包括已删除）', 'tinyint(1)', 'Integer', 'applyToHistoryMailTrueType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 22, '', '2023-12-28 21:47:48', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (790, 21, 'forward_to_flag', '是否转发至：0.否 1.是', 'tinyint(1)', 'Integer', 'forwardToFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, '', '2023-12-28 22:03:23', '', '2024-01-05 09:32:52');
INSERT INTO `gen_table_column` VALUES (791, 21, 'auto_response_flag', '是否自动回复：0.否 1.是', 'tinyint(1)', 'Integer', 'autoResponseFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, '', '2023-12-28 22:03:23', '', '2024-01-05 09:32:53');
INSERT INTO `gen_table_column` VALUES (792, 21, 'label_flag', '是否分类: 0.否 1.是', 'tinyint(1)', 'Integer', 'labelFlag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, '', '2024-01-05 09:32:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (793, 21, 'label_id', '分类ID', 'bigint(20)', 'Long', 'labelId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, '', '2024-01-05 09:32:52', '', NULL);
INSERT INTO `gen_table_column` VALUES (794, 21, 'order_num', '顺序', 'int(11)', 'Long', 'orderNum', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 26, '', '2024-01-05 09:32:53', '', NULL);

-- ----------------------------
-- Table structure for mailbox_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_blacklist`;
CREATE TABLE `mailbox_blacklist`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` tinyint(1) NOT NULL COMMENT '类型: 1.邮箱地址黑名单 2.域名黑名单',
  `content` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '邮箱地址或域名',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_type`(`type`) USING BTREE COMMENT '类型索引'
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '黑名单表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件夹表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_general
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_general`;
CREATE TABLE `mailbox_general`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `default_task_id` bigint(20) NULL DEFAULT NULL COMMENT '默认邮箱',
  `max_per_page` int(5) NULL DEFAULT 20 COMMENT '每页显示邮件数量',
  `email_reading_mode_flag` tinyint(1) NULL DEFAULT NULL COMMENT '邮件阅读模式: 1.启用(适合大屏用户,可快速切换邮件) 0.停用(适合小屏用户,更多空间查看邮件详情) 启用阅读模式后,查看邮件详情时,将保持左侧列表展示',
  `move_delete_report` tinyint(1) NULL DEFAULT 1 COMMENT '移动/删除/举报后: 1.阅读下一封邮件(推荐) 2.回到当前邮件列表',
  `language` tinyint(1) NULL DEFAULT 1 COMMENT '语言: 1.简体中文 2.繁体中文 3.英文',
  `default_font` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认字体',
  `font_size` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文字大小',
  `font_color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文字颜色',
  `group_box_view` tinyint(1) NULL DEFAULT 1 COMMENT '群发箱视图​: 1.任务视图 2.邮件视图',
  `mass_email_display` tinyint(1) NULL DEFAULT NULL COMMENT '群发邮件展示 1.仅在群发箱 2.发件箱与群发箱',
  `remind` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提醒: 1.禁止新邮件到达时在页面弹出通知 2.禁止发送邮件在页面弹出附件提醒',
  `signature_id` bigint(20) NULL DEFAULT NULL COMMENT '签名ID',
  `write_letter` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '写信,逗号分隔: 1.正文拼写检查(编辑器会以红色波浪线提示正文中的拼写错误) 2.新窗口写信 (将在下次打开邮件时生效) 3.发送后返回上一页',
  `paste_format` tinyint(1) NULL DEFAULT NULL COMMENT '粘贴格式 1,始终移除格式 2.始终保持格式 3.每次粘贴提醒',
  `auto_response_flag` tinyint(1) NULL DEFAULT 0 COMMENT '自动回复: 0.停用 1.启用(同一邮箱给你发送多封邮件时,4天内最多对该邮箱自动回复一次)',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `last_day_flag` tinyint(1) NULL DEFAULT NULL COMMENT '最后一天标识 1.勾选 0未选',
  `last_day` datetime(0) NULL DEFAULT NULL COMMENT '最后一天',
  `re_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回复内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_default_task_id`(`default_task_id`) USING BTREE COMMENT '默认邮箱'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱常规表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱服务器查询表' ROW_FORMAT = Dynamic;

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
  `mail_total` int(11) NOT NULL COMMENT '邮件总数',
  `added_count` int(11) NULL DEFAULT NULL COMMENT '新增数',
  `duplicate_count` int(11) NULL DEFAULT NULL COMMENT '重复数',
  `filtered_count` int(11) NULL DEFAULT NULL COMMENT '过滤数',
  `failure_count` int(11) NULL DEFAULT NULL COMMENT '失败数',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_id`(`task_id`) USING BTREE COMMENT '任务ID索引',
  INDEX `idx_create_id`(`create_id`) USING BTREE COMMENT '创建者ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '导入邮件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_label
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_label`;
CREATE TABLE `mailbox_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `color` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签颜色',
  `type` tinyint(1) NOT NULL COMMENT '类型: 1.系统标签 2.自定义标签',
  `order` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_other_config
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_other_config`;
CREATE TABLE `mailbox_other_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `delayed_mail_delivery` tinyint(1) NULL DEFAULT 1 COMMENT '邮件延迟发送: 1.即可发送 2.延迟30秒发送 3.延迟1分钟发送 4.延迟2分钟发送延迟 5.延迟5分钟发送',
  `sending_interval` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发送间隔',
  `local_real_time_time` tinyint(1) NULL DEFAULT NULL COMMENT '当地实时时间: 0.停用 1.启用',
  `email_reminders_flag` tinyint(1) NULL DEFAULT NULL COMMENT '邮件发送提醒: 1.弹窗提醒 2.不提醒',
  `email_translation_function_flag` tinyint(1) NULL DEFAULT NULL COMMENT '邮件翻译功能: 0.停用 1.启用',
  `pending_close` tinyint(1) NULL DEFAULT NULL COMMENT '待处理关闭: 1.回复邮件后自动关闭待处理状态 2.手动关闭待处理状态',
  `automation_pending` tinyint(1) NULL DEFAULT NULL COMMENT '自动化待处理: 0.停用 1.启用',
  `abnormal_mailbox_detection` tinyint(1) NULL DEFAULT NULL COMMENT '异常邮箱检测: 0.停用 1.启用',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '其他配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_quick_text
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_quick_text`;
CREATE TABLE `mailbox_quick_text`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `html` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'html',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '快速文本表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_signature
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_signature`;
CREATE TABLE `mailbox_signature`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '个性签名表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮箱任务表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 634 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件附件表' ROW_FORMAT = Dynamic;

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
  `send_date` datetime(0) NOT NULL COMMENT '发送时间',
  `eml_path` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '原始邮件存储路径',
  `status` tinyint(1) NOT NULL COMMENT '状态: 1.未发送(草稿箱) 2.发送中 3.发送成功 4.发送失败',
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
  INDEX `idx_type`(`type`) USING BTREE COMMENT '邮件类型索引',
  INDEX `idx_status`(`status`) USING BTREE COMMENT '邮件状态索引',
  INDEX `idx_folder`(`folder`) USING BTREE COMMENT '邮件文件夹索引',
  INDEX `idx_uid`(`uid`) USING BTREE COMMENT '邮件唯一id索引',
  INDEX `idx_folder_id`(`folder_id`) USING BTREE COMMENT '文件夹id索引',
  INDEX `idx_fixed_flag`(`fixed_flag`) USING BTREE COMMENT '固定索引',
  INDEX `idx_del_flag`(`del_flag`) USING BTREE COMMENT '删除索引'
) ENGINE = InnoDB AUTO_INCREMENT = 28643 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_email_attachment
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_attachment`;
CREATE TABLE `mailbox_task_email_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT '邮件ID',
  `attachment_id` bigint(20) NOT NULL COMMENT '附件ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_email_id`(`email_id`) USING BTREE COMMENT '邮件索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1380 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_email_id`(`email_id`) USING BTREE COMMENT '邮件id索引'
) ENGINE = InnoDB AUTO_INCREMENT = 28585 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件内容表' ROW_FORMAT = Dynamic;

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
-- Table structure for mailbox_task_email_label
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_email_label`;
CREATE TABLE `mailbox_task_email_label`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT '邮件ID',
  `label_id` bigint(20) NOT NULL COMMENT '标签ID',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_email_id`(`email_id`) USING BTREE COMMENT '邮件索引',
  INDEX `idx_label_id`(`label_id`) USING BTREE COMMENT '标签索引'
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件标签表' ROW_FORMAT = Dynamic;

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
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志(0代表存在2代表删除)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_email_id`(`email_id`) USING BTREE COMMENT '邮件索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮件追踪表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_task_single_setting
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_task_single_setting`;
CREATE TABLE `mailbox_task_single_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID',
  `letter_nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '写信昵称',
  `default_signature_id` bigint(20) NULL DEFAULT NULL COMMENT '默认签名',
  `reply_signature_id` bigint(20) NULL DEFAULT NULL COMMENT '回复/转发签名',
  `default_cc_flag` tinyint(1) NULL DEFAULT NULL COMMENT '默认抄送: 0.关闭 1.启用',
  `default_cc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认抄送,请使用\";\"分隔多个邮箱',
  `default_bcc_flag` tinyint(1) NULL DEFAULT NULL COMMENT '默认密送: 0.关闭 1.启用',
  `default_bcc` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '默认密送,请使用\";\"分隔多个邮箱',
  `trace_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否追踪邮件: 0.否 1.是',
  `return_receipt_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否回执: 0.否 1.是',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_task_id`(`task_id`) USING BTREE COMMENT '任务索引'
) ENGINE = InnoDB AUTO_INCREMENT = 23089 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '单个邮箱设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_template
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_template`;
CREATE TABLE `mailbox_template`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `template_type_id` bigint(20) NULL DEFAULT NULL COMMENT '模板类型',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '模板内容',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_template_type
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_template_type`;
CREATE TABLE `mailbox_template_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '模板类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mailbox_transceiver_rule
-- ----------------------------
DROP TABLE IF EXISTS `mailbox_transceiver_rule`;
CREATE TABLE `mailbox_transceiver_rule`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rule_type` tinyint(1) NOT NULL COMMENT '规则类型: 1.收件规则 2.发件规则',
  `rule_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则名称',
  `execute_condition` tinyint(1) NOT NULL COMMENT '执行条件: 1.满足以下所有条件 2.满足以下任一条件',
  `execute_condition_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行条件内容',
  `execute_operation` tinyint(1) NOT NULL COMMENT '执行操作: 1.执行以下操作 2.移动到【已删除邮件】',
  `fixed_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否固定: 0.否 1.是',
  `read_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否已读: 0.否 1.是',
  `label_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否分类: 0.否 1.是',
  `label_id` bigint(20) NULL DEFAULT NULL COMMENT '分类ID',
  `folder_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否移动至：0.否 1.是',
  `folder_id` bigint(20) NULL DEFAULT -1 COMMENT '文件夹ID',
  `forward_to_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否转发至：0.否 1.是',
  `forward_to` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '转发至',
  `pending_flag` tinyint(1) NULL DEFAULT 0 COMMENT '是否待处理: 0.否 1.是',
  `pending_type` tinyint(1) NULL DEFAULT NULL COMMENT '待处理类型 1.邮件接收时间 2.邮件接收时间之后的第',
  `pending_day` int(5) NULL DEFAULT NULL COMMENT '待处理-天',
  `pending_time` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '待处理-时间',
  `auto_response_flag` tinyint(1) NULL DEFAULT NULL COMMENT '是否自动回复：0.否 1.是',
  `auto_response` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '自动回复',
  `apply_to_history_mail_flag` tinyint(1) NOT NULL DEFAULT 1 COMMENT '应用于历史邮件: 0.否 1.是',
  `apply_to_history_mail_true_type` tinyint(1) NULL DEFAULT NULL COMMENT '应用于历史邮件类型：1.针对收件箱的历史邮件 2.针对收件箱及所有文件夹的历史邮件（不包括已删除）',
  `execute_task_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '执行邮箱ID: 0.表示全部邮箱',
  `other_sending_rules` tinyint(1) NOT NULL COMMENT '其他发件规则: 1.继续执行 2.不再执行',
  `status` tinyint(1) NOT NULL COMMENT '状态 1.开启 0.关闭',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志(0代表存在2代表删除)',
  `create_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_id` bigint(20) NULL DEFAULT NULL COMMENT '更新者ID',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_rule_type`(`rule_type`) USING BTREE COMMENT '规则类型索引'
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '收发件规则表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint(13) NOT NULL COMMENT '触发的时间',
  `sched_time` bigint(13) NOT NULL COMMENT '定时器制定的时间',
  `priority` int(11) NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint(13) NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint(13) NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '深色主题theme-dark，浅色主题theme-light');
INSERT INTO `sys_config` VALUES (4, '账号自助-验证码开关', 'sys.account.captchaEnabled', 'true', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '是否开启验证码功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (6, '用户登录-黑名单列表', 'sys.login.blackIPList', '', 'Y', 'admin', '2023-07-31 13:30:09', '', NULL, '设置登录IP黑名单限制，多个匹配项以;分隔，支持匹配（*通配、网段）');
INSERT INTO `sys_config` VALUES (7, '翻译-AccessKeyID', 'translate.access.key.id', 'LTAI5tBRVRKejS2QnuVHsRMa', 'Y', 'admin', '2024-01-18 14:03:21', 'admin', '2024-01-18 14:04:03', NULL);
INSERT INTO `sys_config` VALUES (8, '翻译-AccessKeySecret', 'translate.access.key.secret', 'hPwRn1Z2Yc7aTvpM7aVANbD7HWBfP7', 'Y', 'admin', '2024-01-18 14:04:48', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL);
INSERT INTO `sys_dept` VALUES (110, 103, '0,100,101,103', '自研部门', 1, NULL, NULL, NULL, '0', '0', 'admin', '2023-11-24 17:00:57', '', NULL);
INSERT INTO `sys_dept` VALUES (111, 103, '0,100,101,103', '外包部门', 2, NULL, NULL, NULL, '0', '0', 'admin', '2023-11-24 17:01:09', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '登录状态列表');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '0', 'admin', '2023-07-31 13:30:09', '', '2024-02-04 17:11:01', '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '0', 'admin', '2023-07-31 13:30:09', '', '2024-02-04 17:11:03', '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_job` VALUES (4, '拉取邮件', 'SYSTEM', 'emailTask.syncEmail', '0 0/5 * * * ? ', '1', '1', '1', 'admin', '2023-09-13 08:27:30', 'admin', '2024-01-09 17:16:14', '');
INSERT INTO `sys_job` VALUES (5, '发送邮件', 'SYSTEM', 'emailTask.sendEmail', '0/10 * * * * ?', '1', '0', '1', 'admin', '2023-09-13 13:28:35', 'admin', '2024-01-09 17:16:16', '');
INSERT INTO `sys_job` VALUES (6, '客户客群清洗', 'SYSTEM', 'customerTask.shuffle', '0 0 0/3 * * ? ', '1', '1', '0', 'admin', '2023-11-27 07:48:56', 'admin', '2023-12-04 14:47:32', '');
INSERT INTO `sys_job` VALUES (7, '移动公海规则处理', 'SYSTEM', 'customerTask.movePublicleadsRulesHandler', '0 5 0 ? * * ', '1', '1', '0', 'admin', '2023-11-27 07:49:49', '', '2023-11-27 07:49:55', '');
INSERT INTO `sys_job` VALUES (8, '异常邮箱检测', 'SYSTEM', 'emailTask.testEmail', '0 0 * * * ?', '1', '1', '0', 'admin', '2024-01-04 14:53:55', '', '2024-01-04 14:54:00', '');
INSERT INTO `sys_job` VALUES (9, '异常邮箱检测', 'DEFAULT', 'emailTask.testEmail', '0 0 0/1 * * ? ', '1', '1', '1', 'admin', '2024-01-23 14:07:49', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 261722 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE,
  INDEX `idx_sys_logininfor_s`(`status`) USING BTREE,
  INDEX `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 901 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int(4) NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
  `is_frame` int(1) NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) NULL DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2292 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, 'system', NULL, '', 1, 0, 'M', '0', '0', '', 'system', 'admin', '2023-07-31 13:30:09', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, 'monitor', NULL, '', 1, 0, 'M', '0', '0', '', 'monitor', 'admin', '2023-07-31 13:30:09', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, 'tool', NULL, '', 1, 0, 'M', '0', '0', '', 'tool', 'admin', '2023-07-31 13:30:09', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, '若依官网', 0, 4, 'http://ruoyi.vip', NULL, '', 0, 0, 'M', '0', '0', '', 'guide', 'admin', '2023-07-31 13:30:09', '', NULL, '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', '', 1, 0, 'C', '0', '0', 'system:user:list', 'user', 'admin', '2023-07-31 13:30:09', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', '', 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', 'admin', '2023-07-31 13:30:09', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, 'menu', 'system/menu/index', '', 1, 0, 'C', '0', '0', 'system:menu:list', 'tree-table', 'admin', '2023-07-31 13:30:09', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, 'dept', 'system/dept/index', '', 1, 0, 'C', '0', '0', 'system:dept:list', 'tree', 'admin', '2023-07-31 13:30:09', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, 'post', 'system/post/index', '', 1, 0, 'C', '0', '0', 'system:post:list', 'post', 'admin', '2023-07-31 13:30:09', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, 'dict', 'system/dict/index', '', 1, 0, 'C', '0', '0', 'system:dict:list', 'dict', 'admin', '2023-07-31 13:30:09', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, 'config', 'system/config/index', '', 1, 0, 'C', '0', '0', 'system:config:list', 'edit', 'admin', '2023-07-31 13:30:09', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, 'notice', 'system/notice/index', '', 1, 0, 'C', '0', '0', 'system:notice:list', 'message', 'admin', '2023-07-31 13:30:09', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, 'log', '', '', 1, 0, 'M', '0', '0', '', 'log', 'admin', '2023-07-31 13:30:09', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, 'online', 'monitor/online/index', '', 1, 0, 'C', '0', '0', 'monitor:online:list', 'online', 'admin', '2023-07-31 13:30:09', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, 'job', 'monitor/job/index', '', 1, 0, 'C', '0', '0', 'monitor:job:list', 'job', 'admin', '2023-07-31 13:30:09', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, 'druid', 'monitor/druid/index', '', 1, 0, 'C', '0', '0', 'monitor:druid:list', 'druid', 'admin', '2023-07-31 13:30:09', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, 'server', 'monitor/server/index', '', 1, 0, 'C', '0', '0', 'monitor:server:list', 'server', 'admin', '2023-07-31 13:30:09', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, 'cache', 'monitor/cache/index', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis', 'admin', '2023-07-31 13:30:09', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '缓存列表', 2, 6, 'cacheList', 'monitor/cache/list', '', 1, 0, 'C', '0', '0', 'monitor:cache:list', 'redis-list', 'admin', '2023-07-31 13:30:09', '', NULL, '缓存列表菜单');
INSERT INTO `sys_menu` VALUES (115, '表单构建', 3, 1, 'build', 'tool/build/index', '', 1, 0, 'C', '0', '0', 'tool:build:list', 'build', 'admin', '2023-07-31 13:30:09', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (116, '代码生成', 3, 2, 'gen', 'tool/gen/index', '', 1, 0, 'C', '0', '0', 'tool:gen:list', 'code', 'admin', '2023-07-31 13:30:09', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (117, '系统接口', 3, 3, 'swagger', 'tool/swagger/index', '', 1, 0, 'C', '0', '0', 'tool:swagger:list', 'swagger', 'admin', '2023-07-31 13:30:09', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, 'operlog', 'monitor/operlog/index', '', 1, 0, 'C', '0', '0', 'monitor:operlog:list', 'form', 'admin', '2023-07-31 13:30:09', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, 'logininfor', 'monitor/logininfor/index', '', 1, 0, 'C', '0', '0', 'monitor:logininfor:list', 'logininfor', 'admin', '2023-07-31 13:30:09', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:user:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:user:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:user:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:user:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:user:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '', '', '', 1, 0, 'F', '0', '0', 'system:user:import', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '', '', '', 1, 0, 'F', '0', '0', 'system:user:resetPwd', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:role:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:role:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:role:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:role:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:role:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:menu:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:dept:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '', '', '', 1, 0, 'F', '0', '0', 'system:post:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '', '', '', 1, 0, 'F', '0', '0', 'system:post:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '', '', '', 1, 0, 'F', '0', '0', 'system:post:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '', '', '', 1, 0, 'F', '0', '0', 'system:post:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '', '', '', 1, 0, 'F', '0', '0', 'system:post:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:dict:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', '', 1, 0, 'F', '0', '0', 'system:config:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', '', 1, 0, 'F', '0', '0', 'system:notice:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '日志导出', 500, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:operlog:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '登录查询', 501, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录删除', 501, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '日志导出', 501, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '账户解锁', 501, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:logininfor:unlock', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '在线查询', 109, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '批量强退', 109, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:batchLogout', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '单条强退', 109, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:online:forceLogout', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '任务查询', 110, 1, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务新增', 110, 2, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:add', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务修改', 110, 3, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务删除', 110, 4, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '状态修改', 110, 5, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:changeStatus', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '任务导出', 110, 6, '#', '', '', 1, 0, 'F', '0', '0', 'monitor:job:export', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '生成查询', 116, 1, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:query', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '生成修改', 116, 2, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:edit', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成删除', 116, 3, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:remove', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '导入代码', 116, 4, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:import', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '预览代码', 116, 5, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:preview', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '生成代码', 116, 6, '#', '', '', 1, 0, 'F', '0', '0', 'tool:gen:code', '#', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2002, '邮件', 0, 4, 'email/index', 'email/index', NULL, 1, 0, 'C', '0', '0', 'email:index', 'email', 'admin', '2023-08-03 14:10:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, '文件夹列表', 2183, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:list', '#', 'admin', '2023-10-11 16:46:24', 'admin', '2023-12-07 11:46:01', '');
INSERT INTO `sys_menu` VALUES (2020, '文件夹新增', 2183, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:add', '#', 'admin', '2023-10-11 16:46:47', 'admin', '2023-12-07 11:46:10', '');
INSERT INTO `sys_menu` VALUES (2021, '文件夹修改', 2183, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:edit', '#', 'admin', '2023-10-11 16:47:17', 'admin', '2023-12-07 11:46:18', '');
INSERT INTO `sys_menu` VALUES (2022, '文件夹删除', 2183, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:delete', '#', 'admin', '2023-10-11 16:47:36', 'admin', '2023-12-07 11:46:24', '');
INSERT INTO `sys_menu` VALUES (2035, '企业管理', 0, 5, 'company', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'compnay', 'admin', '2023-11-01 17:48:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2036, '客户', 0, 6, '/customer', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'peoples', 'admin', '2023-11-01 17:48:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2039, '建档黑名单列表', 2062, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:black:list:records:list', '#', 'admin', '2023-11-24 14:55:02', 'admin', '2023-11-24 15:10:49', '');
INSERT INTO `sys_menu` VALUES (2040, '新建建档黑名单', 2062, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:black:list:records:add', '#', 'admin', '2023-11-24 14:55:39', 'admin', '2023-11-24 15:11:02', '');
INSERT INTO `sys_menu` VALUES (2041, '删除建档黑名单', 2062, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:black:list:records:delete', '#', 'admin', '2023-11-24 14:56:00', 'admin', '2023-11-24 15:11:09', '');
INSERT INTO `sys_menu` VALUES (2042, '客群列表', 2129, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:segment:list', '#', 'admin', '2023-11-24 14:58:18', 'admin', '2023-11-24 15:51:00', '');
INSERT INTO `sys_menu` VALUES (2043, '获取团队成员列表', 2129, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:team:members:get', '#', 'admin', '2023-11-24 14:58:35', 'admin', '2023-11-24 15:51:19', '');
INSERT INTO `sys_menu` VALUES (2044, '查询私海客户列表', 2129, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:privateleads:list', '#', 'admin', '2023-11-24 14:58:50', 'admin', '2023-11-24 15:53:03', '');
INSERT INTO `sys_menu` VALUES (2045, '查询公海客户列表', 2129, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:publicleads:list', '#', 'admin', '2023-11-24 14:59:06', 'admin', '2023-11-24 15:53:14', '');
INSERT INTO `sys_menu` VALUES (2046, '获取客户详情详细信息', 2129, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:detail', '#', 'admin', '2023-11-24 14:59:33', 'admin', '2023-11-24 15:53:19', '');
INSERT INTO `sys_menu` VALUES (2047, '新增客户', 2129, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:add', '#', 'admin', '2023-11-24 14:59:54', 'admin', '2023-11-24 15:54:55', '');
INSERT INTO `sys_menu` VALUES (2048, '修改客户', 2129, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:edit', '#', 'admin', '2023-11-24 15:00:08', 'admin', '2023-11-24 15:55:00', '');
INSERT INTO `sys_menu` VALUES (2049, '移入客户至分组', 2129, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:move:packet', '#', 'admin', '2023-11-24 15:00:24', 'admin', '2023-11-24 15:55:05', '');
INSERT INTO `sys_menu` VALUES (2050, '转移给', 2129, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:transferred:to', '#', 'admin', '2023-11-24 15:00:46', 'admin', '2023-11-24 15:55:12', '');
INSERT INTO `sys_menu` VALUES (2051, '共享给', 2129, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:share:to', '#', 'admin', '2023-11-24 15:01:02', 'admin', '2023-11-24 15:55:18', '');
INSERT INTO `sys_menu` VALUES (2052, '取消跟进', 2129, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:cancel:follow:up', '#', 'admin', '2023-11-24 15:01:20', 'admin', '2023-11-24 15:55:26', '');
INSERT INTO `sys_menu` VALUES (2053, '移入公海', 2129, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:move:to:publicleads', '#', 'admin', '2023-11-24 15:01:37', 'admin', '2023-11-24 15:55:35', '');
INSERT INTO `sys_menu` VALUES (2054, '重新分配', 2129, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:reassign:to', '#', 'admin', '2023-11-24 15:01:49', 'admin', '2023-11-24 15:55:40', '');
INSERT INTO `sys_menu` VALUES (2055, '查询客户跟进人', 2129, 14, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:personnel:list', '#', 'admin', '2023-11-24 15:02:04', 'admin', '2023-11-24 15:55:45', '');
INSERT INTO `sys_menu` VALUES (2056, '取消跟进人', 2129, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:unassign:follow:up', '#', 'admin', '2023-11-24 15:02:19', 'admin', '2023-11-24 15:55:50', '');
INSERT INTO `sys_menu` VALUES (2057, '修改重点关注', 2129, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:edit:focusFlag', '#', 'admin', '2023-11-24 15:02:37', 'admin', '2023-11-24 15:55:55', '');
INSERT INTO `sys_menu` VALUES (2058, '查询公海分组', 2129, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:publicleads:groups:list', '#', 'admin', '2023-11-24 15:02:51', 'admin', '2023-11-24 15:56:00', '');
INSERT INTO `sys_menu` VALUES (2059, '变更公海分组', 2129, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:change:publicleads:groups', '#', 'admin', '2023-11-24 15:03:08', 'admin', '2023-11-24 15:56:05', '');
INSERT INTO `sys_menu` VALUES (2060, '移入私海', 2129, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:move:to:privateleads', '#', 'admin', '2023-11-24 15:03:23', 'admin', '2023-11-24 15:56:09', '');
INSERT INTO `sys_menu` VALUES (2062, '建档黑名单', 2153, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:10:42', 'admin', '2023-11-24 16:15:04', '');
INSERT INTO `sys_menu` VALUES (2063, '客户分组', 2153, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:12:38', 'admin', '2023-11-24 16:14:34', '');
INSERT INTO `sys_menu` VALUES (2064, '客户分组列表', 2063, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:packet:list', '#', 'admin', '2023-11-24 15:13:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2065, '新增客户分组', 2063, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:packet:add', '#', 'admin', '2023-11-24 15:13:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2066, '修改客户分组', 2063, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:packet:edit', '#', 'admin', '2023-11-24 15:13:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2067, '删除客户分组', 2063, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:packet:delete', '#', 'admin', '2023-11-24 15:13:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2068, '客户阶段', 2153, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:14:31', 'admin', '2023-11-24 16:14:44', '');
INSERT INTO `sys_menu` VALUES (2069, '客户阶段列表', 2068, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:stage:list', '#', 'admin', '2023-11-24 15:14:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2070, '新增客户阶段', 2068, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:stage:add', '#', 'admin', '2023-11-24 15:15:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2071, '修改客户阶段', 2068, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:stage:edit', '#', 'admin', '2023-11-24 15:15:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2072, '删除客户阶段', 2068, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:stage:delete', '#', 'admin', '2023-11-24 15:15:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2073, '公海管理', 2153, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:17:36', 'admin', '2023-11-24 16:14:51', '');
INSERT INTO `sys_menu` VALUES (2074, '移入公海规则列表', 2076, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:rules:list', '#', 'admin', '2023-11-24 15:18:17', 'admin', '2023-11-24 15:20:25', '');
INSERT INTO `sys_menu` VALUES (2075, '新增移入公海规则', 2076, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:rules:add', '#', 'admin', '2023-11-24 15:18:35', 'admin', '2023-11-24 15:20:35', '');
INSERT INTO `sys_menu` VALUES (2076, '移入公海规则', 2073, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:18:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2077, '修改移入公海规则', 2076, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:rules:edit', '#', 'admin', '2023-11-24 15:20:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2078, '删除移入公海规则', 2076, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:rules:delete', '#', 'admin', '2023-11-24 15:21:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2079, '移入公海白名单', 2073, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:21:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2080, '移入公海白名单列表', 2079, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:white:list:list', '#', 'admin', '2023-11-24 15:22:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2081, '新增移入公海白名单', 2079, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:white:list:add', '#', 'admin', '2023-11-24 15:22:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2082, '删除移入公海白名单', 2079, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:white:list:remove', '#', 'admin', '2023-11-24 15:22:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2083, '领取上限', 2073, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:23:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2084, '领取上限列表', 2083, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:claim:limit:list', '#', 'admin', '2023-11-24 15:24:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2085, '新增领取上限', 2083, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:claim:limit:add', '#', 'admin', '2023-11-24 15:24:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2086, '删除领取上限', 2083, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:claim:limit:delete', '#', 'admin', '2023-11-24 15:24:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2087, '公海分组', 2073, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:25:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2088, '公海分组列表', 2087, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:groups:list', '#', 'admin', '2023-11-24 15:25:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2089, '新增公海分组', 2087, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:groups:add', '#', 'admin', '2023-11-24 15:25:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2090, '修改公海分组', 2087, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:groups:edit', '#', 'admin', '2023-11-24 15:26:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2091, '删除公海分组', 2087, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:groups:delete', '#', 'admin', '2023-11-24 15:26:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2092, '移入公海原因', 2073, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:26:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2093, '移入公海原因列表', 2092, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:reason:list', '#', 'admin', '2023-11-24 15:27:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2094, '新增移入公海原因', 2092, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:reason:add', '#', 'admin', '2023-11-24 15:27:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2095, '修改移入公海原因', 2092, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:reason:edit', '#', 'admin', '2023-11-24 15:28:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2096, '删除移入公海原因', 2092, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:reason:delete', '#', 'admin', '2023-11-24 15:28:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2097, '客户跟进规则', 2153, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:30:48', 'admin', '2023-11-24 16:14:57', '');
INSERT INTO `sys_menu` VALUES (2098, '客户跟进规则列表', 2097, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:rules:list', '#', 'admin', '2023-11-24 15:31:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2099, '修改客户跟进规则标志', 2097, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:rules:edit:activeFlag', '#', 'admin', '2023-11-24 15:31:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2100, '客户上限', 2153, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:33:53', 'admin', '2023-11-24 16:15:09', '');
INSERT INTO `sys_menu` VALUES (2101, '客户上限列表', 2100, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:limits:list', '#', 'admin', '2023-11-24 15:34:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2102, '修改客户上限', 2100, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:edit', '#', 'admin', '2023-11-24 15:34:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2103, '客户查重设置', 2153, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:37:09', 'admin', '2023-11-24 16:15:14', '');
INSERT INTO `sys_menu` VALUES (2104, '客户查重设置列表', 2103, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:duplication:settings:list', '#', 'admin', '2023-11-24 15:37:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2105, '修改客户查重设置标志', 2103, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:duplication:settings:edit:activeFlag', '#', 'admin', '2023-11-24 15:37:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2106, '写跟进测试', 2153, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-11-24 15:38:16', 'admin', '2023-11-24 16:15:20', '');
INSERT INTO `sys_menu` VALUES (2107, '写跟进模板', 2106, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:39:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2108, '写跟进模板列表', 2107, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:templates:list', '#', 'admin', '2023-11-24 15:39:56', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2109, '新增写跟进模板', 2107, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:templates:add', '#', 'admin', '2023-11-24 15:40:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2110, '修改写跟进模板', 2107, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:templates:edit', '#', 'admin', '2023-11-24 15:40:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2111, '删除写跟进模板', 2107, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:templates:delete', '#', 'admin', '2023-11-24 15:40:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2112, '写跟进快捷文本', 2106, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:40:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2113, '写跟进快捷文本列表', 2112, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:quick:text:list', '#', 'admin', '2023-11-24 15:41:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2114, '新增写跟进快捷文本', 2112, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:quick:text:add', '#', 'admin', '2023-11-24 15:41:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2115, '修改写跟进快捷文本', 2112, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:quick:text:edit', '#', 'admin', '2023-11-24 15:41:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2116, '删除写跟进快捷文本', 2112, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:follow:up:quick:text:delete', '#', 'admin', '2023-11-24 15:42:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2117, '查询客户设置', 2153, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:settings:get', '#', 'admin', '2023-11-24 15:43:50', 'admin', '2023-11-24 16:15:26', '');
INSERT INTO `sys_menu` VALUES (2118, '修改客户设置', 2153, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:settings:edit', '#', 'admin', '2023-11-24 15:44:15', 'admin', '2023-11-24 16:15:32', '');
INSERT INTO `sys_menu` VALUES (2119, '客群管理', 2036, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:47:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2120, '客群列表', 2119, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:list', '#', 'admin', '2023-11-24 15:48:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2121, '客群列表（下拉）', 2119, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:simple:list', '#', 'admin', '2023-11-24 15:48:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2122, '新增客群', 2119, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:add', '#', 'admin', '2023-11-24 15:48:35', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2123, '修改客群', 2119, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:edit', '#', 'admin', '2023-11-24 15:48:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2124, '删除客群', 2119, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:delete', '#', 'admin', '2023-11-24 15:49:06', 'admin', '2023-11-24 15:49:22', '');
INSERT INTO `sys_menu` VALUES (2125, '获取条件规则字段', 2119, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:condition:rule:column', '#', 'admin', '2023-11-24 15:49:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2126, '获取用户列表', 2119, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:user:list', '#', 'admin', '2023-11-24 15:50:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2127, '二级分群字段', 2119, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:subgroup:column', '#', 'admin', '2023-11-24 15:50:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2128, '二级分群字段列表', 2119, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:subgroup:column:list', '#', 'admin', '2023-11-24 15:50:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2129, '客户', 2036, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:50:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2130, '客户查重', 2036, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:56:43', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2131, '客户查重筛选字段列表', 2130, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:duplicate:filter:column:list', '#', 'admin', '2023-11-24 15:57:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2132, '客户查重列表', 2130, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:duplicate:list', '#', 'admin', '2023-11-24 15:57:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2133, '客户设置', 2036, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 15:58:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2134, '移入公海规则', 2133, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:03:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2135, '移入公海规则列表', 2134, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:publicleads:rules:list', '#', 'admin', '2023-11-24 16:03:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2136, '分组设置', 2133, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:08:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2137, '分组列表', 2136, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:packet:list', '#', 'admin', '2023-11-24 16:08:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2138, '标签管理', 2133, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:08:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2139, '客户标签列表', 2138, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:list', '#', 'admin', '2023-11-24 16:09:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2140, '新增个人标签', 2138, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:personal:add', '#', 'admin', '2023-11-24 16:09:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2141, '修改个人标签', 2138, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:personal:edit', '#', 'admin', '2023-11-24 16:09:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2142, '删除个人标签', 2138, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:personal:delete', '#', 'admin', '2023-11-24 16:10:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2143, '设为公司标签', 2138, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:company:set:as:company:tag', '#', 'admin', '2023-11-24 16:10:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2144, '新增公司标签', 2138, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:company:add', '#', 'admin', '2023-11-24 16:10:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2145, '修改公司标签', 2138, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:company:edit', '#', 'admin', '2023-11-24 16:11:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2146, '删除公司标签', 2138, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:tag:company:delete', '#', 'admin', '2023-11-24 16:11:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2147, '客户来源', 2133, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:12:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2148, '客户来源列表', 2147, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:source:list', '#', 'admin', '2023-11-24 16:12:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2149, '客户导入', 2133, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:12:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2150, '下载客户模板', 2149, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:template:download', '#', 'admin', '2023-11-24 16:12:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2151, '导入客户', 2149, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:import', '#', 'admin', '2023-11-24 16:13:11', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2152, '导入客户列表', 2149, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:import:list', '#', 'admin', '2023-11-24 16:13:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2153, '客户设置', 2035, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:14:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2154, '来源设置', 2035, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 16:15:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2155, '客户来源列表', 2154, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:source:list', '#', 'admin', '2023-11-24 16:16:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2156, '新增客户来源', 2154, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:source:add', '#', 'admin', '2023-11-24 16:16:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2157, '修改客户来源', 2154, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:source:edit', '#', 'admin', '2023-11-24 16:16:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2158, '删除客户来源', 2154, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:source:delete', '#', 'admin', '2023-11-24 16:16:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2159, '获取部门和用户信息', 2035, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:user:list', '#', 'admin', '2023-11-24 16:25:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2160, '获取部门和用户信息', 2036, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'system:user:list', '#', 'admin', '2023-11-24 16:25:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2161, '客户写跟进', 2129, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 17:35:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2162, '客户写跟进列表', 2161, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:list', '#', 'admin', '2023-11-24 17:36:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2163, '新增客户写跟进', 2161, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:add', '#', 'admin', '2023-11-24 17:36:22', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2164, '修改客户写跟进', 2161, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:edit', '#', 'admin', '2023-11-24 17:36:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2165, '删除客户写跟进', 2161, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:delete', '#', 'admin', '2023-11-24 17:36:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2166, '写跟进评论', 2129, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 17:37:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2167, '新增写跟进评论', 2166, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:add', '#', 'admin', '2023-11-24 17:37:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2168, '修改写跟进评论', 2166, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:edit', '#', 'admin', '2023-11-24 17:38:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2169, '删除写跟进评论', 2166, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:delete', '#', 'admin', '2023-11-24 17:38:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2170, '客户日程', 2129, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 17:38:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2171, '客户日程列表', 2170, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:schedule:list', '#', 'admin', '2023-11-24 17:39:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2172, '新增客户日程', 2170, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:schedule:add', '#', 'admin', '2023-11-24 17:39:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2173, '修改客户日程', 2170, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:schedule:edit', '#', 'admin', '2023-11-24 17:40:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2174, '删除客户日程', 2170, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:schedule:delete', '#', 'admin', '2023-11-24 17:40:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2175, '修改客户日程重点关注', 2170, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:schedule:edit:focusFlag', '#', 'admin', '2023-11-24 17:40:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2176, '客户文档', 2129, 23, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-11-24 17:41:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2177, '客户文档列表', 2176, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:document:list', '#', 'admin', '2023-11-24 17:41:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2178, '上传文档', 2176, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:document:upload:multiple', '#', 'admin', '2023-11-24 17:42:07', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2179, '下载文档', 2176, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:document:download', '#', 'admin', '2023-11-24 17:42:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2180, '删除文档', 2176, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:document:delete', '#', 'admin', '2023-11-24 17:42:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2181, '客群详情', 2119, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:segment:detail', '#', 'admin', '2023-11-27 13:35:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2182, '查询所有用户', 2129, 24, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:get:all:users', '#', 'admin', '2023-11-29 14:55:33', 'admin', '2023-11-29 14:55:50', '');
INSERT INTO `sys_menu` VALUES (2183, '文件夹', 2002, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', '', '#', 'admin', '2023-12-07 11:45:01', 'admin', '2023-12-07 11:47:36', '');
INSERT INTO `sys_menu` VALUES (2184, '模板', 2002, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 11:50:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2185, '模板列表', 2184, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:list', '#', 'admin', '2023-12-07 11:51:24', 'admin', '2023-12-07 11:53:07', '');
INSERT INTO `sys_menu` VALUES (2186, '新增模板', 2184, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:add', '#', 'admin', '2023-12-07 11:51:44', 'admin', '2023-12-07 11:53:14', '');
INSERT INTO `sys_menu` VALUES (2187, '编辑模板', 2184, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:edit', '#', 'admin', '2023-12-07 11:51:59', 'admin', '2023-12-07 11:53:22', '');
INSERT INTO `sys_menu` VALUES (2188, '删除模板', 2184, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:delete', '#', 'admin', '2023-12-07 11:52:14', 'admin', '2023-12-07 11:53:28', '');
INSERT INTO `sys_menu` VALUES (2189, '模板类型', 2184, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 11:52:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2190, '模板类型列表', 2189, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:type:list', '#', 'admin', '2023-12-07 11:53:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2191, '新增模板类型', 2189, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:type:add', '#', 'admin', '2023-12-07 11:53:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2192, '编辑模板类型', 2189, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:type:edit', '#', 'admin', '2023-12-07 11:55:23', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2193, '删除模板类型', 2189, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:template:type:delete', '#', 'admin', '2023-12-07 11:55:41', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2194, '邮箱管理', 2002, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 11:56:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2195, '邮箱任务列表', 2194, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:list', '#', 'admin', '2023-12-07 11:56:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2196, '新增邮箱任务', 2194, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:add', '#', 'admin', '2023-12-07 11:57:26', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2197, '邮箱任务检测', 2194, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:test', '#', 'admin', '2023-12-07 11:57:41', 'admin', '2023-12-07 11:58:11', '');
INSERT INTO `sys_menu` VALUES (2198, '修改邮箱任务', 2194, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:edit', '#', 'admin', '2023-12-07 11:57:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2199, '邮箱任务解绑', 2194, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:unbind', '#', 'admin', '2023-12-07 11:58:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2200, '快速文本', 2002, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 13:39:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2201, '快速文本列表', 2200, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:quick:text:list', '#', 'admin', '2023-12-07 13:40:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2202, '新增快速文本', 2200, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:quick:text:add', '#', 'admin', '2023-12-07 13:40:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2203, '编辑快速文本', 2200, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:quick:text:edit', '#', 'admin', '2023-12-07 13:40:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2204, '删除快速文本', 2200, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:quick:text:delete', '#', 'admin', '2023-12-07 13:41:02', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2205, '标签', 2002, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 13:41:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2206, '标签列表', 2205, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:list', '#', 'admin', '2023-12-07 13:42:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2207, '新增标签', 2205, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:add', '#', 'admin', '2023-12-07 13:42:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2208, '修改标签颜色', 2205, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:color', '#', 'admin', '2023-12-07 13:42:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2209, '修改标签名称', 2205, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:name', '#', 'admin', '2023-12-07 13:43:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2210, '删除标签', 2205, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:delete', '#', 'admin', '2023-12-07 13:43:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2211, '邮件', 2002, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 13:44:20', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2212, '获取邮件列表-（首页）', 2211, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:list', '#', 'admin', '2023-12-07 13:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2213, '收件箱列表', 2211, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:pull:list', '#', 'admin', '2023-12-07 13:45:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2214, '发件箱列表', 2211, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:send:list', '#', 'admin', '2023-12-07 13:45:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2215, '邮件保存-（写信）', 2211, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:save', '#', 'admin', '2023-12-07 13:46:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2216, '邮件发送-（写信）', 2211, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send', '#', 'admin', '2023-12-07 13:46:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2217, '附件', 2211, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2023-12-07 13:48:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2218, '上传附件', 2217, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:upload', '#', 'admin', '2023-12-07 13:49:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2219, '重命名', 2217, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:rename', '#', 'admin', '2023-12-07 13:49:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2220, '删除附件', 2217, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:delete', '#', 'admin', '2023-12-07 13:49:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2221, '附件下载', 2217, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:download', '#', 'admin', '2023-12-07 13:50:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2222, '邮件固定', 2211, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:fixed', '#', 'admin', '2023-12-07 13:50:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2223, '邮件删除', 2211, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:delete', '#', 'admin', '2023-12-07 13:50:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2224, '快速回复', 2211, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send:quick:reply', '#', 'admin', '2023-12-07 13:51:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2225, '已读邮件', 2211, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:read', '#', 'admin', '2023-12-07 13:51:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2226, '垃圾邮件', 2211, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:spam', '#', 'admin', '2023-12-07 13:52:01', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2227, '移动邮件到文件夹', 2211, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:folder', '#', 'admin', '2023-12-07 13:52:19', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2228, '移动邮件到标签', 2211, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:label', '#', 'admin', '2023-12-07 13:52:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2229, '标记待处理', 2211, 14, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:pending', '#', 'admin', '2023-12-07 13:52:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2230, '邮件导出', 2211, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:export', '#', 'admin', '2023-12-07 13:53:05', 'admin', '2023-12-07 13:53:14', '');
INSERT INTO `sys_menu` VALUES (2231, '上传邮件附件', 2211, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:upload', '#', 'admin', '2023-12-07 13:53:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2232, '下载邮件附件', 2211, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:download', '#', 'admin', '2023-12-07 13:54:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2233, '删除邮件标签', 2211, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:delete:label', '#', 'admin', '2023-12-07 13:54:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2234, '统计菜单邮件数量', 2211, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:count:menu', '#', 'admin', '2023-12-07 13:54:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2235, '邮件详情', 2211, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:detail', '#', 'admin', '2024-01-15 22:30:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2236, '客户模块', 2211, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:03:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2237, '客户往来邮件列表', 2236, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:dealing:email:list', '#', 'admin', '2024-01-16 08:04:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2238, '搜索的客户列表', 2236, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:search:list', '#', 'admin', '2024-01-16 08:06:03', 'admin', '2024-01-16 08:12:41', '');
INSERT INTO `sys_menu` VALUES (2239, '联系人列表', 2236, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:contact:email:list', '#', 'admin', '2024-01-16 08:06:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2240, '通用列表', 2236, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:general:list', '#', 'admin', '2024-01-16 08:10:21', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2241, '公海分组列表', 2236, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:publicleads:groups:list', '#', 'admin', '2024-01-16 08:10:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2242, '客户分组列表', 2236, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:packet:list', '#', 'admin', '2024-01-16 08:10:57', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2243, '客户来源列表', 2236, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:source:list', '#', 'admin', '2024-01-16 08:11:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2244, '客户状态列表', 2236, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:stage:list', '#', 'admin', '2024-01-16 08:11:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2245, '客户星级列表', 2236, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:rating:list', '#', 'admin', '2024-01-16 08:11:49', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2246, '客户活跃度列表', 2236, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:activity:list', '#', 'admin', '2024-01-16 08:12:04', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2247, '客户邮件列表（分页）', 2236, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:customer:email:list', '#', 'admin', '2024-01-16 08:13:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2248, '客户详情', 2236, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:detail', '#', 'admin', '2024-01-16 08:14:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2249, '客户写跟进列表', 2236, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:list', '#', 'admin', '2024-01-16 08:15:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2250, '新增客户写跟进', 2236, 14, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:add', '#', 'admin', '2024-01-16 08:16:31', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2251, '修改客户写跟进', 2236, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:edit', '#', 'admin', '2024-01-16 08:17:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2252, '删除客户写跟进', 2236, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:delete', '#', 'admin', '2024-01-16 08:17:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2253, '新增写跟进评论', 2236, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:add', '#', 'admin', '2024-01-16 08:18:30', 'admin', '2024-01-16 08:18:38', '');
INSERT INTO `sys_menu` VALUES (2254, '修改写跟进评论', 2236, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:edit', '#', 'admin', '2024-01-16 08:19:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2255, '删除写跟进评论', 2236, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:customer:follow:up:records:comment:delete', '#', 'admin', '2024-01-16 08:19:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2256, '常规', 2002, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:23:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2257, '默认邮箱列表', 2256, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:general:default:task:list', '#', 'admin', '2024-01-16 08:23:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2258, '获取邮箱常规详细信息', 2256, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:general:get:info', '#', 'admin', '2024-01-16 08:23:52', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2259, '修改邮箱常规', 2256, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:general:edit', '#', 'admin', '2024-01-16 08:24:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2260, '签名', 2256, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:24:55', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2261, '个性签名列表', 2260, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:signature:list', '#', 'admin', '2024-01-16 08:25:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2262, '新增个性签名', 2260, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:signature:add', '#', 'admin', '2024-01-16 08:25:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2263, '修改个性签名', 2260, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:signature:edit', '#', 'admin', '2024-01-16 08:25:42', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2264, '删除个性签名', 2260, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:signature:delete', '#', 'admin', '2024-01-16 08:25:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2265, '单个邮箱设置', 2256, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:26:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2266, '单个邮箱设置列表', 2265, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:setting:list', '#', 'admin', '2024-01-16 08:26:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2267, '修改单个邮箱设置', 2265, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:setting:edit', '#', 'admin', '2024-01-16 08:26:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2268, '收发件规则', 2002, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:28:00', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2269, '收发件规则列表', 2268, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:list', '#', 'admin', '2024-01-16 08:28:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2270, '获取收发件规则详细信息', 2268, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:query', '#', 'admin', '2024-01-16 08:28:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2271, '新增收发件规则', 2268, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:add', '#', 'admin', '2024-01-16 08:28:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2272, '修改收发件规则', 2268, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:edit', '#', 'admin', '2024-01-16 08:29:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2273, '删除收发件规则', 2268, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:delete', '#', 'admin', '2024-01-16 08:29:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2274, '更新收发件规则状态', 2268, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:rule:update:status', '#', 'admin', '2024-01-16 08:29:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2275, '黑名单', 2002, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:31:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2276, '黑名单列表', 2275, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:blacklist:list', '#', 'admin', '2024-01-16 08:31:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2277, '新增黑名单', 2275, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:blacklist:add', '#', 'admin', '2024-01-16 08:31:40', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2278, '删除黑名单', 2275, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:blacklist:delete', '#', 'admin', '2024-01-16 08:31:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2279, '导入邮件', 2002, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:33:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2280, '邮箱任务列表', 2279, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:import:task:list', '#', 'admin', '2024-01-16 08:33:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2281, '文件夹列表', 2279, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:import:folder:list', '#', 'admin', '2024-01-16 08:33:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2282, '上传', 2279, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:import:upload', '#', 'admin', '2024-01-16 08:34:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2283, '导入邮件列表（分页）', 2279, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:import:page', '#', 'admin', '2024-01-16 08:34:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2284, '其他配置', 2002, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', NULL, '#', 'admin', '2024-01-16 08:38:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2285, '获取其他配置详细信息', 2284, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:config:get:info', '#', 'admin', '2024-01-16 08:39:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2286, '修改其他配置', 2284, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:config:edit', '#', 'admin', '2024-01-16 08:39:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2287, '客户来源列表2', 2236, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:source:list', '#', 'admin', '2024-01-22 17:01:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2288, '客户阶段列表2', 2236, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:stage:list', '#', 'admin', '2024-01-22 17:02:58', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2289, '移入公海原因列表2', 2236, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', 'customer:public:leads:reason:list', '#', 'admin', '2024-01-22 17:04:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2290, '往来邮件列表', 2211, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:dealing:email:list', '#', 'admin', '2024-02-01 08:14:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2291, '邮件内容翻译', 2211, 23, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:translate', '#', 'admin', '2024-02-01 08:14:56', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', 0xE696B0E78988E69CACE58685E5AEB9, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', 0xE7BBB4E68AA4E58685E5AEB9, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int(1) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  `cost_time` bigint(20) NULL DEFAULT 0 COMMENT '消耗时间',
  PRIMARY KEY (`oper_id`) USING BTREE,
  INDEX `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
  INDEX `idx_sys_oper_log_s`(`status`) USING BTREE,
  INDEX `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2023-07-31 13:30:09', '', NULL, '');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', 1, 1, '0', '0', 'admin', '2023-07-31 13:30:09', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', 1, 1, '0', '0', 'admin', '2023-07-31 13:30:09', 'admin', '2024-01-16 10:39:40', '普通角色');
INSERT INTO `sys_role` VALUES (3, '角色测试', 'test', 3, '4', 1, 1, '0', '0', 'admin', '2023-11-23 15:55:59', 'admin', '2023-12-07 13:58:01', NULL);

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 117);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 2002);
INSERT INTO `sys_role_menu` VALUES (2, 2019);
INSERT INTO `sys_role_menu` VALUES (2, 2020);
INSERT INTO `sys_role_menu` VALUES (2, 2021);
INSERT INTO `sys_role_menu` VALUES (2, 2022);
INSERT INTO `sys_role_menu` VALUES (2, 2035);
INSERT INTO `sys_role_menu` VALUES (2, 2036);
INSERT INTO `sys_role_menu` VALUES (2, 2039);
INSERT INTO `sys_role_menu` VALUES (2, 2040);
INSERT INTO `sys_role_menu` VALUES (2, 2041);
INSERT INTO `sys_role_menu` VALUES (2, 2042);
INSERT INTO `sys_role_menu` VALUES (2, 2043);
INSERT INTO `sys_role_menu` VALUES (2, 2044);
INSERT INTO `sys_role_menu` VALUES (2, 2045);
INSERT INTO `sys_role_menu` VALUES (2, 2046);
INSERT INTO `sys_role_menu` VALUES (2, 2047);
INSERT INTO `sys_role_menu` VALUES (2, 2048);
INSERT INTO `sys_role_menu` VALUES (2, 2049);
INSERT INTO `sys_role_menu` VALUES (2, 2050);
INSERT INTO `sys_role_menu` VALUES (2, 2051);
INSERT INTO `sys_role_menu` VALUES (2, 2052);
INSERT INTO `sys_role_menu` VALUES (2, 2053);
INSERT INTO `sys_role_menu` VALUES (2, 2054);
INSERT INTO `sys_role_menu` VALUES (2, 2055);
INSERT INTO `sys_role_menu` VALUES (2, 2056);
INSERT INTO `sys_role_menu` VALUES (2, 2057);
INSERT INTO `sys_role_menu` VALUES (2, 2058);
INSERT INTO `sys_role_menu` VALUES (2, 2059);
INSERT INTO `sys_role_menu` VALUES (2, 2060);
INSERT INTO `sys_role_menu` VALUES (2, 2062);
INSERT INTO `sys_role_menu` VALUES (2, 2063);
INSERT INTO `sys_role_menu` VALUES (2, 2064);
INSERT INTO `sys_role_menu` VALUES (2, 2065);
INSERT INTO `sys_role_menu` VALUES (2, 2066);
INSERT INTO `sys_role_menu` VALUES (2, 2067);
INSERT INTO `sys_role_menu` VALUES (2, 2068);
INSERT INTO `sys_role_menu` VALUES (2, 2069);
INSERT INTO `sys_role_menu` VALUES (2, 2070);
INSERT INTO `sys_role_menu` VALUES (2, 2071);
INSERT INTO `sys_role_menu` VALUES (2, 2072);
INSERT INTO `sys_role_menu` VALUES (2, 2073);
INSERT INTO `sys_role_menu` VALUES (2, 2074);
INSERT INTO `sys_role_menu` VALUES (2, 2075);
INSERT INTO `sys_role_menu` VALUES (2, 2076);
INSERT INTO `sys_role_menu` VALUES (2, 2077);
INSERT INTO `sys_role_menu` VALUES (2, 2078);
INSERT INTO `sys_role_menu` VALUES (2, 2079);
INSERT INTO `sys_role_menu` VALUES (2, 2080);
INSERT INTO `sys_role_menu` VALUES (2, 2081);
INSERT INTO `sys_role_menu` VALUES (2, 2082);
INSERT INTO `sys_role_menu` VALUES (2, 2083);
INSERT INTO `sys_role_menu` VALUES (2, 2084);
INSERT INTO `sys_role_menu` VALUES (2, 2085);
INSERT INTO `sys_role_menu` VALUES (2, 2086);
INSERT INTO `sys_role_menu` VALUES (2, 2087);
INSERT INTO `sys_role_menu` VALUES (2, 2088);
INSERT INTO `sys_role_menu` VALUES (2, 2089);
INSERT INTO `sys_role_menu` VALUES (2, 2090);
INSERT INTO `sys_role_menu` VALUES (2, 2091);
INSERT INTO `sys_role_menu` VALUES (2, 2092);
INSERT INTO `sys_role_menu` VALUES (2, 2093);
INSERT INTO `sys_role_menu` VALUES (2, 2094);
INSERT INTO `sys_role_menu` VALUES (2, 2095);
INSERT INTO `sys_role_menu` VALUES (2, 2096);
INSERT INTO `sys_role_menu` VALUES (2, 2097);
INSERT INTO `sys_role_menu` VALUES (2, 2098);
INSERT INTO `sys_role_menu` VALUES (2, 2099);
INSERT INTO `sys_role_menu` VALUES (2, 2100);
INSERT INTO `sys_role_menu` VALUES (2, 2101);
INSERT INTO `sys_role_menu` VALUES (2, 2102);
INSERT INTO `sys_role_menu` VALUES (2, 2103);
INSERT INTO `sys_role_menu` VALUES (2, 2104);
INSERT INTO `sys_role_menu` VALUES (2, 2105);
INSERT INTO `sys_role_menu` VALUES (2, 2106);
INSERT INTO `sys_role_menu` VALUES (2, 2107);
INSERT INTO `sys_role_menu` VALUES (2, 2108);
INSERT INTO `sys_role_menu` VALUES (2, 2109);
INSERT INTO `sys_role_menu` VALUES (2, 2110);
INSERT INTO `sys_role_menu` VALUES (2, 2111);
INSERT INTO `sys_role_menu` VALUES (2, 2112);
INSERT INTO `sys_role_menu` VALUES (2, 2113);
INSERT INTO `sys_role_menu` VALUES (2, 2114);
INSERT INTO `sys_role_menu` VALUES (2, 2115);
INSERT INTO `sys_role_menu` VALUES (2, 2116);
INSERT INTO `sys_role_menu` VALUES (2, 2117);
INSERT INTO `sys_role_menu` VALUES (2, 2118);
INSERT INTO `sys_role_menu` VALUES (2, 2119);
INSERT INTO `sys_role_menu` VALUES (2, 2120);
INSERT INTO `sys_role_menu` VALUES (2, 2121);
INSERT INTO `sys_role_menu` VALUES (2, 2122);
INSERT INTO `sys_role_menu` VALUES (2, 2123);
INSERT INTO `sys_role_menu` VALUES (2, 2124);
INSERT INTO `sys_role_menu` VALUES (2, 2125);
INSERT INTO `sys_role_menu` VALUES (2, 2126);
INSERT INTO `sys_role_menu` VALUES (2, 2127);
INSERT INTO `sys_role_menu` VALUES (2, 2128);
INSERT INTO `sys_role_menu` VALUES (2, 2129);
INSERT INTO `sys_role_menu` VALUES (2, 2130);
INSERT INTO `sys_role_menu` VALUES (2, 2131);
INSERT INTO `sys_role_menu` VALUES (2, 2132);
INSERT INTO `sys_role_menu` VALUES (2, 2133);
INSERT INTO `sys_role_menu` VALUES (2, 2134);
INSERT INTO `sys_role_menu` VALUES (2, 2135);
INSERT INTO `sys_role_menu` VALUES (2, 2136);
INSERT INTO `sys_role_menu` VALUES (2, 2137);
INSERT INTO `sys_role_menu` VALUES (2, 2138);
INSERT INTO `sys_role_menu` VALUES (2, 2139);
INSERT INTO `sys_role_menu` VALUES (2, 2140);
INSERT INTO `sys_role_menu` VALUES (2, 2141);
INSERT INTO `sys_role_menu` VALUES (2, 2142);
INSERT INTO `sys_role_menu` VALUES (2, 2143);
INSERT INTO `sys_role_menu` VALUES (2, 2144);
INSERT INTO `sys_role_menu` VALUES (2, 2145);
INSERT INTO `sys_role_menu` VALUES (2, 2146);
INSERT INTO `sys_role_menu` VALUES (2, 2147);
INSERT INTO `sys_role_menu` VALUES (2, 2148);
INSERT INTO `sys_role_menu` VALUES (2, 2149);
INSERT INTO `sys_role_menu` VALUES (2, 2150);
INSERT INTO `sys_role_menu` VALUES (2, 2151);
INSERT INTO `sys_role_menu` VALUES (2, 2152);
INSERT INTO `sys_role_menu` VALUES (2, 2153);
INSERT INTO `sys_role_menu` VALUES (2, 2154);
INSERT INTO `sys_role_menu` VALUES (2, 2155);
INSERT INTO `sys_role_menu` VALUES (2, 2156);
INSERT INTO `sys_role_menu` VALUES (2, 2157);
INSERT INTO `sys_role_menu` VALUES (2, 2158);
INSERT INTO `sys_role_menu` VALUES (2, 2159);
INSERT INTO `sys_role_menu` VALUES (2, 2160);
INSERT INTO `sys_role_menu` VALUES (2, 2161);
INSERT INTO `sys_role_menu` VALUES (2, 2162);
INSERT INTO `sys_role_menu` VALUES (2, 2163);
INSERT INTO `sys_role_menu` VALUES (2, 2164);
INSERT INTO `sys_role_menu` VALUES (2, 2165);
INSERT INTO `sys_role_menu` VALUES (2, 2166);
INSERT INTO `sys_role_menu` VALUES (2, 2167);
INSERT INTO `sys_role_menu` VALUES (2, 2168);
INSERT INTO `sys_role_menu` VALUES (2, 2169);
INSERT INTO `sys_role_menu` VALUES (2, 2170);
INSERT INTO `sys_role_menu` VALUES (2, 2171);
INSERT INTO `sys_role_menu` VALUES (2, 2172);
INSERT INTO `sys_role_menu` VALUES (2, 2173);
INSERT INTO `sys_role_menu` VALUES (2, 2174);
INSERT INTO `sys_role_menu` VALUES (2, 2175);
INSERT INTO `sys_role_menu` VALUES (2, 2176);
INSERT INTO `sys_role_menu` VALUES (2, 2177);
INSERT INTO `sys_role_menu` VALUES (2, 2178);
INSERT INTO `sys_role_menu` VALUES (2, 2179);
INSERT INTO `sys_role_menu` VALUES (2, 2180);
INSERT INTO `sys_role_menu` VALUES (2, 2181);
INSERT INTO `sys_role_menu` VALUES (2, 2182);
INSERT INTO `sys_role_menu` VALUES (2, 2183);
INSERT INTO `sys_role_menu` VALUES (2, 2184);
INSERT INTO `sys_role_menu` VALUES (2, 2185);
INSERT INTO `sys_role_menu` VALUES (2, 2186);
INSERT INTO `sys_role_menu` VALUES (2, 2187);
INSERT INTO `sys_role_menu` VALUES (2, 2188);
INSERT INTO `sys_role_menu` VALUES (2, 2189);
INSERT INTO `sys_role_menu` VALUES (2, 2190);
INSERT INTO `sys_role_menu` VALUES (2, 2191);
INSERT INTO `sys_role_menu` VALUES (2, 2192);
INSERT INTO `sys_role_menu` VALUES (2, 2193);
INSERT INTO `sys_role_menu` VALUES (2, 2194);
INSERT INTO `sys_role_menu` VALUES (2, 2195);
INSERT INTO `sys_role_menu` VALUES (2, 2196);
INSERT INTO `sys_role_menu` VALUES (2, 2197);
INSERT INTO `sys_role_menu` VALUES (2, 2198);
INSERT INTO `sys_role_menu` VALUES (2, 2199);
INSERT INTO `sys_role_menu` VALUES (2, 2200);
INSERT INTO `sys_role_menu` VALUES (2, 2201);
INSERT INTO `sys_role_menu` VALUES (2, 2202);
INSERT INTO `sys_role_menu` VALUES (2, 2203);
INSERT INTO `sys_role_menu` VALUES (2, 2204);
INSERT INTO `sys_role_menu` VALUES (2, 2205);
INSERT INTO `sys_role_menu` VALUES (2, 2206);
INSERT INTO `sys_role_menu` VALUES (2, 2207);
INSERT INTO `sys_role_menu` VALUES (2, 2208);
INSERT INTO `sys_role_menu` VALUES (2, 2209);
INSERT INTO `sys_role_menu` VALUES (2, 2210);
INSERT INTO `sys_role_menu` VALUES (2, 2211);
INSERT INTO `sys_role_menu` VALUES (2, 2212);
INSERT INTO `sys_role_menu` VALUES (2, 2213);
INSERT INTO `sys_role_menu` VALUES (2, 2214);
INSERT INTO `sys_role_menu` VALUES (2, 2215);
INSERT INTO `sys_role_menu` VALUES (2, 2216);
INSERT INTO `sys_role_menu` VALUES (2, 2217);
INSERT INTO `sys_role_menu` VALUES (2, 2218);
INSERT INTO `sys_role_menu` VALUES (2, 2219);
INSERT INTO `sys_role_menu` VALUES (2, 2220);
INSERT INTO `sys_role_menu` VALUES (2, 2221);
INSERT INTO `sys_role_menu` VALUES (2, 2222);
INSERT INTO `sys_role_menu` VALUES (2, 2223);
INSERT INTO `sys_role_menu` VALUES (2, 2224);
INSERT INTO `sys_role_menu` VALUES (2, 2225);
INSERT INTO `sys_role_menu` VALUES (2, 2226);
INSERT INTO `sys_role_menu` VALUES (2, 2227);
INSERT INTO `sys_role_menu` VALUES (2, 2228);
INSERT INTO `sys_role_menu` VALUES (2, 2229);
INSERT INTO `sys_role_menu` VALUES (2, 2230);
INSERT INTO `sys_role_menu` VALUES (2, 2231);
INSERT INTO `sys_role_menu` VALUES (2, 2232);
INSERT INTO `sys_role_menu` VALUES (2, 2233);
INSERT INTO `sys_role_menu` VALUES (2, 2234);
INSERT INTO `sys_role_menu` VALUES (2, 2235);
INSERT INTO `sys_role_menu` VALUES (2, 2236);
INSERT INTO `sys_role_menu` VALUES (2, 2237);
INSERT INTO `sys_role_menu` VALUES (2, 2238);
INSERT INTO `sys_role_menu` VALUES (2, 2239);
INSERT INTO `sys_role_menu` VALUES (2, 2240);
INSERT INTO `sys_role_menu` VALUES (2, 2241);
INSERT INTO `sys_role_menu` VALUES (2, 2242);
INSERT INTO `sys_role_menu` VALUES (2, 2243);
INSERT INTO `sys_role_menu` VALUES (2, 2244);
INSERT INTO `sys_role_menu` VALUES (2, 2245);
INSERT INTO `sys_role_menu` VALUES (2, 2246);
INSERT INTO `sys_role_menu` VALUES (2, 2247);
INSERT INTO `sys_role_menu` VALUES (2, 2248);
INSERT INTO `sys_role_menu` VALUES (2, 2249);
INSERT INTO `sys_role_menu` VALUES (2, 2250);
INSERT INTO `sys_role_menu` VALUES (2, 2251);
INSERT INTO `sys_role_menu` VALUES (2, 2252);
INSERT INTO `sys_role_menu` VALUES (2, 2253);
INSERT INTO `sys_role_menu` VALUES (2, 2254);
INSERT INTO `sys_role_menu` VALUES (2, 2255);
INSERT INTO `sys_role_menu` VALUES (2, 2256);
INSERT INTO `sys_role_menu` VALUES (2, 2257);
INSERT INTO `sys_role_menu` VALUES (2, 2258);
INSERT INTO `sys_role_menu` VALUES (2, 2259);
INSERT INTO `sys_role_menu` VALUES (2, 2260);
INSERT INTO `sys_role_menu` VALUES (2, 2261);
INSERT INTO `sys_role_menu` VALUES (2, 2262);
INSERT INTO `sys_role_menu` VALUES (2, 2263);
INSERT INTO `sys_role_menu` VALUES (2, 2264);
INSERT INTO `sys_role_menu` VALUES (2, 2265);
INSERT INTO `sys_role_menu` VALUES (2, 2266);
INSERT INTO `sys_role_menu` VALUES (2, 2267);
INSERT INTO `sys_role_menu` VALUES (2, 2268);
INSERT INTO `sys_role_menu` VALUES (2, 2269);
INSERT INTO `sys_role_menu` VALUES (2, 2270);
INSERT INTO `sys_role_menu` VALUES (2, 2271);
INSERT INTO `sys_role_menu` VALUES (2, 2272);
INSERT INTO `sys_role_menu` VALUES (2, 2273);
INSERT INTO `sys_role_menu` VALUES (2, 2274);
INSERT INTO `sys_role_menu` VALUES (2, 2275);
INSERT INTO `sys_role_menu` VALUES (2, 2276);
INSERT INTO `sys_role_menu` VALUES (2, 2277);
INSERT INTO `sys_role_menu` VALUES (2, 2278);
INSERT INTO `sys_role_menu` VALUES (2, 2279);
INSERT INTO `sys_role_menu` VALUES (2, 2280);
INSERT INTO `sys_role_menu` VALUES (2, 2281);
INSERT INTO `sys_role_menu` VALUES (2, 2282);
INSERT INTO `sys_role_menu` VALUES (2, 2283);
INSERT INTO `sys_role_menu` VALUES (2, 2284);
INSERT INTO `sys_role_menu` VALUES (2, 2285);
INSERT INTO `sys_role_menu` VALUES (2, 2286);
INSERT INTO `sys_role_menu` VALUES (3, 1);
INSERT INTO `sys_role_menu` VALUES (3, 2);
INSERT INTO `sys_role_menu` VALUES (3, 3);
INSERT INTO `sys_role_menu` VALUES (3, 4);
INSERT INTO `sys_role_menu` VALUES (3, 100);
INSERT INTO `sys_role_menu` VALUES (3, 101);
INSERT INTO `sys_role_menu` VALUES (3, 102);
INSERT INTO `sys_role_menu` VALUES (3, 103);
INSERT INTO `sys_role_menu` VALUES (3, 104);
INSERT INTO `sys_role_menu` VALUES (3, 105);
INSERT INTO `sys_role_menu` VALUES (3, 106);
INSERT INTO `sys_role_menu` VALUES (3, 107);
INSERT INTO `sys_role_menu` VALUES (3, 108);
INSERT INTO `sys_role_menu` VALUES (3, 109);
INSERT INTO `sys_role_menu` VALUES (3, 110);
INSERT INTO `sys_role_menu` VALUES (3, 111);
INSERT INTO `sys_role_menu` VALUES (3, 112);
INSERT INTO `sys_role_menu` VALUES (3, 113);
INSERT INTO `sys_role_menu` VALUES (3, 114);
INSERT INTO `sys_role_menu` VALUES (3, 115);
INSERT INTO `sys_role_menu` VALUES (3, 116);
INSERT INTO `sys_role_menu` VALUES (3, 117);
INSERT INTO `sys_role_menu` VALUES (3, 500);
INSERT INTO `sys_role_menu` VALUES (3, 501);
INSERT INTO `sys_role_menu` VALUES (3, 1000);
INSERT INTO `sys_role_menu` VALUES (3, 1001);
INSERT INTO `sys_role_menu` VALUES (3, 1002);
INSERT INTO `sys_role_menu` VALUES (3, 1003);
INSERT INTO `sys_role_menu` VALUES (3, 1004);
INSERT INTO `sys_role_menu` VALUES (3, 1005);
INSERT INTO `sys_role_menu` VALUES (3, 1006);
INSERT INTO `sys_role_menu` VALUES (3, 1007);
INSERT INTO `sys_role_menu` VALUES (3, 1008);
INSERT INTO `sys_role_menu` VALUES (3, 1009);
INSERT INTO `sys_role_menu` VALUES (3, 1010);
INSERT INTO `sys_role_menu` VALUES (3, 1011);
INSERT INTO `sys_role_menu` VALUES (3, 1012);
INSERT INTO `sys_role_menu` VALUES (3, 1013);
INSERT INTO `sys_role_menu` VALUES (3, 1014);
INSERT INTO `sys_role_menu` VALUES (3, 1015);
INSERT INTO `sys_role_menu` VALUES (3, 1016);
INSERT INTO `sys_role_menu` VALUES (3, 1017);
INSERT INTO `sys_role_menu` VALUES (3, 1018);
INSERT INTO `sys_role_menu` VALUES (3, 1019);
INSERT INTO `sys_role_menu` VALUES (3, 1020);
INSERT INTO `sys_role_menu` VALUES (3, 1021);
INSERT INTO `sys_role_menu` VALUES (3, 1022);
INSERT INTO `sys_role_menu` VALUES (3, 1023);
INSERT INTO `sys_role_menu` VALUES (3, 1024);
INSERT INTO `sys_role_menu` VALUES (3, 1025);
INSERT INTO `sys_role_menu` VALUES (3, 1026);
INSERT INTO `sys_role_menu` VALUES (3, 1027);
INSERT INTO `sys_role_menu` VALUES (3, 1028);
INSERT INTO `sys_role_menu` VALUES (3, 1029);
INSERT INTO `sys_role_menu` VALUES (3, 1030);
INSERT INTO `sys_role_menu` VALUES (3, 1031);
INSERT INTO `sys_role_menu` VALUES (3, 1032);
INSERT INTO `sys_role_menu` VALUES (3, 1033);
INSERT INTO `sys_role_menu` VALUES (3, 1034);
INSERT INTO `sys_role_menu` VALUES (3, 1035);
INSERT INTO `sys_role_menu` VALUES (3, 1036);
INSERT INTO `sys_role_menu` VALUES (3, 1037);
INSERT INTO `sys_role_menu` VALUES (3, 1038);
INSERT INTO `sys_role_menu` VALUES (3, 1039);
INSERT INTO `sys_role_menu` VALUES (3, 1040);
INSERT INTO `sys_role_menu` VALUES (3, 1041);
INSERT INTO `sys_role_menu` VALUES (3, 1042);
INSERT INTO `sys_role_menu` VALUES (3, 1043);
INSERT INTO `sys_role_menu` VALUES (3, 1044);
INSERT INTO `sys_role_menu` VALUES (3, 1045);
INSERT INTO `sys_role_menu` VALUES (3, 1046);
INSERT INTO `sys_role_menu` VALUES (3, 1047);
INSERT INTO `sys_role_menu` VALUES (3, 1048);
INSERT INTO `sys_role_menu` VALUES (3, 1049);
INSERT INTO `sys_role_menu` VALUES (3, 1050);
INSERT INTO `sys_role_menu` VALUES (3, 1051);
INSERT INTO `sys_role_menu` VALUES (3, 1052);
INSERT INTO `sys_role_menu` VALUES (3, 1053);
INSERT INTO `sys_role_menu` VALUES (3, 1054);
INSERT INTO `sys_role_menu` VALUES (3, 1055);
INSERT INTO `sys_role_menu` VALUES (3, 1056);
INSERT INTO `sys_role_menu` VALUES (3, 1057);
INSERT INTO `sys_role_menu` VALUES (3, 1058);
INSERT INTO `sys_role_menu` VALUES (3, 1059);
INSERT INTO `sys_role_menu` VALUES (3, 1060);
INSERT INTO `sys_role_menu` VALUES (3, 2002);
INSERT INTO `sys_role_menu` VALUES (3, 2019);
INSERT INTO `sys_role_menu` VALUES (3, 2020);
INSERT INTO `sys_role_menu` VALUES (3, 2021);
INSERT INTO `sys_role_menu` VALUES (3, 2022);
INSERT INTO `sys_role_menu` VALUES (3, 2035);
INSERT INTO `sys_role_menu` VALUES (3, 2036);
INSERT INTO `sys_role_menu` VALUES (3, 2039);
INSERT INTO `sys_role_menu` VALUES (3, 2040);
INSERT INTO `sys_role_menu` VALUES (3, 2041);
INSERT INTO `sys_role_menu` VALUES (3, 2042);
INSERT INTO `sys_role_menu` VALUES (3, 2043);
INSERT INTO `sys_role_menu` VALUES (3, 2044);
INSERT INTO `sys_role_menu` VALUES (3, 2045);
INSERT INTO `sys_role_menu` VALUES (3, 2046);
INSERT INTO `sys_role_menu` VALUES (3, 2047);
INSERT INTO `sys_role_menu` VALUES (3, 2048);
INSERT INTO `sys_role_menu` VALUES (3, 2049);
INSERT INTO `sys_role_menu` VALUES (3, 2050);
INSERT INTO `sys_role_menu` VALUES (3, 2051);
INSERT INTO `sys_role_menu` VALUES (3, 2052);
INSERT INTO `sys_role_menu` VALUES (3, 2053);
INSERT INTO `sys_role_menu` VALUES (3, 2054);
INSERT INTO `sys_role_menu` VALUES (3, 2055);
INSERT INTO `sys_role_menu` VALUES (3, 2056);
INSERT INTO `sys_role_menu` VALUES (3, 2057);
INSERT INTO `sys_role_menu` VALUES (3, 2058);
INSERT INTO `sys_role_menu` VALUES (3, 2059);
INSERT INTO `sys_role_menu` VALUES (3, 2060);
INSERT INTO `sys_role_menu` VALUES (3, 2062);
INSERT INTO `sys_role_menu` VALUES (3, 2063);
INSERT INTO `sys_role_menu` VALUES (3, 2064);
INSERT INTO `sys_role_menu` VALUES (3, 2065);
INSERT INTO `sys_role_menu` VALUES (3, 2066);
INSERT INTO `sys_role_menu` VALUES (3, 2067);
INSERT INTO `sys_role_menu` VALUES (3, 2068);
INSERT INTO `sys_role_menu` VALUES (3, 2069);
INSERT INTO `sys_role_menu` VALUES (3, 2070);
INSERT INTO `sys_role_menu` VALUES (3, 2071);
INSERT INTO `sys_role_menu` VALUES (3, 2072);
INSERT INTO `sys_role_menu` VALUES (3, 2073);
INSERT INTO `sys_role_menu` VALUES (3, 2074);
INSERT INTO `sys_role_menu` VALUES (3, 2075);
INSERT INTO `sys_role_menu` VALUES (3, 2076);
INSERT INTO `sys_role_menu` VALUES (3, 2077);
INSERT INTO `sys_role_menu` VALUES (3, 2078);
INSERT INTO `sys_role_menu` VALUES (3, 2079);
INSERT INTO `sys_role_menu` VALUES (3, 2080);
INSERT INTO `sys_role_menu` VALUES (3, 2081);
INSERT INTO `sys_role_menu` VALUES (3, 2082);
INSERT INTO `sys_role_menu` VALUES (3, 2083);
INSERT INTO `sys_role_menu` VALUES (3, 2084);
INSERT INTO `sys_role_menu` VALUES (3, 2085);
INSERT INTO `sys_role_menu` VALUES (3, 2086);
INSERT INTO `sys_role_menu` VALUES (3, 2087);
INSERT INTO `sys_role_menu` VALUES (3, 2088);
INSERT INTO `sys_role_menu` VALUES (3, 2089);
INSERT INTO `sys_role_menu` VALUES (3, 2090);
INSERT INTO `sys_role_menu` VALUES (3, 2091);
INSERT INTO `sys_role_menu` VALUES (3, 2092);
INSERT INTO `sys_role_menu` VALUES (3, 2093);
INSERT INTO `sys_role_menu` VALUES (3, 2094);
INSERT INTO `sys_role_menu` VALUES (3, 2095);
INSERT INTO `sys_role_menu` VALUES (3, 2096);
INSERT INTO `sys_role_menu` VALUES (3, 2097);
INSERT INTO `sys_role_menu` VALUES (3, 2098);
INSERT INTO `sys_role_menu` VALUES (3, 2099);
INSERT INTO `sys_role_menu` VALUES (3, 2100);
INSERT INTO `sys_role_menu` VALUES (3, 2101);
INSERT INTO `sys_role_menu` VALUES (3, 2102);
INSERT INTO `sys_role_menu` VALUES (3, 2103);
INSERT INTO `sys_role_menu` VALUES (3, 2104);
INSERT INTO `sys_role_menu` VALUES (3, 2105);
INSERT INTO `sys_role_menu` VALUES (3, 2106);
INSERT INTO `sys_role_menu` VALUES (3, 2107);
INSERT INTO `sys_role_menu` VALUES (3, 2108);
INSERT INTO `sys_role_menu` VALUES (3, 2109);
INSERT INTO `sys_role_menu` VALUES (3, 2110);
INSERT INTO `sys_role_menu` VALUES (3, 2111);
INSERT INTO `sys_role_menu` VALUES (3, 2112);
INSERT INTO `sys_role_menu` VALUES (3, 2113);
INSERT INTO `sys_role_menu` VALUES (3, 2114);
INSERT INTO `sys_role_menu` VALUES (3, 2115);
INSERT INTO `sys_role_menu` VALUES (3, 2116);
INSERT INTO `sys_role_menu` VALUES (3, 2117);
INSERT INTO `sys_role_menu` VALUES (3, 2118);
INSERT INTO `sys_role_menu` VALUES (3, 2119);
INSERT INTO `sys_role_menu` VALUES (3, 2120);
INSERT INTO `sys_role_menu` VALUES (3, 2121);
INSERT INTO `sys_role_menu` VALUES (3, 2122);
INSERT INTO `sys_role_menu` VALUES (3, 2123);
INSERT INTO `sys_role_menu` VALUES (3, 2124);
INSERT INTO `sys_role_menu` VALUES (3, 2125);
INSERT INTO `sys_role_menu` VALUES (3, 2126);
INSERT INTO `sys_role_menu` VALUES (3, 2127);
INSERT INTO `sys_role_menu` VALUES (3, 2128);
INSERT INTO `sys_role_menu` VALUES (3, 2129);
INSERT INTO `sys_role_menu` VALUES (3, 2130);
INSERT INTO `sys_role_menu` VALUES (3, 2131);
INSERT INTO `sys_role_menu` VALUES (3, 2132);
INSERT INTO `sys_role_menu` VALUES (3, 2133);
INSERT INTO `sys_role_menu` VALUES (3, 2134);
INSERT INTO `sys_role_menu` VALUES (3, 2135);
INSERT INTO `sys_role_menu` VALUES (3, 2136);
INSERT INTO `sys_role_menu` VALUES (3, 2137);
INSERT INTO `sys_role_menu` VALUES (3, 2138);
INSERT INTO `sys_role_menu` VALUES (3, 2139);
INSERT INTO `sys_role_menu` VALUES (3, 2140);
INSERT INTO `sys_role_menu` VALUES (3, 2141);
INSERT INTO `sys_role_menu` VALUES (3, 2142);
INSERT INTO `sys_role_menu` VALUES (3, 2143);
INSERT INTO `sys_role_menu` VALUES (3, 2144);
INSERT INTO `sys_role_menu` VALUES (3, 2145);
INSERT INTO `sys_role_menu` VALUES (3, 2146);
INSERT INTO `sys_role_menu` VALUES (3, 2147);
INSERT INTO `sys_role_menu` VALUES (3, 2148);
INSERT INTO `sys_role_menu` VALUES (3, 2149);
INSERT INTO `sys_role_menu` VALUES (3, 2150);
INSERT INTO `sys_role_menu` VALUES (3, 2151);
INSERT INTO `sys_role_menu` VALUES (3, 2152);
INSERT INTO `sys_role_menu` VALUES (3, 2153);
INSERT INTO `sys_role_menu` VALUES (3, 2154);
INSERT INTO `sys_role_menu` VALUES (3, 2155);
INSERT INTO `sys_role_menu` VALUES (3, 2156);
INSERT INTO `sys_role_menu` VALUES (3, 2157);
INSERT INTO `sys_role_menu` VALUES (3, 2158);
INSERT INTO `sys_role_menu` VALUES (3, 2159);
INSERT INTO `sys_role_menu` VALUES (3, 2160);
INSERT INTO `sys_role_menu` VALUES (3, 2183);
INSERT INTO `sys_role_menu` VALUES (3, 2184);
INSERT INTO `sys_role_menu` VALUES (3, 2185);
INSERT INTO `sys_role_menu` VALUES (3, 2186);
INSERT INTO `sys_role_menu` VALUES (3, 2187);
INSERT INTO `sys_role_menu` VALUES (3, 2188);
INSERT INTO `sys_role_menu` VALUES (3, 2189);
INSERT INTO `sys_role_menu` VALUES (3, 2190);
INSERT INTO `sys_role_menu` VALUES (3, 2191);
INSERT INTO `sys_role_menu` VALUES (3, 2192);
INSERT INTO `sys_role_menu` VALUES (3, 2193);
INSERT INTO `sys_role_menu` VALUES (3, 2194);
INSERT INTO `sys_role_menu` VALUES (3, 2195);
INSERT INTO `sys_role_menu` VALUES (3, 2196);
INSERT INTO `sys_role_menu` VALUES (3, 2197);
INSERT INTO `sys_role_menu` VALUES (3, 2198);
INSERT INTO `sys_role_menu` VALUES (3, 2199);
INSERT INTO `sys_role_menu` VALUES (3, 2200);
INSERT INTO `sys_role_menu` VALUES (3, 2201);
INSERT INTO `sys_role_menu` VALUES (3, 2202);
INSERT INTO `sys_role_menu` VALUES (3, 2203);
INSERT INTO `sys_role_menu` VALUES (3, 2204);
INSERT INTO `sys_role_menu` VALUES (3, 2205);
INSERT INTO `sys_role_menu` VALUES (3, 2206);
INSERT INTO `sys_role_menu` VALUES (3, 2207);
INSERT INTO `sys_role_menu` VALUES (3, 2208);
INSERT INTO `sys_role_menu` VALUES (3, 2209);
INSERT INTO `sys_role_menu` VALUES (3, 2210);
INSERT INTO `sys_role_menu` VALUES (3, 2211);
INSERT INTO `sys_role_menu` VALUES (3, 2212);
INSERT INTO `sys_role_menu` VALUES (3, 2213);
INSERT INTO `sys_role_menu` VALUES (3, 2214);
INSERT INTO `sys_role_menu` VALUES (3, 2215);
INSERT INTO `sys_role_menu` VALUES (3, 2216);
INSERT INTO `sys_role_menu` VALUES (3, 2217);
INSERT INTO `sys_role_menu` VALUES (3, 2218);
INSERT INTO `sys_role_menu` VALUES (3, 2219);
INSERT INTO `sys_role_menu` VALUES (3, 2220);
INSERT INTO `sys_role_menu` VALUES (3, 2221);
INSERT INTO `sys_role_menu` VALUES (3, 2222);
INSERT INTO `sys_role_menu` VALUES (3, 2223);
INSERT INTO `sys_role_menu` VALUES (3, 2224);
INSERT INTO `sys_role_menu` VALUES (3, 2225);
INSERT INTO `sys_role_menu` VALUES (3, 2226);
INSERT INTO `sys_role_menu` VALUES (3, 2227);
INSERT INTO `sys_role_menu` VALUES (3, 2228);
INSERT INTO `sys_role_menu` VALUES (3, 2229);
INSERT INTO `sys_role_menu` VALUES (3, 2230);
INSERT INTO `sys_role_menu` VALUES (3, 2231);
INSERT INTO `sys_role_menu` VALUES (3, 2232);
INSERT INTO `sys_role_menu` VALUES (3, 2233);
INSERT INTO `sys_role_menu` VALUES (3, 2234);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint(20) NULL DEFAULT NULL COMMENT '部门ID',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `nick_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2024-02-19 08:41:50', 'admin', '2023-07-31 13:30:09', '', '2024-02-19 08:41:50', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', '0', '0', '127.0.0.1', '2023-10-11 17:02:20', 'admin', '2023-07-31 13:30:09', '', '2023-10-11 17:02:20', '测试员');
INSERT INTO `sys_user` VALUES (3, 103, 'zhangsan', '张三', '00', '', '', '0', '', '$2a$10$NSwAgerUG49RhiRVPnY36.XSMwCJEZ4vARRFjzo4MkF03WCWV679G', '0', '0', '127.0.0.1', '2024-02-19 08:42:25', 'admin', '2023-11-24 15:36:09', '', '2024-02-19 08:42:25', NULL);
INSERT INTO `sys_user` VALUES (4, 108, 'lisi', '李四', '00', 'lisi@qq.com', '', '0', '', '$2a$10$FVHJbhW.58uulyMe3v5Qju6WdCnemzIynCy9tC7CMqasrcaoH72ee', '0', '0', '127.0.0.1', '2024-01-16 10:40:11', 'admin', '2023-11-24 16:20:09', '', '2024-01-16 10:40:11', NULL);
INSERT INTO `sys_user` VALUES (5, 103, 'wangwu', '王五', '00', 'wangwu@qq.com', '', '0', '', '$2a$10$..bkISEQcoaCHN0qKwuoye35VJVYgdgXzw.27QMdS7x.ZmFyv6Ah.', '0', '0', '127.0.0.1', '2023-12-07 14:29:41', 'admin', '2023-11-24 16:20:43', 'admin', '2023-12-07 14:29:41', NULL);
INSERT INTO `sys_user` VALUES (6, 110, 'zhaoliu', '赵六', '00', 'zhaoliu@163.com', '', '0', '', '$2a$10$xTUFh7aBG85LVA107mlZneGUJwY.josM9Pf7KOO9RHp9pGAbGSfx2', '0', '0', '', NULL, 'admin', '2023-11-24 17:10:16', '', NULL, NULL);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (3, 2);
INSERT INTO `sys_user_role` VALUES (4, 3);
INSERT INTO `sys_user_role` VALUES (5, 3);
INSERT INTO `sys_user_role` VALUES (6, 2);

SET FOREIGN_KEY_CHECKS = 1;
