package com.meq.segments;

import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DashboardSegments {

    private final WebDriver browser;

    public DashboardSegments(WebDriver browser) {
        this.browser = browser;
    }

    public void navigateToMePage() {
        System.out.println("Navigating to MeQ ME page from dashboard");
//        WebElement ele = browser.findElement(By.xpath("//body[@id='ng-app']/div/meq-app-bar/div/div/div/header/div/div/button[2]/span"));
//        WebElement ele = browser.findElement(By.cssSelector(".MuiButton-root:nth-child(2)"));
//        WebElement ele = browser.findElement(By.xpath("span[text()='Me']"));
        WebElement ele = Helpers.waitForExistence(browser, By.xpath("//span[text()='Me']"), 15);

        Actions actions = new Actions(browser);
        actions.moveToElement(ele).perform();

        WebElement meMenu = Helpers.waitForExistence(browser,By.linkText("My Profile"), 15);
        actions.moveToElement(meMenu);

        actions.click().build().perform();


//        Helpers.waitForExistence(browser, By.ByCssSelector.cssSelector("MuiButtonBase-root:nth-child(2) > .MuiButton-label"), 15 ).click();
//        Helpers.waitForExistence(browser, By.xpath("//body[@id='ng-app']/div/meq-app-bar/div/div/div/header/div/div/button[2]/span"), 15).click();

    }

}
