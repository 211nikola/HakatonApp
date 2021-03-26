-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 29, 2020 at 04:43 PM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hakaton_projekat`
--

-- --------------------------------------------------------

--
-- Table structure for table `administrator`
--

CREATE TABLE `administrator` (
  `AdministratorID` int(11) NOT NULL,
  `KorisnickoIme` varchar(255) NOT NULL,
  `Lozinka` varchar(255) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `TipAdministratora` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `administrator`
--

INSERT INTO `administrator` (`AdministratorID`, `KorisnickoIme`, `Lozinka`, `Ime`, `Prezime`, `Mail`, `TipAdministratora`) VALUES
(1, 'admin', 'admin', 'Nikola', 'Obradovic', 'nikola@gmail.com', 'Odrzavanje servera');

-- --------------------------------------------------------

--
-- Table structure for table `clan`
--

CREATE TABLE `clan` (
  `ClanID` int(11) NOT NULL,
  `TimID` int(11) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `Uloga` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `clan`
--

INSERT INTO `clan` (`ClanID`, `TimID`, `Ime`, `Prezime`, `Mail`, `Uloga`) VALUES
(22, 31, 'Clan1', 'Clan1', 'clan1\"mail.com', 'Dizajner'),
(23, 31, 'Clan2', 'Clan2', 'Clan2', 'Clan2'),
(27, 35, 'd', 'd', 'd', 'd'),
(28, 36, 't', 't', 't', 't'),
(29, 36, 't', 't', 't', 't'),
(30, 37, 'yyyY', 'y', 'y', 'y');

-- --------------------------------------------------------

--
-- Table structure for table `hakaton`
--

CREATE TABLE `hakaton` (
  `HakatonID` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `Datum` date NOT NULL,
  `AdministratorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hakaton`
--

INSERT INTO `hakaton` (`HakatonID`, `Naziv`, `Datum`, `AdministratorID`) VALUES
(46, 'test', '2020-08-19', 1),
(47, 'TEST', '2020-08-27', 1),
(50, 'T', '2020-08-27', 1),
(51, 'Hakaton 1', '2020-08-28', 1),
(52, 'Hakaton 1', '2020-08-27', 1);

-- --------------------------------------------------------

--
-- Table structure for table `mentor`
--

CREATE TABLE `mentor` (
  `MentorID` int(11) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `Profesija` varchar(255) NOT NULL,
  `administratorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mentor`
--

INSERT INTO `mentor` (`MentorID`, `Ime`, `Prezime`, `Mail`, `Profesija`, `administratorID`) VALUES
(16, 'Mentor', 'Mentor', 'mentor@gmail.com', 'mentor', 1),
(17, 'e', 'e', 'e', 'e', 1);

-- --------------------------------------------------------

--
-- Table structure for table `ocena`
--

CREATE TABLE `ocena` (
  `OcenaID` int(11) NOT NULL,
  `TimID` int(11) NOT NULL,
  `Dizajn` int(10) NOT NULL,
  `Efikasnost` int(10) NOT NULL,
  `Slozenost` int(10) NOT NULL,
  `Komentar` varchar(255) NOT NULL,
  `SudijaID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `sudija`
--

