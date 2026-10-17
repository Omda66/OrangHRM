package TC4;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage.Dashboard.Dashboard;
import pages.AdminPage.TimePage.TimePage;
import pages.EmployeePage.E_TimePage.E_TimePage;
import pages.LoginPage.LoginPage;
import pages.EmployeePage.E_PimPage.E_PimPage;

public class TC4 extends BaseTest {
    @Test
    public void TC4()  {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        E_PimPage ePimPage=new E_PimPage(driver);
        E_TimePage eTimePage=new E_TimePage(driver);
        Dashboard dashboard=new Dashboard(driver);
        TimePage timePage=new TimePage(driver);
        loginPage.enterUsernameField(newEmployee.getValue("username").toString());
        loginPage.enterPasswordField(newEmployee.getValue("password").toString());
        loginPage.clickLogin();
        softAssert.assertEquals(ePimPage.getPimTitle(), "Dashboard");
        ePimPage.clickOnTimeButton();
        softAssert.assertTrue(eTimePage.getTitle().contains("Time"));
        eTimePage.clickOnEdite();
        eTimePage.enterProjectNAmeField("a");
        eTimePage.selectActivity();
        eTimePage.enterToFromHour("11","11");
        eTimePage.clickSave();
        eTimePage.clickOnSubmit();
        eTimePage.clickOnUserDropDown();
        eTimePage.clickOnLogOut();
        String username = configHandler.getValue("username");
        String password = configHandler.getValue("password");
        loginPage.enterUsernameField(username);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();
        dashboard.clickOnTimeButton();
        timePage.enterEmployeeName("Ahmed Emad");
        timePage.clickOnViewButton();
        timePage.clickOnSubmit();
        timePage.enterComment("its good , keep going");
        timePage.clickOnApprove();
        softAssert.assertAll();
    }



}
