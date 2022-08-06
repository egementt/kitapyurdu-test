package com.example.kitapyurdutest.constants;

import org.openqa.selenium.By;


public class Constants {

    //HIT ENTER TO SEARCH
    public static final By SEARCH_AREA = By.id("search-input");

    //SELECT RANDOM BOOK TO ADD SHOPPING CART
    public static final By BOOK_TABLE = By.className("product-list");

    public static final By CART_ICON = By.id("cart-items");

    public static final By BUTTON_ADD_TO_CART = By.className("add-to-cart");
    public static final By MY_CART = By.id("sprite-cart-icon");

    public static final By BUTTON_GO_TO_CART = By.id("js-cart");

    public static final By PRODUCT_QUANTITY_TEXT = By.name("quantity");

    public static final By BUTTON_REFRESH = By.className("fa-refresh");

    public static final By POPUP_REFRESH = By.className("swal2-popup");

    public static final By BUTTON_DELETE_BOOK = By.className("remove");

    public static final By TEXT_CART_EMPTY = By.id("cart-items-empty");


}
