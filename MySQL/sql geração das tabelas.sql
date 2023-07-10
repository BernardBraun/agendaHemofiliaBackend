-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema app_hemofilia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema app_hemofilia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `app_hemofilia` DEFAULT CHARACTER SET utf8 ;
USE `app_hemofilia` ;

-- -----------------------------------------------------
-- Table `app_hemofilia`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`state` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `uf` CHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`city` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `state_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_city_state1_idx` (`state_id` ASC) VISIBLE,
  CONSTRAINT `fk_city_state1`
    FOREIGN KEY (`state_id`)
    REFERENCES `app_hemofilia`.`state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`hemocenter`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`hemocenter` (
  `id` INT NOT NULL,
  `hemocenter_name` VARCHAR(50) NOT NULL,
  `hemocenter_phone` VARCHAR(12) NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_hemocenter_city1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_hemocenter_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `app_hemofilia`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `complete_name` VARCHAR(150) NOT NULL,
  `birth_date` DATE NOT NULL,
  `height` FLOAT NOT NULL,
  `weight` FLOAT NULL,
  `hemophilia_type` CHAR(1) NOT NULL,
  `hemophilia_situation` VARCHAR(9) NOT NULL,
  `infusion_days` INT NOT NULL,
  `cellphone` VARCHAR(11) NOT NULL,
  `email` VARCHAR(80) NOT NULL,
  `father_name` VARCHAR(150) NOT NULL,
  `mother_name` VARCHAR(150) NOT NULL,
  `inhibitor` INT(1) NOT NULL,
  `hemocenter_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idperson_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  INDEX `fk_person_hemocenter1_idx` (`hemocenter_id` ASC) VISIBLE,
  CONSTRAINT `fk_person_hemocenter1`
    FOREIGN KEY (`hemocenter_id`)
    REFERENCES `app_hemofilia`.`hemocenter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `strees_name` VARCHAR(200) NOT NULL,
  `district` VARCHAR(80) NOT NULL,
  `postal_code` VARCHAR(9) NOT NULL,
  `person_id` INT NOT NULL,
  `city_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_person1_idx` (`person_id` ASC) VISIBLE,
  INDEX `fk_address_city1_idx` (`city_id` ASC) VISIBLE,
  CONSTRAINT `fk_address_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `app_hemofilia`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_address_city1`
    FOREIGN KEY (`city_id`)
    REFERENCES `app_hemofilia`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`login` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(80) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_login_person1_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_login_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `app_hemofilia`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`diary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`diary` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `infusion_date` DATETIME NOT NULL,
  `reason` INT(1) NOT NULL COMMENT '{ key: \'1\', value: \'Profilaxia\' },\n{ key: \'2\', value: \'Emergência\' },\n{ key: \'3\', value: \'Imunotolerância\' },\n{ key: \'4\', value: \'Cirurgia\' }',
  `bleed_type_local` INT(1) NOT NULL COMMENT '{ key: \'1\', value: \'Hermatrose\' },\n{ key: \'2\', value: \'Ferimento / Trauma\' },\n{ key: \'3\', value: \'Local\' },\n{ key: \'4\', value: \'Outros (detalhar na observação)\' }',
  `treatment` INT(1) NOT NULL COMMENT 'vai depender do fator utilizado (será feito enum com todos os tipos de tratamento)',
  `observation` TEXT NOT NULL,
  `person_id` INT NOT NULL,
  `hemocenter_id` INT NULL COMMENT 'quando o campo estiver nulo, é por que foi feito no hemocentro pai (o principal no cadastro)',
  PRIMARY KEY (`id`),
  INDEX `fk_diary_person1_idx` (`person_id` ASC) VISIBLE,
  INDEX `fk_diary_hemocenter1_idx` (`hemocenter_id` ASC) VISIBLE,
  CONSTRAINT `fk_diary_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `app_hemofilia`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_diary_hemocenter1`
    FOREIGN KEY (`hemocenter_id`)
    REFERENCES `app_hemofilia`.`hemocenter` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `app_hemofilia`.`bleed_inform`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `app_hemofilia`.`bleed_inform` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bleed_date` DATETIME NOT NULL,
  `bleed_local` VARCHAR(80) NOT NULL,
  `bleed_treatment` VARCHAR(255) NOT NULL,
  `observation` VARCHAR(255) NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bleed_inform_person1_idx` (`person_id` ASC) VISIBLE,
  CONSTRAINT `fk_bleed_inform_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `app_hemofilia`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
