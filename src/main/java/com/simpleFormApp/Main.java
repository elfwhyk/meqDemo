package com.simpleFormApp;

import com.simpleFormApp.pages.CreateTicketPage;
import com.simpleFormApp.pages.HomePage;
import com.simpleFormApp.segments.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver browser = null;
        try {
            // TODO some sort of config manager for test data, secure credentials, etc
            browser = Browser.getBrowserInstance();

            HomePage homePage = new HomePage(browser);
            CreateTicketPage createTicketPage = new CreateTicketPage(browser);

            homePage.navigateToHomePage();
            homePage.clickCreateTicket();

            // TODO ENUM for applicationArea
            createTicketPage.createTicket("TestTicket1",
                    "Settings",
                    "Test Description %^&*g",
                    false,
                    "10/10/1991",
                    "test@test.com",
                    "",
                    Arrays.asList("Bug"));


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