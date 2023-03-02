CREATE TABLE `task` (
  `id` binary(16) NOT NULL,
  `creator_id` binary(16) NOT NULL,
  `category_id` binary(16) NOT NULL,
  `title` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `start_date` datetime(6) NOT NULL,
  `end_date` datetime(6) NOT NULL,
  `is_strict_deadline` boolean NOT NULL DEFAULT '0',
  `complexity` varchar(50) NOT NULL,
  `expected_hour` decimal(6,2) NOT NULL DEFAULT '1.00',
  `created` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  `updated` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_AK_creator_id_category_id_title` (`creator_id`,`category_id`,`title`),
  KEY `FK_task_category_idx` (`category_id`),
  CONSTRAINT `FK_category` FOREIGN KEY (`category_id`) REFERENCES `task_category` (`id`)
);