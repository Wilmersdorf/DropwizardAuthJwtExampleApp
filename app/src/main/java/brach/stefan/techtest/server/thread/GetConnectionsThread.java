package brach.stefan.techtest.server.thread;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.handler.callback.GetConnectionsThreadHc;
import brach.stefan.techtest.helper.HandlerHelper;
import brach.stefan.techtest.helper.Logger;
import brach.stefan.techtest.message.GetConnectionsThreadMo;
import brach.stefan.techtest.model.ConnectionDto;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.response.JSONArrayResponse;
import brach.stefan.techtest.server.json.from.ConnectionsFromJson;

public class GetConnectionsThread implements Runnable {
    private MainActivity ma;
    private String authToken;
    private String email;
    private OkHttpClient client = new OkHttpClient();
    private Handler handler = new Handler(Looper.getMainLooper(), new GetConnectionsThreadHc());

    public GetConnectionsThread(MainActivity ma, String authToken, String email) {
        this.ma = ma;
        this.authToken = authToken;
        this.email = email;
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(email)) {
            Logger.warn("GetConnectionsThread email is empty.");
        } else {
            getConnections();
        }
    }

    private void getConnections() {
        String url = PreferencesHelper.getUrl(ma) + "/user";
        Request request = new Request.Builder().url(url).addHeader("authToken", authToken).build();
        JSONArrayResponse response = new JSONArrayResponse(client.newCall(request));
        if (response.isSuccessful()) {
            Logger.log("success getConnections", response.getResult());
            ArrayList<ConnectionDto> connections = ConnectionsFromJson.parse(response.getResult());
            GetConnectionsThreadMo getConnectionsThreadMo = new GetConnectionsThreadMo();
            getConnectionsThreadMo.setMa(ma);
            getConnectionsThreadMo.setConnections(connections);
            HandlerHelper.sendMessage(handler, getConnectionsThreadMo);
        } else {
            Logger.log("failure " + response.getCode());
        }
    }
}
