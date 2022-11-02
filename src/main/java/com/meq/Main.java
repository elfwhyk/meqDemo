package com.meq;

import com.meq.segments.Browser;
import com.meq.segments.LoginSegments;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver browser = null;
        try {
            System.out.println("Hello world!");

            browser = Browser.getBrowserInstance();
            LoginSegments meQSegments = new LoginSegments(browser);

            meQSegments.navigateToMeqLogin();
            meQSegments.enterAndSubmitEmail("shane.burgoon@gmail.com");
            meQSegments.enterAndSubmitPasswordShouldLandOnDashboard("K45ufJ4CfZLhyCb$");
        } catch (Throwable t) {
            // take screenshot
            System.out.println("Failed doing some tests: " + t.getLocalizedMessage());
            t.printStackTrace();
            if(browser != null) {
                TakesScreenshot scrShot = ((TakesScreenshot) browser);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File(fileWithPath);
                FileUtils.copyFile(SrcFile, DestFile);
            }

        } finally {
            //        Browser.killWebDriver(browser);
            // TODO roll up results
            // TODO grab browser console logs
            // TODO grab browser network history
        }

    }
}