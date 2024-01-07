package PageObject.Page_Model;

import Common.Log;
import PageObject.Base.PageModel_Base;
import Utils.StringHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.Assert;

import java.util.List;

import static DriverFactory.AppiumDriverEx.getDriver;
import static Helpers.Reporter.AllureReportManager.saveTextLog;
import static Utils.RandomEx.RandomNextInt;
import static Utils.RandomEx.RandomlySelectOneFromList;
import static Utils.Sleep.sleepInSeconds;

public class CartStep2 extends PageModel_Base {

    private final By btn_backtocart1_Selector = MobileBy.xpath("(//android.widget.TextView[@text='Xem lại giỏ hàng'])");
    private final By txt_fullname_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[2]/(android.widget.EditText)");
    private final By rdo_gender_Anh_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_textinput_male'])");
    private final By rdo_gender_Chi_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_textinput_female'])");
    private final By txt_phonenumber_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[1]/(android.widget.EditText)");
    protected final By txt_address_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[3]/(android.widget.EditText)");
    private final By chk_isApartment_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_checkbox_isApartment'])");
    private final By chk_call_oder_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_checkbox_callOther'])");
    private final By rdo_other_gender_Anh_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_radio_maleOther'])");
    private final By rdo_other_gender_Chi_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_radio_femaleOther'])");
    //    protected final By txt_other_fullname_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[4]/(android.widget.EditText)");
    protected final By txt_other_fullname_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_other_info']//android.view.ViewGroup[@resource-id='txt_input_view'])[1]/android.widget.EditText");
    protected final By txt_other_phonenumber_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_other_info']//android.view.ViewGroup[@resource-id='txt_input_view'])[2]/android.widget.EditText");

