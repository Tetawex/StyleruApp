package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.ocpsoft.prettytime.PrettyTime;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.EventsItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class EventsRecyclerAdapter extends BaseRecyclerAdapter<EventsItem> {
    public EventsRecyclerAdapter(Context context, List<EventsItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_events,parent,false);
        EventsViewHolder  holder;
        holder = new EventsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        EventsItem item= getData().get(position);
        EventsViewHolder holder=(EventsViewHolder) uncastedHolder;

        DateTime itemDateTime=new DateTime(item.getDateTime());

        String fullDateTimeString=itemDateTime.toString(DateTimeFormat.longDate());
        holder.date.setText(fullDateTimeString.substring(0, fullDateTimeString.length() - 8));

        holder.time.setText(new DateTime(item.getDateTime()).toString("HH:mm"));
        holder.location.setText(item.getLocation());
        holder.image.setAdjustViewBounds(true);
        if(!(item.getImageUrl()).equals("")) {
            holder.image.setVisibility(View.VISIBLE);
            Glide
                    .with(context)
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.placeholder_loading)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(holder.image);
        }
        else
            holder.image.setVisibility(View.GONE);
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.subtitle)
        TextView subtitle;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.location)
        TextView location;

        @BindView(R.id.image)
        ImageView image;

        public EventsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
