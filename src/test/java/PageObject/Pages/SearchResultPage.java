package PageObject.Pages;

import PageObject.Base.PageModel_Base;
import PageObject.Components.BoxProductComponent;
import PageObject.Components.HeaderComponent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SearchResultPage extends PageModel_Base {

    public SearchResultPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    public SearchResultPage Click_IconCart_Header() {
        new HeaderComponent(this.appiumDriver).Click_IconCart_Header_Comp();
        return this;
    }

    public SearchResultPage AddProduct_Random() {
        new BoxProductComponent(this.appiumDriver).AddProductToCartRandom_Comp();
        return this;
    }
}
