package com.automation.utils;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExtentTestListener implements ITestListener {

    private static final ExtentReports extent =
            ExtentReportManager.getReportInstance();

    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest extentTest = test.get();

        extentTest.fail(result.getThrowable());

        try {
            BaseTest baseTest = (BaseTest) result.getInstance();
            WebDriver driver = baseTest.getDriver();

            if (driver != null) {
                Path screenshotDir =
                        Paths.get("test-output", "screenshots");

                Files.createDirectories(screenshotDir);

                String testName = result.getMethod()
                        .getMethodName()
                        .replaceAll("[^a-zA-Z0-9_-]", "_");

                String fileName = testName + "_"
                        + System.currentTimeMillis() + ".png";

                File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                Path destination = screenshotDir.resolve(fileName);

                FileHandler.copy(source, destination.toFile());

                // Report path is relative to ExtentReport.html
                extentTest.addScreenCaptureFromPath(
                        "screenshots/" + fileName
                );
            }

        } catch (Exception e) {
            extentTest.warning(
                    "Could not capture or attach screenshot: "
                            + e.getMessage()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().skip("Test skipped");
    }

    @Override
    public void onFinish(org.testng.ITestContext context) {
        extent.flush();
    }
}

