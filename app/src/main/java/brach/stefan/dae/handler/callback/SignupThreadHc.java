package brach.stefan.dae.handler.callback;

import android.os.Message;
import android.text.TextUtils;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.helper.ErrorHelper;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.message.SignupThreadMo;
import brach.stefan.dae.server.thread.LoginThread;

public class SignupThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        SignupThreadMo signupThreadMo = (SignupThreadMo) msg.obj;
        MainActivity ma = signupThreadMo.getMa();
        if (signupThreadMo.isAnErrorOccured()) {
            ma.cancelDialog();
            String responseMsg = signupThreadMo.getResponseMessage();
            String toShow = ErrorHelper.getTranslatedErrMsg(ma, responseMsg);
            //password not valid
            if (TextUtils.isEmpty(toShow) && !TextUtils.isEmpty(responseMsg)) {
                if (responseMsg.startsWith("Password")) {
                    toShow = responseMsg;
                }
            }
            //connection error
            if (TextUtils.isEmpty(toShow) && signupThreadMo.getCode() == -1) {
                toShow = TranslationHelper.getString(ma, "connection_error");
            }
            //other error
            if (TextUtils.isEmpty(toShow)) {
                toShow = TranslationHelper.getString(ma, "unable_to_signup");
            }
            //show message
            ma.displayToast(toShow);
        } else {
            String email = signupThreadMo.getEmail();
            String password = signupThreadMo.getPassword();
            new Thread(new LoginThread(ma, email, password)).start();
        }
        return false;
    }
}
