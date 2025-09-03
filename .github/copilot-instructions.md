# IronWars - Programming Languages Learning Platform

IronWars is a Java Spring Boot 3.x REST API application for managing programming language learning. Teachers create exercises and exams while students complete them to progress through experience levels.

**ALWAYS reference these instructions first and fallback to search or bash commands only when you encounter information that does not match what is documented here.**

## Working Effectively

### Prerequisites
- **Java 17+** (application requires Java 17, tested with OpenJDK 17.0.16)
- **Maven 3.6+** (uses Maven wrapper - ALWAYS use `./mvnw` commands)
- **Database**: MySQL (production) or H2 (development/testing)

### Bootstrap, Build, and Test
- **NEVER CANCEL builds or tests** - they may take 5+ minutes in some environments
- Build from clean state:
  ```bash
  cd IronWars  # Navigate to the IronWars subdirectory
  ./mvnw clean package -DskipTests
  ```
  - Takes ~6 seconds on fast systems, up to 5+ minutes on slower systems
  - **CRITICAL**: Set timeout to 10+ minutes minimum, preferably 15+ minutes
  
- Run tests (requires database configuration):
  ```bash
  ./mvnw test
  ```
  - Takes ~10 seconds on fast systems, up to 15+ minutes on slower systems  
  - **NEVER CANCEL**: Set timeout to 30+ minutes minimum
  - **Note**: Tests currently fail without proper database setup due to entity validation constraints

- Quick compilation check:
  ```bash
  ./mvnw compile
  ```
  - Takes ~30 seconds first time (dependencies download), ~3-5 seconds subsequent builds

### Running the Application

#### Development Mode (H2 In-Memory Database)
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
```
- **NEVER CANCEL**: Allow 2-5 minutes for startup on slower systems
- Starts Tomcat server on port 8080
- H2 console available at: http://localhost:8080/h2-console
- Database URL: `jdbc:h2:mem:ironwars`

#### Production Mode (MySQL Database)
```bash
./mvnw spring-boot:run
```
- Requires MySQL database running on localhost:3306
- Database name: `IronWars`
- Default credentials: root/felix (see application.properties)
- **NEVER CANCEL**: Allow 2-5 minutes for startup

### Application Endpoints
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **H2 Console**: http://localhost:8080/h2-console (development mode only)
- **REST API Base**: http://localhost:8080/

#### Key REST Endpoints
- `GET /teachers` - List all teachers
- `GET /students` - List all students  
- `GET /languages` - List all languages
- `GET /api/exercises` - List all exercises (note the `/api` prefix)
- `GET /exams` - List all exams

## Validation

### Manual Testing After Changes
**ALWAYS run through at least one complete validation scenario after making changes:**

1. **Basic Application Health Check**:
   ```bash
   # Start application
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=h2
   
   # Wait for "Started IronWarsApplication in X.X seconds" message
   # Test basic endpoints
   curl http://localhost:8080/teachers
   curl http://localhost:8080/languages
   curl http://localhost:8080/students
   curl http://localhost:8080/api/exercises
   ```

2. **Swagger UI Validation**:
   - Navigate to http://localhost:8080/swagger-ui/index.html
   - Verify OpenAPI documentation loads properly
   - Test at least one GET endpoint through the Swagger interface

3. **Database Validation** (H2 development mode):
   - Navigate to http://localhost:8080/h2-console
   - Connect with URL: `jdbc:h2:mem:ironwars`, User: `sa`, Password: (empty)
   - Verify tables are created: `exams`, `exercises`, `language`, `student`, `teacher`

### Pre-commit Validation
- **Always build and test your changes** before committing:
  ```bash
  ./mvnw clean package -DskipTests
  # Verify application starts properly
  ./mvnw spring-boot:run -Dspring-boot.run.profiles=h2 &
  # Test endpoints, then stop application
  ```

## Critical Timing and Timeout Information

### Build Times (Set appropriate timeouts)
- `./mvnw clean` - 1-2 seconds
- `./mvnw compile` - 30 seconds (first time), 3-5 seconds (subsequent)
- `./mvnw package -DskipTests` - 6 seconds fast systems, **up to 5+ minutes slower systems**
- `./mvnw test` - **10 seconds to 15+ minutes depending on environment**
- `./mvnw spring-boot:run` - **2-5 minutes startup time**

### **NEVER CANCEL WARNING**
- **NEVER CANCEL** any Maven build or test command
- **NEVER CANCEL** application startup
- Always set timeouts with generous buffers:
  - Builds: minimum 10 minutes, recommended 15+ minutes
  - Tests: minimum 20 minutes, recommended 30+ minutes
  - Application startup: minimum 5 minutes

## Important Notes

### Database Configuration Issues
- **DataLoader**: Currently disabled due to validation constraint violations
- Tests require proper database setup to pass
- Default configuration uses MySQL but H2 profile available for development

### Entity Mapping Issues Fixed
- Fixed `Exam.java` entity mapping issue (`@Column` on `@OneToOne` relationship)
- Fixed repository method naming (`findByDificulty` vs `findByDifficulty`)

### Project Structure Key Locations
```
IronWars/
├── pom.xml                              # Maven configuration
├── src/main/java/com/YronJack/IronWars/
│   ├── IronWarsApplication.java         # Main application class
│   ├── controller/                      # REST controllers
│   ├── model/                          # JPA entities (User, Student, Teacher, etc.)
│   ├── repository/                     # JPA repositories  
│   ├── service/                        # Business logic
│   └── config/                         # Configuration (DataLoader, OpenAPI)
├── src/main/resources/
│   ├── application.properties          # Main configuration (MySQL)
│   └── application-h2.properties       # H2 development configuration
└── src/test/
    ├── java/                           # Test classes
    └── resources/
        └── application-test.properties  # Test configuration
```

## Frequently Referenced Information

### Repository Root Structure
```
├── README.md
├── YronWars.drawio
├── .gitignore  
└── IronWars/           # Main application directory
    ├── pom.xml
    ├── mvnw            # Maven wrapper (Unix)
    ├── mvnw.cmd        # Maven wrapper (Windows)
    └── src/
```

### Maven Dependencies
- Spring Boot 3.5.5
- Java 17
- MySQL Connector
- H2 Database (development)
- Lombok
- Spring Data JPA
- SpringDoc OpenAPI (Swagger)
- Spring Boot DevTools

### Common Issues
- **Build fails**: Check Java version (requires Java 17+)
- **Tests fail**: Database configuration issues, use H2 profile for testing
- **404 errors**: Check controller mappings (exercises at `/api/exercises`)
- **Startup fails**: DataLoader validation constraints (currently disabled)

**CRITICAL REMINDER**: This application has entity mapping and validation complexities. Always validate thoroughly after changes and never cancel long-running operations.