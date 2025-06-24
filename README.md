# 💬 Chatty Backend - Spring Boot REST API

This is the backend for the **Chatty** app — a real-time chat application built using **Spring Boot**. It exposes REST APIs for user authentication and chat messaging. Designed to be used with a frontend (React + Vite) and deployed to **Railway**.

---

## ⚙️ Technologies Used

- ☕ Spring Boot
- 🔐 Spring Security (JWT)
- 🧠 Spring Data JPA
- 🐘 PostgreSQL or MySQL
- 🌍 RESTful API
- ☁️ Deployed on Railway

---

## 📦 Features

- ✅ User Registration and Login
- ✅ JWT-based Authentication
- ✅ Send & Retrieve Messages
- ✅ CORS Config for Frontend Communication
- ✅ Environment Configurable via `application.properties`

---
Structure

mkdir -p chatty-backend/src/main/java/com/example/chatty/{controller,model,repository,security,service}

mkdir -p chatty-backend/src/main/resources

touch chatty-backend/pom.xml

touch chatty-backend/src/main/java/com/example/chatty/ChattyApplication.java

touch chatty-backend/src/main/resources/application.properties



---

## 🔐 API Endpoints

| Method | Endpoint              | Description              |
|--------|------------------------|--------------------------|
| POST   | `/api/auth/register`   | Register new user        |
| POST   | `/api/auth/login`      | Authenticate user + JWT  |
| GET    | `/api/chat/messages`   | Get chat history         |
| POST   | `/api/chat/message`    | Post new message         |

> All chat endpoints require a valid JWT token in the `Authorization` header.

---

## 🔧 Configuration

### `application.properties` (example)

```properties
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/chattydb
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update

# Allow frontend domain (React on Railway)
spring.web.cors.allowed-origins=https://chatty-react-vite.up.railway.app

Use Postman or cur
curl -X POST https://chatty-production-9a74.up.railway.app/api/auth/login \
-H "Content-Type: application/json" \
-d '{"username":"testuser", "password":"1234"}'



