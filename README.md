# Banking Services System

Backend banking application built using Java, Spring Boot, and MySQL. The system supports core banking operations such as account management, deposits, withdrawals, fund transfers, debit card generation, and transaction history tracking.

---

# Features

- Account creation and management
- Deposit and withdrawal operations
- Fund transfer between accounts
- Debit card generation
- Transaction history tracking
- RESTful API architecture
- Database transaction management
- Concurrent transaction handling using pessimistic locking

---

# Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven

---

# Project Structure

```text
src/main/java/org/bank/Bank/Management
│
├── controller
├── dto
├── enums
├── exception
├── generator
├── model
├── repository
└── service
```

---

# Core Functionalities

## Account Management
- Create account
- Fetch account details
- Manage account balance

## Banking Transactions
- Deposit money
- Withdraw money
- Transfer money between accounts
- Store transaction history

## Debit Card Services
- Generate debit cards
- Link debit cards with customer accounts

---

# Concurrency Handling

Implemented pessimistic locking using JPA to maintain consistency during concurrent account transactions.

```java
@Lock(LockModeType.PESSIMISTIC_WRITE)
Optional<Account> findByAccountNumber(String accountNumber);
```

This prevents race conditions during:
- simultaneous withdrawals
- concurrent transfers
- balance updates

---

# Database Entities

Main entities used in the system:

- Account
- Transaction
- DebitCard
- AccountSequence

Relationships are managed using JPA and Hibernate.

---

# API Endpoints

## Account APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/account` | Create account |
| GET | `/account` | Get all accounts |
| PATCH | `/account/deposit/{accountNumber}/{amount}` | Deposit money |
| PATCH | `/account/withdraw/{accountNumber}/{amount}` | Withdraw money |
| PATCH | `/account/transfer/{sender}/{receiver}/{amount}` | Transfer money |

---

## Debit Card APIs

| Method | Endpoint | Description |
|---|---|---|
| POST | `/cards` | Generate debit card |
| GET | `/cards` | Get all debit cards |

---

## Transaction APIs

| Method | Endpoint | Description |
|---|---|---|
| GET | `/transactions` | Get all transactions |
| GET | `/transactions/statement/{accountNumber}` | Get account statement |

---

# How To Run

## Clone Repository

```bash
git clone https://github.com/mdobariya3606-prog/banking-services-system.git
```

## Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

## Run Application

```bash
mvn spring-boot:run
```

---

# Learning Outcomes

Through this project, I learned:

- REST API development using Spring Boot
- Layered backend architecture
- Transaction management
- JPA and Hibernate basics
- Database relationship mapping
- Concurrent transaction handling
- Exception handling
- Service and repository layer separation

---

# Future Improvements

- JWT authentication
- Role-based access control
- Swagger documentation
- Docker deployment
- Unit and integration testing
- API validation improvements

---

# Author

## Meet Dobariya

- GitHub: https://github.com/mdobariya3606-prog
- LinkedIn: https://www.linkedin.com/in/meet-dobariya-1a216a2b6/
