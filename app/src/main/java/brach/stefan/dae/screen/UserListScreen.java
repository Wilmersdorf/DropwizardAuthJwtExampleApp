package brach.stefan.dae.screen;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.R;
import brach.stefan.dae.adapter.list.UserListAdapter;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.Logger;
import brach.stefan.dae.helper.LogoutHelper;
import brach.stefan.dae.layout.FooterBuilder;
import brach.stefan.dae.layout.LayoutHelper;
import brach.stefan.dae.model.ConnectionDto;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.server.thread.ChangeConnectionThread;
import brach.stefan.dae.server.thread.GetConnectionsThread;

public class UserListScreen extends Screen {
    private MainActivity ma;
    private String authToken;
    private String email;
    private UserListAdapter userListAdapter;

    public UserListScreen(MainActivity ma) {
        this.ma = ma;
        initialize();
    }

    private void initialize() {
        ma.setContentView(R.layout.list_screen);
        authToken = PreferencesHelper.getAuthToken(ma);
        email = PreferencesHelper.getEmail(ma);
        Logger.log(email);
        LayoutHelper.setTextViewText(ma, R.id.tv_email, email);
        ListView listView = (ListView) ma.findViewById(R.id.lv);
        listView.addFooterView(FooterBuilder.createFooterView(ma));
        getConnections(false);
        setOnClickListeners();
    }

    private void getConnections(boolean showMessage) {
        GetConnectionsThread getConnectionsThread = new GetConnectionsThread(ma, authToken, email, showMessage);
        ma.getExecutor().execute(getConnectionsThread);
    }

    private void setOnClickListeners() {
        LinearLayout logoutLin = (LinearLayout) ma.findViewById(R.id.ll_logout);
        logoutLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutHelper.logout(ma);
            }
        });
        LinearLayout refreshLin = (LinearLayout) ma.findViewById(R.id.ll_refresh);
        refreshLin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getConnections(true);
            }
        });
    }

    public void setConnections(ArrayList<ConnectionDto> connections) {
        if (userListAdapter == null) {
            userListAdapter = new UserListAdapter(ma, this, connections);
            ListView listView = (ListView) ma.findViewById(R.id.lv);
            listView.setAdapter(userListAdapter);
        } else {
            userListAdapter.setData(connections);
            userListAdapter.notifyDataSetChanged();
        }
    }

    public void changeConnection(String otherUserEmail, boolean isConnected) {
        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setEmail(otherUserEmail);
        connectionDto.setConnected(isConnected);
        String authToken = PreferencesHelper.getAuthToken(ma);
        ChangeConnectionThread thread = new ChangeConnectionThread(ma, email, authToken, connectionDto);
        MainActivity.getExecutor().execute(thread);
    }

    @Override
    public ScreenType getType() {
        return ScreenType.UserListScreen;
    }
}
