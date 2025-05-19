package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;

    public LoginPage(WebDriver driver, ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test=test;
    }

    @FindBy(id = "loginId")
    private WebElement loginId;

    @FindBy(id = "password")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[@data-testid='appleButton-button']")
    private WebElement loginUsingAppleAccount;

    @FindBy(xpath = "//button[@data-testid='googleButton-button']")
    private WebElement loginUsingGoogleAccount;

    @FindBy(xpath = "//button[@data-testid='facebookButton-button']")
    private WebElement loginUsingFacebookAccount;

    @FindBy(xpath = "//*[@data-testid='login-button']")
    private WebElement loginButton;

    @Description("verify Login Page")
    public LoginPage verifyLoginPage() {
        commonActions.dismissCookieAlert();
        Assert.assertTrue(loginId.isDisplayed(),"Email Input box is not display");
        Assert.assertTrue(loginPassword.isDisplayed(),"Password box is not display");
        Assert.assertTrue(loginUsingGoogleAccount.isDisplayed(),"Login using Google account option is not showing up");
        Assert.assertTrue(loginUsingAppleAccount.isDisplayed(),"Login using Apple account option is not showing up");
        Assert.assertTrue(loginUsingFacebookAccount.isDisplayed(),"Login using Facebook account option is not showing up");
        test.info("Successfully verify all the elements on the Login page");
        return new LoginPage(driver,test);
    }

    @Description("login With Existing User")
    public TwoFactorAuthPage loginWithExistingUser(String userLoginId, String userPassword) {
        commonActions.dismissCookieAlert();
        loginId.sendKeys(userLoginId);
        loginPassword.sendKeys(userPassword);
        loginButton.click();
        test.info("Successfully clicked on login button");

        return new TwoFactorAuthPage(driver,test);
    }
}
