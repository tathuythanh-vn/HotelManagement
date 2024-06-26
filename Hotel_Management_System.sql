-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: HMS1
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admininfo`
--
DROP TABLE IF EXISTS `admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admininfo` (
  `NID` varchar(25) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `admininfo`
--
LOCK TABLES `admininfo` WRITE;
/*!40000 ALTER TABLE `admininfo` DISABLE KEYS */;
INSERT INTO `admininfo` (`NID`,`NAME`,`PASSWORD`) VALUES ('123','admin','admin'),('admin','admin','admin'),('root','admin','admin');
/*!40000 ALTER TABLE `admininfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employeeinfo`
--
DROP TABLE IF EXISTS `employeeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employeeinfo` (
  `NAME` varchar(30) DEFAULT NULL,
  `NID` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `employeeinfo`
--
LOCK TABLES `employeeinfo` WRITE;
/*!40000 ALTER TABLE `employeeinfo` DISABLE KEYS */;
INSERT INTO `employeeinfo` VALUES ('1','1','1','1','1','1'),('123','111','1111','111','111','111'),('2','2','2','2','2','2'),('3','3','3','3','3','3'),('Md. Mursalin','mursalin','mursalin','mur@gmail.com','dhaka, bangla','01222222'),('rakib','rakib','rakib','hasan@gmail.com','dhaka','012323');
/*!40000 ALTER TABLE `employeeinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerinfo`
--
DROP TABLE IF EXISTS `customerinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerinfo` (
  `NAME` varchar(30) DEFAULT NULL,
  `NID` varchar(30) NOT NULL,
  `PASSWORD` varchar(30) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `PHONE` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `CITIZENID` varchar(30) DEFAULT NULL,
  `CUSTOMERTYPEID` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `customerinfo`
--
LOCK TABLES `customerinfo` WRITE;
/*!40000 ALTER TABLE `customerinfo` DISABLE KEYS */;
INSERT INTO `customerinfo` VALUES ('mursalin','mursalin','mursalin','mursalin@gmail.com','0123456789','India','123456789','FOREIGN');
/*!40000 ALTER TABLE `customerinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customertype`
--
DROP TABLE IF EXISTS `customertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customertype` (
  `CUSTOMERTYPEID` varchar(30),
  `CUSTOMERTYPENAME` varchar(30),
  `CUSTOMERSURCHARGERATE` varchar(30),
  PRIMARY KEY (`CUSTOMERTYPEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `customertype`
--
LOCK TABLES `customertype` WRITE;
/*!40000 ALTER TABLE `customertype` DISABLE KEYS */;
INSERT INTO `customertype` VALUES
('DOMESTIC','Khách trong nước','1'),
('FOREIGN','Khách nước ngoài','1.5');
/*!40000 ALTER TABLE `customertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roominfo`
--
DROP TABLE IF EXISTS `roominfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roominfo` (
  `ROOMNO` varchar(30) NOT NULL,
  `ROOMTYPE` varchar(10) DEFAULT NULL,
  `PRICEDAY` varchar(30) DEFAULT NULL,
  `STATUS` varchar(12) DEFAULT NULL,
  `NOTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROOMNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `roominfo`
--
LOCK TABLES `roominfo` WRITE;
/*!40000 ALTER TABLE `roominfo` DISABLE KEYS */;
INSERT INTO `roominfo` VALUES('1.2', 'A', '150000', 1, 'Not Full'),
                             ('1.3', 'B', '170000', 2, 'Not Full'),
                             ('1.4', 'C', '200000', 3, 'Full'),
                             ('2.1', 'A', '150000', 0, 'Not Full'),
                             ('2.2', 'B', '170000', 1, 'Not Full'),
                             ('2.3', 'C', '200000', 2, 'Not Full'),
                             ('3.1', 'A', '150000', 3, 'Full'),
                             ('3.2', 'B', '170000', 0, 'Not Full'),
                             ('3.3', 'C', '200000', 1, 'Not Full'),
                             ('4.1', 'A', '150000', 2, 'Not Full'),
                             ('4.2', 'B', '170000', 3, 'Full'),
                             ('4.3', 'C', '200000', 2, 'Not Full'),
                             ('5.1', 'A', '150000', 3, 'Not Full'),
                             ('5.2', 'B', '170000', 0, 'Not Full'),
                             ('5.3', 'C', '200000', 0, 'Not Full'),
                             ('5.11', 'A', '150000', 0, 'Not Full');
