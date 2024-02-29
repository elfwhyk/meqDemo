package com.simpleFormApp.pages;

import com.simpleFormApp.Helpers;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CreateTicketPage {

    private final WebDriver browser;

    // TODO find appropriate selectors for each field
    private final By titleField = By.id("mat-input-5");
    private final By descriptionField = By.id("mat-input-5");
    private final By contactEmailField = By.id("mat-input-5");
    private final By tagsField = By.id("mat-input-5");
    private final By videoUrlField = By.id("mat-input-5");
    private final By resolvedCheckbox = By.id("mat-input-5");
    private final By dateField = By.id("mat-input-5");
    private final By submitTicket = By.id("");
    private final By bugTagCheckbox = By.cssSelector("#mat-option-20 > span");
    // TODO selectors for the tag checkboxes

    public CreateTicketPage(WebDriver browser) {
        this.browser = browser;
    }

    public static String baseUrl = "http://localhost:4200/create";

    public void navigateToCreateTicketPage() {
        System.out.println("Navigating to home page " + baseUrl);
        browser.get(baseUrl); // will fail out if cannot load page. Is there an implicit wait here?
        Helpers.waitForExistence(browser, titleField, 15 ); // wait for title field
    }

    // TODO proper error handling and error logging
    public void createTicket(String title, String applicationArea, String description, Boolean resolved, String date, String contactEmail, String videoUrl, List<String> tags) {
        browser.findElement(titleField).sendKeys(title);
        browser.findElement(descriptionField).sendKeys(description);
        browser.findElement(dateField).sendKeys(date);
        browser.findElement(contactEmailField).sendKeys(contactEmail);
        // todo check current state of the checkbox before checking or unchecking it
        if(resolved) {
            browser.findElement(resolvedCheckbox).click(); // this assumes it started unchecked ( which is true for a new ticket )
        }


        browser.findElement(submitTicket).click();
    }

    // TODO enum for tags
    private void selectTagsFromDropdown(List<String> tags) {
        browser.findElement(tagsField).click(); // this opens the dropdown
        // select each tag in the list if available
           // TODO log a warn for anything not selectable
        tags.forEach(tag -> {
            browser.findElement(tagSelectorByTagName(tag)).click(); // todo determine if this is already checked before checking it again
        });
    }

    private By tagSelectorByTagName(String tagName) {
        return By.cssSelector("");
    }
    private void selectApplicationAreaDropdown(String applicationArea) throws InterruptedException {
        // this is an old wrapper I used to use for retrying some navigation for resilliency
        int maxAttempts = 5;
        for(int i = 0; i < maxAttempts; i++) {
            try {
                // TODO navigate the dropdown ; I don't have time to implement this its been awhile
                break;
            } catch (StaleElementReferenceException e) {
                Thread.sleep(i * 500);
            }
        }


    }
}
