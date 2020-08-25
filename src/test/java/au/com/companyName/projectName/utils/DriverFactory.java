package au.com.companyName.projectName.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory
{

   private DriverFactory()
   {
      //Do-nothing..Do not allow to initialize this class from outside
   }
   private static final DriverFactory instance = new DriverFactory();

   public static DriverFactory getInstance()
   {
      return instance;
   }

   ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>() // thread local driver object for webdriver
   {
      final String os = System.getProperty("os.name");
      @Override
      protected WebDriver initialValue()
      {
         if (os.startsWith("Mac")) {
            String firefoxDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/geckodriver";
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            String chromeDriverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/mac/chromedriver";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
         } else {
            String firefoxDriverPath = System.getProperty("user.dir")
                    + "//src//test//resources//drivers//windows//geckodriver.exe";
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            String chromeDriverPath = System.getProperty("user.dir")
                    + "//src//test//resources//drivers//windows//chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            String edgeDriverPath = System.getProperty("user.dir")
                    + "//src//test//resources//drivers//windows//msedgedriver.exe";
            System.setProperty("webdriver.edge.driver", edgeDriverPath);
         }

         String browserParameter = org.testng.Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser").toLowerCase();
         if ("chrome".equals(browserParameter)) {
            return new ChromeDriver();
         } else if ("firefox".equals(browserParameter)) {
            return new FirefoxDriver();
         }
         if (os.toLowerCase().contains("win")) {
            return new EdgeDriver();
         } else {
            return new SafariDriver();
         }
      }
   };

   public void setWindowSize() {
      Dimension d = new Dimension(800,480);

      driver.get().manage().window().setSize(d);
   }

   public WebDriver getDriver() // call this method to get the driver object and launch the browser
   {
      return driver.get();
   }

   public void removeDriver() // Quits the driver and closes the browser
   {
      driver.get().quit();
      driver.remove();
   }

}