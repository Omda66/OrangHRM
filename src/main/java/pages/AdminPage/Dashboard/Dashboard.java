package pages.AdminPage.Dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.time.Duration;

public class Dashboard extends BasePage {
    public Dashboard(WebDriver driver) {
        super(driver);
    }

    private final By dashboardTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");
    private final By pimButton = By.xpath("//li[@class=\"oxd-main-menu-item-wrapper\"][2]");
    private final By timeButton = By.xpath("//li[@class=\"oxd-main-menu-item-wrapper\"][4]");
    private final By x = By.xpath("");

    public String getTitleDashboard() {
        return getText(dashboardTitle, Duration.ofSeconds(5000));
    }

    public void clickOnPimButton() {
        click(pimButton);
    }
    public void clickOnTimeButton() {
        click(timeButton);
    }

}
