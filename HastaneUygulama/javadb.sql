-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1:3306
-- Üretim Zamanı: 29 Ara 2020, 17:59:21
-- Sunucu sürümü: 5.7.31
-- PHP Sürümü: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `javadb`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `appoinment`
--

DROP TABLE IF EXISTS `appoinment`;
CREATE TABLE IF NOT EXISTS `appoinment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `hasta_id` int(11) NOT NULL,
  `hasta_name` varchar(255) NOT NULL,
  `app_date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `appoinment`
--

INSERT INTO `appoinment` (`id`, `doctor_id`, `doctor_name`, `hasta_id`, `hasta_name`, `app_date`) VALUES
(1, 1, '1', 1, '1', '1'),
(23, 3, 'Ayşe', 12, 'Hasta Metin', '2020-12-20 10:00:00'),
(22, 3, 'Ayşe', 11, 'Hasta Emre', '2020-12-27 14:00:00');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `clinic`
--

DROP TABLE IF EXISTS `clinic`;
CREATE TABLE IF NOT EXISTS `clinic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `clinic`
--

INSERT INTO `clinic` (`id`, `name`) VALUES
(5, 'Kardiyoloji'),
(2, 'Göz Hastalıkları'),
(3, 'Diş Hekimliği'),
(6, 'Ortopedi'),
(8, 'KBB');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tcno` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `user`
--

INSERT INTO `user` (`id`, `tcno`, `password`, `name`, `type`) VALUES
(1, '1', '111111', 'emre çiftçi', 'bashekim'),
(8, '323232', '23ğşfid', 'Doktor Emin', 'doktor'),
(3, '3', '333333', 'Ayşe', 'doktor'),
(7, '42370586050', '1234', 'Ahmet Mehmet', 'doktor'),
(10, '45454545454', '1234', 'Sedef YEşildağ', 'doktor'),
(13, '2342342342', '123', 'Mehmet Gündoğdu', 'doktor'),
(11, '12', '121212', 'Hasta Emre', 'hasta'),
(12, '123', '123123', 'Hasta Metin', 'hasta');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `whour`
--

DROP TABLE IF EXISTS `whour`;
CREATE TABLE IF NOT EXISTS `whour` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,
  `doctor_name` varchar(255) NOT NULL,
  `wdate` varchar(255) NOT NULL,
  `status` enum('a','p') NOT NULL DEFAULT 'a',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `whour`
--

INSERT INTO `whour` (`id`, `doctor_id`, `doctor_name`, `wdate`, `status`) VALUES
(1, 1, '1', '1', 'a'),
(2, 3, 'Ayşe', '2020-12-20 12:00:00', 'a'),
(7, 10, 'Sedef YEşildağ', '2020-12-22 10:00:00', 'p'),
(4, 3, 'Ayşe', '2020-12-27 14:00:00', 'p'),
(9, 3, 'Ayşe', '2020-12-20 10:00:00', 'a'),
(8, 3, 'Ayşe', '2020-12-23 16:00:00', 'p'),
(10, 3, 'Ayşe', '2021-12-22 15:30:00', 'p');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `worker`
--

DROP TABLE IF EXISTS `worker`;
CREATE TABLE IF NOT EXISTS `worker` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `clinic_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=18 DEFAULT CHARSET=latin5;

--
-- Tablo döküm verisi `worker`
--

INSERT INTO `worker` (`id`, `clinic_id`, `user_id`) VALUES
(1, 1, 1),
(17, 6, 7),
(6, 3, 8),
(7, 5, 5),
(9, 5, 7),
(16, 8, 8),
(14, 2, 3),
(15, 3, 10);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
