-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: e_commerce
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `user_data`
--

DROP TABLE IF EXISTS `user_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_data` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_role` enum('ADMIN','CUSTOMER') DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UKdhxuydjj5l1vds6s9eex23dcr` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_data`
--

LOCK TABLES `user_data` WRITE;
/*!40000 ALTER TABLE `user_data` DISABLE KEYS */;
INSERT INTO `user_data` VALUES (1,'asdf@gmail.com','$2a$10$.ZoCtIJIuhoSiukqt2izZOfKp2nmFqnU0F62ej2MxTuc0sO.Jdiba','Davidjoshua','CUSTOMER'),(2,'joshua@gmail.com','$2a$10$DrhT3VB7BMX75q3TZI2ETee2lTtKpeQ6EqiWIorvJl1z61HDKIPuC','Davidjoshua','CUSTOMER'),(3,'abc@gmail.com','$2a$10$WIGaMlEj1eNK3Ni.g8kGmuR.udoCQrD0feaMIBhfgWCkF6dyZed8a','Davidjoshua','CUSTOMER'),(4,'goat@gmail.com','$2a$10$Tjm14sha.hL9TOAAAx5r9OzCDg3k1ojqVoRge/Ms.L2iK6pKFEyve','goat','CUSTOMER'),(5,'almighty@gmail.com','$2a$10$PHA13GE542jPVSgp2sXCmOa2z7c5Ph27xQUaT05Lv5QgTCNWUafW.','ALMIGTHY','ADMIN'),(6,'david@gmail.com','$2a$10$Xv3q/Xp4q0.4h3tqhG7jT.76J2X/4/6hD3bMMRYXS7GCsDThV6U8e','Raj Gowda','CUSTOMER'),(7,'uncle@gmail.com','$2a$10$MdcsYi0cW8ch2KtP7MEJL.sV5t/gyWNX9QXtwKGJSK/S3Djhsi4XK','Anukul','CUSTOMER'),(8,'murtaza@gmail.com','$2a$10$CLzIoPYsR8d5KkRCj489UOtJ.dklCWHxOBQ1SE4irusJDNJxqMfbm','Murtaza','CUSTOMER');
/*!40000 ALTER TABLE `user_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-14 13:09:49
