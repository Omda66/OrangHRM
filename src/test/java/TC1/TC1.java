package TC1;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Dashboard.Dashboard;
import pages.LoginPage.LoginPage;
import pages.PimPage.AddEmployee.AddEmployee;
import pages.PimPage.PimPage;

public class TC1 extends BaseTest {
    @Test
    public void TC1() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = new Dashboard(driver);
        PimPage pimPage = new PimPage(driver);
        AddEmployee addEmployee=new AddEmployee(driver);
        String username = configHandler.getValue("username");
        String password = configHandler.getValue("password");
        loginPage.enterUsernameField(username);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();
        softAssert.assertEquals(dashboard.getTitleDashboard(), "Dashboard");
        dashboard.clickOnPimButton();
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        pimPage.clickOnAddEmployee();
        softAssert.assertEquals(addEmployee.getAddEmployeeTilte(),"Add Employee");
        addEmployee.enterFirstNameField("Ahmed");
        addEmployee.enterlastNameField("Emad");
        addEmployee.enterEmployeeIdField("1234");
        addEmployee.clickOnCreatDetials();
        addEmployee.enterUsernameField("Omdaaa_66");
        addEmployee.enterPasswordField("Ahmed_Emad66");
        addEmployee.enterConfirmPasswordField("Ahmed_Emad66");
        addEmployee.clickOnSaveButton();
        Assert.assertEquals(addEmployee.getSucceflyMessage(),"Success");
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        addEmployee.clickOnUserDropDown();
        addEmployee.clickOnLogOut();
        loginPage.enterUsernameField("Omdaaa_66");
        loginPage.enterPasswordField("Ahmed_Emad66");
        loginPage.clickLogin();
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        softAssert.assertAll();
    }

}
