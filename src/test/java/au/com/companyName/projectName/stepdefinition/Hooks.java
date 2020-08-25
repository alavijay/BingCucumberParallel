package au.com.companyName.projectName.stepdefinition;

import au.com.companyName.projectName.utils.DriverFactory;
import com.google.common.io.Files;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Hooks {
    WebDriver driver;

    @Before
    public void SetUp() {
        driver = DriverFactory.getInstance().getDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("https://www.bing.com/");
    }

    @After
    public void tearDown() throws IOException {
        captureScreenshot();
        DriverFactory.getInstance().removeDriver();
    }

    private String setTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SS");
        return formatter.format(date);
    }

    public void captureScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String browserParameter = org.testng.Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser").toLowerCase();
        String screenshotName = browserParameter + setTime() +".png";
        File trgtFile = new File(System.getProperty("user.dir") + "//screenshots//"+ screenshotName );
        trgtFile.getParentFile().mkdir();
        trgtFile.createNewFile();
        Files.copy(scrFile, trgtFile);
    }
}
