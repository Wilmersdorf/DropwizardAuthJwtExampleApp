package brach.stefan.dae.response;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import brach.stefan.dae.enums.Code;

public class JSONArrayResponse {
    private int code;
    private JSONArray resultJA = null;
    private String responseMessage = "";

    public JSONArrayResponse(Call call) {
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
            setResult(new JSONArray(body));
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

    public JSONArray getResult() {
        return resultJA;
    }

    public void setResult(JSONArray result) {
        this.resultJA = result;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
