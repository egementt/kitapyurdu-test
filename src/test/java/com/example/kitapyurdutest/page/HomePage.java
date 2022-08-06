package com.example.kitapyurdutest.page;

import com.example.kitapyurdutest.base.BasePage;
import org.apache.log4j.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

import static com.example.kitapyurdutest.constants.Constants.*;

public class HomePage extends BasePage {

    static final Logger logger = Logger.getLogger(HomePage.class.getName());

    public HomePage(WebDriver driver) {
        super(driver);
        PropertyConfigurator.configure("C:\\Users\\egeme\\Desktop\\seleniumWork\\kitapyurdu-test\\src\\log4j.properties");

    }

    public HomePage sendTextSearch(String text){
        WebElement searchArea = findElement(SEARCH_AREA);
        if (!searchArea.getAttribute("value").isEmpty()){
            searchArea.clear();
        }
        if (text.isEmpty()){
            logger.error("Search text can not be empty");
        }else{
            sendKeys(SEARCH_AREA, text );
            logger.info("Text sent to search area: " + text);
        }

        return this;
    }

    public HomePage hitEnterToSearch(){
        findElement(SEARCH_AREA).sendKeys(Keys.ENTER);
        logger.info("Hit Enter key to search.");
        return this;
    }

    private WebElement selectRandomBook(){
        List<WebElement> elements =findElements(BOOK_TABLE);
        Random random = new Random();
        WebElement randomBook = elements.get(random.nextInt(elements.size()));
        logger.info("Random book selected: " + randomBook.getText().substring(0,16) + "...");
        return randomBook;
    }

    public HomePage addToChart(){
        WebElement webElement = selectRandomBook();
        webElement.findElement(BUTTON_ADD_TO_CHART).click();
        logger.info("Book added to chart.");
        return this;
    }

    public Integer checkMyChart() {
        String chartText = findElement(CART_ICON).getText();
        int chartValue = Integer.parseInt(chartText);
        if (chartValue == 0){
            logger.info("Chart is empty.");
        }
        else if (chartValue < 0){
            logger.error("Chart value can not be lower than 0");
        }else{
            logger.info(chartValue + " items at the chart.");
        }
        return chartValue;
    }

    public HomePage clickMyChartButton(){
        findElement(MY_CART).click();
        logger.info("Clicked the chart button");
        return this;
    }

    public HomePage clickGoToChartButton(){
        findElement(BUTTON_GO_TO_CHART).click();
        logger.info("Clicked the go to chart button");
        return this;
    }

    private Integer getQuantityOfProduct(){
        String quantityString = findElement(PRODUCT_QUANTITY_TEXT).getAttribute("value");
        int quantity = Integer.parseInt(quantityString);

        if (quantity < 0){
            logger.error("Quantity can not be lower than 0");
        }
        logger.info("Current quantity of product is " + quantity);

        return quantity;
    }

    public HomePage addQuantity(){
        int quantity = getQuantityOfProduct();
        findElement(PRODUCT_QUANTITY_TEXT).clear();
        sendKeys(PRODUCT_QUANTITY_TEXT, String.valueOf(quantity + 1));
        logger.info("Add 1 to the current quantity. New value of quantity is: " +quantity);
        return this;
    }

    public HomePage refreshCart(){
        findElement(BUTTON_REFRESH).click();
        logger.info("Chart refreshed.");
        return this;
    }

    public HomePage checkAlert(){
        String expectedString = findElement(POPUP_REFRESH).getText();
        if (!expectedString.isEmpty()){
            logger.info("Chart updating...");
        }else {
            logger.error("Char doesn't update.");
        }
        return this;
    }

    public HomePage clickDeleteButton(){
        findElement(BUTTON_DELETE_BOOK).click();
        logger.info("Clicked the delete button.");
        return this;
    }

    public HomePage checkChartItemsEmpty(){
        findElement(TEXT_CHART_EMPTY).getText();
        logger.info("Chart is empty");
        return this;
    }




}
