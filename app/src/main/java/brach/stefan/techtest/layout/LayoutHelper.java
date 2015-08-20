package brach.stefan.techtest.layout;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

import brach.stefan.techtest.MainActivity;

public class LayoutHelper {
    public static String getStringFromEditText(MainActivity ma, int id) {
        EditText editText = (EditText) ma.findViewById(id);
        return getStringFromEditText(editText);
    }

    public static String getStringFromEditText(EditText et) {
        String s = "";
        Editable editable = et.getText();
        if (editable != null) s = editable.toString();
        if (s == null) s = "";
        return s.trim();
    }

    public static void setEditTextText(MainActivity ma, int id, String text) {
        EditText et = (EditText) ma.findViewById(id);
        if (et != null) {
            et.setText(text);
        }
    }

    public static void setTextViewText(MainActivity ma, int id, String text) {
        TextView textView = (TextView) ma.findViewById(id);
        if (textView != null) {
            textView.setText(text);
        }
    }
}
