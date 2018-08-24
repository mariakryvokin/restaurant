-- MySQL Script generated by MySQL Workbench
-- Tue Aug 21 14:10:57 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema restaurant
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema restaurant
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8 ;
USE `restaurant` ;

-- -----------------------------------------------------
-- Table `restaurant`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` VARCHAR(15) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `name_ua` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`dish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`dish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `name_ua` VARCHAR(45) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dish_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_dish_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `restaurant`.`category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(13,2) NOT NULL,
  `date_time` TIMESTAMP(2) NOT NULL,
  `status` VARCHAR(45) NOT NULL DEFAULT 'notConfirmed',
  `user_id` INT NOT NULL,
  `admin_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_order_user1_idx` (`user_id` ASC),
  INDEX `fk_order_user2_idx` (`admin_id` ASC),
  CONSTRAINT `fk_order_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `restaurant`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_user2`
    FOREIGN KEY (`admin_id`)
    REFERENCES `restaurant`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`check`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`check` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL DEFAULT 'notPaid',
  `date_time` TIMESTAMP(2) NOT NULL,
  `sum` DECIMAL(13,2) NOT NULL,
  `order_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_check_order1_idx` (`order_id` ASC),
  INDEX `fk_check_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_check_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `restaurant`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_check_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `restaurant`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`order_has_dish`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurant`.`order_has_dish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` INT NULL DEFAULT 1,
  `dish_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_dish_has_order_dish1_idx` (`dish_id` ASC),
  INDEX `fk_order_has_dish_order1_idx` (`order_id` ASC),
  CONSTRAINT `fk_dish_has_order_dish1`
    FOREIGN KEY (`dish_id`)
    REFERENCES `restaurant`.`dish` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_has_dish_order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `restaurant`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;