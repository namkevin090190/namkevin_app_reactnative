package Helpers.Reporter;

import Common.Log;
import Utils.API_Services.GoogleDrive;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

import static Helpers.Capture.ScreenshotEx.takeAScreenShot;
import static Helpers.Helpers.createFileNameImage;

public class Listener implements ITestListener {
    private static String link;

    public static String getLink() {
        return link;
    }

    GoogleDrive googleDrive;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        // TODO Auto-generated method stub
        Log.info("++++++++++++++++++++++++++ " + iTestContext.getName().toUpperCase() + " test Finished ++++++++++++++++++++++++++ ");
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.error("ERORR: " + result.getName() + "\n" + result.getThrowable());
        File screenShot = takeAScreenShot(result);
        try {
            AllureReportManager.attachImage(result.getName(), screenShot);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        try {
//            screenShot = TakeAsScreenShotAndAttachAllure(result);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if (googleDrive == null)
            googleDrive = new GoogleDrive();

        try {
            link = googleDrive.UploadFilePhoto(createFileNameImage(result), screenShot);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestStart(ITestResult iTest) {
        Log.info("++++++++++++++++++++++++++ " + iTest.getName().toUpperCase() + "  start testing .... ++++++++++++++++++++++++++ ");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("Testcase " + iTestResult.getName().toUpperCase() + " tested SUCCESS.");
    }





}