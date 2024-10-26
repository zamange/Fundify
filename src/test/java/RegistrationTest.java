//import static org.junit.jupiter.api.Assertions.*;
//
//import fundify.User;
//import fundify.UserDAO;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class RegistrationTest {
//    private UserDAO userDAO;
//
//    @BeforeEach
//    public void setUp() {
//        // Set up database connection or a mock UserDAO instance.
//        userDAO = new UserDAO();
//        userDAO.createDatabase(); // Ensure the database is created before tests
//    }
//
//    @Test
//    public void testUserRegistrationSuccess() {
//        User user = new User("Alice", "1234567890");
//
//        boolean isRegistered = userDAO.addUser(user);
//
//        assertTrue(isRegistered, "User should be successfully registered or already exists in the database.");
//
//        // Additional verification can be done here if needed
//    }
//
//
//    @Test
//    public void testUserRegistrationWithEmptyFields() {
//        User user = new User("", "");
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            userDAO.addUser(user);
//        });
//
//        String expectedMessage = "Name and phone number are required";
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage, "Empty fields should throw an exception with a specific message.");
//    }
//
//    @Test
//    public void testUserRegistrationWithInvalidPhoneNumber() {
//        User user = new User("Alice", "abc123");
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            userDAO.addUser(user);
//        });
//
//        String expectedMessage = "Phone number must be numeric";
//        String actualMessage = exception.getMessage();
//
//        assertEquals(expectedMessage, actualMessage, "Invalid phone number should throw an exception.");
//    }
//}
