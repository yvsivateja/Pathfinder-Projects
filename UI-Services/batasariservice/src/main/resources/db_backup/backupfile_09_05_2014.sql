-- MySQL dump 10.13  Distrib 5.1.73, for Win32 (ia32)
--
-- Host: localhost    Database: batasari
-- ------------------------------------------------------
-- Server version	5.1.73-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `companyid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `companyname` varchar(45) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `expirydate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `address1` text CHARACTER SET latin1,
  `address2` text CHARACTER SET latin1,
  `address3` text CHARACTER SET latin1,
  `city` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `country` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `state` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `notes` text CHARACTER SET latin1,
  `shortkey` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Batasari',1,'0000-00-00 00:00:00','synergy park','gachibowli','serlingampally','hyderabad','india','Telangana','Blah\r\nBlah','bat');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `employeeid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employeename` varchar(45) CHARACTER SET latin1 NOT NULL DEFAULT '',
  `age` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `sex` char(2) CHARACTER SET latin1 DEFAULT NULL,
  `phone` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `emailid` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  `notes` text CHARACTER SET latin1,
  `companyid` int(10) unsigned NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`employeeid`),
  UNIQUE KEY `employeename_unique` (`employeename`,`companyid`) USING BTREE,
  KEY `FK_employee_company` (`companyid`),
  CONSTRAINT `FK_employee_company` FOREIGN KEY (`companyid`) REFERENCES `company` (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Batasari Admin','25','M','9127329083','sivateja9@gmail.com','',1,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `roleid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rolename` varchar(45) NOT NULL DEFAULT '',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `isdefault` tinyint(1) unsigned DEFAULT NULL,
  `companyid` int(10) unsigned NOT NULL DEFAULT '0',
  `isdisplayed` tinyint(1) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`roleid`),
  UNIQUE KEY `rolename_unique` (`rolename`,`companyid`) USING BTREE,
  KEY `FK_role_company` (`companyid`),
  CONSTRAINT `FK_role_company` FOREIGN KEY (`companyid`) REFERENCES `company` (`companyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'SuperAdmin',1,0,1,1);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rolesacl`
--

DROP TABLE IF EXISTS `rolesacl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rolesacl` (
  `rolesaclid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(10) unsigned NOT NULL DEFAULT '0',
  `cancreate` tinyint(1) NOT NULL DEFAULT '0',
  `canupdate` tinyint(1) NOT NULL DEFAULT '0',
  `candelete` tinyint(1) NOT NULL DEFAULT '0',
  `canview` tinyint(1) NOT NULL DEFAULT '0',
  `module` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`rolesaclid`),
  KEY `FK_rolesacl_role` (`roleid`),
  CONSTRAINT `FK_rolesacl_role` FOREIGN KEY (`roleid`) REFERENCES `roles` (`roleid`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rolesacl`
--

LOCK TABLES `rolesacl` WRITE;
/*!40000 ALTER TABLE `rolesacl` DISABLE KEYS */;
INSERT INTO `rolesacl` VALUES (1,1,1,1,1,1,'COMPANY'),(3,1,1,1,1,1,'EMPLOYEE'),(13,1,1,1,1,1,'USERS'),(14,1,1,1,1,1,'ROLES');
/*!40000 ALTER TABLE `rolesacl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `userid` int(10) unsigned NOT NULL DEFAULT '0',
  `roleid` int(10) unsigned NOT NULL DEFAULT '0',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`roleid`,`userid`,`status`),
  KEY `FK_userrole_user` (`userid`),
  CONSTRAINT `FK_userrole_role` FOREIGN KEY (`roleid`) REFERENCES `roles` (`roleid`),
  CONSTRAINT `FK_userrole_user` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,1,1);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `userrolespermissionsview`
--

DROP TABLE IF EXISTS `userrolespermissionsview`;
/*!50001 DROP VIEW IF EXISTS `userrolespermissionsview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `userrolespermissionsview` (
 `username` tinyint NOT NULL,
  `userid` tinyint NOT NULL,
  `employeeid` tinyint NOT NULL,
  `companyid` tinyint NOT NULL,
  `rolename` tinyint NOT NULL,
  `roleid` tinyint NOT NULL,
  `module` tinyint NOT NULL,
  `cancreate` tinyint NOT NULL,
  `canupdate` tinyint NOT NULL,
  `candelete` tinyint NOT NULL,
  `canview` tinyint NOT NULL,
  `issuperadmin` tinyint NOT NULL,
  `companyname` tinyint NOT NULL,
  `shortkey` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Temporary table structure for view `userrolesview`
--

DROP TABLE IF EXISTS `userrolesview`;
/*!50001 DROP VIEW IF EXISTS `userrolesview`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `userrolesview` (
 `username` tinyint NOT NULL,
  `rolename` tinyint NOT NULL,
  `status` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '',
  `password` varchar(500) NOT NULL DEFAULT '',
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `companyid` int(10) unsigned NOT NULL DEFAULT '0',
  `employeeid` int(10) unsigned NOT NULL DEFAULT '0',
  `issuperadmin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userid`),
  UNIQUE KEY `companyusername_unique` (`username`,`companyid`) USING BTREE,
  KEY `FK_user_company` (`companyid`),
  KEY `FK_user_employee` (`employeeid`),
  CONSTRAINT `FK_user_company` FOREIGN KEY (`companyid`) REFERENCES `company` (`companyid`),
  CONSTRAINT `FK_user_employee` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$hs3qURdZVvWdljZaEvRXquw9fU/Ye7IyxHKIAO9RvYo7Gc6NJyrhq',1,1,1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `userrolespermissionsview`
--

/*!50001 DROP TABLE IF EXISTS `userrolespermissionsview`*/;
/*!50001 DROP VIEW IF EXISTS `userrolespermissionsview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `userrolespermissionsview` AS select `u`.`username` AS `username`,`u`.`userid` AS `userid`,`u`.`employeeid` AS `employeeid`,`u`.`companyid` AS `companyid`,`r`.`rolename` AS `rolename`,`r`.`roleid` AS `roleid`,`racl`.`module` AS `module`,`racl`.`cancreate` AS `cancreate`,`racl`.`canupdate` AS `canupdate`,`racl`.`candelete` AS `candelete`,`racl`.`canview` AS `canview`,`u`.`issuperadmin` AS `issuperadmin`,`c`.`companyname` AS `companyname`,`c`.`shortkey` AS `shortkey` from ((((`users` `u` join `userrole` `ur` on((`ur`.`userid` = `u`.`userid`))) join `roles` `r` on((`r`.`roleid` = `ur`.`roleid`))) join `rolesacl` `racl` on((`racl`.`roleid` = `r`.`roleid`))) join `company` `c` on((`c`.`companyid` = `u`.`companyid`))) order by `r`.`rolename`,`racl`.`module` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `userrolesview`
--

/*!50001 DROP TABLE IF EXISTS `userrolesview`*/;
/*!50001 DROP VIEW IF EXISTS `userrolesview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `userrolesview` AS select `u`.`username` AS `username`,`r`.`rolename` AS `rolename`,`u`.`status` AS `status` from ((`users` `u` join `userrole` `ur` on((`ur`.`userid` = `u`.`userid`))) join `roles` `r` on((`ur`.`roleid` = `r`.`roleid`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-09-05 20:20:16
