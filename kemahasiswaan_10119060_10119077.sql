-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 04, 2021 at 05:56 PM
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
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `idbarang` varchar(10) NOT NULL,
  `namabarang` varchar(50) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`idbarang`, `namabarang`, `harga`) VALUES
('10111', 'Oksigen kecil', 120000),
('10112', 'Oksigen besar', 150000),
('10113', 'Okigen sedang', 140000);

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `idpegawai` varchar(10) NOT NULL,
  `namapegawai` varchar(50) NOT NULL,
  `kotasal` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`idpegawai`, `namapegawai`, `kotasal`) VALUES
('10119060', 'Megianto', 'Subang'),
('10119077', 'Ridhwan Anwar', 'Bandung');

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `nopesanan` int(11) NOT NULL,
  `idbarang` varchar(10) NOT NULL,
  `namapelanggan` varchar(50) NOT NULL,
  `idpegawai` varchar(10) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `totalharga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`nopesanan`, `idbarang`, `namapelanggan`, `idpegawai`, `jumlah`, `totalharga`) VALUES
(2043, '10113', 'gangga', '10119060', 10, 1400000),
(2044, '10113', 'Abdul', '10119077', 4, 560000),
(2045, '10112', 'ambar', '10119060', 2, 300000),
(2046, '10112', 'adis', '10119060', 2, 300000);

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
('10118099', 'Zafar Ashidiq', 'Bandung', '2021-12-27', 'Jl.Tubagus'),
('10119060', 'Megianto Adi Saputra', 'Bandung', '2001-05-19', 'Jl.skeloa'),
('10119066', 'Andar Wati ', 'Bandung', '1970-01-01', 'Jl.Letenan ukin'),
('10119077', 'Ridhwan Anwar Fauzan', 'Bandung', '2001-05-01', 'Jl.panjaitan'),
('10119088', 'ANdriano', 'Bandung', '2021-12-27', 'JL.Tubagus');

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
  `uas` double NOT NULL,
  `nilai_absen` double NOT NULL,
  `nilai_tugas` double NOT NULL,
  `nilai_uts` double NOT NULL,
  `nilai_uas` double NOT NULL,
  `nilai_akhir` int(11) NOT NULL,
  `indeks` char(5) NOT NULL,
  `ket` varchar(20) NOT NULL,
  `angkatan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_nilai`
--

INSERT INTO `t_nilai` (`kd_nilai`, `nim`, `kd_mk`, `absensi`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`, `nilai_absen`, `nilai_tugas`, `nilai_uts`, `nilai_uas`, `nilai_akhir`, `indeks`, `ket`, `angkatan`) VALUES
(42, '10119088', 'IF34348', 12, 100, 89, 90, 90, 90, 4.285714285714286, 23.25, 27, 36, 91, 'A', 'Lulus', '1970-01-01'),
(43, '10105121', 'IF289897', 14, 90, 90, 90, 90, 90, 5, 22.5, 27, 36, 91, 'A', 'Lulus', '2021-01-03');

-- --------------------------------------------------------

--
-- Table structure for table `t_pengguna`
--

