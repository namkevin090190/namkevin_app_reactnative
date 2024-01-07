package TestScript;


import Common.Log;
import Utils.API_Services.Line;
import Utils.PropertiesFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static DriverFactory.AppiumDriverEx.*;
import static Helpers.Helpers.*;
import static org.apache.commons.lang3.SystemUtils.getHostName;


public class BaseTest {

    protected AppiumDriver<MobileElement> appiumDriver;


    @Parameters({"Udid"})
    @BeforeSuite
    public void SetUpDriver() {
//        deleteAllFilesFromDirectory(getProjectPath() + "//target//allure-results");
        deleteAllFilesFromDirectory(getProjectPath() + "//allure-results");
        Log.info("Deleted allure results");
        appiumDriver = InitAppiumDriver();

    }

//    @BeforeClass
//    public void getDriver() {
//        this.appiumDriver = getAppiumDriver();
//    }


    @AfterTest(alwaysRun = true)
    public void closeDriver() {
        PropertiesFile.setPropertiesFile("configs.properties");
        if (PropertiesFile.getPropValue("isUninstall").equalsIgnoreCase("true")) {
            getDriver().removeApp("com.bachhoaxanh");
            Log.info("Uninstall com.bachhoaxanh success!");
        }
//        ExcuteCommandCMD("allure serve target/allure-results");
        CloseDriver();

    }

    @AfterSuite(alwaysRun = true)
    public void DeleteFile() {
//        String ScreenShotFolderPath = getProjectPath().concat("//ScreenShots");
//        String CaptureFolderPath = getProjectPath().concat("//Videos");
        PropertiesFile.setPropertiesFile("configs.properties");
       if(PropertiesFile.getPropValue("isDeleteFile").equalsIgnoreCase("true")){
           deleteAllFilesFromDirectory(getProjectPath().concat("//ScreenShots"));
           deleteAllFilesFromDirectory(getProjectPath().concat("//Videos"));
           Log.info("Deleted all files in ScreenShots and Videos");
       }
    }

    private String CreateMesagebasic(ITestResult result) {
        PropertiesFile.setPropertiesFile("configs.properties");
        String methodName = result.getName();
        String msg = result.isSuccess() ? methodName.concat(" - ").concat("PASSED!") : methodName.concat(" - ").concat("FAILED");
        String domain = PropertiesFile.getPropValue("domain");
        return domain.concat(": " + msg);
    }

    public void SendNotifyToLine(ITestResult result, String OderID) {
        String msgToPost = CreateMesagebasic(result)
                .concat(" - OderID - " + OderID)
                .concat(" - " + getHostName());
        Line.pushNotifyToLine(msgToPost);
    }

    public void SendNotifyToLine(ITestResult result, String OderID, String link, String linkrc) {
        String msgToPost = CreateMesagebasic(result)
                .concat(" - OderID - " + OderID)
                .concat(" - " + getHostName())
                .concat("\nScreenshot when error: " + link)
                .concat("\n LinkRC: " + linkrc);
        Line.pushNotifyToLine(msgToPost);
    }


//
//    public void UninstallAfterTest() {
//        PropertiesFile.setPropertiesFile("configs.properties");
//        String Command = "adb uninstall com.bachhoaxanh";
//        runCMDwithCommand(Command);
//    }

}
