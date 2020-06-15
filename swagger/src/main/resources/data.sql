-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1:3306
-- Létrehozás ideje: 2020. Jún 15. 11:34
-- Kiszolgáló verziója: 5.7.24
-- PHP verzió: 7.3.1




/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `reboarding`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `loggedinworkers`
--

DROP TABLE IF EXISTS `loggedinworkers`;
CREATE TABLE IF NOT EXISTS `loggedinworkers` (
  `workerId` varchar(32) NOT NULL
);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `waitinglist`
--

DROP TABLE IF EXISTS `waitinglist`;
CREATE TABLE IF NOT EXISTS `waitinglist` (
  `workerId` varchar(32) NOT NULL,
  `entryDate` date NOT NULL,
  `listPosition` int(11) NOT NULL
);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
