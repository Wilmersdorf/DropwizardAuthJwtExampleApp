package brach.stefan.dae.preferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Pref;
import brach.stefan.dae.enums.Role;

public class PreferencesHelper {
    public static void saveCachedEmail(MainActivity ma, String cachedEmail) {
        saveString(ma, Pref.CACHED_EMAIL.toString(), cachedEmail);
    }

    public static String getCachedEmail(MainActivity ma) {
        String cachedEmail = getString(ma, Pref.CACHED_EMAIL.toString());
        if (TextUtils.isEmpty(cachedEmail)) {
            cachedEmail = "admin@test.io";
        }
        return cachedEmail;
    }

    public static void saveAuthToken(MainActivity ma, String authToken) {
        saveString(ma, Pref.AUTHTOKEN.toString(), authToken);
    }

    public static String getAuthToken(MainActivity ma) {
        return getString(ma, Pref.AUTHTOKEN.toString());
    }

    public static void saveEmail(MainActivity ma, String email) {
        saveString(ma, Pref.EMAIL.toString(), email);
    }

    public static String getEmail(MainActivity ma) {
        return getString(ma, Pref.EMAIL.toString());
    }

    public static void saveRole(MainActivity ma, Role role) {
        if (role != null) {
            saveString(ma, Pref.ROLE.toString(), role.toString());
        }
    }

    public static Role getRole(MainActivity ma) {
        String roleStr = getString(ma, Pref.ROLE.toString());
        Role role = Role.parse(roleStr);
        return role;
    }

    public static void saveUrl(MainActivity ma, String url) {
        saveString(ma, Pref.URL.toString(), url);
    }

    public static String getUrl(MainActivity ma) {
        return getString(ma, Pref.URL.toString());
    }

    public static void saveBoolean(MainActivity ma, String key, boolean value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ma);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public static boolean getBoolean(MainActivity ma, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ma);
        boolean value = preferences.getBoolean(key, false);
        return value;
    }

    public static void saveString(MainActivity ma, String key, String value) {
        if (!TextUtils.isEmpty(key)) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ma);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public static String getString(MainActivity ma, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ma);
        String value = preferences.getString(key, "");
        return value;
    }
}
