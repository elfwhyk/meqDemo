package com.simpleFormApp;

import com.simpleFormApp.segments.Browser;
import com.simpleFormApp.segments.NavBarSegments;
import com.simpleFormApp.segments.LoginSegments;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver browser = null;
        try {
            // TODO some sort of config manager for test data, secure credentials, etc
            browser = Browser.getBrowserInstance();
            LoginSegments meQSegments = new LoginSegments(browser);
            NavBarSegments navBarSegments = new NavBarSegments(browser);

            meQSegments.navigateToMeqLogin();
            meQSegments.enterAndSubmitEmail("shane.burgoon@gmail.com");
            meQSegments.enterAndSubmitPasswordShouldLandOnDashboard("K45ufJ4CfZLhyCb$");

            // TODO validate navBar exists from each page, navigation from each page may be overkill

            navBarSegments.navigateToDiscoverAllTopicsPage();
            navBarSegments.navigateToDiscoverSkillsPage();
            navBarSegments.navigateToDiscoverActivitiesPage();
            navBarSegments.navigateToDiscoverCupOfCalmPage();
            navBarSegments.navigateToDiscoverCalmCastPage();
            navBarSegments.navigateToDiscoverMeditationsPage();
            navBarSegments.navigateToProfilePage();
            navBarSegments.navigateToSkillsPage();
            navBarSegments.navigateToActivitiesPage();
            navBarSegments.navigateToJournalPage();
            navBarSegments.navigateToSavedForLaterPage();



        } catch (Throwable t) {
            System.out.println("Failed doing some tests: " + t.getLocalizedMessage());
            t.printStackTrace();
            if(browser != null) {
                Helpers.takeScreenshot(browser);
            }
        } finally {
            // roll up results
            if(browser != null) {
                // grab browser console logs
                System.out.println("printing browser console logs");
                for (LogEntry entry : browser.manage().logs().get(LogType.BROWSER)) {
                    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
                    //do something useful with the data
                }
                Helpers.takeScreenshot(browser);

                // TODO grab browser network history
            }
            Browser.killWebDriver(browser);
        }

    }
}