# Task Manager API

Backend project developed with **Spring Boot** and **Docker**.  
This application manages a task system with persistent data in a relational database and advanced security features.

---

## 📐 Project Architecture

The codebase follows the principles of **Clean Architecture** and **Separation of Concerns**.

### 🧱 Layers

#### Controller & DTO
Uses Data Transfer Objects (DTOs) to decouple database entities from the API interface, improving security and maintainability.

#### Service Layer
All business logic is isolated in services, making the codebase testable and reusable.

#### Repository Pattern
Handles direct interaction with MySQL through Spring Data JPA.

#### Global Exception Handling
Implements centralized error handling using `@ControllerAdvice`, returning consistent JSON error responses.

#### Soft Delete
Instead of permanently deleting records, tasks are marked as deleted using a `deletedAt` column.  
This ensures data integrity and allows historical recovery while keeping tasks invisible to users.

---

## 🛠️ Technologies Used

- Java & Spring Boot – Application framework and core logic
- MySQL 8 – Relational database for task persistence
- Docker & Docker Compose – Containerization and service orchestration
- PhpMyAdmin – Database management interface for development and testing

---

## 🔐 Security & Best Practices

- Passwords are securely stored using **BCrypt hashing**
- Sensitive configuration values are stored in environment variables (`.env`)
- The `.env` file is excluded from version control for security purposes

---

## 🚀 How to Run the Project

1. Clone the repository
2. Copy `.env.example` to a new file named `.env`
3. Fill in your credentials in the `.env` file
4. Start the services using Docker:

```bash
docker compose up --build
```

---

## 📡 Accessing Services

- **API Documentation (Swagger UI):**  
  http://localhost:8080/swagger-ui.html

- **PhpMyAdmin (Database UI):**  
  http://localhost:8081

---

## 🎯 Project Overview

This project provides a secure and scalable backend API for task management, built with modern development practices. It emphasizes clean architecture, maintainability, security, and containerized deployment for ease of setup and collaboration.
