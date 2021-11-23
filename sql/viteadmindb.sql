/*
 Navicat Premium Data Transfer

 Source Server         : LOCALDB
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : viteadmindb

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 23/11/2021 14:58:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'ICON',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单地址',
  `pid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id，0为父类，子类时p_id为父id的id',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '权限管理', 'nil-close-circle', '/auth', '0', 2);
INSERT INTO `admin_menu` VALUES ('1461683551938236417', 'DEMO', 'nil-compass', '/demo', '0', 0);
INSERT INTO `admin_menu` VALUES ('1461688257905381377', 'Demo3', 'nil-location', '/demo/demo1', '1461683551938236417', 0);
INSERT INTO `admin_menu` VALUES ('1462088809587904514', '首页', 'nil-folder', '/home', '0', 9999);
INSERT INTO `admin_menu` VALUES ('1462270499270660098', '修改密码', 'nil-insurance', '/auth/pass', '1', -1);
INSERT INTO `admin_menu` VALUES ('2', '角色管理', 'nil-folder', '/auth/role', '1', 0);
INSERT INTO `admin_menu` VALUES ('3', '用户管理', 'nil-Dollar', '/auth/user', '1', 0);
INSERT INTO `admin_menu` VALUES ('4', '菜单管理', 'nil-save', '/auth/menu', '1', 0);

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `role_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'key',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role
-- ----------------------------
INSERT INTO `admin_role` VALUES ('10096', '管理员', 'admin');
INSERT INTO `admin_role` VALUES ('1461341427988299777', '超超级管理员', 'adminplus');
INSERT INTO `admin_role` VALUES ('79', '普通管理员2', 'simple_admin');

-- ----------------------------
-- Table structure for admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_role_menu`;
CREATE TABLE `admin_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色Id',
  `menu_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单Id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '将角色和菜单关联' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_role_menu
-- ----------------------------
INSERT INTO `admin_role_menu` VALUES ('1462043688620228610', '79', '1');
INSERT INTO `admin_role_menu` VALUES ('1462043688620228611', '79', '2');
INSERT INTO `admin_role_menu` VALUES ('1462043688620228612', '79', '3');
INSERT INTO `admin_role_menu` VALUES ('1462043688620228613', '79', '4');
INSERT INTO `admin_role_menu` VALUES ('1462043688620228614', '79', '1461683551938236417');
INSERT INTO `admin_role_menu` VALUES ('1462043688620228615', '79', '1461688257905381377');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911810', '10096', '1');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911811', '10096', '1462270499270660098');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911812', '10096', '2');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911813', '10096', '3');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911814', '10096', '4');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911815', '10096', '1461683551938236417');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911816', '10096', '1461688257905381377');
INSERT INTO `admin_role_menu` VALUES ('1462276485393911817', '10096', '1462088809587904514');

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `role_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系人姓名',
  `status` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启用状态1启用0禁用',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户名和密码' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES ('1461314894502514689', '王华华', 'whh', '$2a$10$jVqHK2X6kmUan3VB0r4qIeYtsKd5Ap.vn1plDxXIz1dtG8zwk4b.a', '10096', '1', NULL);
INSERT INTO `admin_user` VALUES ('1462027513123266562', '陈建', 'chen', '$2a$10$2O8r76NUMvfCMoLHoK4A8ujCxbammsGoVwLjBm/ZhfpRjhA1s/stm', '1461341427988299777', '1', NULL);
INSERT INTO `admin_user` VALUES ('2', '阿萨德', 'admin', '$2a$10$870yXy8dcnPejMUp6a50VOQDlom1gGQBHCHr/EMQNRT8Sy.aBEOhG', '10096', '0', '2021090999991011');

SET FOREIGN_KEY_CHECKS = 1;
