package fundify.models;

import java.sql.Timestamp;

public class Transaction {
    private String userPhoneNumber;
    private String supplierAccountNumber;
    private double amount;
    private Timestamp timestamp;

    public Transaction(String userPhoneNumber, String supplierAccountNumber, double amount) {
        this.userPhoneNumber = userPhoneNumber;
        this.supplierAccountNumber = supplierAccountNumber;
        this.amount = amount;
        this.timestamp = new Timestamp(System.currentTimeMillis()); // Set current time
    }

    public Transaction(String userPhoneNumber, String supplierAccountNumber, double amount, Timestamp timestamp) {
        this.userPhoneNumber = userPhoneNumber;
        this.supplierAccountNumber = supplierAccountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
    }


//    public String getId() {
//        return id;
//    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public String getSupplierAccountNumber() {
        return supplierAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userPhoneNumber='" + userPhoneNumber + '\'' +
                ", supplierAccountNumber='" + supplierAccountNumber + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}

//
//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "date='" + date + '\'' +
//                ", amount=" + amount +
//                '}';
//    }
//}
