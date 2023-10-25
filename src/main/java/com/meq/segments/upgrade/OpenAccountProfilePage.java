package com.meq.segments.upgrade;

import com.meq.BasePage;
import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.meq.segments.upgrade.OpenAccountAddressPage.openAccountAddressPageContinueButton;

public class OpenAccountProfilePage extends BasePage {

    public static final By openAccountProfilePageFirstName =
            By.xpath("//input[@data-auto='fNameField']");
    public static final By openAccountProfilePageLastName =
            By.xpath("//input[@data-auto='lNameField']");

    public static final By openAccountProfilePageDob =
            By.xpath("//input[@data-auto='dobField']");
    public static final By openAccountProfilePageContinueButton =
            By.xpath("//button[@data-auto='piContinueButton']");

    // todo pull in from config loader
    public static String pageUrl =
            "https://www.upgrade.com/deposit/apply/basic-information?pdc=DEP_CH_03";

    public OpenAccountProfilePage(WebDriver browser) {
        super(browser, pageUrl);
    }

    public void enterFirstName(String fName) {
        browser.findElement(openAccountProfilePageFirstName).sendKeys(fName);
    }

    public void enterLastName(String lName) {
        browser.findElement(openAccountProfilePageLastName).sendKeys(lName);
    }

    // TODO format validation on this field
    public void enterDob(String dob) {
        browser.findElement(openAccountProfilePageDob).sendKeys(dob);
    }

    public OpenAccountAddressPage submitContinueAccountCreation() {
        browser.findElement(openAccountProfilePageContinueButton).click();
        Helpers.waitForExistence(browser, openAccountAddressPageContinueButton, 15 );
        return new OpenAccountAddressPage(browser);
    }
}
