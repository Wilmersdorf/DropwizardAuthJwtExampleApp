package brach.stefan.dae.message;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.model.ConnectionDto;

public class ChangeConnectionThreadMo {
    private MainActivity ma;
    private String email;
    private ConnectionDto connectionDto;
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

    public ConnectionDto getConnectionDto() {
        return connectionDto;
    }

    public void setConnectionDto(ConnectionDto connectionDto) {
        this.connectionDto = connectionDto;
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
