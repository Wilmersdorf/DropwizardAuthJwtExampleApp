package brach.stefan.techtest.screen;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import brach.stefan.techtest.MainActivity;
import brach.stefan.techtest.R;
import brach.stefan.techtest.adapter.list.AdminListAdapter;
import brach.stefan.techtest.enums.ScreenType;
import brach.stefan.techtest.helper.LogoutHelper;
import brach.stefan.techtest.layout.LayoutHelper;
import brach.stefan.techtest.model.AdminConnectionDto;
import brach.stefan.techtest.preferences.PreferencesHelper;
import brach.stefan.techtest.server.thread.GetAdminConnectionsThread;

public class AdminListScreen extends Screen {
    private MainActivity ma;
    private String authToken;
    private String email;
    private AdminListAdapter adminListAdapter;

    public AdminListScreen(MainActivity ma) {
        this.ma = ma;
        initialize();
    }

    private void initialize() {
        ma.setContentView(R.layout.list_screen);
        authToken = PreferencesHelper.getAuthToken(ma);
        email = PreferencesHelper.getEmail(ma);
        LayoutHelper.setTextViewText(ma, R.id.tv_email, email);
        getConnections();
        setOnClickListeners();
    }

    private void getConnections() {
        GetAdminConnectionsThread getAdminConnectionsThread = new GetAdminConnectionsThread(ma, authToken, email);
        ma.getExecutor().execute(getAdminConnectionsThread);
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
                getConnections();
            }
        });
    }

    public void setConnections(ArrayList<AdminConnectionDto> connections) {
        if (adminListAdapter == null) {
            adminListAdapter = new AdminListAdapter(ma, this, connections);
            ListView listView = (ListView) ma.findViewById(R.id.lv);
            listView.setAdapter(adminListAdapter);
        } else {
            adminListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public ScreenType getType() {
        return ScreenType.AdminListScreen;
    }
}
