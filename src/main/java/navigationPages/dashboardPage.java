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

    //Menu bar PageObjects/WebElements in Dashboard
    @FindBy(xpath="//a[@id='menu_admin_viewAdminModule']")
    private WebElement AdmBarBtn;
    @FindBy(xpath="//a[@id='menu_pim_viewPimModule']")
    private WebElement PimBtn;
    @FindBy(xpath="//a[@id='menu_leave_viewLeaveModule']")
    private WebElement LeaveBtn;
    @FindBy(xpath="//b[contains(text(),'Time')]")
    private WebElement TimeBtn;
    @FindBy(xpath="//b[contains(text(),'Recruitment')]")
    private WebElement RecruitmentBtn;
    @FindBy(xpath="//a[@id='menu_pim_viewMyDetails']")
    private WebElement MyInfoBtn;
    @FindBy(xpath="//a[@id='menu__Performance']")
    private WebElement PerformanceBtn;
    @FindBy(xpath="//a[@id='menu_dashboard_index']")
    private WebElement DashboardBtn;
    @FindBy(xpath="//a[@id='menu_directory_viewDirectory']")
    private WebElement DirectoryBtn;
    @FindBy(xpath="//b[contains(text(),'Maintenance')]")
    private WebElement MaintenanceBtn;
    @FindBy(xpath="//a[@id='menu_buzz_viewBuzz']")
    private WebElement BuzzBtn;

   //Navigate to Menu options
    public void navigateToAdminBarOptions() {
        AdmBarBtn.click();
    }

    public void navigateToPimBarOptions() {
        PimBtn.click();
    }

    public void navigateToLeaveBarOptions() {
        LeaveBtn.click();
    }

    public void navigateToTimeBarOptions() { TimeBtn.click(); }

    public void navigateToRecruitmentBarOptions() { RecruitmentBtn.click(); }

    public void navigateToMyInfoBarOptions() { MyInfoBtn.click(); }

    public void navigateToPerformanceBarOptions() { PerformanceBtn.click(); }

    public void navigateToDashboardBarOptions() {
        DashboardBtn.click();
    }

    public void navigateToDirectoryBarOptions() {
        DirectoryBtn.click();
    }

    public void navigateToMaintenanceBarOptions() {
        MaintenanceBtn.click();
    }

    public void navigateToBuzzBarOptions() {
        BuzzBtn.click();
    }
}
