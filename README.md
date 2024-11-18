# Bank Application  

## Overview  
The Bank Application is a Spring Boot-based project that provides a secure and feature-rich platform for managing user accounts, loans, and customer-admin interactions. This project incorporates layered architecture, role-based access control, and secure JWT authentication to deliver a seamless banking experience.  

---

## Features  

### **Admin Functionalities**  
1. **Account Management**  
   - Activate or deactivate user accounts.  
   - Deposit, withdraw, and transfer money between accounts.  

2. **Loan Management**  
   - Approve or deny loans based on user balances and minimum amount criteria.  
   - View all pending loan applications.  

3. **Messaging System**  
   - Send messages directly to users for communication.  

### **Customer Functionalities**  
1. **Account Management**  
   - Deposit and withdraw money.  
   - Transfer money to other accounts.  

2. **Loan Management**  
   - Apply for loans.  
   - Calculate EMI for desired loan amounts and terms.  

3. **Messaging System**  
   - Send messages to admins.  
   - View all received messages.  

### **Authentication and Authorization**  
- **Sign Up**: Register as a new user.  
- **Login**: Authenticate and receive a JWT token for secure access.  
- **Logout**: Logout a user.  

---

## API Endpoints  

### **Admin Endpoints (`/admin`)**  
- **Account Management**  
  - `PUT /activate/{userId}`: Activate a user account.  
  - `PUT /deactivate/{userId}`: Deactivate a user account.  
  - `PUT /deposit`: Deposit money into a user's account.  
  - `PUT /withdraw`: Withdraw money from a user's account.  
  - `PUT /transfer`: Transfer money between accounts.  

- **Loan Management**  
  - `PUT /loanApprove/{loanId}/{minAmount}`: Approve or deny a loan based on minimum balance.  
  - `GET /loans`: Retrieve all pending loan applications.  

- **Messaging**  
  - `POST /sendMessage/{userId}`: Send a message to a user.  

### **Authentication Endpoints (`/auth`)**  
- `POST /signup`: Register a new user.  
- `POST /login`: Login and receive a JWT token.  
- `POST /logout`: Logout and invalidate the JWT token.  

### **Customer Endpoints (`/customer`)**  
- **Account Management**  
  - `PUT /deposit/{amount}`: Deposit money into your account.  
  - `PUT /withdraw/{amount}`: Withdraw money from your account.  
  - `POST /transfer`: Transfer money to another account.  

- **Loan Management**  
  - `POST /applyLoan`: Apply for a loan.  
  - `GET /calculateEMI`: Calculate the EMI for a loan.  

- **Messaging**  
  - `POST /sendMessage`: Send a message to the admin.  
  - `GET /getMessages`: Retrieve all messages.  

---

## Technology Stack  

- **Backend Framework**: Spring Boot  
- **Authentication**: JWT with Spring Security  
- **Database**: MySQL  
- **API Testing**: Postman  

---

## How to Run  

1. **Clone the Repository**  
   ```bash
   https://github.com/arupx3492/Bank_Management.git
   cd BankingSystem
   ```  

2. **Configure the Database**  
   - Update the `application.properties` file with your MySQL database credentials.  

3. **Run the Application**  
   ```bash
   mvn spring-boot:run
   ```  

4. **Access the API**  
   - Use Postman to test the API endpoints.  
   - Swagger UI is available at `http://localhost:8080/swagger-ui/index.html`.  

---

## Future Enhancements  

- Add WebSocket-based real-time chat functionality.  
- Implement transaction history tracking.  
- Enhance the user interface with a frontend framework.  

--- 
