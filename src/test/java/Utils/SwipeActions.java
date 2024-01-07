package Utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.testng.Assert;

import java.time.Duration;

import static Utils.Sleep.sleepInSeconds;


public class SwipeActions {

    private AppiumDriver<MobileElement> appiumDriver;
    private Dimension mobileScreenSize;
    TouchAction touchAction;

    int screenHeight;
    int screenWidth;

    public SwipeActions(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        this.touchAction = new TouchAction(this.appiumDriver);
        this.mobileScreenSize = appiumDriver.manage().window().getSize();
    }

    private void SwipeFromPointToPoint(PointOption FromElement, PointOption ToElement) {
        touchAction
                .press(FromElement)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(ToElement)
                .release()
                .perform();
    }

    private void VerifyElement(MobileElement Element) {
        Assert.assertTrue(Element.isDisplayed(), "Element is not display");
    }

    private PointOption GetLocationElement(MobileElement Element) {
        VerifyElement(Element);
        Point Element_Point = Element.getLocation();
        return new PointOption<>().withCoordinates(Element_Point);
    }

    private void swipeWithDirection(String direction) {
        int xStartPoint = mobileScreenSize.getWidth() / 2;
        int xEndpointPoint = mobileScreenSize.getWidth() / 2;
        int yStartPoint = mobileScreenSize.getHeight() / 2;
        int yEndpoint = mobileScreenSize.getHeight() / 2;

        switch (direction) {
            case "up":
                yStartPoint = (int) (mobileScreenSize.getHeight() * 0.8);
                yEndpoint = (int) (mobileScreenSize.getHeight() * 0.2);
                break;
            case "down":
                yStartPoint = (int) (mobileScreenSize.getHeight() * 0.2);
                yEndpoint = (int) (mobileScreenSize.getHeight() * 0.8);
                break;
            case "left":
                xStartPoint = (int) (mobileScreenSize.getWidth() * 0.8);
                xEndpointPoint = (int) (mobileScreenSize.getWidth() * 0.2);
                break;
            case "right":
                xStartPoint = (int) (mobileScreenSize.getWidth() * 0.2);
                xEndpointPoint = (int) (mobileScreenSize.getWidth() * 0.8);
                break;
        }
        PointOption startPoint = PointOption.point(xStartPoint, yStartPoint);
        PointOption endPoint = PointOption.point(xEndpointPoint, yEndpoint);
        touchAction.press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(1000)))
                .moveTo(endPoint)
                .release().perform();
    }

    public void SwipeFromElementToElement(MobileElement ElementFrom, MobileElement ElementTo) {
        SwipeFromPointToPoint(GetLocationElement(ElementFrom), GetLocationElement(ElementTo));
    }

    //SwipeUp = ScrollDown (Swipe with direct from botton to top)
    public SwipeActions SwipeUp() {
        screenHeight = mobileScreenSize.getHeight();
        screenWidth = mobileScreenSize.getWidth();

        int xStartPoint = (50 * screenWidth) / 100;
        int yStartPoint = (50 * screenHeight) / 100;

        int xEndPoint = xStartPoint;
        int yEndPoint = (10 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);
//        PointOption midpoin = new PointOption().point(xEndPoint,yEndPoint);

        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();
        return this;
    }

    public SwipeActions SwipeDown() {
        screenHeight = mobileScreenSize.getHeight();
        screenWidth = mobileScreenSize.getWidth();

        int xStartPoint = (50 * screenWidth) / 100;
        int yStartPoint = (30* screenHeight) / 100;

        int xEndPoint = xStartPoint;
        int yEndPoint = (80 * screenHeight) / 100;

        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        touchAction
                .press(startPoint)
                .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(2)))
                .moveTo(endPoint)
                .release()
                .perform();
        return this;
    }

    public SwipeActions SwipeUpWithTimes(int times) {

        for (int i = 1; i <= times; i++) {
            SwipeUp();
            sleepInSeconds(3);
        }
        return this;
    }



    public void SwipeFromElementToElementWithTimes(MobileElement ElementFrom, MobileElement ElementTo, int times) {
        VerifyElement(ElementFrom);
        VerifyElement(ElementTo);
        PointOption PointOP_From = GetLocationElement(ElementFrom);
        PointOption PointOP_To = GetLocationElement(ElementTo);
        for (int i = 1; i <= times; i++) {
            SwipeFromPointToPoint(PointOP_From, PointOP_To);
        }
    }


}
