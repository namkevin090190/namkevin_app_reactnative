package PageObject.Pages;

import Common.Log;
import PageObject.Page_Model.ListAddress;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import static Utils.Sleep.sleepInSeconds;

public class ListAddressPage extends ListAddress {

    public ListAddressPage(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    public ListAddressPage DeleteAdressByID(String AddresdId) {
        if (isAtListAddressPage()) {
            int index = findAddressId(AddresdId);
            if (index != -1) {
                this.List_Btn_Delete_Address().get(index).click();
                Btn_Yes_Popup_Confirm().click();
                Log.info("Delete AddressID " + AddresdId + " Succes!");
                sleepInSeconds(3);
                if (findAddressId(AddresdId) == -1) {
                    Log.info("Verify Delete address success");
                } else {
                    Log.info("Address exist");
                }
            } else {
                Log.info("Not found AddressID : " + AddresdId);
            }
        }
        return this;
    }

    public ListAddressPage BackToAccountPage() {
        if(isAtListAddressPage()){
            this.Btn_Back().click();
            if(NavigateToAccountPage().check_isAtProfilePage_Login()){
                Log.info("Back to AccountPage success");
            }
        }else {
            Log.info("Not at ListAddressPage");
        }
        return this;
    }
}
