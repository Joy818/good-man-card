/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : db_gmc

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 15/03/2022 21:47:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gmc_task
-- ----------------------------
DROP TABLE IF EXISTS `gmc_task`;
CREATE TABLE `gmc_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rid` varchar(255) DEFAULT NULL COMMENT '随机唯一ID',
  `type` varchar(255) DEFAULT NULL COMMENT '任务类型',
  `params` text COMMENT '任务参数',
  `state` int DEFAULT NULL COMMENT '任务状态',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '附加信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='任务表';

SET FOREIGN_KEY_CHECKS = 1;
