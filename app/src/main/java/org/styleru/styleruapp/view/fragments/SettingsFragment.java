package org.styleru.styleruapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.SettingsDataModel;
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

    private SettingsDataModel settingsDataModel;
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
        toolbarInteractor.setToolbarTitle(getString(R.string.settings));
        toolbarInteractor.setToolbarElevationDp(4);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_settings, menu);
        MenuItem item = menu.findItem(R.id.action_sync);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
