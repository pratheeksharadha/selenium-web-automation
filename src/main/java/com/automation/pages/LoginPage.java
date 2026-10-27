package com.automation.pages;

import com.automation.utils.ConfigReader;
import com.automation.utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private static final Logger logger =
            LogManager.getLogger(LoginPage.class);

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils= new WaitUtils(driver);
    }

    public void open() {
        logger.info("Opening SauceDemo application");
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    public void login(String username, String password) {

        logger.info("Entering login credentials");
        waitUtils.waitForVisibility(usernameField).sendKeys(username);
        waitUtils.waitForVisibility(passwordField).sendKeys(password);
        waitUtils.waitForClickable(loginButton).click();

        logger.info("Login button clicked");
    }


    public String getLoginErrorMessage() {
        By errorMessage = By.cssSelector("[data-test='error']");
        return waitUtils.waitForVisibility(errorMessage).getText();
    }

    public boolean isLoginErrorDisplayed() {
        By errorMessage = By.cssSelector("[data-test='error']");
        return waitUtils.waitForVisibility(errorMessage).isDisplayed();
    }

}
