package loginTestCases;

import dirverSetUp.chromeDriverSetUp;
import globalVariables.setGlobalVariables;
import navigationPages.loginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TC2_invalidUsername {

    WebDriver driver = chromeDriverSetUp.setupDriver();

    // Page Object
    loginPage login = new loginPage(driver);

    String invalidUser = "invalidUser";
    String password = setGlobalVariables.PASSWORD;

    @BeforeTest
    public void startWebDriver() {
        driver.get(setGlobalVariables.HOME_PAGE);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_02_invalidUsername() throws InterruptedException {
        login.login(invalidUser, password);

        //Verify the expected message was displayed
        WebElement lockedMessage = driver.findElement(By.xpath("//span[@id='spanMessage']"));
        assertEquals(lockedMessage.getText(),"Invalid credentials");
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
