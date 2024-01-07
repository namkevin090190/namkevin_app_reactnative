package PageObject.Components;

import Common.Log;
import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static Helpers.Reporter.AllureReportManager.*;
import static Utils.Sleep.sleepInSeconds;

public class CancelOderComponent extends PageModel_Base {

    private final By lst_rdo_option_canceloder_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='orderResult_btn_Radio'])");
    private final By txt_reaseon_canceloder_Comp_Selector = MobileBy.xpath("(//android.view.ViewGroup[@resource-id='txt_input_view'])//(android.widget.EditText)");
    private final By btn_accept_canceloder_popup_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='orderResult_btn_CancelOrder'])");
    private final By btn_close_canceloder_popup_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='orderResult_btn_CancelOrder_close'])");
    private final By btn_canceloder_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='orderResult_txt_CancelOrder'])");
    private final By btn_backhome_after_canceloder_Comp_Selector = MobileBy.xpath("(//android.widget.TextView[@resource-id='orderResult_btn_CancelOrderSuccess'])");



    public CancelOderComponent(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }

    private MobileElement txt_Reaseon_CancelOder_Comp() {
        return wait.waitForClickAbleBySelector(txt_reaseon_canceloder_Comp_Selector);
    }

    private MobileElement btn_Accept_CancelOder_Popup_Comp() {
        return wait.waitForClickAbleBySelector(btn_accept_canceloder_popup_Comp_Selector);
    }

    private MobileElement btn_Cancel_CancelOder_Popup_Comp() {
        return wait.waitForClickAbleBySelector(btn_close_canceloder_popup_Comp_Selector);
    }

    private MobileElement btn_CancelOder_Comp() {
        return wait.waitForClickAbleBySelector(btn_canceloder_Comp_Selector);
    }

    private MobileElement rdi_option_canceloder_last_Comp() {
        List<MobileElement> lst_options = this.appiumDriver.findElements(lst_rdo_option_canceloder_Comp_Selector);
        return wait.waitForClickAbleByElement(lst_options.get((lst_options.size() - 1)));
    }
    private MobileElement btn_Backhome_After_CancelOder_Comp() {
        return wait.waitForClickAbleBySelector(btn_backhome_after_canceloder_Comp_Selector);
    }

    @Step("Cancel OrderTest")
    public CancelOderComponent Cancel_Oder_Comp() {
        this.btn_CancelOder_Comp().click();
        sleepInSeconds(1.5);
        this.rdi_option_canceloder_last_Comp().click();
        this.txt_Reaseon_CancelOder_Comp().sendKeys("IT test");
        this.btn_Accept_CancelOder_Popup_Comp().click();
        Log.info("Cancel oder successfully! ");
        this.btn_Backhome_After_CancelOder_Comp().click();
        Log.info("Go to Home!");

        saveTextLog("Cancel oder successfully! ");
        return this;
    }

}
