package com.simpleFormApp.segments;

import com.simpleFormApp.Helpers;
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

    public void navigateToDiscoverAllTopicsPage() throws InterruptedException {
        accessNavBar("Discover", "Browse All Topics", "https://mymeq.com/my-meq/dashboard/#/discover/all");
    }

    public void navigateToDiscoverSkillsPage() throws InterruptedException {
        accessNavBar("Discover", "Skills", "https://mymeq.com/my-meq/dashboard/#/explore/skills");
    }

    public void navigateToDiscoverActivitiesPage() throws InterruptedException {
        accessNavBar("Discover", "Activities", "https://mymeq.com/my-meq/dashboard/#/explore/activities?groupId=101"); // whats with the groupId?
    }

    public void navigateToDiscoverCupOfCalmPage() throws InterruptedException {
        accessNavBar("Discover", "Cup of Calm Blog", "https://mymeq.com/my-meq/dashboard/#/must-reads/cup-of-calm");
    }

    public void navigateToDiscoverCalmCastPage() throws InterruptedException {
        accessNavBar("Discover", "Calm-Cast Videos", "https://mymeq.com/my-meq/dashboard/#/must-reads/calm-casts");
    }

    public void navigateToDiscoverMeditationsPage() throws InterruptedException {
        accessNavBar("Discover", "Meditations", "https://mymeq.com/my-meq/dashboard/#/discover/meditations/");
    }

    public void navigateToProfilePage() throws InterruptedException {
        accessNavBar("Me", "My Profile", "https://mymeq.com/my-meq/dashboard/#/my-meq/stress-profile");
    }

    public void navigateToSkillsPage() throws InterruptedException {
        accessNavBar("Me", "My Skills Journey", "https://mymeq.com/my-meq/dashboard/#/my-meq/journey");
    }

    public void navigateToActivitiesPage() throws InterruptedException {
        accessNavBar("Me", "My Activities", "https://mymeq.com/my-meq/dashboard/#/explore/activities");
    }

    public void navigateToJournalPage() throws InterruptedException {
        accessNavBar("Me", "My Journal", "https://mymeq.com/my-meq/dashboard/#/journal");
    }

    public void navigateToSavedForLaterPage() throws InterruptedException {
        accessNavBar("Me", "Saved For Later", "https://mymeq.com/my-meq/dashboard/#/discover/saved");
    }

    private void accessNavBar(String subMenu, String subMenuLinkName, String subPageUrl) throws InterruptedException {
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
                break;
            } catch (StaleElementReferenceException e) {
                Thread.sleep(i * 500);
            }
        }
        // validate that a new page navigation occurred, if one is expected
        if(subPageUrl != null) {
            Helpers.waitForPageLoad(browser, subPageUrl, 10);
        }

    }

}
