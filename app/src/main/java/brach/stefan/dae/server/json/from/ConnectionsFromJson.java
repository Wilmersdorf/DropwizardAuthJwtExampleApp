package brach.stefan.dae.server.json.from;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import brach.stefan.dae.helper.JB;
import brach.stefan.dae.model.ConnectionDto;

public class ConnectionsFromJson {
    public static ArrayList<ConnectionDto> parse(JSONArray connectionsJa) {
        ArrayList<ConnectionDto> connections = new ArrayList<>();
        if (connectionsJa != null) {
            for (int i = 0; i < connectionsJa.length(); i++) {
                JSONObject connectionJo = JB.optJOFromJA(connectionsJa, i);
                ConnectionDto connectionDto = new ConnectionDto();
                String email = JB.optString(connectionJo, "email");
                boolean connected = connectionJo.optBoolean("connected");
                connectionDto.setEmail(email);
                connectionDto.setConnected(connected);
                connections.add(connectionDto);
            }
        }
        return connections;
    }
}
