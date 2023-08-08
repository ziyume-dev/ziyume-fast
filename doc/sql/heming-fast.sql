/*
 Source Server         : localmysql
 Source Server Type    : MySQL
 Source Server Version : 101102 (10.11.2-MariaDB-1:10.11.2+maria~ubu2204)
 Source Host           : localhost:3306
 Source Schema         : heming-fast

 Target Server Type    : MySQL
 Target Server Version : 101102 (10.11.2-MariaDB-1:10.11.2+maria~ubu2204)
 File Encoding         : 65001

 Date: 08/08/2023 10:24:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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

SET FOREIGN_KEY_CHECKS = 1;
