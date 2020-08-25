package au.com.companyName.projectName.page;

import au.com.companyName.projectName.utils.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver = DriverFactory.getInstance().getDriver();

    /**
     * scroll via space bar
     */
    public void spacebarScroll() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.SPACE).build().perform();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    /**
     * Wait for Element
     */
    public void explicitWait(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Wait for text visible
     */
    public void explicitWaitForText(WebElement element, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    /**
     * capture page load time
     */
    public double pageLoadTime() {
        final JavascriptExecutor js = (JavascriptExecutor) driver;
        // time of the process of navigation and page load
        return (double) (Double) js.executeScript(
                "return (window.performance.timing.loadEventEnd - window.performance.timing.navigationStart) / 1000");
    }
}
