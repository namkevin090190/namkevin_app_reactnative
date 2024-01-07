package Utils;

import org.apache.commons.text.WordUtils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringHandler {

    public static String removeAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("");
    }

    public static String capitalizeFully(String text) {
        return WordUtils.capitalizeFully(text);
    }
}
