package fundify.models;

import fundify.models.BookkeepingRecord;

import java.util.Scanner;

public class BookkeepingHandler {
    private final BookkeepingDAO bookkeepingDAO;
    private final Scanner scanner;

    public BookkeepingHandler() {
        this.bookkeepingDAO = new BookkeepingDAO();
        this.scanner = new Scanner(System.in);
    }

    // Method to handle adding a new bookkeeping record
    public void addBookkeepingRecord() {
        System.out.print("Enter record type (Income/Expense): ");
        String type = scanner.nextLine();

        System.out.print("Enter a brief description: ");
        String description = scanner.nextLine();

        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Clear the newline character

        BookkeepingRecord record = new BookkeepingRecord(type, description, amount);
        if (bookkeepingDAO.addRecord(record)) {
            System.out.println("Record added successfully.");
        } else {
            System.out.println("Failed to add record.");
        }
    }
}
