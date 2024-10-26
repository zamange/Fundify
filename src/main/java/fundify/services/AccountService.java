package fundify.services;

import fundify.UserDAO;
import fundify.services.SavingsAccount;

import java.util.Scanner;

public class AccountService {
    private UserDAO userDAO;
    private Scanner scanner;

    public AccountService() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }

    public void createSavingsAccount(String userPhoneNumber) {
        // Simulate identity verification
        if (verifyIdentity(userPhoneNumber)) {
            String accountNumber = generateAccountNumber();
            SavingsAccount account = new SavingsAccount(accountNumber, userPhoneNumber);

            // Save the account to the database
            if (userDAO.updateUserWithSavingsAccount(userPhoneNumber, account)) {
                System.out.println("Savings account created and saved successfully!");
                System.out.println("Account Number: " + account.getAccountNumber());
                setSavingsGoal(account);
            } else {
                System.out.println("Failed to save savings account to the database.");
            }
        } else {
            System.out.println("Identity verification failed. Account creation aborted.");
        }
    }

    private boolean verifyIdentity(String userPhoneNumber) {
        // Simulate identity verification process (this could be more complex)
        return true; // Assume verification is successful for this example
    }

    private String generateAccountNumber() {
        // Generate a unique account number (simplified for this example)
        return "SAV" + System.currentTimeMillis(); // Use current time as a simple unique ID
    }

    private void setSavingsGoal(SavingsAccount account) {
        System.out.print("Enter your savings goal: ");
        double goal = scanner.nextDouble();
        account.setSavingsGoal(goal);
        System.out.println("Savings goal set to: R" + goal);
    }
}
