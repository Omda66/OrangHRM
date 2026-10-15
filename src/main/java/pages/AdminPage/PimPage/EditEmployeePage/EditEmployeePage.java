package pages.AdminPage.PimPage.EditEmployeePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class EditEmployeePage extends BasePage {
    public EditEmployeePage(WebDriver driver) {
        super(driver);
    }

    private final By contactDetails = By.xpath("//div[@role=\"tab\"][2]//a[@class=\"orangehrm-tabs-item\"]");
    private final By emergencyContacts = By.xpath("//div[@role=\"tab\"][3]//a[@class=\"orangehrm-tabs-item\"]");
    private final By dependents = By.xpath("//div[@role=\"tab\"][4]//a[@class=\"orangehrm-tabs-item\"]");
    private final By x = By.xpath("");

    public void clickOnContactDetails(){
        click(contactDetails);
    }
    public void clickOnEmergencyContacts(){
        click(emergencyContacts);
    }
    public void clickOnDependents(){
        click(dependents);
    }
}
