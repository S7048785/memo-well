-- Current sql file was generated after introspecting the database
-- If you want to run this migration please uncomment this code before executing migrations
/*
CREATE TABLE `categories` (
	`id` int unsigned AUTO_INCREMENT NOT NULL,
	`name` varchar(50) NOT NULL,
	`sort_order` int DEFAULT 0,
	CONSTRAINT `categories_id` PRIMARY KEY(`id`),
	CONSTRAINT `name` UNIQUE(`name`)
);
--> statement-breakpoint
CREATE TABLE `comments` (
	`id` int unsigned AUTO_INCREMENT NOT NULL,
	`post_id` int unsigned NOT NULL,
	`user_id` int unsigned NOT NULL,
	`parent_id` int unsigned DEFAULT 0,
	`content` text NOT NULL,
	`created_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	CONSTRAINT `comments_id` PRIMARY KEY(`id`)
);
--> statement-breakpoint
CREATE TABLE `likes` (
	`id` int unsigned AUTO_INCREMENT NOT NULL,
	`user_id` int unsigned NOT NULL,
	`post_id` int unsigned NOT NULL,
	`created_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	CONSTRAINT `likes_id` PRIMARY KEY(`id`),
	CONSTRAINT `uk_user_post` UNIQUE(`user_id`,`post_id`)
);
--> statement-breakpoint
CREATE TABLE `posts` (
	`id` int unsigned AUTO_INCREMENT NOT NULL,
	`user_id` int unsigned NOT NULL,
	`category_id` int unsigned,
	`type` tinyint(1) NOT NULL DEFAULT 1,
	`content` text,
	`image_url` varchar(255),
	`card_color` varchar(20) DEFAULT '#FFFFFF',
	`like_count` int unsigned DEFAULT 0,
	`comment_count` int unsigned DEFAULT 0,
	`is_top` tinyint(1) DEFAULT 0,
	`created_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	`updated_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	CONSTRAINT `posts_id` PRIMARY KEY(`id`)
);
--> statement-breakpoint
CREATE TABLE `users` (
	`id` int unsigned AUTO_INCREMENT NOT NULL,
	`email` varchar(64) NOT NULL,
	`username` varchar(50) NOT NULL,
	`password` varchar(255) NOT NULL,
	`avatar` varchar(255),
	`bio` varchar(64),
	`gender` tinyint(1) DEFAULT 0,
	`role` varchar(10) DEFAULT 'user',
	`created_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	`updated_at` datetime DEFAULT (CURRENT_TIMESTAMP),
	CONSTRAINT `users_id` PRIMARY KEY(`id`),
	CONSTRAINT `username` UNIQUE(`username`)
);
--> statement-breakpoint
CREATE INDEX `idx_parent_id` ON `comments` (`parent_id`);--> statement-breakpoint
CREATE INDEX `idx_post_id` ON `comments` (`post_id`);--> statement-breakpoint
CREATE INDEX `idx_post_id` ON `likes` (`post_id`);--> statement-breakpoint
CREATE INDEX `idx_category_id` ON `posts` (`category_id`);--> statement-breakpoint
CREATE INDEX `idx_type_created` ON `posts` (`type`,`created_at`);--> statement-breakpoint
CREATE INDEX `idx_user_id` ON `posts` (`user_id`);
*/