# Recommended Git Commits

Based on your current git status and diff, your project cleanup and renaming was already committed (your branch is ahead by 1 commit). The current unstaged/untracked changes can be broken down into the following logical commits. 

You can run these commands directly in your terminal to safely stage and commit the work piece-by-piece:

### 1. Add Security & Web Dependencies
This commit adds the necessary libraries to the `build.gradle` file.

**Commands:**
```bash
git add build.gradle
git commit -m "build: add Spring Web, Security, and JWT dependencies" -m "Added spring-boot-starter-web, spring-boot-starter-security, and jjwt libraries for stateless authentication."
```

---

### 2. JWT Security Configuration
This commit introduces the core security components like the JWT utility, the authentication filter, and the Spring Security config.

**Commands:**
```bash
git add src/main/java/com/springbootdemo/springbootlearn/security/
git commit -m "feat(security): implement stateless JWT authentication setup" -m "Created JwtUtil, JwtAuthenticationFilter, and SecurityConfig to secure endpoints and provide BCrypt password hashing."
```

---

### 3. Domain Models & Core Services
This commit adds the `User` and `Task` entities along with their respective business logic services.

**Commands:**
```bash
git add src/main/java/com/springbootdemo/springbootlearn/model/
git add src/main/java/com/springbootdemo/springbootlearn/service/
git commit -m "feat(core): add User and Task models with in-memory services" -m "Implemented UserService for registration and login. Implemented TaskService for creating and completing tasks."
```

---

### 4. API Controllers (POST Endpoints)
This commit exposes the REST API routes.

**Commands:**
```bash
git add src/main/java/com/springbootdemo/springbootlearn/controller/
git commit -m "feat(api): implement auth and task POST endpoints" -m "Added AuthController (/api/auth/register, /api/auth/login) and TaskController (/api/tasks, /api/tasks/{id}/complete)."
```
