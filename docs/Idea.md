# Task Management System API with JWT Auth

This plan outlines the implementation of a **Task Management Application** that fulfills your requirements. It will feature a secure login mechanism using JWT and password hashing (BCrypt), along with 4 distinct GET and POST endpoints.

> [!TIP]
> A Task Management System is a perfect choice because it naturally provides related entities (Users and Tasks) that are easy to understand but complex enough to showcase real-world authentication and data handling.

## Proposed Application Idea: Task Management System
This API will allow users to register, log in, manage their personal tasks, and view system statistics. The authentication will secure the task endpoints so that only authenticated users can access or create tasks.

### Features
1. **User Authentication:** Registration and Login with JWT token generation.
2. **Password Hashing:** Passwords will be securely hashed using Spring Security's `BCryptPasswordEncoder`.
3. **Stateless Security:** Every protected request will be authenticated using a JWT filter.
4. **Core Domain (Tasks):** Users can create tasks and retrieve them.

---

## 4 POST Endpoints

1. **`POST /api/auth/register`** 
   - **Purpose:** Registers a new user. Hashes the password and saves the user in-memory.
2. **`POST /api/auth/login`** 
   - **Purpose:** Authenticates the user's credentials against the hashed password and returns a JWT token.
3. **`POST /api/tasks`** 
   - **Purpose:** Creates a new task. 
   - **Security:** Requires a valid JWT token.
4. **`POST /api/tasks/{id}/complete`** 
   - **Purpose:** Marks a specific task as completed. 
   - **Security:** Requires a valid JWT token.

## 4 GET Endpoints

1. **`GET /api/tasks`** 
   - **Purpose:** Retrieves a list of all tasks. 
   - **Security:** Requires a valid JWT token.
2. **`GET /api/tasks/{id}`** 
   - **Purpose:** Retrieves the details of a specific task by its ID.
   - **Security:** Requires a valid JWT token.
3. **`GET /api/tasks/stats`**
   - **Purpose:** Retrieves task statistics (e.g., total tasks, completed tasks).
   - **Security:** Requires a valid JWT token.
4. **`GET /api/public/ping`**
   - **Purpose:** A public health-check endpoint to verify the API is running.
   - **Security:** No authentication required (public).

---

## Proposed Changes

### Dependencies
We will update `build.gradle` to include:
- `spring-boot-starter-web` (For building REST APIs)
- `spring-boot-starter-security` (For authentication and BCrypt hashing)
- `jjwt-api`, `jjwt-impl`, `jjwt-jackson` (For generating and validating JWT tokens)

### Components

#### [MODIFY] [build.gradle](file:///c:/Users/cnevi/Projects/springboot-learn/build.gradle)
Add the necessary web, security, and JWT dependencies.

#### [NEW] Configuration Layer
- `SecurityConfig.java`: Configure Spring Security to use stateless sessions and define which endpoints are public/secured.
- `JwtAuthenticationFilter.java`: Filter to extract the JWT from the `Authorization` header and validate it.
- `JwtUtil.java`: Utility class to generate and parse JWT tokens.

#### [NEW] Controllers
- `AuthController.java`: Exposes the `/api/auth/register` and `/api/auth/login` POST endpoints.
- `TaskController.java`: Exposes the remaining task-related GET and POST endpoints.
- `PublicController.java`: Exposes the public `GET /api/public/ping` endpoint.

#### [NEW] Models, Repositories, & Services
- `User.java` and `Task.java`: Basic JPA Entities mapped to a persistent H2 file-based database.
- `UserRepository.java` and `TaskRepository.java`: Spring Data JPA interfaces for database access.
- `UserService.java`: Handles user registration (password hashing) and lookup.
- `TaskService.java`: Handles business logic for creating and retrieving tasks.

## Open Questions

> [!IMPORTANT]
> 1. **Data Storage:** The application uses an H2 file-based database configured via Spring Data JPA to ensure data persists between restarts.
> 2. **Application Domain:** Are you happy with the "Task Management" concept, or would you prefer a different mock application (e.g., E-Commerce, Blog)?

## Verification Plan
1. Send a POST request to `/api/auth/register` and verify the user is created.
2. Send a POST request to `/api/auth/login` to receive a JWT token.
3. Use the JWT token in the `Authorization: Bearer <token>` header to hit the secured `GET` and `POST` endpoints.
4. Verify that hitting secured endpoints *without* the token returns a `403 Forbidden` or `401 Unauthorized`.
