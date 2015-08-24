package brach.stefan.dae.server.thread;

import android.os.Handler;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.handler.callback.SignupThreadHc;
import brach.stefan.dae.helper.ClientHelper;
import brach.stefan.dae.helper.HandlerHelper;
import brach.stefan.dae.helper.JB;
import brach.stefan.dae.helper.Logger;
import brach.stefan.dae.message.SignupThreadMo;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.response.JSONObjectResponse;
import brach.stefan.dae.server.MediaTypes;

public class SignupThread implements Runnable {
    private String email;
    private String password;
    private MainActivity ma;
    private OkHttpClient client = ClientHelper.createClient();
    private Handler handler = HandlerHelper.createHandler(new SignupThreadHc());

    public SignupThread(MainActivity ma, String email, String password) {
        this.email = email;
        this.password = password;
        this.ma = ma;
    }

    @Override
    public void run() {
        signup();
    }

    public void signup() {
        String url = PreferencesHelper.getUrl(ma) + "/signup";
        JSONObject jsonParams = new JSONObject();
        JB.put(jsonParams, "email", email);
        JB.put(jsonParams, "password", password);
        RequestBody body = RequestBody.create(MediaTypes.JSON, jsonParams.toString());
        Request request = new Request.Builder().url(url).post(body).build();
        JSONObjectResponse response = new JSONObjectResponse(client.newCall(request));
        SignupThreadMo signupThreadMo = createSignupThreadMo();
        if (response.isSuccessful()) {
            HandlerHelper.sendMessage(handler, signupThreadMo);
        } else {
            signupThreadMo.setAnErrorOccured(true);
            signupThreadMo.setResponseMessage(response.getResponseMessage());
            signupThreadMo.setCode(response.getCode());
            Logger.log(response.getResponseMessage());
            HandlerHelper.sendMessage(handler, signupThreadMo);
        }
    }

    private SignupThreadMo createSignupThreadMo() {
        SignupThreadMo signupThreadMo = new SignupThreadMo();
        signupThreadMo.setMa(ma);
        signupThreadMo.setEmail(email);
        signupThreadMo.setPassword(password);
        return signupThreadMo;
    }
}
