package Helpers.DataFaker;
import Utils.Constants;
import net.datafaker.Faker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class DataFaker {
    private static Faker faker;

    public static Faker createFaker() {
        faker = new Faker(new Locale(Constants.LOCATE));
        return faker;
    }

    public static Faker createFakerByLocate(String locateName) {
        faker = new Faker(new Locale(locateName));
        return faker;
    }

    public static Faker getFaker() {
        if (faker == null) {
            faker = createFaker();
        }
        return faker;
    }

    public static Faker getFakerByLocate(String locateName) {
        faker = createFakerByLocate(locateName);
        return faker;
    }

//    public static void setFaker(Faker faker) {
//        DataFaker.faker = faker;
//    }


    public static String getCellPhoneByFaker() {
//            String[] phoneTest = {"0938727300","0938727600","0369130313"};
            String[] phoneTest = {"0938727300","0938727600"};
            return phoneTest[new Random().nextInt(phoneTest.length)];
    }

    public static String getAddressByFaker() {
        return getFakerByLocate("vi").address().streetAddressNumber() +  " " + getFakerByLocate("vi").address().streetName();
    }

    public static String getFullNameByFaker() {
        return getFakerByLocate("vi").name().fullName();
    }
}
