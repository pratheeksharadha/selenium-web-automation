package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.utils.WaitUtils;

public class ProductsPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By pageTitle = By.className("title");
    private final By backpackAddButton =
            By.id("add-to-cart-sauce-labs-backpack");
    private final By cartLink = By.className("shopping_cart_link");
    private final By cartBadge = By.className("shopping_cart_badge");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public String getPageTitle() {
        return waitUtils.waitForVisibility(pageTitle).getText();
    }

    public void addBackpackToCart() {
        waitUtils.waitForClickable(backpackAddButton).click();
    }

    public String getCartCount() {
        return waitUtils.waitForVisibility(cartBadge).getText();
    }

    public void openCart() {
        waitUtils.waitForClickable(cartLink).click();
    }
}
