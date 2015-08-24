package brach.stefan.dae.handler.callback;

import android.os.Message;
import android.text.TextUtils;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Role;
import brach.stefan.dae.helper.ErrorHelper;
import brach.stefan.dae.helper.Logger;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.message.LoginThreadMo;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.screen.AdminListScreen;
import brach.stefan.dae.screen.UserListScreen;

public class LoginThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        LoginThreadMo loginThreadMo = (LoginThreadMo) msg.obj;
        MainActivity ma = loginThreadMo.getMa();
        ma.cancelDialog();
        if (loginThreadMo.isAnErrorOccured() == true) {
            String responseMsg = loginThreadMo.getResponseMessage();
            String toShow = ErrorHelper.getTranslatedErrMsg(ma, responseMsg);
            //connection error
            if (TextUtils.isEmpty(toShow) && loginThreadMo.getCode() == -1) {
                toShow = TranslationHelper.getString(ma, "connection_error");
            }
            //other error
            if (TextUtils.isEmpty(toShow)) {
                toShow = TranslationHelper.getString(ma, "unable_to_login");
            }
            //show message
            ma.displayToast(toShow);
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
