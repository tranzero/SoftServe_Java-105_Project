CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `lines`
--

DROP TABLE IF EXISTS `lines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lines` (
  `LINEID` int(10) unsigned NOT NULL,
  `LINENAME` varchar(100) NOT NULL,
  PRIMARY KEY (`LINEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lines`
--

LOCK TABLES `lines` WRITE;
/*!40000 ALTER TABLE `lines` DISABLE KEYS */;
INSERT INTO `lines` VALUES (1,'L\'viv - Stryy'),(2,'Stryy - L\'viv');
/*!40000 ALTER TABLE `lines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `posts` (
  `POSTID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(100) DEFAULT NULL,
  `DESCRIPTION` varchar(1000) DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  PRIMARY KEY (`POSTID`),
  UNIQUE KEY `POSTID_UNIQUE` (`POSTID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'TestNews','TestTestTestTestTestTestTestTest','2003-01-20'),(2,'РЎР°РјРѕРѕР±РѕСЂРѕРЅР° С‚Р° В«РђРІС‚РѕРЅРѕРјРЅРёР№ РѕРїС–СЂВ» РїРѕРєРёРЅСѓС‚СЊ РїСЂРёРјС–С‰РµРЅРЅСЏ ','РџСЂРµРґСЃС‚Р°РІРЅРёРєРё РЅР°СЂРѕРґРЅРѕС— СЃР°РјРѕРѕР±РѕСЂРѕРЅРё РїРѕС‡РЅСѓС‚СЊ РІРёРІС–Р»СЊРЅСЏС‚Рё РїСЂРёРјС–С‰РµРЅРЅСЏ Р›СЊРІС–РІСЃСЊРєРѕС— РѕР±Р»РґРµСЂР¶Р°РґРјС–РЅС–СЃС‚СЂР°С†С–С—. В«РђРІС‚РѕРЅРѕРјРЅРёР№ РѕРїС–СЂВ» СѓР¶Рµ РїРѕРєРёРЅСѓРІ Р’РёРЅРЅРёС‡РµРЅРєР°, 18. В«РџСЂР°РІРёР№ СЃРµРєС‚РѕСЂВ», С‰Рѕ Р·Р°Р№РЅСЏРІ РїРµСЂС€РёР№ РїРѕРІРµСЂС…, РїРѕРєРё РЅРµ РІРёСЂС–С€РёРІ, С‡Рё РїС–РґРµ Р· Р›РћР”Рђ.','2005-08-09'),(3,'РЈ РљРёС”РІС– Р·Р°СЃС‚СЂРµР»РёР»Рё С‰Рµ РѕРґРЅРѕРіРѕ РјС–Р»С–С†С–РѕРЅРµСЂР°','РЈ РљРёС”РІС– 42-СЂС–С‡РЅРёР№ СЃС‚Р°СЂС€РёР№ РїСЂР°РїРѕСЂС‰РёРє РјС–Р»С–С†С–С— РїРѕРјРµСЂ Сѓ Р»С–РєР°СЂРЅС– РІС–Рґ РІРѕРіРЅРµРїР°Р»СЊРЅРѕРіРѕ РїРѕСЂР°РЅРµРЅРЅСЏ РІ РіСЂСѓРґРё.','2555-01-08'),(4,'Р’С–Рґ РєСЂРёРјС–РЅР°Р»СЊРЅРѕС— РІС–РґРїРѕРІС–РґР°Р»СЊРЅРѕСЃС‚С– Р·РІС–Р»СЊРЅРёР»Рё 8 Р°РєС‚РёРІС–СЃ','Р’С–СЃСЊРјРѕС… СѓС‡Р°СЃРЅРёРєС–РІ РјР°СЃРѕРІРёС… Р·Р°РІРѕСЂСѓС€РµРЅСЊ, С‰Рѕ СЃС‚Р°Р»РёСЃСЏ Сѓ РіСЂСѓРґРЅС– РјРёРЅСѓР»РѕРіРѕ СЂРѕРєСѓ, Р·РІС–Р»СЊРЅРёР»Рё РІС–Рґ РєСЂРёРјС–РЅР°Р»СЊРЅРѕС— РІС–РґРїРѕРІС–РґР°Р»СЊРЅРѕСЃС‚С–. РџСЂРѕ С†Рµ РїРѕРІС–РґРѕРјРёР»Рё Сѓ РїСЂРµСЃ-СЃР»СѓР¶Р±С– РїСЂРѕРєСѓСЂР°С‚СѓСЂРё РљРёС”РІР°.','2555-01-06');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `routes` (
  `ROUTEID` int(10) unsigned NOT NULL,
  `LINEID` int(10) unsigned NOT NULL,
  `ROUTECODE` varchar(20) NOT NULL,
  `STARTTIME` time NOT NULL,
  PRIMARY KEY (`ROUTEID`),
  KEY `LINEID` (`LINEID`),
  CONSTRAINT `ROUTES_ibfk_1` FOREIGN KEY (`LINEID`) REFERENCES `lines` (`LINEID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (1,1,'1000000000001','17:38:00'),(2,2,'1000000000002','05:20:00');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stations` (
  `STATIONID` int(11) unsigned NOT NULL,
  `STATIONCODE` varchar(20) NOT NULL,
  `STATIONNAME` varchar(100) NOT NULL,
  PRIMARY KEY (`STATIONID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES (1,'0000000000001','Stryy'),(2,'0000000000002','Ugers\'ko'),(3,'0000000000003','Pyatnychany'),(4,'0000000000004','Bil\'che-Vol.'),(5,'0000000000005','Pisochne'),(6,'0000000000006','Myckolayiv-Dnist.'),(7,'0000000000007','Zadorognia'),(8,'0000000000008','Cherkasy-Lv.'),(9,'0000000000009','Dmytriya'),(10,'0000000000010','Shchirets\'-1'),(11,'0000000000011','Shchirets\'-2'),(12,'0000000000012','Semenivka'),(13,'0000000000013','Z.P. Pustomyty'),(14,'0000000000014','Glynna-Navaria'),(15,'0000000000015','Oboroshin'),(16,'0000000000016','Sknyliv'),(17,'0000000000017','L\'viv');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stationsonlines`
--

DROP TABLE IF EXISTS `stationsonlines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stationsonlines` (
  `STATIONONLINEID` int(10) unsigned NOT NULL,
  `LINEID` int(10) unsigned NOT NULL,
  `STATIONID` int(10) unsigned NOT NULL,
  `STATIONORDERNUM` int(10) unsigned NOT NULL,
  PRIMARY KEY (`STATIONONLINEID`),
  KEY `LINEID` (`LINEID`),
  KEY `STATIONID` (`STATIONID`),
  CONSTRAINT `STATIONSONLINES_ibfk_1` FOREIGN KEY (`LINEID`) REFERENCES `lines` (`LINEID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `STATIONSONLINES_ibfk_2` FOREIGN KEY (`STATIONID`) REFERENCES `stations` (`STATIONID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stationsonlines`
--

LOCK TABLES `stationsonlines` WRITE;
/*!40000 ALTER TABLE `stationsonlines` DISABLE KEYS */;
INSERT INTO `stationsonlines` VALUES (1,1,17,1),(2,1,16,2),(3,1,15,3),(4,1,14,4),(5,1,13,5),(6,1,12,6),(7,1,11,7),(8,1,10,8),(9,1,9,9),(10,1,8,10),(11,1,7,11),(12,1,6,12),(13,1,5,13),(14,1,4,14),(15,1,3,15),(16,1,2,16),(17,1,1,17),(18,2,1,1),(19,2,2,2),(20,2,3,3),(21,2,4,4),(22,2,5,5),(23,2,6,6),(24,2,7,7),(25,2,8,8),(26,2,9,9),(27,2,10,10),(28,2,11,11),(29,2,12,12),(30,2,13,13),(31,2,14,14),(32,2,15,15),(33,2,16,16),(34,2,17,17);
/*!40000 ALTER TABLE `stationsonlines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stops`
--

DROP TABLE IF EXISTS `stops`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stops` (
  `STOPID` int(10) unsigned NOT NULL,
  `ROUTEID` int(10) unsigned NOT NULL,
  `STATIONONLINEID` int(10) unsigned NOT NULL,
  `ARRIVAL` time NOT NULL,
  `DEPARTURE` time NOT NULL,
  PRIMARY KEY (`STOPID`),
  KEY `ROUTEID` (`ROUTEID`,`STATIONONLINEID`),
  KEY `STATIONONLINEID` (`STATIONONLINEID`),
  CONSTRAINT `STOPS_ibfk_1` FOREIGN KEY (`ROUTEID`) REFERENCES `routes` (`ROUTEID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `STOPS_ibfk_2` FOREIGN KEY (`STATIONONLINEID`) REFERENCES `stationsonlines` (`STATIONONLINEID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stops`
--

LOCK TABLES `stops` WRITE;
/*!40000 ALTER TABLE `stops` DISABLE KEYS */;
INSERT INTO `stops` VALUES (1,1,1,'00:00:00','00:00:00'),(2,1,2,'00:09:00','00:10:00'),(3,1,3,'00:12:00','00:13:00'),(4,1,4,'00:24:00','00:25:00'),(5,1,7,'00:37:00','00:38:00'),(6,1,12,'01:01:00','01:02:00'),(7,1,13,'01:09:00','01:10:00'),(8,1,14,'01:17:00','01:18:00'),(9,1,17,'02:33:00','00:00:00'),(10,2,18,'00:00:00','00:00:00'),(11,2,21,'00:17:00','00:18:00'),(12,2,22,'00:25:00','00:26:00'),(13,2,23,'00:33:00','00:34:00'),(14,2,28,'01:00:00','01:02:00'),(15,2,31,'01:15:00','01:16:00'),(16,2,32,'01:23:00','01:24:00'),(17,2,33,'01:30:00','01:31:00'),(18,2,34,'01:41:00','00:00:00');
/*!40000 ALTER TABLE `stops` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transports`
--

DROP TABLE IF EXISTS `transports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transports` (
  `TRANSPORTID` int(10) AUTO_INCREMENT NOT NULL,
  `ROUTEID` int(10) unsigned NOT NULL,
  `TRANSPORTCODE` varchar(20) NOT NULL,
  `STARTTIME` time NOT NULL,
  `SEATCLASS1` int(10) NOT NULL,
  `SEATCLASS2` int(10) NOT NULL,
  `SEATCLASS3` int(10) NOT NULL,
  `GENPRICE` decimal(7,2) NOT NULL,
  PRIMARY KEY (`TRANSPORTID`),
  KEY `ROUTEID` (`ROUTEID`),
  CONSTRAINT `ROUTES_ROUTEID_fk` 
  FOREIGN KEY (`ROUTEID`) 
  REFERENCES `routes` (`ROUTEID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transports`
--

LOCK TABLES `transports` WRITE;
/*!40000 ALTER TABLE `transports` DISABLE KEYS */;
/*!40000 ALTER TABLE `transports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USERID` int(10) unsigned NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `FIRSTNAME` varchar(100) NOT NULL,
  `LASTNAME` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `PASSWD` varchar(100) NOT NULL,
  `REGDATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ROLE` enum('REGUSER','MANAGER','ADMIN') NOT NULL,
  PRIMARY KEY (`USERID`),
  UNIQUE KEY `USERNAME` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'tanzero','Oleg','Lesniak','test@mail.first','','2014-01-14 01:41:35','REGUSER'),(2,'yherasym','Yaroslav','Herasym','test@mail.second','','2014-01-14 01:42:46','MANAGER'),(3,'mychaylo.partyka','Mychaylo','Partyka','test@mail.third','','2014-01-14 01:43:46','ADMIN');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-02-04 13:17:23
