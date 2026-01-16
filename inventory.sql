-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 09, 2026 at 10:59 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employees_id` int NOT NULL,
  `name_employees` text NOT NULL,
  `password` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employees_id`, `name_employees`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'Doni', '1001', 'STAFF'),
(3, 'Martin', '1002', 'STAFF'),
(4, 'Wildan', '1003', 'STAFF');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `items_id` int NOT NULL,
  `item_name` text NOT NULL,
  `date_in` text NOT NULL,
  `quantity` int NOT NULL,
  `safety_stock` int NOT NULL,
  `classification` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Rop` int NOT NULL,
  `demand` int NOT NULL,
  `lead_time` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`items_id`, `item_name`, `date_in`, `quantity`, `safety_stock`, `classification`, `Rop`, `demand`, `lead_time`) VALUES
(1, 'Kulit', '09/01/2026', 200, 20, 'A', 45, 5, 5),
(2, 'Kulit Sintetis', '09/01/2026', 250, 20, 'A', 70, 10, 5),
(3, 'Karet', '09/01/2026', 300, 30, 'A', 75, 15, 3),
(4, 'Busa Ortholite', '09/01/2026', 320, 25, 'A', 105, 20, 4),
(5, 'Kain Mesh', '09/01/2026', 250, 25, 'A', 65, 20, 2),
(6, 'Kain Kanvas', '09/01/2026', 250, 20, 'A', 50, 15, 2),
(7, 'Pelat Penguat', '09/01/2026', 250, 15, 'B', 75, 15, 4),
(8, 'Insole Board', '09/01/2026', 300, 25, 'B', 125, 20, 5),
(9, 'Midsole ', '09/01/2026', 300, 25, 'B', 125, 20, 5),
(10, 'Heel ', '09/01/2026', 250, 20, 'B', 80, 15, 4),
(11, 'Toe Puff ', '09/01/2026', 200, 15, 'B', 90, 15, 5),
(12, 'Tali Sepatu', '09/01/2026', 450, 30, 'C', 90, 30, 2),
(13, 'Benang Jahit', '09/01/2026', 300, 30, 'C', 90, 30, 2),
(14, 'Lem Sepatu', '09/01/2026', 250, 35, 'C', 75, 20, 2),
(15, 'Label', '09/01/2026', 250, 20, 'C', 60, 20, 2),
(16, 'Aksesoris', '09/01/2026', 300, 20, 'C', 40, 20, 1),
(17, 'Kemasan', '09/01/2026', 500, 40, 'C', 120, 40, 2);

-- --------------------------------------------------------

--
-- Table structure for table `items_out`
--

CREATE TABLE `items_out` (
  `item_out_id` int NOT NULL,
  `date_out` text NOT NULL,
  `quantity_out` int NOT NULL,
  `item_id` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Triggers `items_out`
--
DELIMITER $$
CREATE TRIGGER `Delete_trg_items_out` AFTER DELETE ON `items_out` FOR EACH ROW BEGIN
    UPDATE items
    SET quantity = quantity + OLD.quantity_out
    WHERE items_id = OLD.item_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `Update_trg_items_out_reduce_stock` AFTER UPDATE ON `items_out` FOR EACH ROW BEGIN
    UPDATE items
    SET quantity = quantity + (OLD.quantity_out - NEW.quantity_out)
    WHERE items_id = NEW.item_id;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `trg_items_out_reduce_stock` AFTER INSERT ON `items_out` FOR EACH ROW BEGIN
    UPDATE items
    SET quantity = quantity - NEW.quantity_out
    WHERE items_id = NEW.item_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `report_id` int NOT NULL,
  `item_id_report` int NOT NULL,
  `report_description` text NOT NULL,
  `report_date` text NOT NULL,
  `employees_id_report` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employees_id`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`items_id`);

--
-- Indexes for table `items_out`
--
ALTER TABLE `items_out`
  ADD PRIMARY KEY (`item_out_id`),
  ADD KEY `FK` (`item_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`report_id`),
  ADD KEY `FK_items` (`item_id_report`),
  ADD KEY `FK_employees` (`employees_id_report`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employees_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `items_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `items_out`
--
ALTER TABLE `items_out`
  MODIFY `item_out_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `report_id` int NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `items_out`
--
ALTER TABLE `items_out`
  ADD CONSTRAINT `FK` FOREIGN KEY (`item_id`) REFERENCES `items` (`items_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `report`
--
ALTER TABLE `report`
  ADD CONSTRAINT `FK_employees` FOREIGN KEY (`employees_id_report`) REFERENCES `employees` (`employees_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `FK_items` FOREIGN KEY (`item_id_report`) REFERENCES `items` (`items_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
