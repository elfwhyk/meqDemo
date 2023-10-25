package com.meq;

import com.meq.segments.Browser;
import com.meq.segments.NavBarSegments;
import com.meq.segments.LoginSegments;
import com.meq.segments.upgrade.*;
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
            HomePage upgradeHomePage = new HomePage(browser);

//            meQSegments.navigateToMeqLogin();
            // TODO load from secure credential store in github actions, or some protected service
//            meQSegments.enterAndSubmitEmail("shane.burgoon@gmail.com");
//            meQSegments.enterAndSubmitPasswordShouldLandOnDashboard("K45ufJ4CfZLhyCb$");
//
//            // TODO validate navBar exists from each page, navigation from each page may be overkill
//
//            navBarSegments.navigateToDiscoverAllTopicsPage();
//            navBarSegments.navigateToDiscoverSkillsPage();
//            navBarSegments.navigateToDiscoverActivitiesPage();
//            navBarSegments.navigateToDiscoverCupOfCalmPage();
//            navBarSegments.navigateToDiscoverCalmCastPage();
//            navBarSegments.navigateToDiscoverMeditationsPage();
//            navBarSegments.navigateToProfilePage();
//            navBarSegments.navigateToSkillsPage();
//            navBarSegments.navigateToActivitiesPage();
//            navBarSegments.navigateToJournalPage();
//            navBarSegments.navigateToSavedForLaterPage();

            /* ---- - ---open account workflow UPGRADE - - - - */
            upgradeHomePage.navigateToHomePage();
            OpenAccountPage openAccountPage = upgradeHomePage.openAccount();

            openAccountPage.enterNewAccountEmailAddress("shane.burgoon@gmail.com");
            OpenAccountProfilePage openAccountProfilePage = openAccountPage.submitCreateNewAccount();

            openAccountProfilePage.enterFirstName("shane");
            openAccountProfilePage.enterLastName("Burgoon");
            openAccountProfilePage.enterDob("07/08/1991");
            OpenAccountAddressPage openAccountAddressPage = openAccountProfilePage.submitContinueAccountCreation();

            openAccountAddressPage.enterStreet("5 72nd st");
            openAccountAddressPage.enterPhone("8023438903");
            OpenAccountCreatePasswordPage openAccountCreatePasswordPage = openAccountAddressPage.submitContinueAccountCreation();

            openAccountCreatePasswordPage.enterPassword("CorrectHorseBatteryStaple$1");
            openAccountCreatePasswordPage.submitContinueAccountCreation();
            browser.wait(10000);

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