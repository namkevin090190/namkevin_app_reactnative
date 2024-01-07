package PageObject.Pages;

import Common.Log;
import Models.OrderData;
import PageObject.Components.ListProfileComponent;
import PageObject.Page_Model.CartStep2;
import Utils.Constants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;

import java.util.Map;

import static DriverFactory.AppiumDriverEx.getDriver;
import static Helpers.Reporter.AllureReportManager.saveTextLog;
import static Utils.Sleep.sleepInSeconds;

public class CartStep2Page extends CartStep2 {
    ListProfileComponent listProfileComponent;

    public CartStep2Page(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        if (listProfileComponent == null)
            listProfileComponent = new ListProfileComponent(appiumDriver);
    }


    public long handle_listaddress(Map<String, String> data) {
        long addressID = 0;
        sleepInSeconds(1.5);
        if (listProfileComponent.check_ListProfile_isDisplayed()) {
            if (!listProfileComponent.find_Address_RecieverEqualsLocation(data)) {
                if (data.get(OrderData.getOtherName()).isEmpty() && data.get(OrderData.getOtherPhone()).isEmpty()) {
                    listProfileComponent.Add_Address_Other(data.get(OrderData.getAddress()));
//                    input_OtherCustomerInfo(data.get(OrderData.getOtherName()), data.get(OrderData.getOtherPhone()));
                } else {
                    listProfileComponent.Address_with_OtherInfo(data);
                }
                listProfileComponent.findAdress_WithInputLocation(data);
            }
        } else {
            input_CustomerInfo(data.get(OrderData.getCustomerPhone()), data.get(OrderData.getCustomerName()), data.get(OrderData.getAddress()));
            String gender = choose_Gender();
            data.put(OrderData.getCustomerGender(), gender);
            Log.info("Selected Gender : " + gender);
        }
        return addressID;
    }


    @Step("Input Customer Info")
    public CartStep2Page input_CustomerInfo(String phoneNumber, String fullName, String address) {
        if (check_LocationIsShophouse()) {
            input_PhoneNumber_Cart2(phoneNumber);
            input_FullName_Cart2(fullName);
        } else {
            input_PhoneNumber_Cart2(phoneNumber);
            input_FullName_Cart2(fullName);
            input_Address_Cart2(address);
        }
        return this;
    }

    public CartStep2Page input_OtherCustomerInfo(String OtherfullName, String OtherphoneNumber) {
        int i = 0;
        while (i <= 3) {
            try {
                wait.waitForTryCatch_ClickableBySelector(txt_other_fullname_cart2_Selector).isDisplayed();
//                Log.info("Đã click gọi");
                break;
            } catch (Exception ignored) {
                chk_Call_Other_Cart2().click();
            }
            i++;
        }
        input_OtherFullName_Cart2(OtherfullName);
        try {
            input_OtherPhoneNumber_Cart2(OtherphoneNumber);
        } catch (Exception ignored) {
            swipeActions.SwipeFromElementToElement(label_Address_Shipping_Cart2(), btn_BackToCart1_Cart2());
            input_OtherPhoneNumber_Cart2(OtherphoneNumber);
        }
        return this;
    }

    public String choose_Other_Gender() {
        return choose_Other_Gender_Random_Cart2();
    }

    public String choose_Gender() {
        return choose_Gender_Random_Cart2();
    }

    @Step("Choose ship date time")
    public CartStep2Page choose_ShipDateTime() {
//        Scroll_ChooseShipDateTime();
        Swipe_chooseShipDate();
        choose_ShipDateTime_Random_Cart2();
        return this;
    }

    @Step("Choose payment type")
    public CartStep2Page choose_Payment(String payment) {
        click_btnLoadmorePayment();
        swipeActions.SwipeFromElementToElement(this.label_Choose_Payment_Cart2(), this.label_Choose_Ship_DateTime_Cart2());
        if (payment.equalsIgnoreCase("Tiền mặt")) {
            choose_Payment_Type_Cash_Cart2();
        } else {
            choose_Payment_Cart2(payment);
        }
        return this;
    }

