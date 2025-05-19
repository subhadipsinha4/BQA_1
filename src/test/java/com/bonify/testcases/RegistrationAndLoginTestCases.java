package com.bonify.testcases;

import com.bonify.base.BaseTest;
import com.bonify.pages.HomePage;
import com.bonify.utils.CommonActions;
import com.bonify.utils.Groups;
import jdk.jfr.Description;
import org.testng.annotations.Test;

public class RegistrationAndLoginTestCases extends BaseTest {

    public RegistrationAndLoginTestCases(){}

    @Test(groups = {Groups.SMOKE,Groups.REGRESSION}, priority = 1)
    @Description("Verify new user registration flow")
    public void verifyNewUserRegistrationFlow() throws InterruptedException {
        HomePage homePage=new HomePage(driver,test);

        CommonActions commonActions=new CommonActions(driver);
        String newUserEmail=commonActions.getRandomEmailId();
        String password="Bonify"+newUserEmail;

        homePage.clickOnNueBaiBonify()
                .verifyRegistrationPage()
                .enterUserRegistrationDetailsAndSubmit(newUserEmail,password)
                .verifyAccountConfirmationDetails(newUserEmail);
    }

    @Test(groups = {Groups.REGRESSION}, priority = 2)
    @Description("verify Login And Logout Flow With Existing User")
    public void verifyLoginAndLogoutFlowWithExistingUser() {
        HomePage homePage=new HomePage(driver,test);
        String existingUserEmail="subhadipsinha12345@gmail.com";
        String existingPassword="A@123456";

        homePage.clickOnEinloggenButton()
                .verifyLoginPage()
                .loginWithExistingUser(existingUserEmail,existingPassword)
                .verifyTwoFactorAuthDetails()
                .clickOnLogout()
                .verifySuccessfulLogOut();
    }
}
