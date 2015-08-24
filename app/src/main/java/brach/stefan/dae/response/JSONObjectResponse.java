package brach.stefan.dae.response;

import android.text.TextUtils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import brach.stefan.dae.enums.Code;

public class JSONObjectResponse {
    private int code;
    private JSONObject resultJO = null;
    private String responseMessage = "";

    public JSONObjectResponse() {
    }

    public JSONObjectResponse(Call call) {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            setCode(Code.IOEXCEPTION.getValue());
            return;
        }
        setCode(response.code());
        setResponseMessage(response.message());
        if (getResponseMessage() == null) setResponseMessage("");
        try {
            String body = response.body().string();
            if (!TextUtils.isEmpty(body) && !body.startsWith("[") && !body.startsWith("{")) {
                responseMessage = body;
            } else {
                setResult(new JSONObject(body));
            }
        } catch (IOException e) {
        } catch (JSONException e) {
        }
    }

    public boolean isSuccessful() {
        return code >= 200 && code < 300;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public JSONObject getResult() {
        return resultJO;
    }

    public void setResult(JSONObject result) {
        this.resultJO = result;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
