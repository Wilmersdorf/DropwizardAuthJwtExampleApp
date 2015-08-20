package brach.stefan.techtest.helper;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.screen.LoginScreen;

public class LogoutHelper {
    public static void logout(MainActivity ma) {
        ma.setScreen(new LoginScreen(ma));
        PreferencesHelper.saveEmail(ma, "");
        PreferencesHelper.saveAuthToken(ma, "");
    }
}
