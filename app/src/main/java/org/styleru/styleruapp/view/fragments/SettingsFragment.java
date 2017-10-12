package org.styleru.styleruapp.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.dto.SettingsDataModel;
import org.styleru.styleruapp.model.dto.support.Settings;
import org.styleru.styleruapp.model.dto.support.University;
import org.styleru.styleruapp.presenter.SettingsPresenter;
import org.styleru.styleruapp.presenter.SettingsPresenterImpl;
import org.styleru.styleruapp.view.SettingsView;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 29.03.2017.
 */

public class SettingsFragment extends Fragment implements SettingsView {
    @BindView(R.id.vuz)
    public TextView vuzTextView;
    @BindView(R.id.but)
    public View vuzClickTarget;
    @BindView(R.id.progressbar)
    public View progressbar;
    @BindView(R.id.exit)
    public TextView exitTextView;
    private SettingsPresenter presenter;
    private ToolbarInteractor toolbarInteractor;

    private Settings settings;
    private SettingsDataModel settingsDataModel;
    private MenuItem syncMenuItem;

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
        ButterKnife.bind(this, view);
        toolbarInteractor = (ToolbarInteractor) getActivity();
        toolbarInteractor.setToolbarTitleMode(ToolbarInteractor.Mode.BASIC);
        toolbarInteractor.setToolbarTitle(getString(R.string.settings));
        toolbarInteractor.setToolbarElevationDp(4);
        setHasOptionsMenu(true);
        registerForContextMenu(vuzClickTarget);
        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onLogout();
            }
        });
        presenter = new SettingsPresenterImpl(this);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_settings, menu);
        syncMenuItem = menu.findItem(R.id.action_sync);
        syncMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                presenter.onSettingsSync(settings);
                return true;
            }
        });
        syncMenuItem.setEnabled(false);
        super.onCreateOptionsMenu(menu, inflater);
        presenter.onSettingsModelDownload();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(SettingsDataModel data) {
        settingsDataModel = data;
        settings = new Settings();
        settings.setUniversityId(null);
        vuzTextView.setText(R.string.all);
        for (University u : settingsDataModel.getUniversitiesList()) {
            if (u.isChecked()) {
                settings.setUniversityId(u.getId());
                vuzTextView.setText(u.getName());
            }
        }
        vuzClickTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());
                //builderSingle.setIcon(R.drawable.ic_explore);
                builderSingle.setTitle("");

                final ArrayAdapter<University> arrayAdapter =
                        new ArrayAdapter<University>(getContext(), R.layout.view_dialog_item);
                University university = new University();
                university.setChecked(false);
                university.setId(null);
                university.setName(getString(R.string.all));
                for (University u : settingsDataModel.getUniversitiesList()) {
                    arrayAdapter.add(u);
                }
                arrayAdapter.add(university);
                builderSingle.setNegativeButton(R.string.decline, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which).getName();
                        settings.setUniversityId(arrayAdapter.getItem(which).getIdInteger());
                        vuzTextView.setText(arrayAdapter.getItem(which).getName());
                        /*AlertDialog.Builder builderInner = new AlertDialog.Builder(getContext());
                        builderInner.setMessage(strName);
                        builderInner.setTitle("Your Selected Item is");
                        builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,int which) {
                                dialog.dismiss();
                            }
                        });
                        builderInner.show();*/
                        dialog.dismiss();
                    }
                });
                builderSingle.show();
            }
        });
    }

    @Override
    public void switchToLoginPage() {
        Singletons.getPreferencesManager().setAuthToken("");
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void notifySyncState(int state) {
        if (state == 1)
            Toast.makeText(getContext(), R.string.settings_sync_success, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(), R.string.settings_sync_fail, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
        syncMenuItem.setEnabled(false);
    }

    @Override
    public void stopProgressBar() {
        progressbar.setVisibility(View.GONE);
        syncMenuItem.setEnabled(true);
    }
}
