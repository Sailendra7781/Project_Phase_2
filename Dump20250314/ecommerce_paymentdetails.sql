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
-- Table structure for table `paymentdetails`
--

DROP TABLE IF EXISTS `paymentdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymentdetails` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `UserId` int DEFAULT NULL,
  `CustomerName` varchar(45) DEFAULT NULL,
  `CustomerEmail` varchar(45) DEFAULT NULL,
  `CustomerPhoneNumber` varchar(45) DEFAULT NULL,
  `ProductDetailsId` varchar(45) DEFAULT NULL,
  `surl` varchar(150) DEFAULT NULL,
  `furl` varchar(150) DEFAULT NULL,
  `RequestedDateTime` datetime DEFAULT NULL,
  `RequestedData` text,
  `ResponseDateTime` datetime DEFAULT NULL,
  `Status` int DEFAULT NULL,
  `ErrorMessage` varchar(45) DEFAULT NULL,
  `BankCode` varchar(45) DEFAULT NULL,
  `PayuTransactionId` varchar(45) DEFAULT NULL,
  `Mode` varchar(45) DEFAULT NULL,
  `MtxId` varchar(45) DEFAULT NULL,
  `ResponseData` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentdetails`
--

LOCK TABLES `paymentdetails` WRITE;
/*!40000 ALTER TABLE `paymentdetails` DISABLE KEYS */;
INSERT INTO `paymentdetails` VALUES (1,2,'Rama','rama@example.com','9585736183','1,2','http://localhost:8080/springboot/views/payment/success.jsp','http://localhost:8080/springboot/views/payment/failure.jsp','2025-01-30 13:18:26','09n09G|859849674668|218580|EcommerceProductDetails|Rama|rama@example.com|||||||||||qLUspXsf','2025-01-30 13:19:11',1,NULL,'CC','403993715533267228','CC','859849674668','Mode: CC|status: success|unMappedStatus: captured|productinfo: EcommerceProductDetails|hash: 86aa265508f4cf3898e2df4574775c13be16824c1770f0808a20b8bb19259f90f5687abc5b91dc1ffedd4a41b9389ca9f20caac89a38332839b3a46e6c9f9fbe|key: 09n09G|txnid: 859849674668|amount: 218580.00|customerName: Rama|customerPhone: 9585736183|customerEmail: rama@example.com|udf1: |udf2: |udf3: |udf4: |udf5: |udf6: |udf7: |udf8: |udf9: |udf10: |PG_TYPE: CC-PG|bank_ref_num: 543481880601221400|bankcode: CC|error: E000|name_on_card: null|cardnum: XXXXXXXXXXXX2346|cardhash: null|error_Message: No Error'),(2,2,'Rama','rama@example.com','9585736183','1,2','http://localhost:8080/payment/success.jsp','http://localhost:8080/payment/failure.jsp','2025-03-07 14:35:34','09n09G|771226256388|394560|EcommerceProductDetails|Rama|rama@example.com|||||||||||qLUspXsf',NULL,0,NULL,NULL,NULL,NULL,'771226256388',NULL),(3,2,'Rama','rama@example.com','9585736183','1,2','http://localhost:8080/springboot/views/payment/success.jsp','http://localhost:8080/springboot/views/payment/failure.jsp','2025-03-10 13:46:07','09n09G|619968236347|394560|EcommerceProductDetails|Rama|rama@example.com|||||||||||qLUspXsf',NULL,0,NULL,NULL,NULL,NULL,'619968236347',NULL),(4,2,'Rama','rama@example.com','9585736183','1,2','http://localhost:8080/payment/success.jsp','http://localhost:8080/payment/failure.jsp','2025-03-10 15:32:07','09n09G|797867831115|394560|EcommerceProductDetails|Rama|rama@example.com|||||||||||qLUspXsf',NULL,0,NULL,NULL,NULL,NULL,'797867831115',NULL),(5,2,'Rama','rama@example.com','9585736183','1,2','http://localhost:8080/payment_success','http://localhost:8080/payment_failure','2025-03-11 17:28:12','','2025-03-11 17:28:12',1,NULL,'CNB01','','Card','137793719739','');
/*!40000 ALTER TABLE `paymentdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-14 14:25:43
