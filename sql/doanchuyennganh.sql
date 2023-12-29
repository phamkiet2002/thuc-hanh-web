-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 20, 2023 at 02:22 PM
-- Server version: 5.7.40
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `doanchuyennganh`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

DROP TABLE IF EXISTS `chitiethoadon`;
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `id_hoadon` bigint(20) NOT NULL,
  `id_laptop` bigint(20) NOT NULL,
  PRIMARY KEY (`id_hoadon`,`id_laptop`),
  KEY `FK952ttw59qod67xvu08mqmbulb` (`id_laptop`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`price`, `quantity`, `id_hoadon`, `id_laptop`) VALUES
(25490000, 1, 15, 51),
(27490000, 1, 15, 52),
(47990000, 1, 16, 56);

-- --------------------------------------------------------

--
-- Table structure for table `danhgia`
--

DROP TABLE IF EXISTS `danhgia`;
CREATE TABLE IF NOT EXISTS `danhgia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_laptop` bigint(20) DEFAULT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `ngaydanhgia` datetime(6) DEFAULT NULL,
  `noidung` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbrc7k9c9lmwi35vn3l99yggad` (`id_laptop`),
  KEY `FKbltj6xuakh3eb1f1bm79yw0k5` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
CREATE TABLE IF NOT EXISTS `danhmuc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `danhmuc`
--

