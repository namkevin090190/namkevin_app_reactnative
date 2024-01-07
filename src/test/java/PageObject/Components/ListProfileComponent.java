package PageObject.Components;

import Common.Log;
import Models.OrderData;
import PageObject.Base.PageModel_Base;
import Utils.RandomEx;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.Map;

import static Utils.Sleep.sleepInSeconds;

public class ListProfileComponent extends PageModel_Base {

    private final By lst_profile_address_reciever_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='address_profile_info_receiver']");
    private final By lst_address_full_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='address_full_address']");
    private final By btn_address_loadmore_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='load_more_address']");
    private final By btn_add_other_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='add_other_address']");
    private final By txt_input_address_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='txt_input_view']/android.widget.EditText");
    private final By btn_complete_add_PopUp_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='btn_complete_add_address']");
    private final By ddl_province_PopUp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='dropdown_province']");
    private final By dd_district_PopUp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='dropdown_district']");
    private final By ddl_ward_PopUp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='dropdown_ward']");
    private final By txt_info_reciever_PopUp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_info_receiver']");
    private final By btn_collapse_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='collapse_address']");
    private final By lst_btn_delete_address_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='delete_address']");
    private final By btn_accpet_delete_address_popup_Selector = MobileBy.xpath("//android.widget.TextView[@text='Đồng ý xóa']");
    private final By chkbox_call_other_Popup_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='cart_checkbox_callOther']");
    private final By txt_phone_other_Popup_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='view_input_Address'])/(android.view.ViewGroup[@resource-id='txt_input_view'])[1]/(android.widget.EditText)");
    private final By txt_name_other_Popup_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='view_input_Address'])/(android.view.ViewGroup[@resource-id='txt_input_view'])[2]/(android.widget.EditText)");
    private final By rdio_gender_other_male_Popup_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='cart_radio_maleOther']");
    private final By rdio_gender_other_female_Popup_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='cart_radio_femaleOther']");
    private final By txt_isApartment_Selector = MobileBy.xpath("//android.widget.TextView[@text='Yêu cầu mang lên lầu (+5.000đ)']");


    public ListProfileComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    protected MobileElement Btn_Address_LoadMore() {
        return wait.waitForClickAbleBySelector(btn_address_loadmore_Selector);
    }

    protected MobileElement Btn_Add_Other_Address() {
        return wait.waitForClickAbleBySelector(btn_add_other_address_Selector);
    }

    protected MobileElement Txt_Input_Address() {
        return wait.waitForClickAbleBySelector(txt_input_address_Selector);
    }

    protected MobileElement Btn_Complete_Add_Address() {
        return wait.waitForClickAbleBySelector(btn_complete_add_PopUp_address_Selector);
    }

    protected MobileElement Btn_Collapse_Address() {
        return wait.waitForTryCatch_ClickableBySelector(btn_collapse_address_Selector);
    }

    protected MobileElement Txt_Phone_Other() {
        return wait.waitForClickAbleBySelector(txt_phone_other_Popup_Selector);
    }

    protected MobileElement Txt_Name_Other() {
        return wait.waitForClickAbleBySelector(txt_name_other_Popup_Selector);
    }

