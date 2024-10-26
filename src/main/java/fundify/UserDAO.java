package fundify;

import fundify.services.SavingsAccount;

import java.sql.*;

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
;

    // Constructor to create the database and table if it doesn't exist
    public UserDAO() {
        createDatabase();
    }

    // Method to create the database and users table
    public void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection connection = DriverManager.getConnection(DB_URL);
                 Statement statement = connection.createStatement()) {

                statement.execute(CREATE_TABLE_SQL);
                System.out.println("Database and users table created successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
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
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }

    // Method to update user with savings account information (if you already have this)
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
            e.printStackTrace();
            return false;
        }
    }
}
