package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.automation.utils.WaitUtils;

public class CheckoutPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By checkoutButton = By.id("checkout");
    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void clickCheckout() {
        waitUtils.waitForClickable(checkoutButton).click();
    }

    public void enterCustomerDetails(
            String first, String last, String postal) {

        waitUtils.waitForVisibility(firstName).sendKeys(first);
        waitUtils.waitForVisibility(lastName).sendKeys(last);
        waitUtils.waitForVisibility(postalCode).sendKeys(postal);
    }

    public void continueCheckout() {
        waitUtils.waitForClickable(continueButton).click();
    }

    public void finishCheckout() {
        waitUtils.waitForClickable(finishButton).click();
    }

    public String getConfirmationMessage() {
        return waitUtils.waitForVisibility(completeHeader).getText();
    }
}
