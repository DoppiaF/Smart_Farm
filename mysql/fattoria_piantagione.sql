CREATE DATABASE  IF NOT EXISTS `fattoria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fattoria`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: fattoria
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `piantagione`
--

DROP TABLE IF EXISTS `piantagione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `piantagione` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_pianta` varchar(20) DEFAULT NULL,
  `area` int DEFAULT NULL,
  `stato` varchar(20) DEFAULT 'da monitorare',
  `num_zone` int NOT NULL DEFAULT '4',
  `concimazione` tinyint DEFAULT '0',
  `raccolta` tinyint DEFAULT '0',
  `id_irrigazione` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `irrigazione_idx` (`id_irrigazione`),
  CONSTRAINT `irrigazione` FOREIGN KEY (`id_irrigazione`) REFERENCES `irrigazione` (`id_irrigazione`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `piantagione`
--

LOCK TABLES `piantagione` WRITE;
/*!40000 ALTER TABLE `piantagione` DISABLE KEYS */;
INSERT INTO `piantagione` VALUES (2,'grano',1,'ottimale',9,0,1,NULL),(3,'grano',100,'buono',6,1,0,NULL),(4,'grano saraceno',20,'scarso',1,1,0,NULL),(5,'grano',100,'buono',6,1,0,NULL),(6,'grano',100,'buono',6,1,0,NULL),(7,'cicoria',5,'ottimale',1,0,0,NULL),(8,'melanzane',5,'ottimale',1,0,0,NULL),(9,'finocchi',5,'ottimale',1,0,0,NULL);
/*!40000 ALTER TABLE `piantagione` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-12 11:21:07
