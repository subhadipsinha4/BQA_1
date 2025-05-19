package com.bonify.pages;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.CommonActions;
import jdk.jfr.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    CommonActions commonActions;
    ExtentTest test;


    public HomePage(WebDriver driver,ExtentTest test){
        this.driver=driver;
        PageFactory.initElements(driver,this);
        commonActions=new CommonActions(driver);
        this.test=test;
    }
    @FindBy(xpath ="//div[@id='dropwdown-bar']")
    private WebElement selectLanguage;
    @FindBy(xpath = "//button[text()='Neu bei Bonify']")
    private WebElement neuBaiBonifyButton;
    @FindBy(xpath = "//*[@data-testid='login-button']")
    private WebElement loginButton;


    @Description("Click on Nue Bai Bonify")
    public RegistrationPage clickOnNueBaiBonify() {
        commonActions.dismissCookieAlert();
        commonActions.waitForElementsToBeVisible(neuBaiBonifyButton);
        neuBaiBonifyButton.click();
        test.info("Successfully clicked on Neu Bai Bonify Button");
        return new RegistrationPage(driver,test);
    }


    @Description("Click on Einloggen Button")
    public LoginPage clickOnEinloggenButton() {
        commonActions.dismissCookieAlert();
        commonActions.waitForElementsToBeVisible(loginButton);
        loginButton.click();
        test.info("Successfully clicked on Einloggen button ");
        return new LoginPage(driver,test);
    }
}
