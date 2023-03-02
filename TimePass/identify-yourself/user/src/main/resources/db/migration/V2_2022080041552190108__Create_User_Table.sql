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