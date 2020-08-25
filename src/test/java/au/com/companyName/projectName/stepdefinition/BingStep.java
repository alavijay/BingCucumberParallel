package au.com.companyName.projectName.stepdefinition;

import au.com.companyName.projectName.page.homePage;
import au.com.companyName.projectName.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class BingStep {

    WebDriver driver = DriverFactory.getInstance().getDriver();
    homePage homePO;

    @Given("user is on home page")
    public void userIsOnHomePage() {
        homePO = new homePage(driver);
        homePO.verifyHomePage();
    }

    @When("user searches for food")
    public void userSearchesForFood() {
        homePO = new homePage(driver);
        homePO.searchfood();

    }

    @Then("results are displayed")
    public void resultsAreDisplayed() {
        homePO = new homePage(driver);
        homePO.verifyResult();
    }
}