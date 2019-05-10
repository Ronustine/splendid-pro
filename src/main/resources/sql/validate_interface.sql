/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : splendid_pro

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-05-10 15:15:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for validate_interface
-- ----------------------------
DROP TABLE IF EXISTS `validate_interface`;
CREATE TABLE `validate_interface` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `standard_id` bigint(20) NOT NULL COMMENT '标准code\n',
  `interface_name` varchar(45) DEFAULT NULL COMMENT '接口名称',
  `interface_code` varchar(45) DEFAULT NULL COMMENT '接口code ',
  `interface_desc` varchar(45) DEFAULT NULL COMMENT '接口描述',
  `creator` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建者 : 创建者',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `modifier` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '修改者 : 修改者',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 : 是否删除',
  PRIMARY KEY (`id`),
  KEY `fk_mdas_standard_interface_mdas_standard1_idx` (`standard_id`),
  CONSTRAINT `fk_mdas_standard_interface_mdas_standard1` FOREIGN KEY (`standard_id`) REFERENCES `validate_standard_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COMMENT='验收标准信息记录表';

-- ----------------------------
-- Records of validate_interface
-- ----------------------------
INSERT INTO `validate_interface` VALUES ('1001', '1', '案例1', 'demo1', '案例1', 'SYSTEM', '2019-05-10 13:47:29', 'SYSTEM', '2019-05-10 13:47:29', '0');
