import 'dotenv/config';
import { drizzle } from "drizzle-orm/mysql2";
import { posts } from './schema/schema';

if (!process.env.DATABASE_URL) {
  throw new Error('DATABASE_URL 未设置');
}
const db = drizzle(process.env.DATABASE_URL);

const searchPosts = async () => {
  return await db
    .select()
    .from(posts);
}

console.log(await searchPosts());
