package Helpers.Capture;


import Helpers.Helpers;
import Helpers.Reporter.AllureReportManager;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import static DriverFactory.AppiumDriverEx.getDriver;
import static Helpers.Helpers.createFileNameImage;

public class ScreenshotEx {

    public static void takeWholeScreenShot() {
        String imagePath = Helpers.getProjectPath() + File.separator + "ScreenShot" + File.separator + "whole_screen.png";
        File image = getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(image, new File(imagePath));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void takeElemScreenShot(MobileElement element, String fileName) {
        String csScreenLocation = Helpers.getProjectPath() + File.separator + "ScreenShot" + File.separator + fileName + ".png";
        File elemScreenShot = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(elemScreenShot, new File(csScreenLocation));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static File takeAScreenShot(ITestResult result) {
        String fileLocation = Helpers.getProjectPath() + File.separator + "ScreenShots" + File.separator + createFileNameImage(result);
        // Save screenshot to the system and attach to Allure report
        File screenShot = getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenShot, new File(fileLocation));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return screenShot;
    }


}
