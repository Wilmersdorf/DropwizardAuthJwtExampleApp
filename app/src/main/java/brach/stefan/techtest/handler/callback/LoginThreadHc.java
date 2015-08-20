package brach.stefan.techtest.handler.callback;

import android.os.Message;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.enums.Role;
import brach.stefan.techtest.helper.Logger;
import brach.stefan.techtest.message.LoginThreadMo;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.screen.AdminListScreen;
import brach.stefan.techtest.screen.UserListScreen;

public class LoginThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        LoginThreadMo loginThreadMo = (LoginThreadMo) msg.obj;
        MainActivity ma = loginThreadMo.getMa();
        ma.cancelDialog();
        if (loginThreadMo.isAnErrorOccured() == true) {
            ma.displayToast("Unable to login.");
        } else {
            if (Role.ADMIN == loginThreadMo.getRole()) {
                saveToPref(ma, loginThreadMo);
                ma.setScreen(new AdminListScreen(loginThreadMo.getMa()));
            } else if (Role.NORMAL == loginThreadMo.getRole()) {
                saveToPref(ma, loginThreadMo);
                ma.setScreen(new UserListScreen(loginThreadMo.getMa()));
            }
        }
        return false;
    }

    private void saveToPref(MainActivity ma, LoginThreadMo loginThreadMo) {
        PreferencesHelper.saveAuthToken(ma, loginThreadMo.getAuthToken());
        PreferencesHelper.saveEmail(ma, loginThreadMo.getEmail());
        PreferencesHelper.saveRole(ma, loginThreadMo.getRole());
        Logger.log(loginThreadMo.getEmail());
    }
}
