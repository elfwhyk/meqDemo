package com.simpleFormApp.segments;

import com.simpleFormApp.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.simpleFormApp.WebElements.loginButton;

public class LoginSegments {

    private final WebDriver browser;

    public LoginSegments(WebDriver browser) {
        this.browser = browser;
    }

    public static String meQLoginUrl = "https://mymeq.com/";

    public void navigateToMeqLogin() {
        System.out.println("Navigating to MeQ login page " + meQLoginUrl);
        browser.get(meQLoginUrl); // will fail out if cannot load page. Is there an implicit wait here?
        Helpers.waitForExistence(browser, By.id("email"), 15 );
    }

    public void enterAndSubmitEmail(String emailAddress) {
        browser.findElement(By.id("email")).sendKeys(emailAddress);
        browser.findElement(By.id("qa-enter-email-continue")).click();
    }

    public void enterAndSubmitPasswordShouldLandOnDashboard(String password) throws InterruptedException {
        Helpers.waitForExistence(browser, By.id("password"), 15).sendKeys(password);
        browser.findElement(loginButton).click();
        Helpers.waitForPageLoad(browser, "https://mymeq.com/my-meq/dashboard/#/my-meq/my-active-skill", 15);
    }

}
