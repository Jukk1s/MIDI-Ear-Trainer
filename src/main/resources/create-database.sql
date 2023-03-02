CREATE DATABASE IF NOT EXISTS `midieartrainer`;
USE `midieartrainer`;

CREATE TABLE IF NOT EXISTS `user` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

CREATE TABLE IF NOT EXISTS `game` (
  `GameID` int(11) NOT NULL AUTO_INCREMENT,
  `PlayedAt` timestamp NOT NULL,
  `SelectedInterval` int(11) NOT NULL,
  `CorrectInterval` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  PRIMARY KEY (`GameID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `game_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;