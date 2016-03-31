package workshop5.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Model Class including all the functional methods
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
     * Method to calculate the numerical integration
     *
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
     * Method to calculate the function
     *
     * @param x
     * @param dof
     * @return f(x) result
     */
    public static double functionX(double x, int dof) {
        double fx;
        double x1 = 1 + ((Math.pow(x, 2)) / dof);
        double exp = (double) (dof + 1) / 2;
        x1 = Math.pow(x1, -exp);
        double x2 = gammaFunction(exp) / (Math.pow(dof * Math.PI, MIDDLE) * gammaFunction((double) dof / 2));
        fx = x2 * x1;
        return fx;
    }

    /**
     * Method to calculate the gamma function for integers
     *
     * @param number
     * @return gama result
     */
    public static int gammaFunction(int number) {
        if (number > 0) {
            number--;
        }
        return (int) factorial(number);
    }

    /**
     * Method to calculate the gamma function for doubles
     *
     * @param number
     * @return gama result
     */
    public static double gammaFunction(double number) {
        if (number > 0) {
            number--;
        }
        return factorial(number);
    }

    /**
     * Method to calculate the factorial function for doubles
     *
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
