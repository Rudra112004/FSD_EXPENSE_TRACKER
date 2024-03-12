# Expense Tracker

Expense Tracker is a web application built using React, Spring Boot, MySQL, and Bootstrap. It allows users to manage their expenses by tracking transactions across multiple wallets.

## Features

- **Wallet Management**: Each user can create multiple wallets with different priorities.
- **Transaction Tracking**: Users can add transactions to any of their wallets, specifying date, type, category, and amount.
- **Filtering Transactions**: Users can filter transactions within a wallet by type, category, and date.
- **Dashboard**: Displays an overview of wallets, prioritized by their importance.

## Technologies Used

- **Frontend**:
  - React: A JavaScript library for building user interfaces.
  - Bootstrap: A front-end framework for developing responsive and mobile-first websites.
- **Backend**:
  - Spring Boot: An opinionated framework for building production-ready Spring applications.
  - MySQL: A relational database management system.
- **Others**:
  - Axios: A promise-based HTTP client for making AJAX requests.
  - Lombok: A Java library that helps reduce boilerplate code in models and DTOs.

## Installation

1. Clone the repository:

    ```sh
    git clone https://github.com/Rudra112004/FSD_EXPENSE_TRACKER.git
    ```

2. Set up the database:
   - Install MySQL and create a new database.
   - Update the `application.properties` file with your database configuration.

3. Run the backend server:
   - Navigate to the `wallet-wallet` directory.
   - Run the following command to start the Spring Boot application:

    ```sh
    cd wallet/wallet
    ./mvnw spring-boot:run
    ```

4. Run the frontend:
   - Navigate to the `expreact` directory.
   - Run the following commands to install dependencies and start the React application:

    ```sh
    cd expreact
    npm install
    npm start
    ```

5. Access the application:
   - Open your web browser and go to `http://localhost:3000`.

## Usage

1. Create wallets with different priorities.
2. Add transactions to your wallets, specifying date, type, category, and amount.
3. Filter transactions within a wallet by type, category, or date.
4. View the dashboard to see an overview of wallets, prioritized by their importance.

## Contributors

- [Rudra Patel](https://github.com/Rudra112004)


Feel free to contribute to the project by forking and submitting pull requests. If you encounter any issues or have suggestions for improvement, please open an issue in the repository. Thank you for using the Expense Tracker!