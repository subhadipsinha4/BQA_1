package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LogoutPage {

    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;

    public LogoutPage(WebDriver driver,ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test= test;
    }

    @FindBy(id="msRoriwX5IzDAgqxhNAk92ZgfxwTeQB5")
    private WebElement loggedOutMessage;

    @FindBy(id="TpCp3FBaSFwFC3TXAX7qUa7UpKaJUILm")
    private WebElement LoginAgainButton;


    @Description("verify Successful LogOut")
    public LogoutPage verifySuccessfulLogOut() {
        commonActions.waitForElementsToBeVisible(loggedOutMessage);
        Assert.assertEquals(loggedOutMessage.getText(),"Du bist ausgeloggt. Bis bald!","Wrong logout message showing up");
        Assert.assertTrue(LoginAgainButton.isDisplayed(),"Login Again Button is not showing up");
        test.info("Successfully verify the logout page");
        return new LogoutPage(driver,test);
    }
}
