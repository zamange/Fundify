package fundify.services;

import fundify.UserDAO;
import fundify.models.Transaction;
import fundify.services.SavingsAccount;

import java.util.List;
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

    public void transferFunds(String userPhoneNumber) {
        System.out.print("Enter the supplier's account number: ");
        String supplierAccountNumber = scanner.nextLine();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        // Check if the user has sufficient funds
        double currentBalance = getUserBalance(userPhoneNumber);
        if (currentBalance < amount) {
            System.out.println("Insufficient funds. Transfer cannot be completed.");
            return;
        }

        // Prompt for confirmation
        System.out.println("You are about to transfer R" + amount + " to " + supplierAccountNumber + ". Do you want to proceed? (yes/no)");
        scanner.nextLine(); // Clear the buffer
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {
            processTransfer(userPhoneNumber, supplierAccountNumber, amount);
            System.out.println("Transfer completed successfully.");
        } else {
            System.out.println("Transfer canceled.");
        }
    }

    private void processTransfer(String userPhoneNumber, String supplierAccountNumber, double amount) {
        // Deduct the amount from the user's balance
        updateBalance(userPhoneNumber, -amount);

        // Create a transaction receipt
        Transaction transaction = new Transaction(generateTransactionId(), userPhoneNumber, amount);

        // Save the transaction to the database
        userDAO.saveTransaction(transaction);
    }

    private double getUserBalance(String userPhoneNumber) {
        // Implement logic to retrieve the user's current balance from the database
        return 0.0; // Placeholder: replace with actual balance retrieval
    }

    private void updateBalance(String userPhoneNumber, double amount) {
        // Implement logic to update the user's balance in the database
    }

    private String generateTransactionId() {
        // Generate a unique transaction ID
        return "TX" + System.currentTimeMillis();
    }

    // Method to transfer funds to suppliers
    public boolean transferFunds(String userPhoneNumber, String supplierAccountNumber, double amount) {
        // You can implement balance checks and necessary validations here
        // For demonstration, we'll directly save the transaction
        Transaction transaction = new Transaction(userPhoneNumber, supplierAccountNumber, amount);
        boolean transactionSaved = userDAO.saveTransaction(transaction);

        if (transactionSaved) {
            System.out.println("Funds transferred successfully!");
            return true;
        } else {
            System.out.println("Failed to transfer funds. Please try again.");
            return false;
        }
    }

    // Method to get transaction history for a user
    public void getTransactionHistory(String userPhoneNumber) {
        List<Transaction> transactions = userDAO.getTransactionsByUserPhoneNumber(userPhoneNumber);

        if (transactions.isEmpty()) {
            System.out.println("No transactions found for this user.");
            return;
        }

        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction); // Assuming Transaction has a proper toString() method
        }
    }
}
