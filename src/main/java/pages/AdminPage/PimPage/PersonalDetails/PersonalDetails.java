package pages.AdminPage.PimPage.PersonalDetails;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class PersonalDetails extends BasePage {
    public PersonalDetails(WebDriver driver) {
        super(driver);
    }
    private final By fullName=By.xpath("//div[@class=\"orangehrm-edit-employee-name\"][normalize-space()='Ahmed Emad']");


    public String getFullName()  {
        System.out.println("/////////////////////////////////////////////");
        System.out.println(getText(fullName));
        System.out.println("/////////////////////////////////////////////");
        return getText(fullName);
    }
}
