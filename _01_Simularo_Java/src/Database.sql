-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema tienda
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tienda` DEFAULT CHARACTER SET utf8mb4 ;
USE `tienda` ;

-- -----------------------------------------------------
-- Table `tienda`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`cliente` (
  `id_cliente` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tienda`.`tienda`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`tienda` (
  `id_tienda` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `ubicacion` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_tienda`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tienda`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`producto` (
  `id_producto` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `precio` DECIMAL(10,2) NOT NULL,
  `id_tienda` INT(11) NULL DEFAULT NULL,
  `stock` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  INDEX `fk_tienda` (`id_tienda` ASC) VISIBLE,
  CONSTRAINT `fk_tienda`
    FOREIGN KEY (`id_tienda`)
    REFERENCES `tienda`.`tienda` (`id_tienda`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `tienda`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tienda`.`compra` (
  `id_compra` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha_compra` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP(),
  `cantidad` INT(11) NULL DEFAULT NULL,
  `id_cliente` INT(11) NULL DEFAULT NULL,
  `id_producto` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id_compra`),
  INDEX `fk_cliente` (`id_cliente` ASC) VISIBLE,
  INDEX `fk_producto` (`id_producto` ASC) VISIBLE,
  CONSTRAINT `fk_cliente`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `tienda`.`cliente` (`id_cliente`),
  CONSTRAINT `fk_producto`
    FOREIGN KEY (`id_producto`)
    REFERENCES `tienda`.`producto` (`id_producto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
