package brach.stefan.techtest.server.thread;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.handler.callback.GetAdminConnectionsThreadHc;
import brach.stefan.techtest.helper.HandlerHelper;
import brach.stefan.techtest.helper.Logger;
import brach.stefan.techtest.message.GetAdminConnectionsThreadMo;
import brach.stefan.techtest.model.AdminConnectionDto;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.response.JSONArrayResponse;
import brach.stefan.techtest.server.json.from.AdminConnectionsFromJson;

public class GetAdminConnectionsThread implements Runnable {
    private MainActivity ma;
    private String authToken;
    private String email;
    private OkHttpClient client = new OkHttpClient();
    private Handler handler = new Handler(Looper.getMainLooper(), new GetAdminConnectionsThreadHc());

    public GetAdminConnectionsThread(MainActivity ma, String authToken, String email) {
        this.ma = ma;
        this.authToken = authToken;
        this.email = email;
    }

    @Override
    public void run() {
        if (TextUtils.isEmpty(email)) {
            Logger.warn("GetConnectionsThread email is empty.");
        } else {
            getAdminConnections();
        }
    }

    private void getAdminConnections() {
        String url = PreferencesHelper.getUrl(ma) + "/admin/user";
        Request request = new Request.Builder().url(url).addHeader("authToken", authToken).build();
        JSONArrayResponse response = new JSONArrayResponse(client.newCall(request));
        if (response.isSuccessful()) {
            Logger.log("success getAdminConnections", response.getResult());
            ArrayList<AdminConnectionDto> connections = AdminConnectionsFromJson.parse(response.getResult());
            Logger.log(MainActivity.getGson().toJson(connections));
            GetAdminConnectionsThreadMo getAdminConnectionsThreadMo = new GetAdminConnectionsThreadMo();
            getAdminConnectionsThreadMo.setMa(ma);
            getAdminConnectionsThreadMo.setConnections(connections);
            HandlerHelper.sendMessage(handler, getAdminConnectionsThreadMo);
        } else {
            Logger.log("failure " + response.getCode());
        }
    }
}
