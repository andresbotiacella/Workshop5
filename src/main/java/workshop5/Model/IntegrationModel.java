package workshop5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class to manage the math operations used in the numerical integration
 * process
 *
 * @author Andy
 */
public class IntegrationModel {

    public final static double ZERO = 0;
    public final static double MIDDLE = 0.5;
    public final static double ERROR = 0.00001;

    
    public static ArrayList<String[]> getData(String fileName){
        
        ArrayList<String> data = new ArrayList<>();
        ArrayList<String[]> values = new ArrayList<>();
        // We will be using a try-with-resource block
        try (BufferedReader reader = new BufferedReader(
                new FileReader(fileName))) {
            // Access the data from the file
            // Create a new StringBuilder
            StringBuilder string = new StringBuilder();
            
            // Read line-by-line
            String line = reader.readLine();
            // While there comes a new line, execute this
            while(line != null) {
                // Add these lines to the String builder
                data.add(line);
                // Read the next line
                line = reader.readLine();
            }
        } catch (Exception er) {
            // Since there was an error, you probably want to notify the user
            // For that error. So return the error.
            data = new ArrayList<String>();
        }
        // Return the string read from the file
        for(String l : data)
        {
            String[] valueRow = l.split("-");
            values.add(valueRow);
        }
        return values;
    }
    
    /**
     * this method to calculate numerical integration of the t distribution
     *
     * @method calculateNumericalIntegrationP
     * @param t T Distribution
     * 
     * @return p numerical integration
     */
    public static double calculateNumericalIntegrationP(IntegrationDistribution t) {

        double p = (t.getWidthSegment() / 3) * 1 * functionX(t.getxValue() * 0, t.getDof());
        for (int i = 1; i < t.getNumSeg(); i++) {
            if (i % 2 == 0) {
                p = p + (t.getWidthSegment() / 3) * 2 * functionX(t.getWidthSegment() * i, t.getDof());
            } else {
                p = p + (t.getWidthSegment() / 3) * 4 * functionX(t.getWidthSegment() * i, t.getDof());
            }

        }
        p = p + (t.getWidthSegment() / 3) * 1 * functionX(t.getxValue(), t.getDof());
        return p;
    }

    /**
     * this method to calculate f(x) to use numerical integration process
     *
     * @method functionX
     * @param x
     * @param dof
     * @return f(x) result
     */
    public static double functionX(double x, int dof) {
        double fx;
        double x1 = 1 + ((Math.pow(x, 2)) / dof);
        double exp = (double) (dof + 1) / 2;
        x1 = Math.pow(x1, -exp);
        double x2 = gamaFucntion(exp) / (Math.pow(dof * Math.PI, MIDDLE) * gamaFucntion((double) dof / 2));
        fx = x2 * x1;
        return fx;
    }

    /**
     * this method to calculate gamma function of the integer number
     *
     * @method gamaFucntion
     * @param number
     * @return gama result
     */
    public static int gamaFunction(int number) {
        if (number > 0) {
            number--;
        }
        return (int) factorial(number);
    }

    /**
     * this method to calculate gamma function of the double number
     *
     * @method gamaFucntion
     * @param number
     * @return gama result
     */
    public static double gamaFucntion(double number) {
        if (number > 0) {
            number--;
        }
        return factorial(number);
    }

    /**
     * this method to calculate the factorial function of the number x!
     *
     * @method factorial
     * @param number
     * @return factorial result
     */
    public static double factorial(double number) {

        if (number == ZERO) {
            return 1.0;
        } else if (number == MIDDLE) {
            return number * Math.sqrt(Math.PI);
        } else {
            return number * factorial(number - 1);
        }

    }

}
