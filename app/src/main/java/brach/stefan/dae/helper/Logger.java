package brach.stefan.dae.helper;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import brach.stefan.dae.MainActivity;

public class Logger {
    public final static void warn(String message) {
        if (MainActivity.isLog() == true) {
            Log.w(" ", message);
        }
    }

    public final static void log(String message) {
        if (MainActivity.isLog() == true) {
            Log.d(" ", message);
        }
    }

    public final static void log(String message, JSONObject jo) {
        if (MainActivity.isLog() == true) {
            JB.LogJSONObject(message, jo);
        }
    }

    public final static void log(String message, JSONArray ja) {
        if (MainActivity.isLog() == true) {
            JB.LogJSONArray(message, ja);
        }
    }

    public final static String separator = "--------------------------";

    public final static void logJsonException(JSONException e) {
        if (MainActivity.isLog() == true) {
            String msg = "";
            if (e != null && e.getMessage() != null) {
                msg = e.getMessage();
            }
            Log.w(" ", "jsonException: " + msg);
        }
    }
}
