/*
 Navicat Premium Data Transfer

 Source Server         : localhostPostgre
 Source Server Type    : PostgreSQL
 Source Server Version : 140004
 Source Host           : localhost:5432
 Source Catalog        : lfs
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140004
 File Encoding         : 65001

 Date: 27/10/2022 16:32:10
*/


-- ----------------------------
-- Sequence structure for auth_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_menu_id_seq";
CREATE SEQUENCE "public"."auth_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_resource_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_resource_id_seq";
CREATE SEQUENCE "public"."auth_resource_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_resource_sort_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_resource_sort_id_seq";
CREATE SEQUENCE "public"."auth_resource_sort_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_role_id_seq";
CREATE SEQUENCE "public"."auth_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_role_menu_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_role_menu_relation_id_seq";
CREATE SEQUENCE "public"."auth_role_menu_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_role_permission_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_role_permission_relation_id_seq";
CREATE SEQUENCE "public"."auth_role_permission_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_role_resource_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_role_resource_relation_id_seq";
CREATE SEQUENCE "public"."auth_role_resource_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_user_id_seq";
CREATE SEQUENCE "public"."auth_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for auth_user_role_relation_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."auth_user_role_relation_id_seq";
CREATE SEQUENCE "public"."auth_user_role_relation_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for auth_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_menu";
CREATE TABLE "public"."auth_menu" (
  "id" int4 NOT NULL DEFAULT nextval('auth_menu_id_seq'::regclass),
  "parent_id" int4 NOT NULL,
  "title" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "parent_title" varchar(32) COLLATE "pg_catalog"."default",
  "level" int2 NOT NULL,
  "sort" int2,
  "name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" varchar(32) COLLATE "pg_catalog"."default",
  "hidden" int2 DEFAULT 1,
  "component" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "del" int2 NOT NULL DEFAULT 1,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0)
)
;
COMMENT ON COLUMN "public"."auth_menu"."parent_id" IS '父级ID';
COMMENT ON COLUMN "public"."auth_menu"."title" IS '菜单名称';
COMMENT ON COLUMN "public"."auth_menu"."parent_title" IS '父菜单名称';
COMMENT ON COLUMN "public"."auth_menu"."level" IS '菜单级数';
COMMENT ON COLUMN "public"."auth_menu"."sort" IS '菜单排序';
COMMENT ON COLUMN "public"."auth_menu"."name" IS '前端名称';
COMMENT ON COLUMN "public"."auth_menu"."path" IS '路由地址';
COMMENT ON COLUMN "public"."auth_menu"."icon" IS '前端图标';
COMMENT ON COLUMN "public"."auth_menu"."hidden" IS '显示状态：0->显示；1->不显示';
COMMENT ON COLUMN "public"."auth_menu"."component" IS '组件路径';
COMMENT ON COLUMN "public"."auth_menu"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."auth_menu"."creator" IS '创建者';
COMMENT ON COLUMN "public"."auth_menu"."updater" IS '更新者';
COMMENT ON COLUMN "public"."auth_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_menu"."update_time" IS '更新时间';