CREATE TABLE `t_pengguna` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `nama_lengkap` varchar(60) NOT NULL,
  `email` varchar(50) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_pengguna`
--

INSERT INTO `t_pengguna` (`id`, `username`, `nama_lengkap`, `email`, `no_hp`, `password`) VALUES
(10119060, 'megiadis', 'megianto', 'megi@gmail.com', '8772', '63a70bfcf41d48dedba1be5b704161dc'),
(10119077, 'ridhwan', 'ridwan', 'ridwan@gmai.com', '976786855', '10119077'),
(10119096, 'zaelani', 'zaelani akbar', 'akbarzan@gmail.com', '089777644', 'zaelani'),
(10119097, 'abdul', 'Abdul Rasyid', 'ocid@gmail.com', '089997667', 'abdul'),
(10119098, 'kania', 'kania dewi', 'kania@gmail.com', '089778654', 'kania'),
(10119099, 'jajang', 'jajang ujang', 'jajang@gmail.com', '08776564', 'jajang'),
(10119100, 'maemunah', 'maemunah', 'maemun@gmail.com', '0987986875', 'maemunah'),
(10119101, 'jaelani', 'jaelani akbar', 'akbar@gmail.com', '08978687', 'jaelani'),
(10119102, 'admin', 'Admin', 'admin@gmail.com', '089079868', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `t_simulasi`
--

CREATE TABLE `t_simulasi` (
  `kd_nilai` int(11) NOT NULL,
  `kd_mk` varchar(8) NOT NULL,
  `pabsen` double NOT NULL,
  `ptugas` double NOT NULL,
  `puts` double NOT NULL,
  `puas` double NOT NULL,
  `absensi` double NOT NULL,
  `tugas1` double NOT NULL,
  `tugas2` double NOT NULL,
  `tugas3` double NOT NULL,
  `uts` double NOT NULL,
  `uas` double NOT NULL,
  `nilai_absen` double NOT NULL,
  `nilai_tugas` double NOT NULL,
  `nilai_uts` double NOT NULL,
  `nilai_uas` double NOT NULL,
  `nilai_akhir` int(11) NOT NULL,
  `indeks` char(5) NOT NULL,
  `ket` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_simulasi`
--

INSERT INTO `t_simulasi` (`kd_nilai`, `kd_mk`, `pabsen`, `ptugas`, `puts`, `puas`, `absensi`, `tugas1`, `tugas2`, `tugas3`, `uts`, `uas`, `nilai_absen`, `nilai_tugas`, `nilai_uts`, `nilai_uas`, `nilai_akhir`, `indeks`, `ket`) VALUES
(44, 'IF256544', 20, 30, 40, 19, 11, 100, 100, 100, 100, 100, 15.714285714285714, 30, 40, 19, 105, 'E', 'Tidak Lulus'),
(45, 'IF37325P', 10, 50, 20, 20, 14, 100, 100, 100, 100, 100, 10, 50, 20, 20, 100, 'A', 'Lulus'),
(46, 'IF235359', 20, 20, 30, 30, 13, 80, 85, 60, 60, 60, 18.571428571428573, 15, 18, 18, 70, 'B', 'Lulus'),
(47, 'IF99191', 40, 20, 25, 25, 13, 80, 90, 90, 40, 80, 37.142857142857146, 17.333333333333336, 10, 20, 84, 'A', 'Lulus');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`idbarang`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`idpegawai`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`nopesanan`),
  ADD KEY `idbarang` (`idbarang`),
  ADD KEY `idpegawai` (`idpegawai`);

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
-- Indexes for table `t_pengguna`
--
ALTER TABLE `t_pengguna`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_simulasi`
--
ALTER TABLE `t_simulasi`
  ADD PRIMARY KEY (`kd_nilai`),
  ADD KEY `kd_mk` (`kd_mk`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `nopesanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2047;

--
-- AUTO_INCREMENT for table `t_nilai`
--
ALTER TABLE `t_nilai`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `t_pengguna`
--
ALTER TABLE `t_pengguna`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10119103;

--
-- AUTO_INCREMENT for table `t_simulasi`
--
ALTER TABLE `t_simulasi`
  MODIFY `kd_nilai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`idbarang`) REFERENCES `barang` (`idbarang`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pesanan_ibfk_2` FOREIGN KEY (`idpegawai`) REFERENCES `pegawai` (`idpegawai`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t_nilai`
--
ALTER TABLE `t_nilai`
  ADD CONSTRAINT `t_nilai_ibfk_1` FOREIGN KEY (`nim`) REFERENCES `t_mahasiswa` (`nim`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `t_nilai_ibfk_2` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t_simulasi`
--
ALTER TABLE `t_simulasi`
  ADD CONSTRAINT `t_simulasi_ibfk_2` FOREIGN KEY (`kd_mk`) REFERENCES `t_mata_kuliah` (`kd_mk`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
