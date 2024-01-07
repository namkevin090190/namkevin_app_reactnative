package PageObject.Page_Model;

import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class Account extends PageModel_Base {

    private final By txt_input_otp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='txt_input_view']/android.widget.EditText");
    private final By btn_login_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='btn_Profile_login']");
    private final By txt_input_phone_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='txt_input_view']/android.view.ViewGroup/android.widget.EditText");
    private final By txt_customer_name_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_customer_name']");
    private final By btn_logout_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_logout']");
    protected final By btn_cancel_logout_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='txt_option_logout'])[1]");
    private final By btn_accept_logout_Selector = MobileBy.xpath("(//android.widget.TextView[@text='Đồng ý'])");
    private final By btn_edit_profile_customer_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_edit_profile_customer']");
    private final By btn_list_address_page_Selector = MobileBy.xpath("//android.widget.TextView[@text='Địa chỉ nhận hàng']");
    private final By btn_histories_page_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_histories_page']");


    public Account(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }


    protected MobileElement txt_Input_OTP() {
        return wait.waitForClickAbleBySelector(txt_input_otp_Selector);
    }

    protected MobileElement txt_Input_Phone() {
        return wait.waitForClickAbleBySelector(txt_input_phone_Selector);
    }

    protected MobileElement btn_Login() {
        return wait.waitForClickAbleBySelector(btn_login_Selector);
    }

    protected MobileElement btn_Logout() {
        return wait.waitForClickAbleBySelector(btn_logout_Selector);
    }

    protected MobileElement btn_Cancel_Logout() {
        return wait.waitForClickAbleBySelector(btn_cancel_logout_Selector);
    }

    protected MobileElement btn_Accept_Logout() {
        return wait.waitForClickAbleBySelector(btn_accept_logout_Selector);
    }
    protected MobileElement txt_Customer_Name() {
        return wait.waitForVisibilityOfSelector(txt_customer_name_Selector);
    }
    protected MobileElement btn_List_Address_Page() {
        return wait.waitForClickAbleBySelector(btn_list_address_page_Selector);
    }
    protected void input_Phone(String phone) {
        wait.waitForClickAbleBySelector(txt_input_phone_Selector).click();
        this.txt_Input_Phone().sendKeys(phone);
    }

    protected void input_OTP(String OTP) {
        try {
            wait.waitForTryCatch_ClickableBySelector(txt_input_otp_Selector).click();
            this.txt_Input_OTP().sendKeys(OTP);
        } catch (Exception ignored) {
        }

    }

    protected void click_Login() {
        wait.waitForClickAbleBySelector(btn_login_Selector).click();
    }



}
