package brach.stefan.techtest.server.thread;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.helper.JB;
import brach.stefan.techtest.helper.Logger;
import brach.stefan.techtest.model.ConnectionDto;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.response.JSONObjectResponse;
import brach.stefan.techtest.server.MediaTypes;

public class ChangeConnectionThread implements Runnable {
    private MainActivity ma;
    private String email;
    private String authToken;
    private ConnectionDto connectionDto;
    private OkHttpClient client = new OkHttpClient();

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
        if (response.isSuccessful()) {
            Logger.log("changeConnection success");
        } else {
            Logger.log("changeConnection error " + response.getCode());
        }
    }
}
