package TestScript;

import Common.Log;
import Helpers.Capture.ScreenRecord;
import Helpers.Reporter.AllureReportManager;
import Helpers.Reporter.Listener;
import TestFlows.OrderFlows;
import Utils.API_Services.GoogleDrive;
import Utils.PropertiesFile;
import io.qameta.allure.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.util.Map;


import static Helpers.Helpers.*;
import static Helpers.Reporter.AllureReportManager.saveTextLog;


@Listeners(Listener.class)
public class OrderTest extends BaseTest {
    OrderFlows orderFlows;
    File record;
    GoogleDrive drive;

    @BeforeClass
    public void InitOderFlows() {
        if (orderFlows == null)
            orderFlows = new OrderFlows();
    }

    @Step("Start record")
    @BeforeMethod
    public void StartRecord() {
//
        String screenName = OrderTest.class.getName().replace("TestScript.", "");
        Log.info("screenName: " + screenName);
        record = ScreenRecord.startRecord(screenName);

    }

    @Step("Send Notify")
    @AfterMethod
    public void SendNotify(ITestResult result) throws Exception {
        PropertiesFile.setPropertiesFile("configs.properties");
        String isSendNotify = PropertiesFile.getPropValue("isSendNotification");
        if (drive == null)
            drive = new GoogleDrive();

        try {
            if (orderFlows.getOderID() != null) {
                orderFlows.CancelOder();
                Log.info("Cancel oder ".concat(orderFlows.getOderID()).concat(" Success! "));
                saveTextLog("Cancel oder ".concat(orderFlows.getOderID()).concat(" Success! "));
            }
        } catch (Exception ignore) {
        }
//
        ScreenRecord.stopRecord();
        if (!result.isSuccess()) {
//            File screenshot = TakeAScreenShot(result);
//            String link = drive.UploadFilePhoto(createFileNameImage(result), screenshot);
            String link = Listener.getLink();
            String linkRC = drive.UploadFileVideo(createFileNameVideo(result), record);
            AllureReportManager.attachText("Video Record: ", linkRC);
            AllureReportManager.attachVideo(record);
            if (isSendNotify.equalsIgnoreCase("true")) {
                SendNotifyToLine(result, orderFlows.getOderID(), link, linkRC);
            }
        } else {
            if (isSendNotify.equalsIgnoreCase("true")) {
                SendNotifyToLine(result, orderFlows.getOderID());
            }

        }
        orderFlows.setOderID(null);
        try {
            orderFlows.BackHome();
        } catch (Exception ignored) {
        }

        if (!result.isSuccess() && result.getName().contains("Login"))
            orderFlows.Logout();
    }

    @Test(dataProvider = "getOrderDeliveryData", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    @Story("Orrder from the search results page")
    public void Oder_Delivery(Map<String, String> data) {
        orderFlows.OrderDelivery(data);
    }

    @Story("Order from shophouse")
    @Test(dataProvider = "getOrderShopHouseData", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    public void Oder_ShopHouse(Map<String, String> data) {
        orderFlows.OrderShophouse(data);
    }

    @Story("Order Delivery with other customer")
    @Test(dataProvider = "getOrderDeliveryDataWithOtherCustomer", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    public void Oder_DeliveryWithOtherCustomer(Map<String, String> data) {
        orderFlows.OrderDeliveryWithOtherCustomer(data);
    }
    @Story("Order Delivery Login")
    @Test(dataProvider = "getOrderDeliveryDataLogin", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    public void Oder_DeliveryLogin(Map<String, String> data) {
        orderFlows.OrderDeliveryLogin(data);
    }

    @Story("Oder Delivery with other customer")
    @Test(dataProvider = "getOrderDeliveryDataLoginWithOtherCustomer", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    public void Oder_DeliveryLoginWithOtherCustomer(Map<String, String> data) {
        orderFlows.OrderDeliveryLoginWithOtherCustomer(data);
    }
    @Story("Check Address Shipping when Select shophouse and tab delevery")
    @Test(dataProvider = "getOrderShopHouseData", dataProviderClass = DataTest.dataprovider.DataProviderManager.class)
    public void checkAddress(Map<String, String> data) {
        orderFlows.checkAddressWhenSelectShop(data);
    }


}

