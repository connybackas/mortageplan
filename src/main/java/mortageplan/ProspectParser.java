package mortageplan;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author conny
 */
public class ProspectParser {

    private File file;
    private ArrayList<Prospect> prospects;
    private Pattern namePattern;
    private Pattern digitPattern;

    public ProspectParser(File file, Pattern namePattern, Pattern digitPattern) {
        this.file = file;
        this.namePattern = namePattern;
        this.digitPattern = digitPattern;
        prospects = new ArrayList<>();
    }

    private void extractProspect(Scanner line) {
        String name, totalLoan, interest, years;

        name = line.findInLine(namePattern);
        totalLoan = line.findInLine(digitPattern);
        interest = line.findInLine(digitPattern);
        years = line.findInLine(digitPattern);
        if (name == null || totalLoan == null || interest == null || years == null) {
            return;
        }

        prospects.add(new Prospect(name, Double.parseDouble(totalLoan),
                Double.parseDouble(interest), Double.parseDouble(years)));
    }

    public void parse() throws FileNotFoundException {

        Scanner lines = new Scanner(this.file);

        while (lines.hasNextLine()) {

            extractProspect(lines);
            lines.nextLine();

        }
        lines.close();
    }

    public ArrayList<Prospect> getProspects() {
        return prospects;
    }

}
