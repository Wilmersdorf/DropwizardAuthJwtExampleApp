package brach.stefan.dae;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.screen.LoginScreen;
import brach.stefan.dae.screen.Screen;

public class MainActivity extends AppCompatActivity {
    //if log is on or off
    private static final boolean log = false;
    // gson is thread safe
    private static final Gson gson = new Gson();
    // toast object to display Messages
    private Toast mToastText;
    // dialog to indicate waiting time
    private ProgressDialog dialog;
    // reference to the currently active screen
    private Screen screen;
    // queue for threads
    private static final ExecutorService executor = Executors.newFixedThreadPool(1);
    // start url
    private final String startUrl = "http://ec2-52-28-166-253.eu-central-1.compute.amazonaws.com:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString("abc", null);
        editor.commit();
        if (TextUtils.isEmpty(PreferencesHelper.getUrl(this))) {
            PreferencesHelper.saveUrl(this, startUrl);
        }
        mToastText = Toast.makeText(this, "", Toast.LENGTH_LONG);
        new LoginScreen(this);
    }

    public void displayToast(final String message) {
        if (!TextUtils.isEmpty(message)) {
            mToastText.setText(message);
            mToastText.show();
        }
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

    public static boolean isLog() {
        return log;
    }
}
