package pages.AdminPage.PimPage.ReportsPage.InfoReportPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class InfoReportPage extends BasePage {
    public InfoReportPage(WebDriver driver) {
        super(driver);
    }
    private final By fullscreenREportButton=By.xpath("//i[@class=\"oxd-icon bi-arrows-fullscreen oxd-icon-button__icon --toggable-icon\"]");
public void clickOnFullscreenREportButton(){
    click(fullscreenREportButton);
}

}

