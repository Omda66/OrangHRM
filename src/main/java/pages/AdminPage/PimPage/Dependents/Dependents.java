package pages.AdminPage.PimPage.Dependents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class Dependents extends BasePage {
    public Dependents(WebDriver driver) {
        super(driver);
    }

    private final By addDependentsButton = By.xpath("//div[@class=\"orangehrm-edit-employee-content\"]/div[@class=\"orangehrm-horizontal-padding orangehrm-vertical-padding\"]//button[@type=\"button\"]");
    private final By nameField = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By relationList = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By relationOpthion = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By dataOfBirthField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By succesflyMessage = By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");


    public void clickOnAddDependentsButton() {
        click(addDependentsButton);
    }

    public void enterNameField(String name) {
        sendText(nameField, name);
    }

    public void selectRelationList() {
        click(relationList);
        click(relationOpthion);
    }

    public void enterDataOfBirthField(String DOB) {
        sendText(dataOfBirthField, DOB);
    }

    public void clickOnSave() {
        click(saveButton);
    }

    public String getSucceflyMessage() {
        return getText(succesflyMessage);
    }

}
