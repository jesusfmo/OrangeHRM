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

public class TC9_deleteUser {

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
    public void TC_09_deleteUser() throws InterruptedException {

        //This is working, but tomorrow find a better way to optimize it, it's weak

        java.util.List<WebElement> links = driver.findElements(By.name("chkSelectRow[]"));
        System.out.println(links.size());
        links.get(3).click();

        Thread.sleep(2000);

        WebElement DeleteUsrBtn = driver.findElement(By.xpath("//input[@id='btnDelete']")); //Identify "Delete" button to delete a user
        DeleteUsrBtn.click(); //Click on "Delete" button

        Thread.sleep(2000);

        WebElement DeleteUsrOkBtn = driver.findElement(By.xpath("//input[@id='dialogDeleteBtn']")); //Identify "Delete" button to delete a user
        DeleteUsrOkBtn.click(); //Click on "Delete" button

        //Corroborate user is created expecting "Successfully Saved" message
        WebElement SuccessMsg = driver.findElement(By.xpath("//div[contains(.,'Successfully Deleted')]"));
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
