package pages.EmployeePage.E_Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.time.Duration;

public class E_Dashboard extends BasePage {
    public E_Dashboard(WebDriver driver) {
        super(driver);
    }
    private final By dashboardTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");
    private final By pimTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");
    private final By myInfoButton = By.xpath("//li[@class=\"oxd-main-menu-item-wrapper\"][3]");


    public void clickOnMyInfo(){
        click(myInfoButton);
    }
    public String getTitleDashboard() {
        return getText(dashboardTitle);
    }
    public String getTitlePim() {
        return getText(pimTitle);
    }
}
