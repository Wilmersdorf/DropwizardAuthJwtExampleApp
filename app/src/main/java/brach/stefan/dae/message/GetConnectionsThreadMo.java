package brach.stefan.dae.message;

import java.util.ArrayList;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.model.ConnectionDto;

public class GetConnectionsThreadMo {
    private MainActivity ma;
    private String email = "";
    private boolean showMessage = true;
    private ArrayList<ConnectionDto> connections;
    private boolean anErrorOccured = false;
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

    public boolean isShowMessage() {
        return showMessage;
    }

    public void setShowMessage(boolean showMessage) {
        this.showMessage = showMessage;
    }

    public ArrayList<ConnectionDto> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<ConnectionDto> connections) {
        this.connections = connections;
    }

    public boolean isAnErrorOccured() {
        return anErrorOccured;
    }

    public void setAnErrorOccured(boolean anErrorOccured) {
        this.anErrorOccured = anErrorOccured;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
