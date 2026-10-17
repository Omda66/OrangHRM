package pages.AdminPage.PimPage.ReportsPage.EditReportPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class EditReportPage extends BasePage {
    public EditReportPage(WebDriver driver) {
        super(driver);
    }

    private final By selectionCriteriaList = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters orangehrm-report-criteria --span-column-2\"]//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By selectionCriteriaOptionJobTitle = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][8]");
    private final By selectionCriteriaOptionPayGrade = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][3]");
    private final By selectionCriteriaAddButton = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters orangehrm-report-criteria --span-column-2\"]//i[@class=\"oxd-icon bi-plus\"]");
    private final By jobTitleList = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][2]//div[@class=\"oxd-select-text--after\"]");
    private final By payGradeList = By.xpath("//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"][3]//div[@class=\"oxd-select-text--after\"]");
    private final By jobTitleOption = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By payGradeOption = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][3]");
    private final By includeList = By.xpath("//div[@class=\"oxd-form-row\"][2]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By includeOptionPastEmployeesOnly = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][3]");
    private final By selectDisplayFieldGroupList = By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters\"]//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By selectDisplayFieldGroupOption = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By selectDisplayFieldList = By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters orangehrm-report-criteria --span-column-2\"]//i[@class=\"oxd-icon bi-caret-down-fill oxd-select-text--arrow\"]");
    private final By selectDisplayFieldOption = By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By displayFieldsAddButton = By.xpath("//div[@class=\"oxd-form-row\"][3]//div[@class=\"oxd-grid-item oxd-grid-item--gutters orangehrm-report-criteria --span-column-2\"]//i[@class=\"oxd-icon bi-plus\"]");
    private final By saveButton = By.xpath("//button[@type=\"submit\"]");
    private final By succesflyMessage = By.xpath("//p[@class=\"oxd-text oxd-text--p oxd-text--toast-title oxd-toast-content-text\"]");

    public void slectCriteriaJpbTitleAndpayGrade() {
        click(selectionCriteriaList);
        click(selectionCriteriaOptionJobTitle);
        click(selectionCriteriaAddButton);
        click(selectionCriteriaList);
        click(selectionCriteriaOptionPayGrade);
        click(selectionCriteriaAddButton);
        click(jobTitleList);
        click(jobTitleOption);
        click(payGradeList);
        click(payGradeOption);
    }

    public void selectIclude() {
        click(includeList);
        click(includeOptionPastEmployeesOnly);
    }

    public void SelectDisplayFields() {
        click(selectDisplayFieldGroupList);
        click(selectDisplayFieldGroupOption);
        click(selectDisplayFieldList);
        click(selectDisplayFieldOption);
        click(displayFieldsAddButton);
    }

    public void clickOnSaveButton() {
        click(saveButton);
    }

    public String getSucceflyMessage() {
        return getText(succesflyMessage);
    }


}
