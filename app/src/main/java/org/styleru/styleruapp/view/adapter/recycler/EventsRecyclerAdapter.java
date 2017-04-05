package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.EventsItem;

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
        View view=inflater.inflate(R.layout.card_event_vol_4,parent,false);
        EventsViewHolder  holder;
        holder = new EventsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        EventsItem item= getData().get(position);
        EventsViewHolder holder=(EventsViewHolder) uncastedHolder;

        DateTime itemDateTime=new DateTime(item.getDateTime().replace(' ','T'));

        String fullDateTimeString=itemDateTime.toString(DateTimeFormat.longDate());
        holder.date.setText(fullDateTimeString.substring(0, fullDateTimeString.length() - 8));

        holder.time.setText(new DateTime(item.getDateTime().replace(' ','T')).toString("HH:mm"));
        holder.title1.setText(item.getTitle());
        //if(!(item.getImageUrl()).equals("")) {
        if(item.getImageUrl()!=null) {
            holder.imageHolder.setVisibility(View.VISIBLE);


            Glide
                    .with(context)
                    .load(item.getImageUrl())
                    .into(holder.image);
        }
        else {
            holder.imageHolder.setVisibility(View.GONE);


        }

        /*if(item.isViewAttendants()) {
            holder.attendance.setVisibility(View.GONE);
        }
        else {
            holder.attendance.setVisibility(View.VISIBLE);
        }*/

        holder.subtitle.setText(item.getSubtitle());
        holder.location.setText(item.getLocation());
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title1;


        @BindView(R.id.subtitle)
        TextView subtitle;


        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.location)
        TextView location;

        @BindView(R.id.image_holder)
        View imageHolder;

        @BindView(R.id.image)
        ImageView image;

        //@BindView(R.id.attendance)
        //Button attendance;


        public EventsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
