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
        webElement.findElement(BUTTON_ADD_TO_CART).click();
        logger.info("Book added to cart.");
        return this;
    }

    public Integer checkMyCart() {
        String cartText = getText(CART_ICON);
        int cartValue = Integer.parseInt(cartText);
        if (cartValue == 0){
            logger.info("Cart is empty.");
        }
        else if (cartValue < 0){
            logger.error("Cart value can not be lower than 0");
        }else{
            logger.info(cartValue + " items at the chart.");
        }
        return cartValue;
    }

    public HomePage clickMyCartButton(){
        click(MY_CART);
        logger.info("Clicked the cart button");
        return this;
    }

    public HomePage clickGoToCartButton(){
        click(BUTTON_GO_TO_CART);
        logger.info("Clicked the go to cart button");
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
        clear(PRODUCT_QUANTITY_TEXT);
        sendKeys(PRODUCT_QUANTITY_TEXT, String.valueOf(quantity + 1));
        logger.info("Add 1 to the current quantity. New value of quantity is: " +quantity);
        return this;
    }

    public HomePage refreshCart(){
        click(BUTTON_REFRESH);
        logger.info("Cart refreshed.");
        return this;
    }

    public HomePage checkAlert(){
        String expectedString = getText(POPUP_REFRESH);
        if (!expectedString.isEmpty()){
            logger.info("Cart updating...");
        }else {
            logger.error("Car doesn't update.");
        }
        return this;
    }

    public HomePage clickDeleteButton(){
        click(BUTTON_DELETE_BOOK);
        logger.info("Clicked the delete button.");
        return this;
    }

    public HomePage checkChartItemsEmpty(){
        getText(TEXT_CART_EMPTY);
        logger.info("Cart is empty");
        return this;
    }




}
