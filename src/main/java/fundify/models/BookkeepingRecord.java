package fundify.models;

public class BookkeepingRecord {
    private String type;        // Type of record (Income/Expense)
    private String description; // Description of the record
    private double amount;      // Amount of money involved in the record

    // Constructor
    public BookkeepingRecord(String type, String description, double amount) {
        this.type = type;
        this.description = description;
        this.amount = amount;
    }

    // Getters
    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }
}
