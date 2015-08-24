package brach.stefan.dae.server.thread;

import android.os.Handler;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.handler.callback.ChangeConnectionThreadHc;
import brach.stefan.dae.helper.ClientHelper;
import brach.stefan.dae.helper.HandlerHelper;
import brach.stefan.dae.helper.JB;
import brach.stefan.dae.message.ChangeConnectionThreadMo;
import brach.stefan.dae.model.ConnectionDto;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.response.JSONObjectResponse;
import brach.stefan.dae.server.MediaTypes;

public class ChangeConnectionThread implements Runnable {
    private MainActivity ma;
    private String email;
    private String authToken;
    private ConnectionDto connectionDto;
    private OkHttpClient client = ClientHelper.createClient();
    private Handler handler = HandlerHelper.createHandler(new ChangeConnectionThreadHc());

    public ChangeConnectionThread(MainActivity ma, String email, String authToken, ConnectionDto connectionDto) {
        this.ma = ma;
        this.email = email;
        this.authToken = authToken;
        this.connectionDto = connectionDto;
    }

    @Override
    public void run() {
        changeConnection();
    }

    private void changeConnection() {
        String url = PreferencesHelper.getUrl(ma) + "/user/connection";
        String bodyStr = JB.ObjectToString(connectionDto);
        RequestBody body = RequestBody.create(MediaTypes.JSON, bodyStr);
        Request request = new Request.Builder().url(url).post(body).header("authToken", authToken).build();
        JSONObjectResponse response = new JSONObjectResponse(client.newCall(request));
        ChangeConnectionThreadMo changeConnectionThreadMo = new ChangeConnectionThreadMo();
        changeConnectionThreadMo.setMa(ma);
        changeConnectionThreadMo.setEmail(email);
        changeConnectionThreadMo.setConnectionDto(connectionDto);
        if (response.isSuccessful()) {
            HandlerHelper.sendMessage(handler, changeConnectionThreadMo);
        } else {
            changeConnectionThreadMo.setAnErrorOccured(true);
            changeConnectionThreadMo.setResponseMessage(response.getResponseMessage());
            changeConnectionThreadMo.setCode(response.getCode());
            HandlerHelper.sendMessage(handler, changeConnectionThreadMo);
        }
    }
}
