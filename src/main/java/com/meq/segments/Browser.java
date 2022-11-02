package com.meq.segments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Browser {

    public static WebDriver getBrowserInstance() throws InterruptedException, IllegalStateException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("platform", "Windows 10");
        chromeOptions.setCapability("browserVersion", "latest");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        chromeOptions.setCapability(ChromeOptions.CAPABILITY, options);
        chromeOptions.setCapability("extendedDebugging", "true");

        // build metadata
        chromeOptions.setCapability("name", "TODODynamicTestName");
        chromeOptions.setCapability("tags", Arrays.asList("invoker", "TODOinvoker"));
        chromeOptions.setCapability("build", "TODOGithash");

        // todo anything needed for browserstack
//        final String seleniumUrl = "https:// "; // TODO

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\elfwh\\Documents\\chromedriver.exe");


        // build webdriver object
        CompletableFuture<WebDriver> futureWebDriver = new CompletableFuture<>();
        WebDriver webDriver = null;
        int maxAttempts = 5;
        // attempt to create a webdriver object, dont fail on first failure
        for(int attempt=0; attempt < maxAttempts && webDriver == null; attempt++){
            System.out.println("Attempting to create client, attempt " + (attempt + 1) + " out of " + maxAttempts);
            if(attempt > 0){
                long sleep = attempt * 2000; // 2 seconds linear backoff
                System.out.println("Sleeping " + sleep + " milliseconds before trying to create WebDriver client again");
                Thread.sleep(sleep);

            }
            try {
//                URL webDriverUrl = new URL(seleniumUrl); // TODO enable for remote execution
                new Thread(() -> {
                    try {
//                        futureWebDriver.complete(new RemoteWebDriver(webDriverUrl, chromeOptions));
                        futureWebDriver.complete(new ChromeDriver());
                    } catch (Exception e) {
                        futureWebDriver.completeExceptionally(e);

                    }

                }).start();
                webDriver = futureWebDriver.get(20, TimeUnit.SECONDS);
            } catch (Exception e) {
                System.out.println("WARN!! failed to start the WebDriver client: " + e.getLocalizedMessage());
            }
        }

        if(webDriver == null) {
            throw new IllegalStateException("Was unable to create a WebDriver client");
        }

        return webDriver;
    }


    // TODO method for shutting down Browserstack connection
    public static void killWebDriver(WebDriver webDriver) {
        if (webDriver != null) {
            try {
                webDriver.close();
                webDriver.quit();
                System.out.printf("successfully shut down webdriver");
            } catch (Exception e) {
                System.out.println("ERROR! failed to shut down webdriver");
            }
        }
    }

}
