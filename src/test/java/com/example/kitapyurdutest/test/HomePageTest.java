package com.example.kitapyurdutest.test;

import com.example.kitapyurdutest.base.BaseTest;
import com.example.kitapyurdutest.page.HomePage;
import com.example.kitapyurdutest.util.CSVReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;


public class HomePageTest extends BaseTest {

    HomePage homePage;
    static final org.apache.log4j.Logger logger = Logger.getLogger(HomePageTest.class.getName());


    @Before
    public void before(){
        homePage = new HomePage(getWebDriver());
        PropertyConfigurator.configure("C:\\Users\\egeme\\Desktop\\seleniumWork\\kitapyurdu-test\\src\\log4j.properties");
    }

    @Test
    public void test(){
        try{
            CSVReader csvReader = new CSVReader("C:\\Users\\egeme\\Desktop\\seleniumWork\\kitapyurdu-test\\src\\test\\java\\com\\example\\kitapyurdutest\\res\\test-text.csv");
            Thread thread = Thread.currentThread();
            synchronized (thread){
                logger.info("File found at the given path.");
                csvReader.readFile().forEach( text -> {

                    try {
                        thread.wait(2000L);
                        homePage.sendTextSearch(text).hitEnterToSearch().addToChart();
                        thread.wait(1000L);
                        homePage.checkMyCart();
                        homePage.clickMyCartButton().clickGoToCartButton().addQuantity().refreshCart();
                        thread.wait(250L);
                        homePage.checkAlert().clickDeleteButton().checkChartItemsEmpty();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }



                });
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        }
    }
}
