-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: localhost    Database: car_hire
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1
create schema `hire_car`;
use `hire_car`;

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL AUTO_INCREMENT,
  `confirmation_letter_sent_yn` varchar(255) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL,
  `payment_recieved_yn` varchar(255) DEFAULT NULL,
  `booking_status_code` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `reg_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`booking_id`),
  KEY `FK7llt42tpe4kdq61saxahhpgrl` (`booking_status_code`),
  KEY `FKbkgiov0f0769j3kb3u6u3i4o` (`customer_id`),
  KEY `FKra08n5tnt1w16uegg57gl52bq` (`reg_number`),
  CONSTRAINT `FK7llt42tpe4kdq61saxahhpgrl` FOREIGN KEY (`booking_status_code`) REFERENCES `booking_status` (`booking_status_code`),
  CONSTRAINT `FKbkgiov0f0769j3kb3u6u3i4o` FOREIGN KEY (`customer_id`) REFERENCES `custromer` (`customer_id`),
  CONSTRAINT `FKra08n5tnt1w16uegg57gl52bq` FOREIGN KEY (`reg_number`) REFERENCES `vehicle` (`reg_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_status`
--

DROP TABLE IF EXISTS `booking_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_status` (
  `booking_status_code` int(11) NOT NULL AUTO_INCREMENT,
  `booking_status_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`booking_status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_status`
--

LOCK TABLES `booking_status` WRITE;
/*!40000 ALTER TABLE `booking_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `custromer`
--

DROP TABLE IF EXISTS `custromer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `custromer` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(255) DEFAULT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `address_line_3` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `county` varchar(255) DEFAULT NULL,
  `customer_details` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `custromer`
--

LOCK TABLES `custromer` WRITE;
/*!40000 ALTER TABLE `custromer` DISABLE KEYS */;
/*!40000 ALTER TABLE `custromer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manufacturer` (
  `manufacturer_code` int(11) NOT NULL AUTO_INCREMENT,
  `manufacturer_name` varchar(255) DEFAULT NULL,
  `manufacurer_details` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manufacturer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES (9,'Japan','S?n xu?t t?i Nh?t B?n @.@'),(10,'Korea','S?n xu?t t?i Hàn Qu?c ^.^'),(11,'Japan','S?n xu?t t?i Nh?t B?n @.@'),(12,'Korea','S?n xu?t t?i Hàn Qu?c ^.^'),(13,'Korea','S?n xu?t t?i Hàn Qu?c ^.^'),(14,'French','S?n xu?t t?i Pháp'),(18,'Japan','S?n xu?t t?i Nh?t B?n @.@'),(20,'Japan','S?n xu?t t?i Nh?t B?n @.@'),(21,'Japan','S?n xu?t t?i Nh?t B?n @.@');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `model`
--

DROP TABLE IF EXISTS `model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `model` (
  `model_code` int(11) NOT NULL AUTO_INCREMENT,
  `daily_hire_rate` varchar(255) DEFAULT NULL,
  `model_name` varchar(255) DEFAULT NULL,
  `manufacturer_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`model_code`),
  KEY `FK75ch9fbb6njih47wir8b5f6mg` (`manufacturer_code`),
  CONSTRAINT `FK75ch9fbb6njih47wir8b5f6mg` FOREIGN KEY (`manufacturer_code`) REFERENCES `manufacturer` (`manufacturer_code`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (2,'30chiec/tuan','Toyota 4 ch?',9),(3,'30chiec/tuan','Toyota 4 ch?',11),(7,'30chiec/tuan','Toyota 4 ch?',18),(9,'30chiec/tuan','Toyota 4 ch?',20),(10,'30chiec/tuan','Toyota 4 ch?',21);
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `reg_number` int(11) NOT NULL AUTO_INCREMENT,
  `current_mileage` varchar(255) DEFAULT NULL,
  `daily_mot_due` date DEFAULT NULL,
  `engine_size` varchar(255) DEFAULT NULL,
  `model_code` int(11) DEFAULT NULL,
  `vehicle_categorry_code` int(11) DEFAULT NULL,
  PRIMARY KEY (`reg_number`),
  KEY `FKlc6cq9wbd42enh14m2wjys1y8` (`model_code`),
  KEY `FKs1tmna4f8bac7voa7n0gee3ih` (`vehicle_categorry_code`),
  CONSTRAINT `FKlc6cq9wbd42enh14m2wjys1y8` FOREIGN KEY (`model_code`) REFERENCES `model` (`model_code`),
  CONSTRAINT `FKs1tmna4f8bac7voa7n0gee3ih` FOREIGN KEY (`vehicle_categorry_code`) REFERENCES `vehicle_category` (`vehicle_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_category`
--

DROP TABLE IF EXISTS `vehicle_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle_category` (
  `vehicle_category_code` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_category_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`vehicle_category_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_category`
--

LOCK TABLES `vehicle_category` WRITE;
/*!40000 ALTER TABLE `vehicle_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `vehicle_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-07 17:11:27