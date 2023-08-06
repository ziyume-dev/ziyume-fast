/*
 Navicat Premium Data Transfer

 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 101102 (10.11.2-MariaDB-1:10.11.2+maria~ubu2204)
 Source Host           : localhost:3306
 Source Schema         : heming-fast

 Target Server Type    : MySQL
 Target Server Version : 101102 (10.11.2-MariaDB-1:10.11.2+maria~ubu2204)
 File Encoding         : 65001

 Date: 06/08/2023 21:20:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for heming_menu
-- ----------------------------
DROP TABLE IF EXISTS `heming_menu`;
CREATE TABLE `heming_menu`  (
  `id` bigint NOT NULL,
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级 id',
  `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '前端名称',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由地址',
  `redirect` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '重定向地址',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组件路径',
  `icon` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '菜单图标名称',
  `disabled` tinyint NULL DEFAULT NULL COMMENT '禁用状态：0->禁用；1->启用',
  `hidden` tinyint NULL DEFAULT NULL COMMENT '菜单显示状态：0->禁用；1->启用',
  `keep_alive` tinyint NULL DEFAULT NULL COMMENT '是否缓存：0->否；1->是',
  `always_show` tinyint NULL DEFAULT NULL COMMENT '取消自动计算根路由模式：0->否；1->是',
  `is_root` tinyint NULL DEFAULT NULL COMMENT '是否跟路由：0->否；1->是',
  `frame_src` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内联外部地址',
  `affix` tinyint NULL DEFAULT NULL COMMENT '是否固定：0->否；1->是',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_menu
-- ----------------------------
INSERT INTO `heming_menu` VALUES (5, 0, '系统管理', 'system', '/system', '/system/user/index', 'Layout', 'AppstoreOutlined', 0, 0, 1, 0, 1, NULL, 0, 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (6, 5, '用户管理', 'user', 'user', '', '/system/user/index', 'TeamOutlined', 0, 0, 1, 0, 0, NULL, 0, 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (7, 5, '菜单管理', 'menu', 'menu', NULL, '/system/menu/index', 'MenuOutlined', 0, 0, 1, 0, 0, NULL, 0, 6, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (8, 5, '资源管理', 'resource', 'resource', NULL, '/system/resource/index', 'TagOutlined', 0, 0, 1, 0, 0, NULL, 0, 7, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (10, 5, '资源类别管理', 'resource_type', 'resourceType', NULL, '/system/resourceType/index', 'TagsOutlined', 0, 0, 1, 0, 0, NULL, 0, 8, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (11, 5, '角色管理', 'role', 'role', NULL, '/system/role/index', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 5, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (12, 0, 'Dashboard', 'Dashboard', '/dashboard', '/dashboard/index', 'Layout', 'DashboardOutlined', 0, 0, 1, 0, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (13, 12, '控制台', 'console', 'index', NULL, '/dashboard/index', 'DashboardOutlined', 0, 0, 1, 0, 0, NULL, 0, 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (14, 0, '设置', 'setting', '/setting', '/setting/person/index', 'Layout', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 9, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_menu` VALUES (15, 14, '个人设置', 'setting_person', 'person', NULL, '/setting/person/index', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 10, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_resource
-- ----------------------------
DROP TABLE IF EXISTS `heming_resource`;
CREATE TABLE `heming_resource`  (
  `id` bigint NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '资源描述',
  `type_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源类型 code',
  `route_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '路由分配 key',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_resource
-- ----------------------------
INSERT INTO `heming_resource` VALUES (2, '获取用户信息', '/user/info', '获取用户信息接口', '1', '1', 0, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (3, '用户分页', '/user/pageList', '获取用户分页列表', '1', '1', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (4, '删除用户', '/user/deleteUser/**', '删除用户', '1', '1', 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (5, '角色分页', '/role/pageList', '获取角色分页列表', '2', '1', 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (6, '资源分页', '/resource/pageList', '获取资源分页列表', '3', '1', 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (7, '资源类别分页', '/resourceType/pageList', '获取资源类别分页列表', '4', '1', 5, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource` VALUES (8, '菜单分页', '/menu/pageList', '获取菜单分页列表', '5', '1', 6, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_resource_type
-- ----------------------------
DROP TABLE IF EXISTS `heming_resource_type`;
CREATE TABLE `heming_resource_type`  (
  `id` bigint NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '排序',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_resource_type
-- ----------------------------
INSERT INTO `heming_resource_type` VALUES (1, '用户管理', 'user', '用户管理分类', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource_type` VALUES (2, '角色管理', 'role', '角色管理分类', 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource_type` VALUES (3, '资源管理', 'resource', '资源管理分类', 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource_type` VALUES (4, '资源类别管理', 'resurceType', '资源类别管理分类', 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO `heming_resource_type` VALUES (5, '菜单管理', 'menu', '菜单管理分类', 5, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_role
-- ----------------------------
DROP TABLE IF EXISTS `heming_role`;
CREATE TABLE `heming_role`  (
  `id` bigint NOT NULL,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint NOT NULL COMMENT '角色启用状态：0->禁用；1->启用',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del` tinyint NOT NULL COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_role
-- ----------------------------
INSERT INTO `heming_role` VALUES (1, '超级管理员', 'platform-super-admin', '超级管理员~', 1, 1, 'heming', 'heming', '2023-05-25 20:16:38', '2023-05-25 20:16:40', 1);

-- ----------------------------
-- Table structure for heming_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `heming_role_menu`;
CREATE TABLE `heming_role_menu`  (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `menu_id` bigint NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `heming_role_menu_role_id_menu_id_uindex`(`role_id` ASC, `menu_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色菜单关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_role_menu
-- ----------------------------
INSERT INTO `heming_role_menu` VALUES (5, 1, 5);
INSERT INTO `heming_role_menu` VALUES (6, 1, 6);
INSERT INTO `heming_role_menu` VALUES (7, 1, 7);
INSERT INTO `heming_role_menu` VALUES (8, 1, 8);
INSERT INTO `heming_role_menu` VALUES (9, 1, 10);
INSERT INTO `heming_role_menu` VALUES (10, 1, 11);
INSERT INTO `heming_role_menu` VALUES (11, 1, 12);
INSERT INTO `heming_role_menu` VALUES (12, 1, 13);
INSERT INTO `heming_role_menu` VALUES (13, 1, 14);
INSERT INTO `heming_role_menu` VALUES (14, 1, 15);

-- ----------------------------
-- Table structure for heming_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `heming_role_resource`;
CREATE TABLE `heming_role_resource`  (
  `id` bigint NOT NULL,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `resource_id` bigint NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `heming_role_resource_role_id_resource_id_uindex`(`role_id` ASC, `resource_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_role_resource
-- ----------------------------
INSERT INTO `heming_role_resource` VALUES (2, 1, 2);
INSERT INTO `heming_role_resource` VALUES (3, 1, 3);
INSERT INTO `heming_role_resource` VALUES (4, 1, 4);
INSERT INTO `heming_role_resource` VALUES (5, 1, 5);
INSERT INTO `heming_role_resource` VALUES (6, 1, 6);
INSERT INTO `heming_role_resource` VALUES (7, 1, 7);
INSERT INTO `heming_role_resource` VALUES (8, 1, 8);

-- ----------------------------
-- Table structure for heming_user
-- ----------------------------
DROP TABLE IF EXISTS `heming_user`;
CREATE TABLE `heming_user`  (
  `id` bigint NOT NULL,
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号(用户名)',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像(地址)',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `telephone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` tinyint NULL DEFAULT NULL COMMENT '排序',
  `creator` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `updater` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint NOT NULL COMMENT '帐号启用状态：0->禁用；1->启用',
  `del` tinyint NOT NULL COMMENT '逻辑删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_user
-- ----------------------------
INSERT INTO `heming_user` VALUES (1, 'heming', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'heming@qq.com', '鹤鸣', '13612345678', '你好', 1, 'heming', 'heming', '2023-05-25 20:18:52', '2023-05-25 20:18:54', 1, 1);
INSERT INTO `heming_user` VALUES (5, 'test', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', '1', '测试', '1', '1', 2, NULL, NULL, NULL, NULL, 1, 1);

-- ----------------------------
-- Table structure for heming_user_role
-- ----------------------------
DROP TABLE IF EXISTS `heming_user_role`;
CREATE TABLE `heming_user_role`  (
  `id` bigint NOT NULL,
  `user_id` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `heming_user_role_user_id_role_id_uindex`(`user_id` ASC, `role_id` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of heming_user_role
-- ----------------------------
INSERT INTO `heming_user_role` VALUES (1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
