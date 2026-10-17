package pages.EmployeePage.E_PimPage.E_ContactDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class E_ContactDetails extends BasePage {
    public E_ContactDetails(WebDriver driver) {
        super(driver);
    }

    private final By street1Field = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By street2Field = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By cityFiled = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][3]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By state_ProvinceFiled = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][4]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By zip_PostalCodeField = By.xpath("//div[@class=\"oxd-form-row\"][1]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][5]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By succesflyMessage=By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");


    private final By homeField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By mobileField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By workField = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][3]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By workEmailField = By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By otherEmailField = By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By EmergencyContactsButton = By.xpath("//div[@role=\"tab\"][3]//a[@class=\"orangehrm-tabs-item\"]");


    public void enterStreet1Field(String steert1){
        sendText(street1Field,steert1);
    }
    public void enterStreet2Field(String steert2){
        sendText(street2Field,steert2);
    }
    public void enterCityField(String city){
        sendText(cityFiled,city);
    }
    public void enterState_ProvinceFiled(String state_Province){
        sendText(state_ProvinceFiled,state_Province);
    }
    public void enterzip_PostalCodeField(String zip_PostalCode){
        sendText(zip_PostalCodeField,zip_PostalCode);
    }
    public void enterhomeField(String home){
        sendText(homeField,home);
    }
    public void entermobileField(String mobile){
        sendText(mobileField,mobile);
    }
    public void enterworkField(String work){
        sendText(workField,work);
    }
    public void enterworkEmailField(String workEmail){
        sendText(workEmailField,workEmail);
    }
    public void enterotherEmailField(String otherEmail){
        sendText(otherEmailField,otherEmail);

    }
    public void clickOnSaveButton(){
        click(saveButton);
    }

    public void clickOnEmergencyContacts() {
        click(EmergencyContactsButton);
    }
    public String getSucceflyMessage(){
        return getText(succesflyMessage);
    }
}
