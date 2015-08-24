package brach.stefan.dae.layout.font;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CustomCheckBox extends android.widget.CheckBox {
    public CustomCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        SetTypeFace(context, attrs, defStyle);
    }

    public CustomCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        SetTypeFace(context, attrs, 0);
    }

    public CustomCheckBox(Context context) {
        super(context);
        SetTypeFace(context, null, 0);
    }

    public void SetTypeFace(Context context, AttributeSet attrs, int defStyle) {
        String typeface = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "typeface");
        String textsize = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "textsize");
        setPaintFlags(this.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG | Paint.LINEAR_TEXT_FLAG);
        setTypeface(Constants.getTypeface(context, typeface), defStyle);
        setTextSize(Constants.getTextSize(context, textsize));
    }
}
