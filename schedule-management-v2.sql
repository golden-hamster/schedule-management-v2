CREATE DATABASE schedule;

USE schedule;

CREATE TABLE `user` (
                        `user_id` BIGINT NOT NULL AUTO_INCREMENT,
                        `created_at` DATETIME(6),
                        `modified_at` DATETIME(6),
                        `email` VARCHAR(255),
                        `password` VARCHAR(255),
                        `username` VARCHAR(255),
                        `role` ENUM('ADMIN', 'USER'),
                        PRIMARY KEY (`user_id`)
);

CREATE TABLE `schedule` (
                            `schedule_id` BIGINT NOT NULL AUTO_INCREMENT,
                            `created_at` DATETIME(6),
                            `modified_at` DATETIME(6),
                            `user_id` BIGINT,
                            `content` VARCHAR(255),
                            `title` VARCHAR(255),
                            `weather` VARCHAR(255),
                            PRIMARY KEY (`schedule_id`),
                            FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `comment` (
                           `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
                           `created_at` DATETIME(6),
                           `modified_at` DATETIME(6),
                           `schedule_id` BIGINT,
                           `user_id` BIGINT,
                           `content` VARCHAR(255),
                           PRIMARY KEY (`comment_id`),
                           FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`schedule_id`),
                           FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
);

CREATE TABLE `schedule_user` (
                                 `schedule_user_id` BIGINT NOT NULL AUTO_INCREMENT,
                                 `schedule_schedule_id` BIGINT,
                                 `user_user_id` BIGINT,
                                 PRIMARY KEY (`schedule_user_id`),
                                 FOREIGN KEY (`schedule_schedule_id`) REFERENCES `schedule` (`schedule_id`),
                                 FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`)
);
