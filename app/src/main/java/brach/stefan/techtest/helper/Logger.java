package brach.stefan.techtest.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Logger {
    public final static void warn(String message) {
        Log.w(" ", message);
    }

    public final static void log(String message) {
        Log.d(" ", message);
    }

    public final static void log(String message, JSONObject jo) {
        JB.LogJSONObject(message, jo);
    }

    public final static void log(String message, JSONArray ja) {
        JB.LogJSONArray(message, ja);
    }

    public final static String separator = "--------------------------";

    public final static void logJsonException(JSONException e) {
        String msg = "";
        if (e != null && e.getMessage() != null) {
            msg = e.getMessage();
        }
        Log.w(" ", "jsonException: " + msg);
    }
}
