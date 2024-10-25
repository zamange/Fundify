package fundify;

public class User {
    private String name;
    private String phoneNumber;
    private double accountBalance; // Added account balance field

    // Constructor
    public User(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.accountBalance = 0.0; // Default balance
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }
}
