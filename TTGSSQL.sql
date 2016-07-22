-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2016 at 10:54 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ttdata`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE IF NOT EXISTS `admins` (
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `username` varchar(10) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `school` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `school` (`school`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='all admins of this system';

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`fname`, `lname`, `username`, `pass`, `school`) VALUES
('zadock', 'malick', 'malick', 'hura', 2),
('mick', 'mick', 'mick', 'mick', 5),
('vincent', 'peter', 'vincent', 'nyadendi', 1),
('vincent', 'peter', 'vincente', 'nyadendi', 4);

-- --------------------------------------------------------

--
-- Table structure for table `amendments`
--

CREATE TABLE IF NOT EXISTS `amendments` (
  `tteventid` int(11) NOT NULL,
  `newtimestart` date NOT NULL,
  `newtimeend` date NOT NULL,
  `newday` enum('MON','TUE','WED','THU','FRI') NOT NULL,
  PRIMARY KEY (`tteventid`),
  UNIQUE KEY `tteventid` (`tteventid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='table of all amendments';

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
  `name` varchar(100) DEFAULT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `deptID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deptID` (`deptID`),
  KEY `deptID_2` (`deptID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE IF NOT EXISTS `departments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lecturers`
--

CREATE TABLE IF NOT EXISTS `lecturers` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lecturerusers`
--

CREATE TABLE IF NOT EXISTS `lecturerusers` (
  `username` varchar(20) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `unitsid` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `unitsid` (`unitsid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `migration`
--

CREATE TABLE IF NOT EXISTS `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `migration`
--

INSERT INTO `migration` (`version`, `apply_time`) VALUES
('m000000_000000_base', 1457386646),
('m130524_201442_init', 1457386653),
('m140209_132017_init', 1457902903),
('m140403_174025_create_account_table', 1457902905),
('m140504_113157_update_tables', 1457902911),
('m140504_130429_create_token_table', 1457902913),
('m140830_171933_fix_ip_field', 1457902914),
('m140830_172703_change_account_table_name', 1457902914),
('m141222_110026_update_ip_field', 1457902915),
('m141222_135246_alter_username_length', 1457902916),
('m150614_103145_update_social_account_table', 1457902919),
('m150623_212711_fix_username_notnull', 1457902920);

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE IF NOT EXISTS `profile` (
  `user_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `public_email` varchar(255) DEFAULT NULL,
  `gravatar_email` varchar(255) DEFAULT NULL,
  `gravatar_id` varchar(32) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `bio` text,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `roominfo`
--

CREATE TABLE IF NOT EXISTS `roominfo` (
  `id` int(11) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `size` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `roominfo`
--

INSERT INTO `roominfo` (`id`, `type`, `size`) VALUES
(1, 'Normal', 'Normal'),
(2, NULL, 'Big'),
(3, 'Lab', NULL),
(4, 'Hall', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sid` int(11) DEFAULT NULL,
  `size` int(1) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sid` (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `schools`
--

CREATE TABLE IF NOT EXISTS `schools` (
  `sid` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sid` (`sid`),
  KEY `sid_2` (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

-- --------------------------------------------------------

--
-- Table structure for table `semesterunits`
--

CREATE TABLE IF NOT EXISTS `semesterunits` (
  `lecid` int(4) DEFAULT NULL,
  `stageid` int(11) NOT NULL,
  `unitcode` varchar(10) DEFAULT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `unitcode` (`unitcode`),
  KEY `unitcode_2` (`unitcode`),
  KEY `unitcode_3` (`unitcode`),
  KEY `stageid` (`stageid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `social_account`
--

CREATE TABLE IF NOT EXISTS `social_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `provider` varchar(255) NOT NULL,
  `client_id` varchar(255) NOT NULL,
  `data` text,
  `code` varchar(32) DEFAULT NULL,
  `created_at` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `account_unique` (`provider`,`client_id`),
  UNIQUE KEY `account_unique_code` (`code`),
  KEY `fk_user_account` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `stages`
--

CREATE TABLE IF NOT EXISTS `stages` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `stages`
--

INSERT INTO `stages` (`id`, `name`) VALUES
(1, 'Y1S1'),
(2, 'Y1S2'),
(3, 'Y1S3'),
(4, 'Y2S1'),
(5, 'Y2S2'),
(6, 'Y2S3'),
(7, 'Y3S1'),
(8, 'Y3S2'),
(9, 'Y3S3'),
(10, 'Y4S1'),
(11, 'Y4S2'),
(12, 'Y4S3');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `number` int(4) DEFAULT NULL,
  `stageid` int(11) NOT NULL,
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `courseID` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `courseID` (`courseID`),
  KEY `stageid` (`stageid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `studentusers`
--

CREATE TABLE IF NOT EXISTS `studentusers` (
  `username` varchar(20) NOT NULL,
  `pass` varchar(15) NOT NULL,
  `school` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='table of student users';

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE IF NOT EXISTS `token` (
  `user_id` int(11) NOT NULL,
  `code` varchar(32) NOT NULL,
  `created_at` int(11) NOT NULL,
  `type` smallint(6) NOT NULL,
  UNIQUE KEY `token_unique` (`user_id`,`code`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `ttevents`
--

CREATE TABLE IF NOT EXISTS `ttevents` (
  `tteventid` int(11) NOT NULL AUTO_INCREMENT,
  `lecturerid` int(11) NOT NULL,
  `studentid` int(11) NOT NULL,
  `roomid` int(11) NOT NULL,
  `semunitid` int(11) NOT NULL,
  `timestart` date NOT NULL,
  `timeend` date NOT NULL,
  `day` enum('MON','TUE','WED','THU','FRI') NOT NULL,
  `description` varchar(10) NOT NULL,
  PRIMARY KEY (`tteventid`),
  UNIQUE KEY `lecturerid` (`lecturerid`,`roomid`,`semunitid`,`timestart`,`timeend`,`day`),
  KEY `lecturerid_2` (`lecturerid`),
  KEY `roomid` (`roomid`),
  KEY `semunitid` (`semunitid`),
  KEY `studentid` (`studentid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `units`
--

CREATE TABLE IF NOT EXISTS `units` (
  `name` varchar(100) DEFAULT NULL,
  `code` varchar(10) NOT NULL DEFAULT '',
  `courseID` int(11) DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `code` (`code`),
  KEY `courseID` (`courseID`),
  KEY `code_2` (`code`),
  KEY `courseID_2` (`courseID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `unitsoflecturers`
--

CREATE TABLE IF NOT EXISTS `unitsoflecturers` (
  `ref` int(11) NOT NULL,
  `code` varchar(10) NOT NULL DEFAULT '',
  PRIMARY KEY (`code`),
  KEY `ref` (`ref`),
  KEY `ref_2` (`ref`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `auth_key` varchar(32) NOT NULL,
  `confirmed_at` int(11) DEFAULT NULL,
  `unconfirmed_email` varchar(255) DEFAULT NULL,
  `blocked_at` int(11) DEFAULT NULL,
  `registration_ip` varchar(45) DEFAULT NULL,
  `created_at` int(11) NOT NULL,
  `updated_at` int(11) NOT NULL,
  `flags` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_unique_email` (`email`),
  UNIQUE KEY `user_unique_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `amendments`
--
ALTER TABLE `amendments`
  ADD CONSTRAINT `amendmentstottevents` FOREIGN KEY (`tteventid`) REFERENCES `ttevents` (`tteventid`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `coursestodepartments` FOREIGN KEY (`deptID`) REFERENCES `departments` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `departments`
--
ALTER TABLE `departments`
  ADD CONSTRAINT `deptoschools` FOREIGN KEY (`sid`) REFERENCES `schools` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `fk_user_profile` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `roomtoschool` FOREIGN KEY (`sid`) REFERENCES `schools` (`sid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `semesterunits`
--
ALTER TABLE `semesterunits`
  ADD CONSTRAINT `semesterunits_ibfk_1` FOREIGN KEY (`stageid`) REFERENCES `stages` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `semesterunitstounits` FOREIGN KEY (`unitcode`) REFERENCES `units` (`code`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `social_account`
--
ALTER TABLE `social_account`
  ADD CONSTRAINT `fk_user_account` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `studentstocurses` FOREIGN KEY (`courseID`) REFERENCES `courses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `studentstage` FOREIGN KEY (`stageid`) REFERENCES `stages` (`id`);

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `fk_user_token` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `ttevents`
--
ALTER TABLE `ttevents`
  ADD CONSTRAINT `tteventstorooms` FOREIGN KEY (`roomid`) REFERENCES `rooms` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tteventstolecturers` FOREIGN KEY (`lecturerid`) REFERENCES `lecturers` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tteventstosemunits` FOREIGN KEY (`semunitid`) REFERENCES `semesterunits` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tteventtostudents` FOREIGN KEY (`studentid`) REFERENCES `students` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `units`
--
ALTER TABLE `units`
  ADD CONSTRAINT `unitstocourses` FOREIGN KEY (`courseID`) REFERENCES `courses` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `unitsoflecturers`
--
ALTER TABLE `unitsoflecturers`
  ADD CONSTRAINT `unitsoflecturerstolecturers` FOREIGN KEY (`ref`) REFERENCES `lecturers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
