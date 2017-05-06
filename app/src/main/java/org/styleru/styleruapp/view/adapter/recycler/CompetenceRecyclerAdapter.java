package org.styleru.styleruapp.view.adapter.recycler;


import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.support.Skill;
import org.styleru.styleruapp.model.dto.support.Subskill;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompetenceRecyclerAdapter extends BaseRecyclerAdapter<Skill> {
    public CompetenceRecyclerAdapter(Context context, List<Skill> data) {
        super(context, data);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.layout_compete,parent,false);
        CompetenceViewHolder  holder;
        holder = new CompetenceViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {

        Skill item= getData().get(position);
        List<Subskill> item2 = item.getSubskills();

        CompetenceViewHolder holder=(CompetenceViewHolder) uncastedHolder;
        holder.text.setText(item.getName());



        holder.chart.setTouchEnabled(false);
        holder.chart.setDescription("");
        holder.chart.getLegend().setEnabled(false);
        YAxis yAxis = holder.chart.getYAxis();
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
        holder.chart.setData(data);


    }

    class CompetenceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.chart)
        RadarChart chart;


        public CompetenceViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

