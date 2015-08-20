package brach.stefan.techtest.helper;

import android.os.Handler;
import android.os.Message;

public class HandlerHelper {
    public static void sendMessage(Handler handler, Object object) {
        Message message = Message.obtain();
        message.obj = object;
        handler.sendMessage(message);
    }
}
