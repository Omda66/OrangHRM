package TC3;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage.Dashboard.Dashboard;
import pages.LoginPage.LoginPage;
import pages.AdminPage.PimPage.Dependents.Dependents;
import pages.AdminPage.PimPage.EditEmployeePage.EditEmployeePage;
import pages.AdminPage.PimPage.EmergencyContacts.EmergencyContacts;
import pages.AdminPage.PimPage.PimPage;

public class TC3 extends BaseTest {
    @Test
    public void TC3 (){
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = new Dashboard(driver);
        PimPage pimPage = new PimPage(driver);
        EmergencyContacts emergencyDetails=new EmergencyContacts(driver);
        EditEmployeePage editEmployeePage = new EditEmployeePage(driver);
        Dependents dependents =new Dependents(driver);
        String username = configHandler.getValue("username");
        String password = configHandler.getValue("password");
        loginPage.enterUsernameField(username);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();
        softAssert.assertEquals(dashboard.getTitleDashboard(), "Dashboard");
        dashboard.clickOnPimButton();
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        pimPage.clickOnEditOneEmployee();
        editEmployeePage.clickOnEmergencyContacts();
        emergencyDetails.clickOnAddEmergencyContactsButton();
        emergencyDetails.enterNameField("Ahmed");
        emergencyDetails.enterRelationshipField("single");
        emergencyDetails.enterHomeTelephoneField("0123");
        emergencyDetails.enterMobileField("01234");
        emergencyDetails.enterWorkTelephoneField("0133546");
        emergencyDetails.clickOnSave();
        emergencyDetails.removeExistingContacts();
        emergencyDetails.clickVerifyDelete();
        Assert.assertEquals(emergencyDetails.getSucceflyMessage(),"Success");
        editEmployeePage.clickOnDependents();
        dependents.clickOnAddDependentsButton();
        dependents.enterNameField("Ahmed");
        dependents.selectRelationList();
        dependents.enterDataOfBirthField("2001-12-11");
        dependents.clickOnSave();
        Assert.assertEquals(dependents.getSucceflyMessage(),"Success");
    }
}
