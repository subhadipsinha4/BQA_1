package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class TwoFactorAuthPage {

    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;

    public TwoFactorAuthPage(WebDriver driver, ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test=test;
    }

    @FindBy(xpath = "//*[@alt='logo']")
    private WebElement bonifyLogo;

    @FindBy(xpath = "//*[text()='Zwei-Faktoren-Authentifizierung']")
    private WebElement twoFactorAuthText;

    @FindBy(xpath = "//*[text()='Abmelden']")
    private WebElement logOut;



    @Description("verify Two Factor Auth Details")
    public TwoFactorAuthPage verifyTwoFactorAuthDetails() {
        commonActions.dismissCookieAlert();
        commonActions.waitForElementsToBeVisible(twoFactorAuthText);
        Assert.assertTrue(twoFactorAuthText.isDisplayed(),"Two-factor authentication text is not display");
        Assert.assertTrue(logOut.isDisplayed(),"logOut button is not display");
        test.info("Successfully verify element on the Auth page");
        return new TwoFactorAuthPage(driver,test);
    }

    @Description("Click on Logout Button")
    public LogoutPage clickOnLogout() {
        logOut.click();
        test.info("Successfully clicked on Logout button");
        return new LogoutPage(driver,test);
    }
}
