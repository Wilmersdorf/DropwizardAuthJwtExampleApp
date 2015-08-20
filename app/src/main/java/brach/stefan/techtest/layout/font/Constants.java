package brach.stefan.techtest.layout.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.LruCache;

public class Constants {
    public final static String LATO = "lato_regular.ttf";
    public final static String LATO_BOLD = "lato_bold.ttf";
    public final static String FONT = LATO;
    public final static String FONT_BOLD = LATO_BOLD;
    public final static float ATTRIBUTE_FONT_SIZE_HUGE_VALUE = 14 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_ALMOST_HUGE_VALUE = 13 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_BIG_VALUE = 12 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_INCREASED_VALUE = 11 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_NORMAL_VALUE = 10 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_MEDIUM_VALUE = 9 * 1.4f;
    public final static float ATTRIBUTE_FONT_SIZE_SMALL_VALUE = 8 * 1.4f;
    private static LruCache<String, Typeface> typefaceCache;
    //
    private final static float[] textsize;

    static {
        textsize = new float[8];
        textsize[1] = ATTRIBUTE_FONT_SIZE_SMALL_VALUE;
        textsize[2] = ATTRIBUTE_FONT_SIZE_MEDIUM_VALUE;
        textsize[3] = ATTRIBUTE_FONT_SIZE_NORMAL_VALUE;
        textsize[4] = ATTRIBUTE_FONT_SIZE_INCREASED_VALUE;
        textsize[5] = ATTRIBUTE_FONT_SIZE_BIG_VALUE;
        textsize[6] = ATTRIBUTE_FONT_SIZE_ALMOST_HUGE_VALUE;
        textsize[7] = ATTRIBUTE_FONT_SIZE_HUGE_VALUE;
    }

    public static synchronized Typeface getTypeface(Context context, String font) {
        if (typefaceCache == null) {
            typefaceCache = new LruCache<String, Typeface>(2);
            Typeface normal = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", FONT));
            Typeface bold = Typeface.createFromAsset(context.getAssets(), String.format("fonts/%s", FONT_BOLD));
            typefaceCache.put("1", normal);
            typefaceCache.put("2", bold);
        }
        if (font == null) font = "1";
        return typefaceCache.get(font);
    }

    public static float getTextSize(Context context, String sizeStr) {
        if (sizeStr == null) sizeStr = "1";
        int size = Integer.parseInt(sizeStr);
        float density = context.getResources().getDisplayMetrics().density;
        float returnValue = textsize[size];
        return returnValue;
    }
}
