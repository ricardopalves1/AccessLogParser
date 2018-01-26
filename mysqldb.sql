-- Adminer 4.3.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `mysqldb`;
CREATE DATABASE `mysqldb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `mysqldb`;

DROP TABLE IF EXISTS `ips_blocked`;
CREATE TABLE `ips_blocked` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cause` varchar(255) NOT NULL,
  `end_date` datetime(3) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  `requests` int(11) NOT NULL,
  `start_date` datetime(3) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKtbhph3urqrlpq7lqsj3nd7bln` (`ip_address`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `ip_blocked_events`;
CREATE TABLE `ip_blocked_events` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_date` datetime(3) NOT NULL,
  `ip_address` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`),
  UNIQUE KEY `UK_fv6pnd7ybuy3r5elid0d3es4y` (`event_date`),
  KEY `FK2uers30boslldvbs937stgsnv` (`id`),
  CONSTRAINT `FK2uers30boslldvbs937stgsnv` FOREIGN KEY (`id`) REFERENCES `ips_blocked` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=444 DEFAULT CHARSET=latin1;


-- 2018-01-26 03:08:35
