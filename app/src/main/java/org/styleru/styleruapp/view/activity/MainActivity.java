package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.view.fragments.DepartmentsFragment;
import org.styleru.styleruapp.view.fragments.EventsFragment;
import org.styleru.styleruapp.view.fragments.PeopleFragment;
import org.styleru.styleruapp.view.fragments.ProfileFragment;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabOverall;
import org.styleru.styleruapp.view.fragments.ProjectsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ActionBarDrawerToggle toggle;

    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.appbarlayout)
    public AppBarLayout appBarLayout;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setTitle("АКТИВИТИ");


        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        switchFragment(R.id.nav_events);
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switchFragment(id);
        return true;
    }
    public void switchFragment(int id){
        final android.support.v4.app.FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        switch(id) {
            case(R.id.nav_people):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new PeopleFragment());
                break;
            case(R.id.nav_departments):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new DepartmentsFragment());
                break;
            case(R.id.nav_events):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new EventsFragment());
                break;
            case(R.id.nav_profile):
                setAppBarElevation(0);
                transaction.replace(R.id.container, new ProfileFragment());
                break;
            case(R.id.nav_projects):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new ProjectsFragment());
                break;
        }
        transaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
    public void setAppBarElevation(float elevationDp){
        if(android.os.Build.VERSION.SDK_INT >= 21)
            appBarLayout.setElevation(TypedValue.applyDimension(TypedValue.
                    COMPLEX_UNIT_DIP, elevationDp, getResources().getDisplayMetrics()));
    }

}
