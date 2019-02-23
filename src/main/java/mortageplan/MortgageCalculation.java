package mortageplan;

import java.text.DecimalFormat;

/**
 *
 * @author conny
 */
public class MortgageCalculation {

    private Prospect prospect;

    public MortgageCalculation(Prospect prospect) {
        this.prospect = prospect;

    }

    private double raiseValue(double value, int exponent) {
        double returnValue = value;

        for (int i = 1; i < exponent; i++) {
            returnValue *= value;
        }
        return returnValue;
    }

    public double getFixedMonthlyPayment() {

        /*Interest on a monthly basis*/
        double monthlyInterest = prospect.getInterest() / 12;
        /*Number of payments*/
        int numOfPayments = (int) (prospect.getYears() * 12);
        double value = raiseValue(1 + monthlyInterest, numOfPayments);

        return prospect.getTotalLoan() * (monthlyInterest * value) / (value - 1);
    }

}
