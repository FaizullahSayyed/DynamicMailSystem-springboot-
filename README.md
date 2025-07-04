# DynamicMailSystem Spring Boot

A Spring Boot application for sending emails to company employees, staff, executives, and customers, with user registration and action logging.

## Features

- **Send Emails:** Send emails to employees, staff, executives, or all groups via web interface.
- **User Registration:** Register users as employee, staff, executive, or customer.
- **Database Integration:** Uses PostgreSQL to store users and log email actions.
- **Email Logging:** All email actions are logged in the `email_actions` table.

## Technologies

- Java 17+
- Spring Boot
- Spring Mail
- Spring JDBC
- PostgreSQL

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/FaizullahSayyed/DynamicMailSystem-springboot-.git
cd DynamicMailSystem-springboot-
```

### 2. Configure Database

Edit `src/main/resources/application.properties` with your PostgreSQL credentials.

### 3. Create Database Tables

```sql
-- Employees
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Staff
CREATE TABLE staff (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    role VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Executives
CREATE TABLE executives (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    position VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Customers
CREATE TABLE customers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Email Actions
CREATE TABLE email_actions (
    id SERIAL PRIMARY KEY,
    recipient_group VARCHAR(50) NOT NULL,
    subject VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    sent_by VARCHAR(100),
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 4. Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

### 5. Usage

- Open [http://localhost:8081/](http://localhost:8081/) for the main page.
- Click "Register User" to add a new user.
- Use the buttons to send emails to different groups.

## License

This project is for educational/demo purposes.