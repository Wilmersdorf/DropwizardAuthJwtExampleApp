package brach.stefan.dae.message;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.enums.Role;

public class LoginThreadMo {
    private MainActivity ma;
    private String authToken = "";
    private String email = "";
    private Role role;
    private boolean anErrorOccured = false;
    private String responseMessage = "";
    private int code = 0;

    public MainActivity getMa() {
        return ma;
    }

    public void setMa(MainActivity ma) {
        this.ma = ma;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public boolean isAnErrorOccured() {
        return anErrorOccured;
    }

    public void setAnErrorOccured(boolean anErrorOccured) {
        this.anErrorOccured = anErrorOccured;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
