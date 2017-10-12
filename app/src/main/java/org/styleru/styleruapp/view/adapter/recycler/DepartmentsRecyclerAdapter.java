package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.DepartmentsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tetawex on 07.03.17.
 */

public class DepartmentsRecyclerAdapter extends BaseRecyclerAdapter<DepartmentsItem> {
    public DepartmentsRecyclerAdapter(Context context, List<DepartmentsItem> data) {
        super(context, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_departments, parent, false);
        DepartmentsViewHolder holder;
        holder = new DepartmentsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        DepartmentsItem item = getData().get(position);
        DepartmentsViewHolder holder = (DepartmentsViewHolder) uncastedHolder;
        holder.email.setText(item.getEmail());
        holder.managerName.setText(item.getManager());
        holder.name.setText(item.getName());
        holder.phone.setText(item.getPhone());
        Glide
                .with(context)
                .load(item.getImgUrl())
                .asBitmap().centerCrop()
                .placeholder(R.drawable.placeholder_loading)
                .into(holder.image);

    }

    class DepartmentsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.manager_name)
        TextView managerName;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.phone)
        TextView phone;

        public DepartmentsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
