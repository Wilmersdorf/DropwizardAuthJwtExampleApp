package brach.stefan.dae.layout;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import brach.stefan.dae.MainActivity;

public class LayoutHelper {
    public static void setEditTextInputFilter(EditText et, InputFilter filter) {
        if (et == null || filter == null) return;
        et.setFilters(new InputFilter[]{filter});
    }

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

    public static void hideKeyboard(final MainActivity ma) {
        final View view = ma.getCurrentFocus();
        if (view != null) {
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) ma.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }, 0);
        }
    }
}
