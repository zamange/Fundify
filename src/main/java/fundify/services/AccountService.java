package fundify.services;

import fundify.UserDAO;
import fundify.models.Transaction;
import fundify.services.SavingsAccount;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

import static fundify.UserDAO.DB_URL;

public class AccountService {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    private UserDAO userDAO;
    private Scanner scanner;

    public AccountService() {
        this.userDAO = new UserDAO();
        this.scanner = new Scanner(System.in);
    }

    public SavingsAccount getSavingsAccount(String userPhoneNumber) {
        return userDAO.getSavingsAccountByUserPhoneNumber(userPhoneNumber);
    }

    public void createSavingsAccount(String userPhoneNumber) {
        // Simulate identity verification
        if (verifyIdentity(userPhoneNumber)) {
            String accountNumber = generateAccountNumber();
            System.out.print("Enter your savings goal: ");
            double savingsGoal = scanner.nextDouble();
            SavingsAccount account = new SavingsAccount(accountNumber, userPhoneNumber, savingsGoal);

            // Save the account to the database
            if (userDAO.updateUserWithSavingsAccount(userPhoneNumber, account)) {
                System.out.println("Welcome!");
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Savings goal set to: R" + account.getSavingsGoal());
            } else {
                System.out.println("Failed to save savings account to the database.");
            }
        } else {
            System.out.println("Identity verification failed. Account creation aborted.");
        }
    }

    private boolean verifyIdentity(String userPhoneNumber) {
        return true; // Assume verification is successful for this example
    }

    private String generateAccountNumber() {
        return "SAV" + System.currentTimeMillis(); // Use current time as a simple unique ID
    }

    private void setSavingsGoal(SavingsAccount account) {
        System.out.print("Enter your savings goal: ");
        double goal = scanner.nextDouble();
        account.setSavingsGoal(goal);
        System.out.println("Savings goal set to: R" + goal);
    }

    public void depositFunds(String userPhoneNumber, double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount. Please enter a positive value.");
            return;
        }

        // Get the user's savings account
        SavingsAccount account = userDAO.getSavingsAccountByUserPhoneNumber(userPhoneNumber);
        if (account == null) {
            System.out.println("Account not found for this phone number.");
            return;
        }

        // Update the balance
        double newBalance = account.getBalance() + amount;
        if (userDAO.updateAccountBalance(userPhoneNumber, newBalance)) {
            account.setBalance(newBalance); // Update the local account balance
            Transaction transaction = new Transaction(generateTransactionId(), userPhoneNumber, amount);
            userDAO.saveTransaction(transaction);

            System.out.println("Successfully deposited R" + amount + " into your account.");
        } else {
            System.out.println("Failed to update the account balance in the database.");
        }
    }

    public void transferFunds(String userPhoneNumber) {
        System.out.print("Enter the supplier's account number: ");
        String supplierAccountNumber = scanner.nextLine();

        System.out.print("Enter the amount to transfer: ");
        double amount = scanner.nextDouble();

        double currentBalance = getUserBalance(userPhoneNumber);
        if (currentBalance < amount) {
            System.out.println("Insufficient funds. Transfer cannot be completed.");
            return;
        }

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
        // Get the current balance of the user
        double currentBalance = getUserBalance(userPhoneNumber);

        // Update balance: Deduct the amount being transferred
        double newBalance = currentBalance - amount;

        // Update the balance in the database
        if (userDAO.updateAccountBalance(userPhoneNumber, newBalance)) {
            // Create and save the transaction
            Transaction transaction = new Transaction(generateTransactionId(), userPhoneNumber, amount);
            userDAO.saveTransaction(transaction);
        } else {
            System.out.println("Failed to update account balance during transfer.");
        }
    }

    private double getUserBalance(String userPhoneNumber) {
        String SELECT_BALANCE_SQL = "SELECT account_balance FROM users WHERE phone_number = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BALANCE_SQL)) {

            preparedStatement.setString(1, userPhoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getDouble("account_balance");
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve user balance.");
            e.printStackTrace();
        }
        return 0.0; // Return 0 if balance couldn't be retrieved
    }

    private String generateTransactionId() {
        return "TX" + System.currentTimeMillis();
    }

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
