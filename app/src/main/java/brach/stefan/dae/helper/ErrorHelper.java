package brach.stefan.dae.helper;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import brach.stefan.dae.MainActivity;

public class ErrorHelper {
    @NonNull
    public static String getTranslatedErrMsg(MainActivity ma, String message) {
        String toReturn = "";
        if (!TextUtils.isEmpty(message)) {
            Logger.log("getTranslatedErrMsg " + message);
            toReturn = TranslationHelper.getString(ma, message);
        }
        return toReturn;
    }
}
