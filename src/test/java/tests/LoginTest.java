
package tests;

import base.BaseTest;
import com.automation.pages.LoginPage;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
                {"invalid_user", "wrong_password"},
                {"standard_user", "wrong_password"},
                {"", "secret_sauce"}
        };
    }

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

            loginPage.open();
            loginPage.login("standard_user", "secret_sauce");

            assertTrue(
                    driver.getCurrentUrl().contains("inventory"),
                    "Login failed"
            );
    }


    @Test
    public void invalidLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login("invalid_user", "wrong_password");

        assertTrue(
                loginPage.isLoginErrorDisplayed(),
                "Expected login error was not displayed"
        );

        assertTrue(
                loginPage.getLoginErrorMessage()
                        .contains("Username and password do not match"),
                "Unexpected login error message"
        );
    }

    @Test(dataProvider = "invalidLoginData")
    public void invalidLoginDataTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.open();
        loginPage.login(username, password);

        assertTrue(loginPage.isLoginErrorDisplayed());
    }

}
