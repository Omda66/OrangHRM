package pages.EmployeePage.E_PimPage.E_PersonalDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class E_PersonalDetails extends BasePage {
    public E_PersonalDetails(WebDriver driver) {
        super(driver);
    }
    private final By maritalStatusList= By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//div[@class=\"oxd-select-text--after\"]");
    private final By nationalityList=By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//div[@class=\"oxd-select-wrapper\"]");
    private final By saveButton=By.xpath("//div[@class=\"orangehrm-horizontal-padding orangehrm-vertical-padding\"]//button[@type=\"submit\"]");
    private final By maritalStatusOpthion=By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By nationalityOpthion=By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By succesflyMessage=By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");
  private final By clickOnContactDetails=By.xpath("//div[@role=\"tab\"][2]//a[@class=\"orangehrm-tabs-item\"]");

    private final By userDropDown = By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]");
    private final By logOut = By.xpath("//li[4]//a[@role=\"menuitem\"]");
    public void selectMaritalStatus(){
        click(maritalStatusList);
        click(maritalStatusOpthion);
    }
    public void selectNationality(){
        click(nationalityList);
        click(nationalityOpthion);
    }
    public void clickSave(){
        click(saveButton);
    }
    public String getSucceflyMessage(){
        return getText(succesflyMessage);
    }
    public void clickOnUserDropDown(){
        click(userDropDown);
    }
    public void clickOnLogOut(){
        click(logOut);
    }
    public void clickOnContactDetails(){
        click(clickOnContactDetails);
    }
}
