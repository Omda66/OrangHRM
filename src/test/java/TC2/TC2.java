package TC2;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AdminPage.Dashboard.Dashboard;
import pages.AdminPage.PimPage.PersonalDetails.PersonalDetails;
import pages.AdminPage.PimPage.PimPage;
import pages.EmployeePage.E_Dashboard.E_Dashboard;
import pages.EmployeePage.E_PimPage.E_ContactDetails.E_ContactDetails;
import pages.EmployeePage.E_PimPage.E_EmergencyContacts.E_EmergencyContacts;
import pages.LoginPage.LoginPage;
import pages.EmployeePage.E_PimPage.E_PersonalDetails.E_PersonalDetails;


public class TC2 extends BaseTest {

    @Test
    public void TC2()  {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        E_Dashboard eDashboard = new E_Dashboard(driver);
        E_PersonalDetails ePersonalDetails = new E_PersonalDetails(driver);
        E_EmergencyContacts eEmergencyContacts = new E_EmergencyContacts(driver);
        E_ContactDetails eContactDetails = new E_ContactDetails(driver);
        Dashboard dashboard=new Dashboard(driver);
        PimPage pimPage=new PimPage(driver);
        PersonalDetails personalDetails=new PersonalDetails(driver);
        loginPage.enterUsernameField(newEmployee.getValue("username").toString());
        loginPage.enterPasswordField(newEmployee.getValue("password").toString());
        loginPage.clickLogin();
        softAssert.assertEquals(eDashboard.getTitleDashboard(), "Dashboard");
        eDashboard.clickOnMyInfo();
        softAssert.assertEquals(eDashboard.getTitlePim(), "PIM");
        ePersonalDetails.selectMaritalStatus();
        ePersonalDetails.selectNationality();
        ePersonalDetails.clickSave();
        Assert.assertEquals(ePersonalDetails.getSucceflyMessage(), "Success");
        ePersonalDetails.clickOnContactDetails();
        eContactDetails.enterworkEmailField(jsonFileManger.getValue("work_email").toString());
        eContactDetails.enterotherEmailField(jsonFileManger.getValue("other_email").toString());
        eContactDetails.enterStreet1Field(jsonFileManger.getValue("street_1").toString());
        eContactDetails.enterStreet2Field(jsonFileManger.getValue("street_2").toString());
        eContactDetails.enterCityField(jsonFileManger.getValue("city").toString());
        eContactDetails.enterState_ProvinceFiled(jsonFileManger.getValue("state_province").toString());
        eContactDetails.enterzip_PostalCodeField(jsonFileManger.getValue("zip_postal_code").toString());
        eContactDetails.enterhomeField(jsonFileManger.getValue("home").toString());
        eContactDetails.entermobileField(jsonFileManger.getValue("mobile").toString());
        eContactDetails.enterworkField(jsonFileManger.getValue("work").toString());
        eContactDetails.clickOnSaveButton();
        Assert.assertEquals(eContactDetails.getSucceflyMessage(), "Success");
        eContactDetails.clickOnEmergencyContacts();
        eEmergencyContacts.clickOnAddEmergencyContactsButton();
        eEmergencyContacts.enterNameField(excelFile.getSpasifcCelValue(0,0));
        eEmergencyContacts.enterRelationshipField(excelFile.getSpasifcCelValue(1,0));
        eEmergencyContacts.enterHomeTelephoneField("0"+excelFile.getSpasifcCelValue(2,0));
        eEmergencyContacts.enterMobileField("0"+excelFile.getSpasifcCelValue(3,0));
        eEmergencyContacts.enterWorkTelephoneField("0"+excelFile.getSpasifcCelValue(4,0));
        eEmergencyContacts.clickOnSave();
        eEmergencyContacts.removeExistingContacts();
        eEmergencyContacts.clickVerifyDelete();
        Assert.assertEquals(eEmergencyContacts.getSucceflyMessage(), "Success");
        ePersonalDetails.clickOnUserDropDown();
        ePersonalDetails.clickOnLogOut();
        String username = configHandler.getValue("username");
        String password = configHandler.getValue("password");
        loginPage.enterUsernameField(username);
        loginPage.enterPasswordField(password);
        loginPage.clickLogin();
        dashboard.clickOnPimButton();
        pimPage.enterEmployeeName(newEmployee.getValue("fullName").toString());
        pimPage.clickOnSearchButton();
        pimPage.clickOnEmployee();
        String fullName=personalDetails.getFullName();
        softAssert.assertEquals(newEmployee.getValue("fullName").toString(),fullName);
        softAssert.assertAll();
    }
}
