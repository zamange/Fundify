package fundify;

import fundify.models.BookkeepingHandler;
import fundify.services.AccountService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        userDAO.createDatabase(); // Create database and users table

        RegistrationService registrationService = new RegistrationService();
        registrationService.registerUser(); // Register a user

        Scanner scanner = new Scanner(System.in);
        String response = "";

        while (!response.equals("5")) {
            // Output all available options to the user
            System.out.println("\nAvailable Features:");
            System.out.println("1: Mobile Banking and Digital Wallets");
            System.out.println("2: Microloan Platforms");
            System.out.println("3: Financial Literacy Programs");
            System.out.println("4: Digital Bookkeeping and Invoicing Tools");
            System.out.println("5: Close");
            System.out.print("Please select an option (1-5): ");

            response = scanner.nextLine();

            switch (response) {
                case "1":
                    AccountService accountService = new AccountService();
                    System.out.print("Please enter your phone number for verification: ");
                    String phoneNumber = scanner.nextLine();
                    accountService.createSavingsAccount(phoneNumber);
                    break;

                case "2":
                    // Microloan platform logic could go here
                    System.out.println("Microloan platform feature is not yet implemented.");
                    break;

                case "3":
                    FinancialLiteracyModule.accessFinancialEducation();
                    break;

                case "4":
                    BookkeepingHandler bookkeepingHandler = new BookkeepingHandler();
                    bookkeepingHandler.addBookkeepingRecord();
                    break;

                case "5":
                    System.out.println("Thank you for using Fundify!");
                    break;

                default:
                    System.out.println("Invalid selection. Please choose a valid option.");
                    break;
            }
        }

        scanner.close();
    }
}
