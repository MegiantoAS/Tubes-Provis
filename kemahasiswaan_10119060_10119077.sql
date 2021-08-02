-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2021 at 02:07 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kemahasiswaan_10119060_10119077`
--

-- --------------------------------------------------------

--
-- Stand-in structure for view `hitungnilai`
-- (See below for the actual view)
--
CREATE TABLE `hitungnilai` (
`nilaiabsensi` double
,`nilaitugas` double
,`nilaiuts` double
,`nilaiuas` double
,`nilaiakhir` double
,`indeks` varchar(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `keterangan`
-- (See below for the actual view)
--
CREATE TABLE `keterangan` (
`ket` varchar(11)
);

-- --------------------------------------------------------

--
-- Table structure for table `t_mahasiswa`
--

CREATE TABLE `t_mahasiswa` (
  `nim` varchar(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `ttl` varchar(20) NOT NULL,
  `tgl_lahir` date NOT NULL,
  `alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_mahasiswa`
--

INSERT INTO `t_mahasiswa` (`nim`, `nama`, `ttl`, `tgl_lahir`, `alamat`) VALUES
('10105120', 'Hendro Herlambang', 'Bandung', '1988-05-21', 'JL. Tubagus Ismail No 5'),
('10105121', 'Ratu Husna', 'Bandung', '1988-03-20', 'Jl. Cumandiri 15'),
('10105122', 'Angga Setiaydi', 'Bandung', '1988-10-30', 'Jl.Sekeloa 20'),
('10119060', 'Megianto Adi Saputra', 'Bandung', '2001-05-19', 'Jl.skeloa'),
('10119077', 'Ridhwan Anwar Fauzan', 'Bandung', '2001-05-01', 'Jl.panjaitan');

-- --------------------------------------------------------

--
-- Table structure for table `t_mata_kuliah`
--

CREATE TABLE `t_mata_kuliah` (
  `kd_mk` varchar(8) NOT NULL,
  `nama_mk` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_mata_kuliah`
--

INSERT INTO `t_mata_kuliah` (`kd_mk`, `nama_mk`) VALUES
('IF235359', 'Pemograman Visual'),
('IF256544', 'B.Inggris'),
('IF289897', 'Sistem Informasi'),
('IF34348', 'Pemograman Lanjut'),
('IF37325P', 'Komputer Grafik'),
('IF99191', 'Algoritma'),
('IF99192', 'Teorema Bahasa'),
('IF991922', 'Kalkulus ');

-- --------------------------------------------------------

--
-- Table structure for table `t_nilai`
--

CREATE TABLE `t_nilai` (
  `kd_nilai` int(11) NOT NULL,
  `nim` varchar(8) NOT NULL,
  `kd_mk` varchar(8) NOT NULL,
  `absensi` double NOT NULL,
  `tugas1` double NOT NULL,
  `tugas2` double NOT NULL,
  `tugas3` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nilai`, `nim`, `kd_mk`, `absensi`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`) VALUES
(31, '10105121', 'IF34348', 14, 80, 80, 80, 80, 80),
(32, '10105120', 'IF34348', 14, 100, 100, 100, 100, 100);

-- --------------------------------------------------------

--
-- Structure for view `hitungnilai`
--
DROP TABLE IF EXISTS `hitungnilai`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `hitungnilai`  AS SELECT `t_nilai`.`absensi`/ 14 * 100 * 5 / 100 AS `nilaiabsensi`, (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) AS `nilaitugas`, `t_nilai`.`uts`* 30 / 100 AS `nilaiuts`, `t_nilai`.`uas`* 40 / 100 AS `nilaiuas`, `t_nilai`.`absensi`/ 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 AS `nilaiakhir`, CASE WHEN `t_nilai`.`absensi` / 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 > 79 THEN 'A' WHEN `t_nilai`.`absensi` / 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 > 67 THEN 'B' WHEN `t_nilai`.`absensi` / 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 > 55 THEN 'C' WHEN `t_nilai`.`absensi` / 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 > 44 THEN 'D' WHEN `t_nilai`.`absensi` / 14 * 100 * 5 / 100 + (`t_nilai`.`tugas1` + `t_nilai`.`tugas2` + `t_nilai`.`tugas3`) / 3 * (25 / 100) + `t_nilai`.`uts` * 30 / 100 + `t_nilai`.`uas` * 40 / 100 >= 0 THEN 'E' END AS `indeks` FROM `t_nilai` ;

-- --------------------------------------------------------

--
-- Structure for view `keterangan`
--
DROP TABLE IF EXISTS `keterangan`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `keterangan`  AS SELECT CASE WHEN `hitungnilai`.`nilaiakhir` > 79 THEN 'LULUS' WHEN `hitungnilai`.`nilaiakhir` > 67 THEN 'LULUS' WHEN `hitungnilai`.`nilaiakhir` > 55 THEN 'LULUS' WHEN `hitungnilai`.`nilaiakhir` > 44 THEN 'TIDAK LULUS' WHEN `hitungnilai`.`nilaiakhir` >= 0 THEN 'TIDAK LULUS' END AS `ket` FROM `hitungnilai` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_mahasiswa`
--
ALTER TABLE `t_mahasiswa`
  ADD PRIMARY KEY (`nim`);

--
-- Indexes for table `t_mata_kuliah`
--
ALTER TABLE `t_mata_kuliah`
  ADD PRIMARY KEY (`kd_mk`);

--
-- Indexes for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD PRIMARY KEY (`kd_nilai`),
  ADD KEY `nim` (`nim`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_nilai`
--
ALTER TABLE `t_nilai`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD CONSTRAINT `t_nilai_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_nilai_ibfk_2` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
