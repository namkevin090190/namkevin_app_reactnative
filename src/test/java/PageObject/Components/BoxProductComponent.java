package PageObject.Components;

import Common.Log;
import PageObject.Base.PageModel_Base;
import Utils.RandomEx;
import Utils.StringHandler;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static DriverFactory.AppiumDriverEx.getDriver;
import static Helpers.Reporter.AllureReportManager.saveTextLog;
import static Utils.Sleep.sleepInSeconds;

public class BoxProductComponent extends PageModel_Base {
    private final By lst_btn_Buy_BoxSP_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='boxsp_txt_buy'])");
    private final By btn_decrease_BoxSP_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='boxsp_btn_decrease'])");
    private final By btn_back_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='btn_back'])");
    private final By txt_product_name_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='box_prod_name'])");
    private final By txt_product_price_Comp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='view_default']//android.widget.TextView");
    private final By txt_product_name_in_popup_Comp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='box_prod_name_in_popup']");
    private final By txt_product_price_unit_in_popup_Comp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='prod_unit_price_in_popup']");
    private final By txt_product_price_fresh_in_popup_Comp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='prod_price_in_popup']");
    private final By txt_product_unit_in_popup_Comp_Selector = MobileBy.xpath("//android.view.ViewGroup[@resource-id='prod_unit_in_popup']");
    private final By txt_product_type_fresh_in_popup_Comp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='span']");
    private final By btn_buy_in_popup_Comp_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='btn_buy_in_Popup']");

    public BoxProductComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    private List<MobileElement> Lst_Btn_Buy_BoxSP_Comp() {
        return this.appiumDriver.findElements(lst_btn_Buy_BoxSP_Comp_Selector);
    }

    private boolean check_Procduct_AddedToCart() {
        try {
            wait.waitForTryCatch_ClickableBySelector(btn_decrease_BoxSP_Comp_Selector);
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private boolean check_isPopUp_Unit() {
        try {
            if (wait.waitForTryCatch_VissibilityOfSelector(txt_product_price_unit_in_popup_Comp_Selector).isDisplayed())
                return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private boolean check_PopUp_Before_AddToCart() {
        try {
            if (wait.waitForTryCatch_VissibilityOfSelector(btn_buy_in_popup_Comp_Selector).isDisplayed())
                return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private void getInfo_Product(int index) {
        List<MobileElement> lst_productNames = this.appiumDriver.findElements(txt_product_name_Comp_Selector);
        Log.info("lst_productNames.size(): " + lst_productNames.size());
        String productName = lst_productNames.get(index).getText();
        List<MobileElement> lst_productPrices;
        String productPrice;
        try {
            lst_productPrices = this.appiumDriver.findElements(txt_product_price_Comp_Selector);
            productPrice = lst_productPrices.get(index).getText();
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            lst_productPrices = getDriver().findElementsByXPath("(//android.view.ViewGroup[@resource-id='view_default'])/(android.widget.TextView)");
            productPrice = lst_productPrices.get(index).getText();
        }
        Log.info("lst_productPrices.size(): " + lst_productPrices.size());


        Log.info("Info product added to cart: \nProduct Name: " + productName + "\nProduct Price: " + productPrice);
        saveTextLog("Info product added to cart: \nProduct Name: " + productName + "\nProduct Price: " + productPrice);
    }

    @Step("Add product to cart")
    public BoxProductComponent AddProductToCartRandom_Comp() {
        By txt_product_name_first = MobileBy.xpath("(//android.widget.TextView[@resource-id='box_prod_name'])[1]");
        wait.waitForClickAbleBySelector(txt_product_name_first);
        int n = this.Lst_Btn_Buy_BoxSP_Comp().size();
        int i = 0;
        if (n > 0) {
            do {
                int r = random.nextInt(this.Lst_Btn_Buy_BoxSP_Comp().size());
                if (this.Lst_Btn_Buy_BoxSP_Comp().get(r).getText().trim().equals("TẠM HẾT HÀNG")) {
                    Log.info("Product Info just out of stock!!!");
                    saveTextLog("Product Info just out of stock!!!");
                } else if (this.Lst_Btn_Buy_BoxSP_Comp().get(r).getText().trim().equals("NGƯNG KINH DOANH")) {
                    Log.info("Discontinued product!");
                    saveTextLog("Discontinued product!");
                } else {
                    this.Lst_Btn_Buy_BoxSP_Comp().get(r).click();
//                    this.Lst_Btn_Buy_BoxSP_Comp().get(1).click();
                    if (check_PopUp_Before_AddToCart()) {
                        wait.waitForTryCatch_ClickableBySelector(btn_buy_in_popup_Comp_Selector).click();
                        sleepInSeconds(1);
                    }
                    this.getInfo_Product(r);
                    sleepInSeconds(0.5);
                }
                i++;
                if (i > (n - 1)) {
                    swipeActions.SwipeUpWithTimes(2);
                    this.AddProductToCartRandom_Comp();
                }
            } while (!check_Procduct_AddedToCart());
        } else {
            Log.info("No products match the search keyword");
            saveTextLog("No products match the search keyword");
        }
        return this;
    }

    private void getInfo_Product_PopUp_Unit() {
        List<MobileElement> lst_productPrices = appiumDriver.findElements(txt_product_price_unit_in_popup_Comp_Selector);
        List<MobileElement> lst_ProductUnit = appiumDriver.findElements(txt_product_unit_in_popup_Comp_Selector);
        int r = RandomEx.RandomNextInt(lst_productPrices.size());
        if (lst_productPrices.size() > 1) {
            lst_productPrices.get(r).click();
        }
        String productPrice = lst_productPrices.get(r).getText();
        String productUnit = lst_ProductUnit.get(r).getText();
        Log.info("Info product added to cart: \nproductPrice:" + productPrice + "\nProduct Unit: " + productUnit);
        saveTextLog("Info product added to cart: \nproductPrice:" + productPrice + "\nProduct Unit: " + productUnit);
    }

    private void getInfo_Product_PopUp_Fresh() {
        List<MobileElement> lst_productPrices = appiumDriver.findElements(txt_product_price_fresh_in_popup_Comp_Selector);
        List<MobileElement> lst_ProductType = appiumDriver.findElements(txt_product_type_fresh_in_popup_Comp_Selector);

        int r = RandomEx.RandomNextInt(lst_productPrices.size());
        if (lst_productPrices.size() > 1) {
            lst_productPrices.get(r).click();
        }
        String productPrice = lst_productPrices.get(r).getText();
        String productType = lst_ProductType.get(r).getText();
        Log.info("Info product added to cart: \nProduct Unit: " + productType + "\nproductPrice:" + productPrice);
        saveTextLog("Info product added to cart: \nProduct Unit: " + productType + "\nproductPrice:" + productPrice);

    }
}