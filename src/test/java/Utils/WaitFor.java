package Utils;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static Utils.Constants.*;


public class WaitFor {

    AppiumDriver<MobileElement> appiumDriver;
    WebDriverWait wait;
    WebDriverWait wait_short;


    public WaitFor(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        wait = new WebDriverWait(this.appiumDriver, WAIT_SHORT_TIME);
        wait_short = new WebDriverWait(this.appiumDriver, WAIT_SHORT_TRYCATCH);
    }

    public MobileElement waitForClickAbleByElement(MobileElement element) {
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public MobileElement waitForClickAbleBySelector(By Selector) {
        return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(Selector));
    }

    public MobileElement waitForVisibilityOfSelector(By Selector) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(Selector));
    }

    public MobileElement waitForVisibilityOfElement(MobileElement Element) {
        return (MobileElement) wait.until(ExpectedConditions.visibilityOf(Element));
    }

    public boolean waitFortextToBePresentInElement(MobileElement Element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElement(Element, text));
    }

    public boolean waitFortextToBePresentInSelector(By Selector, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(Selector, text));
    }

    public boolean waitForinvisibilityOfSelector(By Selector) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(Selector));
    }

    public MobileElement waitForTryCatch_VissibilityOfSelector(By Selector) {
        return (MobileElement) wait_short.until(ExpectedConditions.visibilityOfElementLocated(Selector));
    }

    public MobileElement waitForTryCatch_ClickableBySelector(By Selector) {
        return (MobileElement) wait_short.until(ExpectedConditions.elementToBeClickable(Selector));
    }

    public void waitForTitleIs(String Title) {
        wait.until(ExpectedConditions.titleIs(Title));
    }

    public void waitForPresentBySelector(By Selector, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(Selector, text));
    }
}
