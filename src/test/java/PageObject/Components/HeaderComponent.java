package PageObject.Components;

import Common.Log;
import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static Helpers.Reporter.AllureReportManager.saveTextLog;
import static Utils.Sleep.sleepInSeconds;

public class HeaderComponent extends PageModel_Base {


    private final By icn_header_cart_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_headerCart'])");
    private final By btn_header_Location_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='header_btn_location'])");
    private final By txt_header_SearchInput_Location_Comp_Selector = MobileBy.AccessibilityId("location_textinput_search_location");
    private final By lst_provinces_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_province'])");
    private final By lst_districts_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_district'])");
    private final By lst_wards_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_ward'])");
    private final By first_province_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_province'])[1]");
    private final By first_district_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_district'])[1]");
    private final By first_ward_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='location_txt_selected_ward'])[1]");
    private final By txt_header_search_product_input_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='header_txt_textinput_search'])");
    private final By btn_back_search_product_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_back'])");
    private final By btn_menu_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_headerMenu'])");
    private final By btn_home_menu_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_headerHome'])");
    private final By btn_close_menu_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_headerClose'])");

    public HeaderComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    private MobileElement Icon_Header_Cart_Comp() {
        return wait.waitForVisibilityOfSelector(icn_header_cart_Comp_Selector);
    }

    private MobileElement Txt_Header_Search_Product_Input_Comp() {
        return wait.waitForClickAbleBySelector(txt_header_search_product_input_Comp_Selector);
    }

    private MobileElement Btn_Header_Location_Comp() {
        return wait.waitForClickAbleBySelector(btn_header_Location_Comp_Selector);
    }

    private MobileElement Btn_Header_Menu_Comp() {
        return wait.waitForClickAbleBySelector(btn_menu_Comp_Selector);
    }

    private MobileElement Btn_Header_Menu_BackHome_Comp() {
        return wait.waitForClickAbleBySelector(btn_home_menu_Comp_Selector);
    }

    private MobileElement Btn_Header_Menu_Close_Comp() {
        return wait.waitForClickAbleBySelector(btn_close_menu_Comp_Selector);
    }


    @Step("Choose location")
    public String chooseLoation_Random_Header_Comp() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btn_header_Location_Comp_Selector));

        this.Btn_Header_Location_Comp().click();
        wait.waitForClickAbleBySelector(first_province_Comp_Selector);

        List<MobileElement> lst_provinces = appiumDriver.findElements(lst_provinces_Comp_Selector);
        int n1 = lst_provinces.size();
        int r1 = random.nextInt(n1);

        String province = lst_provinces.get(r1).getText();
        Log.info("Chose : " + province);
        lst_provinces.get(r1).click();

        wait.waitForClickAbleBySelector(first_district_Comp_Selector);
        List<MobileElement> lst_districts = appiumDriver.findElements(lst_districts_Comp_Selector);
        int n2 = lst_districts.size();
        int r2 = random.nextInt(n2);
        String district = lst_districts.get(r2).getText();
        Log.info("Chose : " + district);
        lst_districts.get(r2).click();

        wait.waitForClickAbleBySelector(first_ward_Comp_Selector);
        List<MobileElement> lst_wards = appiumDriver.findElements(lst_wards_Comp_Selector);
        int n3 = lst_wards.size();
        int r3 = random.nextInt(n3);
        String ward = lst_wards.get(r3).getText();
        Log.info("Chose : " + ward);
        lst_wards.get(r3).click();

        wait.waitForClickAbleBySelector(btn_header_Location_Comp_Selector);
        Log.info("Selected location : " + ward + " - " + district + " - " + province);
        saveTextLog("Selected location : " + ward + " - " + district + " - " + province);
        return ward + ", " + district + ", " + province;
    }

    @Step("Choose location")
    public HeaderComponent chooseLoation_Header_Comp(String province, String district, String ward) {
        String province_selected = null;
        String district_selected = null;
        String ward_selected = null;
        webDriverWait.until(ExpectedConditions.elementToBeClickable(btn_header_Location_Comp_Selector));
        this.Btn_Header_Location_Comp().click();
        wait.waitForClickAbleBySelector(first_province_Comp_Selector);

        List<MobileElement> lst_provinces = appiumDriver.findElements(lst_provinces_Comp_Selector);
        for (MobileElement province_Element : lst_provinces) {
            if (province_Element.getText().contains(province)) {
                province_selected = province_Element.getText();
                Log.info("Selected : " + province_selected);
                province_Element.click();
                break;
            }
        }

        wait.waitForClickAbleBySelector(first_district_Comp_Selector);
        List<MobileElement> lst_districts = appiumDriver.findElements(lst_districts_Comp_Selector);

        for (MobileElement district_Element : lst_districts) {
            if (district_Element.getText().contains(district)) {
                district_selected = district_Element.getText();
                Log.info("Selected : " + district_selected);
                district_Element.click();
                break;
            }
        }

        wait.waitForClickAbleBySelector(first_ward_Comp_Selector);
        List<MobileElement> lst_wards = appiumDriver.findElements(lst_wards_Comp_Selector);
        for (MobileElement ward_Element : lst_wards) {
            if (ward_Element.getText().contains(ward)) {
                ward_selected = ward_Element.getText();
                Log.info("Selected: " + ward_selected);
                ward_Element.click();
                break;
            }

        }
        wait.waitForClickAbleBySelector(btn_header_Location_Comp_Selector);
        Log.info("Selected location : " + ward_selected + " - " + district_selected + " - " + province_selected);
        saveTextLog("Selected location : " + ward_selected + " - " + district_selected + " - " + province_selected);
        return this;
    }

    @Step("Search product in header")
    public HeaderComponent input_SearchProduct_Header_Comp(String Key_search) {
        this.Txt_Header_Search_Product_Input_Comp().click();
        wait.waitForClickAbleBySelector(btn_back_search_product_Comp_Selector);
        MobileElement Txt_Header_Search_Product_Input_Comp = wait.waitForClickAbleBySelector(MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_delay_default'])[1]/(//android.widget.EditText)"));
        Txt_Header_Search_Product_Input_Comp.sendKeys(Key_search);
//        this.Txt_Header_Search_Product_Input_Comp().sendKeys(Key_search);
        Log.info("input search product keys: " + Key_search);
        saveTextLog("input search product keys: " + Key_search);
        sleepInSeconds(5);
        this.appiumDriver.getKeyboard().sendKeys(Keys.ENTER);
        return this;
    }

    @Step("Click on cart button header")
    public HeaderComponent Click_IconCart_Header_Comp() {
        try {
            this.Icon_Header_Cart_Comp().click();
        } catch (StaleElementReferenceException e) {
            this.appiumDriver.findElement(icn_header_cart_Comp_Selector) .click();
        }
        Log.info("Clicked on cart button");
        return this;
    }

    public HeaderComponent BackToHomeFromMenu_Comp() {
        this.Btn_Header_Menu_Comp().click();
        this.Btn_Header_Menu_BackHome_Comp().click();
        wait.waitForClickAbleBySelector(btn_header_Location_Comp_Selector);
        return this;
    }

    public boolean check_isHome_Comp() {
        try {
            wait.waitForTryCatch_ClickableBySelector(btn_header_Location_Comp_Selector).isDisplayed();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
}