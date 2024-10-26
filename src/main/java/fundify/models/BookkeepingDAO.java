package fundify.models;

import fundify.models.BookkeepingRecord;

import java.sql.*;

public class BookkeepingDAO {
    private static final String DB_URL = "jdbc:sqlite:Bookkeeping.db";
    
    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS bookkeeping ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "type TEXT NOT NULL,"
            + "description TEXT NOT NULL,"
            + "amount REAL NOT NULL"
            + ");";

    // Constructor to create the database and table if it doesn't exist
    public BookkeepingDAO() {
        createDatabase();
    }

    // Method to create the bookkeeping table
    private void createDatabase() {
        try (Connection connection = DriverManager.getConnection(DB_URL);
             Statement statement = connection.createStatement()) {
            statement.execute(CREATE_TABLE_SQL);
            System.out.println("Bookkeeping table created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a bookkeeping record
    public boolean addRecord(BookkeepingRecord record) {
        String INSERT_RECORD_SQL = "INSERT INTO bookkeeping (type, description, amount) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD_SQL)) {
            preparedStatement.setString(1, record.getType());
            preparedStatement.setString(2, record.getDescription());
            preparedStatement.setDouble(3, record.getAmount());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Return true if record was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if there was an error
        }
    }
}
