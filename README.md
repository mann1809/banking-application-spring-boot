# Banking System - Spring Boot

**Backend Banking Application** | Spring Boot + JWT + Docker

A complete banking system with secure authentication, account management, and transaction processing. Demonstrates
modern Java development practices and production-ready architecture.

## What This Project Shows

- **Backend Development**: Spring Boot REST API with comprehensive banking operations
- **Security Implementation**: JWT authentication with role-based access (Admin/Customer)
- **Database Design**: PostgreSQL with JPA/Hibernate for transaction management
- **DevOps Skills**: Docker containerization and multi-service orchestration
- **Clean Architecture**: Layered design with controllers, services, and repositories

## Core Features

**User Management**

- Secure registration and login system
- JWT token-based authentication
- Admin and Customer role separation

**Banking Operations**

- Account balance checking
- Fund transfers between accounts
- Complete transaction history
- Chequebook request system with admin approval

**Admin Dashboard**

- User account creation and management
- Transaction monitoring across all accounts
- Chequebook request approval workflow

## Technology Stack

- **Java 21** - Latest LTS version
- **Spring Boot 3.5.5** - Modern framework for rapid development
- **Spring Security + JWT** - Secure authentication system
- **PostgreSQL** - Relational database with JPA/Hibernate
- **Docker** - Containerized deployment
- **Maven** - Build and dependency management

## Quick Start

**Option 1: Docker (Recommended)**

```bash
mvn clean package
docker-compose up --build
```

Access at: `http://localhost:8083`

**Option 2: Local Development**

```bash
mvn spring-boot:run
```

Requires: Java 21, PostgreSQL on localhost:5432

## API Endpoints

**Authentication**

- `POST /auth/signup` - User registration
- `POST /auth/login` - User authentication

**Customer Operations**

- `GET /api/customer/balance` - Check account balance
- `POST /api/customer/transferFunds` - Transfer money
- `GET /api/customer/transactionHistory` - View transactions
- `POST /api/customer/chequebook` - Request chequebook

**Admin Operations**

- `POST /api/admin/addAccount` - Create user accounts
- `GET /api/admin/transactionHistory?username={username}` - View any user's transactions
- `GET /api/admin/checkBalance?username={username}` - Check any user's balance
- `GET /api/admin/pendingRequests` - View chequebook requests
- `GET /api/admin/approveRequests?username={username}` - Approve requests

## Project Structure

```
src/main/java/com/banking/app/
├── controller/          # REST controllers
├── dto/                # Data Transfer Objects
├── entity/             # JPA entities
├── repository/         # Data repositories
└── service/            # Business logic services
```

## Architecture Highlights

- **Layered Architecture**: Controllers → Services → Repositories
- **JWT Security**: Stateless authentication with role-based access
- **Database Integration**: JPA entities with PostgreSQL
- **Docker Ready**: Multi-container setup with persistent data
- **RESTful Design**: Clean API endpoints following REST principles