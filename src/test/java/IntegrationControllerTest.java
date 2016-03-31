import java.util.ArrayList;
import workshop5.Controller.IntegrationController;
import workshop5.Model.IntegrationModel;
import workshop5.Model.IntegrationDistribution;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Class containing the unit tests for the Controller
 *
 * @author Andy
 */
public class IntegrationControllerTest {
    
    /**
     *Empty Constructor
     */
    public IntegrationControllerTest() {
    }
   
    /**
     * Test for loadIntegrationData method, from class IntegrationController.
     */
    @Test
    public void testLoadIntegrationData() {
        System.out.println("Testing loadIntegrationData Method:");
        String fileName = "List1.txt";
        IntegrationController testController = new IntegrationController();
        ArrayList<String[]> dataList = testController.loadIntegrationData(fileName);
        assertTrue("Data List must have values.", dataList.size() > 0);
    }
    
    /**
     * Test for calculateIntegrationFunction method, from class IntegrationController.
     */
    @Test
    public void testCalculateIntegrationFunction() {

        IntegrationController controller = new IntegrationController();
            
        ArrayList<String[]> dataFromFile = controller.loadIntegrationData("List1.txt");

        IntegrationDistribution distribution1 = new IntegrationDistribution();
        String[] data1 = dataFromFile.get(0);
        int numberOfSegments1 = Integer.parseInt(data1[0]);
        int degreesOfFreedom1 = Integer.parseInt(data1[1]);
        double x1 = Double.parseDouble(data1[2]);
        distribution1.setNumSeg(numberOfSegments1);
        distribution1.setDof(degreesOfFreedom1);
        distribution1.setxValue(x1);
        distribution1.setWidthSegment(distribution1.getxValue() / distribution1.getNumSeg());
        IntegrationDistribution t1Result = controller.calculateIntegrationFunction(distribution1);

        assertTrue("Error in the calculation", (double)Math.round(t1Result.getP() * 100000) / 100000 == 0.35006);

        
    }

}
