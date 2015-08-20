package brach.stefan.techtest.server.thread;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.enums.Role;
import brach.stefan.techtest.handler.callback.LoginThreadHc;
import brach.stefan.techtest.helper.HandlerHelper;
import brach.stefan.techtest.helper.JB;
import brach.stefan.techtest.helper.Logger;
import brach.stefan.techtest.message.LoginThreadMo;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.response.JSONObjectResponse;
import brach.stefan.techtest.server.MediaTypes;

public class LoginThread implements Runnable {
    private String email;
    private String password;
    private MainActivity ma;
    private OkHttpClient client = new OkHttpClient();
    private Handler handler = new Handler(Looper.getMainLooper(), new LoginThreadHc());

    public LoginThread(MainActivity ma, String email, String password) {
        this.email = email;
        this.password = password;
        this.ma = ma;
    }

    @Override
    public void run() {
        login();
    }

    public void login() {
        String url = PreferencesHelper.getUrl(ma) + "/login";
        JSONObject jsonParams = new JSONObject();
        JB.put(jsonParams, "email", email);
        JB.put(jsonParams, "password", password);
        RequestBody body = RequestBody.create(MediaTypes.JSON, jsonParams.toString());
        Request request = new Request.Builder().url(url).post(body).build();
        JSONObjectResponse response = new JSONObjectResponse(client.newCall(request));
        if (response.isSuccessful()) {
            Logger.log("success login");
            String authToken = JB.optString(response.getResult(), "authToken");
            String roleStr = JB.optString(response.getResult(), "role", Role.UNKNOWN.toString());
            Role role = Role.parse(roleStr);
            LoginThreadMo loginThreadMo = new LoginThreadMo();
            loginThreadMo.setMa(ma);
            loginThreadMo.setAuthToken(authToken);
            loginThreadMo.setEmail(email);
            loginThreadMo.setRole(role);
            HandlerHelper.sendMessage(handler, loginThreadMo);
        } else {
            Logger.log("error login " + response.getCode());
            LoginThreadMo loginThreadMo = new LoginThreadMo();
            loginThreadMo.setMa(ma);
            loginThreadMo.setAnErrorOccured(true);
            HandlerHelper.sendMessage(handler, loginThreadMo);
        }
    }
}
