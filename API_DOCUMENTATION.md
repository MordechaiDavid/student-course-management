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
| Admin Login | POST | `/api/auth/admin/login` | `{ "email": "string", "password": "string" }` | Authenticates admin users |
| Student Login | POST | `/api/auth/student/login` | `{ "specialKey": "string" }` | Authenticates students |

## Student Management API
Base URL: `/api/admin/students`

| Endpoint | Method | URL | Request Body/Parameters | Description |
|----------|--------|-----|----------------------|-------------|
| Create Student | POST | `/api/admin/students` | `{ "name": "string", "email": "string" }` | Creates a new student |
| Get All Students | GET | `/api/admin/students` |  | Retrieves all students |
| Get Student by ID | GET | `/api/admin/students/{id}` | Path param: id (Long) | Retrieves student details by ID |
| Get Student by Email | GET | `/api/admin/students/email/{email}` | Path param: email (String) | Retrieves student details by email |
| Update Student | PUT | `/api/admin/students/{id}` | Path param: id (Long), body: `{ "name": "string", "email": "string" }` | Updates student information |
| Delete Student | DELETE | `/api/admin/students/{id}` | Path param: id (Long) | Deletes a student |

## Course Management API
Base URL: `/api/admin/courses`

| Endpoint | Method | URL | Request Body/Parameters | Description |
|----------|--------|-----|----------------------|-------------|
| Create Course | POST | `/api/admin/courses` | `{ "name": "string", "description": "string" }` | Creates a new course |
| Get All Courses | GET | `/api/admin/courses` |  | Retrieves all courses |
| Get Course by ID | GET | `/api/admin/courses/{id}` | Path param: id (Long) | Retrieves course details by ID |
| Update Course | PUT | `/api/admin/courses/{id}` | Path param: id (Long), body: `{ "name": "string", "description": "string" }` | Updates course information |
| Delete Course | DELETE | `/api/admin/courses/{id}` | Path param: id (Long) | Deletes a course |

## Course Registration API (Student)
Base URL: `/api/student/courses`

| Endpoint | Method | URL | Request Body/Parameters | Description |
|----------|--------|-----|----------------------|-------------|
| Register for Course | POST | `/api/student/courses/register` | `{ "studentId": "long", "courseId": "long" }` | Registers student for a course |
| Cancel Registration | DELETE | `/api/student/courses/unregister` | `{ "studentId": "long", "courseId": "long" }` | Cancels course registration |

## Admin Dashboard API
Base URL: `/api/admin/dashboard`

| Endpoint | Method | URL | Description |
|----------|--------|-----|-------------|
| Get All Students with Courses | GET | `/api/admin/dashboard/students` | Returns list of students with their registered courses |
| Get All Courses with Students | GET | `/api/admin/dashboard/courses` | Returns list of courses with enrolled students |

## Error Handling

| Status Code | Description | Possible Causes |
|-------------|-------------|----------------|
| 200 OK | Successful operation | Request completed successfully |
| 201 Created | Resource created | Resource was successfully created |
| 400 Bad Request | Invalid request | Missing or invalid parameters |
| 401 Unauthorized | Authentication failed | Invalid or expired session key |
| 403 Forbidden | Permission denied | Insufficient access rights |
| 404 Not Found | Resource not found | Invalid ID or deleted resource |
| 500 Server Error | Internal server error | Server-side issues |

## Author
@MordechaiDavid

## Last Updated
2025-06-15 22:23:03