package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AccountConfirmationPage {

    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;

    public AccountConfirmationPage(WebDriver driver,ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test=test;
    }

    @FindBy(xpath = "//*[@alt='bonify-logo']")
    private WebElement bonifyLogo;

    @FindBy(xpath = "//*[@data-testid='CONFIRM_EMAIL_TITLE']")
    private WebElement confirmationEmailTitle;

    @FindBy(xpath = "//*[@data-testid='CONFIRM_EMAIL_SENT_EMAIL']")
    private WebElement confirmationEmailSentEmailId;

    @FindBy(xpath = "//*[@data-testid='CONFIRM_EMAIL_SUPPORT_EMAIL']")
    private WebElement supportEmailId;

    @FindBy(xpath = "//*[@data-testid='CONFIRM_EMAIL_RESEND_EMAIL']")
    private WebElement resendConfirmationEmail;


    @Description("Verify Account Confirmation Details")
    public AccountConfirmationPage verifyAccountConfirmationDetails(String userEmailId) {
        commonActions.dismissCookieAlert();
        Assert.assertTrue(bonifyLogo.isDisplayed(),"Bonify Logo is not showing up");
        Assert.assertTrue(confirmationEmailTitle.getText().equals(userEmailId),"Confirmation email Id missmatch");
        Assert.assertTrue(confirmationEmailSentEmailId.isDisplayed(),"Re-send Confirmation email link is not shown");
        Assert.assertTrue(supportEmailId.getText().equals("support@bonify.de"),"Bonify support email id is not showing up");
        Assert.assertTrue(resendConfirmationEmail.isDisplayed(),"Confirmation email Id missmatch");
        test.info("Successfully verify the Account Confirmation Page");
        return new AccountConfirmationPage(driver,test);
    }
}
