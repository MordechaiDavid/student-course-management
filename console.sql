USE student_course_manager;

CREATE TABLE admins
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE,
    hash_password VARCHAR(255)
);

CREATE TABLE students
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    special_key VARCHAR(255) NOT NULL UNIQUE,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP

);



CREATE TABLE courses
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE course_registrations
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    course_id  BIGINT NOT NULL,
    CONSTRAINT fk_registration_student FOREIGN KEY (student_id)
        REFERENCES students (id)
        ON DELETE CASCADE,

    CONSTRAINT fk_registration_course FOREIGN KEY (course_id)
        REFERENCES courses (id)
        ON DELETE CASCADE,

    CONSTRAINT uc_student_course UNIQUE (student_id, course_id)

);

CREATE TABLE sessions
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    session_key VARCHAR(255)              NOT NULL UNIQUE,
    user_type   ENUM ('ADMIN', 'STUDENT') NOT NULL,
    user_id     BIGINT                    NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expires_at  TIMESTAMP                 NOT NULL
);