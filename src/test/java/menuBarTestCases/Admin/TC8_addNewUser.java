package menuBarTestCases.Admin;

import dirverSetUp.chromeDriverSetUp;
import globalVariables.setGlobalVariables;
import navigationPages.dashboardPage;
import navigationPages.loginPage;
import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class TC8_addNewUser {

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
        dashboard.navigateToAdminBarOptions(); //Click on Admin option in the bar
    }

    @Test
    public void TC_08_addNewUser() {

        //Comment all code and make it better, change the assertions to Assert 'Successfully Saved' JS text
        WebElement AddUserBtn = driver.findElement(By.xpath("//input[@id='btnAdd']")); //Identify "Add" button to add new user
        AddUserBtn.click(); //Click on "Add" button

        //Corroborate Add users pages is displayed
        WebElement AddUserTxt = driver.findElement(By.xpath("//h1[@id='UserHeading']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@id='UserHeading']")));
        assertTrue(AddUserTxt.isDisplayed());

        //Submit an already known employee name
        WebElement SubmitEmployeeName = driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']"));
        SubmitEmployeeName.sendKeys("Aaliyah Haq");

        //Submit username, create a random string, it may contain letters and numbers depending on the selection
        WebElement SubmitUsername = driver.findElement(By.xpath("//input[@id='systemUser_userName']"));
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        SubmitUsername.sendKeys(generatedString);

        //Submit and repeat password
        WebElement SubmitPassword = driver.findElement(By.xpath("//input[@id='systemUser_password']"));
        SubmitPassword.sendKeys("password");

        WebElement ConfirmPassword = driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']"));
        ConfirmPassword.sendKeys("password");

        //Save created user
        WebElement SaveBtn = driver.findElement(By.xpath("//input[@id='btnSave']"));
        SaveBtn.click();

        //Corroborate user is created expecting "Successfully Saved" message
        WebElement SuccessMsg = driver.findElement(By.xpath("//div[contains(.,'Successfully Saved')]"));
        wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
        assertTrue(SuccessMsg.isDisplayed());

        //Another way to look for Success Message
        //By xpath = By.xpath("//div[contains(.,'Successfully Saved')]");
        //WebElement element = (new WebDriverWait(driver,5)).until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
