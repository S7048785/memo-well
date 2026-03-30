# AGENTS.md - Developer Guide for memo-wall

## Project Overview

This is a monorepo containing a full-stack application:

- **react-admin**: React 19 + Vite + TanStack Router/Query + Tailwind CSS v4 (admin dashboard)
- **next-web**: Next.js 16 + HeroUI + Tailwind CSS v4 (frontend web)
- **springboot-backend**: Spring Boot 3 + Gradle (Java REST API)

## Commands

### react-admin
| Command | Description |
|---------|-------------|
| `npm run dev` | Start development server |
| `npm run build` | Type-check and build for production |
| `npm run lint` | Run ESLint on all files |
| `npm run preview` | Preview production build |

### next-web
| Command | Description |
|---------|-------------|
| `npm run dev` | Start development server with Turbopack |
| `npm run build` | Build for production |
| `npm run start` | Start production server |
| `npm run lint` | Run ESLint with auto-fix |

### springboot-backend
| Command | Description |
|---------|-------------|
| `./gradlew bootRun` | Start Spring Boot application |
| `./gradlew build` | Build the application |
| `./gradlew test` | Run tests |
| `./gradlew test --tests "TestClass.testMethod"` | Run a single test |

## Running Tests

### react-admin
No test framework is currently configured.

### next-web
No test framework is currently configured.

### springboot-backend
Uses JUnit 5 (via Gradle). Run all tests:
```bash
./gradlew test
```
Run a single test:
```bash
./gradlew test --tests "com.memo.wall.controller.UserControllerTest.testGetUser"
```

## Code Style Guidelines

### TypeScript
- Strict mode is enabled in both TS projects
- Avoid `any` - use proper TypeScript types
- Use `type` instead of `interface` where possible
- Enable `noUnusedLocals` and `noUnusedParameters`

### Import Conventions
- Use path alias `@/` for imports from `src/` (react-admin)
- Use path alias `@/` for imports from project root (next-web)
- Order: external libraries → internal modules → relative paths

### React Patterns (react-admin & next-web)
- Use functional components with arrow functions
- Use TanStack Query (`@tanstack/react-query`) for data fetching
- Use TanStack Router in react-admin, Next.js App Router in next-web

### Tailwind CSS v4
- Use `@import "tailwindcss"` syntax
- Use `@theme` directive for custom theme values
- Use `clsx` and `tailwind-merge` for conditional classes:
  ```ts
  import { clsx } from "clsx"
  import { twMerge } from "tailwind-merge"
  
  export function cn(...inputs: (string | undefined | null | false)[]) {
    return twMerge(clsx(inputs))
  }
  ```

### Naming Conventions
- **Components**: PascalCase (e.g., `Button`, `UserProfile`)
- **Files**: kebab-case for components, PascalCase for utilities
- **Variables/functions**: camelCase
- **Constants**: UPPER_SNAKE_CASE

### Error Handling
- Use try/catch for async operations
- Handle API errors gracefully
- Use TypeScript discriminated unions for error states

## File Organization

### react-admin
```
react-admin/src/
├── components/ui/       # Reusable UI components
├── lib/
│   ├── utils.ts         # cn helper
│   ├── request.ts      # Axios instance
│   └── queryClient.ts  # TanStack Query client
└── App.tsx
```

### next-web
```
next-web/
├── app/                 # Next.js App Router
├── components/          # React components
└── config/              # Configuration files
```

### springboot-backend
```
springboot-backend/src/main/java/com/memo/wall/
├── controller/          # REST controllers
├── service/             # Business logic
├── repository/          # Data access
└── model/               # Entities and DTOs
```

## ESLint Configuration

### react-admin
- Extends: `eslint:recommended`, `typescript-eslint/recommended`, `react-hooks/recommended`, `react-refresh/vite`

### next-web
- Extends: `eslint:recommended`, `next/core-web-vitals`, Prettier, TypeScript

## Build Configuration

### react-admin
- Vite 7 with React plugin and Babel compiler
- TanStack Router with auto-code-splitting

### next-web
- Next.js 16 with Turbopack
- HeroUI component library

### springboot-backend
- Spring Boot 3.x with Gradle
- Java 17+

## Notes
- No test framework in frontend projects (react-admin, next-web)
- shadcn/ui components in react-admin
- HeroUI components in next-web
- API types auto-generated in `src/__generated/`
