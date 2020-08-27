package au.com.companyName.projectName.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.io.IOException;

public class AtoPage extends BasePage{

    private static final Logger LOGGER = LoggerFactory.getLogger(AtoPage.class);

    WebDriver driver;
    @FindBy(className = "page-header") WebElement pageHeader;
    @FindBy(id = "textpayeeName") WebElement payeeName;
    @FindBy(id = "vrb-paymentFrequency-span-0") WebElement weeklyPayment;
    @FindBy(id = "vrb-paymentFrequency-span-1") WebElement fortnightlyPayment;
    @FindBy(id = "vrb-paymentFrequency-span-2") WebElement monthlyPayment;
    @FindBy(id = "vrb-paymentFrequency-span-3") WebElement yearlyPayment;
    @FindBy(id = "textgrossEarnings") WebElement grossEarning;
    @FindBy(id = "vrb-tFNProvided-span-0") WebElement tfnNo;
    @FindBy(id = "vrb-tFNProvided-span-1") WebElement tfnYes;
    @FindBy(id = "bnav-n1-btn4") WebElement calculateButton;
    @FindBy(id = "spntaxWithheld") WebElement taxWithHeldAmt;

    public AtoPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public void loadAtoPage() {
        LOGGER.info("*** naviagating to ATO calculator  ****");
        driver.get("https://www.ato.gov.au/Calculators-and-tools/Host/?anchor=TWC&anchor=TWC/questions#TWC/questions");
        explicitWait(pageHeader);
        LOGGER.info("*** ATO page loaded ****");
    }

    public void calculateWithHeldAmount() throws IOException {
        LOGGER.info("*** Attempting to fill ATO calculator fields  ****");
        Assert.assertTrue(pageLoadTime()<30 ,"page load is more than 30 seconds");
        explicitWait(payeeName);
        payeeName.sendKeys("Test1");
        fortnightlyPayment.click();
        grossEarning.sendKeys("100000");
        calculateButton.click();
        explicitWait(taxWithHeldAmt);
        String result = taxWithHeldAmt.getText().replace(",","");
        String testID = "testcase13";
        storeResults(testID,result);
        String expectedResult = searchCsvLine(0,testID).split(",")[1];
        Assert.assertEquals(result,expectedResult);
        LOGGER.info("*** calculation Asserted  ****");
    }
}