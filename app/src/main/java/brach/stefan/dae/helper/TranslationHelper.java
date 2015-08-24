package brach.stefan.dae.helper;

import android.support.annotation.NonNull;

import brach.stefan.dae.MainActivity;

public class TranslationHelper {
    @NonNull
    public static String getString(MainActivity ma, String string) {
        int id = ma.getResources().getIdentifier(string, "string", ma.getPackageName());
        if (id == 0) return "";
        return ma.getResources().getString(id);
    }
}
