-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2024 at 06:05 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `id` int(50) NOT NULL,
  `activity` varchar(255) NOT NULL,
  `user` varchar(255) NOT NULL,
  `datetime` datetime(6) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`id`, `activity`, `user`, `datetime`, `description`) VALUES
(24124, 'adding new item', 'user DB 1', '2024-03-25 12:12:33.000000', 'description DB 1'),
(24125, 'Transaksi', 'adli', '2024-03-30 05:46:03.000000', 'Transaksi pada tanggal2024-03-30 05:46:03'),
(24126, 'Transaksi', 'adli', '2024-03-30 05:47:20.000000', 'Transaksi pada tanggal 2024-03-30 05:47:20'),
(24127, 'Transaksi', 'adli', '2024-03-30 07:21:57.000000', 'Transaksi pada tanggal 2024-03-30 07:21:57, Total Barang: 115000.0, Total Belanja: 115000.0, Jumlah Dibayar: 500000.0, Kembalian: 385000.0'),
(24128, 'Transaksi', 'adli', '2024-03-30 07:27:18.000000', 'Transaksi pada tanggal 2024-03-30 07:27:18, Total Barang: 10, Total Belanja: 115000.0, Jumlah Dibayar: 200000.0, Kembalian: 85000.0'),
(24129, 'Transaksi', 'adli', '2024-03-30 17:48:21.000000', 'Transaksi pada tanggal 2024-03-30 05:48:21, Total Barang: 7, Total Belanja: 95000.0, Jumlah Dibayar: 500000.0, Kembalian: 405000.0'),
(24130, 'Transaksi', 'adli', '2024-03-30 23:15:30.000000', 'Transaksi pada tanggal 2024-03-30 11:15:30, Total Barang: 7, Total Belanja: 85000.0, Jumlah Dibayar: 100000.0, Kembalian: 15000.0'),
(24131, 'Transaksi', 'adli', '2024-03-30 23:17:30.000000', 'Transaksi pada tanggal 2024-03-30 11:17:30, Total Barang: 8, Total Belanja: 100000.0, Jumlah Dibayar: 200000.0, Kembalian: 100000.0'),
(24132, 'Transaksi', 'adli', '2024-03-30 23:19:50.000000', 'Transaksi pada tanggal 2024-03-30 11:19:50, Total Barang: 14, Total Belanja: 175000.0, Jumlah Dibayar: 200000.0, Kembalian: 25000.0'),
(24133, 'Transaksi', 'adli', '2024-03-30 23:23:29.000000', 'Transaksi pada tanggal 2024-03-30 11:23:29, Total Barang: 14, Total Belanja: 175000.0, Jumlah Dibayar: 200000.0, Kembalian: 25000.0'),
(24134, 'Transaksi', 'adli', '2024-03-30 23:30:09.000000', 'Transaksi pada tanggal 2024-03-30 11:30:09, Total Barang: 20, Total Belanja: 250000.0, Jumlah Dibayar: 500000.0, Kembalian: 250000.0'),
(24135, 'Transaksi', 'adli', '2024-03-30 23:33:14.000000', 'Transaksi pada tanggal 2024-03-30 11:33:14, Total Barang: 22, Total Belanja: 295000.0, Jumlah Dibayar: 300000.0, Kembalian: 5000.0'),
(24136, 'Transaksi', 'adli', '2024-03-30 23:34:18.000000', 'Transaksi pada tanggal 2024-03-30 11:34:18, Total Barang: 30, Total Belanja: 375000.0, Jumlah Dibayar: 400000.0, Kembalian: 25000.0'),
(24137, 'Transaksi', 'adli', '2024-03-30 23:34:53.000000', 'Transaksi pada tanggal 2024-03-30 11:34:53, Total Barang: 12, Total Belanja: 165000.0, Jumlah Dibayar: 200000.0, Kembalian: 35000.0'),
(24138, 'Login', 'adli', '2024-03-31 12:21:31.000000', 'User adli logged in at 2024-03-31 12:21:31'),
(24139, 'Logout', 'adli', '2024-03-31 12:21:36.000000', 'User adli logged out at 2024-03-31 12:21:36'),
(24140, 'Login', 'adli', '2024-03-31 12:28:14.000000', 'User adli logged in at 2024-03-31 12:28:14'),
(24141, 'Login', 'adli', '2024-03-31 12:29:18.000000', 'User adli logged in at 2024-03-31 12:29:18'),
(24142, 'Logout', 'adli', '2024-03-31 12:29:20.000000', 'User adli logged out at 2024-03-31 12:29:20'),
(24143, 'Login', 'adli', '2024-03-31 12:30:47.000000', 'User adli logged in at 2024-03-31 12:30:47'),
(24144, 'Logout', 'adli', '2024-03-31 12:30:50.000000', 'User adli logged out at 2024-03-31 12:30:50'),
(24145, 'Login', 'adli', '2024-03-31 14:20:53.000000', 'User adli logged in at 2024-03-31 14:20:53'),
(24146, 'Transaksi 2024-03-31 14:21:17', 'adli', '2024-03-31 14:21:17.000000', ', Total Barang: 10, Total Belanja: 125000.0, Jumlah Dibayar: 200000.0, Kembalian: 75000.0'),
(24147, 'Login', 'adli', '2024-03-31 14:31:55.000000', 'User adli logged in at 2024-03-31 14:31:55'),
(24148, 'Logout', 'adli', '2024-03-31 14:31:58.000000', 'User adli logged out at 2024-03-31 14:31:58'),
(24149, 'Login', 'adli', '2024-03-31 14:38:38.000000', 'Logged to User App'),
(24150, 'Logout', 'adli', '2024-03-31 14:38:43.000000', 'logged out from User App '),
(24151, 'Login', 'adli', '2024-03-31 14:39:57.000000', 'Logged to User App'),
(24152, 'Logout', 'adli', '2024-03-31 14:40:06.000000', 'logged out from User App '),
(24153, 'Login', 'adli', '2024-03-31 14:44:38.000000', 'Logged to User App'),
(24154, 'Login', 'adli', '2024-03-31 15:05:53.000000', 'Logged to User App'),
(24155, 'Login', 'adli', '2024-03-31 15:12:40.000000', 'Logged to User App'),
(24156, 'Logout', 'adli', '2024-03-31 15:12:43.000000', 'logged out from User App '),
(24157, 'Login', 'adli', '2024-03-31 15:20:03.000000', 'Logged to User App'),
(24158, 'Login', 'adli', '2024-03-31 15:27:03.000000', 'Logged to User App'),
(24159, 'Login', 'adli', '2024-03-31 15:27:50.000000', 'Logged to User App'),
(24160, 'Logout', 'adli', '2024-03-31 15:27:52.000000', 'logged out from User App '),
(24161, 'Login', 'adli', '2024-03-31 15:43:35.000000', 'Logged to User App'),
(24162, 'Login', 'nazrul', '2024-03-31 16:12:00.000000', 'Logged to User App'),
(24163, 'Login', 'nazrul', '2024-03-31 16:43:45.000000', 'Logged to User App'),
(24164, 'Logout', 'nazrul', '2024-03-31 16:43:48.000000', 'logged out from User App '),
(24165, 'Login', 'adli', '2024-03-31 16:49:01.000000', 'Logged to User App'),
(24166, 'Logout', 'adli', '2024-03-31 16:49:05.000000', 'logged out from User App '),
(24167, 'Login', 'nazrul', '2024-03-31 16:49:12.000000', 'Logged to User App'),
(24168, 'Transaksi 2024-03-31 16:49:41', 'nazrul', '2024-03-31 16:49:41.000000', 'Total Barang: 31, Total Belanja: 385000.0, Jumlah Dibayar: 400000.0, Kembalian: 15000.0'),
(24169, 'Logout', 'nazrul', '2024-03-31 16:49:48.000000', 'logged out from User App '),
(24170, 'Login', 'rizky', '2024-03-31 16:49:55.000000', 'Logged to User App'),
(24171, 'Transaksi 2024-03-31 16:50:28', 'rizky', '2024-03-31 16:50:28.000000', 'Total Barang: 14, Total Belanja: 180000.0, Jumlah Dibayar: 200000.0, Kembalian: 20000.0'),
(24172, 'Logout', 'rizky', '2024-03-31 16:50:32.000000', 'logged out from User App '),
(24173, 'Login', 'putra', '2024-03-31 16:50:48.000000', 'Logged to User App'),
(24174, 'Transaksi 2024-03-31 16:51:13', 'putra', '2024-03-31 16:51:13.000000', 'Total Barang: 24, Total Belanja: 295000.0, Jumlah Dibayar: 300000.0, Kembalian: 5000.0'),
(24175, 'Logout', 'putra', '2024-03-31 16:51:16.000000', 'logged out from User App '),
(24176, 'Logout', 'putra', '2024-03-31 16:51:17.000000', 'logged out from User App '),
(24177, 'Login', 'adli', '2024-03-31 16:55:20.000000', 'Logged to User App'),
(24178, 'Logout', 'adli', '2024-03-31 16:55:23.000000', 'logged out from User App '),
(24179, 'Login', 'rizky', '2024-03-31 16:55:36.000000', 'Logged to User App'),
(24180, 'Logout', 'rizky', '2024-03-31 16:55:39.000000', 'logged out from User App '),
(24181, 'Logout', 'rizky', '2024-03-31 16:55:41.000000', 'logged out from User App '),
(24182, 'Login', 'nazrul', '2024-03-31 17:20:42.000000', 'Logged in to Admin App'),
(24183, 'Logout', 'nazrul', '2024-03-31 17:21:04.000000', 'Logged out from Admin App'),
(24184, 'Login', 'nazrul', '2024-03-31 17:24:59.000000', 'Logged in to Admin App'),
(24185, 'Logout', 'nazrul', '2024-03-31 17:25:03.000000', 'Logged out from Admin App'),
(24186, 'Logout', 'nazrul', '2024-03-31 17:25:07.000000', 'Logged out from Admin App'),
(24187, 'Login', 'nazrul', '2024-03-31 17:48:15.000000', 'Logged in to Admin App'),
(24188, 'Logout', 'nazrul', '2024-03-31 17:48:18.000000', 'Logged out from Admin App'),
(24189, 'Logout', 'nazrul', '2024-03-31 17:48:20.000000', 'Logged out from Admin App'),
(24190, 'Login', 'adli', '2024-03-31 17:50:50.000000', 'Logged in to Admin App'),
(24191, 'Logout', 'adli', '2024-03-31 17:51:05.000000', 'Logged out from Admin App'),
(24192, 'Login', 'nazrul', '2024-03-31 21:08:12.000000', 'Logged in to Admin App'),
(24193, 'Logout', 'nazrul', '2024-03-31 21:09:00.000000', 'Logged out from Admin App'),
(24194, 'Login', 'nazrul', '2024-03-31 21:19:03.000000', 'Logged in to Admin App'),
(24195, 'Logout', 'nazrul', '2024-03-31 21:20:08.000000', 'Logged out from Admin App'),
(24196, 'Logout', 'nazrul', '2024-03-31 21:20:29.000000', 'Logged out from Admin App'),
(24197, 'Login', 'adli', '2024-03-31 21:24:07.000000', 'Logged in to Admin App'),
(24198, 'Logout', 'adli', '2024-03-31 21:24:11.000000', 'Logged out from Admin App'),
(24199, 'Login', 'nazrul', '2024-03-31 21:26:08.000000', 'Logged in to Admin App'),
(24200, 'Login', 'nazrul', '2024-03-31 21:29:51.000000', 'Logged in to Admin App'),
(24201, 'Transaksi 2024-03-31 21:30:53', 'nazrul', '2024-03-31 21:30:53.000000', 'Total Barang: 4, Total Belanja: 40000.0, Jumlah Dibayar: 50000.0, Kembalian: 10000.0'),
(24202, 'Login', 'nazrul', '2024-03-31 21:41:06.000000', 'User nazrul logged in at 2024-03-31 21:41:06'),
(24203, 'Logout', 'nazrul', '2024-03-31 21:41:19.000000', 'User nazrul logged out at 2024-03-31 21:41:19'),
(24204, 'Login', 'nazrul', '2024-03-31 21:43:48.000000', 'User nazrul logged in at 2024-03-31 21:43:48'),
(24205, 'Transaksi', 'nazrul', '2024-03-31 21:44:52.000000', 'Transaksi pada tanggal 2024-03-31 09:44:52, Total Barang: 4, Total Belanja: 40000.0, Jumlah Dibayar: 50000.0, Kembalian: 10000.0'),
(24206, 'Logout', 'nazrul', '2024-03-31 21:44:57.000000', 'User nazrul logged out at 2024-03-31 21:44:57'),
(24207, 'Login', 'nazrul', '2024-03-31 21:53:31.000000', 'User nazrul logged in at 2024-03-31 21:53:31'),
(24208, 'Transaksi', 'nazrul', '2024-03-31 21:54:30.000000', 'Transaksi pada tanggal 2024-03-31 09:54:30, Total Barang: 5, Total Belanja: 50000.0, Jumlah Dibayar: 100000.0, Kembalian: 50000.0'),
(24209, 'Logout', 'nazrul', '2024-03-31 21:54:34.000000', 'User nazrul logged out at 2024-03-31 21:54:34');

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kode` varchar(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `harga` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kode`, `nama`, `harga`) VALUES
('1111', 'Chitato BBQ Normal', 10000),
('2222', 'Oreo Normal', 15000);

