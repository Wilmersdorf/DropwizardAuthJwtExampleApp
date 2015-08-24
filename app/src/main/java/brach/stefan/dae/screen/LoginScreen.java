package brach.stefan.dae.screen;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.R;
import brach.stefan.dae.enums.InputCase;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.helper.UrlValidator;
import brach.stefan.dae.layout.LayoutHelper;
import brach.stefan.dae.layout.LowercaseInputFilterBuilder;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.server.thread.LoginThread;
import brach.stefan.dae.server.thread.SignupThread;

public class LoginScreen extends Screen {
    private MainActivity ma;

    public LoginScreen(MainActivity ma) {
        this.ma = ma;
        initialize();
    }

    private void initialize() {
        ma.setContentView(R.layout.activity_main_login);
        initEmailEdit();
        initUrlEdit();
        initClickListeners();
    }

    private void login() {
        LayoutHelper.hideKeyboard(ma);
        if (urlIsValid()) {
            EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
            EditText passwordEdit = (EditText) ma.findViewById(R.id.et_password);
            String email = LayoutHelper.getStringFromEditText(emailEdit);
            String password = LayoutHelper.getStringFromEditText(passwordEdit);
            ma.createDialog("Logging in.");
            new Thread(new LoginThread(ma, email, password)).start();
        } else {
            showUrlNotValidMsg();
        }
    }

    private void signup() {
        LayoutHelper.hideKeyboard(ma);
        if (urlIsValid()) {
            EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
            EditText passwordEdit = (EditText) ma.findViewById(R.id.et_password);
            String email = LayoutHelper.getStringFromEditText(emailEdit);
            String password = LayoutHelper.getStringFromEditText(passwordEdit);
            ma.createDialog("Signing up.");
            new Thread(new SignupThread(ma, email, password)).start();
        } else {
            showUrlNotValidMsg();
        }
    }

    private void showUrlNotValidMsg() {
        ma.displayToast(TranslationHelper.getString(ma, "url_not_valid"));
    }

    private boolean urlIsValid() {
        String url = PreferencesHelper.getUrl(ma);
        if (TextUtils.isEmpty(url)) {
            return false;
        } else return UrlValidator.isValidUrl(url);
    }

    private void initEmailEdit() {
        final EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
        emailEdit.setText(PreferencesHelper.getCachedEmail(ma));
        emailEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesHelper.saveCachedEmail(ma, LayoutHelper.getStringFromEditText(emailEdit));
            }
        });
        LayoutHelper.setEditTextInputFilter(emailEdit, LowercaseInputFilterBuilder.create(InputCase.TO_LOWER_CASE));
    }

    private void initUrlEdit() {
        final EditText urlEdit = (EditText) ma.findViewById(R.id.et_url);
        urlEdit.setText(PreferencesHelper.getUrl(ma));
        urlEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                PreferencesHelper.saveUrl(ma, LayoutHelper.getStringFromEditText(urlEdit));
            }
        });
    }

    private void initClickListeners() {
        LinearLayout loginLin = (LinearLayout) ma.findViewById(R.id.ll_log_in);
        loginLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        LinearLayout signupLin = (LinearLayout) ma.findViewById(R.id.ll_sign_up);
        signupLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    @Override
    public ScreenType getType() {
        return ScreenType.LoginScreen;
    }
}
