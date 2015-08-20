package brach.stefan.techtest.helper;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import brach.stefan.techtest.MainActivity;

public class JB {
    public final static String ObjectToString(Object obj) {
        if (obj == null) {
            return new JSONObject().toString();
        } else {
            return MainActivity.getGson().toJson(obj);
        }
    }

    public final static JSONObject fromString(String s) {
        JSONObject jo = null;
        try {
            jo = new JSONObject(s);
        } catch (JSONException e) {
            Logger.logJsonException(e);
        }
        return jo;
    }

    public final static JSONObject put(JSONObject jo, String name, Object value) {
        try {
            jo.put(name, value);
        } catch (JSONException e) {
            Logger.logJsonException(e);
        }
        return jo;
    }

    public final static String toString(JSONArray ja, int format) {
        if (ja == null) return "NULL";
        String s = "";
        try {
            s = ja.toString(format);
        } catch (JSONException e) {
            Logger.logJsonException(e);
        }
        return s;
    }

    public final static void LogJSONArray(final String message, final JSONArray ja) {
        if (message != null) Logger.log(message);
        for (int i = 0; i < ja.length(); i++) {
            try {
                Object object = ja.get(i);
                if (object == null) {
                    Logger.log(i + ": null");
                } else if (object instanceof JSONObject) {
                    JSONObject objectJo = (JSONObject) object;
                    Logger.log(i + ": " + objectJo.toString(4));
                } else if (object instanceof JSONArray) {
                    JSONArray objectJa = (JSONArray) object;
                    Logger.log(i + ": " + objectJa.toString(4));
                } else if (object instanceof String) {
                    String objectStr = (String) object;
                    Logger.log(i + ": " + objectStr);
                } else if (object instanceof Integer) {
                    Integer objectInt = (Integer) object;
                    Logger.log(i + ": " + objectInt);
                } else if (object instanceof Long) {
                    Long objectLong = (Long) object;
                    Logger.log(i + ": " + objectLong);
                } else if (object instanceof Boolean) {
                    Boolean objectBool = (Boolean) object;
                    Logger.log(i + ": " + objectBool);
                }
            } catch (JSONException e) {
            }
        }
    }

    public final static void LogJSONObject(final String message, final JSONObject jo) {
        if (jo == null) Logger.log(message + " is null");
        else if (jo.length() == 0) Logger.log(message + " has length 0");
        else {
            Logger.log("---" + message + "---");
            Iterator<String> iter = jo.keys();
            while (iter.hasNext()) {
                String key = iter.next();
                try {
                    Object object = jo.get(key);
                    if (object == null) {
                        Logger.log(key + ": null");
                    } else if (object instanceof JSONObject) {
                        JSONObject objectJo = (JSONObject) object;
                        Logger.log(key + ": " + objectJo.toString(4));
                    } else if (object instanceof JSONArray) {
                        JSONArray objectJa = (JSONArray) object;
                        Logger.log(key + ": " + objectJa.toString(4));
                    } else if (object instanceof String) {
                        String objectStr = (String) object;
                        Logger.log(key + ": " + objectStr);
                    } else if (object instanceof Integer) {
                        Integer objectStr = (Integer) object;
                        Logger.log(key + ": " + objectStr);
                    } else if (object instanceof Long) {
                        Long objectLong = (Long) object;
                        Logger.log(key + ": " + objectLong);
                    } else if (object instanceof Boolean) {
                        Boolean objectBool = (Boolean) object;
                        Logger.log(key + ": " + objectBool);
                    }
                } catch (JSONException e) {
                }
            }
        }
    }

    public final static String toString(JSONObject jo, int format) {
        if (jo == null) return "NULL";
        String s = "";
        try {
            s = jo.toString(format);
        } catch (JSONException e) {
            Logger.logJsonException(e);
        }
        return s;
    }

    public final static long optLongFromJOInJO(JSONObject inJo, String fromJo, String key) {
        if (inJo == null) return 0;
        JSONObject jo = inJo.optJSONObject(fromJo);
        if (jo == null) return 0;
        else {
            return jo.optLong(key);
        }
    }

    public final static String optStringFromJOInJO(JSONObject inJo, String fromJo, String key) {
        if (inJo == null) return "";
        JSONObject jo = inJo.optJSONObject(fromJo);
        if (jo == null) return "";
        else {
            return optString(jo, key);
        }
    }

    public final static JSONArray optJAFromJO(JSONObject jo, String nameJa) {
        JSONArray ja = jo.optJSONArray(nameJa);
        if (ja == null) ja = new JSONArray();
        return ja;
    }

    public final static JSONObject optJOFromJA(JSONArray ja, int index) {
        JSONObject jo = ja.optJSONObject(index);
        if (jo == null) jo = new JSONObject();
        return jo;
    }

    public final static JSONArray optJAFromJA(JSONArray ja, int index) {
        JSONArray returnJSONArray = ja.optJSONArray(index);
        if (returnJSONArray == null) returnJSONArray = new JSONArray();
        return returnJSONArray;
    }

    public static String optString(JSONObject json, String key, String defaultValue) {
        if (json.isNull(key)) return defaultValue;
        else return json.optString(key, defaultValue);
    }

    public static boolean optBoolean(JSONObject jo, String key, boolean defaultValue) {
        if (jo == null || TextUtils.isEmpty(key)) {
            return defaultValue;
        } else {
            return jo.optBoolean(key, defaultValue);
        }
    }

    //http://stackoverflow.com/questions/18226288/json-jsonobject-optstring-returns-string-null
    //https://code.google.com/p/android/issues/detail?id=13830
    public static String optString(JSONObject jo, String key) {
        if (jo == null) return "";
        else if (jo.isNull(key)) return "";
        else return jo.optString(key, "");
    }
}
