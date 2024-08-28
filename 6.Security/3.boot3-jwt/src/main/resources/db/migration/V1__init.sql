
-- create table role
CREATE TABLE IF NOT EXISTS `role` (
  `id` binary(16) NOT NULL,
  `name` varchar(50) NOT NULL,
  `created` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_AK_name` (`name`)
);

-- insert role in role db
INSERT INTO `role` VALUES (uuid_to_bin(uuid()),'SUPER_ADMIN',current_timestamp,0);
INSERT INTO `role` VALUES (uuid_to_bin(uuid()),'ADMIN',current_timestamp,0);
INSERT INTO `role` VALUES (uuid_to_bin(uuid()),'USER',current_timestamp,0);

-- create table user
CREATE TABLE IF NOT EXISTS `user` (
    `id` binary(16) NOT NULL,
    `username` varchar(50) NOT NULL,
    `email` varchar(60) DEFAULT NULL,
    `password` varchar(255) NOT NULL,
    `contact_no` varchar(30) NOT NULL,
    `created` datetime(6) NOT NULL,
    `updated` datetime(6) NOT NULL,
    `version` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_AK_email` (`email`),
    UNIQUE KEY `user_AK_contact_no` (`contact_no`)
);

-- create table role_users
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


