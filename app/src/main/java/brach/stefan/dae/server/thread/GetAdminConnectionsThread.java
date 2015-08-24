package brach.stefan.dae.server.thread;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.handler.callback.GetAdminConnectionsThreadHc;
import brach.stefan.dae.helper.ClientHelper;
import brach.stefan.dae.helper.HandlerHelper;
import brach.stefan.dae.helper.IsAllowedHelper;
import brach.stefan.dae.helper.Logger;
import brach.stefan.dae.message.GetAdminConnectionsThreadMo;
import brach.stefan.dae.model.AdminConnectionDto;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.response.JSONArrayResponse;
import brach.stefan.dae.server.json.from.AdminConnectionsFromJson;

public class GetAdminConnectionsThread implements Runnable {
    private MainActivity ma;
    private String authToken;
    private String email;
    private OkHttpClient client = ClientHelper.createClient();
    private Handler handler = new Handler(Looper.getMainLooper(), new GetAdminConnectionsThreadHc());
    private boolean showMessage = false;

    public GetAdminConnectionsThread(MainActivity ma, String authToken, String email, boolean showMessage) {
        this.ma = ma;
        this.authToken = authToken;
        this.email = email;
        this.showMessage = showMessage;
    }

    @Override
    public void run() {
        if (IsAllowedHelper.isAllowed(ma, email) == false) {
            return;
        } else {
            getAdminConnections();
        }
    }

    private void getAdminConnections() {
        String url = PreferencesHelper.getUrl(ma) + "/admin/user";
        Request request = new Request.Builder().url(url).addHeader("authToken", authToken).build();
        JSONArrayResponse response = new JSONArrayResponse(client.newCall(request));
        //
        GetAdminConnectionsThreadMo getAdminConnectionsThreadMo = new GetAdminConnectionsThreadMo();
        getAdminConnectionsThreadMo.setMa(ma);
        getAdminConnectionsThreadMo.setEmail(email);
        getAdminConnectionsThreadMo.setShowMessage(showMessage);
        //
        if (response.isSuccessful()) {
            Logger.log("success getAdminConnections", response.getResult());
            ArrayList<AdminConnectionDto> connections = AdminConnectionsFromJson.parse(response.getResult());
            Logger.log(MainActivity.getGson().toJson(connections));
            getAdminConnectionsThreadMo.setConnections(connections);
            HandlerHelper.sendMessage(handler, getAdminConnectionsThreadMo);
        } else {
            Logger.log("failure getAdminConnections" + response.getCode());
            getAdminConnectionsThreadMo.setAnErrorOccured(true);
            getAdminConnectionsThreadMo.setCode(response.getCode());
            HandlerHelper.sendMessage(handler, getAdminConnectionsThreadMo);
        }
    }
}
