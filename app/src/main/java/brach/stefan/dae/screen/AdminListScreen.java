package brach.stefan.dae.screen;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import brach.stefan.dae.MainActivity;
import brach.stefan.dae.R;
import brach.stefan.dae.adapter.list.AdminListAdapter;
import brach.stefan.dae.enums.ScreenType;
import brach.stefan.dae.helper.LogoutHelper;
import brach.stefan.dae.layout.FooterBuilder;
import brach.stefan.dae.layout.LayoutHelper;
import brach.stefan.dae.model.AdminConnectionDto;
import brach.stefan.dae.preferences.PreferencesHelper;
import brach.stefan.dae.server.thread.GetAdminConnectionsThread;

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
        ListView listView = (ListView) ma.findViewById(R.id.lv);
        listView.addFooterView(FooterBuilder.createFooterView(ma));
        getConnections(false);
        setOnClickListeners();
    }

    private void getConnections(boolean showMessage) {
        GetAdminConnectionsThread getAdminConnectionsThread = new GetAdminConnectionsThread(ma, authToken, email, showMessage);
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
                getConnections(true);
            }
        });
    }

    public void setConnections(ArrayList<AdminConnectionDto> connections) {
        if (adminListAdapter == null) {
            adminListAdapter = new AdminListAdapter(ma, this, connections);
            ListView listView = (ListView) ma.findViewById(R.id.lv);
            listView.setAdapter(adminListAdapter);
        } else {
            adminListAdapter.setData(connections);
            adminListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public ScreenType getType() {
        return ScreenType.AdminListScreen;
    }
}
