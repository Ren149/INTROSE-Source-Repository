CREATE DATABASE  IF NOT EXISTS `pharmacy` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `pharmacy`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: pharmacy
-- ------------------------------------------------------
-- Server version	5.7.10-log

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
-- Table structure for table `batch`
--

DROP TABLE IF EXISTS `batch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `batch` (
  `batchID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `productID` int(10) NOT NULL,
  `total_batch_quantity` int(11) NOT NULL,
  `batch_quantity_left` int(11) NOT NULL,
  `expiry_month` int(11) NOT NULL,
  `expiry_year` year(4) NOT NULL,
  `entry_date` date NOT NULL,
  `buying_price` float NOT NULL,
  `lot_number` varchar(45) NOT NULL,
  PRIMARY KEY (`batchID`),
  UNIQUE KEY `batchID_UNIQUE` (`batchID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
INSERT INTO `batch` VALUES (0000000001,1,30,4,1,2018,'2016-08-15',10,'AAAAA'),(0000000002,2,0,0,2,2018,'2016-08-15',1,'AAAASDF'),(0000000003,3,40,8,7,2018,'2016-08-15',2,'ZZZZ'),(0000000004,3,0,0,3,2018,'2016-08-15',2,'BBBB'),(0000000005,1,0,0,1,2018,'2016-08-17',10,'ASDZZ'),(0000000006,4,30,0,1,2018,'2016-08-18',10,'ZZZZ'),(0000000007,4,40,0,2,2018,'2016-08-18',10,'zzx'),(0000000008,5,20,0,12,2018,'2016-08-18',100,'123654'),(0000000009,5,20,0,12,2021,'2016-08-18',100,'123654'),(0000000010,5,20,15,6,2019,'2016-08-18',100,'123654');
/*!40000 ALTER TABLE `batch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `line_item`
--

DROP TABLE IF EXISTS `line_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `line_item` (
  `lineitemID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `salesID` int(11) NOT NULL,
  `productID` int(11) NOT NULL,
  `quantity_sold` int(11) NOT NULL,
  `unit_price` float NOT NULL,
  `total_price_sold` float NOT NULL,
  PRIMARY KEY (`lineitemID`),
  UNIQUE KEY `lineitemID_UNIQUE` (`lineitemID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_item`
--

LOCK TABLES `line_item` WRITE;
/*!40000 ALTER TABLE `line_item` DISABLE KEYS */;
INSERT INTO `line_item` VALUES (0000000001,1,2,11,0,0),(0000000002,2,3,2,0,0),(0000000003,3,3,2,0,0),(0000000004,4,3,5,0,0),(0000000005,4,1,4,0,0),(0000000006,5,3,2,0,0),(0000000007,6,3,2,0,0),(0000000008,7,3,1,0,0),(0000000009,8,1,1,0,0),(0000000010,9,3,1,0,0),(0000000011,10,3,5,0,0),(0000000012,11,3,1,0,0),(0000000013,12,1,11,0,0),(0000000014,13,3,1,0,0),(0000000015,16,5,10,0,0),(0000000016,17,5,1,0,0),(0000000017,18,5,15,0,0),(0000000018,19,5,1,0,0),(0000000019,20,5,1,0,0),(0000000020,21,5,1,0,0),(0000000021,22,5,1,0,0),(0000000022,23,5,1,0,0),(0000000023,24,5,1,0,0),(0000000024,25,5,17,0,0);
/*!40000 ALTER TABLE `line_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `productID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `selling_price` float NOT NULL,
  `isDiscontinued` binary(1) NOT NULL,
  PRIMARY KEY (`productID`),
  UNIQUE KEY `productID_UNIQUE` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (0000000001,'TUSERAN FORTE',20,'0'),(0000000002,'ROBITUSSIN LIQUIGEL',2,'1'),(0000000003,'ALAXAN FR',3,'0'),(0000000004,'CEELIN PLUS',20,'0'),(0000000005,'TEST',200,'0');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reorder_point`
--

DROP TABLE IF EXISTS `reorder_point`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reorder_point` (
  `reorderpointID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `item_type` varchar(45) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`reorderpointID`),
  UNIQUE KEY `reorderpointID_UNIQUE` (`reorderpointID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reorder_point`
--

LOCK TABLES `reorder_point` WRITE;
/*!40000 ALTER TABLE `reorder_point` DISABLE KEYS */;
/*!40000 ALTER TABLE `reorder_point` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sales` (
  `salesID` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `total_price_sold` int(11) NOT NULL,
  `date_sold` date NOT NULL,
  PRIMARY KEY (`salesID`),
  UNIQUE KEY `salesID_UNIQUE` (`salesID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` VALUES (0000000001,22,'2016-08-16'),(0000000002,6,'2016-08-16'),(0000000003,6,'2016-08-16'),(0000000004,95,'2016-08-16'),(0000000005,6,'2016-08-16'),(0000000006,6,'2016-08-16'),(0000000007,3,'2016-08-18'),(0000000008,20,'2016-08-18'),(0000000009,3,'2016-08-18'),(0000000010,15,'2016-08-18'),(0000000011,3,'2016-08-18'),(0000000012,220,'2016-08-18'),(0000000013,3,'2016-08-18'),(0000000014,0,'2016-08-18'),(0000000015,0,'2016-08-18'),(0000000016,2000,'2016-08-18'),(0000000017,200,'2016-08-18'),(0000000018,3000,'2016-08-18'),(0000000019,200,'2016-08-18'),(0000000020,200,'2016-08-18'),(0000000021,200,'2016-08-18'),(0000000022,200,'2016-08-18'),(0000000023,200,'2016-08-18'),(0000000024,200,'2016-08-18'),(0000000025,3400,'2016-08-18'),(0000000026,2000,'2016-08-18'),(0000000027,2230,'2016-08-18');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-18 20:04:09
