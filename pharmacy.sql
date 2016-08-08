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
  PRIMARY KEY (`batchID`),
  UNIQUE KEY `batchID_UNIQUE` (`batchID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batch`
--

LOCK TABLES `batch` WRITE;
/*!40000 ALTER TABLE `batch` DISABLE KEYS */;
INSERT INTO `batch` VALUES (0000000001,1,30,23,1,2018,'2016-08-08',10),(0000000002,2,0,0,1,2018,'2016-08-08',1),(0000000003,3,30,30,1,2018,'2016-08-08',10),(0000000004,3,20,20,1,2018,'2016-08-08',10),(0000000005,3,10,10,1,2018,'2016-08-08',10),(0000000006,2,0,0,3,2020,'2016-08-08',1),(0000000007,2,5,3,2,2019,'2016-08-08',1),(0000000008,2,20,18,2,2019,'2016-08-08',1),(0000000009,2,5,3,1,2018,'2016-08-08',1),(0000000010,2,5,3,1,2018,'2016-08-08',1),(0000000011,2,5,3,1,2018,'2016-08-08',1),(0000000012,1,5,4,2,2019,'2016-08-08',10),(0000000013,2,10,8,1,2018,'2016-08-08',2),(0000000014,3,12,12,1,2018,'2016-08-08',11),(0000000015,1,10,10,3,2020,'2016-08-08',12.6),(0000000016,1,1,1,1,2018,'2016-08-08',12.6),(0000000017,3,8,8,1,2018,'2016-08-08',13.789);
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
  PRIMARY KEY (`lineitemID`),
  UNIQUE KEY `lineitemID_UNIQUE` (`lineitemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `line_item`
--

LOCK TABLES `line_item` WRITE;
/*!40000 ALTER TABLE `line_item` DISABLE KEYS */;
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
  `selling_price` int(11) NOT NULL,
  `isDiscontinued` binary(1) NOT NULL,
  PRIMARY KEY (`productID`),
  UNIQUE KEY `productID_UNIQUE` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (0000000001,'CEELIN PLUS',20,'0'),(0000000002,'B-COMPLEX (PHAREX)',2,'0'),(0000000003,'APPEBON',20,'0');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
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

-- Dump completed on 2016-08-08 15:05:07
