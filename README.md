# Banking System - Spring Boot Project

A comprehensive banking system built with Spring Boot, featuring user authentication, account management, and
transaction processing.

## Features

### Authentication & Authorization

- User registration and login
- JWT-based authentication
- Role-based access control (Admin/Customer)

### Customer Features

- View account balance
- Transfer funds between accounts
- View transaction history
- Request chequebook

### Admin Features

- Create new user accounts
- View any user's transaction history
- Check account balances for any user
- View and approve chequebook requests

## Technology Stack

- **Framework**: Spring Boot 3.5.5
- **Language**: Java 21
- **Database**: PostgreSQL
- **Security**: Spring Security with JWT
- **ORM**: Spring Data JPA with Hibernate
- **Build Tool**: Maven

## Dependencies

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- Spring Boot Starter Security
- PostgreSQL Driver
- JWT (JSON Web Token) Library
- Lombok for boilerplate code reduction

## Prerequisites

- Java 21 or higher
- PostgreSQL database
- Maven

## Database Configuration

The application is configured to connect to a PostgreSQL database:

- **Host**: localhost
- **Port**: 5432
- **Database**: banking-system
- **Username**: postgres

Update the database credentials in `src/main/resources/application.yaml` as needed.

## Running the Application

1. Ensure PostgreSQL is running and the database `banking-system` exists
2. Clone the repository
3. Navigate to the project directory
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. The application will start on port 8082

## API Endpoints

### Authentication

- `POST /auth/signup` - Register a new user
- `POST /auth/login` - User login

### Customer Operations

- `GET /api/customer/balance` - Get account balance
- `POST /api/customer/transferFunds` - Transfer funds
- `GET /api/customer/transactionHistory` - Get transaction history
- `POST /api/customer/chequebook` - Request chequebook

### Admin Operations

- `POST /api/admin/addAccount` - Create new user account
- `GET /api/admin/transactionHistory?username={username}` - Get user transaction history
- `GET /api/admin/checkBalance?username={username}` - Get user account balance
- `GET /api/admin/pendingRequests` - Get pending chequebook requests
- `GET /api/admin/approveRequests?username={username}` - Approve chequebook request

## Project Structure

```
src/main/java/com/banking/app/
├── controller/          # REST controllers
├── dto/                # Data Transfer Objects
├── entity/             # JPA entities
├── repository/         # Data repositories
└── service/            # Business logic services
```

## Security

The application uses JWT tokens for authentication. The JWT secret is configured in the application.yaml file. Make sure
to use a secure secret key in production environments.