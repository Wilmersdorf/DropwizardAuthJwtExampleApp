package brach.stefan.dae.handler.callback;

import android.os.Message;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Code;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.IsAllowedHelper;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.message.GetConnectionsThreadMo;
import brach.stefan.dae.screen.UserListScreen;

public class GetConnectionsThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        GetConnectionsThreadMo getConnectionsThreadMo = (GetConnectionsThreadMo) msg.obj;
        MainActivity ma = getConnectionsThreadMo.getMa();
        String email = getConnectionsThreadMo.getEmail();
        if (IsAllowedHelper.isAllowed(ma, email) == false) {
            return false;
        } else if (ma.getScreenType() == ScreenType.UserListScreen) {
            if (getConnectionsThreadMo.isAnErrorOccured() == true) {
                String toShow = TranslationHelper.getString(ma, "unable_to_refresh_connections");
                if (getConnectionsThreadMo.getCode() == Code.IOEXCEPTION.getValue()) {
                    toShow = toShow + " " + TranslationHelper.getString(ma, "connection_error");
                }
                ma.displayToast(toShow);
            } else {
                UserListScreen userListScreen = (UserListScreen) ma.getScreen();
                userListScreen.setConnections(getConnectionsThreadMo.getConnections());
                if (getConnectionsThreadMo.isShowMessage() == true) {
                    String toShow = TranslationHelper.getString(ma, "refreshed_connections");
                    ma.displayToast(toShow);
                }
            }
        }
        return false;
    }
}
