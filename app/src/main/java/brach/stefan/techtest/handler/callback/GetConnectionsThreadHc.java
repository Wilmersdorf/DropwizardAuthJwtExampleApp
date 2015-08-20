package brach.stefan.techtest.handler.callback;

import android.os.Message;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.enums.ScreenType;
import brach.stefan.techtest.message.GetConnectionsThreadMo;
import brach.stefan.techtest.screen.UserListScreen;

public class GetConnectionsThreadHc implements android.os.Handler.Callback {
    @Override
    public boolean handleMessage(Message msg) {
        GetConnectionsThreadMo getConnectionsThreadMo = (GetConnectionsThreadMo) msg.obj;
        MainActivity ma = getConnectionsThreadMo.getMa();
        if (ma.getScreenType() == ScreenType.UserListScreen) {
            UserListScreen userListScreen = (UserListScreen) ma.getScreen();
            userListScreen.setConnections(getConnectionsThreadMo.getConnections());
        }
        return false;
    }
}
