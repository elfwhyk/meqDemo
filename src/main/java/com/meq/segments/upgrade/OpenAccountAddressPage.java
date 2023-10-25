package com.meq.segments.upgrade;

import com.meq.BasePage;
import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.meq.segments.upgrade.OpenAccountProfilePage.openAccountProfilePageFirstName;

public class OpenAccountAddressPage extends BasePage {

    // todo autocomplete test in each browser?
    public static final By openAccountAddressPageAddress =
            By.xpath("//input[@name='street']");
    public static final By openAccountAddressPageCity =
            By.xpath("//input[@data-auto='cityField']");

    public static final By openAccountAddressPagePhone =
            By.xpath("//input[@data-auto='phoneNumberField']");
    public static final By openAccountAddressPageContinueButton =
            By.xpath("//button[@data-auto='addressContinueButton']");

    // todo pull in from config loader
    public static String pageUrl =
            "https://www.upgrade.com/deposit/apply/contact-information?pdc=DEP_CH_03";

    public OpenAccountAddressPage(WebDriver browser) {
        super(browser, pageUrl);
    }

    public void enterStreet(String street) {
        browser.findElement(openAccountAddressPageAddress).sendKeys(street);
    }

    public void enterCity(String city) {
        browser.findElement(openAccountAddressPageCity).sendKeys(city);
    }

    // TODO format validation on this field
    public void enterPhone(String phone) {
        browser.findElement(openAccountAddressPagePhone).sendKeys(phone);
    }

    public OpenAccountCreatePasswordPage submitContinueAccountCreation() {
        browser.findElement(openAccountAddressPageContinueButton).click();
//        Helpers.waitForExistence(browser, openAccountProfilePageFirstName, 15 ); // TODO
        return new OpenAccountCreatePasswordPage(browser);
    }
}
