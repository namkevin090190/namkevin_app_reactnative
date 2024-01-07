package DriverFactory;

import Common.Log;
import DriverFactory.CapabilitiesEX.MobileCapabilityTypeEx;
import Helpers.Helpers;
import Utils.PropertiesFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppiumDriverEx {

    private static AppiumDriver<MobileElement> appiumDriver = null;
    static AppiumDriverLocalService appiumServer;

    public static AppiumDriver<MobileElement> InitAppiumDriver() {
        PropertiesFile.setPropertiesFile("configs.properties");
        String Udid = PropertiesFile.getPropValue("Udid");
        String apkPathEmulator = PropertiesFile.getPropValue("apkPathEmulator");
        String apkPathRealDevice = PropertiesFile.getPropValue("apkPathRealDevice");


        AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();
        try {

            appiumServiceBuilder.withIPAddress("127.0.0.1").usingAnyFreePort();
//            /* Use AndroidServerFlagEx, extended from AndroidSeverFlag, for automatically discovering compatible Chrome driver */
//            appiumServiceBuilder.withArgument(AndroidServerFlagEx.ALLOW_INSECURE, "chromedriver_autodownload");
            appiumServer = AppiumDriverLocalService.buildService(appiumServiceBuilder);
            appiumServer.start();
            // Setup DesiredCapabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.PLATFORM_NAME, "Android");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTOMATION_NAME, "uiautomator2");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.UDID, Udid);
            if (Udid.contains("emulator")) {
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP, Helpers.getProjectPath() + apkPathEmulator);
                Log.info("Installed x86");
            } else {
                desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP, Helpers.getProjectPath() + apkPathRealDevice);
                Log.info("Installed armebi-v7a");
            }
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_PACKAGE, "com.bachhoaxanh");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.APP_ACTIVITY, "com.bachhoaxanh.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.AUTO_GRANT_PERMISSIONS, "true");
            desiredCapabilities.setCapability(MobileCapabilityTypeEx.NEW_COMMAND_TIMEOUT, "360");


            // tạo 1 AndroidDriver có thông số Capability
            appiumDriver = new AndroidDriver<>(appiumServer.getUrl(), desiredCapabilities);
            if (appiumDriver != null) {
//                appiumDriver.manage().timeouts().implicitlyWait(Constants.WAIT_LONG_TIME,TimeUnit.SECONDS);
                Log.info("Init appiumDriver successfully.");
                Log.info("SessionID: " + appiumDriver.getSessionId());
            } else
                Log.info("Error when connecting to Appium Server!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appiumDriver;
    }

    public static void CloseDriver() {
        if (appiumDriver != null) {
            appiumDriver.quit();
        }
        if (appiumServer != null) {
            appiumServer.stop();
        }
    }

    public static AppiumDriver<MobileElement> getDriver() {
        return appiumDriver;
    }
}

