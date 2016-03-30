
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;
import spark.ModelAndView;
import static spark.Spark.get;
import com.heroku.sdk.jdbc.DatabaseUrl;
import workshop5.Controller.IntegrationController;
import workshop5.Model.IntegrationDistribution;
import static spark.Spark.get;
import static spark.Spark.get;
import static spark.Spark.get;


/**
 * 
 * @author Andy
 */
public class Main {

    public static void main(String[] args) {

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");


        get("/Integration", (req, res) -> {
            
            IntegrationController controller = new IntegrationController();
            
            ArrayList<String[]> dataFromFile = controller.loadIntegrationData("List1.txt");
            
            //Test Case 1
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
            
            
            //Test Case 2
            IntegrationDistribution distribution2 = new IntegrationDistribution();
            String[] data2 = dataFromFile.get(1);
            int numberOfSegments2 = Integer.parseInt(data2[0]);
            int degreesOfFreedom2 = Integer.parseInt(data2[1]);
            double x2 = Double.parseDouble(data2[2]);
            distribution2.setNumSeg(numberOfSegments2);
            distribution2.setDof(degreesOfFreedom2);
            distribution2.setxValue(x2);
            distribution2.setWidthSegment(distribution2.getxValue() / distribution2.getNumSeg());
            IntegrationDistribution t2Result = controller.calculateIntegrationFunction(distribution2);
            
            
            //Test Case 3
            IntegrationDistribution distribution3 = new IntegrationDistribution();
            String[] data3 = dataFromFile.get(2);
            int numberOfSegments3 = Integer.parseInt(data3[0]);
            int degreesOfFreedom3 = Integer.parseInt(data3[1]);
            double x3 = Double.parseDouble(data3[2]);
            distribution3.setNumSeg(numberOfSegments3);
            distribution3.setDof(degreesOfFreedom3);
            distribution3.setxValue(x3);
            distribution3.setWidthSegment(distribution3.getxValue() / distribution3.getNumSeg());
            IntegrationDistribution t3Result = controller.calculateIntegrationFunction(distribution3);

        
           String result="<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<style>\n" +
"table, th, td {\n" +
"    border: 1px solid black;\n" +
"    border-collapse: collapse;\n" +
"}\n" +
"th, td {\n" +
"    padding: 5px;\n" +
"}\n" +
"</style>\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<table style=\"width:100%\">\n" +
"  <tr>\n" +
"    <th>P Expected</th>\n" +
"    <th>P Actual</th>      \n" +
"   \n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>0.35006</td>\n" +
"    <td>"+t1Result.getP()+"</td>     \n" +
"    \n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>0.36757</td>\n" +
"    <td>"+t2Result.getP()+"</td>       \n" +
"    \n" +
"  </tr>\n" +
"  <tr>\n" +
"    <td>0.49500</td>\n" +
"    <td>"+t3Result.getP()+"</td>       \n" +
"    \n" +
"  </tr>\n" +
"</table>\n" +
"\n" +
"</body>\n" +
"</html>";
 return result;
            
        });



        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        

    }

}