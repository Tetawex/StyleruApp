package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.DepartmentsItem;
import org.styleru.styleruapp.model.dto.ParticipantsItem;
import org.styleru.styleruapp.view.activity.ProfileActivity;
import org.styleru.styleruapp.view.activity.ProfileActivity2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class ProjectParticipantsRecyclerAdapter extends BaseRecyclerAdapter<ParticipantsItem> {
    public ProjectParticipantsRecyclerAdapter(Context context, List<ParticipantsItem> data) {
        super(context, data);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.view_project_participants_recycleritem,parent,false);
        ProjectParticipantsViewHolder  holder;
        holder = new ProjectParticipantsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        ParticipantsItem item= getData().get(position);
        ProjectParticipantsViewHolder holder=(ProjectParticipantsViewHolder) uncastedHolder;
        holder.name.setText(item.getName());
        holder.duty.setText(item.getDuty());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("id",item.getId());
                bundle.putString("name",item.getName());
                Intent intent = new Intent(context,ProfileActivity2.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    class ProjectParticipantsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.parent)
        View parent;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.duty)
        TextView duty;
        public ProjectParticipantsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
