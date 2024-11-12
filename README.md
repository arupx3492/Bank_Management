# GreatLearningAssignmentApplication

## Overview
This Spring Boot application is designed to manage various banking functionalities, including customer and admin operations, such as managing user accounts, loan applications, and chat functionality between users and admins. The application is secured using JWT-based authentication, with different role-based access for customers and admins.

## Features
- **User Authentication**: JWT-based authentication with Spring Security, supporting roles for `ADMIN` and `CUSTOMER`.
- **Account Management**: Users Can Deposit, Withdraw, transfer funds, and apply for a Loan.
- **Loan Management**: Admins can approve or deny loans based on customer balance.
- **Chat Functionality**: Allows communication between `CUSTOMER` and `ADMIN`.
- **REST API Documentation**: API documentation is available through Swagger UI.

## Technologies Used
- **Spring Boot**: Core application framework.
- **Spring Security**: For authentication and role-based authorization.
- **JWT**: JSON Web Token for secure user authentication.
- **Spring Data JPA**: For ORM and database interaction.
- **MySQL**: Database for storing application data.
- **Swagger**: API documentation and testing tool.

## Setup and Configuration

### Prerequisites
- Java 17 
- Maven
- MySQL

### Database Configuration
Configure the database properties in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

## Swagger UI Configuration

Swagger documentation is set up using the `springdoc-openapi` library. To access Swagger UI, visit:


Swagger UI allows you to test all available endpoints in the application.

## Security Configuration

The application uses JWT for secure access to APIs. Only authenticated users with valid tokens can access specific resources. The roles and accessible endpoints are:

- `/auth/**`: Publicly accessible for authentication (login).
- `/admin/**`: Accessible only by users with the `ADMIN` role.
- `/customer/**`: Accessible only by users with the `CUSTOMER` role.
- `/swagger-ui/**` and `/v3/api-docs/**`: Publicly accessible for API documentation.

