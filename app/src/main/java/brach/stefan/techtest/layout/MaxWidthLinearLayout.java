package brach.stefan.techtest.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class MaxWidthLinearLayout extends LinearLayout {
    private int mMaxWidth = 0;

    public MaxWidthLinearLayout(Context context) {
        super(context);
    }

    public MaxWidthLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaxWidthLinearLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        mMaxWidth = attrs.getAttributeIntValue("http://schemas.android.com/apk/res-auto", "maxWidth", 0);
        if (mMaxWidth != 0) {
            mMaxWidth = Units.DpToPx(mMaxWidth, context);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (mMaxWidth > 0 && mMaxWidth < measuredWidth) {
            int measureMode = MeasureSpec.getMode(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(mMaxWidth, measureMode);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
