package com.bonify.base;

import com.aventstack.extentreports.ExtentTest;
import com.bonify.utils.ExtentReportManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public static ExtentTest test;
    public static WebDriver driver;
    public static Properties prop;

    public BaseTest(){
        prop=new Properties();
    }

    public void getPropertie(){
        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @BeforeMethod(alwaysRun = true)
    public void setUpBase(Method method) {
        getPropertie();
        test = ExtentReportManager.createTest(method.getName());
        String browser=prop.getProperty("browser");

        if(browser.equals("chrome")){
            WebDriverManager.chromedriver().setup();
            driver=new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.get(prop.getProperty("url"));
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void teardown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.skip("Test skipped");
        }

        ExtentReportManager.flushReport();
        if (driver != null) {
            driver.quit();
        }
    }
}
