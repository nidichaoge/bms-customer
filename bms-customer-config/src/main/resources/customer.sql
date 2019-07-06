## bms_customer
CREATE TABLE `bms_tag` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增标签ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '标签名称',
  `customer_num` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '该标签下的客户数量',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `is_auto` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '标签类型:是否自动标签',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '标签描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bid_dat_name` (`business_id`,`deleted_at`,`name`),
  KEY `idx_bid_dt_crt` (`business_id`,`deleted_at`,`created_at`),
  KEY `idx_bid_dt_num` (`business_id`,`deleted_at`,`customer_num`) COMMENT '用于按客户人数排序'
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商户下标签';

CREATE TABLE `bms_tag_to_customer` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `tag_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '标签ID',
  `customer_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户ID',
  `is_auto_tagged` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否自动打标',
  `tag_source` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '打标来源',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_tid_dat_cid` (`tag_id`, `business_id`, `deleted_at`, `customer_id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储标签与客户的关系';

CREATE TABLE `bms_customer_to_tag` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `customer_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户ID',
  `tag_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '标签ID',
  `is_auto_tagged` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否自动打标',
  `tag_source` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '打标来源',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_cid_bid_dat_tid` (`customer_id`, `business_id`, `deleted_at`, `tag_id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储客户与标签关系';

CREATE TABLE `bms_customer` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增客户ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `nick_name` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '客户昵称',
  `mobile` VARCHAR(12) NOT NULL DEFAULT '' COMMENT '客户手机号',
  `gender` TINYINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户性别:0-未知 1-男 2-女',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  `description` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '客户备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bid_dat_mobile` (`business_id`, `deleted_at`, `mobile`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商户下客户';

CREATE TABLE `bms_customer_info` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `customer_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户ID',
  `area` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '客户所在地',
  `wechat` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '微信号',
  `birthday` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '客户生日',
  `anniversary` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '客户纪念日',
  `created_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录创建时间',
  `updated_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录更新时间',
  `deleted_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '记录删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bid_dat_cid` (`business_id`, `deleted_at`, `customer_id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='存储商户下客户信息';

CREATE TABLE `bms_trade_info` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `customer_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户ID',
  `trade_count` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '总购次',
  `total_amount` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '总消费金额',
  `last_trade_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '最后一次消费时间',
  `version` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bid_cid` (`business_id`,`customer_id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='客户购买行为';

CREATE TABLE `bms_refund_info` (
  `id` BIGINT UNSIGNED AUTO_INCREMENT NOT NULL COMMENT '自增ID',
  `business_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '商户ID',
  `customer_id` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '客户ID',
  `refund_count` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '总退次',
  `total_refund_amount` BIGINT UNSIGNED NOT NULL DEFAULT '0' COMMENT '总退款金额',
  `last_refund_at` DATETIME NOT NULL DEFAULT '1970-01-01 08:00:00' COMMENT '最后一次退款时间',
  `version` INT UNSIGNED NOT NULL DEFAULT '0' COMMENT '版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bid_cid` (`business_id`,`customer_id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT='客户退款行为';