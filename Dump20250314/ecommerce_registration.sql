-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `Name` varchar(100) DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `EmailID` varchar(150) DEFAULT NULL,
  `MobileNumber` varchar(50) DEFAULT NULL,
  `Gender` int DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Address` text,
  `Password` varchar(150) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `CreatedIP` varchar(20) DEFAULT NULL,
  `Username` varchar(20) DEFAULT NULL,
  `userType` int DEFAULT NULL,
  `updatedDate` datetime DEFAULT NULL,
  `updatedIP` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES ('Rama',1,'srk4202003@gmail.com','9585736183',1,'India','No.46, Jayanagar','JKK0xZe0b21RuVByf4L0xw==','2025-01-16 11:50:09','0:0:0:0:0:0:0:1','Rama@2043',1,'2025-01-20 11:21:45','0:0:0:0:0:0:0:1'),('Rama',2,'rama@example.com','9585736183',1,'India','45678tyuidfghjcvbn','oyKQdkFoksTXDBwmEY7MjA==','2025-01-17 12:29:47','0:0:0:0:0:0:0:1','Rama@204',2,'2025-02-20 22:13:34','0:0:0:0:0:0:0:1'),('Sriram',3,'rama@e.com','1234567890',1,'SouthAfrica','Guduvancheri','3mo3eXctUnmNBOLRuxJVQw==','2025-01-24 16:02:41','0:0:0:0:0:0:0:1','Sriram@1',2,NULL,NULL),('Sriram',4,'sriram@gmail.com','9566188746',1,'India','99,Vedal,Kanchipuram','NNdPL5TTvTz45l00A/0C7Q==','2025-02-05 10:32:34','0:0:0:0:0:0:0:1','Sriram@12',2,NULL,NULL),('Krishna',5,'krishna@gmail.com','9125348764',1,'Spain','Yelehanka, Bangalore','kFTR76SVFufokNoPee67PQ==','2025-02-20 22:09:41','0:0:0:0:0:0:0:1','Krishna@1',2,NULL,NULL);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-14 14:25:42
