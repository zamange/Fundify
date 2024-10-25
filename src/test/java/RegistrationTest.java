//import static org.junit.jupiter.api.Assertions.*;
//
//import fundify.User;
//import fundify.UserDAO;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.BeforeEach;
////import fundify.UserDAO.java;
//
//public class RegistrationTest {
//    private UserDAO userDAO;
//
//    @BeforeEach
//    public void setUp() {
//        // Set up database connection or a mock UserDAO instance.
//        userDAO = new UserDAO();
//    }
//
//    @Test
//    public void testUserRegistrationSuccess() {
//        User user = new User("Alice", "1234567890");
//
//        boolean isRegistered = userDAO.addUser(user);
//
//        assertTrue(isRegistered, "User should be successfully registered and saved to the database.");
//
//        // Additional assertions can be added to verify that the user exists in the database.
//    }
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
////    @org.junit.Test
////    @Test
////    public void testUserRegistrationWithInvalidPhoneNumber() {
////        User user = new User("Alice", "abc123");
////
////        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////            userDAO.addUser(user);
////        });
////
////        String expectedMessage = "Phone number must be numeric";
////        String actualMessage = exception.getMessage();
////
////        assertEquals(expectedMessage, actualMessage, "Invalid phone number should throw an exception.");
////    }
//}
