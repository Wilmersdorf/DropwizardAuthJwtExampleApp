package brach.stefan.techtest.message;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.model.AdminConnectionDto;

public class GetAdminConnectionsThreadMo {
    private MainActivity ma;
    private ArrayList<AdminConnectionDto> connections;

    public MainActivity getMa() {
        return ma;
    }

    public void setMa(MainActivity ma) {
        this.ma = ma;
    }

    public ArrayList<AdminConnectionDto> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<AdminConnectionDto> connections) {
        this.connections = connections;
    }
}
