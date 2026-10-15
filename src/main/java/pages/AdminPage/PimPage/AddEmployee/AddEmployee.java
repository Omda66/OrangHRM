package pages.AdminPage.PimPage.AddEmployee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import java.time.Duration;

public class AddEmployee extends BasePage {
    public AddEmployee(WebDriver driver) {
        super(driver);
    }

    private final By addEmployeeTilte = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 orangehrm-main-title\"]");
    private final By firstNameField = By.xpath("//input[@name=\"firstName\"]");
    private final By lastNameField = By.xpath("//input[@name=\"lastName\"]");
    private final By employeeIdField = By.xpath("//div[contains(@class,\"input-field-bottom-space\")]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By creatDetailsSwitch = By.xpath("//div[@class=\"oxd-switch-wrapper\"]");
    private final By userNameField = By.xpath("//div[@class=\"oxd-form-row\"][2]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By passwordField = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters user-password-cell\"]//input[@type=\"password\"]");
    private final By confirmPasswordField = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//input[@type=\"password\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By enabledRadioButton = By.xpath("//div[@class=\"oxd-radio-wrapper\"]//input[@value=\"1\"]");/*********************************************************************************/
    private final By userDropDown = By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]");
    private final By logOut = By.xpath("//li[4]//a[@role=\"menuitem\"]");
    private final By succesflyMessage=By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");
    private final By x = By.xpath("");


    public String getAddEmployeeTilte() {
        return getText(addEmployeeTilte);
    }

    public void enterFirstNameField(String firstName) {
        sendText(firstNameField, firstName);
    }

    public void enterlastNameField(String lastName) {
        sendText(lastNameField, lastName);
    }

    public void enterEmployeeIdField(String employeeId) {
        sendText(employeeIdField, employeeId);
    }

    public void clickOnCreatDetials() {
        click(creatDetailsSwitch, Duration.ofSeconds(3000));
    }
    public void enterUsernameField(String username){
        sendText(userNameField,username);
    }
    public void enterPasswordField(String password){
        sendText(passwordField,password);
    }
    public void enterConfirmPasswordField(String confirmPassword){
        sendText(confirmPasswordField,confirmPassword);
    }
    public void clickOnSaveButton(){
        click(saveButton);
    }
    public void clickOnUserDropDown(){
        click(userDropDown);
    }
    public void clickOnLogOut(){
        click(logOut);
    }
    public String getSucceflyMessage(){
        return getText(succesflyMessage);
    }
}
