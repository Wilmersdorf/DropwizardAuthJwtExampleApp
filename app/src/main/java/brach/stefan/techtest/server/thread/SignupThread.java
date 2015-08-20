package brach.stefan.techtest.server.thread;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import org.json.JSONObject;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.helper.JB;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.response.JSONObjectResponse;
import brach.stefan.techtest.server.MediaTypes;

public class SignupThread implements Runnable {
    private String email;
    private String password;
    private MainActivity ma;
    private OkHttpClient client = new OkHttpClient();

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
        if (response.isSuccessful()) {
            ma.displayToast("success");
        } else {
            ma.displayToast("an error occured " + response.getCode());
        }
    }
}
