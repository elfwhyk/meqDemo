package com.meq;

import com.meq.segments.Browser;
import com.meq.segments.NavBarSegments;
import com.meq.segments.LoginSegments;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        WebDriver browser = null;
        try {
            browser = Browser.getBrowserInstance();
            LoginSegments meQSegments = new LoginSegments(browser);
            NavBarSegments navBarSegments = new NavBarSegments(browser);

            meQSegments.navigateToMeqLogin();
            // TODO load from secure credential store in github actions, or some protected service
            meQSegments.enterAndSubmitEmail("shane.burgoon@gmail.com");
            meQSegments.enterAndSubmitPasswordShouldLandOnDashboard("K45ufJ4CfZLhyCb$");

            // TODO validate navBar exists from each page, navigation from each page may be overkill

//            navBarSegments.navigateToProfilePage();
//            navBarSegments.navigateToSkillsPage();
//            navBarSegments.navigateToActivitiesPage();
//            navBarSegments.navigateToJournalPage();
//            navBarSegments.navigateToSavedForLaterPage();


        } catch (Throwable t) {
            System.out.println("Failed doing some tests: " + t.getLocalizedMessage());
            t.printStackTrace();
            // TODO take screenshot
//            if(browser != null) {
//                TakesScreenshot scrShot = ((TakesScreenshot) browser);
//                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//                File DestFile=new File("/screenshots");
//                FileUtils.copyFile(SrcFile, DestFile);
//            }
        } finally {
            // roll up results
            if(browser != null) {
                // grab browser console logs
                System.out.println("printing browser console logs");
                for (LogEntry entry : browser.manage().logs().get(LogType.BROWSER)) {
                    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
                    //do something useful with the data
                }
                Path currentRelativePath = Paths.get("");
                String s = currentRelativePath.toAbsolutePath().toString();
                // handle windows filesystem
                s = s.replace("\\C", "C//");
                s = s.replace("\\","/");
                String screenshotPath = s + "/src/screenshots/" + UUID.randomUUID() + ".png";
                System.out.println("screenshot path " + screenshotPath);

                TakesScreenshot screenshot = ((TakesScreenshot) browser);
                File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
                File destinationFile = new File(screenshotPath);
                FileUtils.copyFile(sourceFile, destinationFile);

                // TODO grab browser network history
            }
//            Browser.killWebDriver(browser);
        }

    }
}