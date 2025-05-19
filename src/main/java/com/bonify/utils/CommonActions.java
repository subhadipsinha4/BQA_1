package com.bonify.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActions {

    public WebDriverWait wait;
    public WebDriver driver;

    public CommonActions(WebDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(this.driver, Duration.ofSeconds(60));
    }

    public void waitForElementsToBeVisible(WebElement webElement){
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitForElementsToBeClickable(WebElement webElement){
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void waitForSometime(long millisecond){
        try{
            Thread.sleep(millisecond);
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void dismissCookieAlert(){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        waitForSometime(5000);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#usercentrics-root")));
//            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
//            System.out.println(" Scrolled to bottom to trigger banner.");

            waitForSometime(3000);

            WebElement shadowHost = driver.findElement(By.cssSelector("#usercentrics-root"));
            SearchContext shadowRoot = shadowHost.getShadowRoot();

            WebElement consentButton = shadowRoot.findElement(By.cssSelector("button[data-testid='uc-deny-all-button']"));
            consentButton.click();
            System.out.println("Deny all the cookies");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String getRandomEmailId(){
        int randomNumber=(int)(Math.random()*9999);
        String emailId="qatest+"+randomNumber+"@gmail.com";
        return emailId;
    }
}