-- ----------------------------
-- Records of auth_menu
-- ----------------------------
INSERT INTO "public"."auth_menu" VALUES (1, 0, '权限管理', NULL, 1, 1, 'auth', '/auth', 'el-icon-success', 1, 'Layout', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (6, 0, '系统管理', NULL, 1, 6, 'system', '/system', 'el-icon-s-tools', 1, 'Layout', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (11, 1, '角色管理', '权限管理', 2, 0, 'authRole', '/auth/authRole', 'el-icon-s-custom', 1, '/auth/authRole/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (12, 1, '菜单管理', '权限管理', 2, 0, 'authMenu', '/auth/authMenu', 'el-icon-menu', 1, '/auth/authMenu/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (13, 1, '资源管理', '权限管理', 2, 0, 'authResource', '/auth/authResource', 'el-icon-s-promotion', 1, '/auth/authResource/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (15, 1, '资源类别管理', '权限管理', 2, 0, 'authResourceSort', '/auth/authResourceSort', 'el-icon-finished', 1, '/auth/authResourceSort/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (3, 1, '1', '1', 1, 1, '1', '1', '1', 1, '1', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (10, 1, '用户管理', '权限管理', 2, 0, 'authUser', '/auth/authUser', 'el-icon-user-solid', 1, '/auth/authUser/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (51, 6, '版本日志', '系统管理', 2, 0, 'version', '/system/version', 'el-icon-s-promotion', 1, '/system/version/index', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');
INSERT INTO "public"."auth_menu" VALUES (100, 0, '首页', NULL, 1, 0, 'Dashboard', '/dashboard', 'el-icon-s-platform', 1, 'Layout', 1, NULL, NULL, '2022-10-21 15:47:49', '2022-10-21 15:47:49');

-- ----------------------------
-- Table structure for auth_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_resource";
CREATE TABLE "public"."auth_resource" (
  "id" int4 NOT NULL DEFAULT nextval('auth_resource_id_seq'::regclass),
  "name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "url" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(200) COLLATE "pg_catalog"."default",
  "category_id" int4 NOT NULL,
  "del" int2 NOT NULL DEFAULT 1,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0)
)
;
COMMENT ON COLUMN "public"."auth_resource"."name" IS '资源名称';
COMMENT ON COLUMN "public"."auth_resource"."url" IS '资源路径';
COMMENT ON COLUMN "public"."auth_resource"."description" IS '资源描述';
COMMENT ON COLUMN "public"."auth_resource"."category_id" IS '资源类别 id';
COMMENT ON COLUMN "public"."auth_resource"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."auth_resource"."creator" IS '创建者';
COMMENT ON COLUMN "public"."auth_resource"."updater" IS '更新者';
COMMENT ON COLUMN "public"."auth_resource"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_resource"."update_time" IS '更新时间';

-- ----------------------------
-- Records of auth_resource
-- ----------------------------
INSERT INTO "public"."auth_resource" VALUES (1, '添加用户', '/user/addUser', '添加权限管理模块用户', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (2, '删除用户', '/user/delUser/**', '删除后台管理系统的用户', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (3, '用户信息', '/user/info', '管理系统用户登陆后查询个人信息', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (4, '修改用户', '/user/updateUser', '更新后台管理系统用户信息', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (5, '用户列表', '/user/list', '获取管理系统所有用户', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (6, '导出用户', '/user/exportUser', '导出权限管理模块用户', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (7, '获取用户', '/user/getUser/**', '根据id获取用户', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (8, '更新状态', '/user/changeSwitch', '用户账户启用状态更新', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (9, '导出列表', '/user/export', '导出管理系统用户列表', 3, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (10, '菜单列表', '/menu/list', '查询后台管理菜单列表', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (11, '菜单详情', '/menu/getMenu/**', '根据id查询菜单详情', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (12, '修改菜单', '/menu/updateMenu', '修改菜单', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (13, '更新状态', '/menu/changeSwitch', '菜单是否显示状态更新', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (14, '删除菜单', '/menu/delMenu/**', '根据id删除菜单', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (15, '新增菜单', '/menu/addMenu', '新增菜单', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (16, '父菜单查询', '/menu/getParentMenu', '查询所有的父菜单', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (17, '角色列表', '/role/list', '获取角色列表', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (18, '角色信息', '/role/getRole/**', '查询角色详细', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (19, '新增角色', '/role/addRole', '新增角色', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (20, '修改角色', '/role/updateRole', '修改角色信息', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (21, '删除角色', '/role/delRole/**', '根据id删除角色', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (22, '更新状态', '/role/changeSwitch', '角色启用状态更新', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (23, '资源列表', '/resource/list', '获取资源列表', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (24, '资源详情', '/resource/getResource/**', '根据id获取资源详情', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (25, '新增资源', '/resource/addResource', '新增资源', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (26, '更新资源', '/resource/updateResource', '更新资源信息', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (27, '删除资源', '/resource/delResource/**', '根据id删除资源', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (28, '获取所有角色', '/role/getRoleAll', '获取所有角色', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (29, '更新用户角色', '/role/updateRoleById', '更新用户的角色', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (30, '查询角色', '/role/getRoleById/**', '根据用户id查询角色', 6, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (31, '获取菜单树', '/menu/getMenuTreeById/**', '根据角色id获取菜单树', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (32, '获取所有菜单树', '/menu/getAllMenuTree', '获取所有菜单树', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (33, '获取资源树', '/resource/getResourceTreeById/**', '根据角色id获取资源树', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (34, '获取所有资源树', '/resource/getAllResourceTree', '获取所有资源树', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (35, '更新菜单树', '/menu/updateMenuTree', '更新菜单树', 2, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (36, '更新资源树', '/resource/updateResourceTree', '更新资源树', 5, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (37, '资源类别列表', '/resourceSort/list', '获取资源类别列表', 4, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (38, '资源类别详细', '/resourceSort/getResourceSort/**', '查询资源类别详细', 4, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (39, '新增资源类别', '/resourceSort/addResourceSort', '新增资源类别', 4, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (40, '修改资源类别', '/resourceSort/updateResourceSort', '修改资源类别', 4, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (41, '删除资源类别', '/resourceSort/delResourceSort/**', '删除资源类别', 4, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');
INSERT INTO "public"."auth_resource" VALUES (42, '版本日志列表', '/updateLog/list', '版本日志列表', 7, 1, NULL, NULL, '2022-10-21 15:47:27', '2022-10-21 15:47:27');

-- ----------------------------
-- Table structure for auth_resource_sort
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_resource_sort";
CREATE TABLE "public"."auth_resource_sort" (
  "id" int4 NOT NULL DEFAULT nextval('auth_resource_sort_id_seq'::regclass),
  "category_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "sort" int2,
  "del" int2 NOT NULL DEFAULT 1,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0)
)
;
COMMENT ON COLUMN "public"."auth_resource_sort"."category_name" IS '资源类别名称';
COMMENT ON COLUMN "public"."auth_resource_sort"."description" IS '资源描述';
COMMENT ON COLUMN "public"."auth_resource_sort"."sort" IS '排序';
COMMENT ON COLUMN "public"."auth_resource_sort"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."auth_resource_sort"."creator" IS '创建者';
COMMENT ON COLUMN "public"."auth_resource_sort"."updater" IS '更新者';
COMMENT ON COLUMN "public"."auth_resource_sort"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_resource_sort"."update_time" IS '更新时间';

-- ----------------------------
-- Records of auth_resource_sort
-- ----------------------------
INSERT INTO "public"."auth_resource_sort" VALUES (1, '权限管理', '权限管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (2, '菜单管理', '菜单管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (3, '用户管理', '用户管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (4, '资源类别管理', '资源类别管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (5, '资源管理', '资源管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (6, '角色管理', '角色管理', 1, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');
INSERT INTO "public"."auth_resource_sort" VALUES (7, '版本日志', '版本日志', 6, 1, NULL, NULL, '2022-10-21 15:46:51', '2022-10-21 15:46:51');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_role";
CREATE TABLE "public"."auth_role" (
  "id" int4 NOT NULL DEFAULT nextval('auth_role_id_seq'::regclass),
  "name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "user_count" int4 DEFAULT 0,
  "status" int2 NOT NULL DEFAULT 0,
  "sort" int2,
  "del" int2 NOT NULL DEFAULT 1,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0)
)
;
COMMENT ON COLUMN "public"."auth_role"."name" IS '名称';
COMMENT ON COLUMN "public"."auth_role"."description" IS '描述';
COMMENT ON COLUMN "public"."auth_role"."user_count" IS '用户数量';
COMMENT ON COLUMN "public"."auth_role"."status" IS '启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."auth_role"."sort" IS '排序';
COMMENT ON COLUMN "public"."auth_role"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."auth_role"."creator" IS '创建者';
COMMENT ON COLUMN "public"."auth_role"."updater" IS '更新者';
COMMENT ON COLUMN "public"."auth_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_role"."update_time" IS '更新时间';

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO "public"."auth_role" VALUES (1, '超级管理员', '超级管理员，拥有所有的权限', 1, 1, 0, 1, NULL, NULL, '2022-10-21 15:46:16', '2022-10-21 15:46:16');
INSERT INTO "public"."auth_role" VALUES (2, '测试员111', '测试账号权限', 2, 1, 0, 1, NULL, NULL, '2022-10-21 15:46:16', '2022-10-21 15:46:16');
INSERT INTO "public"."auth_role" VALUES (18, '测试11111111', '测温顶起顶起1', NULL, 1, 4, 0, NULL, NULL, '2022-10-21 17:28:48', '2022-10-21 17:28:48');
INSERT INTO "public"."auth_role" VALUES (19, '测试22333', '11111', NULL, 0, 12, 1, NULL, 'admin', '2022-10-27 16:28:00', '2022-10-27 16:28:00');
INSERT INTO "public"."auth_role" VALUES (14, '测试', '测试3', 0, 0, 1, 1, NULL, 'admin', '2022-10-27 16:31:49', '2022-10-27 16:31:49');

-- ----------------------------
-- Table structure for auth_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_role_menu_relation";
CREATE TABLE "public"."auth_role_menu_relation" (
  "id" int4 NOT NULL DEFAULT nextval('auth_role_menu_relation_id_seq'::regclass),
  "role_id" int4 NOT NULL,
  "menu_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."auth_role_menu_relation"."role_id" IS '角色 id';
COMMENT ON COLUMN "public"."auth_role_menu_relation"."menu_id" IS '菜单 id';

-- ----------------------------
-- Records of auth_role_menu_relation
-- ----------------------------
INSERT INTO "public"."auth_role_menu_relation" VALUES (91, 1, 1);
INSERT INTO "public"."auth_role_menu_relation" VALUES (96, 1, 6);
INSERT INTO "public"."auth_role_menu_relation" VALUES (97, 1, 10);
INSERT INTO "public"."auth_role_menu_relation" VALUES (98, 1, 11);
INSERT INTO "public"."auth_role_menu_relation" VALUES (99, 1, 12);
INSERT INTO "public"."auth_role_menu_relation" VALUES (100, 1, 13);
INSERT INTO "public"."auth_role_menu_relation" VALUES (101, 1, 14);
INSERT INTO "public"."auth_role_menu_relation" VALUES (102, 1, 15);
INSERT INTO "public"."auth_role_menu_relation" VALUES (118, 1, 51);
INSERT INTO "public"."auth_role_menu_relation" VALUES (134, 1, 100);
INSERT INTO "public"."auth_role_menu_relation" VALUES (135, 2, 1);
INSERT INTO "public"."auth_role_menu_relation" VALUES (136, 2, 10);
INSERT INTO "public"."auth_role_menu_relation" VALUES (137, 2, 11);
INSERT INTO "public"."auth_role_menu_relation" VALUES (138, 2, 12);
INSERT INTO "public"."auth_role_menu_relation" VALUES (139, 2, 13);
INSERT INTO "public"."auth_role_menu_relation" VALUES (140, 2, 14);
INSERT INTO "public"."auth_role_menu_relation" VALUES (141, 2, 15);
INSERT INTO "public"."auth_role_menu_relation" VALUES (142, 2, 6);
INSERT INTO "public"."auth_role_menu_relation" VALUES (143, 2, 51);
INSERT INTO "public"."auth_role_menu_relation" VALUES (144, 2, 100);

-- ----------------------------
-- Table structure for auth_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_role_permission_relation";
CREATE TABLE "public"."auth_role_permission_relation" (
  "id" int4 NOT NULL DEFAULT nextval('auth_role_permission_relation_id_seq'::regclass),
  "role_id" int4 NOT NULL,
  "permission_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."auth_role_permission_relation"."role_id" IS '角色 id';
COMMENT ON COLUMN "public"."auth_role_permission_relation"."permission_id" IS '权限 id';

-- ----------------------------
-- Records of auth_role_permission_relation
-- ----------------------------

-- ----------------------------
-- Table structure for auth_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_role_resource_relation";
CREATE TABLE "public"."auth_role_resource_relation" (
  "id" int4 NOT NULL DEFAULT nextval('auth_role_resource_relation_id_seq'::regclass),
  "role_id" int4 NOT NULL,
  "resource_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."auth_role_resource_relation"."role_id" IS '角色 id';
COMMENT ON COLUMN "public"."auth_role_resource_relation"."resource_id" IS '资源 id';

-- ----------------------------
-- Records of auth_role_resource_relation
-- ----------------------------
INSERT INTO "public"."auth_role_resource_relation" VALUES (1, 1, 1);
INSERT INTO "public"."auth_role_resource_relation" VALUES (2, 1, 2);
INSERT INTO "public"."auth_role_resource_relation" VALUES (3, 1, 3);
INSERT INTO "public"."auth_role_resource_relation" VALUES (4, 1, 4);
INSERT INTO "public"."auth_role_resource_relation" VALUES (5, 1, 5);
INSERT INTO "public"."auth_role_resource_relation" VALUES (6, 1, 6);
INSERT INTO "public"."auth_role_resource_relation" VALUES (7, 1, 7);
INSERT INTO "public"."auth_role_resource_relation" VALUES (8, 1, 8);
INSERT INTO "public"."auth_role_resource_relation" VALUES (9, 1, 9);
INSERT INTO "public"."auth_role_resource_relation" VALUES (10, 1, 10);
INSERT INTO "public"."auth_role_resource_relation" VALUES (11, 1, 11);
INSERT INTO "public"."auth_role_resource_relation" VALUES (12, 1, 12);
INSERT INTO "public"."auth_role_resource_relation" VALUES (13, 1, 13);
INSERT INTO "public"."auth_role_resource_relation" VALUES (14, 1, 14);
INSERT INTO "public"."auth_role_resource_relation" VALUES (15, 1, 15);
INSERT INTO "public"."auth_role_resource_relation" VALUES (16, 1, 16);
INSERT INTO "public"."auth_role_resource_relation" VALUES (17, 1, 17);
INSERT INTO "public"."auth_role_resource_relation" VALUES (18, 1, 18);
INSERT INTO "public"."auth_role_resource_relation" VALUES (19, 1, 19);
INSERT INTO "public"."auth_role_resource_relation" VALUES (20, 1, 20);
INSERT INTO "public"."auth_role_resource_relation" VALUES (21, 1, 21);
INSERT INTO "public"."auth_role_resource_relation" VALUES (22, 1, 22);
INSERT INTO "public"."auth_role_resource_relation" VALUES (23, 1, 23);
INSERT INTO "public"."auth_role_resource_relation" VALUES (24, 1, 24);
INSERT INTO "public"."auth_role_resource_relation" VALUES (25, 1, 25);
INSERT INTO "public"."auth_role_resource_relation" VALUES (26, 1, 26);
INSERT INTO "public"."auth_role_resource_relation" VALUES (27, 1, 27);
INSERT INTO "public"."auth_role_resource_relation" VALUES (28, 1, 28);
INSERT INTO "public"."auth_role_resource_relation" VALUES (29, 1, 29);
INSERT INTO "public"."auth_role_resource_relation" VALUES (30, 1, 30);
INSERT INTO "public"."auth_role_resource_relation" VALUES (31, 1, 31);
INSERT INTO "public"."auth_role_resource_relation" VALUES (32, 1, 32);
INSERT INTO "public"."auth_role_resource_relation" VALUES (33, 1, 33);
INSERT INTO "public"."auth_role_resource_relation" VALUES (34, 1, 34);
INSERT INTO "public"."auth_role_resource_relation" VALUES (35, 1, 35);
INSERT INTO "public"."auth_role_resource_relation" VALUES (36, 1, 36);
INSERT INTO "public"."auth_role_resource_relation" VALUES (37, 1, 37);
INSERT INTO "public"."auth_role_resource_relation" VALUES (38, 1, 38);
INSERT INTO "public"."auth_role_resource_relation" VALUES (39, 1, 39);
INSERT INTO "public"."auth_role_resource_relation" VALUES (40, 1, 40);
INSERT INTO "public"."auth_role_resource_relation" VALUES (41, 1, 41);
INSERT INTO "public"."auth_role_resource_relation" VALUES (42, 1, 42);

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_user";
CREATE TABLE "public"."auth_user" (
  "id" int4 NOT NULL DEFAULT nextval('auth_user_id_seq'::regclass),
  "username" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" varchar(64) COLLATE "pg_catalog"."default",
  "email" varchar(64) COLLATE "pg_catalog"."default",
  "phone" varchar(11) COLLATE "pg_catalog"."default",
  "nick_name" varchar(32) COLLATE "pg_catalog"."default",
  "note" varchar(255) COLLATE "pg_catalog"."default",
  "login_time" timestamp(6),
  "status" int2 NOT NULL DEFAULT 0,
  "del" int2 NOT NULL DEFAULT 1,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0)
)
;
COMMENT ON COLUMN "public"."auth_user"."username" IS '用户名';
COMMENT ON COLUMN "public"."auth_user"."password" IS '密码';
COMMENT ON COLUMN "public"."auth_user"."icon" IS '头像';
COMMENT ON COLUMN "public"."auth_user"."email" IS '邮箱';
COMMENT ON COLUMN "public"."auth_user"."phone" IS '手机';
COMMENT ON COLUMN "public"."auth_user"."nick_name" IS '昵称';
COMMENT ON COLUMN "public"."auth_user"."note" IS '备注信息';
COMMENT ON COLUMN "public"."auth_user"."login_time" IS '最后登录时间';
COMMENT ON COLUMN "public"."auth_user"."status" IS '帐号启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."auth_user"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."auth_user"."creator" IS '创建者';
COMMENT ON COLUMN "public"."auth_user"."updater" IS '更新者';
COMMENT ON COLUMN "public"."auth_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."auth_user"."update_time" IS '更新时间';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO "public"."auth_user" VALUES (2, 'test', '$2a$10$E0A60hzJ.yBHJhyZ970Oze205OGuu4LIrjDSPQvcGBDl40O0oaiqC', 'https://www.besscroft.com/uploads/avatar.png', 'test@qq.com', '12345678902', '测试员', '测试员', '2021-04-24 12:45:54', 1, 1, NULL, NULL, '2022-10-21 15:44:11', '2022-10-21 15:44:11');
INSERT INTO "public"."auth_user" VALUES (3, 'user1', '$2a$10$U9qlXI22XmUjzAgZiH0kMOalOkBTM23LvmAownM1GNXNQTRhO4Mtu', 'https://www.besscroft.com/uploads/avatar.png', 'user1@qq.com', '111', '普通用户1', '普通用户1', '2021-04-04 18:18:44', 1, 1, NULL, NULL, '2022-10-21 15:44:11', '2022-10-21 15:44:11');
INSERT INTO "public"."auth_user" VALUES (1, 'admin', '$2a$10$E0A60hzJ.yBHJhyZ970Oze205OGuu4LIrjDSPQvcGBDl40O0oaiqC', 'https://www.besscroft.com/uploads/avatar.png', 'admin@qq.com', '12345678901', '管理员', '管理员', '2022-10-27 16:27:53.553', 1, 1, NULL, NULL, '2022-10-21 15:44:11', '2022-10-21 15:44:11');
INSERT INTO "public"."auth_user" VALUES (4, '张三', '$2a$10$jnxVO4Oqs2Y6Zem1HYmp0eHwjaoKqvbZeWkT7uDGt9beoxxg9f6q.', NULL, NULL, NULL, NULL, NULL, '2022-10-21 16:30:23.534104', 1, 0, NULL, NULL, '2022-10-21 16:30:24', '2022-10-21 16:30:24');

-- ----------------------------
-- Table structure for auth_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS "public"."auth_user_role_relation";
CREATE TABLE "public"."auth_user_role_relation" (
  "id" int4 NOT NULL DEFAULT nextval('auth_user_role_relation_id_seq'::regclass),
  "user_id" int4 NOT NULL,
  "role_id" int4 NOT NULL
)
;
COMMENT ON COLUMN "public"."auth_user_role_relation"."user_id" IS '用户 id';
COMMENT ON COLUMN "public"."auth_user_role_relation"."role_id" IS '角色 id';

-- ----------------------------
-- Records of auth_user_role_relation
-- ----------------------------
INSERT INTO "public"."auth_user_role_relation" VALUES (1, 1, 1);
INSERT INTO "public"."auth_user_role_relation" VALUES (4, 2, 2);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_menu_id_seq"
OWNED BY "public"."auth_menu"."id";
SELECT setval('"public"."auth_menu_id_seq"', 101, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_resource_id_seq"
OWNED BY "public"."auth_resource"."id";
SELECT setval('"public"."auth_resource_id_seq"', 43, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_resource_sort_id_seq"
OWNED BY "public"."auth_resource_sort"."id";
SELECT setval('"public"."auth_resource_sort_id_seq"', 8, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_role_id_seq"
OWNED BY "public"."auth_role"."id";
SELECT setval('"public"."auth_role_id_seq"', 20, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_role_menu_relation_id_seq"
OWNED BY "public"."auth_role_menu_relation"."id";
SELECT setval('"public"."auth_role_menu_relation_id_seq"', 145, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_role_permission_relation_id_seq"
OWNED BY "public"."auth_role_permission_relation"."id";
SELECT setval('"public"."auth_role_permission_relation_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_role_resource_relation_id_seq"
OWNED BY "public"."auth_role_resource_relation"."id";
SELECT setval('"public"."auth_role_resource_relation_id_seq"', 42, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_user_id_seq"
OWNED BY "public"."auth_user"."id";
SELECT setval('"public"."auth_user_id_seq"', 4, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."auth_user_role_relation_id_seq"
OWNED BY "public"."auth_user_role_relation"."id";
SELECT setval('"public"."auth_user_role_relation_id_seq"', 8, true);

-- ----------------------------
-- Indexes structure for table auth_menu
-- ----------------------------
CREATE UNIQUE INDEX "auth_menu_path_uindex" ON "public"."auth_menu" USING btree (
  "path" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_menu
-- ----------------------------
ALTER TABLE "public"."auth_menu" ADD CONSTRAINT "auth_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table auth_resource
-- ----------------------------
ALTER TABLE "public"."auth_resource" ADD CONSTRAINT "auth_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table auth_resource_sort
-- ----------------------------
ALTER TABLE "public"."auth_resource_sort" ADD CONSTRAINT "auth_resource_sort_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table auth_role
-- ----------------------------
ALTER TABLE "public"."auth_role" ADD CONSTRAINT "auth_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_role_menu_relation
-- ----------------------------
CREATE UNIQUE INDEX "auth_role_menu_relation_role_id_menu_id_uindex" ON "public"."auth_role_menu_relation" USING btree (
  "role_id" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "menu_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_role_menu_relation
-- ----------------------------
ALTER TABLE "public"."auth_role_menu_relation" ADD CONSTRAINT "auth_role_menu_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_role_permission_relation
-- ----------------------------
CREATE UNIQUE INDEX "auth_role_permission_relation_role_id_permission_id_uindex" ON "public"."auth_role_permission_relation" USING btree (
  "role_id" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "permission_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_role_permission_relation
-- ----------------------------
ALTER TABLE "public"."auth_role_permission_relation" ADD CONSTRAINT "auth_role_permission_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_role_resource_relation
-- ----------------------------
CREATE UNIQUE INDEX "auth_role_resource_relation_role_id_resource_id_uindex" ON "public"."auth_role_resource_relation" USING btree (
  "role_id" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "resource_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_role_resource_relation
-- ----------------------------
ALTER TABLE "public"."auth_role_resource_relation" ADD CONSTRAINT "auth_role_resource_relation_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_user
-- ----------------------------
CREATE UNIQUE INDEX "auth_user_email_uindex" ON "public"."auth_user" USING btree (
  "email" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE UNIQUE INDEX "auth_user_phone_uindex" ON "public"."auth_user" USING btree (
  "phone" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
CREATE UNIQUE INDEX "auth_user_username_uindex" ON "public"."auth_user" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_user
-- ----------------------------
ALTER TABLE "public"."auth_user" ADD CONSTRAINT "auth_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table auth_user_role_relation
-- ----------------------------
CREATE UNIQUE INDEX "auth_user_role_relation_user_id_role_id_uindex" ON "public"."auth_user_role_relation" USING btree (
  "user_id" "pg_catalog"."int4_ops" ASC NULLS LAST,
  "role_id" "pg_catalog"."int4_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table auth_user_role_relation
-- ----------------------------
ALTER TABLE "public"."auth_user_role_relation" ADD CONSTRAINT "auth_user_role_relation_pkey" PRIMARY KEY ("id");
