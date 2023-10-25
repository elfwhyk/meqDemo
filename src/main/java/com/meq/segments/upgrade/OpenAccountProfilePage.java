package com.meq.segments.upgrade;

import com.meq.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenAccountProfilePage extends BasePage {

    private static final By openAccountProfilePageFirstName =
            By.xpath("//input[@data-auto='fNameField']");
    private static final By openAccountProfilePageLastName =
            By.xpath("//input[@data-auto='lNameField']");

    private static final By openAccountProfilePageDob =
            By.xpath("//input[@data-auto='dobField']");
    private static final By openAccountProfilePageContinueButton =
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

    public void submitContinueAccountCreation() {
        browser.findElement(openAccountProfilePageContinueButton).click();

    }
}
