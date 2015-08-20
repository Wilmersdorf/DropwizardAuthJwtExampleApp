package brach.stefan.techtest.handler.callback;

import android.os.Message;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.enums.ScreenType;
import brach.stefan.techtest.message.GetAdminConnectionsThreadMo;
import brach.stefan.techtest.screen.AdminListScreen;

public class GetAdminConnectionsThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        GetAdminConnectionsThreadMo getAdminConnectionsThreadMo = (GetAdminConnectionsThreadMo) msg.obj;
        MainActivity ma = getAdminConnectionsThreadMo.getMa();
        if (ma.getScreenType() == ScreenType.AdminListScreen) {
            AdminListScreen adminListScreen = (AdminListScreen) ma.getScreen();
            adminListScreen.setConnections(getAdminConnectionsThreadMo.getConnections());
        }
        return false;
    }
}
