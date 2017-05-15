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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.model.dto.ProfileItem;
import org.styleru.styleruapp.model.dto.support.Skill;
import org.styleru.styleruapp.presenter.ProfilePresenter;
import org.styleru.styleruapp.presenter.ProfilePresenterImpl;
import org.styleru.styleruapp.view.ProfileView;
import org.styleru.styleruapp.view.adapter.recycler.CompetenceRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.ProfileProjectsRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.TimelineRecyclerAdapter;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabProjects;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProfileActivity2 extends AppCompatActivity implements ProfileView {
    private static final int DEFAULT_BATCH_SIZE=10;
    private ProfileFragmentTabProjects.OnFragmentInteractionListener mListener;
    private ProfilePresenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ExpandableLayout expandableLayoutTimeline,expandableLayoutCompetence,expandableLayoutProjects;
    private CompetenceRecyclerAdapter competenceRecyclerAdapter;
    private ProfileProjectsRecyclerAdapter projectsRecyclerAdapter;
    private TimelineRecyclerAdapter timelineRecyclerAdapter;
    @BindView(R.id.profile_id)
    public ImageView imageView;
    @BindView(R.id.progressbar)
    public View progressbar;
    @BindView(R.id.text_skills_list)
    public TextView textSkillsList;
    @BindView(R.id.text_phone)
    public TextView textPhone;
    @BindView(R.id.text_email)
    public TextView textEmail;
    @BindView(R.id.text_review)
    public TextView textReview;
    Toolbar toolbar;

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
        ButterKnife.bind(this);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Это сабтайтд");
        expandableLayoutTimeline = (ExpandableLayout) findViewById(R.id.expandable_layout_timeline);
        expandableLayoutCompetence = (ExpandableLayout) findViewById(R.id.expandable_layout_competence);
        expandableLayoutProjects = (ExpandableLayout) findViewById(R.id.expandable_layout_projects);

        competenceRecyclerAdapter=new CompetenceRecyclerAdapter(this, Collections.emptyList());
        projectsRecyclerAdapter=new ProfileProjectsRecyclerAdapter(this, Collections.emptyList());
        timelineRecyclerAdapter=new TimelineRecyclerAdapter(this, Collections.emptyList());

        recycler_compete.setLayoutManager(new LinearLayoutManager(this));
        recycler_compete.setAdapter(competenceRecyclerAdapter);
        recycler_projects.setLayoutManager(new LinearLayoutManager(this));
        recycler_projects.setAdapter(projectsRecyclerAdapter);
        recycler_timeline.setLayoutManager(new LinearLayoutManager(this));
        recycler_timeline.setAdapter(timelineRecyclerAdapter);

        presenter=new ProfilePresenterImpl(this);

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
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleMargin(20,0,0,60);
//
//        TextView compet = (TextView) findViewById(R.id.compet);
        dynamicToolbarColor();
        toolbarTextAppearance();
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
        //presenter.onRequestProfileData(getIntent().getIntExtra("id",1));

        presenter.onRequestProfileData(1);
    }

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


    private void toolbarTextAppearance() {
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
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopProgressBar() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void inflateData(ProfileItem item) {
        competenceRecyclerAdapter.setDataWithNotify(item.getSkills());
        timelineRecyclerAdapter.setDataWithNotify(item.getTimeline());
        projectsRecyclerAdapter.setDataWithNotify(item.getProjects());
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.user_name));
        textPhone.setText(item.getPhone());
        textReview.setText(item.getSummary());
        textEmail.setText(item.getEmail());
        toolbar.setTitle(item.getFirst_name()+" "+item.getLast_name());
        Glide
                .with(this)
                .load(item.getImage_url())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.placeholder_loading_circled)
                .into(imageView);
        String list="";
        for (Skill skill:item.getSkills()) {
            list=list+skill.getName()+", ";
        }
        textSkillsList.setText(list.substring(list.length()-2));
    }
}