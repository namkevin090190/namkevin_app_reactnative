package PageObject.Pages;

import PageObject.Base.PageModel_Base;
import PageObject.Components.HeaderComponent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HistoriesPage extends PageModel_Base {

    public HistoriesPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }
    public HistoriesPage Click_IconCart_Header() {
        new HeaderComponent(this.appiumDriver).Click_IconCart_Header_Comp();
        return this;
    }
}
