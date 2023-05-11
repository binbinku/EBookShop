-- MySQL dump 10.13  Distrib 8.0.32, for macos13 (x86_64)
--
-- Host: 127.0.0.1    Database: ebookshop
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `bookcategorys`
--

DROP TABLE IF EXISTS `bookcategorys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookcategorys` (
  `bcno` int NOT NULL,
  `cname` text,
  PRIMARY KEY (`bcno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcategorys`
--

LOCK TABLES `bookcategorys` WRITE;
/*!40000 ALTER TABLE `bookcategorys` DISABLE KEYS */;
INSERT INTO `bookcategorys` VALUES (1001,'计算机'),(1002,'文学'),(1003,'政治');
/*!40000 ALTER TABLE `bookcategorys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookorder`
--

DROP TABLE IF EXISTS `bookorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookorder` (
  `boid` int NOT NULL,
  `bcfmtime` text,
  `btotalprice` int DEFAULT NULL,
  `baddress` text,
  `btelephone` text,
  PRIMARY KEY (`boid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookorder`
--

LOCK TABLES `bookorder` WRITE;
/*!40000 ALTER TABLE `bookorder` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bno` int NOT NULL,
  `bname` varchar(20) DEFAULT NULL,
  `bauthor` varchar(20) DEFAULT NULL,
  `bprice` int DEFAULT NULL,
  `bcategory` int DEFAULT NULL,
  `burl` text,
  `binfo` varchar(20) DEFAULT NULL,
  `bpubdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (0,'Python编程','那个人',99,1001,'https://weread-1258476243.file.myqcloud.com/weread/cover/8/YueWen_22806930/t6_YueWen_22806930.jpg','一本计算机编程语言书籍','2022'),(1,'网络是怎样连接的','那个人',68,1001,'https://weread-1258476243.file.myqcloud.com/weread/cover/15/YueWen_907755/t6_YueWen_907755.jpg','一本计算机编程语言书籍','2021'),(2,'Java编程的逻辑','那个人',68,1001,'https://weread-1258476243.file.myqcloud.com/weread/cover/7/YueWen_923038/t6_YueWen_923038.jpg','一本计算机编程语言书籍','2021');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopadmin`
--

DROP TABLE IF EXISTS `shopadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopadmin` (
  `aid` int NOT NULL,
  `adminaccount` text,
  `adminpassword` text,
  `email` text,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopadmin`
--

LOCK TABLES `shopadmin` WRITE;
/*!40000 ALTER TABLE `shopadmin` DISABLE KEYS */;
INSERT INTO `shopadmin` VALUES (0,'admin','admin','222@qq.com');
/*!40000 ALTER TABLE `shopadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopnotice`
--

DROP TABLE IF EXISTS `shopnotice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopnotice` (
  `nid` int NOT NULL AUTO_INCREMENT,
  `noticetitle` text,
  `noticedate` text,
  `noticecontent` text,
  PRIMARY KEY (`nid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopnotice`
--

LOCK TABLES `shopnotice` WRITE;
/*!40000 ALTER TABLE `shopnotice` DISABLE KEYS */;
INSERT INTO `shopnotice` VALUES (1,'公告001','这是公告001','这是公告001的具体内容'),(2,'公告002','这是公告002','这是公告002的具体内容');
/*!40000 ALTER TABLE `shopnotice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uid` int NOT NULL DEFAULT '0',
  `account` text,
  `password` text,
  `gender` text,
  `telephone` text,
  `address` text,
  `email` text,
  `signtime` text,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'bk','112233',NULL,NULL,NULL,NULL,NULL),(1,'binbinku','123456','男','112233','china','123@gmail.com','2023-1-1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-10 17:38:34
