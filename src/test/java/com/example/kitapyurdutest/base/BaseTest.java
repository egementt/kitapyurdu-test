package com.example.kitapyurdutest.base;


import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class BaseTest {

    static WebDriver webDriver = null;


    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        setWebDriver(new ChromeDriver());
        getWebDriver().manage().window().maximize();
        getWebDriver().navigate().to("https://www.kitapyurdu.com/");
        BasicConfigurator.configure();

    }

    public static WebDriver getWebDriver(){
        return webDriver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        BaseTest.webDriver = webDriver;
    }

    public void tearDown(){
        getWebDriver().quit();
    }
}
