package brach.stefan.dae.helper;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class HandlerHelper {
    public static void sendMessage(Handler handler, Object object) {
        Message message = Message.obtain();
        message.obj = object;
        handler.sendMessage(message);
    }

    public static Handler createHandler(android.os.Handler.Callback callback) {
        return new Handler(Looper.getMainLooper(), callback);
    }
}
