
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

        /**
        *Calculates and Displays Integration
        */
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
         
            
            String htmlData = "Test Case # 1<br><br>"; //Title
            htmlData += "<div style=\"display: inline-flex\">";
            htmlData += "<table style=\"border: 1px solid; border-collapse: collapse; text-align: center\">"; //Open Table
            htmlData += "<tr><th style=\"border: 1px solid; width: 100px;\">Number of Segments</th><th style=\"border: 1px solid; width: 100px;\">Degrees of Freedom</th><th style=\"border: 1px solid; width: 100px;\">x</th><th style=\"border: 1px solid; width: 150px;\">P expected</th><th style=\"border: 1px solid; width: 150px;\">P actual</th></tr>"; //Header
            htmlData += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", numberOfSegments1, degreesOfFreedom1, x1, 0.35006, t1Result.getP());
            htmlData += "</table><br>"; //Close Table
            htmlData += "</div><br><br><br>";
            
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
            
            htmlData += "Test Case # 2<br><br>"; //Title
            htmlData += "<div style=\"display: inline-flex\">";
            htmlData += "<table style=\"border: 1px solid; border-collapse: collapse; text-align: center\">"; //Open Table
            htmlData += "<tr><th style=\"border: 1px solid; width: 100px;\">Number of Segments</th><th style=\"border: 1px solid; width: 100px;\">Degrees of Freedom</th><th style=\"border: 1px solid; width: 100px;\">x</th><th style=\"border: 1px solid; width: 150px;\">P expected</th><th style=\"border: 1px solid; width: 150px;\">P actual</th></tr>"; //Header
            htmlData += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", numberOfSegments2, degreesOfFreedom2, x2, 0.36757, t2Result.getP());
            htmlData += "</table><br>"; //Close Table
            htmlData += "</div><br><br><br>";
            
            
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
            
            htmlData += "Test Case # 3<br><br>"; //Title
            htmlData += "<div style=\"display: inline-flex\">";
            htmlData += "<table style=\"border: 1px solid; border-collapse: collapse; text-align: center\">"; //Open Table
            htmlData += "<tr><th style=\"border: 1px solid; width: 100px;\">Number of Segments</th><th style=\"border: 1px solid; width: 100px;\">Degrees of Freedom</th><th style=\"border: 1px solid; width: 100px;\">x</th><th style=\"border: 1px solid; width: 150px;\">P expected</th><th style=\"border: 1px solid; width: 150px;\">P actual</th></tr>"; //Header
            htmlData += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", numberOfSegments3, degreesOfFreedom3, x3, 0.49500, t3Result.getP());
            htmlData += "</table><br>"; //Close Table
            htmlData += "</div><br><br><br>";
        
            return htmlData;
            
        });


        /**
        *Display Index
        */
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        

    }

}