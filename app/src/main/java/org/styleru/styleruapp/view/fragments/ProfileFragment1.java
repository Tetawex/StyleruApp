package org.styleru.styleruapp.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.adapter.tab.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment1 extends Fragment {
    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;

    private ToolbarInteractor toolbarInteractor;

    public ProfileFragment1() {
        // Required empty public constructor
    }

    public static ProfileFragment1 newInstance(String param1, String param2) {
        ProfileFragment1 fragment = new ProfileFragment1();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        toolbarInteractor = (ToolbarInteractor) getActivity();
        toolbarInteractor.setToolbarTitleMode(ToolbarInteractor.Mode.BASIC);
        toolbarInteractor.setToolbarTitle(getString(R.string.profile));
        toolbarInteractor.setToolbarElevationDp(0);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        setupViewPager(viewPager);
        if (android.os.Build.VERSION.SDK_INT >= 21)
            tabLayout.setElevation(TypedValue.applyDimension(TypedValue.
                    COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        if (android.os.Build.VERSION.SDK_INT >= 21)
            toolbarInteractor.setToolbarElevationDp(0);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ProfileFragmentTabOverall(), getContext().getString(R.string.profile));
        adapter.addFragment(new ProfileFragmentTabProjects(), getContext().getString(R.string.projects));
        adapter.addFragment(new ProfileFragmentTabTimeline(), getContext().getString(R.string.timeline));
        viewPager.setAdapter(adapter);
    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        MainActivity activity = (MainActivity) getActivity();
//        MenuInflater inflater1 = activity.getMenuInflater();
//        inflater1.inflate(R.menu.settings, menu);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}