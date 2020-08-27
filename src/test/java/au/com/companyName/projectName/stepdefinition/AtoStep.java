package au.com.companyName.projectName.stepdefinition;

import au.com.companyName.projectName.page.AtoPage;
import au.com.companyName.projectName.utils.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class AtoStep {
    WebDriver driver = DriverFactory.getInstance().getDriver();
    AtoPage AtoPO;

    @Given("^user is on ATO calculator page$")
    public void navToAto(){
        AtoPO = new AtoPage(driver);
        AtoPO.loadAtoPage();
    }

    @When("^user inputs values in calculator$")
    public void userInputsValuesInCalculator() throws IOException {
        AtoPO = new AtoPage(driver);
        AtoPO.calculateWithHeldAmount();
    }
}