/*!40000 ALTER TABLE `roominfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roomrentalform`
--
DROP TABLE IF EXISTS `roomrentalform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomrentalform` (
  `ROOMFORMNO` int NOT NULL AUTO_INCREMENT,
  `ROOMNO` varchar(30) DEFAULT NULL,
  `STARTDATE` date DEFAULT NULL,
  PRIMARY KEY (`ROOMFORMNO`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `roomrentalformdetail`
--
DROP TABLE IF EXISTS `roomrentalformdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roomrentalformdetail` (
  `ROOMFORMNO` int NOT NULL,
  `NID` varchar(30) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `CUSTOMERTYPEID` varchar(30) DEFAULT NULL,
  `CITIZENID` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`ROOMFORMNO`,`NID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bill`
--
DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `BILLNO` int NOT NULL AUTO_INCREMENT,
  `NID` varchar(30) DEFAULT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `ROOMTOTALPRICE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`BILLNO`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `bill`
--
LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
-- Insert data into bill table
INSERT INTO `bill`VALUES
('2024-05-01', 'John Doe', '123 Main St', '5000000');
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `billdetail`
--
DROP TABLE IF EXISTS `billdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billdetail` (
  `BILLNO` int NOT NULL,
  `ROOMFORMNO` int NOT NULL,
  `TOTALDAYS` varchar(30) DEFAULT NULL,
  `PRICEDAY` varchar(30) DEFAULT NULL,
  `TOTALPRICE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`BILLNO`,`ROOMFORMNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `billdetail`
--
LOCK TABLES `billdetail` WRITE;
/*!40000 ALTER TABLE `billdetail` DISABLE KEYS */;
-- Insert data into billdetail table
INSERT INTO `billdetail`  VALUES
('2024-05-01','1','3','50000' ,'150000');
/*!40000 ALTER TABLE `billdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parameters`
--
DROP TABLE IF EXISTS `parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parameters` (
  `PARAMETERNAME` varchar(30) NOT NULL,
  `PARAMETERVALUE` varchar(30) NOT NULL,
  PRIMARY KEY (`PARAMETERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
--
-- Dumping data for table `parameters`
--
LOCK TABLES `parameters` WRITE;
/*!40000 ALTER TABLE `parameters` DISABLE KEYS */;
-- Insert data into parameters table
INSERT INTO `parameters` VALUES
('AROOMCOUNT','0'),
('BROOMCOUNT','0'),
('CROOMCOUNT','0'),

('A','150.000'),
('B','170.000'),
('C','200.000'),

('DOMESTICCUSTOMERCOUNT','0'),
('FOREIGNCUSTOMERCOUNT','0'),

('MAXCUSTOMER','3'),

