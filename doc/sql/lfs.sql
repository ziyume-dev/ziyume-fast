/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : lfs

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 12/08/2021 11:07:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `auth_menu`;
CREATE TABLE `auth_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父菜单名称',
  `level` int(4) NULL DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) NULL DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端名称',
  `path` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) NULL DEFAULT 1 COMMENT '显示状态：0->显示；1->不显示',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO `auth_menu` VALUES (1, 0, '2021-03-21 18:56:44', '权限管理', NULL, 1, 1, 'auth', '/auth', 'el-icon-success', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (2, 0, '2021-03-21 18:57:06', '订单管理', NULL, 1, 2, 'order', '/order', 'el-icon-s-order', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (3, 0, '2021-03-21 18:57:49', '商品管理', NULL, 1, 3, 'product', '/product', 'el-icon-s-goods', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (4, 0, '2021-03-30 10:47:44', '营销管理', NULL, 1, 4, 'market', '/market', 'el-icon-s-marketing', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (5, 0, '2021-03-30 10:48:47', '会员管理', NULL, 1, 5, 'user', '/user', 'el-icon-user-solid', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (6, 0, '2021-03-30 10:49:29', '系统管理', NULL, 1, 6, 'system', '/system', 'el-icon-s-tools', 1, 'Layout');
INSERT INTO `auth_menu` VALUES (10, 1, '2021-03-21 18:57:47', '用户管理', '权限管理', 2, 0, 'authUser', '/auth/authUser', 'el-icon-user-solid', 1, '/auth/authUser/index');
INSERT INTO `auth_menu` VALUES (11, 1, '2021-03-21 18:58:31', '角色管理', '权限管理', 2, 0, 'authRole', '/auth/authRole', 'el-icon-s-custom', 1, '/auth/authRole/index');
INSERT INTO `auth_menu` VALUES (12, 1, '2021-03-21 18:58:34', '菜单管理', '权限管理', 2, 0, 'authMenu', '/auth/authMenu', 'el-icon-menu', 1, '/auth/authMenu/index');
INSERT INTO `auth_menu` VALUES (13, 1, '2021-03-30 10:41:52', '资源管理', '权限管理', 2, 0, 'authResource', '/auth/authResource', 'el-icon-s-promotion', 1, '/auth/authResource/index');
INSERT INTO `auth_menu` VALUES (14, 1, '2021-03-30 10:44:02', '权限管理', '权限管理', 2, 0, 'authPermission', '/auth/authPermission', 'el-icon-check', 1, '/auth/authPermission/index');
INSERT INTO `auth_menu` VALUES (15, 1, '2021-03-30 10:46:32', '资源类别管理', '权限管理', 2, 0, 'authResourceSort', '/auth/authResourceSort', 'el-icon-finished', 1, '/auth/authResourceSort/index');
INSERT INTO `auth_menu` VALUES (20, 2, '2021-03-30 10:36:21', '订单列表', '订单管理', 2, 0, 'orderList', '/order/orderList', 'el-icon-s-order', 1, '/order/orderList/index');
INSERT INTO `auth_menu` VALUES (21, 2, '2021-03-30 10:38:40', '定时任务', '订单管理', 2, 0, 'orderTimeTask', '/order/orderTimeTask', 'el-icon-timer', 1, '/order/orderTimeTask/index');
INSERT INTO `auth_menu` VALUES (22, 2, '2021-03-30 10:39:58', '售后订单', '订单管理', 2, 0, 'orderReturn', '/order/orderReturn', 'el-icon-s-claim', 1, '/order/orderReturn');
INSERT INTO `auth_menu` VALUES (23, 2, '2021-03-30 10:40:43', '售后原因', '订单管理', 2, 0, 'orderReason', '/order/orderReason', 'el-icon-notebook-1', 1, '/order/orderReason/index');
INSERT INTO `auth_menu` VALUES (30, 3, '2021-03-30 10:33:46', '品牌管理', '商品管理', 2, 0, 'productBrand', '/product/productBrand', 'el-icon-postcard', 1, '/product/productBrand/index');
INSERT INTO `auth_menu` VALUES (31, 3, '2021-03-27 15:47:53', '商品列表', '商品管理', 2, 0, 'productList', '/product/productList', 'el-icon-s-goods', 1, '/product/productList/index');
INSERT INTO `auth_menu` VALUES (32, 3, '2021-03-30 10:31:03', '类型管理', '商品管理', 2, 0, 'productType', '/product/productType', 'el-icon-price-tag', 1, '/product/productType/index');
INSERT INTO `auth_menu` VALUES (33, 3, '2021-03-30 10:29:15', '分类管理', '商品管理', 2, 0, 'productSort', '/product/productSort', 'el-icon-discount', 1, '/product/productSort/index');
INSERT INTO `auth_menu` VALUES (34, 3, '2021-03-27 15:49:10', '修改商品', '商品管理', 2, 0, 'productUpdate', '/product/productUpdate', 'el-icon-sold-out', 1, '/product/productUpdate/index');
INSERT INTO `auth_menu` VALUES (35, 3, '2021-03-27 15:49:04', '添加商品', '商品管理', 2, 0, 'productAdd', '/product/productAdd', 'el-icon-sell', 1, '/product/productAdd/index');
INSERT INTO `auth_menu` VALUES (40, 4, '2021-03-30 10:54:02', '虚拟币管理', '营销管理', 2, 0, 'marketBit', '/market/marketBit', 'el-icon-s-finance', 1, '/market/marketBit/index');
INSERT INTO `auth_menu` VALUES (41, 4, '2021-03-30 10:51:23', '秒杀管理', '营销管理', 2, 0, 'marketSpike', '/market/marketSpike', 'el-icon-alarm-clock', 1, '/market/marketSpike/index');
INSERT INTO `auth_menu` VALUES (42, 4, '2021-03-30 10:52:29', '优惠券管理', '营销管理', 2, 0, 'marketCoupon', '/market/marketCoupon', 'el-icon-s-ticket', 1, '/market/marketCoupon/index');
INSERT INTO `auth_menu` VALUES (43, 4, '2021-03-30 10:53:21', '广告管理', '营销管理', 2, 0, 'marketAD', '/market/marketAD', 'el-icon-data-line', 1, '/market/marketAD/index');
INSERT INTO `auth_menu` VALUES (50, 5, '2021-03-30 10:55:06', '会员列表', '会员管理', 2, 0, 'userList', '/user/userList', 'el-icon-user', 1, '/user/userList/index');
INSERT INTO `auth_menu` VALUES (51, 6, '2021-04-10 10:39:32', '版本日志', '系统管理', 2, 0, 'version', '/system/version', 'el-icon-s-promotion', 1, '/system/version/index');
INSERT INTO `auth_menu` VALUES (100, 0, '2021-08-12 10:58:54', '首页', NULL, 1, 0, 'Dashboard', '/dashboard', 'el-icon-s-platform', 1, 'Layout');

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级权限id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `type` int(1) NULL DEFAULT NULL COMMENT '权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）',
  `uri` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前端资源路径',
  `status` int(1) NULL DEFAULT NULL COMMENT '启用状态；0->禁用；1->启用',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_permission
-- ----------------------------

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource`;
CREATE TABLE `auth_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `category_id` bigint(20) NULL DEFAULT NULL COMMENT '资源类别ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块资源表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_resource
-- ----------------------------
INSERT INTO `auth_resource` VALUES (7, '用户信息', '/user/info', '管理系统用户登陆后查询个人信息', '2021-07-08 17:37:11', 3);

-- ----------------------------
-- Table structure for auth_resource_sort
-- ----------------------------
DROP TABLE IF EXISTS `auth_resource_sort`;
CREATE TABLE `auth_resource_sort`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源类别名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块资源类别管理表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_resource_sort
-- ----------------------------

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) NULL DEFAULT NULL COMMENT '用户数量',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(1) NULL DEFAULT 0 COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `del` int(1) NULL DEFAULT NULL COMMENT '假删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES (1, '超级管理员', '超级管理员，拥有所有的权限', 1, '2021-06-09 16:45:11', 1, 0, 1);
INSERT INTO `auth_role` VALUES (2, '测试员111', '测试账号权限', 2, '2021-06-09 16:45:15', 1, 0, 1);

-- ----------------------------
-- Table structure for auth_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_menu_relation`;
CREATE TABLE `auth_role_menu_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块角色菜单关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role_menu_relation
-- ----------------------------
INSERT INTO `auth_role_menu_relation` VALUES (91, 1, 1);
INSERT INTO `auth_role_menu_relation` VALUES (92, 1, 2);
INSERT INTO `auth_role_menu_relation` VALUES (93, 1, 3);
INSERT INTO `auth_role_menu_relation` VALUES (94, 1, 4);
INSERT INTO `auth_role_menu_relation` VALUES (95, 1, 5);
INSERT INTO `auth_role_menu_relation` VALUES (96, 1, 6);
INSERT INTO `auth_role_menu_relation` VALUES (97, 1, 10);
INSERT INTO `auth_role_menu_relation` VALUES (98, 1, 11);
INSERT INTO `auth_role_menu_relation` VALUES (99, 1, 12);
INSERT INTO `auth_role_menu_relation` VALUES (100, 1, 13);
INSERT INTO `auth_role_menu_relation` VALUES (101, 1, 14);
INSERT INTO `auth_role_menu_relation` VALUES (102, 1, 15);
INSERT INTO `auth_role_menu_relation` VALUES (103, 1, 20);
INSERT INTO `auth_role_menu_relation` VALUES (104, 1, 21);
INSERT INTO `auth_role_menu_relation` VALUES (105, 1, 22);
INSERT INTO `auth_role_menu_relation` VALUES (106, 1, 23);
INSERT INTO `auth_role_menu_relation` VALUES (107, 1, 30);
INSERT INTO `auth_role_menu_relation` VALUES (108, 1, 31);
INSERT INTO `auth_role_menu_relation` VALUES (109, 1, 32);
INSERT INTO `auth_role_menu_relation` VALUES (110, 1, 33);
INSERT INTO `auth_role_menu_relation` VALUES (111, 1, 34);
INSERT INTO `auth_role_menu_relation` VALUES (112, 1, 35);
INSERT INTO `auth_role_menu_relation` VALUES (113, 1, 40);
INSERT INTO `auth_role_menu_relation` VALUES (114, 1, 41);
INSERT INTO `auth_role_menu_relation` VALUES (115, 1, 42);
INSERT INTO `auth_role_menu_relation` VALUES (116, 1, 43);
INSERT INTO `auth_role_menu_relation` VALUES (117, 1, 50);
INSERT INTO `auth_role_menu_relation` VALUES (118, 1, 51);
INSERT INTO `auth_role_menu_relation` VALUES (119, 2, 50);
INSERT INTO `auth_role_menu_relation` VALUES (120, 2, 51);
INSERT INTO `auth_role_menu_relation` VALUES (121, 2, 40);
INSERT INTO `auth_role_menu_relation` VALUES (122, 2, 41);
INSERT INTO `auth_role_menu_relation` VALUES (123, 2, 33);
INSERT INTO `auth_role_menu_relation` VALUES (124, 2, 30);
INSERT INTO `auth_role_menu_relation` VALUES (125, 2, 22);
INSERT INTO `auth_role_menu_relation` VALUES (126, 2, 15);
INSERT INTO `auth_role_menu_relation` VALUES (127, 2, 20);
INSERT INTO `auth_role_menu_relation` VALUES (128, 2, 10);
INSERT INTO `auth_role_menu_relation` VALUES (129, 2, 1);
INSERT INTO `auth_role_menu_relation` VALUES (130, 2, 2);
INSERT INTO `auth_role_menu_relation` VALUES (131, 2, 3);
INSERT INTO `auth_role_menu_relation` VALUES (132, 2, 4);
INSERT INTO `auth_role_menu_relation` VALUES (133, 2, 5);
INSERT INTO `auth_role_menu_relation` VALUES (134, 1, 100);

-- ----------------------------
-- Table structure for auth_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission_relation`;
CREATE TABLE `auth_role_permission_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块角色权限关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for auth_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_resource_relation`;
CREATE TABLE `auth_role_resource_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) NULL DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块角色资源关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_role_resource_relation
-- ----------------------------
INSERT INTO `auth_role_resource_relation` VALUES (1, 1, 7);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `icon` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `nick_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) NULL DEFAULT 1 COMMENT '帐号启用状态：0->禁用；1->启用',
  `del` int(1) NOT NULL COMMENT '假删除：0->删除状态；1->可用状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES (1, 'admin', '$2a$10$E0A60hzJ.yBHJhyZ970Oze205OGuu4LIrjDSPQvcGBDl40O0oaiqC', 'https://www.52bess.com/uploads/avatar.png', 'admin@qq.com', '12345678901', '管理员', '管理员', '2021-02-24 21:22:48', '2021-08-12 11:06:30', 1, 1);
