package brach.stefan.dae.server.thread;

import android.os.Handler;
import android.os.Looper;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Role;
import brach.stefan.dae.handler.callback.LoginThreadHc;
import brach.stefan.dae.helper.ClientHelper;
import brach.stefan.dae.helper.HandlerHelper;
import brach.stefan.dae.helper.JB;
import brach.stefan.dae.message.LoginThreadMo;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.response.JSONObjectResponse;
import brach.stefan.dae.server.MediaTypes;

public class LoginThread implements Runnable {
    private String email;
    private String password;
    private MainActivity ma;
    private OkHttpClient client = ClientHelper.createClient();
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
        LoginThreadMo loginThreadMo = new LoginThreadMo();
        loginThreadMo.setMa(ma);
        if (response.isSuccessful()) {
            String authToken = JB.optString(response.getResult(), "authToken");
            String roleStr = JB.optString(response.getResult(), "role", Role.UNKNOWN.toString());
            Role role = Role.parse(roleStr);
            loginThreadMo.setAuthToken(authToken);
            loginThreadMo.setEmail(email);
            loginThreadMo.setRole(role);
            HandlerHelper.sendMessage(handler, loginThreadMo);
        } else {
            loginThreadMo.setAnErrorOccured(true);
            loginThreadMo.setResponseMessage(response.getResponseMessage());
            loginThreadMo.setCode(response.getCode());
            HandlerHelper.sendMessage(handler, loginThreadMo);
        }
    }
}
