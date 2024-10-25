package fundify;

import java.util.Scanner;

public class RegistrationService {
    private UserDAO userDAO;
    private Scanner scanner;

    public RegistrationService() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }

    public void registerUser() {
        // Prompt user for name and phone number
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();

        // Validate input (basic example)
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Name and phone number cannot be empty.");
            return;
        }

        // Create a new User instance
        User newUser = new User(name, phoneNumber);

        // Add user to the database
        boolean isRegistered = userDAO.addUser(newUser);

        if (isRegistered) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Failed to register user.");
        }
    }
}
