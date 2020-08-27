package au.com.companyName.projectName.main;

import au.com.companyName.projectName.helpers.ReportHelper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

@CucumberOptions(dryRun = false, monochrome = true, features = "src/test/resources/features", glue = "au/com/companyName/projectName/stepdefinition", plugin = {
        "pretty", "json:target/cucumber.json"})

public class CucumberRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider()
    public Object[][] scenarios() {
        return super.scenarios(); // to run parallel features
    }

    @BeforeSuite
    public void beforeSuite() throws IOException {
            File file = new File(System.getProperty("user.dir") + "//screenshots");

            if (file.exists()) {
            FileUtils.cleanDirectory(file);
            }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ReportHelper.generateCucumberReport();
    }

}