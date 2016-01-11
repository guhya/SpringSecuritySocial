-- phpMyAdmin SQL Dump
-- version 3.4.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 07, 2016 at 04:10 PM
-- Server version: 5.0.95
-- PHP Version: 5.5.30

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `companyprofile`
--

-- --------------------------------------------------------

--
-- Table structure for table `persistent_logins`
--

CREATE TABLE IF NOT EXISTS `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  PRIMARY KEY  (`series`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `tbUser`
--

CREATE TABLE IF NOT EXISTS `tbUser` (
  `seq` int(11) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `password` varchar(512) NOT NULL,
  `firstName` varchar(100) default NULL,
  `lastName` varchar(100) default NULL,
  `enabled` varchar(1) default NULL,
  `regId` varchar(100) default NULL,
  `regIp` varchar(96) default NULL,
  `regDate` datetime default NULL,
  `modId` varchar(100) default NULL,
  `modIp` varchar(96) default NULL,
  `modDate` datetime default NULL,
  `delYn` varchar(1) NOT NULL,
  PRIMARY KEY  (`seq`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

-- --------------------------------------------------------

--
-- Table structure for table `tbUserRole`
--

CREATE TABLE IF NOT EXISTS `tbUserRole` (
  `seq` int(11) NOT NULL auto_increment,
  `username` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `regId` varchar(100) default NULL,
  `regIp` varchar(96) default NULL,
  `regDate` datetime default NULL,
  `modId` varchar(100) default NULL,
  `modIp` varchar(96) default NULL,
  `modDate` datetime default NULL,
  `delYn` varchar(1) NOT NULL,
  PRIMARY KEY  (`seq`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=47 ;

-- --------------------------------------------------------

--
-- Table structure for table `UserConnection`
--

CREATE TABLE IF NOT EXISTS `UserConnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL default '',
  `rank` int(11) NOT NULL,
  `displayName` varchar(255) default NULL,
  `profileUrl` varchar(512) default NULL,
  `imageUrl` varchar(512) default NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(512) default NULL,
  `refreshToken` varchar(512) default NULL,
  `expireTime` bigint(20) default NULL,
  PRIMARY KEY  (`userId`(50),`providerId`(50),`providerUserId`(100)),
  UNIQUE KEY `UserConnectionRank` (`userId`(50),`providerId`(50),`rank`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
