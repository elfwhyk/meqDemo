package com.meq;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.UUID;

public class Helpers {
    public static WebElement waitForExistence(WebDriver browser, By by, int timeoutSeconds) {
        try {
            return (new WebDriverWait(browser, timeoutSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new AssertionError("Timed out (" + timeoutSeconds + "s) waiting for the '" + by + "' element to appear");
        }
    }

    public static void waitForPageLoad(WebDriver browser, String url, int timeoutSeconds) throws InterruptedException {
        Instant timeout = Instant.now().plusSeconds(timeoutSeconds);
        while(Instant.now().isBefore(timeout)) {
            if (!browser.getCurrentUrl().equalsIgnoreCase(url)) {
                Thread.sleep(1000);
            } else {
                return;
            }
        }

        throw new AssertionError("Did not successfully navigate to " + url);
    }

    public static void takeScreenshot(WebDriver browser) throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        // handle windows filesystem
        s = s.replace("\\C", "C//");
        s = s.replace("\\","/");
        String screenshotPath = s + "/src/screenshots/" + UUID.randomUUID() + ".png";
        System.out.println("screenshot path " + screenshotPath);

        TakesScreenshot screenshot = ((TakesScreenshot) browser);
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(screenshotPath);
        FileUtils.copyFile(sourceFile, destinationFile);
    }
}
