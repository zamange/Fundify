package fundify;

import fundify.models.BookkeepingHandler;
import fundify.services.AccountService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        userDAO.createDatabase(); // Create database and users table

        RegistrationService registrationService = new RegistrationService();
        registrationService.registerUser(); // Register a user0

        // Output all available options to the user
        System.out.println("Available Features:");
        System.out.println("1: Mobile Banking and Digital Wallets");
        System.out.println("2: Microloan Platforms");
        System.out.println("3: Financial Literacy Programs");
        System.out.println("4: Digital Bookkeeping and Invoicing Tools");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Would you like to create a savings account? (yes/no): ");
        String response = scanner.nextLine();

        if (response.equalsIgnoreCase("yes")) {
            AccountService accountService = new AccountService();
            System.out.print("Please enter your phone number for verification: ");
            String phoneNumber = scanner.nextLine();
            accountService.createSavingsAccount(phoneNumber);
        }

        // Bookkeeping feature
        BookkeepingHandler bookkeepingHandler = new BookkeepingHandler();
        System.out.print("Would you like to add a bookkeeping record? (yes/no): ");
        String bookkeepingResponse = scanner.nextLine();

        if (bookkeepingResponse.equalsIgnoreCase("yes")) {
            bookkeepingHandler.addBookkeepingRecord();
        }

        System.out.println("Thank you for using Fundify!");
        scanner.close();
    }
}
