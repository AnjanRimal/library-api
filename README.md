# 📚 Library API

A RESTful library management service built with **Spring Boot 4** and **Java 21**. It manages authors, books and members, and handles borrowing and returning books.

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

## ✨ Features

- CRUD for **authors**, **books** and **members**
- **Many-to-many** relationship between books and authors (`author_books` join table)
- **Borrow / return** workflow that tracks which member holds a book and whether it is available
- Case-insensitive **title search**
- **DTOs** for authors and books, so API responses don't expose raw JPA entities
- Layered architecture: controller → service → repository

## 🏗️ Architecture

```
src/main/java/com/anjan/library_api
├── controller/   REST endpoints (Author, Book, Member)
├── service/      Business logic (borrowing rules, mapping to DTOs)
├── repository/   Spring Data JPA repositories
├── entity/       JPA entities: Author, Book, Member
└── dto/          AuthorDTO, BookDTO
```

```
Author ──< author_books >── Book >── Member (borrowedBy)
```

## 🔌 API endpoints

### Authors
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/authors` | Create an author |
| `GET` | `/authors` | List all authors |
| `GET` | `/authors/{id}` | Get an author by id |
| `DELETE` | `/authors/{id}` | Delete an author |

### Books
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/books` | Create a book |
| `GET` | `/books` | List all books |
| `GET` | `/books/{id}` | Get a book by id |
| `GET` | `/books/search?title=clean` | Search books by title |
| `DELETE` | `/books/{id}` | Delete a book |

### Members
| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/members` | Register a member |
| `GET` | `/members` | List all members |
| `GET` | `/members/{id}` | Get a member by id |
| `POST` | `/members/{memberId}/borrow/{bookId}` | Borrow a book |
| `POST` | `/members/{memberId}/return/{bookId}` | Return a book |
| `DELETE` | `/members/{id}` | Delete a member |

## 🚀 Getting started

### Prerequisites
- Java 21
- MySQL 8 running locally

### 1. Create the database
```sql
CREATE DATABASE library_db;
```

### 2. Configure the connection
Create `src/main/resources/application.properties` (it is git-ignored so credentials stay local):

```properties
spring.application.name=library-api
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run
```bash
./mvnw spring-boot:run        # macOS / Linux
mvnw.cmd spring-boot:run      # Windows
```

The API starts on **http://localhost:8080**.

## 🧪 Try it

Ready-made requests are in [`src/main/resources/test.http`](src/main/resources/test.http) (runs directly in IntelliJ or the VS Code REST Client). Or use curl:

```bash
# Create an author
curl -X POST localhost:8080/authors -H "Content-Type: application/json" \
  -d '{"name":"Robert Martin","email":"robert@example.com","bio":"Author of Clean Code"}'

# Create a book
curl -X POST localhost:8080/books -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","isbn":"978-0132350884","available":true}'

# Register a member, then borrow book 1
curl -X POST localhost:8080/members -H "Content-Type: application/json" \
  -d '{"name":"Anjan","email":"anjan@example.com","phone":"314-000-0000"}'
curl -X POST localhost:8080/members/1/borrow/1

# Search
curl "localhost:8080/books/search?title=clean"
```

## 🛣️ Roadmap
- [ ] Request validation (`@Valid`) and a global exception handler returning proper HTTP status codes
- [ ] Pagination and sorting on list endpoints
- [ ] Swagger / OpenAPI docs via springdoc
- [ ] Unit and integration tests
- [ ] Docker Compose setup (app + MySQL)

## 👤 Author
**Anjan Rimal** · [Portfolio](https://anjanrimal.com/) · [LinkedIn](https://www.linkedin.com/in/anjanrimal) · [GitHub](https://github.com/AnjanRimal)
