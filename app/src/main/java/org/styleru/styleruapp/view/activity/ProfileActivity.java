package org.styleru.styleruapp.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.view.adapter.tab.ViewPagerAdapter;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabOverall;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabProjects;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabTimeline;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 05.04.2017.
 */

public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    public TabLayout tabLayout;
    @BindView(R.id.view_pager)
    public ViewPager viewPager;
    @BindView(R.id.toolbar_profile1)
    public Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        toolbar.setTitle("Настройки");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragmentTabOverall(), getString(R.string.profile));
        adapter.addFragment(new ProfileFragmentTabProjects(), getString(R.string.projects));
        adapter.addFragment(new ProfileFragmentTabTimeline(), getString(R.string.timeline));
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}