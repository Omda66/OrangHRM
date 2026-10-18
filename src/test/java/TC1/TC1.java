package TC1;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage.Dashboard.Dashboard;
import pages.LoginPage.LoginPage;
import pages.AdminPage.PimPage.AddEmployee.AddEmployee;
import pages.AdminPage.PimPage.PimPage;

public class TC1 extends BaseTest {
    @Test
    public void TC1() {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = new Dashboard(driver);
        PimPage pimPage = new PimPage(driver);
        AddEmployee addEmployee = new AddEmployee(driver);
        loginPage.login(configHandler.getValue("username"), configHandler.getValue("password"));
        softAssert.assertEquals(dashboard.getTitleDashboard(), "Dashboard");
        dashboard.clickOnPimButton();
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        pimPage.clickOnAddEmployee();
        softAssert.assertEquals(addEmployee.getAddEmployeeTilte(), "Add Employee");
        addEmployee.enterFullName(newEmployee.getValue("fristName").toString(), newEmployee.getValue("LastName").toString());
        addEmployee.uploadProfilePicture("profile.jpeg");
        addEmployee.enterEmployeeIdField(newEmployee.getValue("ID").toString());
        addEmployee.clickOnCreatDetials();
        addEmployee.createLoginDetails(newEmployee.getValue("username").toString(), newEmployee.getValue("password").toString());
        addEmployee.clickOnSaveButton();
        Assert.assertEquals(addEmployee.getSucceflyMessage(), "Success");
//        Assert.assertEquals(pimPage.getPimTitle(), "PIM");
//        addEmployee.clickOnUserDropDown();
//        addEmployee.clickOnLogOut();
//        loginPage.login(newEmployee.getValue("username").toString(), newEmployee.getValue("password").toString());
//        softAssert.assertAll();
    }

}
