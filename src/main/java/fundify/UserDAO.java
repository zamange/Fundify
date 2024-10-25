package fundify;

import java.sql.*;

public class UserDAO {
    public void createDatabase() {
        try {
            // Load the SQLite JDBC driver
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

    private static final String DB_URL = "jdbc:sqlite:users.db"; // Update with your database URL
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS users ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "name TEXT NOT NULL,"
            + "phone_number TEXT NOT NULL UNIQUE,"
            + "account_balance REAL DEFAULT 0.0"
            + ");";

    // Method to create the database and users table
//    public void createDatabase() {
//        try (Connection connection = DriverManager.getConnection(DB_URL);
//             Statement statement = connection.createStatement()) {
//
//            statement.execute(CREATE_TABLE_SQL);
//            System.out.println("Database and users table created successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
////        }
//    }

    // Method to add a user to the database
    public boolean addUser(User user) {
        String INSERT_USER_SQL = "INSERT INTO users (name, phone_number, account_balance) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setDouble(3, user.getAccountBalance());
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0; // Return true if user was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false in case of an error
        }
    }
}
