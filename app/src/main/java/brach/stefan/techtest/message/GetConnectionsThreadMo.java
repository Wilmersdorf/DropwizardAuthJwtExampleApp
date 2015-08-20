package brach.stefan.techtest.message;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.model.ConnectionDto;

public class GetConnectionsThreadMo {
    private MainActivity ma;
    private ArrayList<ConnectionDto> connections;

    public MainActivity getMa() {
        return ma;
    }

    public void setMa(MainActivity ma) {
        this.ma = ma;
    }

    public ArrayList<ConnectionDto> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<ConnectionDto> connections) {
        this.connections = connections;
    }
}
