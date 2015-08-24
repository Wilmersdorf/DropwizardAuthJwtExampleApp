package brach.stefan.dae.helper;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.screen.LoginScreen;

public class LogoutHelper {
    public static void logout(MainActivity ma) {
        ma.setScreen(new LoginScreen(ma));
        PreferencesHelper.saveEmail(ma, "");
        PreferencesHelper.saveAuthToken(ma, "");
    }
}
