package Helpers.Reporter;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllureReportManager{

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String attachText(String name,String content) {
        return name + ": "+ content;
    }
    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    //Attach Video for Allure
    @Attachment(value = "{0}",type="video/avi")
    public static byte[] attachVideo(File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }

    //Attach Image for Allure
    @Attachment(value = "Image",type="image/png")
    public static byte[] attachImage(File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }

    //Attach Image for Allure
    @Attachment(value = "{fileName}",type="image/png")
    public static byte[] attachImage(String fileName, File file) throws Exception {
        return Files.readAllBytes(file.toPath());
    }


//    private static byte[] getFileImage(File file) throws Exception {
//        return Files.readAllBytes(file.toPath());
//    }


}
