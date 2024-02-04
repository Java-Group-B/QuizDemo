-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: group_b_db
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `question_bank`
--

DROP TABLE IF EXISTS `question_bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_bank` (
  `question_id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `option_a` varchar(255) DEFAULT NULL,
  `option_b` varchar(255) DEFAULT NULL,
  `option_c` varchar(255) DEFAULT NULL,
  `option_d` varchar(255) DEFAULT NULL,
  `correct_answer` char(255) DEFAULT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_bank`
--

LOCK TABLES `question_bank` WRITE;
/*!40000 ALTER TABLE `question_bank` DISABLE KEYS */;
INSERT INTO `question_bank` VALUES (1,' What is the return type of the hashCode() method in the Object class?','Object','int','long','void','b'),(2,'An interface with no fields or methods is known as a ______.','Runnable Interface','Marker Interface','Abstract Interface','CharSequence Interface','b'),(3,'Which option is false about the final keyword?','A final method cannot be overridden in its subclasses.','A final class cannot be extended.','A final class cannot extend other classes.','A final method can be inherited.','a'),(4,'Which of the following is not an OOPS concept in Java?','Polymorphism','Inheritance','Compilation','Encapsulation','c'),(5,'What is not the use of “this” keyword in Java?','Referring to the instance variable when a local variable has the same name','Passing itself to the method of the same class','Passing itself to another method','Calling another constructor in constructor chaining','b'),(6,'Which of these are selection statements in Java?','break','continue','for()','if()','d'),(7,'Which of these statements is incorrect about Thread?','start() method is used to begin execution of the thread','run() method is used to begin execution of a thread before start() method in special cases','A thread can be formed by implementing Runnable interface only','A thread can be formed by a class that extends Thread class','b'),(8,'Which of these keywords are used for the block to be examined for exceptions?','check','throw','catch','try','b'),(9,'When is the object created with new keyword?','At run time','At compile time','depends on code','None','a'),(10,' Which of the following is a superclass of every class in Java?','ArrayList','Abstract class','Object class','String','c');
/*!40000 ALTER TABLE `question_bank` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-04 13:58:38
