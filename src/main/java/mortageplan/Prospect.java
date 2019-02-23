package mortageplan;

/**
 *
 * @author conny
 */
public class Prospect {

    private String name;
    private double interestPercantage;
    private double years;
    private double totalLoan;

    public Prospect(String name, double totalLoan, double interest, double years) {
        this.name = name;
        this.interestPercantage = interest/100;
        this.years = years;
        this.totalLoan = totalLoan;
    }

    public String getName() {
        return name;
    }

    public double getInterest() {
        return interestPercantage;
    }

    public double getYears() {
        return years;
    }

    public double getTotalLoan() {
        return totalLoan;
    }








}
