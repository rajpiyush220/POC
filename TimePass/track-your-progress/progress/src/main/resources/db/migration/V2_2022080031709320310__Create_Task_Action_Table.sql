CREATE TABLE IF NOT EXISTS `task_action` (
  `id` binary(16) NOT NULL,
  `task_id` binary(16) NOT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `spent_hour` decimal(6,2) NOT NULL DEFAULT '1.00',
  `description` varchar(255) DEFAULT NULL,
  `created` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_task_id_idx` (`task_id`),
  CONSTRAINT `FK_task_id` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
);

