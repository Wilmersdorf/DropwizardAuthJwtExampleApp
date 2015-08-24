package brach.stefan.dae.server.json.from;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import brach.stefan.dae.helper.JB;
import brach.stefan.dae.model.AdminConnectionDto;

public class AdminConnectionsFromJson {
    public static ArrayList<AdminConnectionDto> parse(JSONArray connectionsJa) {
        ArrayList<AdminConnectionDto> connections = new ArrayList<>();
        if (connectionsJa != null) {
            for (int i = 0; i < connectionsJa.length(); i++) {
                JSONObject adminConnectionJo = JB.optJOFromJA(connectionsJa, i);
                AdminConnectionDto adminConnectionDto = new AdminConnectionDto();
                String email = JB.optString(adminConnectionJo, "email");
                adminConnectionDto.setEmail(email);
                JSONArray connectedToEmailsJa = JB.optJAFromJO(adminConnectionJo, "connectedToEmails");
                for (int j = 0; j < connectedToEmailsJa.length(); j++) {
                    String connectedToEmail = connectedToEmailsJa.optString(j);
                    adminConnectionDto.addConnectedToEmail(connectedToEmail);
                }
                connections.add(adminConnectionDto);
            }
        }
        return connections;
    }
}
