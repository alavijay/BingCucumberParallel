package au.com.companyName.projectName.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class homePage extends BasePage {

    WebDriver driver;
    @FindBy(id = "sb_form_q")
    WebElement searchBox;
    @FindBy(className = "carousel-title")
    WebElement results;

    public homePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void verifyHomePage() {
        explicitWait(searchBox);
    }

    public void searchfood() {
        searchBox.sendKeys("food");
        searchBox.sendKeys(Keys.ENTER);

    }

    public void verifyResult() {
        explicitWait(results);
        Assert.assertEquals(results.getText(),"Local results for food");
    }
}
