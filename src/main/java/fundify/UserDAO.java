package fundify;

public class UserDAO {
    public boolean addUser(User user) {
        if (user.getName().isEmpty() || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Name and phone number are required");
        }

        if (!user.getPhoneNumber().matches("\\d+")) {
            throw new IllegalArgumentException("Phone number must be numeric");
        }

        // Code to save the user to the database
        // Assuming you’re using JDBC to connect to SQLite
        // Return true if the insertion is successful
        return true;
    }
}
