package loginTestCases;

import dirverSetUp.chromeDriverSetUp;
import globalVariables.setGlobalVariables;
import navigationPages.loginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.sql.Timestamp;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class TC1_validCredentials {

        WebDriver driver = chromeDriverSetUp.setupDriver();

        // Page Object
        loginPage login = new loginPage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 20);

        String user = setGlobalVariables.USER;
        String password = setGlobalVariables.PASSWORD;

        @BeforeTest
        public void startWebDriver() {
            driver.get(setGlobalVariables.HOME_PAGE);
            driver.manage().window().maximize();
        }

        @Test
        public void TC_01_loginSuccess() throws InterruptedException {
            login.login(user, password);

            //Verify if quick launch bar is displayed

            WebElement quickLaunchBar = driver.findElement(By.xpath("//fieldset[@id='panel_resizable_0_0']"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//fieldset[@id='panel_resizable_0_0']")));
            assertTrue(quickLaunchBar.isDisplayed());
        }

        @AfterTest
        public void closeDriver() {
            driver.quit();
        }
    }
