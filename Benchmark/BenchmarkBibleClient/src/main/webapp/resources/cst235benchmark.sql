-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Nov 20, 2022 at 06:53 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cst235benchmark`
--

-- --------------------------------------------------------

--
-- Table structure for table `verses`
--

CREATE TABLE `verses` (
  `id` int(11) NOT NULL,
  `book` varchar(50) NOT NULL,
  `chapter_number` int(11) NOT NULL,
  `verse_number` int(11) NOT NULL,
  `verse_content` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `verses`
--

INSERT INTO `verses` (`id`, `book`, `chapter_number`, `verse_number`, `verse_content`) VALUES
(1, 'Thessalonians', 5, 18, 'give thanks in all circumstances; for this is God\'s will for you in Christ Jesus'),
(2, 'Psalms', 56, 3, 'When I am afraid, I put my trust in you.'),
(3, 'Corinthians', 16, 13, 'Be on your guard; stand firm in the faith; be courageous; be strong.'),
(4, 'Corinthians', 16, 14, 'Do everything in love.'),
(5, 'Thessalonians', 5, 11, 'Therefore encourage one another and build each other up, just as in fact you are doing.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `verses`
--
ALTER TABLE `verses`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `verses`
--
ALTER TABLE `verses`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
