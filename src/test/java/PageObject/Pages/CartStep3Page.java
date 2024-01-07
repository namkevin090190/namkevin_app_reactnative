package PageObject.Pages;

import Common.Log;
import Models.OrderData;
import PageObject.Components.CancelOderComponent;
import PageObject.Page_Model.CartStep3;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.Map;

import static Helpers.Reporter.AllureReportManager.saveTextLog;

public class CartStep3Page extends CartStep3 {

    public CartStep3Page(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public CartStep3Page Cancel_Oder() {
//        this.txt_Customer_Info_Cart3();
        swipeActions.SwipeUp();
        new CancelOderComponent(this.appiumDriver).Cancel_Oder_Comp();
        return this;
    }

    public CartStep3Page Verify_OderInfo(Map<String, String> data) {
        wait.waitForTryCatch_VissibilityOfSelector(txt_title_oder_results_cart3_Selector);
        Log.info("Submit oder successfully!");
        saveTextLog("Submit oder successfully!");

        if (data.get(OrderData.getOderType()).equalsIgnoreCase("Delivery")) {
            if (check_isMessageDisplay()) {
                Verify_MessageOder_Cart3(data.get(OrderData.getCustomerGender()), data.get(OrderData.getCustomerName()), data.get(OrderData.getOtherGender()), data.get(OrderData.getOtherName()));
                Verify_Info_Customer_Cart3(data.get(OrderData.getCustomerGender()), data.get(OrderData.getCustomerName()), data.get(OrderData.getCustomerPhone()));
                Verify_Info_Other_Cart3(data.get(OrderData.getOtherGender()), data.get(OrderData.getOtherName()), data.get(OrderData.getOtherPhone()));
            } else {
                Verify_Info_Other_Cart3(data.get(OrderData.getCustomerGender()), data.get(OrderData.getCustomerName()), data.get(OrderData.getCustomerPhone()));
            }
            String expected_address = data.get(OrderData.getAddress()).trim().concat(", ").concat(data.get(OrderData.getLocationSelected().trim()));
            Verify_Address_Cart3(expected_address);

        } else {
            String expected_address = "Bách hóa XANH " + data.get(OrderData.getAddress()).trim().replace(" - ",", ");
            Verify_Address_Cart3(expected_address);
            Verify_Info_Other_Cart3(data.get(OrderData.getCustomerGender()), data.get(OrderData.getCustomerName()), data.get(OrderData.getCustomerPhone()));
        }

        Verify_ShipDateTime(data.get(OrderData.getShipDate()), data.get(OrderData.getShipTime()));
        Verify_Payment(data.get(OrderData.getPaymentType()));
        Verify_SumTotal_Cart3(data.get(OrderData.getCartTotalCart2()));
        softAssert.assertAll();
        return this;
    }

    public boolean check_isAtCart3Page() {
        try {
            wait.waitForTryCatch_VissibilityOfSelector(txt_title_oder_results_cart3_Selector);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private void Close_PopupCusReview() {
        try {
            if (this.btn_Close_Popup_CusReview().isDisplayed()) {
                this.btn_Close_Popup_CusReview().click();
            }
        } catch (Exception ignored) {
        }
    }
    public String getOderID() {
        Close_PopupCusReview();
        try {
            return wait.waitForVisibilityOfSelector(txt_oderid_cart3_Selector).getText();
        } catch (Exception ignored) {
        }
        return null;
    }

    private boolean check_isMessageDisplay() {
        try {
            wait.waitForTryCatch_VissibilityOfSelector(txt_message_oder_cart3_Selector);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }


}
