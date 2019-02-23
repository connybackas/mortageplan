package mortageplan;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*
* @author conny
*/
public class MortagePlan {

  /**
  * @param args the command line arguments
  */
  public static void main(String[] args) {

    if(args.length == 0){
      System.out.println("No arguments attached, exiting...");
      System.exit(0);
    }
    
    Pattern namePattern = Pattern.compile("\\\"([^\\\"]*)\\\"|[A-Za-zäåöÄÅÖ]*\\s?[A-Za-zäåöÄÅÖ]*");
    Pattern digitPattern = Pattern.compile("\\d*\\.*\\d+");

    ProspectParser parser = new ProspectParser(new File(args[0]),
    namePattern, digitPattern);

    try {
      parser.parse();

      DecimalFormat df = new DecimalFormat("####0.00");
      ArrayList<Prospect> prospects = parser.getProspects();

      System.out.println("********************************************************************************************************************");
      for (int i = 0; i < prospects.size(); i++) {
        Prospect p = prospects.get(i);

        System.out.print("Prospect " + (i + 1) + ": ");
        System.out.println(p.getName() + " wants to borrow " + df.format(p.getTotalLoan())
        + "€" + " for a period of " + prospects.get(i).getYears() + " years and pay "
        + df.format(new MortgageCalculation(p).getFixedMonthlyPayment())
        + "€ each month.");
        System.out.println("********************************************************************************************************************");
      }
    } catch (FileNotFoundException ex) {
      System.out.println("Failed to open file " + args[0] +": "+ ex);
      Logger.getLogger(MortagePlan.class.getName()).log(Level.SEVERE, null, ex);
      System.exit(0);
    }
  }
}
