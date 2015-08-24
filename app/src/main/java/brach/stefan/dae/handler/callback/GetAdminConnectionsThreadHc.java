package brach.stefan.dae.handler.callback;

import android.os.Message;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Code;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.IsAllowedHelper;
import brach.stefan.dae.helper.TranslationHelper;
import brach.stefan.dae.message.GetAdminConnectionsThreadMo;
import brach.stefan.dae.screen.AdminListScreen;

public class GetAdminConnectionsThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        GetAdminConnectionsThreadMo getAdminConnectionsThreadMo = (GetAdminConnectionsThreadMo) msg.obj;
        MainActivity ma = getAdminConnectionsThreadMo.getMa();
        String email = getAdminConnectionsThreadMo.getEmail();
        if (IsAllowedHelper.isAllowed(ma, email) == false) {
            return false;
        } else if (ma.getScreenType() == ScreenType.AdminListScreen) {
            if (getAdminConnectionsThreadMo.isAnErrorOccured() == true) {
                String toShow = TranslationHelper.getString(ma, "unable_to_refresh_connections");
                if (getAdminConnectionsThreadMo.getCode() == Code.IOEXCEPTION.getValue()) {
                    toShow = toShow + " " + TranslationHelper.getString(ma, "connection_error");
                }
                ma.displayToast(toShow);
            } else {
                AdminListScreen adminListScreen = (AdminListScreen) ma.getScreen();
                adminListScreen.setConnections(getAdminConnectionsThreadMo.getConnections());
                if (getAdminConnectionsThreadMo.isShowMessage() == true) {
                    String toShow = TranslationHelper.getString(ma, "refreshed_connections");
                    ma.displayToast(toShow);
                }
            }
        }
        return false;
    }
}
