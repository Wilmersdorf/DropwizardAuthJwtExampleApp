package brach.stefan.techtest.adapter.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.R;
import brach.stefan.techtest.model.AdminConnectionDto;
import brach.stefan.techtest.screen.AdminListScreen;

public class AdminListAdapter extends BaseAdapter {
    private AdminListScreen screen;
    private ArrayList<AdminConnectionDto> connections;
    private MainActivity ma;

    public AdminListAdapter(MainActivity ma, AdminListScreen screen, ArrayList<AdminConnectionDto> connections) {
        this.ma = ma;
        this.screen = screen;
        setData(connections);
    }

    public void setData(ArrayList<AdminConnectionDto> connections) {
        this.connections = connections;
    }

    @Override
    public int getCount() {
        return connections.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = null;
        if (convertView == null) {
            layout = (LinearLayout) ma.getLayoutInflater().inflate(R.layout.admin_connection, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        final AdminConnectionDto connection = connections.get(position);
        TextView nameTv = (TextView) layout.findViewById(R.id.tv_name);
        nameTv.setText(connection.getEmail());
        TextView connectionsTv = (TextView) layout.findViewById(R.id.tv_connections);
        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> connectedToEmails = connection.getConnectedToEmails();
        for (int i = 0; i < connectedToEmails.size(); i++) {
            stringBuilder.append(connectedToEmails.get(i));
            if (i < connectedToEmails.size() - 1) {
                stringBuilder.append(",\n");
            }
        }
        connectionsTv.setText(stringBuilder.toString());
        return layout;
    }
}
