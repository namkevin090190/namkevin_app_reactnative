package PageObject.Pages;

import PageObject.Page_Model.CartStep1;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class CartStep1Page extends CartStep1 {

    public CartStep1Page(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }


    public CartStep1Page Click_DatHang() {
        Click_DatHang_ProcessBar_Cart1();
        return this;
    }

    public CartStep1Page Click_DatHang_ProcessBar_Cart1() {
        clickOnButtonContinuesCart1();
        return this;
    }

    public String get_Sumtotal_Cart1() {
        return get_TextAsMoney(this.txt_Sumtotal_Cart1());
    }

//    public String get_Sumtotal_ProcessBar_Cart1() {
//        return get_TextAsMoney(this.txt_Sumtotal_ProcessBar_Cart1());
//    }

    public String get_Shipfee_Cart1() {
        return get_TextAsMoney(this.txt_Shipfee_Cart1());
    }

    public CartStep1Page verify_SumTotal(String actual, String expected) {
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
        return this;
    }

    private CartStep1Page check_ProductInCart() {
        check_ProductinCart1();
        return this;
    }


}
