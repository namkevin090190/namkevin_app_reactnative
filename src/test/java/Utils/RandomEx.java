package Utils;

import Common.Log;
import io.appium.java_client.MobileElement;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomEx {
    static Random random = new Random();

    public static String RandomlySelectOneFromList(List<MobileElement> List) {
        int r = random.nextInt(List.size());
        if (List.get(r).isEnabled()) {
            List.get(r).click();
        } else {
            Log.info(List.get(r).getText() + " is disable!");
        }
        return List.get(r).getText();
    }

    public static int RandomNextInt(int max) {
        return random.nextInt(max);
    }


}
