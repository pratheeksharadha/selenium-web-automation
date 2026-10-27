package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.utils.WaitUtils;

public class CartPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By pageTitle = By.className("title");
    private final By backpackItem =
            By.id("item_4_title_link");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public String getPageTitle() {
        return waitUtils.waitForVisibility(pageTitle).getText();
    }

    public boolean isBackpackDisplayed() {
        return waitUtils.waitForVisibility(backpackItem).isDisplayed();
    }
}

