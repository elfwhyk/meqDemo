package com.meq;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {
    public static WebElement waitForExistence(WebDriver browser, By by, int timeoutSeconds) {
        try {
            return (new WebDriverWait(browser, timeoutSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            throw new AssertionError("Timed out (" + timeoutSeconds + "s) waiting for the '" + by + "' element to appear");
        }
    }
}