    private final By ddl_shipdate_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='span'])");
    private final By ddl_shiptime_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_dropdown_select_time'])");
    //    private final By lst_shipdate_cart2_Selector = MobileBy.AccessibilityId("cart_dropdown_select_date_view");
    private final By txt_payment_type_cash_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='txt_cashOnDelivery'])");
    private final By lst_shipdate_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='cart_dropdown_select_date_view'])");
    private final By lst_shiptime_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_dropdown_select_time_list'])");
    private final By btn_payment_Selected_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='cart_payment_select_checked'])");
    private final By lst_payment_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_payment_select'])");
    private final By btn_payment_loadmore_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_payment_loadMore'])");
    private final By btn_submitoder_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_finish'])");
    private final By txt_noteoder_cart2_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[1]/(android.widget.EditText)");
    private final By txt_total_product_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_productTotal'])");
    private final By txt_total_shipfee_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_shipfee'])");
    private final By txt_sumtotal_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_cartTotal'])");
    private final By rdo_pickup_at_store_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_pickupAtStore'])");
    private final By rdo_shipdelivery_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_delivery'])");
    private final By txt_label_choose_shipdatetime_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@text='Chọn thời gian nhận hàng'])");
    protected final By txt_address_shophouse_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_checkbox_addressShopHouse_1 '])");
    private final By txt_label_choose_payment_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@text='Chọn hình thức thanh toán'])");
    private final By txt_label_address_shipping_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@text='Địa chỉ nhận hàng'])");
    protected final By txt_delivery_cart2_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='cart_txt_delivery'])");
    protected String customer_gender_selected;
    protected String other_gender_selected;
    protected boolean isOffSwipeCard = true;

    public CartStep2(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    protected MobileElement rdo_Gender_Anh_Cart2() {
        return wait.waitForClickAbleBySelector(rdo_gender_Anh_cart2_Selector);
    }

    protected MobileElement rdo_Gender_Chi_Cart2() {
        return wait.waitForClickAbleBySelector(rdo_gender_Chi_cart2_Selector);
    }

    protected MobileElement rdo_Other_Gender_Anh_Cart2() {
        return wait.waitForClickAbleBySelector(rdo_other_gender_Anh_cart2_Selector);
    }

    protected MobileElement rdo_Other_Gender_Chi_Cart2() {
        return wait.waitForClickAbleBySelector(rdo_other_gender_Chi_cart2_Selector);
    }

    protected MobileElement txt_FullName_Cart2() {
        return wait.waitForClickAbleBySelector(txt_fullname_cart2_Selector);
    }

    protected MobileElement txt_PhoneNumber_Cart2() {
        return wait.waitForClickAbleBySelector(txt_phonenumber_cart2_Selector);
    }

    protected MobileElement txt_Other_FullName_Cart2() {
        return wait.waitForClickAbleBySelector(txt_other_fullname_cart2_Selector);
    }

    protected MobileElement txt_Other_PhoneNumber_Cart2() {
        return wait.waitForClickAbleBySelector(txt_other_phonenumber_cart2_Selector);
    }

    protected MobileElement label_Address_Shipping_Cart2() {
        return wait.waitForVisibilityOfSelector(txt_label_address_shipping_cart2_Selector);
    }


    protected MobileElement txt_Address_Cart2() {
        MobileElement element = null;
        try {
            if (this.rdo_Other_Gender_Anh_Cart2().isDisplayed()) {
                if (isDCStore()) {
                    element = wait.waitForClickAbleBySelector(txt_address_cart2_Selector);
                } else {
                    By locator = By.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[5]/(android.widget.EditText)");
                    element = wait.waitForClickAbleBySelector(locator);
                }
            }
        } catch (Exception ignored) {
            element = wait.waitForClickAbleBySelector(txt_address_cart2_Selector);
        }
        return element;
    }

    protected MobileElement btn_Submitoder_Cart2() {
        return wait.waitForClickAbleBySelector(btn_submitoder_cart2_Selector);
    }

    protected MobileElement ddl_Shipdate_Cart2() {
        return wait.waitForClickAbleBySelector(ddl_shipdate_cart2_Selector);
    }

    protected MobileElement ddl_Shiptime_Cart2() {
        return wait.waitForClickAbleBySelector(ddl_shiptime_cart2_Selector);
    }

    protected MobileElement rdo_Ship_Delivery_Cart2() {
        MobileElement rdo_Ship_Delivery = null;
        try {
            rdo_Ship_Delivery = wait.waitForTryCatch_ClickableBySelector(rdo_shipdelivery_Selector);
        } catch (Exception ignore) {
        }
        return rdo_Ship_Delivery;
    }

    protected MobileElement rdo_PickUp_At_Store_Cart2() {
        MobileElement rdo_PickUp_At_Store = null;
        try {
            rdo_PickUp_At_Store = wait.waitForTryCatch_ClickableBySelector(rdo_pickup_at_store_cart2_Selector);
        } catch (Exception ignore) {
        }
        return rdo_PickUp_At_Store;
    }

    protected MobileElement btn_Payment_Loadmore_Cart2() {
        MobileElement btn_Payment_Loadmore = null;
        try {
            btn_Payment_Loadmore = wait.waitForTryCatch_ClickableBySelector(btn_payment_loadmore_cart2_Selector);
        } catch (Exception ignore) {
        }
        return btn_Payment_Loadmore;
    }

    protected MobileElement txt_NoteOder_Cart2() {
        return wait.waitForClickAbleBySelector(txt_noteoder_cart2_Selector);
    }

    protected MobileElement txt_Total_Product_Cart2() {
        return wait.waitForVisibilityOfSelector(txt_total_product_cart2_Selector);
    }

    protected MobileElement txt_Total_Shipfee_Cart2() {
        return wait.waitForVisibilityOfSelector(txt_total_shipfee_cart2_Selector);
    }

    protected MobileElement btn_BackToCart1_Cart2() {
        return wait.waitForClickAbleBySelector(btn_backtocart1_Selector);
    }

    protected MobileElement chk_Call_Other_Cart2() {
        return wait.waitForTryCatch_ClickableBySelector(chk_call_oder_cart2_Selector);
    }

    protected MobileElement label_Choose_Ship_DateTime_Cart2() {
        return wait.waitForVisibilityOfSelector(txt_label_choose_shipdatetime_cart2_Selector);
    }

    protected MobileElement label_Choose_Payment_Cart2() {
        return wait.waitForVisibilityOfSelector(txt_label_choose_payment_cart2_Selector);
    }

    protected MobileElement txt_Address_Shophouse_Cart2() {
        return wait.waitForTryCatch_VissibilityOfSelector(txt_address_shophouse_cart2_Selector);
    }


    protected MobileElement txt_Delivery_Cart2() {
        return wait.waitForTryCatch_VissibilityOfSelector(txt_delivery_cart2_Selector);
    }

    private boolean check_Off_SwipeCard(MobileElement mobileElement) {
        return mobileElement.getText().contains("Cà thẻ");
    }


    private boolean check_selectedShophouse() {
        try {
            this.txt_Address_Shophouse_Cart2().isDisplayed();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private boolean isDCStore() {
        try {
            wait.waitForTryCatch_ClickableBySelector(chk_isApartment_cart2_Selector);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    @Step("Input full name at Cart2")
    protected void input_FullName_Cart2(String fullName) {
        handle_TextExist(this.txt_FullName_Cart2(), fullName);
        this.appiumDriver.hideKeyboard();
    }

    @Step("Input phone number at Cart2")
    protected void input_PhoneNumber_Cart2(String OtherphoneNumber) {
        handle_TextExist(this.txt_PhoneNumber_Cart2(), OtherphoneNumber);
        this.appiumDriver.hideKeyboard();
    }

    @Step("Input OTHER full name at Cart2")
    protected void input_OtherFullName_Cart2(String fullName) {
        handle_TextExist(this.txt_Other_FullName_Cart2(), fullName);
        this.appiumDriver.hideKeyboard();
    }

    @Step("Input OTHER phone number at Cart2")
    protected void input_OtherPhoneNumber_Cart2(String OtherphoneNumber) {
        handle_TextExist(this.txt_Other_PhoneNumber_Cart2(), OtherphoneNumber);
        this.appiumDriver.hideKeyboard();
    }

    @Step("Input address at Cart2")
    protected void input_Address_Cart2(String address) {
        handle_TextExist(this.txt_Address_Cart2(), address);
        this.appiumDriver.hideKeyboard();
    }

    @Step("Choose Gender at Cart2")
    protected String choose_Gender_Random_Cart2() {
        int r = (int) (Math.random() * 2);
        switch (r) {
            case 1: {
                this.rdo_Gender_Anh_Cart2().click();
                this.rdo_Gender_Anh_Cart2().click();
                Log.info("Selected Anh");
                saveTextLog("Selected Anh");
                customer_gender_selected = "Anh";
//                if (this.rdo_Gender_Anh_Cart2().isSelected()) {
//                    Log.info("Has chosen 'Anh' ready !");
//                    break;
//                } else if (this.rdo_Gender_Chi_Cart2().isSelected()) {
//                    this.rdo_Gender_Anh_Cart2().click();
//                    Log.info("Selected Anh");
//                    break;
//                } else {
//                    this.rdo_Gender_Anh_Cart2().click();
//                    Log.info("Selected Anh");
//                }
            }
            break;
            case 0: {
                this.rdo_Gender_Anh_Cart2().click();
                this.rdo_Gender_Chi_Cart2().click();
                Log.info("Selected Chi");
                saveTextLog("Selected Chi");
                customer_gender_selected = "Chị";
//                if (this.rdo_Gender_Chi_Cart2().isSelected()) {
//                    Log.info("Has chosen 'Chi' ready !!");
//                    break;
//                } else if (this.rdo_Gender_Anh_Cart2().isSelected()) {
//                    this.rdo_Gender_Chi_Cart2().click();
//                    Log.info("Selected Chi");
//                    break;
//                } else {
//                    this.rdo_Gender_Chi_Cart2().click();
//                    Log.info("Selected Chi");
//                }
            }
            break;
        }
        return customer_gender_selected;
    }

    @Step("Choose Other Gender at Cart2")
    protected String choose_Other_Gender_Random_Cart2() {
        int r = RandomNextInt(2);
        switch (r) {
            case 1: {
                this.rdo_Other_Gender_Anh_Cart2().click();
                this.rdo_Other_Gender_Anh_Cart2().click();
                Log.info("Selected Anh");
                saveTextLog("Selected Anh");
                other_gender_selected = "Anh";
//                if (this.rdo_Gender_Anh_Cart2().isSelected()) {
//                    Log.info("Has chosen 'Anh' ready !");
//                    break;
//                } else if (this.rdo_Gender_Chi_Cart2().isSelected()) {
//                    this.rdo_Gender_Anh_Cart2().click();
//                    Log.info("Selected Anh");
//                    break;
//                } else {
//                    this.rdo_Gender_Anh_Cart2().click();
//                    Log.info("Selected Anh");
//                }
            }
            break;
            case 0: {
                this.rdo_Other_Gender_Chi_Cart2().click();
                this.rdo_Other_Gender_Chi_Cart2().click();
//                Log.info("Selected Chi");
                saveTextLog("Selected Chị");
                other_gender_selected = "Chị";
//                if (this.rdo_Gender_Chi_Cart2().isSelected()) {
//                    Log.info("Has chosen 'Chi' ready !!");
//                    break;
//                } else if (this.rdo_Gender_Anh_Cart2().isSelected()) {
//                    this.rdo_Gender_Chi_Cart2().click();
//                    Log.info("Selected Chi");
//                    break;
//                } else {
//                    this.rdo_Gender_Chi_Cart2().click();
//                    Log.info("Selected Chi");
//                }
            }
            break;
        }
        return other_gender_selected;
    }


    @Step("Choose ship date at Cart2")
    private boolean choose_ShipDate_Cart2() {
        String selected;
        this.ddl_Shipdate_Cart2().click();
        sleepInSeconds(0.5);
        List<MobileElement> lst_shipdate = this.appiumDriver.findElements(lst_shipdate_cart2_Selector);
        Log.info("Lst_shipdate" + lst_shipdate.size());
        if (lst_shipdate.size() == 0) {
            this.ddl_Shipdate_Cart2().click();
            sleepInSeconds(0.5);
            lst_shipdate = this.appiumDriver.findElements(lst_shipdate_cart2_Selector);
            Log.info("Lst_shipdate" + lst_shipdate.size());
        }
        if (lst_shipdate.size() > 1) {
            try {
                RandomlySelectOneFromList(lst_shipdate);
            } catch (StaleElementReferenceException ex) {
                lst_shipdate = this.appiumDriver.findElements(lst_shipdate_cart2_Selector);
                Log.info("Lst_shipdate" + lst_shipdate.size());
                RandomlySelectOneFromList(lst_shipdate);
            }
            sleepInSeconds(1);
            selected = this.ddl_Shipdate_Cart2().getText();
            Log.info("Selected: " + selected);
            saveTextLog("Selected: " + selected);
            return true;
        } else if (lst_shipdate.size() == 1) {
            this.ddl_Shipdate_Cart2().click();
            selected = this.ddl_Shipdate_Cart2().getText();
            Log.info("Selected: " + selected);
            saveTextLog("Selected: " + selected);
            return true;
        } else {
            Log.info("Do not get the delivery date!!!");
            return false;
        }
//        return true;
    }

    @Step("Choose ship time at Cart2")
    protected void choose_ShipDateTime_Random_Cart2() {
        String selected;
        if (this.choose_ShipDate_Cart2()) {
            wait.waitForClickAbleBySelector(btn_submitoder_cart2_Selector);
            this.ddl_Shiptime_Cart2().click();
            sleepInSeconds(1);
            List<MobileElement> lst_shiptime = this.appiumDriver.findElements(lst_shiptime_cart2_Selector);
            Log.info("lst_shiptime" + lst_shiptime.size());
            if (lst_shiptime.size() == 0) {
                this.ddl_Shiptime_Cart2().click();
                sleepInSeconds(1);
                lst_shiptime = this.appiumDriver.findElements(lst_shiptime_cart2_Selector);
                Log.info("lst_shiptime" + lst_shiptime.size());
            }
            if (lst_shiptime.size() > 0) {
                try {
                    RandomlySelectOneFromList(lst_shiptime);
                } catch (StaleElementReferenceException ex) {
                    lst_shiptime = this.appiumDriver.findElements(lst_shiptime_cart2_Selector);
                    if (lst_shiptime.size() > 0) {
                        RandomlySelectOneFromList(lst_shiptime);
                    } else
                        choose_ShipDateTime_Random_Cart2();
                }

//                lst_shiptime.get((lst_shiptime.size() - 1)).click();
                sleepInSeconds(1);
                selected = this.ddl_Shiptime_Cart2().getText();
                Log.info("Selected: " + selected);
                saveTextLog("Selected: " + selected);
            } else {
                int i = 0;
                saveTextLog("Do not get the delivery TIME");
                i++;
                if (i <= 3) {
                    saveTextLog("Choose again: " + i);
                    Log.info("Choose again: " + i);
                    this.choose_ShipDateTime_Random_Cart2();
                } else {
                    Assert.fail("Do not get the delivery TIME");
                }
            }
        } else {
            saveTextLog("Do not get the delivery DATE");
            Assert.assertTrue(this.choose_ShipDate_Cart2(), "Do not get the delivery DATE");
        }
    }

//    protected void Scroll_ChooseShipDateTime() {
//        try {
//            if (check_selectedShophouse()) {
//                swipeActions.SwipeFromElementToElement(this.txt_Address_Shophouse_Cart2(), this.txt_PhoneNumber_Cart2());
//            } else {
//                try {
//                    if (this.chk_Call_Other_Cart2().isDisplayed()) {
//                        swipeActions.SwipeFromElementToElement(this.chk_Call_Other_Cart2(), this.txt_PhoneNumber_Cart2());
//                    }
//                } catch (Exception ignored) {
//                }
//
//            }
//        } catch (Exception ignored) {
//        }
//    }

    private boolean check_isPaymentTypeCashDisplay() {
        try {
            if (wait.waitForTryCatch_ClickableBySelector(txt_payment_type_cash_Selector).isDisplayed()) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    protected void Swipe_chooseShipDate() {
        if (!check_selectedShophouse()) {
            try {
                if (this.chk_Call_Other_Cart2().isDisplayed()) {
                    swipeActions.SwipeFromElementToElement(this.chk_Call_Other_Cart2(), this.label_Address_Shipping_Cart2());
                }
            } catch (Exception ignored) {
                swipeActions.SwipeFromElementToElement(this.label_Choose_Payment_Cart2(), this.label_Choose_Ship_DateTime_Cart2());
            }
            try {
                try {
                    MobileElement element = this.appiumDriver.findElementByXPath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[2]/(android.widget.EditText)");
                    if (!element.getText().isEmpty()) {
                        swipeActions.SwipeFromElementToElement(this.label_Choose_Payment_Cart2(), this.label_Choose_Ship_DateTime_Cart2());
                    }

                } catch (Exception ignored) {
                    MobileElement element = this.appiumDriver.findElementByXPath("(//android.view.ViewGroup[@resource-id='txt_input_view'])[3]/(android.widget.EditText)");
                    if (!element.getText().isEmpty()) {
                        swipeActions.SwipeFromElementToElement(this.label_Choose_Payment_Cart2(), this.label_Choose_Ship_DateTime_Cart2());
                    }
                }
            } catch (Exception ignored) {
            }

        } else {
            try {
                if (wait.waitForTryCatch_VissibilityOfSelector(txt_address_shophouse_cart2_Selector).isDisplayed()) {
                    swipeActions.SwipeFromElementToElement(this.label_Choose_Ship_DateTime_Cart2(), this.label_Address_Shipping_Cart2());
                }
            } catch (Exception ignored) {
            }
        }

    }


    protected CartStep2 click_btnLoadmorePayment() {
        do {
            try {
                if (this.btn_Payment_Loadmore_Cart2().isDisplayed()) {
                    this.btn_Payment_Loadmore_Cart2().click();
                    this.btn_Payment_Loadmore_Cart2().click();
                }
                if (check_isPaymentTypeCashDisplay()) {
                    break;
                }
            } catch (Exception ignored) {
            }
        } while (!check_isPaymentTypeCashDisplay());
        return this;
    }

    @Step("Choose payment Type: COD at Cart2")
    protected CartStep2 choose_Payment_Type_Cash_Cart2() {
        wait.waitForClickAbleBySelector(txt_payment_type_cash_Selector).click();
        return this;
    }

    @Step("Choose payment type at Cart2")
    protected CartStep2 choose_Payment_Cart2(String payment) {
        List<MobileElement> lst_Payment = this.appiumDriver.findElements(lst_payment_cart2_Selector);
        String payment_tempt = StringHandler.removeAccent(payment);
        if (lst_Payment.size() > 1) {
            try {
                if (!StringHandler.removeAccent(wait.waitForVisibilityOfSelector(btn_payment_Selected_Selector).getText()).trim().toLowerCase().contains(payment_tempt.trim().toLowerCase())) {
                    for (MobileElement Payment : lst_Payment) {
                        if (check_Off_SwipeCard(Payment)) {
                            isOffSwipeCard = false;
                        }
                        if (StringHandler.removeAccent(Payment.getText()).trim().toLowerCase().contains(payment_tempt.trim().toLowerCase())) {
                            String selected = StringHandler.removeAccent(Payment.getText().toUpperCase());
                            Payment.click();
                            sleepInSeconds(1);
                            Log.info("Chose : " + selected);
                            saveTextLog("Chose : " + selected);
//                            break;
                        }
                    }
                }
            } catch (Exception ignored) {
            }
        }
        return this;
    }

    @Step("Input note oder at Cart2")
    protected void input_NoteOder_Cart2(String note) {
        swipeActions.SwipeFromElementToElement(this.btn_Submitoder_Cart2(), this.ddl_Shipdate_Cart2());
        handle_TextExist(this.txt_NoteOder_Cart2(), note);
        this.appiumDriver.hideKeyboard();
    }

    protected String get_sumtotal_Cart2() {
        return get_TextAsMoney(this.btn_Submitoder_Cart2())
                .replace("HOÀN TẤT MUA", "")
                .replace("THANH TOÁN", "");
    }

    protected boolean check_LocationIsShophouse() {
        try {
            wait.waitForTryCatch_ClickableBySelector(rdo_pickup_at_store_cart2_Selector);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    @Step("Selected Shophouse")
    protected void select_Shophouse_Cart2() {
        do {
            try {
                this.rdo_PickUp_At_Store_Cart2().click();
                wait.waitForTryCatch_ClickableBySelector(txt_address_shophouse_cart2_Selector);
            } catch (StaleElementReferenceException ex) {
                MobileElement element = getDriver().findElement(rdo_pickup_at_store_cart2_Selector);
                element.click();
            } catch (Exception ignored) {
                this.rdo_PickUp_At_Store_Cart2().click();
                Log.info("đã click shophouse lần 2");
                wait.waitForTryCatch_VissibilityOfSelector(txt_address_shophouse_cart2_Selector);
            }
        } while (!this.txt_Address_Shophouse_Cart2().isDisplayed());

    }

    @Step("Check rule Ship Fee when select Shophouse")
    protected boolean check_RuleShipFee() {
        swipeActions.SwipeUp();
        String Total_Product = (get_TextAsMoney(wait.waitForVisibilityOfSelector(txt_total_product_cart2_Selector)))
                .replace(".", "").replace("₫", "").replace("đ", "");
        long Total_Product_Price = Long.parseLong(Total_Product) / 1000;
        String total_shipfee = get_TextAsMoney(wait.waitForVisibilityOfSelector(txt_total_shipfee_cart2_Selector));

        if (Total_Product_Price >= 100 && total_shipfee.equalsIgnoreCase("miễn phí")
                || Total_Product_Price < 100 && !total_shipfee.equalsIgnoreCase("miễn phí")) {
            saveTextLog("verify pass");
            return true;
        } else {
            saveTextLog("verify fail: " + "\nTotal product:" + Total_Product + "Ship Fee:" + total_shipfee);
            Log.info("Total product:" + Total_Product);
            Log.info("Total Ship Fee:" + total_shipfee);
            return false;
        }
    }

    @Step("Check Off SwipeCard when select Shophouse")
    protected boolean check_Off_SwipeCard_Cart2() {
        return isOffSwipeCard;
    }

    protected boolean isDelivery() {
        try {
            return this.txt_Address_Cart2().isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }

}