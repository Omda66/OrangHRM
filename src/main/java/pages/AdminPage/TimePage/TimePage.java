package pages.AdminPage.TimePage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.BasePage;

import java.time.Duration;

public class TimePage extends BasePage {
    public TimePage(WebDriver driver) {
        super(driver);
    }

    private final By employeeNameField = By.xpath("//input[@placeholder=\"Type for hints...\"]");
    private final By nameEmployee = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][normalize-space()='Ahmed Emad']");
    private final By viewButton = By.xpath("//button[@type=\"submit\"]");
    private final By commentFiled = By.xpath("//textarea[@class=\"oxd-textarea oxd-textarea--active oxd-textarea--resize-vertical\"]");
    private final By approveButton = By.xpath("//div[@class=\"oxd-form-actions\"]//button[@type=\"button\"][2]");
    private final By submitButton = By.xpath("//div[@class=\"orangehrm-timesheet-footer--options\"]//button[@type=\"button\"][2]");
    public void enterEmployeeName(String employeeName) {
        sendText(employeeNameField, employeeName);
        click(nameEmployee);
    }

    public void clickOnViewButton() {
        click(viewButton);
    }

    public void enterComment(String comment) {
        sendText(commentFiled, comment);
    }

    public void clickOnApprove() {
        click(approveButton);
    }

    public void clickOnSubmit() {
        click(submitButton);
    }
}
