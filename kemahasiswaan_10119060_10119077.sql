-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2021 at 03:03 PM
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
  `nilai_uts` double NOT NULL,
  `nilai_uas` double NOT NULL,
  `nilai_absen` double NOT NULL,
  `nilai_tugas` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `nilai_akhir` int(11) NOT NULL,
  `indeks` char(5) NOT NULL,
  `ket` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nilai`, `nim`, `kd_mk`, `absensi`, `tugas1`, `tugas2`, `tugas3`, `nilai_uts`, `nilai_uas`, `nilai_absen`, `nilai_tugas`, `uts`, `uas`, `nilai_akhir`, `indeks`, `ket`) VALUES
(3, '10105122', 'IF34348', 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 'E', 'Tidak Lulus'),
(4, '10105121', 'IF37325P', 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 'A', 'Lulus'),
(5, '10119060', 'IF99192', 0, 0, 0, 0, 0, 0, 90, 0, 0, 0, 0, 'A', 'Lulus'),
(6, '10105121', 'IF34348', 0, 0, 0, 0, 0, 0, 70, 0, 0, 0, 0, 'B', 'lulus'),
(7, '10105122', 'IF235359', 0, 0, 0, 0, 0, 0, 40, 0, 0, 0, 0, 'E', 'Tidak Lulus'),
(8, '10105122', 'IF99192', 0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 'A', 'Lulus'),
(9, '10119077', 'IF99191', 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 'C', 'LULUS');

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
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
