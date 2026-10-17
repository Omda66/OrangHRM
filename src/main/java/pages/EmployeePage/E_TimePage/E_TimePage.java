package pages.EmployeePage.E_TimePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class E_TimePage extends BasePage {
    public E_TimePage(WebDriver driver) {
        super(driver);
    }

    private final By timeTitle = By.xpath("//h6[@class=\"oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-module\"]");
    private final By editButton = By.xpath("//div[@class=\"orangehrm-timesheet-footer--options\"]//button[@type=\"button\"][1]");
    private final By projectNAmeField = By.xpath("//input[@placeholder=\"Type for hints...\"]");//Fresh Books Software Ltd - Fresh Books Software Ltd - Phase I
    private final By selectProjectName = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By activitylist = By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By activityOption = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By fromHour = By.xpath("//td[@class=\"orangehrm-timesheet-table-body-cell --center --duration-input\"][1]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By toHour = By.xpath("//td[@class=\"orangehrm-timesheet-table-body-cell --center --duration-input\"][7]//input[@class=\"oxd-input oxd-input--active\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By submitButton = By.xpath("//div[@class=\"orangehrm-timesheet-footer--options\"]/button[@type=\"button\"][2]");
    private final By TotalHour = By.xpath("//td[@class=\"orangehrm-timesheet-table-body-cell --center --freeze-right --highlight-2\"]");
    private final By userDropDown = By.xpath("//i[@class=\"oxd-icon bi-caret-down-fill oxd-userdropdown-icon\"]");
    private final By logOut = By.xpath("//li[4]//a[@role=\"menuitem\"]");
    private final By x = By.xpath("");


    public String getTitle() {
        return getText(timeTitle);
    }

    public void clickOnEdite() {
        click(editButton);
    }

    public void enterProjectNAmeField(String nameProject) {
        sendText(projectNAmeField, nameProject);
        click(selectProjectName);
    }

    public void selectActivity() {
        click(activitylist);
        click(activityOption);
    }

    public void enterToFromHour(String from, String to) {
        sendText(fromHour, from);
        sendText(toHour, to);
    }

    public void clickSave() {
        click(saveButton);
    }


    public void clickOnSubmit() {
        click(submitButton);
    }

    public String getTotalHour() {
        return getText(TotalHour);
    }

    public void clickOnUserDropDown() {
        click(userDropDown);
    }

    public void clickOnLogOut() {
        click(logOut);
    }
}