//    protected MobileElement Rdio_Gender_Other_Male() {
//        return wait.waitForClickAbleBySelector(rdio_gender_other_male_Popup_Selector);
//    }
//
//    protected MobileElement Rdio_Gender_Other_Female() {
//        return wait.waitForClickAbleBySelector(rdio_gender_other_female_Popup_Selector);
//    }

    protected MobileElement Txt_Customer_Info() {
        return wait.waitForClickAbleBySelector(txt_info_reciever_PopUp_Selector);
    }


    public boolean check_ListProfile_isDisplayed() {
        try {
            if (this.appiumDriver.findElements(lst_profile_address_reciever_Selector).size() > 0) {
                return true;
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    public boolean TXT_isApartment_Displayed() {
        try {
            wait.waitForTryCatch_VissibilityOfSelector(txt_isApartment_Selector);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public boolean findAdress_WithInputLocation(Map<String, String> data) {
        boolean isFound = false;
        String[] Location_Array = data.get(OrderData.getLocationSelected()).split(", ");

        List<MobileElement> List_Address_Full = this.appiumDriver.findElements(lst_address_full_Selector);
        List<MobileElement> List_Info_Receiver = this.appiumDriver.findElements(lst_profile_address_reciever_Selector);

        for (MobileElement element : List_Address_Full) {
            String text = element.getText();
            if ((text.trim().contains(Location_Array[0])) && (text.trim().contains(Location_Array[1])) && (text.trim().contains(Location_Array[2]))) {
                String[] info = List_Info_Receiver.get(List_Address_Full.indexOf(element)).getText().split(",");
//                data.put(OrderData.getNameWithGenderReciever(), info[0]);

                data.put(OrderData.getOtherGender(), info[0].substring(0, 3).trim());
                data.put(OrderData.getOtherName(), info[0].substring(4).trim());

                data.put(OrderData.getOtherPhone(), info[1].trim());
                data.put(OrderData.getFullAddress(), text);
                Log.info("Found Address: " + text);
                Log.info("Info Reciever: " + info[0] + ", " + info[1]);
                element.click();
                isFound = true;
                CollapAdress();
                break;
            }
        }

        return isFound;
    }


    public boolean find_Address_RecieverEqualsLocation(Map<String, String> data) {
        boolean isFound = false;

        if (!findAdress_WithInputLocation(data)) {
            try {
                this.Btn_Address_LoadMore().click();
            } catch (Exception ignored) {
                NavigateToCartStep2().SwipeCollapseAddress();
                try {
                    this.Btn_Address_LoadMore().click();
                } catch (Exception ignore) {
                }
            }
            isFound = findAdress_WithInputLocation(data);
        } else {
            isFound = true;
        }
        if (!isFound) {
            Log.info("Not found Address: " + data.get(OrderData.getLocationSelected()));
        }
        return isFound;
    }

    private ListProfileComponent Delete_Address_Random() {
        try {
            this.Btn_Collapse_Address().isDisplayed();
        } catch (Exception ignored) {
            NavigateToCartStep2().SwipeCollapseAddress();
        }
        List<MobileElement> List_Btn = this.appiumDriver.findElements(lst_btn_delete_address_Selector);
        int r = RandomEx.RandomNextInt(List_Btn.size());
        List_Btn.get(r).click();
        wait.waitForClickAbleBySelector(btn_accpet_delete_address_popup_Selector).click();
        return this;
    }

    private ListProfileComponent hamdle_delete_address() {
        List<MobileElement> List_Btn = this.appiumDriver.findElements(lst_btn_delete_address_Selector);
        if (List_Btn.size() == 6) {
            Delete_Address_Random();
        }
        return this;
    }


    public ListProfileComponent Add_Address_Other(String address) {
        hamdle_delete_address();
        this.Btn_Add_Other_Address().click();
        wait.waitForTryCatch_ClickableBySelector(txt_input_address_Selector).click();
        this.Txt_Input_Address().sendKeys(address);
        this.appiumDriver.hideKeyboard();
        try {
            this.Btn_Complete_Add_Address().click();
            this.Btn_Complete_Add_Address().click();
        } catch (Exception ignored) {
        }

        sleepInSeconds(6);
        CollapAdress();
        return this;
    }

    public ListProfileComponent CollapAdress() {

        try {
            this.Btn_Collapse_Address().isDisplayed();
            this.Btn_Collapse_Address().click();
        } catch (Exception ignored) {
            NavigateToCartStep2().SwipeCollapseAddress();
            this.Btn_Collapse_Address().click();
        }
        return this;
    }


    public ListProfileComponent Address_with_OtherInfo(Map<String, String> data) {
        hamdle_delete_address();
        this.Btn_Add_Other_Address().click();
        wait.waitForTryCatch_ClickableBySelector(txt_input_address_Selector).click();
        this.Txt_Input_Address().sendKeys(data.get(OrderData.getAddress()));
        this.appiumDriver.hideKeyboard();
//        String[] InFo = this.Txt_Customer_Info().getText().split("-");
//        data.put(OrderData.getNameWithGenderCustomer(), InFo[0].trim());
//        data.put(OrderData.getCustomerPhone(), InFo[1].trim());


        try {
            wait.waitForClickAbleBySelector(chkbox_call_other_Popup_Selector).click();
            wait.waitForTryCatch_ClickableBySelector(txt_phone_other_Popup_Selector).click();
        } catch (Exception ignored) {
            wait.waitForClickAbleBySelector(chkbox_call_other_Popup_Selector).click();
            this.Txt_Phone_Other().click();
        }
        this.Txt_Phone_Other().sendKeys(data.get(OrderData.getOtherPhone()));
        this.Txt_Name_Other().click();
        this.Txt_Name_Other().sendKeys(data.get(OrderData.getOtherName()));
        String gender = NavigateToCartStep2().choose_Other_Gender();
        data.put(OrderData.getOtherGender(), gender);
        this.Btn_Complete_Add_Address().click();
        sleepInSeconds(2);


        return this;
    }


}
