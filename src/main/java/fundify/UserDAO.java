package fundify;

import java.sql.*;

public class UserDAO {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "phone_number TEXT NOT NULL UNIQUE,"
            + "account_balance REAL DEFAULT 0.0"
            + ");";

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

    // Method to check if a user with the given phone number already exists
    private boolean userExists(String phoneNumber) {
        String CHECK_USER_SQL = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USER_SQL)) {

            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to add a user to the database
    public boolean addUser(User user) {
        if (user.getName().isEmpty() || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Name and phone number are required");
        }
        if (!user.getPhoneNumber().matches("\\d+")) { // Basic numeric check
            throw new IllegalArgumentException("Phone number must be numeric");
        }

        if (userExists(user.getPhoneNumber())) {
            return true; // User already exists, so treat as success
        }

        String INSERT_USER_SQL = "INSERT INTO users (name, phone_number, account_balance) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setDouble(3, user.getAccountBalance());
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
