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
import org.styleru.styleruapp.model.dto.PeopleItem;
import org.styleru.styleruapp.view.activity.ProfileActivity;
import org.styleru.styleruapp.view.activity.ProfileActivity2;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class PeopleRecyclerAdapter extends BaseRecyclerAdapter<PeopleItem> {
    public PeopleRecyclerAdapter(Context context, List<PeopleItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_people_vol2, parent, false);
        PeopleViewHolder holder;
        holder = new PeopleViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {

        PeopleItem item = getData().get(position);
        PeopleViewHolder holder = (PeopleViewHolder) uncastedHolder;

        holder.name.setText(item.getFirstName() + " " + item.getLastName());
        holder.phone.setText(item.getPhone());
        StringBuilder builder = new StringBuilder();
        for (String s : item.getDepartmentNames()) {
            builder.append(s).append(", ");
        }
        for (String s : item.getSubdepartmentNames()) {
            builder.append(s).append(", ");
        }
        String result = builder.toString();
        if (result != "")
            result = result.substring(0, result.length() - 2);
        holder.departments.setText(result);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", item.getId());
                bundle.putString("name", item.getFirstName() + " " + item.getLastName());
                Intent intent = new Intent(context, ProfileActivity2.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        Glide
                .with(context)
                .load(item.getImageUrl())
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

    class PeopleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.parent)
        View parent;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.departments)
        TextView departments;

        public PeopleViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
