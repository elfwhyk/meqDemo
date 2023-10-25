package com.meq.segments.upgrade;

import com.meq.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountCreatePasswordPage extends BasePage {
    // todo password validation logic tests in UI and API
    public static final By openAccountCreatePasswordPagePassword =
            By.xpath("//input[@name='passwordField']");

    // TODO update name of this button ( lol because I found it in an interview )
    public static final By openAccountCreatePasswordPageContinueButton =
            By.xpath("//button[@data-auto='addressContinueButton']");

    // todo pull in from config loader
    public static String pageUrl =
            "https://www.upgrade.com/deposit/apply/account-password?pdc=DEP_CH_03";

    public OpenAccountCreatePasswordPage(WebDriver browser) {
        super(browser, pageUrl);
    }

    public void enterPassword(String password) {
        browser.findElement(openAccountCreatePasswordPagePassword).sendKeys(password);
    }

    public void submitContinueAccountCreation() {
        browser.findElement(openAccountCreatePasswordPageContinueButton).click();

    }
}
