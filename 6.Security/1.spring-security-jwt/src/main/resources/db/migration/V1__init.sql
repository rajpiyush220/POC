
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


