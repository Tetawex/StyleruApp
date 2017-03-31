package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
public RelativeLayout rel;
    public Bitmap myBitmap;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_event_vol_4,parent,false);
        rel = (RelativeLayout) view.findViewById(R.id.image);
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
        if(!(item.getImageUrl()).equals("")) {
            rel.setVisibility(View.VISIBLE);

            holder.title2.setText("");
            holder.title1.setText(item.getTitle());
        }
        else {
            rel.setVisibility(View.GONE);
            holder.title2.setText(item.getTitle());
//            try {
//                Bitmap myBitmap = Glide.with(context)
//                        .load(item.getImageUrl())
//                        .asBitmap()
//                        .centerCrop()
//                        .into(500, 500)
//                        .get();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
//            rel.setBackground(new BitmapDrawable(context.getResources(), myBitmap));



            holder.title1.setText("");
        }

        if(item.isViewAttendants()) {
            holder.attendance.setVisibility(View.GONE);
        }
        else {
            holder.attendance.setVisibility(View.VISIBLE);
        }

        holder.author.setText(item.getAuthor());
        holder.subtitle.setText(item.getSubtitle());
        holder.location.setText(item.getLocation());
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title1)
        TextView title1;

        @BindView(R.id.title2)
        TextView title2;

        @BindView(R.id.subtitle)
        TextView subtitle;

        @BindView(R.id.author)
        TextView author;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.location)
        TextView location;

        @BindView(R.id.attendance)
        Button attendance;


        public EventsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
