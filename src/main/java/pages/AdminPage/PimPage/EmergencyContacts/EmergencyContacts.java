package pages.AdminPage.PimPage.EmergencyContacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class EmergencyContacts extends BasePage {
    public EmergencyContacts(WebDriver driver) {
        super(driver);
    }

    private final By addEmergencyContactsButton = By.xpath("//div[@class=\"orangehrm-edit-employee-content\"]/div[@class=\"orangehrm-horizontal-padding orangehrm-vertical-padding\"]//button[@type=\"button\"]");
    private final By nameField = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By relationshipField = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By homeTelephoneField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By mobileField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By workTelephoneField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][3]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By succesflyMessage = By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");
    private final By removeExistingContacts = By.xpath("//div[@class=\"oxd-table-card\"][1]//button[@class=\"oxd-icon-button oxd-table-cell-action-space\"][1]");
    private final By verifyDelete = By.xpath("//button[@class=\"oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin\"]");
    private final By x = By.xpath("");

    public String getSucceflyMessage() {
        return getText(succesflyMessage);
    }

    public void clickOnAddEmergencyContactsButton() {
        click(addEmergencyContactsButton);
    }

    public void enterNameField(String name) {
        sendText(nameField, name);
    }

    public void enterRelationshipField(String relationship) {
        sendText(relationshipField, relationship);
    }

    public void enterHomeTelephoneField(String homeTelephone) {
        sendText(homeTelephoneField, homeTelephone);
    }

    public void enterMobileField(String mobile) {
        sendText(mobileField, mobile);
    }

    public void enterWorkTelephoneField(String workTelephone) {
        sendText(workTelephoneField, workTelephone);
    }

    public void clickOnSave() {
        click(saveButton);
    }

    public void removeExistingContacts() {
        click(removeExistingContacts);
    }

    public void clickVerifyDelete() {
        click(verifyDelete);
    }

}
