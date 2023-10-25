package com.meq.segments.upgrade;

import com.meq.BasePage;
import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private static final By openAccountButton = By.xpath("//input[@data-auto='emailField']");
    public static String upgradeHomePageUrl = "https://www.upgrade.com/";

    public HomePage(WebDriver browser) {
        super(browser);
    }
    public void navigateToHomePage() {
        System.out.println("Navigating to Upgrade Home Page " + upgradeHomePageUrl);
        browser.get(upgradeHomePageUrl); // will fail out if cannot load page. Is there an implicit wait here?

//        browser.findElement(By.xpath("//a[@data-auto= ’undefined-hero-cta-button’]"));
        Helpers.waitForExistence(browser, By.xpath("//a[@data-auto='undefined-hero-cta-button']"), 15 );
    }

    public OpenAccountPage openAccount() {
        System.out.println("asasdasd");
        browser.findElement(By.xpath("//a[@data-auto='undefined-hero-cta-button']")).click();
        Helpers.waitForExistence(browser, openAccountButton, 15 );
        return new OpenAccountPage(browser);
    }
}
