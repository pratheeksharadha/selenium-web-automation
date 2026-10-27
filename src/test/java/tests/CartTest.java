package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.pages.CartPage;
import com.automation.pages.LoginPage;
import com.automation.pages.ProductsPage;

public class CartTest extends BaseTest {

    @Test
    public void addProductToCartTest() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products"
        );

        productsPage.addBackpackToCart();

        Assert.assertEquals(
                productsPage.getCartCount(),
                "1",
                "Cart should contain one product"
        );

        productsPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");
        Assert.assertTrue(
                cartPage.isBackpackDisplayed(),
                "Backpack should appear in the cart"
        );
    }
}
