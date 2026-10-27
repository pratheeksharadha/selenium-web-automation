package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    private static final Logger logger =
            LogManager.getLogger(BaseTest.class);


    @BeforeMethod
    public void setUp() {
        logger.info("Starting browser setup");
        driver = new ChromeDriver();
        logger.info("Chrome browser launched successfully");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}
