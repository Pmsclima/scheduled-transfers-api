# Scheduled Transfers API

REST API to schedule financial transfers with dynamic fee calculation based on amount and execution date.

## Features
- Create, update, delete and fetch scheduled transfers
- Automatic fee calculation based on extensible business rules (Tax A / B / C)
- Validation and consistent error responses (RFC 7807 ProblemDetail)
- Pagination on list endpoint

## Tech stack
- Java 21
- Spring Boot 3.5.9
- Spring Web
- Spring Data JPA
- H2 (in-memory)
- MapStruct
- Lombok
- Maven

The API will be available at:
- http://localhost:8080

H2 Console (if enabled):
- http://localhost:8080/h2-console

Swagger UI available at:
- http://localhost:8080/swagger-ui.html

## How to run
```bash
mvn clean install
mvn spring-boot:run