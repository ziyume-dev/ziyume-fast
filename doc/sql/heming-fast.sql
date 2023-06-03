/*
 Navicat Premium Data Transfer

 Source Server         : localpg
 Source Server Type    : PostgreSQL
 Source Server Version : 150002 (150002)
 Source Host           : localhost:5432
 Source Catalog        : lfs
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 150002 (150002)
 File Encoding         : 65001

 Date: 30/05/2023 22:18:36
*/


-- ----------------------------
-- Sequence structure for heming_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_menu_id_seq";
CREATE SEQUENCE "public"."heming_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_resource_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_resource_id_seq";
CREATE SEQUENCE "public"."heming_resource_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_resource_type_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_resource_type_id_seq";
CREATE SEQUENCE "public"."heming_resource_type_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_role_id_seq";
CREATE SEQUENCE "public"."heming_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_role_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_role_menu_id_seq";
CREATE SEQUENCE "public"."heming_role_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_role_resource_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_role_resource_id_seq";
CREATE SEQUENCE "public"."heming_role_resource_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_user_id_seq";
CREATE SEQUENCE "public"."heming_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for heming_user_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."heming_user_role_id_seq";
CREATE SEQUENCE "public"."heming_user_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for heming_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_menu";
CREATE TABLE "public"."heming_menu" (
  "id" int8 NOT NULL DEFAULT nextval('heming_menu_id_seq'::regclass),
  "parent_id" int8,
  "title" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "redirect" varchar(255) COLLATE "pg_catalog"."default",
  "component" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "icon" text COLLATE "pg_catalog"."default",
  "disabled" int2 DEFAULT 0,
  "hidden" int2 DEFAULT 0,
  "keep_alive" int2 DEFAULT 0,
  "always_show" int2 DEFAULT 0,
  "is_root" int2 DEFAULT 0,
  "frame_src" varchar(255) COLLATE "pg_catalog"."default",
  "affix" int2 DEFAULT 0,
  "sort" int2,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6),
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."heming_menu"."parent_id" IS '父级 id';
COMMENT ON COLUMN "public"."heming_menu"."title" IS '菜单名称';
COMMENT ON COLUMN "public"."heming_menu"."name" IS '前端名称';
COMMENT ON COLUMN "public"."heming_menu"."path" IS '路由地址';
COMMENT ON COLUMN "public"."heming_menu"."redirect" IS '重定向地址';
COMMENT ON COLUMN "public"."heming_menu"."component" IS '组件路径';
COMMENT ON COLUMN "public"."heming_menu"."icon" IS '菜单图标名称';
COMMENT ON COLUMN "public"."heming_menu"."disabled" IS '禁用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."heming_menu"."hidden" IS '菜单显示状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."heming_menu"."keep_alive" IS '是否缓存：0->否；1->是';
COMMENT ON COLUMN "public"."heming_menu"."always_show" IS '取消自动计算根路由模式：0->否；1->是';
COMMENT ON COLUMN "public"."heming_menu"."is_root" IS '是否跟路由：0->否；1->是';
COMMENT ON COLUMN "public"."heming_menu"."frame_src" IS '内联外部地址';
COMMENT ON COLUMN "public"."heming_menu"."affix" IS '是否固定：0->否；1->是';
COMMENT ON COLUMN "public"."heming_menu"."sort" IS '排序';
COMMENT ON COLUMN "public"."heming_menu"."creator" IS '创建者';
COMMENT ON COLUMN "public"."heming_menu"."updater" IS '更新者';
COMMENT ON COLUMN "public"."heming_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."heming_menu"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."heming_menu"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."heming_menu" IS '菜单';

