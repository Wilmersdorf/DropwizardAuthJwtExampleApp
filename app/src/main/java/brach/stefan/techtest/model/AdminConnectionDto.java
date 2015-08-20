package brach.stefan.techtest.model;

import android.text.TextUtils;

import java.util.ArrayList;

public class AdminConnectionDto {
    private String email;
    private ArrayList<String> connectedToEmails = new ArrayList<String>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getConnectedToEmails() {
        return connectedToEmails;
    }

    public void setConnectedToEmails(ArrayList<String> connectedToEmails) {
        this.connectedToEmails = connectedToEmails;
    }

    public void addConnectedToEmail(String connectedToEmail) {
        if (connectedToEmails == null) {
            connectedToEmails = new ArrayList<>();
        }
        if (!TextUtils.isEmpty(connectedToEmail)) {
            connectedToEmails.add(connectedToEmail);
        }
    }
}
