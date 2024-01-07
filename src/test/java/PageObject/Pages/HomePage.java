package PageObject.Pages;

import PageObject.Base.PageModel_Base;
import PageObject.Components.BoxProductComponent;
import PageObject.Components.HeaderComponent;
import PageObject.Components.NavigateBarComponent;
import PageObject.Page_Model.Home;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage extends Home {

    @AndroidFindBy(accessibility = "boxsp_txt_buy")
    private List<MobileElement> lst_btn_Buy_BoxSP;


    By lst_btn_Buy_boxSP = MobileBy.AccessibilityId("boxsp_txt_buy");

    public HomePage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }


    public HomePage AddProduct_Random() {
        swipeActions.SwipeUpWithTimes(7);
        new BoxProductComponent(this.appiumDriver).AddProductToCartRandom_Comp();
        return this;
    }

    public String Chosse_Location() {
        return new HeaderComponent(this.appiumDriver).chooseLoation_Random_Header_Comp();
    }

    public HeaderComponent Click_IconCart_Header() {
        return new HeaderComponent(this.appiumDriver).Click_IconCart_Header_Comp();
    }

    public HomePage ChooseLocationWithInput(String province, String district, String ward) {
        new HeaderComponent(this.appiumDriver).chooseLoation_Header_Comp(province, district, ward);
        return this;
    }

    public HomePage click_Tab_Profile() {
        new NavigateBarComponent(this.appiumDriver).NavigateToProfile();
        return this;
    }

}
