package fundify.models;


import java.util.List;

public class Account {

    private String username;
    private String accountType;
    private List<Transaction> transactionHistory;

    public Account(String username, String accountType) {
        this.username = username;
        this.accountType = accountType;
    }

    public String getUsername() {
        return username;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", accountType='" + accountType + '\'' +
                '}';
    }


}
