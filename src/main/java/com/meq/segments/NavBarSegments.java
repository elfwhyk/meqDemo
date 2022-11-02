package com.meq.segments;

import com.meq.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class NavBarSegments {

    private final WebDriver browser;

    public NavBarSegments(WebDriver browser) {
        this.browser = browser;
    }

    public void navigateToProfilePage() throws InterruptedException {
        accessNavBar("Me", "My Profile");
    }

    public void navigateToSkillsPage() throws InterruptedException {
        accessNavBar("Me", "My Skills Journey");
    }

    public void navigateToActivitiesPage() throws InterruptedException {
        accessNavBar("Me", "My Activities");
    }

    public void navigateToJournalPage() throws InterruptedException {
        accessNavBar("Me", "My Journal");
    }

    public void navigateToSavedForLaterPage() throws InterruptedException {
        accessNavBar("Me", "Saved For Later");
    }

    private void accessNavBar(String subMenu, String subMenuLinkName) throws InterruptedException {
        System.out.println("Navigating to MeQ " + subMenu + " submenu from navBar. clicking " + subMenuLinkName);
        int maxAttempts = 5;
        for(int i = 0; i < maxAttempts; i++) {
            try {
                WebElement ele = Helpers.waitForExistence(browser, By.xpath("//span[text()='" + subMenu + "']"), 15);

                Actions actions = new Actions(browser);
                actions.moveToElement(ele).perform();
                Thread.sleep(200);

                WebElement meMenu = Helpers.waitForExistence(browser, By.linkText(subMenuLinkName), 15);
                actions.moveToElement(meMenu);
                Thread.sleep(200);

                actions.click().build().perform();
            } catch (StaleElementReferenceException e) {
                Thread.sleep(i * 500);
            }
        }

    }

}
