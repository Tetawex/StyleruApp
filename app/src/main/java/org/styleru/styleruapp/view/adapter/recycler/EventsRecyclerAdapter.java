package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.graphics.Color;
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
import org.styleru.styleruapp.view.EventsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class EventsRecyclerAdapter extends MappedIdRecyclerAdapter<EventsItem> {
    private EventsView eventsView;
    public EventsRecyclerAdapter(Context context, List<EventsItem> data, EventsView eventsView) {
        super(context, data);
        this.eventsView = eventsView;
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
        holder.dateTime.setText(fullDateTimeString.substring(0, fullDateTimeString.length() - 8)
                +", "+new DateTime(itemDateTime).toString("HH:mm"));
        holder.attendantsCount.setText(item.getAttendantsCount()+"");
        holder.title.setText(item.getTitle());
        if(item.getState()<0) {
            holder.buttonGo.setBackgroundColor(Color.parseColor("#eee"));
            holder.buttonGo.setText(context.getString(R.string.go_wait));
            holder.buttonGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });
        }
        else if(itemDateTime.isAfter(DateTime.now())){
            if(item.getState()==1) {
                holder.buttonGo.setBackgroundColor(context
                        .getResources().getColor(R.color.colorButtonUnsubscribe));
                holder.buttonGo.setText(context.getString(R.string.go_future_positive));
                holder.buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.buttonGo.setBackgroundColor(context
                                .getResources().getColor(R.color.colorButtonWait));
                        holder.buttonGo.setText(context.getString(R.string.go_wait));
                        EventsRecyclerAdapter.this.eventsView.requestChangeEventState(item.getId());
                        item.setState(item.getState()-2);
                    }
                });
            }
            else {
                holder.buttonGo.setBackgroundColor(context
                        .getResources().getColor(R.color.colorButtonSubscribe));
                holder.buttonGo.setText(context.getString(R.string.go_future_negative));
                holder.buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.buttonGo.setBackgroundColor(context
                                .getResources().getColor(R.color.colorButtonWait));
                        holder.buttonGo.setText(context.getString(R.string.go_wait));
                        EventsRecyclerAdapter.this.eventsView.requestChangeEventState(item.getId());
                        item.setState(item.getState()-2);
                    }
                });
            }
        }
        else {
            if(item.getState()==1) {
                holder.buttonGo.setBackgroundColor(context
                        .getResources().getColor(R.color.colorButtonVisited));
                holder.buttonGo.setText(context.getString(R.string.go_past_positive));
                holder.buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
            else {
                holder.buttonGo.setBackgroundColor(context
                        .getResources().getColor(R.color.colorButtonNotVisited));
                holder.buttonGo.setText(context.getString(R.string.go_past_negative));
                holder.buttonGo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    }
                });
            }
        }

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

        holder.subtitle.setText(item.getSubtitle());
        holder.location.setText(item.getLocation());
    }
    class EventsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.subtitle)
        TextView subtitle;


        @BindView(R.id.date_time)
        TextView dateTime;

        @BindView(R.id.location)
        TextView location;

        @BindView(R.id.image_holder)
        View imageHolder;

        @BindView(R.id.image)
        ImageView image;

        @BindView(R.id.button_go)
        Button buttonGo;

        @BindView(R.id.button_attendants)
        Button buttonAttendants;

        @BindView(R.id.attendants_count)
        TextView attendantsCount;

        public EventsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
