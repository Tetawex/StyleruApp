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
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.util.DrawerLocker;
import org.styleru.styleruapp.view.ToolbarInteractor;
import org.styleru.styleruapp.view.fragments.DepartmentsFragment;
import org.styleru.styleruapp.view.fragments.EventsFragment;
import org.styleru.styleruapp.view.fragments.PeopleFragment;
import org.styleru.styleruapp.view.fragments.ProfileFragment1;
import org.styleru.styleruapp.view.fragments.ProjectsFragment;
import org.styleru.styleruapp.view.fragments.SettingsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,DrawerLocker,ToolbarInteractor {
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
    @BindView(R.id.toolbar_title)
    public TextView toolbarText;
    @BindView(R.id.toolbar_spinner)
    public Spinner toolbarSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

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
        switchFragment(R.id.nav_events);
        UserInfo info = null;
        try {
            info = Singletons.getUserInfo();
        }catch (NullPointerException npe){
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
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
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switchFragment(id);
        return true;
    }
    public void switchFragment(int id){
        final android.support.v4.app.FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        switch(id) {

            case (R.id.nav_people):
                transaction.replace(R.id.container, new PeopleFragment());
                break;
            case (R.id.nav_departments):
                transaction.replace(R.id.container, new DepartmentsFragment());
                break;
            case (R.id.nav_events):
                transaction.replace(R.id.container, new EventsFragment());
                break;
            case (R.id.nav_profile):
//                transaction.replace(R.id.container, new ProfileFragment());
                Intent intent = new Intent(getApplication(), ProfileActivity2.class);
                startActivity(intent);
                break;
            case (R.id.nav_projects):
                transaction.replace(R.id.container, new ProjectsFragment());
                break;
            case (R.id.nav_settings):
                transaction.replace(R.id.container, new SettingsFragment());
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

    @Override
    public void setDrawerEnabled(boolean enabled) {
        int lockMode = enabled ? DrawerLayout.LOCK_MODE_UNLOCKED :
                DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawer.setDrawerLockMode(lockMode);
    }

    @Override
    public void setToolbarTitleMode(Mode mode) {
        if(mode==Mode.BASIC){
            toolbarText.setVisibility(View.VISIBLE);
            toolbarSpinner.setVisibility(View.GONE);
        }
        else {
            toolbarSpinner.setVisibility(View.VISIBLE);
            toolbarText.setVisibility(View.GONE);
        }
    }

    @Override
    public void setToolbarElevationDp(float elevationDp) {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            appBarLayout.setElevation(TypedValue.applyDimension(TypedValue.
                    COMPLEX_UNIT_DIP, elevationDp, getResources().getDisplayMetrics()));
    }

    @Override
    public void setToolbarVisible(boolean visible) {
        if(visible)
            toolbar.setVisibility(View.VISIBLE);
        else
            toolbar.setVisibility(View.GONE);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbarText.setText(title);
    }

    @Override
    public void setToolbarSpinnerAdapter(SpinnerAdapter adapter) {
        toolbarSpinner.setAdapter(adapter);
    }

    @Override
    public void setToolbarSpinnerListener(AdapterView.OnItemSelectedListener listener) {
        toolbarSpinner.setOnItemSelectedListener(listener);
    }
}
