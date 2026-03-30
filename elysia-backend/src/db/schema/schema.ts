import { mysqlTable, mysqlSchema, AnyMySqlColumn, primaryKey, unique, int, varchar, index, text, datetime, tinyint } from "drizzle-orm/mysql-core"
import { sql } from "drizzle-orm"

export const categories = mysqlTable("categories", {
	id: int({ unsigned: true }).autoincrement().notNull(),
	name: varchar({ length: 50 }).notNull(),
	sortOrder: int("sort_order").default(0),
},
(table) => [
	primaryKey({ columns: [table.id], name: "categories_id"}),
	unique("name").on(table.name),
]);

export const comments = mysqlTable("comments", {
	id: int({ unsigned: true }).autoincrement().notNull(),
	postId: int("post_id", { unsigned: true }).notNull(),
	userId: int("user_id", { unsigned: true }).notNull(),
	parentId: int("parent_id", { unsigned: true }).default(0),
	content: text().notNull(),
	createdAt: datetime("created_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
},
(table) => [
	index("idx_parent_id").on(table.parentId),
	index("idx_post_id").on(table.postId),
	primaryKey({ columns: [table.id], name: "comments_id"}),
]);

export const likes = mysqlTable("likes", {
	id: int({ unsigned: true }).autoincrement().notNull(),
	userId: int("user_id", { unsigned: true }).notNull(),
	postId: int("post_id", { unsigned: true }).notNull(),
	createdAt: datetime("created_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
},
(table) => [
	index("idx_post_id").on(table.postId),
	primaryKey({ columns: [table.id], name: "likes_id"}),
	unique("uk_user_post").on(table.userId, table.postId),
]);

export const posts = mysqlTable("posts", {
	id: int({ unsigned: true }).autoincrement().notNull(),
	userId: int("user_id", { unsigned: true }).notNull(),
	categoryId: int("category_id", { unsigned: true }),
	type: tinyint().default(1).notNull(),
	content: text(),
	imageUrl: varchar("image_url", { length: 255 }),
	cardColor: varchar("card_color", { length: 20 }).default('#FFFFFF'),
	likeCount: int("like_count", { unsigned: true }).default(0),
	commentCount: int("comment_count", { unsigned: true }).default(0),
	isTop: tinyint("is_top").default(0),
	createdAt: datetime("created_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
	updatedAt: datetime("updated_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
},
(table) => [
	index("idx_category_id").on(table.categoryId),
	index("idx_type_created").on(table.type, table.createdAt),
	index("idx_user_id").on(table.userId),
	primaryKey({ columns: [table.id], name: "posts_id"}),
]);

export const users = mysqlTable("users", {
	id: int({ unsigned: true }).autoincrement().notNull(),
	email: varchar({ length: 64 }).notNull(),
	username: varchar({ length: 50 }).notNull(),
	password: varchar({ length: 255 }).notNull(),
	avatar: varchar({ length: 255 }),
	bio: varchar({ length: 64 }),
	gender: tinyint().default(0),
	role: varchar({ length: 10 }).default('user'),
	createdAt: datetime("created_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
	updatedAt: datetime("updated_at", { mode: 'string'}).default(sql`(CURRENT_TIMESTAMP)`),
},
(table) => [
	primaryKey({ columns: [table.id], name: "users_id"}),
	unique("username").on(table.username),
]);
