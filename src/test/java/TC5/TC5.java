package TC5;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage.Dashboard.Dashboard;
import pages.AdminPage.PimPage.ReportsPage.EditReportPage.EditReportPage;
import pages.AdminPage.PimPage.ReportsPage.InfoReportPage.InfoReportPage;
import pages.AdminPage.PimPage.ReportsPage.ReportsPage;
import pages.LoginPage.LoginPage;
import pages.AdminPage.PimPage.PimPage;

public class TC5 extends BaseTest {
    @Test
    public void TC5(){
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        Dashboard dashboard = new Dashboard(driver);
        PimPage pimPage = new PimPage(driver);
        ReportsPage reportsPage=new ReportsPage(driver);
        EditReportPage editReportPage=new EditReportPage(driver);
        InfoReportPage infoReportPage=new InfoReportPage(driver);
        String username = configHandler.getValue("username");
        String password = configHandler.getValue("password");
        loginPage.enterUsernameField(username);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();
        softAssert.assertEquals(dashboard.getTitleDashboard(), "Dashboard");
        dashboard.clickOnPimButton();
        softAssert.assertEquals(pimPage.getPimTitle(), "PIM");
        pimPage.clickOnReportsButton();
        reportsPage.enterSearchField("a");
        reportsPage.clickOnSearchButton();
        reportsPage.clickOnEditButton();
        editReportPage.slectCriteriaJpbTitleAndpayGrade();
        editReportPage.selectIclude();
        editReportPage.SelectDisplayFields();
        editReportPage.clickOnSaveButton();
        Assert.assertEquals(editReportPage.getSucceflyMessage(),"Success");
        infoReportPage.clickOnFullscreenREportButton();
        softAssert.assertAll();



    }
}
