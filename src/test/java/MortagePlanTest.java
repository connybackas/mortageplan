package mortageplantests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import mortageplan.MortgageCalculation;
import mortageplan.Prospect;
import mortageplan.ProspectParser;
import org.junit.After;

import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Conny
 */
public class MortagePlanTest {

    File file;
    Pattern namePattern = Pattern.compile("\\\"([^\\\"]*)\\\"|[A-Za-zäåöÄÅÖ]*\\s?[A-Za-zäåöÄÅÖ]*");
    Pattern digitPattern = Pattern.compile("\\d*\\.*\\d+");

    @Before
    public void createEmptyFile() {
        try {
            file = File.createTempFile("test", ".txt");
        } catch (IOException ex) {
            System.out.println("Could not create the tmp.txt file for tests: " + ex);
            Logger.getLogger(MortagePlanTest.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Exiting....");
            System.exit(0);
        }
    }

    @After
    public void deleteTmpFile() {
        this.file.delete();
    }

    /**
     * Test of MortgageCalculation class.
     */
    @org.junit.Test
    public void testPayBackSumShouldBeSameOrHigher() {
        Prospect p = new Prospect("Doris", 1000, 5, 2);
        MortgageCalculation m = new MortgageCalculation(p);
        double result = m.getFixedMonthlyPayment();
        double payBackSum = result * p.getYears() * 12;

        assertTrue(payBackSum >= p.getTotalLoan());
    }

    /**
     * Test of MortgageCalculation class.
     */
    @org.junit.Test
    public void testGetFixedMonthlyPayment() {
        Prospect p = new Prospect("Börje", 2500, 7, 1);
        MortgageCalculation m = new MortgageCalculation(p);
        double result = m.getFixedMonthlyPayment();
        Double expResult = 216.32;

        assertEquals(expResult, result, 0.01);
    }

    /**
     * Test of ProspectParser class.
     */
    @org.junit.Test
    public void testProspectParser() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);

            //Should be added to prospect list
            writer.println("Börje,30000,4,3");
            //Should not be added to prospect list
            writer.println("Bamse,5000,fyra,3.");
            //Should not be added to prospect list
            writer.println("Andersson,f,2");
            //Should be added to prospect list
            writer.println("Doris,40000,5,2");

            writer.close();
            ProspectParser p = new ProspectParser(file, namePattern, digitPattern);
            p.parse();
            int size = p.getProspects().size();

            assertEquals(2, size);
        } catch (FileNotFoundException ex) {
            String failStr = "Failed to create file writer: " + ex;
            Logger.getLogger(MortagePlanTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(failStr);
        }
    }

    /**
     * Test of ProspectParser class.
     */
    @org.junit.Test(expected = FileNotFoundException.class)
    public void testProspectParserFileNotFound() throws FileNotFoundException {

        ProspectParser p = new ProspectParser(new File("TjollahoppTjollahej.asd"), namePattern, digitPattern);
        p.parse();

    }

}
