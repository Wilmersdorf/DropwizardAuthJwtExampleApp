package brach.stefan.dae.layout;

import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;

import brach.stefan.dae.enums.InputCase;

public class LowercaseInputFilterBuilder {
    public static InputFilter create(final InputCase inputCase) {
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (inputCase == InputCase.TO_LOWER_CASE) {
                        sb.append(Character.toLowerCase(c));
                    } else if (inputCase == InputCase.TO_UPPER_CASE) {
                        sb.append(Character.toUpperCase(c));
                    }
                }
                if (source instanceof Spanned) {
                    SpannableString sp = new SpannableString(sb);
                    TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                    return sp;
                }
                return sb;
            }
        };
        return filter;
    }
}