CREATE TABLE `sudija` (
  `SudijaID` int(11) NOT NULL,
  `KorisnickoIme` varchar(255) NOT NULL,
  `Lozinka` varchar(255) NOT NULL,
  `Ime` varchar(255) NOT NULL,
  `Prezime` varchar(255) NOT NULL,
  `Mail` varchar(255) NOT NULL,
  `Profesija` varchar(255) NOT NULL,
  `Zemlja` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sudija`
--

INSERT INTO `sudija` (`SudijaID`, `KorisnickoIme`, `Lozinka`, `Ime`, `Prezime`, `Mail`, `Profesija`, `Zemlja`) VALUES
(36, '2', '2', '2', '2', '22', '2', '2'),
(37, '3', '3', '3', '3', '3', '3', '3'),
(39, 'Sudija1', 'Sudija1', 'Sudija', 'Sudija', 'sudija@gmail.com', 'Programer', 'Srbija'),
(41, 'test', 'test', 'test', 'test', 'test', 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `sudijahakaton`
--

CREATE TABLE `sudijahakaton` (
  `SudijaID` int(11) NOT NULL,
  `HakatonID` int(11) NOT NULL,
  `AdministratorID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sudijahakaton`
--

INSERT INTO `sudijahakaton` (`SudijaID`, `HakatonID`, `AdministratorID`) VALUES
(36, 50, 1),
(37, 50, 1),
(39, 51, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tim`
--

CREATE TABLE `tim` (
  `TimID` int(11) NOT NULL,
  `Naziv` varchar(255) NOT NULL,
  `AdministratorID` int(11) NOT NULL,
  `MentorID` int(11) NOT NULL,
  `HakatonID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tim`
--

INSERT INTO `tim` (`TimID`, `Naziv`, `AdministratorID`, `MentorID`, `HakatonID`) VALUES
(31, 'Tim 1', 1, 16, 46),
(33, 'Tim3', 1, 16, 50),
(34, 'Tim3', 1, 16, 50),
(35, 'T', 1, 16, 46),
(36, 'TT', 1, 16, 46),
(37, 'YYYY', 1, 16, 46);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrator`
--
ALTER TABLE `administrator`
  ADD PRIMARY KEY (`AdministratorID`);

--
-- Indexes for table `clan`
--
ALTER TABLE `clan`
  ADD PRIMARY KEY (`ClanID`,`TimID`),
  ADD KEY `clan_ibfk_1` (`TimID`);

--
-- Indexes for table `hakaton`
--
ALTER TABLE `hakaton`
  ADD PRIMARY KEY (`HakatonID`),
  ADD KEY `hakaton_ibfk_1` (`AdministratorID`);

--
-- Indexes for table `mentor`
--
ALTER TABLE `mentor`
  ADD PRIMARY KEY (`MentorID`),
  ADD KEY `mentor_ibfk_1` (`administratorID`);

--
-- Indexes for table `ocena`
--
ALTER TABLE `ocena`
  ADD PRIMARY KEY (`OcenaID`,`TimID`),
  ADD KEY `ocena_ibfk_1` (`SudijaID`),
  ADD KEY `ocena_ibfk_2` (`TimID`);

--
-- Indexes for table `sudija`
--
ALTER TABLE `sudija`
  ADD PRIMARY KEY (`SudijaID`);

--
-- Indexes for table `sudijahakaton`
--
ALTER TABLE `sudijahakaton`
  ADD KEY `sudijahakaton_ibfk_1` (`HakatonID`),
  ADD KEY `SudijaID` (`SudijaID`,`HakatonID`) USING BTREE,
  ADD KEY `sudijahakaton_ibfk_3` (`AdministratorID`);

--
-- Indexes for table `tim`
--
ALTER TABLE `tim`
  ADD PRIMARY KEY (`TimID`),
  ADD KEY `tim_ibfk_1` (`MentorID`),
  ADD KEY `tim_ibfk_2` (`AdministratorID`),
  ADD KEY `tim_ibfk_3` (`HakatonID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `administrator`
--
ALTER TABLE `administrator`
  MODIFY `AdministratorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `clan`
--
ALTER TABLE `clan`
  MODIFY `ClanID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `hakaton`
--
ALTER TABLE `hakaton`
  MODIFY `HakatonID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT for table `mentor`
--
ALTER TABLE `mentor`
  MODIFY `MentorID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `ocena`
--
ALTER TABLE `ocena`
  MODIFY `OcenaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `sudija`
--
ALTER TABLE `sudija`
  MODIFY `SudijaID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `tim`
--
ALTER TABLE `tim`
  MODIFY `TimID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `clan`
--
ALTER TABLE `clan`
  ADD CONSTRAINT `clan_ibfk_1` FOREIGN KEY (`TimID`) REFERENCES `tim` (`TimID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `hakaton`
--
ALTER TABLE `hakaton`
  ADD CONSTRAINT `hakaton_ibfk_1` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `mentor`
--
ALTER TABLE `mentor`
  ADD CONSTRAINT `mentor_ibfk_1` FOREIGN KEY (`administratorID`) REFERENCES `administrator` (`AdministratorID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ocena`
--
ALTER TABLE `ocena`
  ADD CONSTRAINT `ocena_ibfk_1` FOREIGN KEY (`SudijaID`) REFERENCES `sudija` (`SudijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ocena_ibfk_2` FOREIGN KEY (`TimID`) REFERENCES `tim` (`TimID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sudijahakaton`
--
ALTER TABLE `sudijahakaton`
  ADD CONSTRAINT `sudijahakaton_ibfk_1` FOREIGN KEY (`HakatonID`) REFERENCES `hakaton` (`HakatonID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sudijahakaton_ibfk_2` FOREIGN KEY (`SudijaID`) REFERENCES `sudija` (`SudijaID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sudijahakaton_ibfk_3` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tim`
--
ALTER TABLE `tim`
  ADD CONSTRAINT `tim_ibfk_1` FOREIGN KEY (`MentorID`) REFERENCES `mentor` (`MentorID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tim_ibfk_2` FOREIGN KEY (`AdministratorID`) REFERENCES `administrator` (`AdministratorID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tim_ibfk_3` FOREIGN KEY (`HakatonID`) REFERENCES `hakaton` (`HakatonID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
