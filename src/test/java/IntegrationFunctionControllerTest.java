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
 * this class has unit test IntegrationController
 *
 * @class IntegrationFunctionControllerTest
 * @author Fabian Hoyos
 */
public class IntegrationFunctionControllerTest {

    public IntegrationFunctionControllerTest() {
    }

   
    @Test
    public void testCalculateIntegrationFunction() {
        
        IntegrationController controller = new IntegrationController();
        
        
        IntegrationDistribution t1 = new IntegrationDistribution();
        t1.setNumSeg(10);
        t1.setDof(9);
        t1.setxValue(1.1);
        t1.setErrorValue(IntegrationModel.ERROR);
        t1.setWidthSegment(t1.getxValue() / t1.getNumSeg());
        IntegrationDistribution t1Result = controller.calculateIntegrationFunction(t1);

        assertEquals("error in the t1 distribution ", 0.35006, (double)Math.round(t1Result.getP() * 100000) / 100000, t1.getErrorValue());

        
        IntegrationDistribution t2 = new IntegrationDistribution();
        t2.setNumSeg(10);
        t2.setDof(10);
        t2.setxValue(1.1812);
        t2.setErrorValue(IntegrationModel.ERROR);
        t2.setWidthSegment(t2.getxValue() / t2.getNumSeg());
        IntegrationDistribution t2Result = controller.calculateIntegrationFunction(t2);

        assertEquals("error in the t2 distribution ", 0.36757, (double)Math.round(t2Result.getP() * 100000) / 100000, t2.getErrorValue());

        IntegrationDistribution t3 = new IntegrationDistribution();
        t3.setNumSeg(10);
        t3.setDof(30);
        t3.setxValue(2.750 );
        t3.setErrorValue(IntegrationModel.ERROR);
        t3.setWidthSegment(t3.getxValue() / t3.getNumSeg());
        IntegrationDistribution t3Result = controller.calculateIntegrationFunction(t3);

        assertEquals("error in the t3 distribution ", 0.49500, (double)Math.round(t3Result.getP() * 100000) / 100000, t3.getErrorValue());

        
    }

}
