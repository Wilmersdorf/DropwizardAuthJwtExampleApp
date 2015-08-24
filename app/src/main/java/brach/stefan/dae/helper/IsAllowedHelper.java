package brach.stefan.dae.helper;

import android.text.TextUtils;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.preferences.PreferencesHelper;

public class IsAllowedHelper {
    public static boolean isAllowed(MainActivity ma, String email) {
        if (ma == null) return false;
        String prefEmail = PreferencesHelper.getEmail(ma);
        if (TextUtils.isEmpty(email)) return false;
        if (TextUtils.isEmpty(prefEmail)) return false;
        if (email.equals(prefEmail)) return true;
        return false;
    }
}