-- --------------------------------------------------------

--
-- Table structure for table `itemtransaksi`
--

CREATE TABLE `itemtransaksi` (
  `id` int(11) NOT NULL,
  `idTransaksi` int(11) NOT NULL,
  `kode` int(11) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` float NOT NULL,
  `jumlah` int(11) NOT NULL,
  `total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `itemtransaksi`
--

INSERT INTO `itemtransaksi` (`id`, `idTransaksi`, `kode`, `nama`, `harga`, `jumlah`, `total`) VALUES
(1, 1, 1111, 'Chitato BBQ Normal', 10000, 4, 40000),
(2, 1, 2222, 'Oreo Normal', 15000, 4, 60000),
(3, 2, 2222, 'Oreo Normal', 15000, 7, 105000),
(4, 2, 1111, 'Chitato BBQ Normal', 10000, 7, 70000),
(5, 3, 2222, 'Oreo Normal', 15000, 7, 105000),
(6, 3, 1111, 'Chitato BBQ Normal', 10000, 7, 70000),
(7, 4, 1111, 'Chitato BBQ Normal', 10000, 10, 100000),
(8, 4, 2222, 'Oreo Normal', 15000, 10, 150000),
(9, 5, 2222, 'Oreo Normal', 15000, 15, 225000),
(10, 5, 1111, 'Chitato BBQ Normal', 10000, 7, 70000),
(11, 6, 2222, 'Oreo Normal', 15000, 15, 225000),
(12, 6, 1111, 'Chitato BBQ Normal', 10000, 15, 150000),
(13, 7, 1111, 'Chitato BBQ Normal', 10000, 3, 30000),
(14, 7, 2222, 'Oreo Normal', 15000, 9, 135000),
(15, 8, 1111, 'Chitato BBQ Normal', 10000, 5, 50000),
(16, 8, 2222, 'Oreo Normal', 15000, 5, 75000),
(17, 9, 1111, 'Chitato BBQ Normal', 10000, 16, 160000),
(18, 9, 2222, 'Oreo Normal', 15000, 15, 225000),
(19, 10, 2222, 'Oreo Normal', 15000, 8, 120000),
(20, 10, 1111, 'Chitato BBQ Normal', 10000, 6, 60000),
(21, 11, 1111, 'Chitato BBQ Normal', 10000, 13, 130000),
(22, 11, 2222, 'Oreo Normal', 15000, 11, 165000),
(25, 12, 1111, 'Chitato BBQ Normal', 10000, 4, 40000),
(26, 13, 1111, 'Chitato BBQ Normal', 10000, 4, 40000),
(27, 14, 1111, 'Chitato BBQ Normal', 10000, 5, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `idTransaksi` int(15) NOT NULL,
  `user` varchar(255) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` time NOT NULL,
  `deskripsi` varchar(255) NOT NULL,
  `totalBelanja` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`idTransaksi`, `user`, `tanggal`, `waktu`, `deskripsi`, `totalBelanja`) VALUES
(1, 'adli', '2024-03-30', '23:17:30', 'Transaksi pada tanggal 2024-03-30 23:17:30, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 100000.0', 100000),
(2, 'adli', '2024-03-30', '23:19:50', 'Transaksi pada tanggal 2024-03-30 23:19:50, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 25000.0', 175000),
(3, 'adli', '2024-03-30', '23:23:29', 'Transaksi pada tanggal 2024-03-30 23:23:29, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 25000.0', 175000),
(4, 'adli', '2024-03-30', '23:30:09', 'Transaksi pada tanggal 2024-03-30 23:30:09, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 500000, Kembalian: 250000.0', 250000),
(5, 'adli', '2024-03-30', '23:33:14', 'Transaksi pada tanggal 2024-03-30 23:33:14, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 300000, Kembalian: 5000.0', 295000),
(6, 'adli', '2024-03-30', '23:34:18', 'Transaksi pada tanggal 2024-03-30 23:34:18, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 400000, Kembalian: 25000.0', 375000),
(7, 'adli', '2024-03-30', '23:34:53', 'Transaksi pada tanggal 2024-03-30 23:34:53, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 35000.0', 165000),
(8, 'adli', '2024-03-31', '14:21:17', 'Transaksi pada tanggal 2024-03-31 14:21:17, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 75000.0', 125000),
(9, 'nazrul', '2024-03-31', '16:49:41', 'Transaksi pada tanggal 2024-03-31 16:49:41, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 400000, Kembalian: 15000.0', 385000),
(10, 'rizky', '2024-03-31', '16:50:28', 'Transaksi pada tanggal 2024-03-31 16:50:28, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 200000, Kembalian: 20000.0', 180000),
(11, 'putra', '2024-03-31', '16:51:13', 'Transaksi pada tanggal 2024-03-31 16:51:13, Total Barang: 2, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 300000, Kembalian: 5000.0', 295000),
(12, 'nazrul', '2024-03-31', '21:30:53', 'Transaksi pada tanggal 2024-03-31 21:30:53, Total Barang: 1, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 50000, Kembalian: 10000.0', 40000),
(13, 'nazrul', '2024-03-31', '21:44:52', 'Transaksi pada tanggal 2024-03-31 21:44:52, Total Barang: 1, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 50000, Kembalian: 10000.0', 40000),
(14, 'nazrul', '2024-03-31', '21:54:30', 'Transaksi pada tanggal 2024-03-31 21:54:30, Total Barang: 1, Total Belanja: TextField[id=totalBelanja, styleClass=text-input text-field], Jumlah Dibayar: 100000, Kembalian: 50000.0', 50000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `session` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Id`, `username`, `password`, `session`) VALUES
(1, 'adli', 'ZrpK662APLUV2JbL9Uk2QvdUAmCsIH2UEPbcCYTH0Xw=', 0),
(2, 'nazrul', 'F9AyPPwDwhnMa0RDp1DfKe/UjSppU0rVpsOkMzpyeOY=', 0),
(3, 'rizky', 'ErsJ4s4CumZSRE26y7hkmd3fDAYS9kan8oDkWfV77fo=', 0),
(4, 'putra', 'izB7mKh2ax3vscqPG9nHvEJ/u2pmWTc6o5+YTWKFHXw=', 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `itemtransaksi`
--
ALTER TABLE `itemtransaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idTransaksi` (`idTransaksi`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`idTransaksi`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24210;

--
-- AUTO_INCREMENT for table `itemtransaksi`
--
ALTER TABLE `itemtransaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `idTransaksi` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `itemtransaksi`
--
ALTER TABLE `itemtransaksi`
  ADD CONSTRAINT `itemtransaksi_ibfk_1` FOREIGN KEY (`idTransaksi`) REFERENCES `transaksi` (`idTransaksi`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
