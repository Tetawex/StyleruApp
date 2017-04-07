package org.styleru.styleruapp.view.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.styleru.styleruapp.R;
import org.styleru.styleruapp.model.dto.ProfileProjectsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Пользователь on 03.04.2017.
 */

public class ProfileProjectsRecyclerAdapter extends BaseRecyclerAdapter<ProfileProjectsItem> {
    public ProfileProjectsRecyclerAdapter(Context context, List<ProfileProjectsItem> data) {
        super(context, data);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.card_profile_projects,parent,false);
        ProfileProjectsViewHolder  holder;
        holder = new ProfileProjectsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder uncastedHolder, int position) {
        ProfileProjectsItem item= getData().get(position);
        ProfileProjectsViewHolder holder=(ProfileProjectsViewHolder) uncastedHolder;



        holder.secname.setText(item.getRole());
        holder.name.setText(item.getTitle());


//        if(item.isViewAttendants()) {
//            holder.attendance.setVisibility(View.GONE);
//        }
//        else {
//            holder.attendance.setVisibility(View.VISIBLE);
//        }

    }

    class ProfileProjectsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;


        @BindView(R.id.secname)
        TextView secname;


        @BindView(R.id.state)
        TextView state;


//        @BindView(R.id.attendance)
//        Button attendance;


        public ProfileProjectsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
