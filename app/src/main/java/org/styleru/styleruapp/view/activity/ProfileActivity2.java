package org.styleru.styleruapp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.model.dto.ProfileItem;
import org.styleru.styleruapp.model.dto.ProfileResponse;
import org.styleru.styleruapp.presenter.ProfilePresenter;
import org.styleru.styleruapp.presenter.ProfilePresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.ProfileView;
import org.styleru.styleruapp.view.adapter.recycler.ProfileProjectsRecyclerAdapter;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabProjects;

import java.util.List;

import butterknife.BindView;


public class ProfileActivity2 extends AppCompatActivity implements ProfileView {
    private static final int DEFAULT_BATCH_SIZE=10;
    private ProfileFragmentTabProjects.OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private ProfileProjectsRecyclerAdapter recyclerAdapter;
    private String authToken;
    private ProfilePresenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ExpandableLayout expandableLayoutTimeline,expandableLayoutCompetence,expandableLayoutProjects;
    private RecyclerView recyclerView;
    private String asd;
    @BindView(R.id.recycler_compete)
    public RecyclerView recycler_compete;
    @BindView(R.id.recycler_projects)
    public RecyclerView recycler_projects;
    @BindView(R.id.recycler_timeline)
    public RecyclerView recycler_timeline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_vol_2);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Это сабтайтд");
        expandableLayoutTimeline = (ExpandableLayout) findViewById(R.id.expandable_layout_timeline);
        expandableLayoutCompetence = (ExpandableLayout) findViewById(R.id.expandable_layout_competence);
        expandableLayoutProjects = (ExpandableLayout) findViewById(R.id.expandable_layout_projects);
        authToken=Singletons.getPreferencesManager().getAuthToken();


        presenter=new ProfilePresenterImpl(this);
        presenter.onProfileCreate(authToken,1);





//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerAdapter=new ProfileProjectsRecyclerAdapter(this,new ArrayList<ProfileProjectsItem>());
//        recyclerView.setAdapter(recyclerAdapter);
//        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
//                (LinearLayoutManager) recyclerView.getLayoutManager()) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                presenter.onProfileCreate();
//                presenter.onDataAppend(recyclerAdapter.getItemCount(),DEFAULT_BATCH_SIZE);
//            }
//        };

 //       chart.invalidate();


        FrameLayout clickerCompetence = (FrameLayout) findViewById(R.id.frame_competence) ;
        ImageView imgCompetenceCollapse = (ImageView) findViewById(R.id.down_competence);
        clickerCompetence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayoutCompetence.isExpanded()) {
                    expandableLayoutCompetence.collapse();
                    imgCompetenceCollapse.setBackgroundResource(R.drawable.arrow_down);
                }

                else
                {
                    expandableLayoutCompetence.expand();
                    imgCompetenceCollapse.setBackgroundResource(R.drawable.arrow_up);
                }
            }
        });
        FrameLayout clickerTimeline = (FrameLayout) findViewById(R.id.frame_timeline);
        ImageView imgTimelineCollapse = (ImageView) findViewById(R.id.down_timeline);
        clickerTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayoutTimeline.isExpanded())
                {
                    expandableLayoutTimeline.collapse();
                    imgTimelineCollapse.setBackgroundResource(R.drawable.arrow_down);
                }
                else
                {
                    expandableLayoutTimeline.expand();
                    imgTimelineCollapse.setBackgroundResource(R.drawable.arrow_up);
                }
            }
        });
        FrameLayout clickerProjects = (FrameLayout) findViewById(R.id.frame_projects);
        ImageView imgProjectCollapse = (ImageView) findViewById(R.id.down_projects);
        clickerProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayoutProjects.isExpanded())
                {
                    expandableLayoutProjects.collapse();
                    imgProjectCollapse.setBackgroundResource(R.drawable.arrow_down);
                }
                else
                {
                    expandableLayoutProjects.expand();
                    imgProjectCollapse.setBackgroundResource(R.drawable.arrow_up);
                }
            }
        });
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        ImageView provileid = (ImageView) findViewById(R.id.profile_id);
        UserInfo info = Singletons.getUserInfo();

//        name.setText(info.getLastName());
//        presenter.onSingleProfileAppend(info.getToken(),info.getUserId());
//        surname.setText(item.surname);
//        name.setText(item.name);
        Glide
                .with(this)
                .load(info.getImageUrl())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.placeholder_loading_circled)
                .into(provileid);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.user_name));
        collapsingToolbarLayout.setExpandedTitleMargin(20,0,0,60);
//
//        TextView compet = (TextView) findViewById(R.id.compet);
        dynamicToolbarColor();
        toolbarTextAppernce();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset)
            {
                Drawable upArrow = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null);
                Drawable settings = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_settings, null);
//                if (offset < -100)
//                    compet.setVisibility(View.INVISIBLE);
//                else
//                    compet.setVisibility(View.VISIBLE);
                if (offset < -200)
                {
                    upArrow.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    settings.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_ATOP);
                    toolbar.setOverflowIcon(settings);


                }
                else
                {

                    upArrow.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                    getSupportActionBar().setHomeAsUpIndicator(upArrow);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    settings.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_ATOP);
                    toolbar.setOverflowIcon(settings);
                }
            }
        });
    }

//    private void prepateProjectsdata() {
//        ProjectsItem projectsItem = new  ProjectsItem(1,"Дмитрий","Ринат",true,true,"16.06.99");
//        ProjectsList.add(projectsItem);
//
//
//        mAdapter.notifyDataSetChanged();
//    }
    private void dynamicToolbarColor() {

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.galochka);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int primaryDark = getResources().getColor(R.color.colorPrimary);
                int primary = getResources().getColor(R.color.colorPrimary);
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
            }
        });
    }


    private void toolbarTextAppernce() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedAppbar);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showError(Throwable throwable) {

    }

    @Override
    public void startProgressBar() {

    }

    @Override
    public void stopProgressBar() {

    }


    @Override
    public void setData(ProfileResponse response) {

    }

    @Override
    public void appendData(ProfileResponse response) {
       List<ProfileItem> a= response.getData();
        String m =a.get(8).toString();
        Toast toast = Toast.makeText(getApplicationContext(),
                m, Toast.LENGTH_SHORT);
        toast.show();
    }

//    @Override
//    public void appendData(ProfileResponse response) {
//
//    }


}