/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : splendid_pro

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-05-10 15:15:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for validate_standard_info
-- ----------------------------
DROP TABLE IF EXISTS `validate_standard_info`;
CREATE TABLE `validate_standard_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `standard_code` varchar(20) DEFAULT NULL COMMENT '标准code\n',
  `standard_name` varchar(45) DEFAULT NULL,
  `standard_desc` varchar(50) DEFAULT NULL COMMENT '描述',
  `creator` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建者 : 创建者',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `modifier` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '修改者 : 修改者',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 : 是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='验收标准信息记录表';

-- ----------------------------
-- Records of validate_standard_info
-- ----------------------------
INSERT INTO `validate_standard_info` VALUES ('1', 'PAY_1', '支付', '与支付相关的接口', 'SYSTEM', '2018-07-06 16:34:48', 'SYSTEM', '2019-05-09 14:38:47', '0');
