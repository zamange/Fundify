package fundify.services;

public class SavingsAccount {
    private String accountNumber; // Unique account number
    private String userPhoneNumber; // Linked user
    private double savingsGoal; // Savings goal

    public SavingsAccount(String accountNumber, String userPhoneNumber) {
        this.accountNumber = accountNumber;
        this.userPhoneNumber = userPhoneNumber;
        this.savingsGoal = 0.0; // Default savings goal
    }

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
}
