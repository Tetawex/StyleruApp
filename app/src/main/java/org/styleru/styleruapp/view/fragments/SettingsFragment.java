package org.styleru.styleruapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 29.03.2017.
 */

public class SettingsFragment extends Fragment {
    public static final int IDM_HSE = 101;
    public static final int IDM_REU = 102;
    public static final int IDM_ALL = 103;

    @BindView(R.id.vuz)
    TextView vuz;
    @BindView(R.id.but)
    RelativeLayout but;
    @BindView(R.id.exit)
    TextView exit;
    private ToolbarInteractor toolbarInteractor;
    public SettingsFragment() {
        // Required empty public constructor
    }
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this,view);
        toolbarInteractor=(ToolbarInteractor)getActivity();
        toolbarInteractor.setToolbarTitleMode(ToolbarInteractor.Mode.BASIC);
        toolbarInteractor.setToolbarTitle(getString(R.string.profile));
        toolbarInteractor.setToolbarElevationDp(0);
        setHasOptionsMenu(true);
        registerForContextMenu(but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().openContextMenu(but);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singletons.getPreferencesManager().setAuthToken("");
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
//                getFragmentManager().finish();
            }
        });
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, IDM_HSE, Menu.NONE, "ВШЭ");
        menu.add(Menu.NONE, IDM_REU, Menu.NONE, "РЭУ");
        menu.add(Menu.NONE, IDM_ALL, Menu.NONE, "ВСЕ");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case IDM_HSE:
               vuz.setText("ВШЭ");
                break;
            case IDM_REU:
                vuz.setText("РЭУ");
                break;
            case IDM_ALL:
                vuz.setText("ВСЕ");
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
