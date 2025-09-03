# 📘 IronWars – Programming Languages Learning Platform
## 📌 Description

IronWars is a backend application built with Java + Spring Boot that manages a learning environment for different programming languages.
Users can be students or teachers.

👩‍🏫 Teachers create and manage languages and exercises.

👨‍🎓 Students can enroll in languages, complete exercises and exams, and progress through levels (basic, intermediate, advanced).

📝 Exercises and exams are included to assess students’ knowledge.

This project was developed as part of the Backend module (Java, JPA, Spring, MySQL, Lombok).

## 🛠️ Technologies Used

Java 17+
Spring Boot 3.x
Spring Data JPA
Hibernate
MySQL (with DBeaver for DB management)
Lombok
Maven
Swagger / OpenAPI (API documentation)
Postman (endpoint testing)

## 📂 Project Structure

src/main/java/com.YronJack.IronWars
│── controller        -> REST controllers
│── dto               -> Data Transfer Objects (Request/Response DTOs)
│── model             -> JPA entities 
│   ├── User (abstract, base class)
│   ├── Student
│   ├── Teacher
│   ├── Language
│   ├── Exam
│   └── Exercise
│── repository        -> JPA repositories
│── service
│   ├── interfaces    -> Service interfaces
│   └── impl          -> Service implementations
│── enums             -> Enums used in the domain (Score, Level...)


## 🚀 How to Run the Project

1. Clone the repository
   git clone https://github.com/your-username/ironwars.git
   cd ironwars

2. Set up the MySQL database
   CREATE DATABASE ironwars;

3. Configure application.yml (in src/main/resources)
   spring:
   datasource:
    url: jdbc:mysql://localhost:3306/ironwars?useSSL=false&serverTimezone=UTC
    username: root
    password: your_password
   jpa:
    hibernate:
    ddl-auto: update
    show-sql: true
    properties:
    hibernate:
    format_sql: true

4. Build and run the project
   mvn spring-boot:run

6. Open Swagger UI
   http://localhost:8080/swagger-ui/index.html

## 📬 Main Endpoints

👩‍🏫 Teachers (/teachers)

GET /teachers → list all teachers
GET /teachers/{id} → get teacher by ID
POST /teachers → create a new teacher
PUT /teachers/{id} → update a teacher
DELETE /teachers/{id} → delete a teacher
POST /teachers/{teacherId}/languages/{languageId} → assign a language to a teacher
DELETE /teachers/{teacherId}/languages/{languageId} → remove a language from a teacher
GET /teachers/by-language/{languageId} → list teachers by language

👨‍🎓 Students (/students)

Basic CRUD + assign teacher

💻 Languages (/languages)

Basic CRUD + list expert teachers

📝 Exercises (/exercises)

CRUD endpoints to manage exercises linked to a language and authored by a teacher

📘 Exams (/exams)

CRUD endpoints to manage exams, assign exercises, and evaluate students

## 🧪 Tests
Controller test: with @WebMvcTest (e.g., TeacherControllerTest).
Service test: with @SpringBootTest or @ExtendWith(MockitoExtension.class) (e.g., ExamServiceImplTest).

## 👥 Authors
This project was developed by the class team as part of the Backend module.
