package PageObject.Page_Model;

import Common.Log;
import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

import static Utils.Sleep.sleepInSeconds;

public class ListAddress extends PageModel_Base {

    private final By lst_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='address_full_address']");
    private final By lst_name_reciver_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_name_receiver']");
    private final By lst_phone_reciver_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_phone_receiver']");
    private final By lst_btn_delete_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='delete_address']");
    private final By lst_address_id_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_profile_id']");
    private final By label_list_address_page_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='label_listaddress_page']");
    private final By options_yes_Popup_Selector = MobileBy.xpath("//android.widget.TextView[@text='Đồng ý']");
    private final By options_no_Popup_Selector = MobileBy.xpath("//android.widget.TextView[@text='Không xoá']");
    private final By getLst_address_id_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_address_id']");
    private final By btn_back_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='btn_back']");

    public ListAddress(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    protected List<MobileElement> List_AddressFull() {
        return this.appiumDriver.findElements(lst_address_Selector);
    }

    protected List<MobileElement> List_Name_Reciver() {
        return this.appiumDriver.findElements(lst_name_reciver_Selector);
    }

    protected List<MobileElement> List_Phone_Reciver() {
        return this.appiumDriver.findElements(lst_phone_reciver_Selector);
    }

    protected List<MobileElement> List_Btn_Delete_Address() {
        return this.appiumDriver.findElements(lst_btn_delete_address_Selector);
    }

    protected List<MobileElement> List_Address_Id() {
        return this.appiumDriver.findElements(lst_address_id_Selector);
    }

    protected MobileElement Label_List_Address_Page() {
        return wait.waitForVisibilityOfSelector(label_list_address_page_Selector);
    }

    protected MobileElement Btn_Yes_Popup_Confirm() {
        return wait.waitForClickAbleBySelector(options_yes_Popup_Selector);
    }
    protected MobileElement Btn_Back() {
        return wait.waitForClickAbleBySelector(btn_back_Selector);
    }


    protected int findAddressId(String AddresdId) {
        int index = -1;
        for (MobileElement address : this.List_Address_Id()) {
            if (address.getText().equals(AddresdId)) {
                index = List_Address_Id().indexOf(address);
                break;
            }
        }
        return index;
    }

    protected boolean isAtListAddressPage() {
        try {
            return Label_List_Address_Page().isDisplayed();
        } catch (Exception ignored) {
            return false;
        }
    }


}
