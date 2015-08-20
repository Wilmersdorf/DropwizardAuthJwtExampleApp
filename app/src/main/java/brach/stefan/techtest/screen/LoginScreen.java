package brach.stefan.techtest.screen;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.R;
import brach.stefan.techtest.enums.ScreenType;
import brach.stefan.techtest.layout.LayoutHelper;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.server.thread.LoginThread;
import brach.stefan.techtest.server.thread.SignupThread;

public class LoginScreen extends Screen {
    private MainActivity ma;

    public LoginScreen(MainActivity ma) {
        this.ma = ma;
        initialize();
    }

    private void initialize() {
        ma.setContentView(R.layout.activity_main_login);
        EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
        EditText passwordEdit = (EditText) ma.findViewById(R.id.et_password);
        emailEdit.setText(ma.userName);
        passwordEdit.setText(ma.userPassword);
        final EditText urlEdit = (EditText) ma.findViewById(R.id.et_url);
        urlEdit.setText(PreferencesHelper.getUrl(ma));
        urlEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                PreferencesHelper.saveUrl(ma, LayoutHelper.getStringFromEditText(urlEdit));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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

    private void login() {
        EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
        EditText passwordEdit = (EditText) ma.findViewById(R.id.et_password);
        String email = LayoutHelper.getStringFromEditText(emailEdit);
        String password = LayoutHelper.getStringFromEditText(passwordEdit);
        ma.createDialog("Logging in.");
        new Thread(new LoginThread(ma, email, password)).start();
    }

    private void signup() {
        EditText emailEdit = (EditText) ma.findViewById(R.id.et_email);
        EditText passwordEdit = (EditText) ma.findViewById(R.id.et_password);
        String email = LayoutHelper.getStringFromEditText(emailEdit);
        String password = LayoutHelper.getStringFromEditText(passwordEdit);
        new Thread(new SignupThread(ma, email, password)).start();
    }

    @Override
    public ScreenType getType() {
        return ScreenType.LoginScreen;
    }
}
