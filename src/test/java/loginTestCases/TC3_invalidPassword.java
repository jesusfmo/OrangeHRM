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

public class TC3_invalidPassword {

    WebDriver driver = chromeDriverSetUp.setupDriver();

    // Page Object
    loginPage login = new loginPage(driver);

    String user = setGlobalVariables.USER;
    String password = "invalidPassword";

    @BeforeTest
    public void startWebDriver() {
        driver.get(setGlobalVariables.HOME_PAGE);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_03_invalidUsername() throws InterruptedException {
        login.login(user, password);

        //Verify the expected message was displayed
        WebElement lockedMessage = driver.findElement(By.xpath("//span[@id='spanMessage']"));
        assertEquals(lockedMessage.getText(),"Invalid credentials");
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }
}
