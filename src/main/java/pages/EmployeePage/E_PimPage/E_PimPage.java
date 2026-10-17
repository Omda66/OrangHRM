package pages.EmployeePage.E_PimPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class E_PimPage extends BasePage {
    public E_PimPage(WebDriver driver) {
        super(driver);
    }

    private final By timeButton = By.xpath("//li[@class=\"oxd-main-menu-item-wrapper\"][2]");
    private final By getTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");

    public String getPimTitle() {
        return getText(getTitle);
    }
    public void clickOnTimeButton() {
        click(timeButton);
    }
}

