package Utils;

public class Constants {

    private static int getTimeToInitAppiumDriver() {
        PropertiesFile.setPropertiesFile("configs.properties");
        return Integer.parseInt(PropertiesFile.getPropValue("TIME_TO_INIT_APPIUM_DRIVER"));

    }

    private static int getWaitShortTime() {
        PropertiesFile.setPropertiesFile("configs.properties");
        return Integer.parseInt(PropertiesFile.getPropValue("WAIT_SHORT_TIME"));
    }

    private static int getWaitShortTrycatch() {
        PropertiesFile.setPropertiesFile("configs.properties");
        return Integer.parseInt(PropertiesFile.getPropValue("WAIT_SHORT_TRYCATCH"));
    }
    private static String getLocate() {
        PropertiesFile.setPropertiesFile("configs.properties");
        return PropertiesFile.getPropValue("LOCATE");
    }
    private static double getSleepTime() {
        PropertiesFile.setPropertiesFile("configs.properties");
        return Integer.parseInt(PropertiesFile.getPropValue("SLEEP_TIME"));
    }

    public static final int TIME_TO_INIT_APPIUM_DRIVER = getTimeToInitAppiumDriver();
    public static final int WAIT_SHORT_TIME = getWaitShortTime();
    public static final int WAIT_SHORT_TRYCATCH = getWaitShortTrycatch();
    public static final String LOCATE = getLocate();
    public static final String ADDRESS_SHOPHOUSE = "B1-04, Tầng 001, Block B1, Khu căn hộ - Thương mại DV cao tầng đường Kha Vạn Cân, Phường An Bình, TP. Dĩ An, Tỉnh Bình Dương";
    public static final double SLEEP_TIME = getSleepTime();


//    public final static long WAIT_PAGE_LOADED = 20;
}
