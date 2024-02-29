package com.simpleFormApp.pages;

import com.simpleFormApp.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver browser;

    public HomePage(WebDriver browser) {
        this.browser = browser;
    }

    public static String baseUrl = "http://localhost:4200/";

    public void navigateToHomePage() {
        System.out.println("Navigating to home page " + baseUrl);
        browser.get(baseUrl); // will fail out if cannot load page. Is there an implicit wait here?
        Helpers.waitForExistence(browser, By.cssSelector("body > app-root > app-dashboard > a"), 15 ); // wait for "Create ticket" link
    }

    public void clickCreateTicket() {
        browser.findElement(By.cssSelector("body > app-root > app-dashboard > a")).click();
    }

    // TODO edit
    public void editTicketByTitle(String ticketTitle) {

    }

    public void deleteTicketByTitle(String ticketTitle) {
        browser.findElement(findTicketByTitle(ticketTitle));

    }

    // TODO find the row in the page table for a given ticket title
    private By findTicketByTitle(String ticketTitle) {
        // this will return the first ticket with the given title from top to bottom of the page. Not ideal...
        // TODO iterate over the table, checking each row for the title desired. I think given the HTML this may be the only way?
        return By.cssSelector("");
    }

    // would this be better as its own page object for a table row? TBD

}
