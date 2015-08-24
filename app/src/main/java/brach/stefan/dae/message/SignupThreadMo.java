package brach.stefan.dae.message;

import brach.stefan.dae.MainActivity;

public class SignupThreadMo {
    private MainActivity ma;
    private String email = "";
    private String password = "";
    private boolean anErrorOccured = false;
    private String responseMessage = "";
    private int code = 0;

    public MainActivity getMa() {
        return ma;
    }

    public void setMa(MainActivity ma) {
        this.ma = ma;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnErrorOccured() {
        return anErrorOccured;
    }

    public void setAnErrorOccured(boolean anErrorOccured) {
        this.anErrorOccured = anErrorOccured;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
