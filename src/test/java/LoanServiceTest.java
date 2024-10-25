import fundify.models.Account;
import fundify.models.Loan;
import fundify.models.Transaction;
import fundify.services.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class LoanServiceTest {

    private LoanService loanService;
    private Account testAccount;

    @BeforeEach
    public void setup() {
        loanService = new LoanService();

        testAccount = new Account("sshabalala123", "Business");

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("2023-01-15", 5000));
        transactions.add(new Transaction("2023-02-20", 3000));
        testAccount.setTransactionHistory(transactions);
    }

    @Test
    public void testApplyForMicroloan() {
        // Step 1: Apply for a loan using LoanService
        Loan loanEligibility = loanService.applyForLoan(testAccount);

        // Step 2: Validate the maximum loan amount
        assertNotNull(loanEligibility.getMaxAmount(), "Maximum loan amount should be displayed.");
        assertTrue(loanEligibility.getMaxAmount() > 0, "Maximum loan amount should be positive.");

        // Step 3: Validate that repayment options are available
        assertNotNull(loanEligibility.getRepaymentOptions(), "Repayment options should be displayed.");
        assertTrue(loanEligibility.getRepaymentOptions().contains("6 months"), "Repayment options should include '6 months'.");

        // Step 4: Validate that interest rate is correctly calculated and displayed
        assertNotNull(loanEligibility.getInterestRate(), "Interest rate should be displayed based on the account profile.");
        assertTrue(loanEligibility.getInterestRate() > 0, "Interest rate should be positive.");
    }
}
    
    
