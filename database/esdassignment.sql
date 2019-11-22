-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1:3306
-- 產生時間： 2019 年 11 月 22 日 17:42
-- 伺服器版本： 5.7.26
-- PHP 版本： 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `esdassignment`
--

-- --------------------------------------------------------

--
-- 資料表結構 `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `aid` varchar(10) COLLATE utf8_bin NOT NULL,
  `cid` varchar(10) COLLATE utf8_bin NOT NULL,
  `role` varchar(20) COLLATE utf8_bin NOT NULL,
  `firstname` varchar(255) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(255) COLLATE utf8_bin NOT NULL,
  `password` varchar(255) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`aid`),
  KEY `class_ac_cid_fk` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 資料表結構 `attendance`
--

DROP TABLE IF EXISTS `attendance`;
CREATE TABLE IF NOT EXISTS `attendance` (
  `date` date NOT NULL,
  `cid` varchar(10) COLLATE utf8_bin NOT NULL,
  `aid` varchar(10) COLLATE utf8_bin NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`date`,`cid`,`aid`),
  KEY `cid_sd_at_fk` (`cid`),
  KEY `aid_ac_at_fk` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 資料表結構 `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `cid` varchar(10) COLLATE utf8_bin NOT NULL,
  `className` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 資料表結構 `schoolday`
--

DROP TABLE IF EXISTS `schoolday`;
CREATE TABLE IF NOT EXISTS `schoolday` (
  `date` date NOT NULL,
  `cid` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`date`,`cid`),
  KEY `class_sd_cid_fk` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 已傾印資料表的限制(constraint)
--

--
-- 資料表的限制(constraint) `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `class_ac_cid_fk` FOREIGN KEY (`cid`) REFERENCES `class` (`cid`);

--
-- 資料表的限制(constraint) `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `aid_ac_at_fk` FOREIGN KEY (`aid`) REFERENCES `account` (`aid`),
  ADD CONSTRAINT `cid_sd_at_fk` FOREIGN KEY (`cid`) REFERENCES `schoolday` (`cid`),
  ADD CONSTRAINT `date_sd_at_fk` FOREIGN KEY (`date`) REFERENCES `schoolday` (`date`);

--
-- 資料表的限制(constraint) `schoolday`
--
ALTER TABLE `schoolday`
  ADD CONSTRAINT `class_sd_cid_fk` FOREIGN KEY (`cid`) REFERENCES `class` (`cid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
