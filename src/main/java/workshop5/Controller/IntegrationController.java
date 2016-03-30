package workshop5.Controller;

import java.util.ArrayList;
import workshop5.Model.IntegrationModel;
import workshop5.Model.IntegrationDistribution;

/**
 * this control class to manage the operations to calculate numerical
 * integration of the T distribution and communicate with the view classes
 *
 * @author Andy
 */
public class IntegrationController {

    
    public ArrayList<String[]> loadIntegrationData(String fileName){
        return IntegrationModel.getData(fileName);
    }
    
    
    /**
     * this method return T distribution with the value numerical integration p
     *
     * @method calculateIntegrationFunction
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
