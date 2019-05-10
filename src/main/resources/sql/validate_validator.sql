/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : splendid_pro

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-05-10 15:15:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for validate_validator
-- ----------------------------
DROP TABLE IF EXISTS `validate_validator`;
CREATE TABLE `validate_validator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `code` varchar(50) NOT NULL COMMENT '校验器码',
  `type` varchar(20) DEFAULT NULL COMMENT '校验器类型',
  `name` varchar(50) DEFAULT NULL COMMENT '校验器名称',
  `v_desc` varchar(255) DEFAULT NULL COMMENT '校验器说明',
  `rule` varchar(400) DEFAULT NULL COMMENT '规则',
  `tips` varchar(255) DEFAULT NULL COMMENT '校验提示',
  `creator` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建者 : 创建者',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `modifier` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '修改者 : 修改者',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of validate_validator
-- ----------------------------
INSERT INTO `validate_validator` VALUES ('1', 'LOCAL_ZH', 'LOCAL', '中文校验', '校验是否是中文', 'LOCAL', '必须全部是中文', 'SYSTEM', '2018-03-01 17:01:33', 'SYSTEM', '2019-05-09 14:21:05', '0');
INSERT INTO `validate_validator` VALUES ('2', 'SP_CHAR', 'RE_EX', '特殊字符校验', '正则，校验是否有特殊字符', '[`@#$^&￥……|\\[\\]<>/{}]', '不能有特殊字符', 'SYSTEM', '2018-03-01 17:01:33', 'SYSTEM', '2019-05-09 14:21:06', '0');
INSERT INTO `validate_validator` VALUES ('3', 'LETTERS_NUM', 'RE_EX', '字母数字校验', '正则，允许包含字母+数字', '^[a-z0-9A-Z\\.\\+]+$', '只允许是数字、字母、\".\"、\"+\"的组合', 'SYSTEM', '2018-03-01 17:01:33', 'SYSTEM', '2019-05-09 14:21:07', '0');
INSERT INTO `validate_validator` VALUES ('4', 'LOCAL_EN_ZH', 'LOCAL', '中英文校验', '可以是英文、中文混合', null, '只能是中文、英文或者混合', 'SYSTEM', '2018-03-01 17:01:33', 'SYSTEM', '2019-05-09 14:21:09', '0');
