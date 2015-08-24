package brach.stefan.dae.helper;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidator {
    public static boolean isValidUrl(String url) {
        Pattern p = Patterns.WEB_URL;
        Matcher m = p.matcher(url);
        if (m.matches())
            return true;
        else
            return false;
    }
}
