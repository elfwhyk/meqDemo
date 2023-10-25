package com.meq;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    public final WebDriver browser;
    private final String pageUrl;

    public BasePage(WebDriver browser) {
        this.browser = browser;
        this.pageUrl = "https://google.com";
    }

    public BasePage(WebDriver browser, String pageUrl) {
        this.browser = browser;
        this.pageUrl = pageUrl;
    }
}
