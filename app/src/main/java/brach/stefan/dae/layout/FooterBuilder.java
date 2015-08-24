package brach.stefan.dae.layout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import brach.stefan.dae.MainActivity;

public class FooterBuilder {
    public static View createFooterView(MainActivity ma) {
        View view = new View(ma);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = Units.DpToPx(10, ma);
        view.setLayoutParams(params);
        return view;
    }
}
