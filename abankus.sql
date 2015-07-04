-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 04, 2015 at 11:43 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `abankus`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `addressId` bigint(20) NOT NULL AUTO_INCREMENT,
  `address1` varchar(100) NOT NULL,
  `address2` varchar(100) NOT NULL,
  `city` varchar(100) NOT NULL,
  `region` varchar(100) NOT NULL,
  `zipcode` varchar(100) NOT NULL,
  `addressType` varchar(100) NOT NULL,
  `customerId` bigint(20) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FK_jo2vndqkr3qi82fxymui4b7of` (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`addressId`, `address1`, `address2`, `city`, `region`, `zipcode`, `addressType`, `customerId`) VALUES
(1, '28 Dexter Ave', '', 'Kumasi', 'ashanti', '23351', 'primary', 3),
(2, '25 Dexter Ave', '', 'Kumaso', 'ashanti', '23351', 'primary', 4);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `customerId` bigint(20) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) DEFAULT NULL,
  `customerNumber` varchar(100) NOT NULL,
  `firstname` varchar(100) NOT NULL,
  `middlename` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) NOT NULL,
  `customerType` varchar(100) NOT NULL,
  `contactPerson` varchar(255) DEFAULT NULL,
  `gender` varchar(10) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customerId`, `company_name`, `customerNumber`, `firstname`, `middlename`, `lastname`, `customerType`, `contactPerson`, `gender`) VALUES
(3, NULL, 'HOtXsoQZ', 'hubert', NULL, 'boateng', 'individual', NULL, 'male'),
(4, 'Abankus Corp', 'a6m7s5Yj', 'Kofi', NULL, 'Coos', 'company', NULL, 'male');

-- --------------------------------------------------------

--
-- Table structure for table `email`
--

