DROP TABLE IF EXISTS PRODUCT;
CREATE TABLE PRODUCT(
  id INTEGER,
  description VARCHAR2(1000)
);

INSERT INTO PRODUCT (id, description) VALUES (1, 'product 1');
INSERT INTO PRODUCT (id, description) VALUES (2, 'product 2');
INSERT INTO PRODUCT (id, description) VALUES (1, 'product 3');
INSERT INTO PRODUCT (id, description) VALUES (4, 'product 4');

DROP TABLE IF EXISTS OREDER_GOODS;
CREATE TABLE PRODUCT(
  id INTEGER,
  description VARCHAR2(1000)
);


/*Table structure for table `z_gyl_admin` */

DROP TABLE IF EXISTS `z_gyl_admin`;

CREATE TABLE `z_gyl_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` varchar(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `ext` varchar(255) DEFAULT '' COMMENT '分机号',
  `isqq` varchar(255) DEFAULT '' COMMENT '是否为qq',
  `name` varchar(255) DEFAULT '' COMMENT '姓名',
  `password` varchar(255) DEFAULT '' COMMENT '密码',
  `phone` varchar(255) DEFAULT '' COMMENT '手机号码',
  `sex` varchar(11) DEFAULT '' COMMENT '性别：1：男 0：女',
  `status` int(1) DEFAULT '1' COMMENT '1:启用 0：已停用 -1：已删除',
  `tel` varchar(255) DEFAULT '' COMMENT '电话号码',
  `theme` varchar(255) DEFAULT 'deafult' COMMENT '主题',
  `username` varchar(255) DEFAULT '' COMMENT '用户名（登录名）',
  `duty_id` bigint(20) DEFAULT '0' COMMENT '职位编号',
  `org_id` bigint(20) DEFAULT '0' COMMENT '公司编号',
  PRIMARY KEY (`id`),
  KEY `FK_g9am66ran5ptw752edjyaf2rc` (`duty_id`),
  KEY `FK_asrf3cd4lvc7cl9stwj2kc9tu` (`org_id`)
) ;

/*Table structure for table `z_gyl_admin_allow` */

DROP TABLE IF EXISTS `z_gyl_admin_allow`;

CREATE TABLE `z_gyl_admin_allow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_ids` text COMMENT '用户ids',
  `admin_names` text COMMENT '用户名',
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Table structure for table `z_gyl_admin_menu` */

DROP TABLE IF EXISTS `z_gyl_admin_menu`;

