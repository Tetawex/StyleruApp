package org.styleru.styleruapp.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.styleru.styleruapp.view.activity.MainActivity;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.view.adapter.tab.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment {
    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        MainActivity activity = (MainActivity) getActivity();
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle(getContext().getString(R.string.profile));

        ButterKnife.bind(this,view);
        setupViewPager(viewPager);

        if(android.os.Build.VERSION.SDK_INT >= 21)
            tabLayout.setElevation(TypedValue.applyDimension(TypedValue.
                    COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
        if(android.os.Build.VERSION.SDK_INT >= 21)
            activity.getSupportActionBar().setElevation(TypedValue.applyDimension(TypedValue.
                    COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
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

}