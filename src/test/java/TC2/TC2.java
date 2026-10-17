package TC2;

import BaseTest.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.EmployeePage.E_Dashboard.E_Dashboard;
import pages.EmployeePage.E_PimPage.E_ContactDetails.E_ContactDetails;
import pages.EmployeePage.E_PimPage.E_EmergencyContacts.E_EmergencyContacts;
import pages.LoginPage.LoginPage;
import pages.EmployeePage.E_PimPage.E_PersonalDetails.E_PersonalDetails;


public class TC2 extends BaseTest {

    @Test
    public void TC2() {
        SoftAssert softAssert = new SoftAssert();
        LoginPage loginPage = new LoginPage(driver);
        E_Dashboard eDashboard = new E_Dashboard(driver);
        E_PersonalDetails ePersonalDetails = new E_PersonalDetails(driver);
        E_EmergencyContacts eEmergencyContacts = new E_EmergencyContacts(driver);
        E_ContactDetails eContactDetails = new E_ContactDetails(driver);
        loginPage.enterUsernameField("Omda_66");
        loginPage.enterPasswordField("Ahmed_Emad66");
        loginPage.clickLogin();
        softAssert.assertEquals(eDashboard.getTitleDashboard(), "Dashboard");
        eDashboard.clickOnMyInfo();
        softAssert.assertEquals(eDashboard.getTitlePim(), "PIM");
        ePersonalDetails.selectMaritalStatus();
        ePersonalDetails.selectNationality();
        ePersonalDetails.clickSave();
        Assert.assertEquals(ePersonalDetails.getSucceflyMessage(), "Success");
        ePersonalDetails.clickOnContactDetails();
        eContactDetails.enterworkEmailField("work.admin@example.com");
        eContactDetails.enterotherEmailField("other.admin@example.com");
        eContactDetails.enterStreet1Field("street1");
        eContactDetails.enterStreet2Field("street2");
        eContactDetails.enterCityField("cairo");
        eContactDetails.enterState_ProvinceFiled("single");
        eContactDetails.enterzip_PostalCodeField("01210");
        eContactDetails.enterhomeField("0120");
        eContactDetails.entermobileField("01010");
        eContactDetails.enterworkField("03451");
        eContactDetails.clickOnSaveButton();
        Assert.assertEquals(eContactDetails.getSucceflyMessage(), "Success");
        eContactDetails.clickOnEmergencyContacts();
        eEmergencyContacts.clickOnAddEmergencyContactsButton();
        eEmergencyContacts.enterNameField("Ahmed");
        eEmergencyContacts.enterRelationshipField("single");
        eEmergencyContacts.enterHomeTelephoneField("0123");
        eEmergencyContacts.enterMobileField("01234");
        eEmergencyContacts.enterWorkTelephoneField("0133546");
        eEmergencyContacts.clickOnSave();
        eEmergencyContacts.removeExistingContacts();
        eEmergencyContacts.clickVerifyDelete();
        Assert.assertEquals(eEmergencyContacts.getSucceflyMessage(), "Success");
        ePersonalDetails.clickOnUserDropDown();
        ePersonalDetails.clickOnLogOut();
        softAssert.assertAll();
    }
}
