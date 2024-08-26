CREATE DATABASE  IF NOT EXISTS `1cproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `1cproject`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: 1cproject
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `courseID` int NOT NULL AUTO_INCREMENT,
  `courseName` varchar(45) NOT NULL,
  `duration` int NOT NULL,
  `capacity` int DEFAULT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (201,'Digital Marketing',6,400),(202,'UI/UX',8,250),(203,'Information Technology',12,200),(204,'Python',8,500);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cprovider`
--

DROP TABLE IF EXISTS `cprovider`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cprovider` (
  `cProviderID` int NOT NULL AUTO_INCREMENT,
  `cProviderName` varchar(50) NOT NULL,
  `cProviderCon` bigint unsigned NOT NULL,
  `cProviderPass` varchar(125) NOT NULL,
  PRIMARY KEY (`cProviderID`),
  UNIQUE KEY `cProviderCon_UNIQUE` (`cProviderCon`)
) ENGINE=InnoDB AUTO_INCREMENT=202408 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cprovider`
--

LOCK TABLES `cprovider` WRITE;
/*!40000 ALTER TABLE `cprovider` DISABLE KEYS */;
INSERT INTO `cprovider` VALUES (202401,'Shivraj Singh',7722974467,'$2a$10$uOvaz.qzQ3MoTdkId4ywNuLBfTYBOTVeWKcygMVHPKlMv2eNl.ou2'),(202405,'Rohit',6269586250,'$2a$10$j5YnNJPrR2agJaAeM1AfNuQ3/hcFwDuio.FXa0q8PQlmBtJWbT2UK'),(202406,'Deepak Singh',8839560056,'$2a$10$NHCWydx6E8kIlUqHx1FEu.RyEfGrmu1LqIZ0ohb8yzhW9Zx984V3O'),(202407,'Hero Honda',9165369578,'$2a$10$CD9UtverCDbBxaM/5XaUNefJX6LxXMrLHBCQj2c4C3YvoEB011.q.');
/*!40000 ALTER TABLE `cprovider` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `studentID` int NOT NULL,
  `courseID` int NOT NULL,
  KEY `courseID` (`courseID`),
  KEY `studentID` (`studentID`),
  CONSTRAINT `enrollment_ibfk_1` FOREIGN KEY (`courseID`) REFERENCES `courses` (`courseID`),
  CONSTRAINT `enrollment_ibfk_2` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentID` int NOT NULL AUTO_INCREMENT,
  `studentName` varchar(45) NOT NULL,
  `stRoll` int NOT NULL,
  `stCon` bigint DEFAULT NULL,
  `cProviderId` int DEFAULT NULL,
  PRIMARY KEY (`studentID`),
  UNIQUE KEY `stRoll_UNIQUE` (`stRoll`),
  UNIQUE KEY `stCon_UNIQUE` (`stCon`),
  KEY `cProviderId` (`cProviderId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`cProviderId`) REFERENCES `cprovider` (`cProviderID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (101,'Shivraj Singh',2401,7722974467,202401);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-24 10:56:35
