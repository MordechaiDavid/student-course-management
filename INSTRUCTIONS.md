# Running the Student Course Manager Locally

Follow these steps to set up and run the application on your local machine.

## Prerequisites
- **Java 17** or higher
- **Maven**
- **MySQL** (running locally or accessible remotely)

## 1. Clone the Repository
```bash
# Clone this repository
git clone https://github.com/MordechaiDavid/student-course-management.git
cd student-course-management
```

## 2. Configure the Application
1. **Copy the example configuration file:**
   - The file `src/main/resources/application.example.yml` is provided as a template. Copy it to `src/main/resources/application.yml`:
   ```bash
   cp src/main/resources/application.example.yml src/main/resources/application.yml
   ```
2. **Edit your configuration:**
   - Open `src/main/resources/application.yml` in a text editor.
   - Replace `your_username_here` and `your_password_here` with your actual MySQL username and password:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/student_course_manager
       username: your_mysql_username   # <-- Provide your MySQL username
       password: your_mysql_password   # <-- Provide your MySQL password
     jpa:
       hibernate:
         ddl-auto: update
       show-sql: true
     server:
       port: 8080
   ```
   - **Note:** The application will not connect to the database unless you provide valid credentials.

## 3. Set Up the Database
1. Create the database and tables:
   - Open your MySQL client and run the commands in [`console.sql`](console.sql):
     ```sql
     SOURCE console.sql;
     ```
2. (Optional) Insert sample data:
   - Run the commands in [`insertions.sql`](insertions.sql):
     ```sql
     SOURCE insertions.sql;
     ```

## 4. Build the Application
```bash
mvn clean install
```

## 5. Run the Application
```bash
mvn spring-boot:run
```
The application will start on [http://localhost:8080](http://localhost:8080) by default.

## 6. Test the API
- Use the provided Postman collection [`student-course-manager.postman_collection.json`](student-course-manager.postman_collection.json) to test all endpoints.
- Import the collection into Postman and set the `baseUrl` variable to `http://localhost:8080`.

## 7. API Documentation
- See [`API_DOCUMENTATION.md`](API_DOCUMENTATION.md) for detailed API endpoints, request/response formats, and error handling.
- Swagger UI (if enabled) will be available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) or `/swagger-ui/index.html`.

## 8. Default Admin Users
- Default admin users and passwords are provided in [`insertions.sql`](insertions.sql) (passwords are hashed; see comments for plain text).

## Troubleshooting
- Ensure MySQL is running and accessible.
- Check that the database credentials in `application.yml` are correct.
- Review application logs for errors.

---
