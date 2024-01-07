package TestFlows;


import Common.Log;
import Models.OrderData;
import PageObject.Pages.HomePage;
import TestScript.BaseTest;


import java.util.Map;

import static DriverFactory.AppiumDriverEx.getDriver;
import static Utils.Sleep.sleepInSeconds;

public class OrderFlows extends BaseTest {
    String OderID;

    public void setOderID(String oderID) {
        OderID = oderID;
    }

    public String getOderID() {
        return this.OderID;
    }


    HomePage homePage = new HomePage(getDriver());

    public void CancelOder() {
        if (homePage.NavigateToCartStep3().check_isAtCart3Page()) {
            homePage.NavigateToCartStep3().Cancel_Oder();
        }
    }

    public void BackHome() {
        if (!homePage.check_isAtHome()) {
            homePage.BacktoHome_Menu();
        }
    }

    private void InputOtherInfo(Map<String, String> data) {
        homePage.NavigateToCartStep2().input_OtherCustomerInfo(data.get(OrderData.getOtherName()), data.get(OrderData.getOtherPhone()));
        String OtherGender = homePage.NavigateToCartStep2().choose_Other_Gender();
        data.put(OrderData.getOtherGender(), OtherGender);
    }

    private void ChooseLocationRandom(Map<String, String> data) {
        String selected_Location = homePage.Chosse_Location();
        data.put(OrderData.getLocationSelected(), selected_Location);
    }

    private void ChooseLoactionWithInput(Map<String, String> data) {
        String Location_selected = data.get(OrderData.getWard()) + ", " + data.get(OrderData.getDistrict()) + ", " + data.get(OrderData.getProvince());
        data.put(OrderData.getLocationSelected(), Location_selected);
        homePage.ChooseLocationWithInput(data.get(OrderData.getProvince()), data.get(OrderData.getDistrict()), data.get(OrderData.getWard()));
    }

    private void AddProdcutToCartAndGoToCart2(Map<String, String> data) {
        homePage
                .input_SearchProducts(data.get(OrderData.getSearchKeyword()))
                .NavigateToSearchResult()
                .AddProduct_Random()
                .click_IconCart_Header()
                .NavigateToCartStep1()
                .Click_DatHang_ProcessBar_Cart1();
    }

    private void InputInfoCustomer(Map<String, String> data) {
//        .handle_listaddress(dataTest);
        homePage.NavigateToCartStep2()
                .input_CustomerInfo(data.get(OrderData.getCustomerPhone()), data.get(OrderData.getCustomerName()), data.get(OrderData.getAddress()));
        String gender = homePage.NavigateToCartStep2().choose_Gender();
        data.put(OrderData.getCustomerGender(), gender);
        Log.info("Selected Gender : " + gender);
    }

    private void ChooseShipDateTimeAndPaymentType(Map<String, String> data) {
        homePage.NavigateToCartStep2()
                .choose_ShipDateTime();

        data.put(OrderData.getShipDate(), homePage.NavigateToCartStep2().getShipDate());
        if (homePage.NavigateToCartStep2().getShipTime().equalsIgnoreCase("Thời gian nhận")) {
            sleepInSeconds(3);
            data.put(OrderData.getShipTime(), homePage.NavigateToCartStep2().getShipTime());
        } else
            data.put(OrderData.getShipTime(), homePage.NavigateToCartStep2().getShipTime());

        homePage.NavigateToCartStep2().choose_Payment(data.get(OrderData.getPaymentType()));

        String Total_Cart2 = homePage.NavigateToCartStep2().get_SumTotal_Cart2();
        data.put(OrderData.getCartTotalCart2(), Total_Cart2);
        Log.info("Total Cart2: " + Total_Cart2);
    }

    private void SubmitAndVerify(Map<String, String> data) {
        homePage.NavigateToCartStep2()
                .click_SubmitOder();
        this.OderID = homePage.NavigateToCartStep3().getOderID();
        homePage.NavigateToCartStep3()
                .Verify_OderInfo(data);
    }

    private void CancelOderAfterOdered() {
        if (OderID != null) {
            CancelOder();
            Log.info("Cancel OderID: " + OderID + " Success!");
        }
    }

    private void Login(Map<String, String> data) {
        homePage.NavigateToAccountPage()
                .Login_Account(data.get(OrderData.getCustomerPhone()), data.get(OrderData.getOtpLogin()))
                .getCustomerInfo(data);

    }

    public void Logout() {
        BackHome();
        homePage.NavigateToAccountPage()
                .Logout_Account();
    }


    public void OrderDelivery(Map<String, String> dataTest) {
        ChooseLocationRandom(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
//        InputInfoCustomer(dataTest);
         homePage.NavigateToCartStep2().handle_listaddress(dataTest);
        ChooseShipDateTimeAndPaymentType(dataTest);
        SubmitAndVerify(dataTest);
        CancelOderAfterOdered();
    }

    public void OrderShophouse(Map<String, String> dataTest) {
        ChooseLoactionWithInput(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
        homePage.NavigateToCartStep2().Select_Shophouse();
        dataTest.put(OrderData.getAddress(), homePage.NavigateToCartStep2().getAddressShophouse());
        InputInfoCustomer(dataTest);
                                                                                                  ChooseShipDateTimeAndPaymentType(dataTest);
        homePage.NavigateToCartStep2().verify_Rules_Shophouse();
        SubmitAndVerify(dataTest);
        CancelOderAfterOdered();
    }

    public void OrderDeliveryWithOtherCustomer(Map<String, String> dataTest) {
        ChooseLocationRandom(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
        InputInfoCustomer(dataTest);
        InputOtherInfo(dataTest);
        ChooseShipDateTimeAndPaymentType(dataTest);
        SubmitAndVerify(dataTest);
        CancelOderAfterOdered();
    }

    public void OrderDeliveryLogin(Map<String, String> dataTest) {
        Login(dataTest);
        homePage.NavigateToBarComponent().click_Tab_Home();
        ChooseLoactionWithInput(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
        homePage.NavigateToCartStep2().handle_listaddress(dataTest);
        ChooseShipDateTimeAndPaymentType(dataTest);
        SubmitAndVerify(dataTest);
        CancelOderAfterOdered();
        Logout();
    }

    public void OrderDeliveryLoginWithOtherCustomer(Map<String, String> dataTest) {
        Login(dataTest);
        homePage.NavigateToBarComponent().click_Tab_Home();
        ChooseLoactionWithInput(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
        homePage.NavigateToCartStep2().handle_listaddress(dataTest);
        ChooseShipDateTimeAndPaymentType(dataTest);
        SubmitAndVerify(dataTest);
        CancelOderAfterOdered();
        Logout();
    }

    public void checkAddressWhenSelectShop(Map<String, String> dataTest) {
        getDriver().resetApp();
        ChooseLoactionWithInput(dataTest);
        AddProdcutToCartAndGoToCart2(dataTest);
        homePage.NavigateToCartStep2().check_Address_Shipping_Cart2();
    }

}
