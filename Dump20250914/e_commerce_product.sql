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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock_quantity` int(11) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'This is good mobile, please buy!!!!!!!','Moto Edge 60 fusion',12000,50,1,'Motorola','/uploads/7a539a6f-8b0b-4f0d-9be8-1a2ed8c0438b.jpg'),(2,'Best phone in all the phones.','Iphone 17',1000000,10,1,'Apple','/uploads/90d968ed-8e42-4d85-a52e-0612b8985b91.png'),(4,'A 5-quart air fryer for healthy and crispy meals.','Air Fryer',120,60,7,'KitchenPro','/uploads/83e6fc57-78f4-4e81-b010-4827ca24dd76.jpg'),(5,'Automatic espresso machine for perfect coffee at home.','Espresso Machine',350,25,7,'CafeWorld','/uploads/b8daf5ee-6653-4e59-857b-f0cc5764a31a.jpg'),(6,'Comfortable and stylish slim-fit designer jeans.','Designer Jeans',95,80,6,'DenimCo','/uploads/b0b87629-98f9-45b3-8bff-3aab21ec35df.jpg'),(7,'A classic black leather jacket for all seasons.','Leather Jacket',180,35,6,'StyleLeathers','/uploads/4084cbb8-17f4-47d7-b964-b3e752b6d19d.jpg'),(8,'A 27-inch 4K UHD monitor with vibrant colors.','4K Monitor',450,40,5,'ScreenMasters','/uploads/bdccb3e7-dd4a-4428-9970-5d73a738da02.jpg'),(9,'High-fidelity wireless earbuds with long battery life.','Wireless Earbuds',150,120,5,'AudioBrand','/uploads/589e5b56-4ac0-483b-be7e-c32dfe0e76af.jpg'),(10,'A modern smart watch with health tracking features.','Smart Watch',250,50,5,'TechBrand','/uploads/e38be30b-da30-4d5d-80a0-ed73dd811c84.jpg'),(11,'All items available in this cookware set.','Cookware Set',600,14,7,'Pegion','/uploads/0afd5842-44b2-4beb-8361-fbe827564bec.jpg'),(12,'This is a good dress for summer','Summer Dress',750,300,6,'Ajio','/uploads/acfd7f84-093d-4bf9-9871-3630d9043a8e.jpeg'),(13,'Its a good bluetooth speak used for listening songs','Bluetooth Speaker',599,59,5,'Boat','/uploads/bf639009-b1ae-4cdd-8052-e9e584c010e5.jpg'),(14,'This is used to clean your house without human effort, charges on its know.','Vaccum Cleaner',999,27,5,'Vaccummy','/uploads/880be2bc-ef96-46eb-ae07-c07db3ecf5fb.jpeg'),(15,'This will save you from sun','Sunglasses',99,591,6,'Rayban','/uploads/a92745a0-c001-4f06-89fb-d5674cce4286.jpg'),(16,'These are the most trendy shoes in the market.','Trendy Shoes',699,60,6,'Addidas','/uploads/6bb7288c-369f-4b30-8f14-364ece85cdcc.jpg'),(17,'This will grind any item you like.','Mixer Grinder',499,23,7,'Pegion','/uploads/8a32b913-d57d-4a2f-a9ce-78172be6154c.jpg'),(18,'This will boil our food in a fast and easy manner','Cooker',699,44,7,'Prestige','/uploads/3ec5eaff-d31b-4ff0-a3ef-c65d41552061.jpg');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
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
