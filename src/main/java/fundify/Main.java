package fundify;

import fundify.services.AccountService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        userDAO.createDatabase(); // Create database and users table

        RegistrationService registrationService = new RegistrationService();
        registrationService.registerUser(); // Register a user

        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to create a savings account? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            AccountService accountService = new AccountService();
            System.out.print("Please enter your phone number for verification: ");
            String phoneNumber = scanner.nextLine();
            accountService.createSavingsAccount(phoneNumber);
        } else {
            System.out.println("Thank you for using Fundify!");
        }

        scanner.close();
    }
}
