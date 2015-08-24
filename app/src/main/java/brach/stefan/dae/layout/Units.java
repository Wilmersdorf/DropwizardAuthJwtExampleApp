package brach.stefan.dae.layout;

import android.content.Context;
import android.util.TypedValue;

public class Units {
    public static final int DpToPx(float dp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