CREATE TABLE IF NOT EXISTS `email` (
  `emailId` bigint(20) NOT NULL AUTO_INCREMENT,
  `emailAddress` varchar(100) NOT NULL,
  `emailType` varchar(100) NOT NULL,
  `customerId` bigint(20) NOT NULL,
  PRIMARY KEY (`emailId`),
  KEY `FK_bhgnfo39w4h0b3wtx6xd5st68` (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `email`
--

INSERT INTO `email` (`emailId`, `emailAddress`, `emailType`, `customerId`) VALUES
(3, 'hkboateng@gmail.com', 'primary', 3),
(4, 'hkboateng@gmail.com', 'primary', 4);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address1` varchar(255) NOT NULL,
  `address2` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `dateOfBirth` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `homephone` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) NOT NULL,
  `middlename` varchar(255) DEFAULT NULL,
  `state` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `address1`, `address2`, `cellphone`, `city`, `dateOfBirth`, `email`, `employeeId`, `firstname`, `gender`, `homephone`, `lastname`, `middlename`, `state`, `zipcode`) VALUES
(1, '250 Smith Rd', NULL, '347-967-7596', 'Kumasi', '04/01/1970', 'hkboateng@gmail.com', 455652142, 'Admin', 'male', NULL, 'Officer', NULL, 'Ashant1', '23351'),
(3, 'p.o.boc ks 9796', '', '347-967-7223', 'Kumasi', '02', 'hkboateng@outlokk.com', 8964017916, 'hubert', 'male', NULL, 'admin', NULL, 'Ashanti', '23351'),
(15, 'pbox 9796', '', '34796755252', 'kumasi', '02', 'hkboateng@kyco.com', 3277348512, 'kofi', 'male', NULL, 'abankus', NULL, 'ahanti', '23351');

-- --------------------------------------------------------

--
-- Table structure for table `phone`
--

CREATE TABLE IF NOT EXISTS `phone` (
  `phoneId` bigint(20) NOT NULL AUTO_INCREMENT,
  `countrycode` varchar(5) NOT NULL,
  `phoneType` varchar(100) NOT NULL,
  `customerId` bigint(20) NOT NULL,
  `phoneNumber` varchar(100) NOT NULL,
  PRIMARY KEY (`phoneId`),
  KEY `FK_t4t850kdp9lgw1mjp2wmdgjio` (`customerId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `phone`
--

INSERT INTO `phone` (`phoneId`, `countrycode`, `phoneType`, `customerId`, `phoneNumber`) VALUES
(1, '+1', 'home_phone', 3, '565-454-6546'),
(2, '+1', 'home_phone', 4, '564-456-4464');

-- --------------------------------------------------------

--
-- Table structure for table `roletbl`
--

CREATE TABLE IF NOT EXISTS `roletbl` (
  `roleId` int(12) NOT NULL AUTO_INCREMENT,
  `role` varchar(20) NOT NULL,
  `notes` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `role` (`role`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `roletbl`
--

INSERT INTO `roletbl` (`roleId`, `role`, `notes`, `role_name`) VALUES
(1, 'ROLE_USERS', 'For Users', 'All Users'),
(2, 'ROLE_ADMIN', 'For Admins', 'Administrator'),
(3, 'ROLE_MANAGERS', 'For Managers', 'Managers'),
(7, 'ROLE_EMPLOYEE', 'For Employee''s of the Company', 'Employee of Company'),
(8, 'ROLE_SUPERVISORS', 'For Supervisors', 'Supervisors');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `username`, `password`, `enabled`) VALUES
(1, 'admin', '$2a$04$t4vPD2kcY4Y0ZRmk9yOjXu7hfU7kQOL1skERTfr.rMsc7M4oDRxaG', 1);

-- --------------------------------------------------------

--
-- Table structure for table `userroletbl`
--

CREATE TABLE IF NOT EXISTS `userroletbl` (
  `userroleId` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  PRIMARY KEY (`userroleId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `userroletbl`
--

INSERT INTO `userroletbl` (`userroleId`, `role`, `username`) VALUES
(1, 'ROLE_USERS', 'admin'),
(4, 'ROLE_MANAGERS', 'admin'),
(5, 'ROLE_EMPLOYEE', 'admin'),
(6, 'ROLE_SUPERVISORS', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `ROLE` varchar(45) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_role` (`ROLE`,`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user_roles`
--

INSERT INTO `user_roles` (`user_role_id`, `username`, `ROLE`) VALUES
(1, 'admin', 'ROLE_ADMIN'),
(2, 'admin', 'USER_EMPLOYEE');

-- --------------------------------------------------------

--
-- Table structure for table `usrtbl`
--

CREATE TABLE IF NOT EXISTS `usrtbl` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `emailAddress` varchar(255) NOT NULL,
  `employeeId` bigint(20) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `employeeId` (`employeeId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `usrtbl`
--

INSERT INTO `usrtbl` (`userId`, `username`, `password`, `enabled`, `emailAddress`, `employeeId`) VALUES
(1, 'admin', '$2a$04$t4vPD2kcY4Y0ZRmk9yOjXu7hfU7kQOL1skERTfr.rMsc7M4oDRxaG', 1, 'hkboateng@gmail.com', 455652142),
(3, 'admin1', '$2a$10$xaMcjaEZOIOWkXqc3bz.X.tQJ6ODeROC4XGLC0CDboYEXQkxBl77u', 1, 'hkboateng@outlokk.com', 32),
(15, 'admin2', '$2a$10$i2zMi6YJVlIxSFIfpQfV/evbcSkfECf3YjPEQL0JjIyqPcorHdquG', 1, 'hkboateng@kyco.com', 322);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_jo2vndqkr3qi82fxymui4b7of` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`);

--
-- Constraints for table `email`
--
ALTER TABLE `email`
  ADD CONSTRAINT `FK_bhgnfo39w4h0b3wtx6xd5st68` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`);

--
-- Constraints for table `phone`
--
ALTER TABLE `phone`
  ADD CONSTRAINT `FK_t4t850kdp9lgw1mjp2wmdgjio` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
