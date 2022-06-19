package navigationPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class dashboardPage {

    public dashboardPage (WebDriver driver) {
        PageFactory.initElements(driver, this);//We initialize the PageObjects with PageFactory
        //This is a keyword to indicate it is the same class where we will initialize the elements
    }

    //Login PageObjects/WebElements
    @FindBy(xpath="//a[@id='menu_admin_viewAdminModule']")
    private WebElement AdmBarBtn;
    @FindBy(xpath="//a[@id='menu_pim_viewPimModule']")
    private WebElement PimBtn;
    @FindBy(xpath="//a[@id='menu_leave_viewLeaveModule']")
    private WebElement LeaveBtn;

    public void navigateToAdminBarOptions() {
        AdmBarBtn.click();
    }

    public void navigateToPimBarOptions() {
        PimBtn.click();
    }

    public void navigateToLeaveBarOptions() {
        LeaveBtn.click();
    }
}
