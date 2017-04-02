package org.styleru.styleruapp.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.view.fragments.DepartmentsFragment;
import org.styleru.styleruapp.view.fragments.EventsFragment;
import org.styleru.styleruapp.view.fragments.PeopleFragment;
import org.styleru.styleruapp.view.fragments.ProfileFragment;
import org.styleru.styleruapp.view.fragments.ProjectsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private ActionBarDrawerToggle toggle;

    public ImageView imageView;
    public TextView name;
    public TextView email;

    @BindView(R.id.appbar)
    public AppBarLayout appBarLayout;
    @BindView(R.id.toolbar)
    public Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawer;
    public Button butset;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private View.OnClickListener a;


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
        View headerView = navigationView.getHeaderView(0);
        imageView =(ImageView) headerView.findViewById(R.id.user_image);
        name=(TextView) headerView.findViewById(R.id.name);
        email=(TextView) headerView.findViewById(R.id.email);
//        FrameLayout frame= (FrameLayout) findViewById(R.id.frame);
//        if(android.os.Build.VERSION.SDK_INT < 21)
//            frame.setPadding(0,16,0,0);
        switchFragment(R.id.nav_events);

        UserInfo info = Singletons.getUserInfo();
        name.setText(info.getFirstName()+" "+info.getLastName());
        email.setText(info.getEmail());
        Glide
                .with(this)
                .load(info.getImageUrl())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.placeholder_loading_circled)
                .into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_LOW_PROFILE;
        //decorView.setSystemUiVisibility(uiOptions); Зачем это?
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//        navigationView.setOnClickListener(OnClickyes());

//        butset = (Button) findViewById(R.id.btn_settings);
//        butset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), SettingsActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }

//    private View.OnClickListener OnClickyes() {
//        butset = (Button) findViewById(R.id.btn_settings);
//        butset.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplication(), SettingsActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        return a;
//    }

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

            case (R.id.nav_people):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new PeopleFragment());
                break;
            case (R.id.nav_departments):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new DepartmentsFragment());
                break;
            case (R.id.nav_events):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new EventsFragment());
                break;
            case (R.id.nav_profile):
                setAppBarElevation(0);
                transaction.replace(R.id.container, new ProfileFragment());
                break;
            case (R.id.nav_projects):
                setAppBarElevation(4);
                transaction.replace(R.id.container, new ProjectsFragment());
                break;
            case (R.id.nav_settings):
                Intent intent = new Intent(getApplication(), SettingsActivity.class);
                startActivity(intent);
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
