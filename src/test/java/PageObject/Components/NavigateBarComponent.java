package PageObject.Components;

import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class NavigateBarComponent extends PageModel_Base {

    private final By icn_tab_home_Selector = MobileBy.xpath("(//android.widget.ImageView[@resource-id='tab_Home'])");
    private final By icn_tab_promotion_Selector = MobileBy.xpath("(//android.widget.ImageView[@resource-id='tab_Promotion'])");
    private final By tab_cart_Selector = MobileBy.xpath("(//android.widget.ImageView[@resource-id='tab_Cart'])");
    private final By icn_tab_notification_Selector = MobileBy.xpath("(//android.widget.ImageView[@resource-id='tab_Notification'])");
    private final By icn_tab_Account_Selector = MobileBy.xpath("(//android.widget.ImageView[@resource-id='tab_Profile'])");


    public NavigateBarComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    private MobileElement Btn_Home_Tab_Nav() {
        return wait.waitForClickAbleBySelector(icn_tab_home_Selector);
    }

    private MobileElement Btn_Promotion_Tab_Nav() {
        return wait.waitForClickAbleBySelector(icn_tab_promotion_Selector);
    }

    private MobileElement Btn_Cart_Nav() {
        return wait.waitForClickAbleBySelector(tab_cart_Selector);
    }

    private MobileElement Btn_Notify_Tab_Nav() {
        return wait.waitForClickAbleBySelector(icn_tab_notification_Selector);
    }

    private MobileElement Btn_Account_Tab_Nav() {
        return wait.waitForClickAbleBySelector(icn_tab_Account_Selector);
    }

    public NavigateBarComponent click_Tab_Profile() {
        Btn_Account_Tab_Nav().click();
        return this;
    }
    public NavigateBarComponent click_Tab_Home() {
        Btn_Home_Tab_Nav().click();
        return this;
    }

}
