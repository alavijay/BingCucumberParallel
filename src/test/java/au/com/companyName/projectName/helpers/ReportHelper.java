package au.com.companyName.projectName.helpers;

import java.io.File;
import java.util.ArrayList;

import net.masterthought.cucumber.*;

public class ReportHelper {

    public static void generateCucumberReport() {
        File reportOutputDirectory = new File("target");
        ArrayList<String> jsonFiles = new ArrayList<String>();
        jsonFiles.add("target/cucumber.json");

        String projectName = "Project Name";
        String browserParameter = org.testng.Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.addClassifications("Platform", System.getProperty("os.name"));
        configuration.addClassifications("Browser", browserParameter);
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

}