    @Step("Submit oder")
    public CartStep2Page click_SubmitOder() {
        btn_Submitoder_Cart2().click();
        Log.info("Submit oder!");
        return this;
    }

    public CartStep2Page Select_Shophouse() {
        select_Shophouse_Cart2();
        return this;

    }


    protected String get_TotalProduct_Cart2() {
        return get_TextAsMoney(txt_Total_Product_Cart2());
    }

    protected String get_TotalShipFee_Cart2() {
        return get_TextAsMoney(txt_Total_Shipfee_Cart2());
    }

    public String get_SumTotal_Cart2() {
        return get_TextAsMoney(btn_Submitoder_Cart2()).replace("HOÀN TẤT MUA", "").replace("THANH TOÁN", "").trim();
    }

    public CartStep2Page verify_SumTotal(String actual, String expected) {
        softAssert.assertEquals(actual, expected);
        softAssert.assertAll();
        return this;
    }

    protected String get_Gender() {
        return customer_gender_selected;
    }

    @Step("Verify rules Shophouse")
    @Description("Verify rules Shophouse: rule freeship , off swipe card.")
    public CartStep2Page verify_Rules_Shophouse() {
        softAssert.assertTrue(check_RuleShipFee());
        softAssert.assertTrue(check_Off_SwipeCard_Cart2(), "Swipe Card type is on");
        softAssert.assertAll();
        Log.info("Verified rule ShipFee and Off SwipeCard");
        saveTextLog("Verified rule ShipFee and Off SwipeCard");
        return this;
    }

    public String getShipDate() {
        return ddl_Shipdate_Cart2().getText();
    }

    public String getShipTime() {
        if (ddl_Shiptime_Cart2().getText().contains("Thời gian")) {
            sleepInSeconds(1.5);
            return ddl_Shiptime_Cart2().getText();
        }
        return ddl_Shiptime_Cart2().getText();
    }

    public String getAddressShophouse() {
        return wait.waitForTryCatch_VissibilityOfSelector(txt_address_shophouse_cart2_Selector).getText();
    }


    public void SwipeCollapseAddress() {
        if (listProfileComponent.TXT_isApartment_Displayed()) {
            swipeActions.SwipeFromElementToElementWithTimes(label_Address_Shipping_Cart2(), btn_BackToCart1_Cart2(), 3);
        } else {
            swipeActions.SwipeFromElementToElement(label_Address_Shipping_Cart2(), btn_BackToCart1_Cart2());
        }

    }

    public void select_Delivery() {
//        try {
//            if(check_LocationIsShophouse())
//            {
//                if(!isDelivery())
//                {
//                    this.txt_Delivery().click();
//                    this.txt_Address_Cart2().isDisplayed();
//                }
//            }
//
//        } catch (Exception ignored) {
//
//        }
        do {
            try {
                this.txt_Delivery_Cart2().click();
                this.txt_Address_Cart2().isDisplayed();
            } catch (StaleElementReferenceException ex) {
                MobileElement element = getDriver().findElement(txt_delivery_cart2_Selector);
                element.click();
            } catch (Exception ignored) {
                this.txt_Delivery_Cart2().click();
//                Log.info("đã click shophouse lần 2");
                wait.waitForTryCatch_VissibilityOfSelector(txt_address_cart2_Selector);
            }
        } while (!this.txt_Address_Cart2().isDisplayed());


    }


    public void check_Address_Shipping_Cart2() {
        select_Shophouse_Cart2();
        select_Delivery();
        String text = this.txt_Address_Cart2().getText();
        Log.info(": " + text);
        softAssert.assertTrue(!text.contains(Constants.ADDRESS_SHOPHOUSE), "[ERR] - Lỗi địa chỉ shophouse ịn qua bên địa chỉ delivery");
        softAssert.assertAll();
        Log.info("Đã verify address");
    }
}
