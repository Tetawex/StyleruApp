package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.EventsItem;
import org.styleru.styleruapp.model.dto.support.Request;
import org.styleru.styleruapp.view.EventsView;
import org.styleru.styleruapp.view.VacancyView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class VacancyRecyclerAdapter extends MappedIdRecyclerAdapter<Request> {
    private VacancyView vacancyView;

    private boolean canApprove;
    private boolean canRecommend;

    public VacancyRecyclerAdapter(Context context, List<Request> data,
                                  VacancyView vacancyView,
                                  boolean canApprove,boolean canRecommend) {
        super(context, data);
        this.vacancyView = vacancyView;
        this.canApprove=canApprove;
        this.canRecommend=canRecommend;
    }
    public VacancyRecyclerAdapter(Context context, List<Request> data,
                                  VacancyView vacancyView) {
        this(context,data,vacancyView,false,false);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recycleritem_vacancy,parent,false);
        VacancyViewHolder  holder;
        holder = new VacancyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        VacancyViewHolder holder =(VacancyViewHolder)uncastedHolder;
        Request item=getData().get(position);
        holder.name.setText(item.getPeopleName());
        if(item.isRecommended())
            holder.indicatorRecommended.setVisibility(View.VISIBLE);
        else
            holder.indicatorRecommended.setVisibility(View.GONE);
        if(canRecommend) {
            holder.recommendButton.setVisibility(View.VISIBLE);
            holder.recommendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vacancyView.onRecommendVacancy(item.getId(),item.getPeopleName(),item.isRecommended());
                }
            });
        }
        else
            holder.recommendButton.setVisibility(View.GONE);
        if(canApprove) {
            holder.approveButton.setVisibility(View.VISIBLE);
            holder.approveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    vacancyView.onApproveVacancy(item.getId(),item.getPeopleName());
                }
            });
        }
        else
            holder.approveButton.setVisibility(View.GONE);
        Glide
                .with(context)
                .load(item.getImage())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.placeholder_loading_circled)
                .into(new BitmapImageViewTarget(holder.image) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        holder.image.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public void setPrivileges(boolean canApprove,boolean canRecommend){
        this.canApprove=canApprove;
        this.canRecommend=canRecommend;
        notifyDataSetChanged();
    }

    public void tickVacancyByIdWithNotify(int id) {
        Request r=getItemById(id);
        r.setRecommended(!r.isRecommended());
        notifyItemChanged(getData().indexOf(r));
    }

    class VacancyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;

        //@BindView(R.id.departments)
        //TextView departments;

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.recommended_indicator)
        ImageView indicatorRecommended;

        @BindView(R.id.button_approve)
        AppCompatButton approveButton;

        @BindView(R.id.button_recommend)
        AppCompatButton recommendButton;
        public VacancyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
