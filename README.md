# Student Course Manager

A web application for managing students and courses, built with Java Spring Boot and MySQL. This project allows administrators to manage students and courses, and enables students to register for courses with specific constraints.

## Overview
This application is designed to demonstrate skills in database design, backend development, and RESTful API creation. It provides a system where students can register for courses, and administrators can manage both students and courses.

## Features

### Admin Features
- **Login**: Admins authenticate using email and password.
- **CRUD on Students**: Create, read, update, and delete student records. Each student receives an auto-generated `special_key` for login.
- **CRUD on Courses**: Create, read, update, and delete course records.
- **Get All Students**: Retrieve all students and the courses they are registered for.
- **Get All Courses**: Retrieve all courses and the students registered to each.

### Student Features
- **Login**: Students log in using their `special_key`.
- **Register for Courses**: Students can register for up to 2 courses.
- **Cancel Registration**: Students can cancel their course registration.

### Constraints
- A course cannot have more than 30 students.
- A student cannot register for more than 2 courses.

### Implementation Highlights
- Separate authentication for admins and students.
- Session handling: After login, users receive a session key to be included in all subsequent requests.
- Proper validation, error handling, and logging for all backend operations.

## Technology Stack
- **Java 17**
- **Spring Boot 3.4.3**
  - spring-boot-starter-web
  - spring-boot-starter-data-jpa
  - spring-boot-starter-security
  - spring-boot-starter-validation
  - spring-boot-starter-test
- **MySQL 8.0.32**
- **Hibernate (JPA)**
- **Lombok 1.18.36**
- **H2 Database (for testing)**
- **SpringDoc OpenAPI (Swagger) 2.3.0**
- **Maven**

## Database Schema
- See [`console.sql`](console.sql) for table creation scripts.
- See [`insertions.sql`](insertions.sql) for sample data insertions.

Main entities:
- **admins**: id, name, email, hash_password
- **students**: id, name, email, special_key, created_at
- **courses**: id, name, description, created_at
- **enrollments**: id, student_id, course_id
- **sessions**: id, session_key, user_type, user_id, created_at, expires_at

## API Documentation
- See [`API_DOCUMENTATION.md`](API_DOCUMENTATION.md) for a full list of endpoints, request/response formats, and error handling.
- A Postman collection is provided for easy API testing: [`student-course-manager.postman_collection.json`](student-course-manager.postman_collection.json)

## Deliverables
- Source code (this repository)
- Database schema and sample data (`console.sql`, `insertions.sql`)
- API documentation (`API_DOCUMENTATION.md`)
- Postman collection for API testing (`student-course-manager.postman_collection.json`)
- Setup and run instructions (see `INSTRUCTIONS.md`)

## Author
Mordechai David

---

For setup and running instructions, see [INSTRUCTIONS.md](INSTRUCTIONS.md). 