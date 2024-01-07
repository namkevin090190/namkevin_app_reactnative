package PageObject.Page_Model;

import PageObject.Base.PageModel_Base;
import PageObject.Components.BoxProductComponent;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SearchResult extends PageModel_Base {



    public SearchResult(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public BoxProductComponent AddProductToCart() {
        return new BoxProductComponent(this.appiumDriver).AddProductToCartRandom_Comp();
    }
}
