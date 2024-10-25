package fundify.services;

import fundify.models.Account;
import fundify.models.Loan;
import fundify.models.Transaction;

import java.util.List;

public class LoanService {

    public Loan applyForLoan(Account account) {
        // Calculate the maximum loan amount based on the account's transaction history
        double maxAmount = calculateMaxAmount(account);

        // Determine the repayment options based on the account's transaction history
        String repaymentOptions = calculateRepaymentOptions(account);

        // Calculate the interest rate based on the account's transaction history
        double interestRate = calculateInterestRate(account);

        // Return a new Loan object with the calculated values
        return new Loan(maxAmount, repaymentOptions, interestRate);
    }

    // Helper method to calculate the maximum loan amount
    private double calculateMaxAmount(Account account) {
        // For example, let's assume the maximum loan amount is 10 times the average transaction amount
        double averageTransactionAmount = calculateAverageTransactionAmount(account);
        return averageTransactionAmount * 10;
    }

    // Helper method to calculate the average transaction amount
    private double calculateAverageTransactionAmount(Account account) {
        List<Transaction> transactions = account.getTransactionHistory();
        double sum = 0;
        for (Transaction transaction : transactions) {
            sum += transaction.getAmount();
        }
        return sum / transactions.size();
    }

    // Helper method to determine the repayment options
    private String calculateRepaymentOptions(Account account) {
        // For example, let's assume the repayment options are "6 months" or "12 months" based on the account type
        if (account.getAccountType().equals("Business")) {
            return "6 months";
        } else {
            return "12 months";
        }
    }

    // Helper method to calculate the interest rate
    private double calculateInterestRate(Account account) {
        // For example, let's assume the interest rate is 5% for business accounts and 10% for personal accounts
        if (account.getAccountType().equals("Business")) {
            return 0.05;
        } else {
            return 0.10;
        }
    }

}
    
