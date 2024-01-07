package DataTest.dataprovider;

import Helpers.ExcelHelper.Excel_Helpers;
import Helpers.Helpers;
import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class DataProviderManager {

    private DataProviderManager() {
    }

    static Excel_Helpers excel = new Excel_Helpers();

    @DataProvider
    public static Object[][] getOrderDeliveryData() {
        String excelPath = Helpers.getProjectPath() + "\\resources\\DataTest\\DatHang.xlsx";
        Object[][] data = excel.getDataHashMap(excelPath, "Delivery", 1, 1);
        System.out.println("getOderData: " + Arrays.deepToString(data));
        return data;
    }

    @DataProvider
    public static Object[][] getOrderDeliveryDataWithOtherCustomer() {
        String excelPath = Helpers.getProjectPath() + "\\resources\\DataTest\\DatHang.xlsx";
        Object[][] data = excel.getDataHashMap(excelPath, "Delivery", 2, 2);
        System.out.println("getOderData: " + Arrays.deepToString(data));
        return data;
    }

    @DataProvider
    public static Object[][] getOrderShopHouseData() {
        String excelPath = Helpers.getProjectPath() + "\\resources\\DataTest\\DatHang.xlsx";
        Object[][] data = excel.getDataHashMap(excelPath, "Delivery", 3, 3);
        System.out.println("getOderData: " + Arrays.deepToString(data));
        return data;
    }

    @DataProvider
    public static Object[][] getOrderDeliveryDataLogin() {
        String excelPath = Helpers.getProjectPath() + "\\resources\\DataTest\\DatHang.xlsx";
        Object[][] data = excel.getDataHashMap(excelPath, "Delivery", 4, 4);
        System.out.println("getOderData: " + Arrays.deepToString(data));
        return data;
    }

    @DataProvider
    public static Object[][] getOrderDeliveryDataLoginWithOtherCustomer() {
        String excelPath = Helpers.getProjectPath() + "\\resources\\DataTest\\DatHang.xlsx";
        Object[][] data = excel.getDataHashMap(excelPath, "Delivery", 5, 5);
        System.out.println("getOderData: " + Arrays.deepToString(data));
        return data;
    }

}
