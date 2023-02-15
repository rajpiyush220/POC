
CREATE TABLE IF NOT EXISTS `logs` (
    `id` binary(16) NOT NULL,
    `created` datetime(6) NOT NULL,
    `updated` datetime(6) NOT NULL,
    `version` bigint NOT NULL,
    `log_tes` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
    );