INSERT INTO `auth_user` VALUES (2, 'test', '$2a$10$E0A60hzJ.yBHJhyZ970Oze205OGuu4LIrjDSPQvcGBDl40O0oaiqC', 'https://www.52bess.com/uploads/avatar.png', 'test@qq.com', '12345678902', '测试员', '测试员', '2021-03-21 13:42:10', '2021-04-24 12:45:54', 1, 1);
INSERT INTO `auth_user` VALUES (3, 'user1', '$2a$10$U9qlXI22XmUjzAgZiH0kMOalOkBTM23LvmAownM1GNXNQTRhO4Mtu', 'https://www.52bess.com/uploads/avatar.png', 'user1@qq.com', '111', '普通用户1', '普通用户1', '2021-04-04 18:18:44', '2021-04-04 18:18:44', 1, 1);

-- ----------------------------
-- Table structure for auth_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role_relation`;
CREATE TABLE `auth_user_role_relation`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限管理模块用户角色关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of auth_user_role_relation
-- ----------------------------
INSERT INTO `auth_user_role_relation` VALUES (1, 1, 1);
INSERT INTO `auth_user_role_relation` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for web_log
-- ----------------------------
DROP TABLE IF EXISTS `web_log`;
CREATE TABLE `web_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '日志描述信息',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求地址',
  `http_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `class_method` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法路径:全限定名+方法名',
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求者ip地址',
  `request_args` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求入参',
  `response_args` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应出参',
  `start_time` datetime NULL DEFAULT NULL COMMENT '请求时间',
  `spend_time` bigint(20) NULL DEFAULT NULL COMMENT '消耗时间(毫秒)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of web_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
