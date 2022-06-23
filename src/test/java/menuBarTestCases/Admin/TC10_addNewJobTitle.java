package menuBarTestCases.Admin;

import com.thoughtworks.qdox.model.expression.Add;
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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.enterprise.inject.New;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class TC10_addNewJobTitle {

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

        //Hovering needs to be fixed
        driver.get(setGlobalVariables.HOME_PAGE); //Call homepage
        driver.manage().window().maximize();
        login.login(user, password); //send credentials
        //Navigation to Job Title is made through hovering for this test case
        dashboard.navigateToAdminBarOptions(); //Click on Admin option in the bar
        Actions actions = new Actions(driver);
        WebElement AdminBar = driver.findElement(By.xpath("//a[@id='menu_admin_viewAdminModule']"));
        wait.until(ExpectedConditions.visibilityOf(AdminBar));
        actions.moveToElement(AdminBar);
        WebElement JobOpt = driver.findElement(By.xpath("//a[@id='menu_admin_Job']"));
        wait.until(ExpectedConditions.visibilityOf(JobOpt));
        actions.moveToElement(JobOpt);
        WebElement JobTitle = driver.findElement(By.xpath("//a[@id='menu_admin_viewJobTitleList']"));
        wait.until(ExpectedConditions.visibilityOf(JobTitle));
        JobTitle.click();
    }

    @Test
    public void TC_10_addNewJobTitle() {

        WebElement AddBtn = driver.findElement(By.xpath("//input[@id='btnAdd']"));
        wait.until(ExpectedConditions.visibilityOf(AddBtn));
        AddBtn.click();

        //Submit Job title name, create a random string, it may contain letters and numbers depending on the selection
        WebElement NewJobTitleName = driver.findElement(By.xpath("//input[@id='jobTitle_jobTitle']"));
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        NewJobTitleName.sendKeys(generatedString);

        WebElement BtnSave = driver.findElement(By.xpath("//input[@id='btnSave']"));
        BtnSave.click();

        //Corroborate user is created expecting "Successfully Saved" message
        WebElement SuccessMsg = driver.findElement(By.xpath("//div[contains(.,'Successfully Saved')]"));
        wait.until(ExpectedConditions.visibilityOf(SuccessMsg));
        assertTrue(SuccessMsg.isDisplayed());
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
