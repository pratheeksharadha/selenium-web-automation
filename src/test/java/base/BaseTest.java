package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.openqa.selenium.devtools.v134.page.Page.captureScreenshot;

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
    public void tearDown(ITestResult result) {

        if (driver != null) {
            logger.info("Closing browser");
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }

    private void captureScreenshot(String testName) {
        try {
            Path screenshotDirectory =
                    Paths.get("test-output", "screenshots");

            Files.createDirectories(screenshotDirectory);

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path destination = screenshotDirectory.resolve(
                    testName + "_" + System.currentTimeMillis() + ".png"
            );

            Files.copy(
                    source.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            logger.info("Failure screenshot saved at: {}", destination);

        } catch (IOException | RuntimeException e) {
            logger.error("Failed to capture screenshot", e);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
