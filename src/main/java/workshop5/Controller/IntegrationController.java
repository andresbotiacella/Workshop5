package workshop5.Controller;

import java.util.ArrayList;
import workshop5.Model.IntegrationModel;
import workshop5.Model.IntegrationDistribution;

/**
 * Controller class containing the logic displayed in the program
 * @author Andy
 */
public class IntegrationController {

    /**
     * Method that loads data from a file
     *
     * @param fileName name of the file
     * @return List containing all the data by row
     */
    public ArrayList<String[]> loadIntegrationData(String fileName){
        return IntegrationModel.getData(fileName);
    }
    
    
    /**
     * Method that calculates the integration
     *
     * @param t IntegrationDistribution t
     * @return IntegrationDistribution t
     */
    public IntegrationDistribution calculateIntegrationFunction(IntegrationDistribution t) {
        double p1 = IntegrationModel.calculateNumericalIntegrationP(t);
        t.setNumSeg(t.getNumSeg() * 2);
        t.setWidthSegment(t.getxValue() / t.getNumSeg());
        double p2 = IntegrationModel.calculateNumericalIntegrationP(t);
        if (p1 - p2 < Math.E) {
            t.setP(p2);
            return t;

        } else {
            return calculateIntegrationFunction(t);
        }

    }  

}