-- ----------------------------
-- Records of heming_menu
-- ----------------------------
INSERT INTO "public"."heming_menu" VALUES (15, 14, '个人设置', 'setting_person', 'person', NULL, '/setting/person/index', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 10, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (5, 0, '系统管理', 'system', '/system', '/system/user/index', 'Layout', 'AppstoreOutlined', 0, 0, 1, 0, 1, NULL, 0, 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (8, 5, '资源管理', 'resource', 'resource', NULL, '/system/resource/index', 'TagOutlined', 0, 0, 1, 0, 0, NULL, 0, 7, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (10, 5, '资源类别管理', 'resource_type', 'resourceType', NULL, '/system/resourceType/index', 'TagsOutlined', 0, 0, 1, 0, 0, NULL, 0, 8, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (6, 5, '用户管理', 'user', 'user', '', '/system/user/index', 'TeamOutlined', 0, 0, 1, 0, 0, NULL, 0, 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (11, 5, '角色管理', 'role', 'role', NULL, '/system/role/index', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 5, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (7, 5, '菜单管理', 'menu', 'menu', NULL, '/system/menu/index', 'MenuOutlined', 0, 0, 1, 0, 0, NULL, 0, 6, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (12, 0, 'Dashboard', 'Dashboard', '/dashboard', '/dashboard/index', 'Layout', 'DashboardOutlined', 0, 0, 1, 0, 0, NULL, 0, 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (13, 12, '控制台', 'console', 'index', NULL, '/dashboard/index', 'DashboardOutlined', 0, 0, 1, 0, 0, NULL, 0, 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_menu" VALUES (14, 0, '设置', 'setting', '/setting', '/setting/person/index', 'Layout', 'UserOutlined', 0, 0, 1, 0, 0, NULL, 0, 9, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_resource";
CREATE TABLE "public"."heming_resource" (
  "id" int8 NOT NULL DEFAULT nextval('heming_resource_id_seq'::regclass),
  "name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "url" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "type_code" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "route_key" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "sort" int2 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0),
  "update_time" timestamp(0),
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."heming_resource"."name" IS '资源名称';
COMMENT ON COLUMN "public"."heming_resource"."url" IS '资源路径';
COMMENT ON COLUMN "public"."heming_resource"."description" IS '资源描述';
COMMENT ON COLUMN "public"."heming_resource"."type_code" IS '资源类型 code';
COMMENT ON COLUMN "public"."heming_resource"."route_key" IS '路由分配 key';
COMMENT ON COLUMN "public"."heming_resource"."sort" IS '排序';
COMMENT ON COLUMN "public"."heming_resource"."creator" IS '创建者';
COMMENT ON COLUMN "public"."heming_resource"."updater" IS '更新者';
COMMENT ON COLUMN "public"."heming_resource"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."heming_resource"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."heming_resource"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."heming_resource" IS '资源';

-- ----------------------------
-- Records of heming_resource
-- ----------------------------
INSERT INTO "public"."heming_resource" VALUES (2, '获取用户信息', '/user/info', '获取用户信息接口', '1', '1', 0, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (3, '用户分页', '/user/pageList', '获取用户分页列表', '1', '1', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (4, '删除用户', '/user/deleteUser/**', '删除用户', '1', '1', 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (5, '角色分页', '/role/pageList', '获取角色分页列表', '2', '1', 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (6, '资源分页', '/resource/pageList', '获取资源分页列表', '3', '1', 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (7, '资源类别分页', '/resurceType/pageList', '获取资源类别分页列表', '4', '1', 5, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource" VALUES (8, '菜单分页', '/menu/pageList', '获取菜单分页列表', '5', '1', 6, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_resource_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_resource_type";
CREATE TABLE "public"."heming_resource_type" (
  "id" int8 NOT NULL DEFAULT nextval('heming_resource_type_id_seq'::regclass),
  "name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "code" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(200) COLLATE "pg_catalog"."default",
  "sort" int2,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0),
  "update_time" timestamp(0),
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."heming_resource_type"."name" IS '名称';
COMMENT ON COLUMN "public"."heming_resource_type"."code" IS '编码';
COMMENT ON COLUMN "public"."heming_resource_type"."description" IS '排序';
COMMENT ON COLUMN "public"."heming_resource_type"."sort" IS '排序';
COMMENT ON COLUMN "public"."heming_resource_type"."creator" IS '创建者';
COMMENT ON COLUMN "public"."heming_resource_type"."updater" IS '更新者';
COMMENT ON COLUMN "public"."heming_resource_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."heming_resource_type"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."heming_resource_type"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."heming_resource_type" IS '资源类型';

-- ----------------------------
-- Records of heming_resource_type
-- ----------------------------
INSERT INTO "public"."heming_resource_type" VALUES (1, '用户管理', 'user', '用户管理分类', 1, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource_type" VALUES (2, '角色管理', 'role', '角色管理分类', 2, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource_type" VALUES (3, '资源管理', 'resource', '资源管理分类', 3, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource_type" VALUES (4, '资源类别管理', 'resurceType', '资源类别管理分类', 4, NULL, NULL, NULL, NULL, 1);
INSERT INTO "public"."heming_resource_type" VALUES (5, '菜单管理', 'menu', '菜单管理分类', 5, NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for heming_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_role";
CREATE TABLE "public"."heming_role" (
  "id" int8 NOT NULL DEFAULT nextval('heming_role_id_seq'::regclass),
  "name" varchar(64) COLLATE "pg_catalog"."default",
  "code" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default",
  "sort" int2,
  "status" int2 NOT NULL DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0),
  "update_time" timestamp(0),
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."heming_role"."name" IS '角色名称';
COMMENT ON COLUMN "public"."heming_role"."code" IS '角色编码';
COMMENT ON COLUMN "public"."heming_role"."description" IS '描述';
COMMENT ON COLUMN "public"."heming_role"."sort" IS '排序';
COMMENT ON COLUMN "public"."heming_role"."status" IS '角色启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."heming_role"."creator" IS '创建者';
COMMENT ON COLUMN "public"."heming_role"."updater" IS '更新者';
COMMENT ON COLUMN "public"."heming_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."heming_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."heming_role"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."heming_role" IS '角色';

-- ----------------------------
-- Records of heming_role
-- ----------------------------
INSERT INTO "public"."heming_role" VALUES (1, '超级管理员', 'platform-super-admin', '超级管理员~', 1, 1, 'heming', 'heming', '2023-05-25 20:16:38', '2023-05-25 20:16:40', 1);

-- ----------------------------
-- Table structure for heming_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_role_menu";
CREATE TABLE "public"."heming_role_menu" (
  "id" int8 NOT NULL DEFAULT nextval('heming_role_menu_id_seq'::regclass),
  "role_id" int8 NOT NULL,
  "menu_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."heming_role_menu"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."heming_role_menu"."menu_id" IS '菜单id';
COMMENT ON TABLE "public"."heming_role_menu" IS '角色菜单关系表';

-- ----------------------------
-- Records of heming_role_menu
-- ----------------------------
INSERT INTO "public"."heming_role_menu" VALUES (5, 1, 5);
INSERT INTO "public"."heming_role_menu" VALUES (6, 1, 6);
INSERT INTO "public"."heming_role_menu" VALUES (7, 1, 7);
INSERT INTO "public"."heming_role_menu" VALUES (8, 1, 8);
INSERT INTO "public"."heming_role_menu" VALUES (9, 1, 10);
INSERT INTO "public"."heming_role_menu" VALUES (10, 1, 11);
INSERT INTO "public"."heming_role_menu" VALUES (11, 1, 12);
INSERT INTO "public"."heming_role_menu" VALUES (12, 1, 13);
INSERT INTO "public"."heming_role_menu" VALUES (13, 1, 14);
INSERT INTO "public"."heming_role_menu" VALUES (14, 1, 15);

-- ----------------------------
-- Table structure for heming_role_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_role_resource";
CREATE TABLE "public"."heming_role_resource" (
  "id" int8 NOT NULL DEFAULT nextval('heming_role_resource_id_seq'::regclass),
  "role_id" int8 NOT NULL,
  "resource_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."heming_role_resource"."role_id" IS '角色id';
COMMENT ON COLUMN "public"."heming_role_resource"."resource_id" IS '资源id';

-- ----------------------------
-- Records of heming_role_resource
-- ----------------------------
INSERT INTO "public"."heming_role_resource" VALUES (2, 1, 2);
INSERT INTO "public"."heming_role_resource" VALUES (3, 1, 3);
INSERT INTO "public"."heming_role_resource" VALUES (4, 1, 4);
INSERT INTO "public"."heming_role_resource" VALUES (5, 1, 5);
INSERT INTO "public"."heming_role_resource" VALUES (6, 1, 6);
INSERT INTO "public"."heming_role_resource" VALUES (7, 1, 7);
INSERT INTO "public"."heming_role_resource" VALUES (8, 1, 8);

-- ----------------------------
-- Table structure for heming_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_user";
CREATE TABLE "public"."heming_user" (
  "id" int8 NOT NULL DEFAULT nextval('heming_user_id_seq'::regclass),
  "username" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "email" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "name" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "telephone" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "remark" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int2,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_time" timestamp(6),
  "status" int2 NOT NULL DEFAULT 0,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."heming_user"."username" IS '账号(用户名)';
COMMENT ON COLUMN "public"."heming_user"."password" IS '密码';
COMMENT ON COLUMN "public"."heming_user"."avatar" IS '头像(地址)';
COMMENT ON COLUMN "public"."heming_user"."email" IS '邮箱';
COMMENT ON COLUMN "public"."heming_user"."name" IS '昵称';
COMMENT ON COLUMN "public"."heming_user"."telephone" IS '手机';
COMMENT ON COLUMN "public"."heming_user"."remark" IS '备注';
COMMENT ON COLUMN "public"."heming_user"."sort" IS '排序';
COMMENT ON COLUMN "public"."heming_user"."creator" IS '创建者';
COMMENT ON COLUMN "public"."heming_user"."updater" IS '更新者';
COMMENT ON COLUMN "public"."heming_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."heming_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."heming_user"."status" IS '帐号启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."heming_user"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."heming_user" IS '用户';

-- ----------------------------
-- Records of heming_user
-- ----------------------------
INSERT INTO "public"."heming_user" VALUES (5, 'test', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', '1', '测试', '1', '1', 2, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO "public"."heming_user" VALUES (1, 'heming', '94edf28c6d6da38fd35d7ad53e485307f89fbeaf120485c8d17a43f323deee71', 'https://besscroft.com/uploads/avatar.jpeg', 'heming@qq.com', '鹤鸣', '13612345678', '你好', 1, 'heming', 'heming', '2023-05-25 20:18:52', '2023-05-25 20:18:54', 1, 1);

-- ----------------------------
-- Table structure for heming_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."heming_user_role";
CREATE TABLE "public"."heming_user_role" (
  "id" int8 NOT NULL DEFAULT nextval('heming_user_role_id_seq'::regclass),
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."heming_user_role"."user_id" IS '用户id';
COMMENT ON COLUMN "public"."heming_user_role"."role_id" IS '角色id';
COMMENT ON TABLE "public"."heming_user_role" IS '用户角色关系表';

-- ----------------------------
-- Records of heming_user_role
-- ----------------------------
INSERT INTO "public"."heming_user_role" VALUES (1, 1, 1);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_menu_id_seq"
OWNED BY "public"."heming_menu"."id";
SELECT setval('"public"."heming_menu_id_seq"', 15, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_resource_id_seq"
OWNED BY "public"."heming_resource"."id";
SELECT setval('"public"."heming_resource_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_resource_type_id_seq"
OWNED BY "public"."heming_resource_type"."id";
SELECT setval('"public"."heming_resource_type_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_role_id_seq"
OWNED BY "public"."heming_role"."id";
SELECT setval('"public"."heming_role_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_role_menu_id_seq"
OWNED BY "public"."heming_role_menu"."id";
SELECT setval('"public"."heming_role_menu_id_seq"', 14, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_role_resource_id_seq"
OWNED BY "public"."heming_role_resource"."id";
SELECT setval('"public"."heming_role_resource_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_user_id_seq"
OWNED BY "public"."heming_user"."id";
SELECT setval('"public"."heming_user_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."heming_user_role_id_seq"
OWNED BY "public"."heming_user_role"."id";
SELECT setval('"public"."heming_user_role_id_seq"', 1, false);

-- ----------------------------
-- Uniques structure for table heming_menu
-- ----------------------------
ALTER TABLE "public"."heming_menu" ADD CONSTRAINT "heming_menu_name_key" UNIQUE ("name");

-- ----------------------------
-- Primary Key structure for table heming_menu
-- ----------------------------
ALTER TABLE "public"."heming_menu" ADD CONSTRAINT "heming_menu_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table heming_resource
-- ----------------------------
ALTER TABLE "public"."heming_resource" ADD CONSTRAINT "heming_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table heming_resource_type
-- ----------------------------
ALTER TABLE "public"."heming_resource_type" ADD CONSTRAINT "heming_resource_type_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table heming_role
-- ----------------------------
ALTER TABLE "public"."heming_role" ADD CONSTRAINT "heming_role_code_key" UNIQUE ("code");

-- ----------------------------
-- Primary Key structure for table heming_role
-- ----------------------------
ALTER TABLE "public"."heming_role" ADD CONSTRAINT "heming_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table heming_role_menu
-- ----------------------------
CREATE UNIQUE INDEX "heming_role_menu_role_id_menu_id_uindex" ON "public"."heming_role_menu" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST,
  "menu_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table heming_role_menu
-- ----------------------------
ALTER TABLE "public"."heming_role_menu" ADD CONSTRAINT "heming_role_menu_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table heming_role_resource
-- ----------------------------
CREATE UNIQUE INDEX "heming_role_resource_role_id_resource_id_uindex" ON "public"."heming_role_resource" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST,
  "resource_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table heming_role_resource
-- ----------------------------
ALTER TABLE "public"."heming_role_resource" ADD CONSTRAINT "heming_role_resource_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table heming_user
-- ----------------------------
ALTER TABLE "public"."heming_user" ADD CONSTRAINT "heming_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table heming_user_role
-- ----------------------------
CREATE UNIQUE INDEX "heming_user_role_user_id_role_id_uindex" ON "public"."heming_user_role" USING btree (
  "user_id" "pg_catalog"."int8_ops" ASC NULLS LAST,
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table heming_user_role
-- ----------------------------
ALTER TABLE "public"."heming_user_role" ADD CONSTRAINT "heming_user_role_pkey" PRIMARY KEY ("id");
