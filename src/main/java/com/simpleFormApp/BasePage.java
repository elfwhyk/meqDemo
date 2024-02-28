package com.simpleFormApp;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    public final WebDriver browser;
    private final String pageUrl;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        this.pageUrl = "http://localhost:4200/api/RequestForm";
    }

    public BasePage(WebDriver browser, String pageUrl) {
        this.browser = browser;
        this.pageUrl = pageUrl;
    }
}
