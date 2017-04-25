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
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.cache.Singletons;
import org.styleru.styleruapp.model.cache.UserInfo;

import java.util.ArrayList;

/**
 * Created by Пользователь on 15.04.2017.
 */

public class ProfileActivity2 extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout = null;
    private ExpandableLayout expandableLayout1,expandableLayout2;
    ImageView down;
    public boolean flag = true;
    public boolean flag2= true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile_vol_2);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setSubtitle("Это сабтайтд");
//        if(android.os.Build.VERSION.SDK_INT >= 21)





        down = (ImageView) findViewById(R.id.down);


        expandableLayout1 = (ExpandableLayout) findViewById(R.id.expandable_layout_1);
        expandableLayout1.collapse();
        expandableLayout2 = (ExpandableLayout) findViewById(R.id.expandable_layout_2);
        expandableLayout2.collapse();



        RadarChart chart = (RadarChart) findViewById(R.id.chart);
        ArrayList<Entry> entries = new ArrayList<>();
                entries.add(new Entry(1f, 0));
                entries.add(new Entry(2f, 1));
                entries.add(new Entry(3f, 2));
                entries.add(new Entry(2f, 3));
                entries.add(new Entry(2f, 4));
                entries.add(new Entry(1f, 5));
        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "Компетенции" );
        dataset_comp1.setColor(Color.CYAN);
       dataset_comp1.setDrawFilled(true);
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
        String description = "";
        chart.setDescription(description);
//        chart.invalidate();


        FrameLayout clicker2 = (FrameLayout) findViewById(R.id.clickable2) ;
        clicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag2 == true){
                    expandableLayout2.collapse();
                    flag2 = false;
                }
                {
                    expandableLayout2.expand();

                    flag2 = true;
                }
            }
        });

        FrameLayout clicker = (FrameLayout) findViewById(R.id.clickable1);
        clicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == true){
                expandableLayout1.collapse();
                    flag = false;
            }
                {
                    expandableLayout1.expand();

                    flag = true;
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
        dynamicToolbarColor();
        toolbarTextAppernce();
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int offset)
            {
                Drawable upArrow = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_arrow_back, null);
                Drawable settings = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_settings, null);
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


}