CREATE DATABASE  IF NOT EXISTS `fattoria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fattoria`;
-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: fattoria
-- ------------------------------------------------------
-- Server version	8.0.39

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
  KEY `tipo_mangime_idx` (`tipo_alimentazione`),
  CONSTRAINT `id_stalla` FOREIGN KEY (`nome_stalla`) REFERENCES `stalla` (`etichetta_stalla`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animale`
--

LOCK TABLES `animale` WRITE;
/*!40000 ALTER TABLE `animale` DISABLE KEYS */;
INSERT INTO `animale` VALUES (23,200,'M','Bovino','2021-06-04','grano','stalla_A','2021-07-20',NULL,NULL,NULL),(24,300,'M','Bovino','2010-10-10','erba','stalla_A','2014-03-15',NULL,NULL,'2011-01-01'),(27,0,'M','Bovino',NULL,'granoturco','stalla_A',NULL,NULL,NULL,NULL),(28,0,'M','Bovino',NULL,'granoturco','stalla_A',NULL,NULL,NULL,NULL),(30,5,'F','Galline',NULL,'crusca','stalla_G',NULL,NULL,NULL,NULL),(31,120,'M','Cavallo','2015-05-10','carote','stalla_C',NULL,NULL,NULL,NULL),(32,120,'F','Cavallo','2017-06-10','fieno','stalla_C',NULL,NULL,NULL,NULL),(33,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(34,10,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(35,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(36,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(37,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(38,10,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(39,10,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(40,10,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(41,10,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(42,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(43,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(44,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(45,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(46,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(47,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(48,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(49,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(50,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL),(51,0,'M','Ovino',NULL,'granoturco','stalla_O',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `animale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cisterna`
--

DROP TABLE IF EXISTS `cisterna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cisterna` (
  `id` int NOT NULL AUTO_INCREMENT,
  `capacità` int DEFAULT NULL,
  `quantità` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cisterna`
--

LOCK TABLES `cisterna` WRITE;
/*!40000 ALTER TABLE `cisterna` DISABLE KEYS */;
/*!40000 ALTER TABLE `cisterna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `irrigazione`
--

DROP TABLE IF EXISTS `irrigazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `irrigazione` (
  `id_irrigazione` int NOT NULL AUTO_INCREMENT,
  `ora_inizio` time DEFAULT NULL,
  `durata` float DEFAULT NULL,
  `automatico` tinyint NOT NULL DEFAULT '0',
  `stato` varchar(20) DEFAULT 'da programmare',
  `litri_usati` int DEFAULT NULL,
  PRIMARY KEY (`id_irrigazione`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `irrigazione`
--

LOCK TABLES `irrigazione` WRITE;
/*!40000 ALTER TABLE `irrigazione` DISABLE KEYS */;
INSERT INTO `irrigazione` VALUES (1,'06:00:00',60,1,'ok',10);
/*!40000 ALTER TABLE `irrigazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `irrigazionecisterna`
--

DROP TABLE IF EXISTS `irrigazionecisterna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `irrigazionecisterna` (
  `id_sistema_irrigazione` int NOT NULL,
  `id_cisterna` int NOT NULL,
  PRIMARY KEY (`id_sistema_irrigazione`,`id_cisterna`),
  KEY `id_cisterna_idx` (`id_cisterna`),
  CONSTRAINT `id_cisterna` FOREIGN KEY (`id_cisterna`) REFERENCES `cisterna` (`id`),
  CONSTRAINT `id_sistema_irrigazione` FOREIGN KEY (`id_sistema_irrigazione`) REFERENCES `irrigazione` (`id_irrigazione`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `irrigazionecisterna`
--

LOCK TABLES `irrigazionecisterna` WRITE;
/*!40000 ALTER TABLE `irrigazionecisterna` DISABLE KEYS */;
/*!40000 ALTER TABLE `irrigazionecisterna` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listino`
--

DROP TABLE IF EXISTS `listino`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listino` (
  `tipo_prodotto` varchar(15) NOT NULL,
  `prezzo` float DEFAULT NULL,
  `data_prezzamento` date NOT NULL,
  PRIMARY KEY (`tipo_prodotto`,`data_prezzamento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listino`
--

LOCK TABLES `listino` WRITE;
/*!40000 ALTER TABLE `listino` DISABLE KEYS */;
INSERT INTO `listino` VALUES ('Carne Bovino',24,'2024-10-10'),('Carne Cavallo',22,'2022-01-01'),('Groviera',45,'2022-01-01'),('Lana Pecora',9,'2024-10-10'),('Latte Mucca',0.9,'2022-01-01'),('Latte Mucca',1,'2024-10-10'),('Latte Pecora',0.5,'2022-01-01'),('Latte Pecora',0.8,'2024-10-10');
/*!40000 ALTER TABLE `listino` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `magazzino_new`
--

DROP TABLE IF EXISTS `magazzino_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `magazzino_new` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mangime` varchar(15) NOT NULL,
  `quantita` int NOT NULL DEFAULT '1000',
  `data` date NOT NULL,
  `prezzo_kg` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `tipo_mangime_idx` (`mangime`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `magazzino_new`
--

LOCK TABLES `magazzino_new` WRITE;
/*!40000 ALTER TABLE `magazzino_new` DISABLE KEYS */;
INSERT INTO `magazzino_new` VALUES (1,'crusca',500,'2024-07-07',1),(2,'granoturco',1000,'2024-07-07',1),(3,'grano',700,'2024-07-07',1),(4,'crusca',1000,'2024-09-07',1),(5,'crusca',1000,'2024-09-12',1),(6,'crusca',1000,'2024-09-12',1),(7,'grano',1000,'2024-09-12',1),(8,'grano',1000,'2024-09-12',1),(9,'granoturco',1000,'2024-09-12',1),(10,'granoturco',1000,'2024-09-12',1),(11,'granoturco',1000,'2024-09-12',1),(12,'granoturco',1000,'2024-09-12',1),(13,'granoturco',1000,'2024-09-12',1),(14,'crusca',1000,'2024-09-12',1),(15,'granoturco',1000,'2024-09-12',1),(16,'crusca',1000,'2024-09-12',1),(17,'crusca',10,'2024-01-01',1),(18,'crusca',20,'2024-02-02',1),(19,'crusca',30,'2024-03-03',1),(20,'crusca',40,'2024-04-04',1),(21,'carote',-1,'2024-09-12',1),(22,'carote',-2,'2024-09-12',1),(23,'erba',-2,'2024-09-12',1),(24,'crusca',-2,'2024-09-12',1),(25,'grano',-2,'2024-09-12',1),(26,'fieno',-2,'2024-09-12',1),(27,'granoturco',-4,'2024-09-12',1),(28,'fieno',1000,'2024-09-12',1);
/*!40000 ALTER TABLE `magazzino_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mangimi`
--

DROP TABLE IF EXISTS `mangimi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mangimi` (
  `tipo_mangime` varchar(20) NOT NULL,
  `quantita` int DEFAULT NULL,
  `prezzo_kg` float DEFAULT NULL,
  `data` date NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`tipo_mangime`,`data`,`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mangimi`
--

LOCK TABLES `mangimi` WRITE;
/*!40000 ALTER TABLE `mangimi` DISABLE KEYS */;
INSERT INTO `mangimi` VALUES ('carote',10,10,'2024-01-01',3),('crusca',500,4.3,'2024-01-01',0),('crusca',1000,0,'2024-09-12',0),('erba',10,10,'2024-01-01',2),('fieno',10,10,'2024-01-01',1),('grano',2000,3.1,'2024-01-01',0),('grano',1000,0,'2024-09-12',0),('granoturco',1000,5.8,'2024-01-01',0);
/*!40000 ALTER TABLE `mangimi` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `prodotto`
--

DROP TABLE IF EXISTS `prodotto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prodotto` (
  `id_prodotto` int NOT NULL AUTO_INCREMENT,
  `quantita` int DEFAULT NULL,
  `tipo_prodotto` varchar(20) NOT NULL,
  `data_produzione` date NOT NULL,
  `specie` varchar(45) DEFAULT NULL,
  `stalla` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_prodotto`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prodotto`
--

LOCK TABLES `prodotto` WRITE;
/*!40000 ALTER TABLE `prodotto` DISABLE KEYS */;
INSERT INTO `prodotto` VALUES (2,100,'Latte Mucca','2024-10-10','Bovino','Stalla_A'),(3,100,'Latte Pecora','2024-09-10','Ovino','Stalla_B'),(4,20,'Groviera','2023-01-12','Bovino','Stalla_C'),(5,300,'Carne Cavallo','2024-10-10','Cavallo','Stalla_D'),(6,50,'Latte Mucca','2024-05-05','Bovino','Stalla_A'),(7,300,'Carne Mucca','2024-12-09','Bovino','Stalla_A'),(8,100,'Carne Cavallo','2024-09-12',NULL,NULL),(9,100,'Carne Cavallo','2024-09-12',NULL,NULL),(10,100,'Carne Mucca','2024-09-12',NULL,NULL),(11,100,'Latte Mucca','2024-09-12',NULL,NULL),(12,100,'Latte Mucca','2024-09-12',NULL,NULL),(13,100,'Latte Pecora','2024-09-12','Ovino','stalla_O'),(14,100,'Lana Pecora','2024-01-01','Ovino','stalla_O');
/*!40000 ALTER TABLE `prodotto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produzione_animale`
--

DROP TABLE IF EXISTS `produzione_animale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produzione_animale` (
  `id_animale` int NOT NULL,
  `id_prodotto` int NOT NULL,
  PRIMARY KEY (`id_animale`,`id_prodotto`),
  KEY `id_prod_idx` (`id_prodotto`),
  CONSTRAINT `id_prod` FOREIGN KEY (`id_prodotto`) REFERENCES `prodotto` (`id_prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produzione_animale`
--

LOCK TABLES `produzione_animale` WRITE;
/*!40000 ALTER TABLE `produzione_animale` DISABLE KEYS */;
/*!40000 ALTER TABLE `produzione_animale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `raccolta`
--

DROP TABLE IF EXISTS `raccolta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `raccolta` (
  `id_raccolta` int NOT NULL AUTO_INCREMENT,
  `tipo_pianta` varchar(20) NOT NULL,
  `quantità` int DEFAULT NULL,
  `data_raccolta` date DEFAULT NULL,
  `stato` varchar(20) DEFAULT NULL,
  `operatore` int NOT NULL,
  `macchinario_usato` varchar(20) DEFAULT NULL,
  `id_piantagione` int NOT NULL,
  PRIMARY KEY (`id_raccolta`),
  KEY `operatore_idx` (`operatore`),
  KEY `piantagione_idx` (`id_piantagione`),
  CONSTRAINT `operatore` FOREIGN KEY (`operatore`) REFERENCES `utente` (`id`),
  CONSTRAINT `piantagione` FOREIGN KEY (`id_piantagione`) REFERENCES `piantagione` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `raccolta`
--

LOCK TABLES `raccolta` WRITE;
/*!40000 ALTER TABLE `raccolta` DISABLE KEYS */;
/*!40000 ALTER TABLE `raccolta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stalla`
--

DROP TABLE IF EXISTS `stalla`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stalla` (
  `etichetta_stalla` varchar(13) NOT NULL DEFAULT 'struttura_A',
  `capienza` int DEFAULT NULL,
  `razza` varchar(15) DEFAULT NULL,
  `ora_pranzo` time DEFAULT NULL,
  `ora_cena` time DEFAULT NULL,
  PRIMARY KEY (`etichetta_stalla`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stalla`
--

LOCK TABLES `stalla` WRITE;
/*!40000 ALTER TABLE `stalla` DISABLE KEYS */;
INSERT INTO `stalla` VALUES ('stalla_A',500,'Bovino','10:00:00','22:00:00'),('stalla_C',30,'Cavallo','12:30:00','20:00:00'),('stalla_G',300,'Galline','12:00:00','19:00:00'),('stalla_O',25,'Ovino','11:00:00','18:00:00');
/*!40000 ALTER TABLE `stalla` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `data_nascita` date DEFAULT NULL,
  `ruolo_raccolta` tinyint DEFAULT '0' COMMENT 'se true, utente ha accesso alle feature di raccolta e valutazione del terreno',
  `ruolo_irrigazione` tinyint DEFAULT '0' COMMENT 'se true, utente ha accesso alle impostazioni del sistema di irrigazione',
  `ruolo_pastore` tinyint DEFAULT '0' COMMENT 'se true (1) utente ha permesso di gestione completo degli animali nelle varie stalle. mangime, visite veterinarie e gestione.',
  `ruolo_admin` tinyint DEFAULT '1' COMMENT 'utente che ha permesso di creazione, modifica e eliminazione degli altri account',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Ale','ale@email.com','az','2024-09-04 15:05:18','1997-03-13',0,0,0,1),(2,'Racc','racc@email.com','ra','2024-09-06 08:00:13','2001-07-13',1,0,0,0),(5,'Irr','irr@email.com','ir','2024-09-06 08:06:09','1997-04-18',0,1,0,0),(6,'Pas','pas@email.com','pa','2024-09-06 08:06:09','1999-01-30',0,0,1,0),(7,'Pianta','pianta@email.com','pianta','2024-09-06 17:01:14','1998-10-02',1,1,0,0),(10,'All','tuttiRuoli@gmail.com','all','2024-09-08 15:23:42',NULL,1,1,1,0);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visita_veterinaria`
--

DROP TABLE IF EXISTS `visita_veterinaria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `visita_veterinaria` (
  `data` date NOT NULL,
  `diagnosi` varchar(45) DEFAULT NULL,
  `identificativo_animale` int NOT NULL,
  `cura_prescritta` varchar(120) DEFAULT NULL,
  `nome_veterinario` varchar(20) DEFAULT NULL,
  `cognome_veterinario` varchar(20) DEFAULT NULL,
  `stato_animale` varchar(20) DEFAULT NULL,
  `programmata` tinyint DEFAULT NULL,
  PRIMARY KEY (`data`,`identificativo_animale`),
  KEY `id_anim_idx` (`identificativo_animale`),
  CONSTRAINT `id_anim` FOREIGN KEY (`identificativo_animale`) REFERENCES `animale` (`id_animale`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visita_veterinaria`
--

LOCK TABLES `visita_veterinaria` WRITE;
/*!40000 ALTER TABLE `visita_veterinaria` DISABLE KEYS */;
INSERT INTO `visita_veterinaria` VALUES ('2024-09-10','Da compilare',24,'Da compilare','Da compilare','Da compilare','Da compilare',1),('2024-09-11','Da compilare',24,'Da compilare','Da compilare','Da compilare','Da compilare',1),('2024-09-12','compressione torace',24,'riposo','roberto','guidetti','buono',1),('2024-09-13','stato di salute ottimale',24,'nessuna','Antonio','galetta','eccellente',0);
/*!40000 ALTER TABLE `visita_veterinaria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zona`
--

DROP TABLE IF EXISTS `zona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zona` (
  `coord_x` int NOT NULL,
  `coord_y` int NOT NULL,
  `portata_sensore` int DEFAULT '0',
  `stato_generale_terreno` varchar(10) DEFAULT 'buono',
  `sensore_illuminazione` float DEFAULT NULL,
  `sensore_vento` float DEFAULT NULL,
  `sensore_umidità` float DEFAULT NULL,
  `sensore_temperatura` float DEFAULT NULL,
  `sensore_PH` float DEFAULT NULL,
  `id_piantagione` int NOT NULL,
  PRIMARY KEY (`coord_x`,`coord_y`,`id_piantagione`),
  KEY `id_piant_idx` (`id_piantagione`),
  CONSTRAINT `id_piant` FOREIGN KEY (`id_piantagione`) REFERENCES `piantagione` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zona`
--

LOCK TABLES `zona` WRITE;
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-12 20:11:52
