package Models;


import lombok.Data;

@Data
public class OrderData {
    public static int row;
    public static String province = "PROVINCE";
    public static String district = "DISTRICT";
    public static String ward = "WARD";
    public static String customerPhone = "CUSTOMER_PHONE";
    public static String customerName = "CUSTOMER_NAME";
    public static String customerGender = "CUSTOMER_GENDER";
    public static String address = "ADDRESS";
    public static String paymentType = "PAYMENT_TYPE";
    public static String searchKeyword = "KEYWORD";
    public static String shipDate = "SHIPDATE";
    public static String shipTime = "SHIPTIME";
    public static String note = "NOTE";
    public static String otherName = "OTHER_NAME";
    public static String otherPhone = "OTHER_PHONE";
    public static String otherGender = "OTHER_GENDER";
    public static String locationSelected = "LOCATION_SELECTED";
    public static String messageOder = "MESSAGE_ODER";
    public static String cartTotalCart2 = "CART_TOTAL_CART2";
    public static String oderType = "ODER_TYPE";
    public static String otpLogin = "OTP_LOGIN";
    public static String nameWithGenderReciever = "NAME_WITH_GENDER_RECIEVER";
    public static String nameWithGenderCustomer = "NAME_WITH_GENDER_CUSTOMER";
    public static String fullAddress = "FULL_ADDRESS";

    public static String getNameWithGenderCustomer() {
        return nameWithGenderCustomer;
    }
    public static String getFullAddress() {
        return nameWithGenderReciever;
    }

    public static String getNameWithGenderReciever() {
        return nameWithGenderReciever;
    }

    public static String getOtpLogin() {
        return otpLogin;
    }

    public static String getOderType() {
        return oderType;
    }

    public static String getLocationSelected() {
        return locationSelected;
    }

    public static String getMessageOder() {
        return messageOder;
    }

    public static String getCartTotalCart2() {
        return cartTotalCart2;
    }

    public static String getCustomerGender() {
        return customerGender;
    }

    public static String getOtherName() {
        return otherName;
    }

    public static String getOtherPhone() {
        return otherPhone;
    }

    public static String getOtherGender() {
        return otherGender;
    }

    public static String getProvince() {
        return province;
    }

    public static String getDistrict() {
        return district;
    }

    public static String getWard() {
        return ward;
    }

    public static String getSearchKeyword() {
        return searchKeyword;
    }

    public static String getCustomerPhone() {
        return customerPhone;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static String getAddress() {
        return address;
    }

    public static String getPaymentType() {
        return paymentType;
    }

    public static String getShipDate() {
        return shipDate;
    }

    public static String getShipTime() {
        return shipTime;
    }

    public static String getNote() {
        return note;
    }


}
