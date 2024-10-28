package fundify.services;

public class SavingsAccount {
    private String accountNumber;
    private String userPhoneNumber;
    private double savingsGoal;
    private double balance; // Assuming balance is also a part of the SavingsAccount

    public SavingsAccount(String accountNumber, String userPhoneNumber, double savingsGoal) {
        this.accountNumber = accountNumber;
        this.userPhoneNumber = userPhoneNumber;
        this.savingsGoal = savingsGoal;
        this.balance = 0.0; // Initialize balance to 0 or a default value
    }

    // Getters and setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
