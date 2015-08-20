package brach.stefan.techtest.adapter.list;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.R;
import brach.stefan.techtest.model.ConnectionDto;
import brach.stefan.techtest.screen.UserListScreen;

public class UserListAdapter extends BaseAdapter {
    private UserListScreen screen;
    private ArrayList<ConnectionDto> connections;
    private MainActivity ma;

    public UserListAdapter(MainActivity ma, UserListScreen screen, ArrayList<ConnectionDto> connections) {
        this.ma = ma;
        this.screen = screen;
        setData(connections);
    }

    public void setData(ArrayList<ConnectionDto> connections) {
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
            layout = (LinearLayout) ma.getLayoutInflater().inflate(R.layout.user_connection, null);
        } else {
            layout = (LinearLayout) convertView;
        }
        final ConnectionDto connection = connections.get(position);
        TextView nameTv = (TextView) layout.findViewById(R.id.tv_name);
        nameTv.setText(connection.getEmail());
        SwitchButton switchBtn = (SwitchButton) layout.findViewById(R.id.sb);
        switchBtn.setChecked(connection.isConnected());
        switchBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                screen.changeConnection(connection.getEmail(), isChecked);
            }
        });
        return layout;
    }
}