('FOREIGNSURCHARGERATE','1.5'),
('3PEOPLESURCHARGERATE','0.25');
/*!40000 ALTER TABLE `parameters` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `checkinoutinfo`
--

DROP TABLE IF EXISTS `checkinoutinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkinoutinfo` (
                                  `SI_NO` int NOT NULL AUTO_INCREMENT,
                                  `NAME` varchar(30) DEFAULT NULL,
                                  `EMAIL` varchar(30) DEFAULT NULL,
                                  `PHONE` varchar(30) DEFAULT NULL,
                                  `ADDRESS` varchar(30) DEFAULT NULL,
                                  `NID` varchar(15) DEFAULT NULL,
                                  `ROOMNO` varchar(15) DEFAULT NULL,
                                  `ROOMTYPE` varchar(15) DEFAULT NULL,
                                  `ROOMSTATUS` varchar(15) DEFAULT NULL,
                                  `CHECKEDIN` varchar(20) DEFAULT NULL,
                                  `CHECKEDOUT` varchar(20) DEFAULT NULL,
                                  `PRICEDAY` varchar(30) DEFAULT NULL,
                                  `TOTALDAYS` varchar(30) DEFAULT NULL,
                                  `TOTALPRICE` varchar(30) DEFAULT NULL,
                                  `PEOPLENO` varchar (30) DEFAULT NULL,
                                  PRIMARY KEY (`SI_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkinoutinfo`
--

LOCK TABLES `checkinoutinfo` WRITE;
/*!40000 ALTER TABLE `checkinoutinfo` DISABLE KEYS */;
INSERT INTO `checkinoutinfo` VALUES (16,'3','3','3','3','3','12','12','12','2021-12-06','2021-12-06','12','1','12'),(17,'2','2','2','2','2','9','9','9','2021-12-06','2021-12-06','9','1','9'),(18,'4','4','4','4','4','11','Non-Ac','Double','2021-12-06','2021-12-16','500','11','5500'),(19,'8','8','8','8','8','11','Non-Ac','Double','2021-12-06','2021-12-07','500','2','1000'),(20,'3','3','3','3','3','11','Non-Ac','Double','2021-12-06','2021-12-06','500','1','500'),(21,'2','3','2','2','2','13','Ac','12','2020-12-01','2020-12-31','12','31','372'),(22,'2','3','2','2','2','13','Ac','12','2020-09-01','2020-11-30','12','91','1092'),(23,'2','3','2','2','2','13','Ac','12','2013-07-01','2021-11-30','12','155','1860'),(24,'23','3','2','2','2','13','Ac','12','2021-12-06','2021-12-19','12','22','4884'),(25,'Md. Mursalin','mursa@gamil.com','015555','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-12-01','2021-12-10','1500','10','15000'),(26,'Md. Mursalin','mursa@gamil.com','015555','Dhaka, Bangladesh','mursalin','11','Non-Ac','Double','2021-12-02','2021-12-19','500','22','4884'),(27,'mursalin','mursalin@gmail.com','mursalin','mursalin','mursalin','111','AC','Double','2021-11-30','2021-12-18','1000','19','19000'),(28,'mursalin','mursalin@gmail.com','mursalin','mursalin','mursalin','2','AC-Room','Double','2021-11-28','2021-12-08','2000','11','22000'),(29,'1','1','1','1','1','1','AC','Single','2021-11-29','2021-12-17','1500','19','28500'),(30,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-12-17','2021-12-19','1500','22','4884'),(31,'1','1','1','1','1','111','AC','Double','2021-11-28','2021-12-19','1000','22','4884'),(32,'4','4','4','4','4','12','12','12','2021-12-18','2021-12-19','12','22','4884'),(33,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','123','1222','222','2021-11-30','2021-12-25','222','26','5772'),(34,'1','1','1111','1','1','123','1222','222','2021-11-28','2021-12-19','222','22','4884'),(35,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','1','AC','Single','2021-11-29',NULL,'1500',NULL,NULL),(36,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','11','Non-Ac','Double','2021-11-29',NULL,'500',NULL,NULL),(37,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','12','12','12','2021-11-29',NULL,'12',NULL,NULL),(38,'mursalin','mursalin@gmail.com','01222222','Dhaka, Bangladesh','mursalin','111','AC','Double','2021-12-19',NULL,'1000',NULL,NULL),(39,'1','1','1111','1','1','123','1222','222','2021-12-19',NULL,'222',NULL,NULL);
/*!40000 ALTER TABLE `checkinoutinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerinfo`
--

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-26 14:14:02
