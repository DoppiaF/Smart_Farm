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
-- Table structure for table `animale`
--

DROP TABLE IF EXISTS `animale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animale` (
  `id_animale` int NOT NULL AUTO_INCREMENT,
  `peso` int DEFAULT NULL,
  `sesso` char(1) DEFAULT NULL,
  `razza` varchar(12) DEFAULT NULL,
  `data_nascita` date DEFAULT NULL,
  `tipo_alimentazione` varchar(20) DEFAULT NULL,
  `nome_stalla` varchar(20) DEFAULT NULL,
  `data_ingresso` date DEFAULT NULL,
  `data_uscita` date DEFAULT NULL,
  `data_morte` date DEFAULT NULL,
  `data_vaccino` date DEFAULT NULL,
  PRIMARY KEY (`id_animale`),
  KEY `id_stalla_idx` (`nome_stalla`),
  KEY `id_magazzino_idx` (`tipo_alimentazione`),
  CONSTRAINT `id_magazzino` FOREIGN KEY (`tipo_alimentazione`) REFERENCES `magazzino` (`tipo_mangime`),
  CONSTRAINT `id_stalla` FOREIGN KEY (`nome_stalla`) REFERENCES `stalla` (`etichetta_stalla`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animale`
--

LOCK TABLES `animale` WRITE;
/*!40000 ALTER TABLE `animale` DISABLE KEYS */;
INSERT INTO `animale` VALUES (4,100,'M','frisona','2020-01-01','granoturco','stalla_A','2021-01-01',NULL,NULL,'2021-01-01'),(23,200,'M','Bovino','2021-06-04','grano','stalla_A','2021-07-20',NULL,NULL,NULL),(24,300,'M','Bovino','2010-10-10','crusca','stalla_A',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `animale` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-12 11:21:06
