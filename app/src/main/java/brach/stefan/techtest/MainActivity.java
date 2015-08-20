package brach.stefan.techtest;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import brach.stefan.techtest.enums.ScreenType;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.screen.LoginScreen;
import brach.stefan.techtest.screen.Screen;

public class MainActivity extends AppCompatActivity {
    // gson is thread safe
    private static final Gson gson = new Gson();
    // shortcut for test version
    public String userName = "admin@test.io";
    public String userPassword = "adminpw";
    // toast object to display Messages
    private Toast mToastText;
    // dialog to indicate waiting time
    private ProgressDialog dialog;
    // reference to the currently active screen
    private Screen screen;
    // queue for threads
    private static final ExecutorService executor = Executors.newFixedThreadPool(1);
    // start url
    private final String startUrl = "http://192.168.179.21:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (TextUtils.isEmpty(PreferencesHelper.getUrl(this))) {
            PreferencesHelper.saveUrl(this, startUrl);
        }
        mToastText = Toast.makeText(this, "", Toast.LENGTH_LONG);
        new LoginScreen(this);
    }

    public void displayToast(final String message) {
        mToastText.setText(message);
        mToastText.show();
    }

    public void createDialog(String message) {
        dialog = new ProgressDialog(this);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void cancelDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public ScreenType getScreenType() {
        if (screen == null) {
            return ScreenType.Undefined;
        } else {
            return screen.getType();
        }
    }

    public static Gson getGson() {
        return gson;
    }
}
