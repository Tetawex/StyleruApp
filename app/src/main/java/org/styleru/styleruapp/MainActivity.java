package org.styleru.styleruapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.styleru.styleruapp.view.fragments.DirectionsFragment;
import org.styleru.styleruapp.view.fragments.EventsFragment;
import org.styleru.styleruapp.view.fragments.PeopleFragment;
import org.styleru.styleruapp.view.fragments.ProjectsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DirectionsFragment fdirect;
    EventsFragment fevents;
    PeopleFragment fpeople;
    ProjectsFragment fprojects;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fdirect = new DirectionsFragment();
        fevents = new EventsFragment();
        fpeople = new PeopleFragment();
        fprojects = new ProjectsFragment();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }
////
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final android.support.v4.app.FragmentTransaction ftrans = getSupportFragmentManager().beginTransaction();


        if (id == R.id.nav_people) {
           ftrans.replace(R.id.container, fpeople);

        } else if (id == R.id.nav_projects) {
            ftrans.replace(R.id.container, fprojects);

        } else if (id == R.id.nav_direct) {
            ftrans.replace(R.id.container, fdirect);
        } else if (id == R.id.nav_events) {
            ftrans.replace(R.id.container, fevents);

        }
        ftrans.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
//    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//    setSupportActionBar(toolbar);
//
//
//    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        if (id == R.id.nav_people) {
//        ftrans.replace(R.id.container, fpeople);
//        toolbar.setTitle("Профиль человека");
//        } else if (id == R.id.nav_projects) {
//        ftrans.replace(R.id.container, fprojects);
//        toolbar.setTitle("Проекты");
//        } else if (id == R.id.nav_direct) {
//        ftrans.replace(R.id.container, fdirect);
//        toolbar.setTitle("Направления");
//        } else if (id == R.id.nav_events) {
//        ftrans.replace(R.id.container, fevents);
//        toolbar.setTitle("Мероприятия");
//
//        }
//        ftrans.commit();
