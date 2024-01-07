package PageObject.Base;

import Common.Log;
import PageObject.Components.HeaderComponent;
import PageObject.Components.NavigateBarComponent;
import PageObject.Pages.*;
import Utils.SwipeActions;
import Utils.WaitFor;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static Helpers.Reporter.AllureReportManager.saveTextLog;

public class PageModel_Base {

    protected AppiumDriver<MobileElement> appiumDriver;
    protected WaitFor wait;
    protected SwipeActions swipeActions;
    protected Random random;
    protected WebDriverWait webDriverWait;
    public SoftAssert softAssert;

    protected PageModel_Base(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        wait = new WaitFor(this.appiumDriver);
        swipeActions = new SwipeActions(this.appiumDriver);
        random = new Random();
        webDriverWait = new WebDriverWait(this.appiumDriver, 300L, 10);
        softAssert = new SoftAssert();

    }

    public CartStep1Page NavigateToCartStep1() {
        return new CartStep1Page(this.appiumDriver);
    }
    public CartStep2Page NavigateToCartStep2() {
        return new CartStep2Page(this.appiumDriver);
    }

    public HomePage NavigateToHome() {
        return new HomePage(this.appiumDriver);
    }

    public SearchResultPage NavigateToSearchResult() {
        return new SearchResultPage(this.appiumDriver);
    }

    public ProfilePage NavigateToProfile() {
        return new ProfilePage(this.appiumDriver);
    }

    public CartStep3Page NavigateToCartStep3() {
        return new CartStep3Page(this.appiumDriver);
    }

    public HeaderComponent input_SearchProducts(String keyword) {
        return new HeaderComponent(this.appiumDriver).input_SearchProduct_Header_Comp(keyword);
    }

    public HeaderComponent click_IconCart_Header() {
        return new HeaderComponent(this.appiumDriver).Click_IconCart_Header_Comp();
    }

    public HeaderComponent BacktoHome_Menu() {
        return new HeaderComponent(this.appiumDriver).BackToHomeFromMenu_Comp();
    }

    public boolean check_isAtHome() {
        return new HeaderComponent(this.appiumDriver).check_isHome_Comp();
    }

    public String get_TextAsMoney(MobileElement Element) {
        return Element.getText().replace("₫", "").trim();
    }

    public PageModel_Base verify_Equals(String actual, String expected) {
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
        return this;
    }

    public PageModel_Base verify_Contains(String root, String text) {
        softAssert.assertTrue(root.contains(text));
        softAssert.assertAll();
        return this;
    }

    public NavigateBarComponent NavigateToBarComponent() {
        return new NavigateBarComponent(this.appiumDriver);
    }

    public AccountPage NavigateToAccountPage() {
        return new AccountPage(this.appiumDriver);
    }

    protected boolean check_textIsEmty(MobileElement mobileElement) {
        return mobileElement.getText().isEmpty();
    }

    protected boolean check_equalText(MobileElement mobileElement, String textExpected) {
        return mobileElement.getText().equals(textExpected);
    }

    protected void handle_TextExist(MobileElement mobileElement, String textExpected) {

        if (!check_textIsEmty(mobileElement)) {
            if (!check_equalText(mobileElement, textExpected)) {
                mobileElement.click();
                String exist_Text = mobileElement.getText();
                Log.info(exist_Text + " does exist");
                saveTextLog(exist_Text + " does exist");
                mobileElement.clear();
                mobileElement.sendKeys(textExpected);
                Log.info(textExpected + " typed");
                saveTextLog(textExpected + " typed");
            }
        } else {
            mobileElement.click();
            mobileElement.sendKeys(textExpected);
            Log.info("input: " + textExpected);
            saveTextLog("input: " + textExpected);
        }
    }




}
