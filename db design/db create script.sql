-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema streamline_pos_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `streamline_pos_db` ;

-- -----------------------------------------------------
-- Schema streamline_pos_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `streamline_pos_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `streamline_pos_db` ;

-- -----------------------------------------------------
-- Table `streamline_pos_db`.`supplier`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`supplier` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`supplier` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`purchase_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`purchase_order` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`purchase_order` (
  `Id` BIGINT(14) NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `purchased_amount` DOUBLE NOT NULL,
  `description` VARCHAR(256) NULL,
  `type` VARCHAR(45) NULL,
  `special_id_number` VARCHAR(45) NULL,
  `supplier_id` INT NULL,
  `purchase_order_insert_date` DATETIME NULL,
  `purchase_order_update_date` DATETIME NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_purchase_order_supplier1`
    FOREIGN KEY (`supplier_id`)
    REFERENCES `streamline_pos_db`.`supplier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `Id_UNIQUE` ON `streamline_pos_db`.`purchase_order` (`Id` ASC);

CREATE INDEX `fk_purchase_order_supplier1_idx` ON `streamline_pos_db`.`purchase_order` (`supplier_id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`categories`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`categories` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`categories` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`category_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`products`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`products` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `purchase_price` DOUBLE NULL,
  `sale_price` DOUBLE NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `quantity` INT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_products_categories1`
    FOREIGN KEY (`category_id`)
    REFERENCES `streamline_pos_db`.`categories` (`category_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_products_categories1_idx` ON `streamline_pos_db`.`products` (`category_id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`po_lines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`po_lines` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`po_lines` (
  `id` BIGINT(14) NOT NULL AUTO_INCREMENT,
  `quantity` INT NULL,
  `unit_price` DOUBLE NULL,
  `purchase_order_id` BIGINT(14) NOT NULL,
  `products_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Stock_Lines_Stock1`
    FOREIGN KEY (`purchase_order_id`)
    REFERENCES `streamline_pos_db`.`purchase_order` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_po_lines_items1`
    FOREIGN KEY (`products_id`)
    REFERENCES `streamline_pos_db`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_Stock_Lines_Stock1_idx` ON `streamline_pos_db`.`po_lines` (`purchase_order_id` ASC);

CREATE INDEX `fk_po_lines_items1_idx` ON `streamline_pos_db`.`po_lines` (`products_id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`customer` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`customer` (
  `id` BIGINT(14) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(256) NULL,
  `phone` VARCHAR(45) NULL,
  `cell` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`sales_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`sales_order` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`sales_order` (
  `Id` BIGINT(14) NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NULL,
  `total_amount` DOUBLE NULL,
  `customer_id` BIGINT(14) NOT NULL,
  `order_insert_date` DATETIME NULL,
  `order_update_date` DATETIME NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_sales_order_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `streamline_pos_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `Id_UNIQUE` ON `streamline_pos_db`.`sales_order` (`Id` ASC);

CREATE INDEX `fk_sales_order_customer1_idx` ON `streamline_pos_db`.`sales_order` (`customer_id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`order_lines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`order_lines` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`order_lines` (
  `id` BIGINT(14) NOT NULL AUTO_INCREMENT,
  `quantity` INT NULL,
  `unit_price` DOUBLE NULL,
  `total_price` DOUBLE NULL,
  `products_id` INT NOT NULL,
  `sales_order_Id` BIGINT(14) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order_lines_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `streamline_pos_db`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_lines_sales_order1`
    FOREIGN KEY (`sales_order_Id`)
    REFERENCES `streamline_pos_db`.`sales_order` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `streamline_pos_db`.`order_lines` (`id` ASC);

CREATE INDEX `fk_order_lines_products1_idx` ON `streamline_pos_db`.`order_lines` (`products_id` ASC);

CREATE INDEX `fk_order_lines_sales_order1_idx` ON `streamline_pos_db`.`order_lines` (`sales_order_Id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`payment` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`payment` (
  `id` BIGINT(14) NOT NULL,
  `sales_order_Id` BIGINT(14) NOT NULL,
  `insert_date` DATETIME NULL,
  `total_received_amount` DOUBLE NULL,
  `update_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payment_sales_order1`
    FOREIGN KEY (`sales_order_Id`)
    REFERENCES `streamline_pos_db`.`sales_order` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payment_sales_order1_idx` ON `streamline_pos_db`.`payment` (`sales_order_Id` ASC);


-- -----------------------------------------------------
-- Table `streamline_pos_db`.`payment_lines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `streamline_pos_db`.`payment_lines` ;

CREATE TABLE IF NOT EXISTS `streamline_pos_db`.`payment_lines` (
  `id` BIGINT(14) NOT NULL,
  `check_no` VARCHAR(45) NULL,
  `payment_amount` DOUBLE NULL,
  `received_amount` DOUBLE NULL,
  `date` DATETIME NOT NULL,
  `payment_id` BIGINT(14) NOT NULL,
  `payment_mode` VARCHAR(45) NULL,
  `payment_line_date` DATETIME NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payment_lines_payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `streamline_pos_db`.`payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payment_lines_payment1_idx` ON `streamline_pos_db`.`payment_lines` (`payment_id` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
