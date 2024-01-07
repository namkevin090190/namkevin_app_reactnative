package PageObject.Page_Model;

import Common.Log;
import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

public class CartStep1 extends PageModel_Base {


    protected By lst_txt_sumtotal_cart1_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_cartTotal'])");
    protected By txt_shipfee_cart1_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_shipfee'])");


    public CartStep1(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    protected MobileElement txt_Sumtotal_Cart1() {
        List<MobileElement> lst_btn = this.appiumDriver.findElements(lst_txt_sumtotal_cart1_Selector);
        return wait.waitForVisibilityOfElement(lst_btn.get(0));
    }

    protected MobileElement txt_Shipfee_Cart1() {
        return wait.waitForVisibilityOfSelector(txt_shipfee_cart1_Selector);
    }

    protected void clickOnButtonContinuesCart1() {
        //check check_ProductinCart1
        // => true: click vào btn tiếp tục
        // => false: vuốt xuống thử, check lại , nếu vẫn khôbg có thì tìm kiếm và add sản phẩm lại

        List<MobileElement> lst_btn = null;
        try {
            if (!check_ProductinCart1()) {
                swipeActions.SwipeDown();
                wait.waitForVisibilityOfSelector(txt_shipfee_cart1_Selector);
            }

            lst_btn = this.appiumDriver.findElements(lst_txt_sumtotal_cart1_Selector);
            if (lst_btn.size() == 1) {
                wait.waitForVisibilityOfElement(lst_btn.get(0)).click();
            } else
                wait.waitForVisibilityOfElement(lst_btn.get(1)).click();
        } catch (Exception ignored) {
        }
        if (lst_btn == null) {
            NavigateToHome()
                    .input_SearchProducts("banh socola").
                    NavigateToSearchResult()
                    .AddProduct_Random()
                    .click_IconCart_Header()
                    .NavigateToCartStep1()
                    .Click_DatHang();
        }
    }

    protected boolean check_ProductinCart1() {
        try {
            if (wait.waitForTryCatch_VissibilityOfSelector(txt_shipfee_cart1_Selector).isDisplayed()) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

}
