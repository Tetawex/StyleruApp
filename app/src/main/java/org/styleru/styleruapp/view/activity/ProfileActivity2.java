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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;
import org.styleru.styleruapp.model.dto.DepartmentsItem;
import org.styleru.styleruapp.model.dto.ProfileProjectsItem;
import org.styleru.styleruapp.presenter.DepartmentsPresenterImpl;
import org.styleru.styleruapp.presenter.ProfileProjectsPresenter;
import org.styleru.styleruapp.presenter.ProfileProjectsPresenterImpl;
import org.styleru.styleruapp.util.EndlessRecyclerViewScrollListener;
import org.styleru.styleruapp.view.ProfileProjectsView;
import org.styleru.styleruapp.view.adapter.recycler.DepartmentsRecyclerAdapter;
import org.styleru.styleruapp.view.adapter.recycler.ProfileProjectsRecyclerAdapter;
import org.styleru.styleruapp.view.fragments.ProfileFragmentTabProjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Пользователь on 15.04.2017.
 */

public class ProfileActivity2 extends AppCompatActivity implements ProfileProjectsView {
    private static final int DEFAULT_BATCH_SIZE=10;
    private ProfileFragmentTabProjects.OnFragmentInteractionListener mListener;
    private EndlessRecyclerViewScrollListener recyclerViewScrollListener;
    private ProfileProjectsRecyclerAdapter recyclerAdapter;

    private ProfileProjectsPresenter presenter;
    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ExpandableLayout expandableLayoutTimeline,expandableLayoutCompetence,expandableLayoutProjects;
    private RecyclerView recyclerView;
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
        recyclerView= (RecyclerView) findViewById(R.id.proj) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter=new ProfileProjectsRecyclerAdapter(this,new ArrayList<ProfileProjectsItem>());
        recyclerView.setAdapter(recyclerAdapter);

        recyclerViewScrollListener = new EndlessRecyclerViewScrollListener(
                (LinearLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                presenter.onDataAppend(recyclerAdapter.getItemCount(),DEFAULT_BATCH_SIZE);
            }
        };
        recyclerView.addOnScrollListener(recyclerViewScrollListener);
        presenter=new ProfileProjectsPresenterImpl(this);
        presenter.onDataUpdate(DEFAULT_BATCH_SIZE);


        RadarChart chart = (RadarChart) findViewById(R.id.chart);
        chart.setTouchEnabled(false);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);
        YAxis yAxis = chart.getYAxis();
        yAxis.setEnabled(false);
        yAxis.setAxisMaxValue(3);
        yAxis.setAxisMinValue(1);

        ArrayList<Entry> entries = new ArrayList<>();
                entries.add(new Entry(1f, 0));
                entries.add(new Entry(2f, 1));
                entries.add(new Entry(3f, 2));
                entries.add(new Entry(2f, 3));
                entries.add(new Entry(2f, 4));
                entries.add(new Entry(1f, 5));

        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "Компетенции" );
        dataset_comp1.setColor(Color.rgb(40,199,192));
        dataset_comp1.setDrawFilled(true);
        dataset_comp1.setValueTextSize(0);

        ArrayList<RadarDataSet> dataSets = new ArrayList<RadarDataSet>();
        dataSets.add(dataset_comp1);

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("scrum");
        labels.add("Тайм-менеджмент");
        labels.add("ведение проекта");
        labels.add("командообразования");
        labels.add("Мотивация");
        labels.add("переговоры");

        RadarData data = new RadarData(labels, dataSets);
        chart.setData(data);
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
    public void appendData(List<ProfileProjectsItem> data) {
        recyclerAdapter.appendDataWithNotify(data);
    }



    @Override
    public void setData(List<ProfileProjectsItem> data) {
        onDataUpdated();
        recyclerAdapter.setDataWithNotify(data);
    }

    public void onDataUpdated()
    {
        recyclerViewScrollListener.resetState();
    }
//
//    public String[] getDataSet() {
//        String[] mDataSet = new String[100];
//        for (int i = 0; i < 100; i++) {
//            mDataSet[i] = "item" + i;
//        }
//        return mDataSet;
//    }


}