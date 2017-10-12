package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.TimelineItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 06.04.2017.
 */

public class TimelineRecyclerAdapter extends BaseRecyclerAdapter<TimelineItem> {
    private DateTimeFormatter formatter;

    public TimelineRecyclerAdapter(Context context, List<TimelineItem> data) {
        super(context, data);
        formatter = DateTimeFormat.forPattern("dd.MM.yyyy");
    }

    public View view;
    public String preposition;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_profile_timeline, parent, false);
        TimelineViewHolder holder;
        holder = new TimelineViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {

        TimelineItem item = getData().get(position);
        TimelineViewHolder holder = (TimelineViewHolder) uncastedHolder;
//        holder.date.setText(context.getString(R.string.until)+" "+itemDateTime.toString(formatter));
//        if(item.getStatus()== true)
        holder.text.setText(item.getName() + " " + item.getAction() + " " + item.getAction_name() + " " + item.getDate());
        {
            preposition = " в проект ";
            holder.back.setBackgroundResource(R.drawable.gal);
        }
//        else
//            {preposition=" из проекта ";
//                holder.back.setBackgroundResource(R.drawable.vmin);}
//        holder.text.setText(item.getName()+" "+item.getSurname() + preposition + item.getProject()+" "+item.getDate());
    }

    class TimelineViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text)
        TextView text;
        @BindView(R.id.back)
        RelativeLayout back;

        public TimelineViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