INSERT INTO `danhmuc` (`id`, `name`) VALUES
(12, 'gamming'),
(14, 'Tai nghe'),
(16, 'CPU'),
(18, 'văn phòng'),
(23, 'Màn hình'),
(24, 'Bàn phím'),
(25, 'RAM'),
(26, 'VGA'),
(27, 'MAIN'),
(28, 'CASE');

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
CREATE TABLE IF NOT EXISTS `hoadon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_user` bigint(20) DEFAULT NULL,
  `ngaydat` datetime(6) DEFAULT NULL,
  `ghichu` varchar(255) DEFAULT NULL,
  `custom_name` varchar(255) DEFAULT NULL,
  `diachi` varchar(255) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `status` enum('DELETED','CONFIRMED','UNCONFIRMED') DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagf464jk4re83xp7freow7p0c` (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`id`, `id_user`, `ngaydat`, `ghichu`, `custom_name`, `diachi`, `sdt`, `status`) VALUES
(15, 4, '2023-12-19 08:23:05.878000', '', '', '', '', 'CONFIRMED'),
(16, 3, '2023-12-19 08:24:56.753000', '', '', '', '', 'UNCONFIRMED');

-- --------------------------------------------------------

--
-- Table structure for table `laptop`
--

DROP TABLE IF EXISTS `laptop`;
CREATE TABLE IF NOT EXISTS `laptop` (
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_danhmuc` bigint(20) DEFAULT NULL,
  `id_ncc` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjhlq27eyc4fk53tt3hxgwwg1u` (`id_danhmuc`),
  KEY `FKapm0x6c68dqbhwy6ny0oaiwgx` (`id_ncc`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `laptop`
--

INSERT INTO `laptop` (`price`, `quantity`, `id`, `id_danhmuc`, `id_ncc`, `description`, `image`, `name`, `type`) VALUES
(25490000, 9, 51, 12, 8, 'CPU:	Intel Core i5-12450H 3.3GHz up to 4.4GHz 12MB Ram:	8GB DDR5 4800MHz (2x SO-DIMM socket, up to 32GB SDRAM)', 'Laptop gaming Acer Nitro 5 Tiger AN515 58 5935.png', 'Laptop gaming Acer Nitro 5 Tiger AN515 58 5935', 'gamming'),
(27490000, 9, 52, 12, 8, 'CPU	Intel Core i5-13420H 3.4GHz up to 4.6GHz 12MB Smart Cache, 8 Cores 12 Threads Ram16GB (8x2) DDR5 5200MHz (2x SO-DIMM socket, up to 32GB SDRAM)', 'Laptop gaming Acer Nitro V ANV15 51 55CA.png', 'Laptop gaming Acer Nitro V ANV15 51 55CA', 'gamming'),
(53990000, 10, 53, 12, 8, 'CPU	Intel® Core™ i7-12700H (up to 4.7Ghz, 24MB cache) Ram16GB DDR5 4800Mhz (2x8GB) (2x SO-DIMM socket, up to 32GB SDRAM)', 'Laptop gaming Acer Predator Helios Neo PHN16 71 7460.png', 'Laptop gaming Acer Predator Helios 300 PH315 55', 'gamming'),
(47990000, 10, 56, 12, 4, 'CPU	Intel® Core™ i9-13980HX Processor 2.2 GHz (36M  Cache, up to 5.6 GHz, 24 cores: 8 P-cores and 16 E-cores) RAM	16GB (1x16GB) DDR5 4800MHz  (2x slots, up to 32GB)', 'Laptop gaming ASUS ROG Strix G16 G614JU N3135W.png', 'Laptop gaming ASUS ROG Strix G16 G614JV N4261W', 'gamming'),
(23990000, 10, 57, 12, 4, 'CPU	Intel® Core™ i7-12700H Processor 2.3 GHz (24M Cache, up to 4.7 GHz, 14 cores: 6 P-cores and 8 E-cores) RAM8GB DDR4 3200MHz (2x SO-DIMM socket, up to 32GB RAM)', 'Laptop gaming ASUS TUF Gaming A16 FA617NS N3486W Advantage Edition.png', 'Laptop gaming ASUS TUF Gaming F15 FX507ZC4 HN099W', 'gamming'),
(22590000, 10, 58, 12, 9, 'CPU	Intel Core i5-11400H 2.7GHz up to 4.5GHz 12MB RAM	8GB (8x1) DDR4 3200MHz (2x SO-DIMM socket, up to 32GB SDRAM)', 'Laptop Gaming Dell G15 5511 70266676.png', 'Laptop Gaming Dell G15 5511 70266676', 'gamming'),
(44990000, 10, 59, 12, 9, 'CPU	Intel Core i7-11800H 2.3GHz up to 4.6GHz 24MB RAM	32GB (16x2) DDR4 3200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'Laptop gaming Dell Alienware M15 R6 P109F001CBL.png', 'Laptop gaming Dell Alienware M15 R6 ', 'gamming'),
(17490000, 10, 64, 12, 9, 'CPU	Intel Core i5-1135G7 2.4GHz up to 4.2GHz 8MB RAM8GB (8x1) DDR4 3200MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'Laptop Dell Vostro 3510 7T2YC2.png', 'Laptop Dell Vostro 3510 7T2YC2 178', 'van phong'),
(16490000, 10, 69, 18, 9, 'CPU	Intel(R) Core(TM) i5-1335U Processor, 10 Cores 12 Threads (12MB Cache, up to 4.4 GHz) RAM	1 x 8GB DDR4 2666MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'Laptop Dell Inspiron 15 N3530 I3U085W11BLU.png', 'Laptop Dell Inspiron 15 3530 i5U085W11BLU', 'van phong'),
(15990000, 10, 70, 18, 4, 'CPU	AMD Ryzen™ 7 7730U Mobile Processor 2.0GHz (8-core/16-thread, 16MB cache, up to 4.5 GHz max boost) RAM	16GB DDR4 (8GB Onboard + 8GB Sodimm) ', 'Laptop ASUS Vivobook 16 M1605YA MB303W.png', 'Laptop ASUS Vivobook 16 M1605YA MB303W', 'van phong'),
(19590000, 10, 71, 18, 10, 'CPU	Intel Core i5-1135G7 2.4GHz up to 4.2GHz 8MB RAM	8GB (4GBx2) DDR4 3200MHz (2x SO-DIMM socket, up to 16GB SDRAM)', 'aptop-hp-pavilion-15-Laptop HP Pavilion 15 EG0506TX 46M05PA.png', 'Laptop HP Pavilion 15 EG0506TX 46M05PA', 'van phong'),
(15490000, 10, 72, 18, 8, 'CPU	Intel Core i5-12450H 3.3GHz up to 4.4GHz 12MB RAM	16GB (8x2) DDR4 3200MHz (2x SO-DIMM socket, up to 32GB SDRAM)', 'Laptop Acer Aspire 7 A715 76 53PJ.png', 'Laptop Acer Aspire 7 A715 76 53PJ', 'van phong'),
(18990000, 10, 73, 18, 8, 'CPU	Intel Core i5-13420H up to 4.600 GHz, 8 Cores 12 Threads, 12 MB Intel® Smart Cache RAM	8GB DDR4 3200MHz (2 khe nâng cấp tối đa 32GB Ram)', 'Laptop Acer Aspire 5 A514 56P 55K5.png', 'Laptop Acer Aspire 5 A515 58GM 59LJ', 'van phong'),
(37990000, 10, 74, 12, 2, 'CPU	Intel Core i7-13620H (3.6GHz~4.9GHz) 10 Cores 16 Threads RAM	16GB (2 x 8GB) DDR5 5600MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'Laptop gaming MSI Katana 15 B13VGK 1211VN.png', 'Laptop gaming MSI Katana 15 B13VGK 1211VN', 'gamming'),
(74990000, 10, 75, 12, 2, 'CPU	Intel Core i9-13900H 4.1GHz up to 5.4GHz, 14 Cores 20 Threads ,24MB Cache RAM	32GB (16x2) DDR5 5200MHz (2x SO-DIMM socket, up to 64GB SDRAM)', 'Laptop Gaming MSI Stealth 16 Mercedes AMG A13VG 289VN.png', 'Laptop Gaming MSI Stealth 16 Mercedes AMG A13VG 289VN', 'gamming'),
(26990000, 10, 76, 12, 2, 'CPU	AMD Ryzen 7-7735HS 3.20GHz upto 4.75GHz, 8 cores 16 threads, 20MB Cache RAM	16GB (2 x 8GB) DDR5 4800MHz (2 slots, Up to 64GB) Ổ cứng	512GB NVMe PCIe Gen 4 SSD (Còn trống 1 slot M.2 NVMe)', 'Laptop gaming MSI Bravo 15 C7VFK 275VN.png', 'Laptop gaming MSI Bravo 15 C7VFK 275VN', 'gamming'),
(9190000, 10, 77, 18, 2, 'CPU  Intel Core i3-1115G4 (up to 4.1Ghz, 6MB) RAM  8GB DDR4 3200Mhz Onboard (Không nâng cấp)  Ổ cứng  512GB NVMe PCIe Gen 3x4 SSD (1 Slot)', 'Laptop MSI Modern 14 C11M 011VN.png', 'Laptop MSI Modern 14 C11M 011VN', 'van phong'),
(13990000, 10, 78, 18, 2, 'CPU  Intel Core i5-1335U 1.3GHz up to 4.6GHz 12MB RAM  8GB Onboard DDR4 3200MHz Ổ cứng  512GB NVMe PCIe Gen 3x4 SSD (1 Slot)', 'Laptop MSI Modern 14 C13M 610VN.png', 'Laptop MSI Modern 14 C13M 610VN', 'van phong'),
(29990000, 10, 79, 12, 10, 'CPU	Intel Core i5-13450HX (3.4GHz up to 4.6GHz, 20MB Cache), 10 Cores 16 Threads RAM	16GB (8 x 2) DDR5 4800MHz (2x SO-DIMM socket, up to 32GB SDRAM) Ổ cứng	512GB M.2 NVMe™ PCIe® 4.0 SSD', 'Laptop gaming HP Victus 16 r0128T 8C5N3PA.png', 'Laptop gaming HP Victus 16 r0128T 8C5N3PA', 'gamming'),
(1490000, 10, 81, 28, 11, 'Kích thước473 x 210 x 486mm(DWH) Chuẩn	Mid tower Chất liệuSteel + Plastic', 'AntecAX20Elite.png', 'Vỏ máy tính Antec AX90 (4 quạt ARGB)', 'CPU'),
(2290000, 10, 82, 14, 12, 'Hãng sản xuất: Logitech  Tình trạng: Mới Bảo hành: 24 Tháng Màu sắc: Black', 'Tai nghe Logitech G733 LIGHTSPEED Wireless Black.png', 'Tai nghe Logitech G733 LIGHTSPEED Wireless Black', 'Tai nghe'),
(3990000, 10, 83, 24, 4, 'Thương hiệu:	Asus Model:	ROG Falchion Màu sắc:	Đen ', 'Bàn phím cơ Asus ROG Azoth.png', 'Bàn phím Asus ROG Falchion Blue Switch', 'gamming'),
(8490000, 10, 84, 27, 4, 'CPU	Socket AM5 dành cho Bộ xử lý AMD Ryzen 7000 Series Chipset	AMD X670', 'Bo mạch chủ ASUS ROG Strix Z790-E GAMING WIFI II DDR5.png', 'ASUS ROG CROSSHAIR X670E EXTREME (Socket AM5)', 'gamming'),
(8290000, 10, 85, 16, 13, 'Socket	FCLGA1700 Dòng CPU	Core i5 CPU	Intel® Core® i5-14600KF (Raptor Lake)', 'Bộ vi xử lý Intel Core i7 14700K.png', 'Bộ vi xử lý Intel Core i5 14600KF / Turbo up to 5.3GHz /  ', 'CPU'),
(6990000, 10, 86, 25, 2, 'Fan đi kèm	Không Series bộ nhớ	DOMINATOR RGB DDR5 Loại bộ nhớ	DDR5', 'Ram Corsair Vengeance RGB 32GB (2x16GB) 5200 DDR5 White (CMH32GX5M2B5200C40W).png', 'RAM Corsair Dominator Platinum 64GB (2x32GB) ', 'RAM'),
(4190000, 10, 87, 12, 8, 'Kích thước	27 inch Độ phân giải	Full HD (1920 x 1080) Tần số quét	180Hz', 'Màn hình ACER KG270 M5 27 IPS 180Hz chuyên game.png', 'Màn hình ACER KG270 M5 27\" IPS 180Hz chuyên game', 'Man hình'),
(5990000, 10, 88, 26, 2, 'Model	MSI GeForce RTX 4090 GAMING TRIO 24G Nhân đồ họa  NVIDIA GeForce RTX 4090  Giao thức kết nối	PCI Express® Gen 4', 'Thiết bị tản nhiệt MSI MAG CORELIQUID E240 BLACK.png', 'Card màn hình MSI GeForce RTX 4090 GAMING TRIO 24G', 'gamming');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

DROP TABLE IF EXISTS `nhacungcap`;
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`id`, `email`, `name`, `sdt`) VALUES
(2, 'MSI@gmail.com', 'MSI', '0344360653'),
(4, 'ASUS@gmail.com', 'ASUS', '0968760911'),
(8, 'Acer@gmail.com', 'Acer', '0795678778'),
(9, 'dell@gmail.com', 'Dell', '0968760911'),
(10, 'hp@gmail.com', 'Hp', '0968760911'),
(11, 'kietkietpham1357@gmail.com', 'Antec', '0968760911'),
(12, 'Logitech@gmail.com', 'Logitech ', '0968621174'),
(13, 'Intel@gmail.com', 'Intel', '0968760911'),
(14, 'Corsair@gmail.com', 'Corsair ', '0968760911');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_KHACHHANG');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `diachi` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `sdt` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `diachi`, `email`, `name`, `password`, `sdt`, `username`) VALUES
(1, 'Quận 8', 'admin@gmail.com', 'ADMIN', '$2a$10$KwZ/.V9BpZjiMfgHw4YhWufwBwsg53.l1ZRHm07e9sRhAEZs.iiAm', NULL, 'admin'),
(2, 'kiên giang', 'kietpham1357@gmail.com', 'Phạm Tuấn Kiệt', '$2a$10$QuDLub6a6hyO9to0GKsFl.JFmj.qkV.mnlS75M1h7bhQcwEq7KvZm', '', 'kietpham02'),
(3, '', 'hieu@gmail.com', 'hieu', '$2a$10$8rn1N6Ksy1KlmICZv/n5ke6BXaQe0jxH4Wt0Lxm5ktnShjqC0AXKC', '', 'hieu'),
(4, 'quan 8', 'kietkietpham13572222@gmail.com', 'kietpham', '$2a$10$U2ZHmGxeU5/RVt9lL3IUg.rWgomuqylpofi3LGHC0ohq0QBvGI2iS', '12321312', 'kietpham2');

-- --------------------------------------------------------

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `id_role` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  KEY `FK3jjx0t0dfqqkg4032k480n5lk` (`id_role`),
  KEY `FKl0ivkge86pln7h5i75t67pb82` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (`id_role`, `id_user`) VALUES
(1, 1),
(2, 2),
(2, 4),
(2, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `FK952ttw59qod67xvu08mqmbulb` FOREIGN KEY (`id_laptop`) REFERENCES `laptop` (`id`),
  ADD CONSTRAINT `FKl6het11vcn4pd2c579fyvxf1q` FOREIGN KEY (`id_hoadon`) REFERENCES `hoadon` (`id`);

--
-- Constraints for table `danhgia`
--
ALTER TABLE `danhgia`
  ADD CONSTRAINT `FKbltj6xuakh3eb1f1bm79yw0k5` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKbrc7k9c9lmwi35vn3l99yggad` FOREIGN KEY (`id_laptop`) REFERENCES `laptop` (`id`);

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FKagf464jk4re83xp7freow7p0c` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `laptop`
--
ALTER TABLE `laptop`
  ADD CONSTRAINT `FKapm0x6c68dqbhwy6ny0oaiwgx` FOREIGN KEY (`id_ncc`) REFERENCES `nhacungcap` (`id`),
  ADD CONSTRAINT `FKjhlq27eyc4fk53tt3hxgwwg1u` FOREIGN KEY (`id_danhmuc`) REFERENCES `danhmuc` (`id`);

--
-- Constraints for table `users_roles`
--
ALTER TABLE `users_roles`
  ADD CONSTRAINT `FK3jjx0t0dfqqkg4032k480n5lk` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  ADD CONSTRAINT `FKl0ivkge86pln7h5i75t67pb82` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
