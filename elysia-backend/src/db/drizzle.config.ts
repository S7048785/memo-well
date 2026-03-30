import 'dotenv/config';
import { defineConfig } from 'drizzle-kit';

export default defineConfig({
  out: './schema',
  schema: './schema/schema.ts',
  dialect: 'mysql',
  dbCredentials: {
    url: "mysql://root:123456@localhost:3306/memo_well",
  },
});
