-- Add all initialization sql

CREATE TABLE IF NOT EXISTS `youtube_video_details` (
  `id` binary(16) NOT NULL,
  `channel_id` varchar(255) NOT NULL,
  `video_id` varchar(255),
  `duration` bigint NOT NULL,
  `publish_date` date NOT NULL,
  `is_shorts` boolean NOT NULL,
  `embedded_url` text NOT NULL,
  `created` datetime(6) NOT NULL,
  `updated` datetime(6) NOT NULL,
  `version` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `utd_AK_video_id` (`video_id`),
  UNIQUE KEY `utd_AK_channel_id` (`channel_id`)
);