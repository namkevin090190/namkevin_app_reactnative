package PageObject.Pages;

import PageObject.Base.PageModel_Base;
import PageObject.Components.HeaderComponent;
import PageObject.Page_Model.Profile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ProfilePage extends Profile {


    public ProfilePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver= appiumDriver;
    }
}
