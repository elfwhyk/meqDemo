package com.meq.segments.upgrade;

import com.meq.BasePage;
import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountPage extends BasePage {

    private static final By openAccountPageEmailAddress = By.xpath("//input[@data-auto='emailField']");
    private static final By openAccountPageGetStartedButton = By.xpath("//input[@data-auto='emailField']");

    public static String openAccountPageUrl = "https://www.upgrade.com/deposit/apply/account-email?pdc=DEP_CH_03";

    public OpenAccountPage(WebDriver browser) {
        super(browser);
    }

    public void enterNewAccountEmailAddress(String emailAddress) {
        browser.findElement(openAccountPageEmailAddress).sendKeys(emailAddress);
    }

    public OpenAccountProfilePage submitCreateNewAccount() {
        browser.findElement(openAccountPageGetStartedButton).click();
        return new OpenAccountProfilePage(browser);
    }
}
