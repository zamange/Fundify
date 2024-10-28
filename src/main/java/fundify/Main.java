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

        // Declare AccountService outside the switch statement
        AccountService accountService = new AccountService();

        while (!response.equals("6")) { // Updated to 6 for closing
            // Output all available options to the user
            System.out.println("\nAvailable Features:");
            System.out.println("1: Mobile Banking and Digital Wallets");
            System.out.println("2: Deposit");
            System.out.println("3: Financial Literacy Programs");
            System.out.println("4: Digital Bookkeeping and Invoicing Tools");
            System.out.println("5: Transfer funds");
            System.out.println("6: Close");

            System.out.print("Please select an option (1-6): "); // Updated to 6

            response = scanner.nextLine();

            switch (response) {
                case "1":
                    System.out.print("Please enter your phone number for verification: ");
                    String phoneNumber = scanner.nextLine();
                    accountService.createSavingsAccount(phoneNumber);
                    break;

                case "2":
                    System.out.print("Please enter your phone number: ");
                    String userPhoneNumber = scanner.nextLine();

                    System.out.print("Please enter the amount to deposit: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character

                    accountService.depositFunds(userPhoneNumber, amount);
                    break;

                case "3":
                    accessFinancialLiteracyPrograms(); // Call to the financial literacy module
                    break;

                case "4":
                    BookkeepingHandler bookkeepingHandler = new BookkeepingHandler();
                    bookkeepingHandler.addBookkeepingRecord();
                    break;

                case "5":
                    System.out.print("Please enter your phone number: ");
                    String phoneNumberForTransfer = scanner.nextLine();
                    accountService.transferFunds(phoneNumberForTransfer); // Call transfer funds method
                    break;

                case "6":
                    System.out.println("Thank you for using Fundify!");
                    break;

                default:
                    System.out.println("Invalid selection. Please choose a valid option.");
                    break;
            }
        }

        scanner.close();
    }

    private static void accessFinancialLiteracyPrograms() {
        FinancialLiteracyModule.accessFinancialEducation();
    }
}
