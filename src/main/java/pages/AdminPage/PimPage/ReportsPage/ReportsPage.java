package pages.AdminPage.PimPage.ReportsPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ReportsPage extends BasePage {
    public ReportsPage(WebDriver driver) {
        super(driver);
    }
    private final By searchField = By.xpath("//input[@placeholder=\"Type for hints...\"]");
   private final By searchOption=By.xpath("//div[@role=\"listbox\"]//div[@role=\"option\"][2]");
    private final By searchButton = By.xpath("//button[@type=\"submit\"]");
    private final By editButton = By.xpath("//div[@class=\"oxd-table-cell-actions\"]/button[@type=\"button\"][2]");
    private final By x = By.xpath("");

    public void enterSearchField(String search){
        sendText(searchField,search);
        click(searchOption);
    }
    public void clickOnSearchButton(){
        click(searchButton);
    }
    public void clickOnEditButton(){
        click(editButton);
    }


}
