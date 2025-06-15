# Student Course Management API Documentation

## Common Headers
All API endpoints (except authentication) require:
| Header Name | Required | Description |
|------------|----------|-------------|
| Session-Key | Yes | Authentication token obtained from login |

## Authentication API
Base URL: `/api/auth`

| Endpoint | Method | URL | Request Body | Description |
|----------|--------|-----|--------------|-------------|
| Admin Login | POST | `/admin/login` | `{ "email": "string", "password": "string" }` | Authenticates admin users |
| Student Login | POST | `/student/login` | `{ "specialKey": "string" }` | Authenticates students |

## Student Management API
Base URL: `/api/students`

| Endpoint | Method | URL | Request Body/Parameters | Access Level | Description |
|----------|--------|-----|----------------------|--------------|-------------|
| Create Student | POST | `/` | `{ "name": "string", "email": "string" }` | Admin | Creates a new student |
| Get Student | GET | `/{id}` | Path param: id (Long) | Admin | Retrieves student details |
| Update Student | PUT | `/` | `{ "id": "long", "name": "string", "email": "string" }` | Admin | Updates student information |
| Delete Student | DELETE | `/{id}` | Path param: id (Long) | Admin | Deletes a student |

## Course Management API
Base URL: `/api/courses`

| Endpoint | Method | URL | Request Body/Parameters | Access Level | Description |
|----------|--------|-----|----------------------|--------------|-------------|
| Create Course | POST | `/` | `{ "name": "string", "description": "string" }` | Admin | Creates a new course |
| Get Course | GET | `/{id}` | Path param: id (Long) | Admin | Retrieves course details |
| Update Course | PUT | `/` | `{ "id": "long", "name": "string", "description": "string" }` | Admin | Updates course information |
| Delete Course | DELETE | `/{id}` | Path param: id (Long) | Admin | Deletes a course |

## Course Registration API
Base URL: `/api/courses/registration`

| Endpoint | Method | URL | Request Body/Parameters | Access Level | Description |
|----------|--------|-----|----------------------|--------------|-------------|
| Register Course | POST | `/` | `{ "courseId": "long", "studentId": "long" }` | Student | Registers student for a course |
| Cancel Registration | DELETE | `/{id}` | Path param: id (Long) | Student | Cancels course registration |

## Admin Dashboard API
Base URL: `/api/admin/dashboard`

| Endpoint | Method | URL | Access Level | Description |
|----------|--------|-----|--------------|-------------|
| Get All Students | GET | `/students` | Admin | Returns list of students with their registered courses |
| Get All Courses | GET | `/courses` | Admin | Returns list of courses with enrolled students |

## Error Handling

| Status Code | Description | Possible Causes |
|-------------|-------------|----------------|
| 200 OK | Successful operation | Request completed successfully |
| 400 Bad Request | Invalid request | Missing or invalid parameters |
| 401 Unauthorized | Authentication failed | Invalid or expired session key |
| 403 Forbidden | Permission denied | Insufficient access rights |
| 404 Not Found | Resource not found | Invalid ID or deleted resource |
| 500 Server Error | Internal server error | Server-side issues |

## Author
@MordechaiDavid

## Last Updated
2025-06-15 22:23:03