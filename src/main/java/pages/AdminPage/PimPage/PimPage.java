package pages.AdminPage.PimPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.time.Duration;

public class PimPage extends BasePage {
    public PimPage(WebDriver driver) {
        super(driver);
    }

    private final By pimTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");
    private final By addEmployeeButton = By.xpath("//ul//li[contains(@class,\"oxd-topbar-body-nav-tab\")][3]");
    private final By editOneEmployee = By.xpath("//div[@class=\"oxd-table-card\"][1]");
    private final By reportsButton = By.xpath("//li[@class=\"oxd-topbar-body-nav-tab\"][2]");
    private final By x = By.xpath("");

    public String getPimTitle() {
        return getText(pimTitle, Duration.ofSeconds(5000));
    }

    public void clickOnAddEmployee() {
        click(addEmployeeButton);
    }
    public void clickOnEditOneEmployee(){
        click(editOneEmployee);
    }
    public void clickOnReportsButton(){
        click(reportsButton);
    }

}
