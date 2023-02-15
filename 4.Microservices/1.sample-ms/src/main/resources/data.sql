DROP TABLE IF EXISTS example;

CREATE TABLE `example` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `title` varchar(100) NOT NULL,
  `description` varchar(100) NOT NULL,
  `created` date DEFAULT NULL
);

