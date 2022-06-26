package Marketplace;

import dirverSetUp.chromeDriverSetUp;
import globalVariables.setGlobalVariables;
import navigationPages.dashboardPage;
import navigationPages.loginPage;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TC11_requestAddOn {

    WebDriver driver = chromeDriverSetUp.setupDriver();

    // Page Objects
    loginPage login = new loginPage(driver); //Login page objects
    dashboardPage dashboard = new dashboardPage(driver); //Dashboard page objects

    WebDriverWait wait = new WebDriverWait(driver, 20);

    //Get credentials
    String user = setGlobalVariables.USER;
    String password = setGlobalVariables.PASSWORD;

    @BeforeTest
    public void startWebDriver() {

        driver.get(setGlobalVariables.HOME_PAGE); //Call homepage
        driver.manage().window().maximize();
        login.login(user, password); //send credentials
        dashboard.navigateToMarketplace();
    }

    @Test
    public void TC_11_requestAddOn() {

        //This test case is failing, but it was keep for to verify TestNG reporting.

        //Request an AddOn
        WebElement LdapBtn = driver.findElement(By.xpath("//input[@id='buyBtn1']"));
        wait.until(ExpectedConditions.visibilityOf(LdapBtn));
        LdapBtn.click();

        //Create and submit an email string
        WebElement email = driver.findElement(By.xpath("//form[@id='frmBuyNow']//input[@name='email']"));
        wait.until(ExpectedConditions.visibilityOf(email));
        int length = 10;
        boolean Letters = true;
        boolean Numbers = false;
        String lettersString = RandomStringUtils.random(length, Letters, Numbers);
        email.sendKeys(lettersString+"@test.com");

        //Create and submit a phone number
        WebElement PhoneNumber = driver.findElement(By.xpath("//form[@id='frmBuyNow']//input[@name='contactNumber']"));
        wait.until(ExpectedConditions.visibilityOf(PhoneNumber));
        boolean useLetters = false;
        boolean useNumbers = true;
        String PhoneString = RandomStringUtils.random(length, useLetters, useNumbers);
        PhoneNumber.sendKeys(PhoneString);

        //Create and submit an Organization name
        WebElement Organization = driver.findElement(By.xpath("//form[@id='frmBuyNow']//input[@name='organization']"));
        wait.until(ExpectedConditions.visibilityOf(Organization));
        Organization.sendKeys(lettersString);

        //Click on OK to submit changes
        WebElement OkBtn = driver.findElement(By.xpath("//input[@id='modal_confirm_buy']"));
        wait.until(ExpectedConditions.visibilityOf(OkBtn));
        OkBtn.click();

        //Assert AddOn was successfully requested
        WebElement SuccessMsg = driver.findElement(By.xpath("//div[@class='inner']"));
        wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
        System.out.print(SuccessMsg.getText());
        Assert.assertEquals(SuccessMsg.getText(),"Request submitted");
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
