package brach.stefan.dae.server.thread;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.ArrayList;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.handler.callback.GetConnectionsThreadHc;
import brach.stefan.dae.helper.ClientHelper;
import brach.stefan.dae.helper.HandlerHelper;
import brach.stefan.dae.helper.IsAllowedHelper;
import brach.stefan.dae.helper.Logger;
import brach.stefan.dae.message.GetConnectionsThreadMo;
import brach.stefan.dae.model.ConnectionDto;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.response.JSONArrayResponse;
import brach.stefan.dae.server.json.from.ConnectionsFromJson;

public class GetConnectionsThread implements Runnable {
    private MainActivity ma;
    private String authToken;
    private String email;
    private OkHttpClient client = ClientHelper.createClient();
    private Handler handler = new Handler(Looper.getMainLooper(), new GetConnectionsThreadHc());
    private boolean showMessage = false;

    public GetConnectionsThread(MainActivity ma, String authToken, String email, boolean showMessage) {
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
            getConnections();
        }
    }

    private void getConnections() {
        String url = PreferencesHelper.getUrl(ma) + "/user";
        Request request = new Request.Builder().url(url).addHeader("authToken", authToken).build();
        JSONArrayResponse response = new JSONArrayResponse(client.newCall(request));
        //
        GetConnectionsThreadMo getConnectionsThreadMo = new GetConnectionsThreadMo();
        getConnectionsThreadMo.setMa(ma);
        getConnectionsThreadMo.setEmail(email);
        getConnectionsThreadMo.setShowMessage(showMessage);
        //
        if (response.isSuccessful()) {
            Logger.log("success getConnections", response.getResult());
            ArrayList<ConnectionDto> connections = ConnectionsFromJson.parse(response.getResult());
            getConnectionsThreadMo.setConnections(connections);
            HandlerHelper.sendMessage(handler, getConnectionsThreadMo);
        } else {
            Logger.log("failure getConnections" + response.getCode());
            getConnectionsThreadMo.setAnErrorOccured(true);
            getConnectionsThreadMo.setCode(response.getCode());
            HandlerHelper.sendMessage(handler, getConnectionsThreadMo);
        }
    }
}
