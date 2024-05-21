-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: HMS0
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
INSERT INTO `admininfo` (`NID`,`NAME`,`NAME`) VALUES ('123','admin','admin'),('admin','admin','admin'),('root','admin','admin');
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
INSERT INTO `customerinfo` (NAME, NID, PASSWORD, EMAIL, PHONE, ADDRESS, CITIZENID, CUSTOMERTYPEID) VALUES
('mursalin','mursalin','mursalin','mursalin@gmail.com','0123456789','India','123456789','FOREIGN')
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
INSERT INTO `customertype` (`CUSTOMERTYPEID`,`CUSTOMERTYPENAME`,`CUSTOMERSURCHARGERATE`) VALUES
('DOMESTIC','Khách trong nước','1'),
('FOREIGN','Khách nước ngoài','1.5')
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
INSERT INTO `roominfo` (`ROOMNO`, `ROOMTYPE`, `PRICEDAY`, `STATUS`, `NOTE`) VALUES
('1.1', 'A', '150000', '', 'Near window'),
('1.2', 'A', '150000', '', 'Good view'),
('1.3', 'A', '150000', '', 'Near elevator'),
('1.4', 'A', '150000', '', 'Quiet room'),
-- (Add more room numbers for type A up to 22.20, alternating status and notes)
('22.19', 'A', '150000', '', 'Near exit'),
('22.20', 'A', '150000', '', 'Good view'),

-- Room type B
('1.5', 'B', '170000', '', 'Near window'),
('1.6', 'B', '170000', '', 'Good view'),
('1.7', 'B', '170000', '', 'Near elevator'),
('1.8', 'B', '170000', '', 'Quiet room'),
-- (Add more room numbers for type B up to 22.20, alternating status and notes)
('22.1', 'B', '170000', '', 'Near exit'),
('22.2', 'B', '170000', '', 'Good view'),

-- Room type C
('2.1', 'C', '200000', '', 'Near window'),
('2.2', 'C', '200000', '', 'Good view'),
('2.3', 'C', '200000', '', 'Near elevator'),
('2.4', 'C', '200000', '', 'Quiet room'),
-- (Add more room numbers for type C up to 22.20, alternating status and notes)
('21.19', 'C', '200000', '', 'Near exit'),
('21.20', 'C', '200000', '', 'Good view');
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
  PRIMARY KEY (`ROOMFORNO`,`NID`)
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
INSERT INTO `bill` (BILLNO, NID, ADDRESS, ROOMTOTALPRICE) VALUES
('2024-05-01', 'John Doe', '123 Main St', '5000000'),
('2024-05-02', 'Jane Smith', '456 Elm St', '7500000')
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
INSERT INTO `billdetail` (BILLNO, ROOMFORMNO, TOTALDAYS, PRICEDAY, TOTALPRICE) VALUES
('2024-05-01','1','3','50000' ,'150000')
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
INSERT INTO `parameters` (PARAMETERNAME, PARAMETERVALUE) VALUES
('AROOMCOUNT','0'),
('BROOMCOUNT','0'),
('CROOMCOUNT','0'),
('DOMESTICCUSTOMERCOUNT','0'),
('FOREIGNCUSTOMERCOUNT','0'),
('MAXCUSTOMER','3'),
('FOREIGNSURCHARGERATE','1.5'),
('3PEOPLESURCHARGERATE','0.25')
/*!40000 ALTER TABLE `parameters` ENABLE KEYS */;
UNLOCK TABLES;



/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-26 14:14:02
