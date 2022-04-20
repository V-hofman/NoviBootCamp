-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: school
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `ClassID` int NOT NULL AUTO_INCREMENT,
  `Teacher_TeachID` int NOT NULL,
  `ClassName` varchar(45) NOT NULL,
  `ClassRoomNR` int NOT NULL,
  PRIMARY KEY (`ClassID`,`Teacher_TeachID`),
  KEY `fk_Classroom_Teacher1_idx` (`Teacher_TeachID`),
  CONSTRAINT `fk_classroom_teacher1` FOREIGN KEY (`Teacher_TeachID`) REFERENCES `teacher` (`TeachID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (3,1,'Wiskunde',12);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroomstudent`
--

DROP TABLE IF EXISTS `classroomstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroomstudent` (
  `classroom_classroomID` int NOT NULL,
  `student_user_userID` int NOT NULL,
  PRIMARY KEY (`classroom_classroomID`,`student_user_userID`),
  KEY `fk_student1_idx` (`student_user_userID`),
  CONSTRAINT `fk_classroom1` FOREIGN KEY (`classroom_classroomID`) REFERENCES `classroom` (`ClassID`),
  CONSTRAINT `fk_student1` FOREIGN KEY (`student_user_userID`) REFERENCES `student` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroomstudent`
--

LOCK TABLES `classroomstudent` WRITE;
/*!40000 ALTER TABLE `classroomstudent` DISABLE KEYS */;
/*!40000 ALTER TABLE `classroomstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parents`
--

DROP TABLE IF EXISTS `parents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parents` (
  `ParentID` int NOT NULL AUTO_INCREMENT,
  `ParentName` varchar(45) NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`ParentID`,`userID`),
  KEY `fk_parents_users1_idx` (`userID`),
  CONSTRAINT `fk_parents_users1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This table is used to determine the parents data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parentsstudent`
--

DROP TABLE IF EXISTS `parentsstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parentsstudent` (
  `Parents_ParentID` int NOT NULL,
  `Student_userID` int NOT NULL,
  PRIMARY KEY (`Parents_ParentID`,`Student_userID`),
  KEY `fk_student1_idx` (`Student_userID`),
  CONSTRAINT `fk_parents1` FOREIGN KEY (`Parents_ParentID`) REFERENCES `parents` (`ParentID`),
  CONSTRAINT `fk_student2` FOREIGN KEY (`Student_userID`) REFERENCES `student` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parentsstudent`
--

LOCK TABLES `parentsstudent` WRITE;
/*!40000 ALTER TABLE `parentsstudent` DISABLE KEYS */;
/*!40000 ALTER TABLE `parentsstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `StudName` varchar(45) NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `fk_Student_Login1_idx` (`userID`),
  CONSTRAINT `fk_student_users` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This table handles the student information\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('John Smith',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `TeachID` int NOT NULL AUTO_INCREMENT,
  `TeachName` varchar(45) NOT NULL,
  `userID` int NOT NULL,
  PRIMARY KEY (`TeachID`,`userID`),
  KEY `fk_teacher_users1_idx` (`userID`),
  CONSTRAINT `fk_teacher_users1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='This table handles the Teacher information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Mr. Ron',1);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(64) NOT NULL,
  `personName` varchar(45) NOT NULL,
  `isParent` tinyint NOT NULL DEFAULT '0',
  `isTeacher` tinyint NOT NULL DEFAULT '0',
  `isStudent` tinyint NOT NULL DEFAULT '0',
  `isAdmin` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,'john12','$2a$10$dlNz3/LvgrHklxyvGmgaWe3hhsM4pDy3wWTbXW1H9fe6g5.BUQKpS','John Smith',0,0,1,0),(1,'Ron12','1235','Rick Ron',0,1,0,0),(3,'Harry20','9876','Harry Dotter',0,0,0,1),(4,'Derik12','Password','Derik Shoen',0,0,1,0),(5,'Derik12','Password','Derik Shoen',0,0,1,0),(6,'Derik12','Password','Derik Shoen',0,0,1,0),(7,'Derik12','Password','Derik Shoen',0,0,1,0),(8,'Derik12','Password','Derik Shoen',0,0,1,0),(9,'Derik12','Password','Derik Shoen',0,0,1,0),(10,'Derik12','Password','Derik Shoen',0,0,1,0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-04  6:04:36
