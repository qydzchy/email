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

 Date: 27/11/2023 13:36:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2182 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_menu` VALUES (2003, '邮件列表-（首页）', 2002, 1, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:list', '#', 'admin', '2023-10-11 16:26:53', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '邮件保存-（写信）', 2002, 2, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:save', '#', 'admin', '2023-10-11 16:27:15', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '邮件发送-（写信）', 2002, 3, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send', '#', 'admin', '2023-10-11 16:27:33', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '邮件固定', 2002, 4, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:fixed', '#', 'admin', '2023-10-11 16:28:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '邮件删除', 2002, 5, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:delete', '#', 'admin', '2023-10-11 16:28:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2008, '快速回复', 2002, 6, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:send:quick:reply', '#', 'admin', '2023-10-11 16:33:08', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '标记为已读邮件', 2002, 7, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:read', '#', 'admin', '2023-10-11 16:33:53', 'admin', '2023-10-11 16:34:40', '');
INSERT INTO `sys_menu` VALUES (2010, '标记为垃圾邮件', 2002, 8, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:spam', '#', 'admin', '2023-10-11 16:34:28', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '移动邮件到文件夹', 2002, 9, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:folder', '#', 'admin', '2023-10-11 16:36:38', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '移动邮件到标签', 2002, 10, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:move:label', '#', 'admin', '2023-10-11 16:37:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '标记待处理', 2002, 11, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:pending', '#', 'admin', '2023-10-11 16:37:32', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2014, '邮件导出', 2002, 12, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:export', '#', 'admin', '2023-10-11 16:38:12', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2015, '附件上传', 2002, 13, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:upload', '#', 'admin', '2023-10-11 16:40:19', 'admin', '2023-10-11 16:45:30', '');
INSERT INTO `sys_menu` VALUES (2016, '附件下载', 2002, 14, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:download', '#', 'admin', '2023-10-11 16:40:41', 'admin', '2023-10-11 16:45:36', '');
INSERT INTO `sys_menu` VALUES (2017, '附件重命名', 2002, 15, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:rename', '#', 'admin', '2023-10-11 16:44:23', 'admin', '2023-10-11 16:45:44', '');
INSERT INTO `sys_menu` VALUES (2018, '附件删除', 2002, 16, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:attachment:delete', '#', 'admin', '2023-10-11 16:45:03', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2019, '文件夹列表', 2002, 17, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:list', '#', 'admin', '2023-10-11 16:46:24', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2020, '文件夹新增', 2002, 18, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:add', '#', 'admin', '2023-10-11 16:46:47', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2021, '文件夹修改', 2002, 19, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:edit', '#', 'admin', '2023-10-11 16:47:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2022, '文件夹删除', 2002, 20, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:folder:delete', '#', 'admin', '2023-10-11 16:47:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2023, '标签列表', 2002, 21, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:list', '#', 'admin', '2023-10-11 16:48:09', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2024, '标签新增', 2002, 22, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:add', '#', 'admin', '2023-10-11 16:48:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2025, '标签颜色修改', 2002, 23, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:color', '#', 'admin', '2023-10-11 16:48:51', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2026, '标签名称修改', 2002, 24, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:edit:name', '#', 'admin', '2023-10-11 16:49:18', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2027, '标签删除', 2002, 25, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:label:delete', '#', 'admin', '2023-10-11 16:49:39', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2028, '邮箱列表', 2002, 26, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:list', '#', 'admin', '2023-10-11 16:50:10', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2029, '收件箱列表', 2002, 27, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:pull:list', '#', 'admin', '2023-10-11 16:54:44', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2030, '发件箱列表', 2002, 28, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:send:list', '#', 'admin', '2023-10-11 16:55:05', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2031, '邮箱新增', 2002, 29, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:add', '#', 'admin', '2023-10-11 16:55:29', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2032, '邮箱检测', 2002, 30, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:test', '#', 'admin', '2023-10-11 16:56:59', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2033, '邮箱修改', 2002, 31, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:edit', '#', 'admin', '2023-10-11 16:57:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2034, '邮箱解绑', 2002, 32, '', NULL, NULL, 1, 0, 'F', '0', '0', 'email:task:unbind', '#', 'admin', '2023-10-11 16:57:46', '', NULL, '');
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

SET FOREIGN_KEY_CHECKS = 1;
