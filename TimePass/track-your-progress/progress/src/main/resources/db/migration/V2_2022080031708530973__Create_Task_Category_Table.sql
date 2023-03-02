CREATE TABLE IF NOT EXISTS `task_category` (
  `id` binary(16) NOT NULL,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `organization_id` binary(16) NOT NULL,
  `created` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `task_category_AK_name_organization_id` (`name`,`organization_id`)
);