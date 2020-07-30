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
;

CREATE TABLE `instituation` (
	`instituation_id` INT(11) NOT NULL AUTO_INCREMENT,
	`insti_email_address` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`insti_password` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	PRIMARY KEY (`instituation_id`) USING BTREE,
	UNIQUE INDEX `insti_email_address` (`insti_email_address`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
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
	`exam_title` VARCHAR(255) NOT NULL COLLATE 'utf8_general_ci',
	`exam_duration` INT(11) NOT NULL,
	`total_question` INT(11) NOT NULL,
	`exam_instituation_author` INT(11) NULL DEFAULT NULL,
	`exam_admin_author` INT(11) NULL DEFAULT NULL,
	`created_on` DATETIME NOT NULL DEFAULT current_timestamp(),
	`is_draft` ENUM('Y','N') NOT NULL DEFAULT 'N' COLLATE 'utf8_general_ci',
	PRIMARY KEY (`exam_id`) USING BTREE,
	UNIQUE INDEX `exam_title` (`exam_title`) USING BTREE,
	INDEX `FK_exam_instituation` (`exam_instituation_author`) USING BTREE,
	INDEX `FK_exam_admin` (`exam_admin_author`) USING BTREE,
	CONSTRAINT `FK_exam_admin` FOREIGN KEY (`exam_admin_author`) REFERENCES `exam_management`.`admin` (`admin_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_exam_instituation` FOREIGN KEY (`exam_instituation_author`) REFERENCES `exam_management`.`instituation` (`instituation_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;



CREATE TABLE `question` (
	`question_id` INT(11) NOT NULL AUTO_INCREMENT,
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
	`option_id` INT(11) NOT NULL AUTO_INCREMENT,
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
	INDEX `FK_question_answer_option` (`option_id`) USING BTREE
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
	`user_exam_question_answer_id` INT(11) NOT NULL AUTO_INCREMENT,
	`user_id` INT(11) NOT NULL,
	`exam_id` INT(11) NOT NULL,
	`question_id` INT(11) NOT NULL,
	`user_answer_option` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`user_exam_question_answer_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_user` (`user_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_exam` (`exam_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_question` (`question_id`) USING BTREE,
	INDEX `FK_user_exam_question_answer_option` (`user_answer_option`) USING BTREE,
	CONSTRAINT `FK_user_exam_question_answer_exam` FOREIGN KEY (`exam_id`) REFERENCES `exam_management`.`exam` (`exam_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_user_exam_question_answer_option` FOREIGN KEY (`user_answer_option`) REFERENCES `exam_management`.`option` (`option_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_user_exam_question_answer_question` FOREIGN KEY (`question_id`) REFERENCES `exam_management`.`question` (`question_id`) ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT `FK_user_exam_question_answer_user` FOREIGN KEY (`user_id`) REFERENCES `exam_management`.`user` (`user_id`) ON UPDATE RESTRICT ON DELETE RESTRICT
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;



INSERT INTO `admin` (`admin_email_address`, `admin_password`) 
VALUES ('admin1@kits.edu', '123?Abcd');

INSERT INTO `exam` (`exam_title`, `exam_duration`, `total_question`, `exam_admin_author`)
VALUES ('Core Java Quiz', 1, 6, 1);

INSERT INTO `question` (`exam_id`, `question_text`, `question_mark`)
VALUES 
	(1, 'Which of the below are reserved keyword in Java?', 10),
	(1, 'What are the valid statements for static keyword in Java?', 20),
	(1, 'Select all the core concepts of OOPS.', 10),
	(1, 'Which of the following statements are true for inheritance in Java?', 25),
	(1, 'Which of the below are unchecked exceptions in java?', 15),
	(1, 'Which of the following statement(s) are true for java?', 20);
	
INSERT INTO `option` (`question_id`, `option_number`, `option_text`)
VALUES
	(1, 1, 'array'),
	(1, 2, 'goto'),
	(1, 3, 'null'),
	(1, 4, 'int'),
	
	(2, 1, 'We can have static block in a class.'),
	(2, 2, 'The static block in a class is executed every time an object of class is created.'),
	(2, 3, 'We can have static method implementations in interface.'),
	(2, 4, 'We can define static block inside a method.'),
	
	(3, 1, 'Abstraction'),
	(3, 2, 'Inheritance'),
	(3, 3, 'Interface'),
	(3, 4, 'Polymorphism'),
	(3, 5, 'Generics'),
	
	(4, 1, 'The extend keyword is used to extend a class in java.'),
	(4, 2, 'You can extend multiple classes in java.'),
	(4, 3, 'Private members of the superclass are accessible to the subclass.'),
	(4, 4, 'We cant extend Final classes in java.'),

	(5, 1, 'Runtime Exception'),
	(5, 2, 'ClassCast Exception'),
	(5, 3, 'NullPointer Exception'),
	(5, 4, 'IO Exception'),
	
	(6, 1, 'JVM is responsible for converting Byte code to the machine-specific code.'),
	(6, 2, 'We only need JRE to run java programs.'),
	(6, 3, 'JDK is required to compile java programs.'),
	(6, 4, 'JRE doesnt contain JVM');
	
	
INSERT INTO `question_answer` (`question_id`, `option_id`)
VALUES
	(1, 1),
	(2, 6),
	(3, 10),
	(4, 14),
	(5, 19),
	(6, 25);
	
	