package PageObject.Pages;

import Common.Log;
import Models.OrderData;
import PageObject.Page_Model.Account;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.util.Map;

import static Utils.Sleep.sleepInSeconds;

public class AccountPage extends Account {
    public AccountPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
        this.appiumDriver = appiumDriver;
    }

    public AccountPage Login_Account(String phone, String OTP) {
        if (!check_isAtProfilePage_NoLogin()) {
            NavigateToBarComponent().click_Tab_Profile();
        }
        input_Phone(phone);
        click_Login();
        input_OTP(OTP);
        if (check_isAtProfilePage_Login()) {
            Log.info("Login success");
        } else {
            Log.info("Login fail");
        }
        return this;
    }

    public AccountPage Logout_Account() {
        sleepInSeconds(1.5);
        if (!check_isAtProfilePage_Login()) {
            NavigateToBarComponent()
                    .click_Tab_Profile()
                    .click_Tab_Profile();
            swipeActions.SwipeUpWithTimes(3);
        }
        btn_Logout().click();
        btn_Accept_Logout().click();
        if (check_isAtHome() || check_isAtProfilePage_NoLogin()) {
            Log.info("Logout success");
        }
        return this;
    }

    public AccountPage getCustomerInfo(Map<String, String> data) {
        sleepInSeconds(1.5);
        String info = txt_Customer_Name().getText()
                .replace("(", "")
                .replace(")", "");
        String gender = info.substring(0, 3);
        String name = info.substring(4, info.length());
        data.put(OrderData.getCustomerGender(), gender);
        data.put(OrderData.getCustomerName(), name);

        return this;
    }

    public boolean check_isAtProfilePage_Login() {
        try {
            this.txt_Customer_Name();
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    protected boolean check_isAtProfilePage_NoLogin() {
        try {
            this.btn_Login();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

}
