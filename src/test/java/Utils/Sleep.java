package Utils;

public class Sleep {

    public static void sleepInSeconds(double time) {
        try {
            Thread.sleep((long) (Constants.SLEEP_TIME + (time * 1000)));
        } catch (Exception ex) {
            System.out.println("[ERR]" + ex);
        }
    }
}
