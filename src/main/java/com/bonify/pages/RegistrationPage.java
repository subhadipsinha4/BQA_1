package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class RegistrationPage {

    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;

    public RegistrationPage(WebDriver driver,ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test=test;
    }


    @FindBy(xpath = "//*[@data-testid='default-submit']")
    private WebElement registrationButton;

    @FindBy(id = "user_email")
    private WebElement emailInputBox;

    @FindBy(id = "user_password")
    private WebElement passwordBox;

    @FindBy(xpath = "//button[@data-testid='appleButton-button']")
    private WebElement loginUsingAppleAccount;

    @FindBy(xpath = "//button[@data-testid='googleButton-button']")
    private WebElement loginUsingGoogleAccount;

    @FindBy(xpath = "//button[@data-testid='facebookButton-button']")
    private WebElement loginUsingFacebookAccount;


    @Description("Enter User Registration Details And Submit")
    public AccountConfirmationPage enterUserRegistrationDetailsAndSubmit(String userId, String userPassword) {
        emailInputBox.sendKeys(userId);
        test.info("Entered the new user email Id");
        passwordBox.sendKeys(userPassword);
        test.info("Entered the new user password");
        registrationButton.click();
        test.info("Successfully clciked on Registration button");
        return new AccountConfirmationPage(driver,test);
    }

    @Description("verify Registration Page")
    public RegistrationPage verifyRegistrationPage() {
        commonActions.dismissCookieAlert();
        Assert.assertTrue(driver.getCurrentUrl().contains("register"),"Redirected to the wrong url");
        Assert.assertTrue(emailInputBox.isDisplayed(),"Email Input box is not display");
        Assert.assertTrue(passwordBox.isDisplayed(),"Password box is not display");
        Assert.assertTrue(loginUsingGoogleAccount.isDisplayed(),"Login using Google account option is not showing up");
        Assert.assertTrue(loginUsingAppleAccount.isDisplayed(),"Login using Apple account option is not showing up");
        Assert.assertTrue(loginUsingFacebookAccount.isDisplayed(),"Login using Facebook account option is not showing up");
        test.info("Successfully verify all the elements on the Registration screen");
        return new RegistrationPage(driver,test);
    }

}
