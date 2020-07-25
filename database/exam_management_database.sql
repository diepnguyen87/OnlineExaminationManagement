-- --------------------------------------------------------
-- AUTHOR:			Diep Nguyen (PC18)
-- CREATED ON:		25 July 2020
-- --------------------------------------------------------


CREATE DATABASE `exam_management`;

USE `exam_management`;

CREATE TABLE `admin` (
	`admin_id` INT(11) NOT NULL AUTO_INCREMENT,
	`admin_email_address` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`admin_password` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`admin_id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=2
;


CREATE TABLE `instituation` (
	`instituation_id` INT(11) NOT NULL AUTO_INCREMENT,
	`insti_email_address` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`insti_password` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`instituation_id`) USING BTREE,
	UNIQUE INDEX `insti_email_address` (`insti_email_address`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=14
;


CREATE TABLE `user` (
	`user_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_email_address` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`user_password` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`user_id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `exam` (
	`exam_id` INT(11) NOT NULL AUTO_INCREMENT,
	`exam_title` VARCHAR(50) NOT NULL COLLATE 'utf8_general_ci',
	`exam_duration` INT(11) NOT NULL,
	`total_question` INT(20) NOT NULL,
	`exam_author` INT(11) NOT NULL,
	`created_on` DATETIME NOT NULL,
	PRIMARY KEY (`exam_id`) USING BTREE,
	INDEX `FK_exam_instituation` (`exam_author`) USING BTREE,
	CONSTRAINT `FK_exam_instituation` FOREIGN KEY (`exam_author`) REFERENCES `exam_management`.`instituation` (`instituation_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `question` (
	`question_id` INT(11) NOT NULL,
	`exam_id` INT(11) NOT NULL,
	`question_text` TEXT(65535) NOT NULL COLLATE 'utf8_general_ci',
	`question_mark` INT(2) NOT NULL,
	PRIMARY KEY (`question_id`) USING BTREE,
	INDEX `FK_question_exam` (`exam_id`) USING BTREE,
	CONSTRAINT `FK_question_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam_management`.`exam` (`exam_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `option` (
	`option_id` INT(11) NOT NULL,
	`question_id` INT(11) NOT NULL,
	`option_number` INT(2) NOT NULL,
	`option_text` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`option_id`) USING BTREE,
	INDEX `FK_option_question` (`question_id`) USING BTREE,
	CONSTRAINT `FK_option_question` FOREIGN KEY (`question_id`) REFERENCES `exam_management`.`question` (`question_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `question_answer` (
	`question_id` INT(11) NOT NULL,
	`option_id` INT(11) NOT NULL,
	INDEX `FK_question_answer_question` (`question_id`) USING BTREE,
	INDEX `FK_question_answer_option` (`option_id`) USING BTREE,
	CONSTRAINT `FK_question_answer_option` FOREIGN KEY (`option_id`) REFERENCES `exam_management`.`option` (`option_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_question_answer_question` FOREIGN KEY (`question_id`) REFERENCES `exam_management`.`question` (`question_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `score` (
	`user_id` INT(11) NULL DEFAULT NULL,
	`exam_id` INT(11) NULL DEFAULT NULL,
	`score` INT(11) NULL DEFAULT NULL,
	INDEX `FK__user` (`user_id`) USING BTREE,
	INDEX `FK__exam` (`exam_id`) USING BTREE,
	CONSTRAINT `FK__exam` FOREIGN KEY (`exam_id`) REFERENCES `exam_management`.`exam` (`exam_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK__user` FOREIGN KEY (`user_id`) REFERENCES `exam_management`.`user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `user_exam_question_answer` (
	`user_exam_question_answer_id` INT(11) NOT NULL,
	`user_id` INT(11) NOT NULL,
	`exam_id` INT(11) NOT NULL,
	`question_id` INT(11) NOT NULL,
	`user_answer_option` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`user_exam_question_answer_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_user` (`user_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_exam` (`exam_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_question` (`question_id`) USING BTREE,
	CONSTRAINT `FK_user_exam_question_answer_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam_management`.`exam` (`exam_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_user_exam_question_answer_question` FOREIGN KEY (`question_id`) REFERENCES `exam_management`.`question` (`question_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_user_exam_question_answer_user` FOREIGN KEY (`user_id`) REFERENCES `exam_management`.`user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;