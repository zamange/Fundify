package fundify.models;

public class Loan {

    private double maxAmount;
    private String repaymentOptions;
    private double interestRate;

    public Loan(double maxAmount, String repaymentOptions, double interestRate) {
        this.maxAmount = maxAmount;
        this.repaymentOptions = repaymentOptions;
        this.interestRate = interestRate;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public String getRepaymentOptions() {
        return repaymentOptions;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "maxAmount=" + maxAmount +
                ", repaymentOptions='" + repaymentOptions + '\'' +
                ", interestRate=" + interestRate +
                '}';
    }


}
