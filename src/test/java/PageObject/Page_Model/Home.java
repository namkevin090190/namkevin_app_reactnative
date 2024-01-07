package PageObject.Page_Model;

import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class Home extends PageModel_Base {

    private final By txt_title_cate_home_Selector = MobileBy.AccessibilityId("title_cate");


    public Home(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }
}
