package PageObject.Page_Model;

import Common.Log;
import PageObject.Base.PageModel_Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;

import static Utils.StringHandler.capitalizeFully;

public class CartStep3 extends PageModel_Base {


    private final By btn_continue_buy_cart3_Selector = MobileBy.xpath("/android.widget.TextView[@resource-id='orderResult_txt_continueBuy'])");
    private final By lst_txt_customer_info_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='billInfo_txt_customerName']");   //người đặt hàng
    private final By lst_txt_other_info_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='billInfo_txt_RecieveName']");      //người nhận hàng
    private final By txt_shipdatetime_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='billInfo_txt_TimeRecieve']");
    private final By txt_payment_selected_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='billInfo_txt_paymentType']");
    private final By txt_sumtotal_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='billInfo_txt_cartTotal']");
    private final By btn_close_popup_cusreview_cart3_Selector = MobileBy.xpath("//android.widget.ImageView[@resource-id='orderResult_popupCusReview_Close']");
    protected final By txt_title_oder_results_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='cart_title_orderResult']");
    protected final By txt_oderid_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='orderResult_orderId']");
    protected final By txt_message_oder_cart3_Selector = MobileBy.xpath("//android.widget.TextView[@resource-id='txt_message_oderID']");

    public CartStep3(AppiumDriver<MobileElement> appiumDriver) {
        super(appiumDriver);
    }


    protected List<MobileElement> txt_Customer_Info_Cart3() {
        wait.waitForVisibilityOfSelector(lst_txt_customer_info_cart3_Selector);
        return this.appiumDriver.findElements(lst_txt_customer_info_cart3_Selector);
    }

    protected List<MobileElement> txt_Other_Info_Cart3() {
        wait.waitForVisibilityOfSelector(lst_txt_other_info_cart3_Selector);
        return this.appiumDriver.findElements(lst_txt_other_info_cart3_Selector);
    }

    protected MobileElement txt_ShipDateTime_Cart3() {
        return wait.waitForVisibilityOfSelector(txt_shipdatetime_cart3_Selector);
    }

    protected MobileElement txt_Payment_Selected_Cart3() {
        return wait.waitForVisibilityOfSelector(txt_payment_selected_cart3_Selector);
    }

    protected MobileElement txt_SumTotal_Cart3() {
        return wait.waitForVisibilityOfSelector(txt_sumtotal_cart3_Selector);
    }

    protected MobileElement btn_Close_Popup_CusReview() {
        return wait.waitForClickAbleBySelector(btn_close_popup_cusreview_cart3_Selector);
    }


    protected String getMessageOder_Cart3() {
        try {
            return wait.waitForVisibilityOfSelector(txt_message_oder_cart3_Selector).getText();
        } catch (Exception e) {
            return null;
        }
    }

    private String getLastName_Reciever() {
        String[] recieverinfo = this.txt_Other_Info_Cart3().get(0).getText().split(",");
        return capitalizeFully(recieverinfo[0].trim());
    }

    private String getPhone_Reciever() {
        String[] recieverinfo = this.txt_Other_Info_Cart3().get(0).getText().split(",");
        return recieverinfo[1].trim();
    }

    private String getLastName_Customer() {
        try {
            String[] customerinfo = this.txt_Customer_Info_Cart3().get(0).getText().split(",");
            Log.info("Customer info: " + customerinfo[0]);
            return capitalizeFully(customerinfo[0].trim());
        } catch (Exception e) {
            return null;
        }
    }

    private String getPhone_Customer() {
        try {
            String[] customerinfo = this.txt_Customer_Info_Cart3().get(0).getText().split(",");
            return customerinfo[1].trim();
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * The function extracts the last name from a full name and returns it along with a gender prefix.
     *
     * @param Gender   The gender of the customer, which is used to prefix the last name in the returned string.
     * @param fullName The full name of the customer.
     * @return The method is returning a string that concatenates the input `Gender` with the last name of the customer
     * extracted from the input `fullName`.
     */
    private String getNameWithGender(String Gender, String fullName) {
        String[] CustomerfullName = fullName.split(" ");
        String CustomerlastName = CustomerfullName[(CustomerfullName.length) - 1];
        return capitalizeFully(Gender + " " + CustomerlastName);
    }

    @Step("Verify Info Reciver Cart3")
    protected void Verify_Info_Other_Cart3(String expectedGender, String expectedName, String expectedPhone) {
        String expected_nameWithGender = getNameWithGender(expectedGender, expectedName);
//        if(data.get(OrderData.getNameWithGenderReciever()).isEmpty()){
//            data.put(OrderData.getNameWithGenderReciever(), expected_nameWithGender);
//        }
        softAssert.assertEquals(getLastName_Reciever(), expected_nameWithGender, "Sai tên người nhận");
        softAssert.assertEquals(getPhone_Reciever().trim(), expectedPhone.trim(), "Sai số điện thoại người nhận");
    }

    @Step("Verify Info Customer Cart3")
    protected void Verify_Info_Customer_Cart3(String expectedGender, String expectedName, String expectedPhone) {
        String expected_nameWithGender = getNameWithGender(expectedGender, expectedName);
        softAssert.assertEquals(getLastName_Customer(), expected_nameWithGender, "Sai tên người đặt");
        softAssert.assertEquals(getPhone_Customer().trim(), expectedPhone.trim(), "Sai số điện thoại người đặt");
    }

    @Step("Verify Message Oder Cart3")
    protected void Verify_MessageOder_Cart3(String expectedGender_Customer, String expectedName_Customer, String expectedGender_Reciever, String expectedName_Reciever) {
        String expected_Customer = getNameWithGender(expectedGender_Customer, expectedName_Customer);
        String expected_Reciever = getNameWithGender(expectedGender_Reciever, expectedName_Reciever);
        String actualMessage = this.getMessageOder_Cart3().trim();
        String expectedMessage = ("BHX sẽ gửi thông báo đến ".concat(expected_Reciever).concat(" và ").concat(expected_Customer).trim()).trim();
        softAssert.assertEquals(actualMessage, expectedMessage, "[ERR] - Sai thông báo");
    }

    @Step("Verify ShipAddress Cart3")
    protected void Verify_Address_Cart3(String expectedAddress) {

        String acutal_address = this.txt_Other_Info_Cart3().get(1).getText().toLowerCase().trim()
                .replace("tp. ", "")
                .replace("tx.", "")
                .replace("thị trấn", "")
                .replace("thị xã", "")
                .replace("thành phố", "")
                .replace("huyện", "")
                .replace("tt", "")
                .replace("phường 0", "phường")
                .replace("r'lấp", "rlấp")
                .replace("m'gar", "mgar")
                .trim();

        String[] expected_address = expectedAddress.replace("'", "").toLowerCase().split(",");
        Log.info("acutal_address: " + acutal_address);
        String phuongXa = expected_address[1].toLowerCase().trim()
                .replace("đăk", "đắk")
                .replace("thị trấn", "")
                .replace("phường 0", "phường");


        String quanHuyen = expected_address[2].toLowerCase().trim()
                .replace("đăk", "đắk")
                .replace("tx.", "")
                .replace("thị trấn", "")
                .replace("tt", "")
                .replace("huyện ", "")
                .replace("thị xã", "")
                .replace("tp. ", "")
                .replace("r'lấp", "rlấp")
                .replace("m'gar", "mgar")
                .trim();

        String tinhThanh = expected_address[3].toLowerCase().trim()
                .replace("đăk", "đắk")
                .replace("tp. ", "")
                .replace("thành phố", "");

        Log.info("address: " + expected_address[0].toLowerCase().trim());
        Log.info("phuongXa: " + phuongXa);
        Log.info("quanHuyen: " + quanHuyen);
        Log.info("tinhThanh: " + tinhThanh);

        boolean address = acutal_address.toLowerCase().contains(expected_address[0].toLowerCase().trim());
        boolean phuongxa = acutal_address.toLowerCase().contains(phuongXa);
        boolean quanhuyen = acutal_address.toLowerCase().contains(quanHuyen);
        boolean tinhthanh = acutal_address.toLowerCase().contains(tinhThanh);


        softAssert.assertTrue(address, "[ERR] - Sai address giao hàng");
        softAssert.assertTrue(phuongxa, "[ERR] - Sai phuongxa giao hàng");
        softAssert.assertTrue(quanhuyen, "[ERR] - Sai quanhuyen giao hàng");
        softAssert.assertTrue(tinhthanh, "[ERR] - Sai tinhthanh giao hàng");
    }

    @Step("Verify Payment Cart3")
    protected void Verify_Payment(String expectedPayment) {
        String actual_payment = this.txt_Payment_Selected_Cart3().getText().toLowerCase().replace("khi nhận hàng", "").trim();
        softAssert.assertEquals(actual_payment, expectedPayment.toLowerCase(), "[ERR] - Sai Hình thức thanh toán");
    }

    @Step("Verify SumTotal Cart3")
    protected void Verify_SumTotal_Cart3(String expectedSumTotal) {
        String actual_sum_total = this.txt_SumTotal_Cart3().getText().trim().replace("đ", "");
        softAssert.assertEquals(actual_sum_total, expectedSumTotal.replace("đ", ""), "[ERR] - Sai tổng tiền");
    }

    @Step("Verify ShipDateTime Cart3")
    protected void Verify_ShipDateTime(String expectedShipDate, String expectedShipTime) {
        String actual_ship_date_time = this.txt_ShipDateTime_Cart3().getText().trim();
        String expectedShipDateTime = expectedShipTime.substring(0, 9).concat(" - ").concat(expectedShipDate);
        softAssert.assertEquals(actual_ship_date_time, expectedShipDateTime, "[ERR] - Sai ngày giao hàng");
    }
}
