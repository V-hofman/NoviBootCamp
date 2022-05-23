DROP TABLE IF EXISTS `classroom`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `roletype`;
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `classroom` (
                             `ClassID` int NOT NULL AUTO_INCREMENT,
                             `ClassName` varchar(45) NOT NULL,
                             `ClassRoomNR` varchar(45) NOT NULL,
                             `ClassDate` varchar(45) NOT NULL,
                             `ClassTeacher` varchar(45) NOT NULL,
                             PRIMARY KEY (`ClassID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `roletype` (
                            `roleID` int NOT NULL AUTO_INCREMENT,
                            `roleType` varchar(45) NOT NULL,
                            PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO roletype VALUES (1, 'STUDENT');
INSERT INTO roletype VALUES (2, 'ADMIN');

CREATE TABLE `users` (
                         `userID` int NOT NULL AUTO_INCREMENT,
                         `username` varchar(45) NOT NULL,
                         `password` varchar(64) NOT NULL,
                         `personName` varchar(45) DEFAULT NULL,
                         `role` varchar(45) NOT NULL,
                         `userPic` varchar(45),
                         PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student` (
                           `studentID` int NOT NULL AUTO_INCREMENT,
                           `stud_userID` int NOT NULL,
                           PRIMARY KEY (`studentID`,`stud_userID`),
                           KEY `fk_Student_Login1_idx` (`stud_userID`),
                           CONSTRAINT `fk_student_users` FOREIGN KEY (`stud_userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `roles` (
                         `userID` int NOT NULL,
                         `roleID` int NOT NULL,
                         PRIMARY KEY (`userID`,`roleID`),
                         KEY `roletype_roleID_idx` (`roleID`),
                         CONSTRAINT `roletype_roleID` FOREIGN KEY (`roleID`) REFERENCES `roletype` (`roleID`),
                         CONSTRAINT `users_userID` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


