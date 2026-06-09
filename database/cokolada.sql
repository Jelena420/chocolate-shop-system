/*!40101 SET NAMES utf8 */;
SET SQL_MODE='';

CREATE DATABASE IF NOT EXISTS `cokolada` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `cokolada`;

/* RESET */
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `StavkaPorudzbine`;
DROP TABLE IF EXISTS `Porudzbina`;
DROP TABLE IF EXISTS `Cokolada`;
DROP TABLE IF EXISTS `VrstaCokolade`;
DROP TABLE IF EXISTS `Kupac`;
DROP TABLE IF EXISTS `Grad`;
/* Administrator ostaje, ali ćemo ga kreirati za slučaj da ne postoji */
DROP TABLE IF EXISTS `Administrator`;
SET FOREIGN_KEY_CHECKS = 1;

/* ===== Administrator (ostaje isti) ===== */
CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(50) NOT NULL,
  `Prezime` VARCHAR(50) NOT NULL,
  `KorisnickoIme` VARCHAR(30) NOT NULL,
  `Lozinka` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Administrator` (AdministratorID, Ime, Prezime, KorisnickoIme, Lozinka) VALUES
(1, 'Jelena', 'Cavic', 'jeca', 'jeca'),
(2, 'Jovana', 'Petrovic', 'jp', 'jp'),
(3, 'Nikola', 'Ilic', 'ni', 'ni');

/* ===== Grad (NOVI PODACI) ===== */
CREATE TABLE `Grad` (
  `GradID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`GradID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Grad` (GradID, Naziv) VALUES
(1, 'Kragujevac'),
(2, 'Subotica'),
(3, 'Čačak');

/* ===== Kupac (NOVI PODACI) ===== */
CREATE TABLE `Kupac` (
  `KupacID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Telefon` VARCHAR(30) NOT NULL,
  `GradID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`KupacID`),
  CONSTRAINT `fk_grad_id` FOREIGN KEY (`GradID`) REFERENCES `Grad` (`GradID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Kupac` (KupacID, Ime, Prezime, Email, Telefon, GradID) VALUES
(1, 'Luka', 'Lukić', 'luka@gmail.com', '061111111', 1),
(2, 'Milica', 'Jovanović', 'milica@gmail.com', '062222222', 2),
(3, 'Jovan', 'Cvetković', 'jovan@gmail.com', '063333333', 3),
(4, 'Katarina', 'Pavlović', 'katarina@gmail.com', '064444444', 1),
(5, 'Nemanja', 'Stanković', 'nemanja@gmail.com', '065555555', 2);

/* ===== VrstaCokolade (umesto Marka) ===== */
CREATE TABLE `VrstaCokolade` (
  `VrstaCokoladeID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`VrstaCokoladeID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `VrstaCokolade` (VrstaCokoladeID, Naziv) VALUES
(1, 'Mlečna'),
(2, 'Crna'),
(3, 'Bela'),
(4, 'Sa lešnikom'),
(5, 'Sa voćem');

/* ===== Cokolada (umesto Ranac) ===== */
CREATE TABLE `Cokolada` (
  `CokoladaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(50) NOT NULL,
  `Opis` VARCHAR(300) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `VrstaCokoladeID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`CokoladaID`),
  CONSTRAINT `fk_vrsta_cok_id` FOREIGN KEY (`VrstaCokoladeID`) REFERENCES `VrstaCokolade` (`VrstaCokoladeID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

INSERT INTO `Cokolada` (CokoladaID, Naziv, Opis, Cena, VrstaCokoladeID) VALUES
(1, 'Mlečna Classic 100g', 'Klasična mlečna čokolada 100g', 220.00, 1),
(2, 'Mlečna sa keksom 100g', 'Mlečna čokolada sa komadićima keksa', 250.00, 1),
(3, 'Crna 70% kakao 100g', 'Intenzivna crna čokolada 70% kakao', 300.00, 2),
(4, 'Crna 85% kakao 100g', 'Gorka crna čokolada 85% kakao', 350.00, 2),
(5, 'Bela vanila 100g', 'Bela čokolada sa prirodnom vanilom', 260.00, 3),
(6, 'Mlečna sa lešnikom 150g', 'Mlečna čokolada sa celim lešnicima', 420.00, 4),
(7, 'Mlečna sa bademom 150g', 'Mlečna čokolada sa bademima', 450.00, 4),
(8, 'Voćna jagoda 120g', 'Mlečna čokolada sa jagodom', 380.00, 5),
(9, 'Crna Premium kolekcija 300g', 'Selektovana crna čokolada — premium', 1200.00, 2);

/* ===== Porudzbina ===== */
CREATE TABLE `Porudzbina` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumVreme` DATETIME NOT NULL,
  `DatumIsporuke` DATE NOT NULL,
  `UkupanIznos` DECIMAL(10,2) NOT NULL,
  `KupacID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`),
  CONSTRAINT `fk_kup_id`   FOREIGN KEY (`KupacID`) REFERENCES `Kupac` (`KupacID`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `Porudzbina` (PorudzbinaID, DatumVreme, DatumIsporuke, UkupanIznos, KupacID, AdministratorID) VALUES
(1, '2025-08-10 10:15:00', '2025-08-13', 1020.00, 2, 1),
(2, '2025-08-12 16:40:00', '2025-08-15', 2100.00, 4, 2),
(3, '2025-08-15 12:05:00', '2025-08-18', 1810.00, 1, 3);

/* ===== StavkaPorudzbine (referiše Cokolada) ===== */
CREATE TABLE `StavkaPorudzbine` (
  `PorudzbinaID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Kolicina` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Iznos` DECIMAL(10,2) NOT NULL,
  `CokoladaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`PorudzbinaID`,`Rb`),
  CONSTRAINT `fk_por_id`   FOREIGN KEY (`PorudzbinaID`) REFERENCES `Porudzbina` (`PorudzbinaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_cok_id`   FOREIGN KEY (`CokoladaID`) REFERENCES `Cokolada` (`CokoladaID`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;

/* Porudzbina 1 → total 1020.00 */
INSERT INTO `StavkaPorudzbine` (PorudzbinaID, Rb, Kolicina, Cena, Iznos, CokoladaID) VALUES
(1, 1, 2, 300.00, 600.00, 3),  -- 2x Crna 70%
(1, 2, 1, 420.00, 420.00, 6);  -- 1x Mlečna sa lešnikom

/* Porudzbina 2 → total 2100.00 */
INSERT INTO `StavkaPorudzbine` (PorudzbinaID, Rb, Kolicina, Cena, Iznos, CokoladaID) VALUES
(2, 1, 1, 1200.00, 1200.00, 9), -- 1x Crna Premium kolekcija
(2, 2, 2, 260.00,  520.00, 5),  -- 2x Bela vanila
(2, 3, 1, 380.00,  380.00, 8);  -- 1x Voćna jagoda

/* Porudzbina 3 → total 1810.00 */
INSERT INTO `StavkaPorudzbine` (PorudzbinaID, Rb, Kolicina, Cena, Iznos, CokoladaID) VALUES
(3, 1, 3, 220.00, 660.00, 1),  -- 3x Mlečna Classic
(3, 2, 2, 350.00, 700.00, 4),  -- 2x Crna 85%
(3, 3, 1, 450.00, 450.00, 7);  -- 1x Mlečna sa bademom
