/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 80011
Source Host           : localhost:3306
Source Database       : splendid_pro

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2019-05-10 15:14:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for validate_field_info_copy
-- ----------------------------
DROP TABLE IF EXISTS `validate_field_info_copy`;
CREATE TABLE `validate_field_info_copy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `interface_id` bigint(20) NOT NULL,
  `field_name` varchar(50) DEFAULT NULL COMMENT '字段名称',
  `field_code` varchar(50) DEFAULT NULL COMMENT '字段code\n',
  `field_desc` varchar(1000) DEFAULT NULL COMMENT '字段描述',
  `data_type` varchar(30) DEFAULT NULL,
  `data_enum` varchar(200) DEFAULT NULL,
  `parent_field` varchar(50) DEFAULT NULL COMMENT '父节点',
  `field_level` varchar(2) DEFAULT NULL COMMENT '非空',
  `is_parent` tinyint(1) DEFAULT '0' COMMENT '是否是父节点',
  `index_level` tinyint(1) DEFAULT NULL COMMENT '层级',
  `validators_code` varchar(50) DEFAULT NULL,
  `creator` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '创建者 : 创建者',
  `gmt_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 : 创建时间',
  `modifier` varchar(50) NOT NULL DEFAULT 'SYSTEM' COMMENT '修改者 : 修改者',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 : 是否删除',
  PRIMARY KEY (`id`),
  KEY `fk_mdas_standard_field_mdas_standard_interface1_idx` (`interface_id`),
  CONSTRAINT `validate_field_info_copy_ibfk_1` FOREIGN KEY (`interface_id`) REFERENCES `validate_interface` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3534 DEFAULT CHARSET=utf8 COMMENT='验收字段标准';

-- ----------------------------
-- Records of validate_field_info_copy
-- ----------------------------
INSERT INTO `validate_field_info_copy` VALUES ('2001', '1001', '订单', 'order', null, 'List', null, null, '1', '1', '1', null, 'SYSTEM', '2019-05-10 13:51:07', 'SYSTEM', '2019-05-10 13:51:07', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2002', '1001', '订单号', 'orderNo', null, 'String', null, 'order', '1', '0', '2', null, 'SYSTEM', '2019-05-10 13:51:49', 'SYSTEM', '2019-05-10 13:52:25', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2003', '1001', '下单时间', 'orderDate', null, 'Date1', null, 'order', '1', '0', '2', null, 'SYSTEM', '2019-05-10 13:53:21', 'SYSTEM', '2019-05-10 13:53:31', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2004', '1001', '订单金额', 'orderAmount', null, 'Number', null, 'order', '1', '0', '2', null, 'SYSTEM', '2019-05-10 13:54:09', 'SYSTEM', '2019-05-10 13:54:29', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2005', '1001', '商品数量', 'purchaseQty', null, 'Number', null, 'order', '1', '0', '2', null, 'SYSTEM', '2019-05-10 13:55:01', 'SYSTEM', '2019-05-10 13:55:55', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2006', '1001', '邮寄地址', 'mailingAddress', null, 'Object', null, 'order', '1', '1', '2', null, 'SYSTEM', '2019-05-10 13:57:19', 'SYSTEM', '2019-05-10 14:10:13', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2007', '1001', '省', 'province', null, 'String', null, 'mailingAddress', '1', '0', '3', null, 'SYSTEM', '2019-05-10 13:58:55', 'SYSTEM', '2019-05-10 13:59:24', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2008', '1001', '市', 'city', null, 'String', null, 'mailingAddress', '1', '0', '3', null, 'SYSTEM', '2019-05-10 13:59:57', 'SYSTEM', '2019-05-10 14:00:05', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2009', '1001', '区', 'area', null, 'String', null, 'mailingAddress', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:00:43', 'SYSTEM', '2019-05-10 14:01:25', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2010', '1001', '街道', 'street', null, 'String', null, 'mailingAddress', '2', '0', '3', null, 'SYSTEM', '2019-05-10 14:02:41', 'SYSTEM', '2019-05-10 14:02:56', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2011', '1001', '详细地址', 'address', null, 'String', null, 'mailingAddress', '1', '0', '3', 'LOCAL_EN_ZH', 'SYSTEM', '2019-05-10 14:03:17', 'SYSTEM', '2019-05-10 14:22:15', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2012', '1001', '收件人', 'addressee', null, 'String', null, 'mailingAddress', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:04:48', 'SYSTEM', '2019-05-10 14:06:18', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2013', '1001', '商品列表', 'purchaseList', null, 'List', null, 'order', '1', '1', '2', null, 'SYSTEM', '2019-05-10 14:06:56', 'SYSTEM', '2019-05-10 14:10:08', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2014', '1001', '名称', 'name', null, 'String', null, 'purchaseList', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:07:46', 'SYSTEM', '2019-05-10 14:08:09', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2015', '1001', '商品类型', 'type', '0:不明;1:电子商品;2:办公用品;', 'String', '0,1,2', 'purchaseList', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:09:33', 'SYSTEM', '2019-05-10 14:10:01', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2016', '1001', '商品价格', 'price', null, 'Number', null, 'purchaseList', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:12:50', 'SYSTEM', '2019-05-10 14:12:59', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2017', '1001', '商品数量', 'qty', null, 'Number', null, 'purchaseList', '1', '0', '3', null, 'SYSTEM', '2019-05-10 14:13:23', 'SYSTEM', '2019-05-10 14:13:31', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2018', '1001', '折扣券列表', 'couponList', null, 'List', null, 'purchaseList', '2', '0', '3', null, 'SYSTEM', '2019-05-10 14:14:34', 'SYSTEM', '2019-05-10 14:16:36', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2019', '1001', '折扣券码', 'num', '', 'String', null, 'couponList', '1', '0', '4', null, 'SYSTEM', '2019-05-10 14:16:58', 'SYSTEM', '2019-05-10 14:17:15', '0');
INSERT INTO `validate_field_info_copy` VALUES ('2020', '1001', '折扣量', 'price', null, 'Number', null, 'couponList', '2', '0', '4', null, 'SYSTEM', '2019-05-10 14:20:20', 'SYSTEM', '2019-05-10 14:21:31', '0');
