package com.meq;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;

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
}
