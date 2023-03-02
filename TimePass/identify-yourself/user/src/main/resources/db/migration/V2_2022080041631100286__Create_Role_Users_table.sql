
CREATE TABLE IF NOT EXISTS `role_users` (
  `id` binary(16) NOT NULL,
  `roles_id` binary(16) NOT NULL,
  `users_id` binary(16) NOT NULL,
  `created` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_users_AK_roles_id_users_id` (`roles_id`,`users_id`),
  CONSTRAINT `role_users_user_FK_users_id` FOREIGN KEY (`users_id`) REFERENCES `user` (`id`),
  CONSTRAINT `role_users_role_FK_roles_id` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
);