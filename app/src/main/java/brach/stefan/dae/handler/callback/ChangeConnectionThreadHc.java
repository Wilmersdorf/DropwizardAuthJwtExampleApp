package brach.stefan.dae.handler.callback;

import android.os.Handler;
import android.os.Message;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Code;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.IsAllowedHelper;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.message.ChangeConnectionThreadMo;
import brach.stefan.dae.model.ConnectionDto;

public class ChangeConnectionThreadHc implements Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        ChangeConnectionThreadMo changeConnectionThreadMo = (ChangeConnectionThreadMo) msg.obj;
        MainActivity ma = changeConnectionThreadMo.getMa();
        String email = changeConnectionThreadMo.getEmail();
        if (IsAllowedHelper.isAllowed(ma, email) == false) {
            return false;
        } else if (ma.getScreenType() != ScreenType.UserListScreen) {
            return false;
        } else {
            ConnectionDto connectionDto = changeConnectionThreadMo.getConnectionDto();
            String otherEmail = connectionDto.getEmail();
            if (changeConnectionThreadMo.isAnErrorOccured() == true) {
                String translation = TranslationHelper.getString(ma, "unable_to_connect_to");
                String toShow = translation + " " + otherEmail + ".";
                if (changeConnectionThreadMo.getCode() == Code.IOEXCEPTION.getValue()) {
                    toShow = toShow + " " + TranslationHelper.getString(ma, "connection_error");
                    ma.displayToast(toShow);
                }
            } else {
                boolean isConnected = connectionDto.isConnected();
                if (connectionDto.isConnected() == true) {
                    String translation = TranslationHelper.getString(ma, "you_are_now_connected_to");
                    ma.displayToast(translation + " " + otherEmail + ".");
                }
                if (connectionDto.isConnected() == false) {
                    String translation = TranslationHelper.getString(ma, "you_are_no_longer_connected_to");
                    ma.displayToast(translation + " " + otherEmail + ".");
                }
            }
        }
        return false;
    }
}
