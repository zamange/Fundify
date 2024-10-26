package fundify;

import fundify.models.Transaction;
import fundify.services.SavingsAccount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "phone_number TEXT NOT NULL UNIQUE,"
            + "account_balance REAL DEFAULT 0.0,"
            + "savings_account_number TEXT,"
            + "savings_goal REAL DEFAULT 0.0"
            + ");";

    private static final String CREATE_TRANSACTIONS_TABLE_SQL = "CREATE TABLE IF NOT EXISTS transactions ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "user_phone_number TEXT NOT NULL,"
            + "supplier_account_number TEXT NOT NULL,"
            + "amount REAL NOT NULL,"
            + "timestamp TIMESTAMP NOT NULL"
            + ");";

    // Constructor to create the database and tables if they don't exist
    public UserDAO() {
        createDatabase();
    }

    // Method to create the database and users and transactions tables
    public void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()) {

                // Create users table
                statement.execute(CREATE_TABLE_SQL);
                // Create transactions table
                statement.execute(CREATE_TRANSACTIONS_TABLE_SQL);
                System.out.println("Database and tables created successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Failed to create database or tables.");
            e.printStackTrace();
        }
    }

    // Method to add a user to the database
    public boolean addUser(User user) {
        String INSERT_USER_SQL = "INSERT INTO users (name, phone_number) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Return true if user was added successfully
        } catch (SQLException e) {
            System.out.println("Failed to add user.");
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    // Method to update user with savings account information
    public boolean updateUserWithSavingsAccount(String phoneNumber, SavingsAccount account) {
        String UPDATE_USER_SQL = "UPDATE users SET savings_account_number = ?, savings_goal = ? WHERE phone_number = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {

            preparedStatement.setString(1, account.getAccountNumber());
            preparedStatement.setDouble(2, account.getSavingsGoal());
            preparedStatement.setString(3, phoneNumber);
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Failed to update user savings account.");
            e.printStackTrace();
            return false;
        }
    }

    // Method to save a transaction
    public boolean saveTransaction(Transaction transaction) {
        String INSERT_TRANSACTION_SQL = "INSERT INTO transactions (user_phone_number, supplier_account_number, amount, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION_SQL)) {

            preparedStatement.setString(1, transaction.getUserPhoneNumber());
            preparedStatement.setString(2, transaction.getSupplierAccountNumber());
            preparedStatement.setDouble(3, transaction.getAmount());
            preparedStatement.setTimestamp(4, new Timestamp(transaction.getTimestamp().getTime())); // Store as SQL timestamp
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Return true if transaction was saved successfully
        } catch (SQLException e) {
            System.out.println("Failed to save transaction.");
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
    public List<Transaction> getTransactionsByUserPhoneNumber(String phoneNumber) {
        String SELECT_TRANSACTIONS_SQL = "SELECT * FROM transactions WHERE user_phone_number = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTIONS_SQL)) {

            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String userPhone = resultSet.getString("user_phone_number");
                String supplierAccount = resultSet.getString("supplier_account_number");
                double amount = resultSet.getDouble("amount");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                transactions.add(new Transaction(userPhone, supplierAccount, amount, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
