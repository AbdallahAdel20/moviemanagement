# Movie Management System

A Spring Boot application for managing movies with features like user authentication, movie CRUD operations, and more.

## Prerequisites

- Java 21
- Maven 3.6+
- MySQL 8.0+
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

## Technologies Used

- Spring Boot 3.3.10
- Spring Security
- Spring Data JPA
- MySQL Database
- JWT for Authentication
- Lombok
- MapStruct
- Maven

## Project Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd moviemanagement
```

2. Configure MySQL Database:
   - Create a new MySQL database
   - Update the database configuration in `src/main/resources/application.properties` with your database credentials

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/movieapp/
│   │       ├── config/         # Configuration classes
│   │       ├── controller/     # REST controllers
│   │       ├── dto/           # Data Transfer Objects
│   │       ├── entity/        # JPA entities
│   │       ├── repository/    # JPA repositories
│   │       ├── security/      # Security configuration
│   │       ├── service/       # Business logic
│   │       └── util/          # Utility classes
│   └── resources/
│       └── application.properties
└── test/                      # Test classes
```

## Features

- User Authentication and Authorization
- Movie Management (CRUD operations)
- JWT-based Security
- RESTful API endpoints
- Database Integration with MySQL
