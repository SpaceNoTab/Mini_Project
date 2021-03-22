-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2017 at 04:34 AM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gmc`
--

-- --------------------------------------------------------

--
-- Table structure for table `registration`
--

CREATE TABLE `registration` (
  `ID` int(200) NOT NULL,
  `Name` varchar(200) NOT NULL,
  `Password` varchar(200) NOT NULL,
  `Email` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registration`
--

INSERT INTO `registration` (`ID`, `Name`, `Password`, `Email`) VALUES
(2, 'anand', 'ananad', 'ananand@gmial.com'),
(3, 'test', 'test', 'test@gmail.com'),
(4, 'anand', 'anand', 'anand@gmail.com'),
(5, 'ahah', 'ahaj', 'abs@gmail.com'),
(6, 'hy', 'hai', 'haha@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `Name` text NOT NULL,
  `Phone` int(10) NOT NULL,
  `groupblood` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `request`
--

INSERT INTO `request` (`Name`, `Phone`, `groupblood`) VALUES
('hai', 2147483647, 'AB '),
('haihg', 454778, 'A '),
('test', 86488, 'AB-'),
('gaj', 5358, 'AB '),
('sgsuh', 7867, 'AB ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `registration`
--
ALTER TABLE `registration`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `registration`
--
ALTER TABLE `registration`
  MODIFY `ID` int(200) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