CREATE TABLE `z_gyl_admin_menu` (
  `admins_id` bigint(20) NOT NULL,
  `menus_id` bigint(20) NOT NULL,
  PRIMARY KEY (`admins_id`,`menus_id`),
  KEY `FK_b5idmw1ctfpialhkpwo2ny9jp` (`menus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_admin_role` */

DROP TABLE IF EXISTS `z_gyl_admin_role`;

CREATE TABLE `z_gyl_admin_role` (
  `admins_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`admins_id`,`roles_id`),
  KEY `FK_eo4dnkwwg5rh193c7i564bds5` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_change_duty` */

DROP TABLE IF EXISTS `z_gyl_change_duty`;

CREATE TABLE `z_gyl_change_duty` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `admin_id` bigint(20) DEFAULT '0' COMMENT '交易员id',
  `enable_day` date DEFAULT NULL COMMENT '启用日期',
  `creator` bigint(20) DEFAULT NULL COMMENT '创建人账号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` tinyint(4) DEFAULT '0' COMMENT '0 待生效 1已生效',
  `old_org_id` bigint(20) DEFAULT NULL COMMENT '原部门id',
  `old_duty_id` bigint(20) DEFAULT NULL COMMENT '原职位id',
  `new_org_id` bigint(20) DEFAULT NULL COMMENT '新部门id',
  `new_duty_id` bigint(20) DEFAULT NULL COMMENT '新职位id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `z_gyl_diy_tag` */

DROP TABLE IF EXISTS `z_gyl_diy_tag`;

CREATE TABLE `z_gyl_diy_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(256) NOT NULL COMMENT '标签',
  `create_time` int(11) DEFAULT '0' COMMENT '添加时间',
  `modify_time` int(11) DEFAULT '0' COMMENT '更新时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 0-隐藏  1-显示',
  `admin_id` int(10) DEFAULT '0' COMMENT '创建人id',
  PRIMARY KEY (`id`),
  KEY `FK_fjri7hdtnkiykuys79bwlghhf` (`admin_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Table structure for table `z_gyl_duty` */

DROP TABLE IF EXISTS `z_gyl_duty`;

CREATE TABLE `z_gyl_duty` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '删除标识0：正常，1：删除',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `name` varchar(255) DEFAULT '' COMMENT '职位名称',
  `is_super` varchar(1) DEFAULT '0' COMMENT '是否部门负责人 0：不是 1：是（可以查看和统计订单）',
  `sort` int(11) DEFAULT '9999' COMMENT '排序',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '结构树',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级职位',
  `dutyTag` varchar(50) DEFAULT 'OTHER' COMMENT '职位标签',
  PRIMARY KEY (`id`),
  KEY `FK_6axiih68bcrsyvyfmx2ixifgg` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=403 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_log` */

DROP TABLE IF EXISTS `z_gyl_log`;

CREATE TABLE `z_gyl_log` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `exception` text,
  `method` varchar(255) DEFAULT NULL,
  `params` text,
  `remoteAddr` varchar(255) DEFAULT NULL,
  `requestUri` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `userAgent` varchar(255) DEFAULT NULL,
  `createBy_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_jexhhoknfnnlmu5833e33aoiv` (`createBy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46084 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_menu` */

DROP TABLE IF EXISTS `z_gyl_menu`;

CREATE TABLE `z_gyl_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '删除标识0：正常，1：删除',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `grade` int(11) DEFAULT '0' COMMENT '级别',
  `list_order` int(11) DEFAULT '9999' COMMENT '排序',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `permission` varchar(255) DEFAULT '' COMMENT '权限标识',
  `tree_path` varchar(255) DEFAULT ',' COMMENT '父ids树',
  `type` varchar(255) DEFAULT 'OPT' COMMENT 'NAV为导航，Menu为菜单，OPT为操作',
  `url` varchar(255) DEFAULT '' COMMENT '菜单url',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `icon` varchar(100) DEFAULT NULL COMMENT '显示图标',
  `target` varchar(30) DEFAULT '_self' COMMENT '目标',
  PRIMARY KEY (`id`),
  KEY `FK_hlislme6ampj07ctw1p2fgvu6` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_org` */

DROP TABLE IF EXISTS `z_gyl_org`;

CREATE TABLE `z_gyl_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '删除标识0：正常，1：删除',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `address` varchar(255) DEFAULT '' COMMENT '地址',
  `code` varchar(255) DEFAULT '' COMMENT '编号',
  `grade` int(11) DEFAULT '1' COMMENT '级别',
  `name` varchar(255) DEFAULT '' COMMENT '名称',
  `tree_path` varchar(255) DEFAULT NULL COMMENT '父结构树（父ids）',
  `type` varchar(255) DEFAULT '' COMMENT '类型（1：公司；2：部门；）',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `region_id` bigint(20) DEFAULT NULL COMMENT '地区id',
  `chief` bigint(20) DEFAULT NULL COMMENT '负责人',
  `parentLead` bigint(20) DEFAULT NULL COMMENT '上级领导',
  `chiefName` varchar(255) DEFAULT NULL COMMENT '负责人名称',
  `parentLeadName` varchar(255) DEFAULT NULL COMMENT '上级领导名称',
  `org_tag` varchar(50) DEFAULT 'OTHER' COMMENT '部门标签PVC,PP PE,国际贸易,OTHER',
  `sort` int(10) DEFAULT '255' COMMENT '排序',
  PRIMARY KEY (`id`),
  KEY `FK_2b9hr8cu2l8mdt1g07vprbgcv` (`parent_id`),
  KEY `FK_ivbxhcb9xdpeos8w8695n9c5e` (`region_id`),
  KEY `FK_3ej1tmh60cld5ke8ygrplsdo7` (`chief`),
  KEY `FK_qk8fmrwcxu19b6nfp5ovgt4xu` (`parentLead`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_org_role` */

DROP TABLE IF EXISTS `z_gyl_org_role`;

CREATE TABLE `z_gyl_org_role` (
  `z_gyl_role_id` bigint(20) NOT NULL,
  `orgs_id` bigint(20) NOT NULL,
  PRIMARY KEY (`z_gyl_role_id`,`orgs_id`),
  KEY `FK_k2jgevd7fl3k4ylwl4wf9xg3v` (`orgs_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_role` */

DROP TABLE IF EXISTS `z_gyl_role`;

CREATE TABLE `z_gyl_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `createDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `del_flag` varchar(1) DEFAULT '0' COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT '' COMMENT '备注',
  `updateDate` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `dataScope` varchar(255) NOT NULL COMMENT '数据范畴',
  `org_id` bigint(20) DEFAULT NULL COMMENT '所属部门',
  `roleTag` varchar(50) DEFAULT 'OTHER' COMMENT '角色标签',
  PRIMARY KEY (`id`),
  KEY `FK_p805b8aydm7s2w8pj4kr8yks7` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_gyl_role_menu` */

DROP TABLE IF EXISTS `z_gyl_role_menu`;

CREATE TABLE `z_gyl_role_menu` (
  `roles_id` bigint(20) NOT NULL,
  `menus_id` bigint(20) NOT NULL,
  PRIMARY KEY (`roles_id`,`menus_id`),
  KEY `FK_8yk7o3nqvuomnuclf4usvwncq` (`menus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;

/*Table structure for table `z_region` */

DROP TABLE IF EXISTS `z_region`;

CREATE TABLE `z_region` (
  `id` varchar(255) NOT NULL COMMENT '编号',
  `pid` varchar(255) DEFAULT NULL COMMENT '父id',
  `level` varchar(255) DEFAULT NULL COMMENT '级别',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `region_code` varchar(255) DEFAULT NULL,
  `area_id` varchar(255) DEFAULT NULL COMMENT '区域编号',
  `tree_path` varchar(255) DEFAULT NULL COMMENT '父ids',
  `full_name` varchar(255) DEFAULT NULL COMMENT '全称',
  `short_name` varchar(255) DEFAULT NULL COMMENT '短称',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `del_flag` varchar(2) NOT NULL COMMENT '删除标识',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `FK_30j67517pognnjqm3fkebeyyx` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC;